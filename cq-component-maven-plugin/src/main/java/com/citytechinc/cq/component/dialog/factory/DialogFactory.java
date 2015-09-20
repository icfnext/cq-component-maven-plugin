/**
 *    Copyright 2013 CITYTECH, Inc.
 *
 *    Licensed under the Apache License, Version 2.0 (the "License");
 *    you may not use this file except in compliance with the License.
 *    You may obtain a copy of the License at
 *
 *        http://www.apache.org/licenses/LICENSE-2.0
 *
 *    Unless required by applicable law or agreed to in writing, software
 *    distributed under the License is distributed on an "AS IS" BASIS,
 *    WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *    See the License for the specific language governing permissions and
 *    limitations under the License.
 */
package com.citytechinc.cq.component.dialog.factory;

import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.codehaus.plexus.util.StringUtils;

import com.citytechinc.cq.component.annotations.Component;
import com.citytechinc.cq.component.annotations.DialogField;
import com.citytechinc.cq.component.annotations.IgnoreDialogField;
import com.citytechinc.cq.component.annotations.Listener;
import com.citytechinc.cq.component.dialog.Dialog;
import com.citytechinc.cq.component.dialog.DialogElement;
import com.citytechinc.cq.component.dialog.DialogFieldConfig;
import com.citytechinc.cq.component.dialog.DialogParameters;
import com.citytechinc.cq.component.dialog.Listeners;
import com.citytechinc.cq.component.dialog.ListenersParameters;
import com.citytechinc.cq.component.dialog.TabbableDialogElement;
import com.citytechinc.cq.component.dialog.cqincludes.CQInclude;
import com.citytechinc.cq.component.dialog.cqincludes.CQIncludeParameters;
import com.citytechinc.cq.component.dialog.exception.InvalidComponentClassException;
import com.citytechinc.cq.component.dialog.exception.InvalidComponentFieldException;
import com.citytechinc.cq.component.dialog.maker.WidgetMakerParameters;
import com.citytechinc.cq.component.dialog.tab.Tab;
import com.citytechinc.cq.component.dialog.tab.TabParameters;
import com.citytechinc.cq.component.dialog.util.DialogUtil;
import com.citytechinc.cq.component.dialog.widget.WidgetRegistry;
import com.citytechinc.cq.component.dialog.widgetcollection.WidgetCollection;
import com.citytechinc.cq.component.dialog.widgetcollection.WidgetCollectionParameters;
import com.citytechinc.cq.component.maven.util.ComponentMojoUtil;
import com.citytechinc.cq.component.maven.util.LogSingleton;

import javassist.CannotCompileException;
import javassist.ClassPool;
import javassist.CtClass;
import javassist.CtMember;
import javassist.CtMethod;
import javassist.NotFoundException;

public class DialogFactory {
	
	private static final String DEFAULT_TAB_FIELD_NAME = "tab";

	private DialogFactory() {
	}

	public static Dialog make(CtClass componentClass, WidgetRegistry widgetRegistry, ClassLoader classLoader,
		ClassPool classPool) throws InvalidComponentClassException, InvalidComponentFieldException,
		ClassNotFoundException, CannotCompileException, NotFoundException, SecurityException, NoSuchFieldException,
		InstantiationException, IllegalAccessException, IllegalArgumentException, InvocationTargetException,
		NoSuchMethodException {

		Component componentAnnotation = (Component) componentClass.getAnnotation(Component.class);

		/*
		 * Get dialog title
		 */
		String dialogTitle = componentAnnotation.value();

		/*
		 * Setup Tabs from Component tab list
		 */
		List<TabHolder> tabsList = new ArrayList<TabHolder>();

		if (componentAnnotation.tabs().length == 0) {
			TabHolder tabHolder = new TabHolder();
			tabHolder.setTitle(dialogTitle);
			tabsList.add(tabHolder);
		} else {
			for (com.citytechinc.cq.component.annotations.Tab tab : componentAnnotation.tabs()) {
				if (StringUtils.isNotEmpty(tab.title()) && StringUtils.isNotEmpty(tab.classicUIPath())) {
					throw new InvalidComponentClassException("Tabs can have only a path or a title");
				}
				if (StringUtils.isNotEmpty(tab.title()) || StringUtils.isNotEmpty(tab.classicUIPath())) {
					TabHolder tabHolder = new TabHolder();
					if (StringUtils.isNotEmpty(tab.title())) {
						tabHolder.setTitle(tab.title());
					}

					Listener[] listeners = tab.listeners();

					if (listeners.length > 0) {
						ListenersParameters parameters = new ListenersParameters();
						parameters.setListenerAnnotations(listeners);
						tabHolder.setListeners(new Listeners(parameters));
					}

					if (StringUtils.isNotEmpty(tab.classicUIPath())) {
						CQIncludeParameters params = new CQIncludeParameters();
						params.setFieldName(DEFAULT_TAB_FIELD_NAME + tabsList.size());
						params.setPath(tab.classicUIPath());
						CQInclude cqincludes = new CQInclude(params);
						tabHolder.addElement(cqincludes);
					}
					tabsList.add(tabHolder);
				} else {
					tabsList.add(null);
				}
			}
		}
		List<CtMember> fieldsAndMethods = new ArrayList<CtMember>();
		fieldsAndMethods.addAll(ComponentMojoUtil.collectFields(componentClass));
		fieldsAndMethods.addAll(ComponentMojoUtil.collectMethods(componentClass));

		// Load the true class
		Class<?> trueComponentClass = classLoader.loadClass(componentClass.getName());

		/*
		 * Iterate through all fields establishing proper widgets for each
		 */
		for (CtMember member : fieldsAndMethods) {
			if (!member.hasAnnotation(IgnoreDialogField.class)) {
				DialogFieldConfig dialogFieldConfig = null;
				if (member instanceof CtMethod) {
					dialogFieldConfig = DialogUtil.getDialogFieldFromSuperClasses((CtMethod) member);
				} else {
					if (member.hasAnnotation(DialogField.class)) {
						dialogFieldConfig =
							new DialogFieldConfig((DialogField) member.getAnnotation(DialogField.class), member);
					}
				}

				if (dialogFieldConfig != null) {
					WidgetMakerParameters parameters =
						new WidgetMakerParameters(dialogFieldConfig, trueComponentClass, classLoader, classPool,
							widgetRegistry, null, true);

					DialogElement builtFieldWidget = WidgetFactory.make(parameters, -1);
					if (builtFieldWidget != null) {
						builtFieldWidget.setRanking(dialogFieldConfig.getRanking());
						
						int tabIndex = getTabIndexForDialogField(dialogFieldConfig, tabsList);

						if (tabIndex < 1 || tabIndex > tabsList.size()) {
							throw new InvalidComponentFieldException("Invalid tab index " + tabIndex + " for field "
								+ dialogFieldConfig.getFieldName());
						}

						tabsList.get(tabIndex - 1).addElement(builtFieldWidget);
					}
				}
			}
		}

		List<DialogElement> tabList = new ArrayList<DialogElement>();

		for (TabHolder tab : tabsList) {
			if (tab != null) {
				tabList.add(buildTabForDialogElementSet(tab));
			}
		}

		Integer width = null;
		Integer height = null;
		if (componentAnnotation.dialogWidth() > 0) {
			width = componentAnnotation.dialogWidth();
		}
		if (componentAnnotation.dialogHeight() > 0) {
			height = componentAnnotation.dialogHeight();
		}
		DialogParameters dialogParams = new DialogParameters();
		dialogParams.setContainedElements(Dialog.buildTabPanel(tabList));
		dialogParams.setTitle(dialogTitle);
		dialogParams.setFileName(componentAnnotation.fileName());
		dialogParams.setWidth(width);
		dialogParams.setHeight(height);
		return new Dialog(dialogParams);
	}

	private static final DialogElement buildTabForDialogElementSet(TabHolder tab) throws InvalidComponentFieldException {
		/*
		 * Verify that, if elements contains tabbable elements, that it only
		 * contains one element. If the elements set contains a single tab
		 * element, return it as the tab.
		 */
		for (int i = 0; i < tab.getElements().size(); i++) {
			DialogElement curElement = tab.getElements().get(i);
			if (curElement instanceof TabbableDialogElement) {
				LogSingleton.getInstance().debug("Tabbable widget found " + curElement.getFieldName());
				TabbableDialogElement curTabbableElement = (TabbableDialogElement) curElement;

				LogSingleton.getInstance().debug("Is Tab? " + curTabbableElement.isTab());

				if (curTabbableElement.isTab()) {
					if (i != 0 || tab.getElements().size() != 1) {
						throw new InvalidComponentFieldException(
							"A Tab dialog element can not be placed inside another tab.");
					}
					curTabbableElement.setTitle(tab.getTitle());
					return curElement;
				}
			}
		}

		/*
		 * Construct a new Tab object containing all provided elements
		 */
		WidgetCollectionParameters wcp = new WidgetCollectionParameters();
		wcp.setContainedElements(tab.getElements());
		WidgetCollection widgetCollection = new WidgetCollection(wcp);
		TabParameters tabParams = new TabParameters();
		tabParams.setTitle(tab.getTitle());
		tabParams.setContainedElements(Arrays.asList(new DialogElement[] { widgetCollection }));
		tabParams.setListeners(tab.getListeners());
		return new Tab(tabParams);
	}
	
	private static final int getTabIndexForDialogField(DialogFieldConfig dialogFieldConfig, List<TabHolder> tabsList) {
		int tabIndex = dialogFieldConfig.getTab();
		String tabTitle = dialogFieldConfig.getTabTitle();
		
		if (StringUtils.isNotBlank(tabTitle)) {
			for (int i = 0; i < tabsList.size(); i++) {
				TabHolder tabHolder = tabsList.get(i);
				String candidateTitle = tabHolder.getTitle();
				if (tabTitle.equals(candidateTitle)) {
					tabIndex = i + 1;
					break;
				}
			}
		}
		
		return tabIndex;
	}
}
