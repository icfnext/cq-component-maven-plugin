package com.citytechinc.cq.component.dialog.maker.impl;

import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

import javassist.CannotCompileException;
import javassist.CtMember;
import javassist.NotFoundException;

import org.codehaus.plexus.util.StringUtils;

import com.citytechinc.cq.component.annotations.DialogField;
import com.citytechinc.cq.component.annotations.widgets.DialogFieldSet;
import com.citytechinc.cq.component.dialog.DialogElement;
import com.citytechinc.cq.component.dialog.exception.InvalidComponentFieldException;
import com.citytechinc.cq.component.dialog.factory.WidgetFactory;
import com.citytechinc.cq.component.dialog.impl.DialogFieldSetWidget;
import com.citytechinc.cq.component.dialog.impl.WidgetCollection;
import com.citytechinc.cq.component.dialog.maker.AbstractWidgetMaker;
import com.citytechinc.cq.component.dialog.maker.WidgetMakerParameters;
import com.citytechinc.cq.component.maven.util.ComponentMojoUtil;

public class DialogFieldSetWidgetMaker extends AbstractWidgetMaker {

	public DialogFieldSetWidgetMaker(WidgetMakerParameters parameters) {
		super(parameters);
	}

	private static final String ITEMS = "items";

	public DialogElement make() throws ClassNotFoundException, SecurityException, InvalidComponentFieldException,
		NotFoundException, CannotCompileException, NoSuchFieldException, InstantiationException,
		IllegalAccessException, IllegalArgumentException, InvocationTargetException, NoSuchMethodException {

		DialogFieldSet dialogFieldSetAnnotation = getAnnotation(DialogFieldSet.class);

		String fieldName = getFieldNameForField();
		String fieldLabel = getFieldLabelForField();
		String fieldDescription = getFieldDescriptionForField();
		Map<String, String> additionalProperties = getAdditionalPropertiesForField();
		boolean hideLabel = getHideLabelForField();

		boolean collapseFirst = dialogFieldSetAnnotation.collapseFirst();
		boolean collapsible = dialogFieldSetAnnotation.collapsible();
		boolean collapsed = dialogFieldSetAnnotation.collapsed();
		boolean border = dialogFieldSetAnnotation.border();
		String title = null;
		if (!StringUtils.isEmpty(dialogFieldSetAnnotation.title())) {
			title = dialogFieldSetAnnotation.title();
		}

		List<DialogElement> widgetCollection = buildWidgetCollection();

		DialogFieldSetWidget widget = new DialogFieldSetWidget(collapseFirst, collapsible, collapsed, border, title,
			fieldLabel, fieldDescription, hideLabel, fieldName, additionalProperties, widgetCollection);

		setListeners(widget);

		return widget;

	}

	private List<DialogElement> buildWidgetCollection() throws InvalidComponentFieldException, NotFoundException,
		ClassNotFoundException, SecurityException, CannotCompileException, NoSuchFieldException,
		InstantiationException, IllegalAccessException, IllegalArgumentException, InvocationTargetException,
		NoSuchMethodException {

		List<CtMember> fieldsAndMethods = new ArrayList<CtMember>();

		fieldsAndMethods.addAll(ComponentMojoUtil.collectFields(getCtType()));
		fieldsAndMethods.addAll(ComponentMojoUtil.collectMethods(getCtType()));

		List<DialogElement> elements = new ArrayList<DialogElement>();

		for (CtMember curField : fieldsAndMethods) {
			if (curField.hasAnnotation(DialogField.class)) {

				Class<?> fieldClass = parameters.getClassLoader().loadClass(curField.getDeclaringClass().getName());

				WidgetMakerParameters curFieldMember = new WidgetMakerParameters(
					(DialogField) curField.getAnnotation(DialogField.class), curField, fieldClass,
					parameters.getClassLoader(), parameters.getClassPool(), parameters.getWidgetRegistry(), null, true);

				DialogElement builtFieldWidget = WidgetFactory.make(curFieldMember, -1);

				elements.add(builtFieldWidget);
			}
		}

		return Arrays.asList(new DialogElement[] { new WidgetCollection(elements, ITEMS) });

	}

}
