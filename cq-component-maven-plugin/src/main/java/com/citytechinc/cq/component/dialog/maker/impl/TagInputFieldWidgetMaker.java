package com.citytechinc.cq.component.dialog.maker.impl;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

import com.citytechinc.cq.component.annotations.TagNameSpace;
import com.citytechinc.cq.component.annotations.widgets.TagInputField;
import com.citytechinc.cq.component.dialog.DialogElement;
import com.citytechinc.cq.component.dialog.impl.Namespace;
import com.citytechinc.cq.component.dialog.impl.TagInputFieldWidget;
import com.citytechinc.cq.component.dialog.impl.WidgetCollection;
import com.citytechinc.cq.component.dialog.maker.AbstractWidgetMaker;
import com.citytechinc.cq.component.dialog.maker.WidgetMakerParameters;

public class TagInputFieldWidgetMaker extends AbstractWidgetMaker {

	public TagInputFieldWidgetMaker(WidgetMakerParameters parameters) {
		super(parameters);
	}

	public DialogElement make() throws ClassNotFoundException {

		TagInputField tagAnnotation = getAnnotation(TagInputField.class);

		String name = getNameForField();
		String fieldName = getFieldNameForField();
		String fieldLabel = getFieldLabelForField();
		String fieldDescription = getFieldDescriptionForField();
		Boolean isRequired = getIsRequiredForField();
		Map<String, String> additionalProperties = getAdditionalPropertiesForField();
		String defaultValue = getDefaultValueForField();
		boolean hideLabel = getHideLabelForField();

		boolean displayTitles = tagAnnotation.displayTitles();
		List<DialogElement> widgetCollectionHolder = getWidgetCollectionHolderForField(tagAnnotation);

		TagInputFieldWidget widget = new TagInputFieldWidget(displayTitles, fieldLabel, fieldDescription, !isRequired,
			hideLabel, defaultValue, name, fieldName, additionalProperties, widgetCollectionHolder);

		setListeners(widget);

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
