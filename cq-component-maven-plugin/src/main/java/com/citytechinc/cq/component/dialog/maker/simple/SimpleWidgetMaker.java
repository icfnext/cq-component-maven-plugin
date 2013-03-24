package com.citytechinc.cq.component.dialog.maker.simple;

import java.lang.reflect.Field;
import java.util.Map;

import javassist.CtClass;
import javassist.CtField;

import com.citytechinc.cq.component.annotations.DialogField;
import com.citytechinc.cq.component.dialog.Widget;
import com.citytechinc.cq.component.dialog.impl.SimpleWidget;
import com.citytechinc.cq.component.dialog.maker.WidgetMaker;
import com.citytechinc.cq.component.dialog.maker.parent.AbstractWidgetMaker;

public class SimpleWidgetMaker extends AbstractWidgetMaker implements
		WidgetMaker {

	@Override
	public Widget make(String xtype, Field widgetField, CtField ctWidgetField,
			Class<?> containingClass, CtClass ctContainingClass, Map<Class<?>, String> xtypeMap) throws ClassNotFoundException {

		DialogField dialogFieldAnnotation = (DialogField) ctWidgetField.getAnnotation(DialogField.class);

		String name = getNameForField(dialogFieldAnnotation, widgetField);
		String fieldName = getFieldNameForField(dialogFieldAnnotation, widgetField);
		String fieldLabel = getFieldLabelForField(dialogFieldAnnotation, widgetField);
		String fieldDescription = getFieldDescriptionForField(dialogFieldAnnotation);
		Boolean isRequired = getIsRequiredForField(dialogFieldAnnotation);
		Map<String, String> additionalProperties = getAdditionalPropertiesForField(dialogFieldAnnotation);
		String defaultValue = getDefaultValueForField(dialogFieldAnnotation);

		return new SimpleWidget(
				xtype,
				name,
				fieldName,
				fieldLabel,
				fieldDescription,
				isRequired,
				additionalProperties,
				defaultValue);
	}

}
