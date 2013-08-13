package com.citytechinc.cq.component.dialog.hidden;

import com.citytechinc.cq.component.annotations.config.Widget;
import com.citytechinc.cq.component.annotations.widgets.Hidden;
import com.citytechinc.cq.component.dialog.AbstractWidget;

@Widget(annotationClass = Hidden.class, makerClass = HiddenWidgetMaker.class, xtype = HiddenWidget.XTYPE)
public class HiddenWidget extends AbstractWidget {
	public static final String XTYPE = "hidden";

	private final String value;

	public HiddenWidget(HiddenWidgetParameters parameters) {
		super(parameters);
		this.value = parameters.getValue();
	}

	public String getValue() {
		return value;
	}
}
