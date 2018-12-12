package com.citytechinc.cq.component.touchuidialog.widget.multifield;

import java.util.ArrayList;
import java.util.List;

import com.citytechinc.cq.component.dialog.exception.InvalidComponentFieldException;
import com.citytechinc.cq.component.touchuidialog.TouchUIDialogElement;
import com.citytechinc.cq.component.touchuidialog.exceptions.TouchUIDialogGenerationException;
import com.citytechinc.cq.component.touchuidialog.widget.factory.TouchUIWidgetFactory;
import com.citytechinc.cq.component.touchuidialog.widget.maker.AbstractTouchUIWidgetMaker;
import com.citytechinc.cq.component.touchuidialog.widget.maker.TouchUIWidgetMakerParameters;

public class MultiFieldWidgetMaker extends AbstractTouchUIWidgetMaker<MultiFieldWidgetParameters> {

	public MultiFieldWidgetMaker(TouchUIWidgetMakerParameters parameters) {
		super(parameters);
	}

	@Override
	public TouchUIDialogElement make(MultiFieldWidgetParameters widgetParameters) throws ClassNotFoundException,
		InvalidComponentFieldException, TouchUIDialogGenerationException {

		TouchUIDialogElement field = TouchUIWidgetFactory.make(parameters, MultiFieldWidget.RANKING);
		field.setFieldName("field");

		List<TouchUIDialogElement> containedElements = new ArrayList<TouchUIDialogElement>();
		containedElements.add(field);

		widgetParameters.setContainedElements(containedElements);

		return new MultiFieldWidget(widgetParameters);
	}

}
