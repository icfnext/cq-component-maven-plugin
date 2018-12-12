package com.citytechinc.cq.component.touchuidialog.widget.hidedialogfield;

import com.citytechinc.cq.component.dialog.exception.InvalidComponentFieldException;
import com.citytechinc.cq.component.touchuidialog.TouchUIDialogElement;
import com.citytechinc.cq.component.touchuidialog.exceptions.TouchUIDialogGenerationException;
import com.citytechinc.cq.component.touchuidialog.widget.maker.AbstractTouchUIWidgetMaker;
import com.citytechinc.cq.component.touchuidialog.widget.maker.TouchUIWidgetMakerParameters;

public class HideDialogFieldWidgetMaker extends AbstractTouchUIWidgetMaker<HideDialogFieldWidgetParameters> {

    public HideDialogFieldWidgetMaker(TouchUIWidgetMakerParameters parameters) {
        super(parameters);
    }

    @Override
    protected TouchUIDialogElement make(HideDialogFieldWidgetParameters parameters)
        throws ClassNotFoundException, InvalidComponentFieldException, TouchUIDialogGenerationException,
        IllegalAccessException, InstantiationException {
        return new HideDialogFieldWidget(parameters);
    }
}
