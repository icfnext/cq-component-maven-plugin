package com.citytechinc.cq.component.touchuidialog.widget.radiogroup;

import com.citytechinc.cq.component.touchuidialog.widget.AbstractTouchUIWidget;

public class RadioGroupCoral3Widget extends AbstractTouchUIWidget {

	public static final String RESOURCE_TYPE = "granite/ui/components/coral/foundation/form/radiogroup";

	private final String text;

	public RadioGroupCoral3Widget(RadioGroupWidgetParameters parameters) {

		super(parameters);

		text = parameters.getText();

	}

	public String getText() {
		return text;
	}
}
