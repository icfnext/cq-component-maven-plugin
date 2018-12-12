package com.citytechinc.cq.component.touchuidialog.widget.radiogroup;

import com.citytechinc.cq.component.touchuidialog.widget.AbstractTouchUIWidget;

public class RadioGroupWidget extends AbstractTouchUIWidget {

	public static final String RESOURCE_TYPE = "granite/ui/components/foundation/form/radiogroup";
	public static final String RADIO_RESOURCE_TYPE = "granite/ui/components/foundation/form/radio";

	private final String text;

	public RadioGroupWidget(RadioGroupWidgetParameters parameters) {

		super(parameters);

		text = parameters.getText();

	}

	public String getText() {
		return text;
	}
}
