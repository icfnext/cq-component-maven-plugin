package com.citytechinc.cq.component.dialog.maker.impl;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

import com.citytechinc.cq.component.annotations.TagNameSpace;
import com.citytechinc.cq.component.annotations.widgets.TagInputField;
import com.citytechinc.cq.component.dialog.DialogElement;
import com.citytechinc.cq.component.dialog.field.DialogFieldMember;
import com.citytechinc.cq.component.dialog.impl.Namespace;
import com.citytechinc.cq.component.dialog.impl.TagInputFieldWidget;
import com.citytechinc.cq.component.dialog.impl.WidgetCollection;
import com.citytechinc.cq.component.dialog.maker.AbstractWidgetMaker;

public class TagInputFieldWidgetMaker extends AbstractWidgetMaker {

	public DialogElement make(DialogFieldMember field, String xtype, boolean useDotSlashInName)
		throws ClassNotFoundException {

		TagInputField tagAnnotation = field.getAnnotation(TagInputField.class);

		String name = getNameForField(field, useDotSlashInName);
		String fieldName = getFieldNameForField(field);
		String fieldLabel = getFieldLabelForField(field);
		String fieldDescription = getFieldDescriptionForField(field);
		Boolean isRequired = getIsRequiredForField(field);
		Map<String, String> additionalProperties = getAdditionalPropertiesForField(field);
		String defaultValue = getDefaultValueForField(field);
		boolean hideLabel = getHideLabelForField(field);

		boolean displayTitles = tagAnnotation.displayTitles();
		List<DialogElement> widgetCollectionHolder = getWidgetCollectionHolderForField(tagAnnotation);

		TagInputFieldWidget widget = new TagInputFieldWidget(displayTitles, fieldLabel, fieldDescription, !isRequired,
			hideLabel, defaultValue, name, fieldName, additionalProperties, widgetCollectionHolder);

		setListeners(widget, field.getAnnotation().listeners());

		return widget;

	}

	private List<DialogElement> getWidgetCollectionHolderForField(TagInputField tagAnnotation) {

		if (tagAnnotation.namespaces().length == 0) {
			return null;
		}

		List<DialogElement> namespaces = new ArrayList<DialogElement>();
		for (TagNameSpace ns : tagAnnotation.namespaces()) {
			String max = null;
			if (ns.maximum() > -1) {
				max = Integer.toString(ns.maximum());
			}
			Namespace n = new Namespace(ns.value(), max);
			namespaces.add(n);
		}
		return Arrays.asList(new DialogElement[] { new WidgetCollection(namespaces, "namespaces") });

	}

}
