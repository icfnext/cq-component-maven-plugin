package com.citytechinc.cq.component.dialog.impl;

import java.util.List;
import java.util.Map;

import com.citytechinc.cq.component.annotations.config.Widget;
import com.citytechinc.cq.component.annotations.widgets.TagInputField;
import com.citytechinc.cq.component.dialog.AbstractWidget;
import com.citytechinc.cq.component.dialog.DialogElement;
import com.citytechinc.cq.component.dialog.maker.impl.TagInputFieldWidgetMaker;

@Widget(annotationClass = TagInputField.class, makerClass = TagInputFieldWidgetMaker.class, xtype = TagInputFieldWidget.XTYPE)
public class TagInputFieldWidget extends AbstractWidget {
	public static final String XTYPE = "tags";
	private static final String PRIMARY_TYPE = "cq:Widget";

	private final boolean displayTitles;

	public TagInputFieldWidget(boolean displayTitles, String fieldLabel, String fieldDescription, boolean allowBlank,
		boolean hideLabel, String defaultValue, String name, String fieldName,
		Map<String, String> additionalProperties, List<DialogElement> containedElements) {
		super(XTYPE, fieldLabel, fieldDescription, allowBlank, hideLabel, defaultValue, name, PRIMARY_TYPE, null,
			fieldName, additionalProperties, containedElements);
		this.displayTitles = displayTitles;
	}

	public boolean isDisplayTitles() {
		return displayTitles;
	}
}
