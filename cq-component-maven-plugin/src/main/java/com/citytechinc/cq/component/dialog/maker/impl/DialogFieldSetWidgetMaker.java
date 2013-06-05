package com.citytechinc.cq.component.dialog.maker.impl;

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
import com.citytechinc.cq.component.dialog.field.DialogFieldMember;
import com.citytechinc.cq.component.dialog.field.impl.DialogFieldMemberImpl;
import com.citytechinc.cq.component.dialog.impl.DialogFieldSetWidget;
import com.citytechinc.cq.component.dialog.impl.WidgetCollection;
import com.citytechinc.cq.component.dialog.maker.AbstractWidgetMaker;
import com.citytechinc.cq.component.maven.util.ComponentMojoUtil;

public class DialogFieldSetWidgetMaker extends AbstractWidgetMaker {

	private static final String ITEMS = "items";

	public DialogElement make(DialogFieldMember field, String xtype, boolean useDotSlashInName) throws ClassNotFoundException, SecurityException, InvalidComponentFieldException, NotFoundException, CannotCompileException, NoSuchFieldException, InstantiationException, IllegalAccessException {

		DialogFieldSet dialogFieldSetAnnotation = field.getAnnotation(DialogFieldSet.class);

		String fieldName = getFieldNameForField(field);
		String fieldLabel = getFieldLabelForField(field);
		String fieldDescription = getFieldDescriptionForField(field);
		Map<String, String> additionalProperties = getAdditionalPropertiesForField(field);
		boolean hideLabel = getHideLabelForField(field);

		boolean collapseFirst = dialogFieldSetAnnotation.collapseFirst();
		boolean collapsible = dialogFieldSetAnnotation.collapsible();
		boolean collapsed = dialogFieldSetAnnotation.collapsed();
		boolean border = dialogFieldSetAnnotation.border();
		String title = null;
		if (!StringUtils.isEmpty(dialogFieldSetAnnotation.title())) {
			title = dialogFieldSetAnnotation.title();
		}

		List<DialogElement> widgetCollection = buildWidgetCollection(field);

		DialogFieldSetWidget widget = new DialogFieldSetWidget(collapseFirst, collapsible, collapsed, border, title, fieldLabel,
			fieldDescription, hideLabel, fieldName, additionalProperties, widgetCollection);

		setListeners(widget, field.getAnnotation().listeners());

		return widget;

	}

	private List<DialogElement> buildWidgetCollection(DialogFieldMember field) throws InvalidComponentFieldException, NotFoundException, ClassNotFoundException, SecurityException, CannotCompileException, NoSuchFieldException, InstantiationException, IllegalAccessException {

		List<CtMember> fieldsAndMethods = new ArrayList<CtMember>();

		fieldsAndMethods.addAll(ComponentMojoUtil.collectFields(field.getCtType()));
		fieldsAndMethods.addAll(ComponentMojoUtil.collectMethods(field.getCtType()));

		List<DialogElement> elements = new ArrayList<DialogElement>();

		for (CtMember curField : fieldsAndMethods) {
			if (curField.hasAnnotation(DialogField.class)) {

				Class<?> fieldClass = field.getClassLoader().loadClass(curField.getDeclaringClass().getName());

				DialogFieldMember curFieldMember = new DialogFieldMemberImpl(
					(DialogField) curField.getAnnotation(DialogField.class),
					curField,
					fieldClass,
					field.getClassLoader(),
					field.getClassPool(),
					field.getWidgetRegistry()
					);

				DialogElement builtFieldWidget = WidgetFactory.make(curFieldMember, false, -1);

				elements.add(builtFieldWidget);
			}
		}

		return Arrays.asList(new DialogElement[] { new WidgetCollection(elements, ITEMS) });

	}

}
