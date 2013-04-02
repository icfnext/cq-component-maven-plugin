package com.citytechinc.cq.component.dialog.impl;

import java.util.List;
import java.util.Map;

import com.citytechinc.cq.component.annotations.config.Widget;
import com.citytechinc.cq.component.dialog.AbstractWidget;
import com.citytechinc.cq.component.dialog.DialogElement;

@Widget(annotationClass="com.citytechinc.cq.component.annotations.widgets.TagInputField",makerClass="com.citytechinc.cq.component.dialog.maker.impl.TagInputFieldWidgetMaker",xtypes=TagInputFieldWidget.XTYPE)
public class TagInputFieldWidget extends AbstractWidget{
	public static final String XTYPE = "tags";
	private static final String PRIMARY_TYPE = "cq:Widget";
	
	private boolean displayTitles;
	
	public TagInputFieldWidget(boolean displayTitles, String fieldLabel,
			String fieldDescription, boolean allowBlank, String defaultValue,
			String name, String fieldName,
			Map<String, String> additionalProperties,List<? extends DialogElement> containedElements) {
		super(XTYPE, fieldLabel, fieldDescription, allowBlank, defaultValue,
				name, PRIMARY_TYPE, null, fieldName, additionalProperties,
				containedElements);	
		this.displayTitles=displayTitles;
	}

	public boolean isDisplayTitles() {
		return displayTitles;
	}
}
