package com.citytechinc.cq.component.dialog.impl;

import java.util.List;
import java.util.Map;

import com.citytechinc.cq.component.annotations.config.Widget;
import com.citytechinc.cq.component.dialog.AbstractWidget;
import com.citytechinc.cq.component.dialog.DialogElement;

@Widget(annotationClass="com.citytechinc.cq.component.annotations.widgets.MultiCompositeField",makerClass="com.citytechinc.cq.component.dialog.maker.impl.MultiCompositeFieldWidgetMaker",xtypes=MultiCompositeFieldWidget.XTYPE)
public class MultiCompositeFieldWidget extends AbstractWidget{
	public static final String XTYPE = "multicompositefield";
	private static final String PRIMARY_TYPE = "cq:Widget";
	
	private final boolean matchBaseName;
	private final String prefix;
	
	public MultiCompositeFieldWidget(boolean matchBaseName, String prefix,String fieldLabel,
			String fieldDescription, boolean allowBlank, boolean hideLabel,String defaultValue,
			String name, String fieldName,
			Map<String, String> additionalProperties,List<? extends DialogElement> containedElements) {
		super(XTYPE, fieldLabel, fieldDescription, allowBlank, hideLabel,defaultValue,
				name, PRIMARY_TYPE, null, fieldName, additionalProperties,
				containedElements);
		this.matchBaseName=matchBaseName;
		this.prefix=prefix;
	}

	public boolean isMatchBaseName() {
		return matchBaseName;
	}

	public String getPrefix() {
		return prefix;
	}
}
