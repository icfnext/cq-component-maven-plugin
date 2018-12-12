package com.citytechinc.cq.component.touchuidialog.widget.maker;

import com.citytechinc.cq.component.dialog.exception.InvalidComponentFieldException;
import com.citytechinc.cq.component.touchuidialog.TouchUIDialogElement;
import com.citytechinc.cq.component.touchuidialog.exceptions.TouchUIDialogGenerationException;

public interface TouchUIWidgetMaker {

    TouchUIDialogElement make() throws ClassNotFoundException, InvalidComponentFieldException,
        TouchUIDialogGenerationException, IllegalAccessException, InstantiationException;

}