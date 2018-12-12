package com.citytechinc.cq.component.touchuidialog.widget.textfield;

import com.citytechinc.cq.component.touchuidialog.TouchUIDialogElement;
import com.citytechinc.cq.component.touchuidialog.widget.DefaultTouchUIWidgetParameters;
import com.citytechinc.cq.component.touchuidialog.widget.maker.AbstractTouchUIWidgetMaker;
import com.citytechinc.cq.component.touchuidialog.widget.maker.TouchUIWidgetMakerParameters;

public class TextFieldWidgetMaker extends AbstractTouchUIWidgetMaker<DefaultTouchUIWidgetParameters> {

	public TextFieldWidgetMaker(TouchUIWidgetMakerParameters parameters) {
		super(parameters);
	}

	@Override
	public TouchUIDialogElement make(DefaultTouchUIWidgetParameters widgetParameters) {
		widgetParameters.setResourceType(TextFieldWidget.RESOURCE_TYPE);
		return new TextFieldWidget(widgetParameters);
	}

}
