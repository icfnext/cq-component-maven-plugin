package com.citytechinc.cq.component.dialog.impl;

import java.util.List;
import java.util.Map;

import com.citytechinc.cq.component.annotations.config.Widget;
import com.citytechinc.cq.component.annotations.widgets.MultiField;
import com.citytechinc.cq.component.dialog.AbstractWidget;
import com.citytechinc.cq.component.dialog.DialogElement;
import com.citytechinc.cq.component.dialog.maker.impl.MultifieldWidgetMaker;

@Widget(annotationClass = MultiField.class, makerClass = MultifieldWidgetMaker.class, xtype = MultiFieldWidget.XTYPE, ranking = MultiFieldWidget.RANKING)
public class MultiFieldWidget extends AbstractWidget {
	public static final int RANKING = 1000000;
	private static final String PRIMARY_TYPE = "cq:Widget";
	public static final String XTYPE = "multifield";

	private final boolean orderable;
	private final String addItemLabel;

	public MultiFieldWidget(boolean orderable, String addItemLabel, String fieldLabel, String fieldDescription,
		boolean allowBlank, boolean hideLabel, String defaultValue, String name, String fieldName,
		Map<String, String> additionalProperties, List<? extends DialogElement> containedElements) {
		super(XTYPE, fieldLabel, fieldDescription, allowBlank, hideLabel, defaultValue, name, PRIMARY_TYPE, null,
			fieldName, additionalProperties, containedElements);
		this.orderable = orderable;
		this.addItemLabel = addItemLabel;
	}

	public boolean isOrderable() {
		return orderable;
	}

	public String getAddItemLabel() {
		return addItemLabel;
	}
}
