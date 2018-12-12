package com.citytechinc.cq.component.touchuidialog.widget.maker;

import com.citytechinc.cq.component.dialog.exception.InvalidComponentFieldException;
import com.citytechinc.cq.component.touchuidialog.TouchUIDialogElement;
import com.citytechinc.cq.component.touchuidialog.exceptions.TouchUIDialogGenerationException;
import com.citytechinc.cq.component.touchuidialog.widget.DefaultTouchUIWidgetParameters;
import com.citytechinc.cq.component.touchuidialog.widget.SimpleTouchUIWidget;

public class DefaultTouchUIWidgetMaker extends AbstractTouchUIWidgetMaker<DefaultTouchUIWidgetParameters> {

	public DefaultTouchUIWidgetMaker(TouchUIWidgetMakerParameters parameters) {
		super(parameters);
	}

	@Override
	public TouchUIDialogElement make(DefaultTouchUIWidgetParameters widgetParameters) throws ClassNotFoundException,
		InvalidComponentFieldException, TouchUIDialogGenerationException {
		widgetParameters.setResourceType(parameters.getResourceType());

		return new SimpleTouchUIWidget(widgetParameters);

	}

}
