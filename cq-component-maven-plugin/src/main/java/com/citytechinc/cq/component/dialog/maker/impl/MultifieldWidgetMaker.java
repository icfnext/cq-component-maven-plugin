package com.citytechinc.cq.component.dialog.maker.impl;

import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javassist.CannotCompileException;
import javassist.NotFoundException;

import com.citytechinc.cq.component.annotations.widgets.MultiField;
import com.citytechinc.cq.component.dialog.DialogElement;
import com.citytechinc.cq.component.dialog.exception.InvalidComponentFieldException;
import com.citytechinc.cq.component.dialog.factory.WidgetFactory;
import com.citytechinc.cq.component.dialog.impl.MultiFieldWidget;
import com.citytechinc.cq.component.dialog.maker.AbstractWidgetMaker;
import com.citytechinc.cq.component.dialog.maker.WidgetMakerParameters;

public class MultifieldWidgetMaker extends AbstractWidgetMaker {

	public MultifieldWidgetMaker(WidgetMakerParameters parameters) {
		super(parameters);
	}

	public DialogElement make() throws ClassNotFoundException, SecurityException, InvalidComponentFieldException,
		CannotCompileException, NotFoundException, NoSuchFieldException, InstantiationException,
		IllegalAccessException, IllegalArgumentException, InvocationTargetException, NoSuchMethodException {

		MultiField multiFieldAnnotation = getAnnotation(MultiField.class);

		String name = getNameForField();
		String fieldName = getFieldNameForField();
		String fieldLabel = getFieldLabelForField();
		String fieldDescription = getFieldDescriptionForField();
		Map<String, String> additionalProperties = getAdditionalPropertiesForField();
		String defaultValue = getDefaultValueForField();
		boolean hideLabel = getHideLabelForField();

		boolean orderable = getOrderableForField(multiFieldAnnotation);
		String addItemLabel = getAddItemLabelForField(multiFieldAnnotation);
		parameters.setUseDotSlashInName(false);
		DialogElement element = WidgetFactory.make(parameters, MultiFieldWidget.RANKING);

		element.setFieldName("fieldConfig");

		List<DialogElement> elements = new ArrayList<DialogElement>();
		elements.add(element);

		return new MultiFieldWidget(orderable, addItemLabel, fieldLabel, fieldDescription, true, hideLabel,
			defaultValue, name, fieldName, additionalProperties, elements);

	}

	protected boolean getOrderableForField(MultiField annotation) {
		if (annotation != null) {
			return annotation.orderable();
		}

		return MultiField.ORDERABLE_DEFAULT;
	}

	protected String getAddItemLabelForField(MultiField annotation) {
		if (annotation != null) {
			return annotation.addItemLabel();
		}

		return MultiField.ADD_ITEM_LABEL_DEFAULT;
	}

}
