package com.citytechinc.cq.component.dialog.multifield;

import com.citytechinc.cq.component.annotations.config.Widget;
import com.citytechinc.cq.component.annotations.widgets.MultiField;
import com.citytechinc.cq.component.dialog.AbstractWidget;

@Widget(annotationClass = MultiField.class, makerClass = MultifieldWidgetMaker.class, xtype = MultiFieldWidget.XTYPE,
	ranking = MultiFieldWidget.RANKING)
public class MultiFieldWidget extends AbstractWidget {
	public static final int RANKING = 1000000;
	public static final String XTYPE = "multifield";

	private final boolean orderable;
	private final String addItemLabel;

	public MultiFieldWidget(MultiFieldWidgetParameters parameters) {
		super(parameters);
		this.orderable = parameters.isOrderable();
		this.addItemLabel = parameters.getAddItemLabel();
	}

	public boolean isOrderable() {
		return orderable;
	}

	public String getAddItemLabel() {
		return addItemLabel;
	}
}
