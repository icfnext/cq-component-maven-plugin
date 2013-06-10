package com.citytechinc.cq.component.dialog.factory;

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
import com.citytechinc.cq.component.dialog.DialogElement;
import com.citytechinc.cq.component.dialog.exception.InvalidComponentClassException;
import com.citytechinc.cq.component.dialog.exception.InvalidComponentFieldException;
import com.citytechinc.cq.component.dialog.field.impl.DefaultDialogFieldMember;
import com.citytechinc.cq.component.dialog.impl.Dialog;
import com.citytechinc.cq.component.dialog.impl.Html5SmartImageWidget;
import com.citytechinc.cq.component.dialog.impl.Tab;
import com.citytechinc.cq.component.dialog.impl.WidgetCollection;
import com.citytechinc.cq.component.dialog.widget.WidgetRegistry;
import com.citytechinc.cq.component.maven.util.ComponentMojoUtil;

public class DialogFactory {

	private DialogFactory() {
	}

	public static Dialog make(CtClass componentClass, WidgetRegistry widgetRegistry, ClassLoader classLoader,
		ClassPool classPool) throws InvalidComponentClassException, InvalidComponentFieldException,
		ClassNotFoundException, CannotCompileException, NotFoundException, SecurityException, NoSuchFieldException,
		InstantiationException, IllegalAccessException {

		Component componentAnnotation = (Component) componentClass.getAnnotation(Component.class);

		if (componentAnnotation == null) {
			throw new InvalidComponentClassException();
		}

		Map<String, List<DialogElement>> tabMap = new LinkedHashMap<String, List<DialogElement>>();

		/*
		 * Get dialog title
		 */
		String dialogTitle = getDialogTitleForComponent(componentAnnotation);

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

				DefaultDialogFieldMember dialogFieldMember = new DefaultDialogFieldMember(dialogProperty, member,
					trueComponentClass, classLoader, classPool, widgetRegistry);

				DialogElement builtFieldWidget = WidgetFactory.make(dialogFieldMember, true, -1);

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
			tabList.add(new Tab(curMapKey, new WidgetCollection(tabMap.get(curMapKey))));
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
		return new Dialog(tabList, dialogTitle, componentAnnotation.fileName(), width, height);
	}

	private static final String getDialogTitleForComponent(Component component) {
		return component.value();
	}

	private static final String getTabStringForField(DialogField dialogProperty, Component component) {

		String tabString = dialogProperty.tab();

		if (StringUtils.isNotEmpty(tabString)) {
			return tabString;
		}

		return ComponentMojoUtil.transformClassNameToReadable(component.value());
	}

}
