package com.citytechinc.cq.component.dialog.impl;

import java.util.Map;

import com.citytechinc.cq.component.annotations.config.Widget;
import com.citytechinc.cq.component.annotations.widgets.SizeField;
import com.citytechinc.cq.component.dialog.AbstractWidget;
import com.citytechinc.cq.component.dialog.maker.impl.SizeFieldMaker;

@Widget(annotationClass = SizeField.class, makerClass = SizeFieldMaker.class, xtype = SizeFieldWidget.XTYPE)
public class SizeFieldWidget extends AbstractWidget {

	public static final String XTYPE = "sizefield";
	private static final String PRIMARY_TYPE = "cq:Widget";

	private final String heightParameter;
	private final String heightPrefix;
	private final String heightSuffix;
	private final String widthParameter;
	private final String widthPrefix;
	private final String widthSuffix;
	private final int fieldWidth;

	public SizeFieldWidget(String fieldLabel, String fieldDescription, boolean allowBlank, boolean hideLabel,
		String defaultValue, String name, String fieldName, Map<String, String> additionalProperties,
		String heightParameter, String heightPrefix, String heightSuffix, String widthParameter, String widthPrefix,
		String widthSuffix, int fieldWidth) {
		super(XTYPE, fieldLabel, fieldDescription, allowBlank, hideLabel, defaultValue, name, PRIMARY_TYPE, null,
			fieldName, additionalProperties, null);

		this.heightParameter = heightParameter;
		this.heightPrefix = heightPrefix;
		this.heightSuffix = heightSuffix;
		this.widthParameter = widthParameter;
		this.widthPrefix = widthPrefix;
		this.widthSuffix = widthSuffix;
		this.fieldWidth = fieldWidth;

	}

	public String getHeightParameter() {
		return heightParameter;
	}

	public String getHeightPrefix() {
		return heightPrefix;
	}

	public String getHeightSuffix() {
		return heightSuffix;
	}

	public String getWidthParameter() {
		return widthParameter;
	}

	public String getWidthPrefix() {
		return widthPrefix;
	}

	public String getWidthSuffix() {
		return widthSuffix;
	}

	public int getFieldWidth() {
		return fieldWidth;
	}

}
