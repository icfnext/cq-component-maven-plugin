package com.citytechinc.cq.component.dialog.checkbox;

import com.citytechinc.cq.component.annotations.config.Widget;
import com.citytechinc.cq.component.annotations.widgets.CheckBox;
import com.citytechinc.cq.component.dialog.AbstractWidget;

@Widget(annotationClass = CheckBox.class, makerClass = CheckBoxWidgetMaker.class, xtype = CheckBoxWidget.XTYPE)
public class CheckBoxWidget extends AbstractWidget {
	public static final String XTYPE = "checkbox";

	private final String inputValue;
	private final boolean checked;

	public CheckBoxWidget(CheckBoxWidgetParameters parameters) {
		super(parameters);
		this.inputValue = parameters.getInputValue();
		this.checked = parameters.isChecked();
	}

	public String getInputValue() {
		return inputValue;
	}

	public boolean isChecked() {
		return checked;
	}
}
