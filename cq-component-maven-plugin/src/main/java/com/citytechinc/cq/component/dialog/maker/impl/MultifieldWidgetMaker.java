package com.citytechinc.cq.component.dialog.maker.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javassist.CannotCompileException;
import javassist.NotFoundException;

import com.citytechinc.cq.component.annotations.widgets.MultiField;
import com.citytechinc.cq.component.dialog.DialogElement;
import com.citytechinc.cq.component.dialog.exception.InvalidComponentFieldException;
import com.citytechinc.cq.component.dialog.factory.WidgetFactory;
import com.citytechinc.cq.component.dialog.field.DialogFieldMember;
import com.citytechinc.cq.component.dialog.impl.MultiFieldWidget;
import com.citytechinc.cq.component.dialog.maker.AbstractWidgetMaker;

public class MultifieldWidgetMaker extends AbstractWidgetMaker {

	public DialogElement make(DialogFieldMember field, String xtype, boolean useDotSlashInName)
		throws ClassNotFoundException, SecurityException, InvalidComponentFieldException, CannotCompileException,
		NotFoundException, NoSuchFieldException, InstantiationException, IllegalAccessException {

		MultiField multiFieldAnnotation = field.getAnnotation(MultiField.class);

		String name = getNameForField(field, useDotSlashInName);
		String fieldName = getFieldNameForField(field);
		String fieldLabel = getFieldLabelForField(field);
		String fieldDescription = getFieldDescriptionForField(field);
		Map<String, String> additionalProperties = getAdditionalPropertiesForField(field);
		String defaultValue = getDefaultValueForField(field);
		boolean hideLabel = getHideLabelForField(field);

		boolean orderable = getOrderableForField(multiFieldAnnotation);
		String addItemLabel = getAddItemLabelForField(multiFieldAnnotation);

		DialogElement element = WidgetFactory.make(field, false, MultiFieldWidget.RANKING);

		// TODO: Why is this needed?
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
