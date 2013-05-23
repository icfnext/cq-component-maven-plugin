package com.citytechinc.cq.component.dialog.maker.impl;

import java.lang.reflect.AccessibleObject;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javassist.CannotCompileException;
import javassist.ClassPool;
import javassist.CtClass;
import javassist.CtMember;
import javassist.NotFoundException;

import com.citytechinc.cq.component.annotations.DialogField;
import com.citytechinc.cq.component.annotations.widgets.MultiField;
import com.citytechinc.cq.component.dialog.DialogElement;
import com.citytechinc.cq.component.dialog.exception.InvalidComponentFieldException;
import com.citytechinc.cq.component.dialog.factory.WidgetFactory;
import com.citytechinc.cq.component.dialog.impl.MultiFieldWidget;

import com.citytechinc.cq.component.dialog.maker.AbstractWidgetMaker;
import com.citytechinc.cq.component.dialog.maker.WidgetMaker;
import com.citytechinc.cq.component.maven.util.WidgetConfigHolder;

public class MultifieldWidgetMaker extends AbstractWidgetMaker {

	@Override
	public DialogElement make(String xtype, AccessibleObject widgetField, CtMember ctWidgetField,
		Class<?> containingClass, CtClass ctContainingClass, Map<Class<?>, WidgetConfigHolder> xtypeMap,
		Map<String, WidgetMaker> xTypeToWidgetMakerMap, ClassLoader classLoader, ClassPool classPool,
		boolean useDotSlashInName) throws ClassNotFoundException, InvalidComponentFieldException,
		CannotCompileException, NotFoundException, SecurityException, NoSuchFieldException {

		DialogField dialogFieldAnnotation = (DialogField) ctWidgetField.getAnnotation(DialogField.class);
		MultiField multiFieldAnnotation = (MultiField) ctWidgetField.getAnnotation(MultiField.class);

		String name = getNameForField(dialogFieldAnnotation, widgetField, useDotSlashInName);
		String fieldName = getFieldNameForField(dialogFieldAnnotation, widgetField);
		String fieldLabel = getFieldLabelForField(dialogFieldAnnotation, widgetField);
		String fieldDescription = getFieldDescriptionForField(dialogFieldAnnotation);
		Map<String, String> additionalProperties = getAdditionalPropertiesForField(dialogFieldAnnotation);
		String defaultValue = getDefaultValueForField(dialogFieldAnnotation);
		boolean hideLabel = dialogFieldAnnotation.hideLabel();

		boolean orderable = true;
		String addItemLabel = "Add Item";
		if (multiFieldAnnotation != null) {
			orderable = multiFieldAnnotation.orderable();
			addItemLabel = multiFieldAnnotation.addItemLabel();
		}

		DialogElement element = WidgetFactory.make(ctContainingClass, ctWidgetField, widgetField, xtypeMap,
			xTypeToWidgetMakerMap, classLoader, classPool, false, MultiFieldWidget.RANKING);
		element.setFieldName("fieldConfig");
		List<DialogElement> elements = new ArrayList<DialogElement>();
		elements.add(element);
		return new MultiFieldWidget(orderable, addItemLabel, fieldLabel, fieldDescription, true, hideLabel,
			defaultValue, name, fieldName, additionalProperties, elements);

	}

}
