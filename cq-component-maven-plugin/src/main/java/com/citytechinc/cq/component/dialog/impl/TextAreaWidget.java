package com.citytechinc.cq.component.dialog.impl;

import java.util.List;
import java.util.Map;

import com.citytechinc.cq.component.annotations.config.Widget;
import com.citytechinc.cq.component.dialog.AbstractWidget;
import com.citytechinc.cq.component.dialog.DialogElement;

@Widget(annotationClass = "com.citytechinc.cq.component.annotations.widgets.TextArea", makerClass = "com.citytechinc.cq.component.dialog.maker.impl.SimpleWidgetMaker", xtypes = TextAreaWidget.XTYPE)
public class TextAreaWidget extends AbstractWidget {

	public static final String XTYPE = "textarea";

	public TextAreaWidget(String xtype, String fieldLabel, String fieldDescription, boolean allowBlank,
		boolean hideLabel, String defaultValue, String name, String primaryType, String nameSpace, String fieldName,
		Map<String, String> additionalProperties, List<? extends DialogElement> containedElements) {
		super(primaryType, nameSpace, fieldName, hideLabel, hideLabel, fieldName, fieldName, fieldName, fieldName,
			fieldName, additionalProperties, containedElements);
	}
}
