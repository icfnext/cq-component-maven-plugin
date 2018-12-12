package com.citytechinc.cq.component.touchuidialog.widget.textfield;

import com.citytechinc.cq.component.touchuidialog.TouchUIDialogElement;
import com.citytechinc.cq.component.touchuidialog.TouchUIDialogType;
import com.citytechinc.cq.component.touchuidialog.widget.maker.AbstractTouchUIWidgetMaker;
import com.citytechinc.cq.component.touchuidialog.widget.maker.TouchUIWidgetMakerParameters;

public class TextFieldWidgetMaker extends AbstractTouchUIWidgetMaker<TextFieldWidgetParameters> {

    public TextFieldWidgetMaker(TouchUIWidgetMakerParameters parameters) {
        super(parameters);
    }

    @Override
    public TouchUIDialogElement make(TextFieldWidgetParameters widgetParameters) {
        widgetParameters.setTouchUIDialogType(parameters.getTouchUIDialogType());

        if (TouchUIDialogType.CORAL3.isOfType(widgetParameters.getTouchUIDialogType())) {
            return new TextFieldCoral3Widget(widgetParameters);
        }
        return new TextFieldWidget(widgetParameters);
    }
}