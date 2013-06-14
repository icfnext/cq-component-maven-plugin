package com.citytechinc.cq.component.dialog.factory;

import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import javassist.CannotCompileException;
import javassist.ClassPool;
import javassist.CtClass;
import javassist.CtMember;
import javassist.NotFoundException;

import org.codehaus.plexus.util.StringUtils;

import com.citytechinc.cq.component.annotations.Component;
import com.citytechinc.cq.component.annotations.DialogField;
import com.citytechinc.cq.component.dialog.Dialog;
import com.citytechinc.cq.component.dialog.DialogElement;
import com.citytechinc.cq.component.dialog.DialogParameters;
import com.citytechinc.cq.component.dialog.exception.InvalidComponentClassException;
import com.citytechinc.cq.component.dialog.exception.InvalidComponentFieldException;
import com.citytechinc.cq.component.dialog.html5smartimage.Html5SmartImageWidget;
import com.citytechinc.cq.component.dialog.maker.WidgetMakerParameters;
import com.citytechinc.cq.component.dialog.tab.Tab;
import com.citytechinc.cq.component.dialog.tab.TabParameters;
import com.citytechinc.cq.component.dialog.widget.WidgetRegistry;
import com.citytechinc.cq.component.dialog.widgetcollection.WidgetCollection;
import com.citytechinc.cq.component.dialog.widgetcollection.WidgetCollectionParameters;
import com.citytechinc.cq.component.maven.util.ComponentMojoUtil;

public class DialogFactory {

	private DialogFactory() {
	}

	public static Dialog make(CtClass componentClass, WidgetRegistry widgetRegistry, ClassLoader classLoader,
		ClassPool classPool) throws InvalidComponentClassException, InvalidComponentFieldException,
		ClassNotFoundException, CannotCompileException, NotFoundException, SecurityException, NoSuchFieldException,
		InstantiationException, IllegalAccessException, IllegalArgumentException, InvocationTargetException,
		NoSuchMethodException {

		Component componentAnnotation = (Component) componentClass.getAnnotation(Component.class);

		Map<String, List<DialogElement>> tabMap = new LinkedHashMap<String, List<DialogElement>>();

		/*
		 * Get dialog title
		 */
		String dialogTitle = componentAnnotation.value();
		// TODO: anything else
		/*
		 * Setup Tabs from Component tab list if one exists
		 */
		List<String> tabsList = Arrays.asList(componentAnnotation.tabs());

		for (String curTab : tabsList) {
			tabMap.put(curTab, new ArrayList<DialogElement>());
		}

		List<CtMember> fieldsAndMethods = new ArrayList<CtMember>();
		fieldsAndMethods.addAll(ComponentMojoUtil.collectFields(componentClass));
		fieldsAndMethods.addAll(ComponentMojoUtil.collectMethods(componentClass));

		// Load the true class
		Class<?> trueComponentClass = classLoader.loadClass(componentClass.getName());

		List<DialogElement> tabList = new ArrayList<DialogElement>();
		List<DialogElement> imageTabList = new ArrayList<DialogElement>();
		/*
		 * Iterate through all fields establishing proper widgets for each
		 */
		for (CtMember member : fieldsAndMethods) {

			DialogField dialogProperty = (DialogField) member.getAnnotation(DialogField.class);

			if (dialogProperty != null) {

				WidgetMakerParameters parameters = new WidgetMakerParameters(dialogProperty, member,
					trueComponentClass, classLoader, classPool, widgetRegistry, null, true);

				DialogElement builtFieldWidget = WidgetFactory.make(parameters, -1);

				builtFieldWidget.setRanking(dialogProperty.ranking());

				if (builtFieldWidget instanceof Html5SmartImageWidget
					&& ((Html5SmartImageWidget) builtFieldWidget).isTab()) {
					imageTabList.add(builtFieldWidget);
				} else {
					String tabString = getTabStringForField(dialogProperty, componentAnnotation);

					if (!tabMap.containsKey(tabString)) {
						tabMap.put(tabString, new ArrayList<DialogElement>());
					}

					tabMap.get(tabString).add(builtFieldWidget);
				}
			}
		}

		for (String curMapKey : tabMap.keySet()) {
			WidgetCollectionParameters wcp = new WidgetCollectionParameters();
			wcp.setContainedElements(tabMap.get(curMapKey));
			WidgetCollection widgetCollection = new WidgetCollection(wcp);
			TabParameters tabParams = new TabParameters();
			tabParams.setTitle(curMapKey);
			tabParams.setContainedElements(Arrays.asList(new DialogElement[] { widgetCollection }));
			tabList.add(new Tab(tabParams));
		}
		tabList.addAll(imageTabList);
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

	private static final String getTabStringForField(DialogField dialogProperty, Component component) {

		String tabString = dialogProperty.tab();

		if (StringUtils.isNotEmpty(tabString)) {
			return tabString;
		}

		return component.value();
	}

}
