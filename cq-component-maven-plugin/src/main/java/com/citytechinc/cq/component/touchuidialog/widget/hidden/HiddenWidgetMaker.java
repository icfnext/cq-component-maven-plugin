package com.citytechinc.cq.component.touchuidialog.widget.hidden;

import com.citytechinc.cq.component.annotations.widgets.Hidden;
import com.citytechinc.cq.component.touchuidialog.TouchUIDialogElement;
import com.citytechinc.cq.component.touchuidialog.widget.maker.AbstractTouchUIWidgetMaker;
import com.citytechinc.cq.component.touchuidialog.widget.maker.TouchUIWidgetMakerParameters;

public class HiddenWidgetMaker extends AbstractTouchUIWidgetMaker<HiddenWidgetParameters> {

    public HiddenWidgetMaker(TouchUIWidgetMakerParameters parameters) {
        super(parameters);
    }

    @Override
    public TouchUIDialogElement make(HiddenWidgetParameters parameters) throws ClassNotFoundException {
        Hidden hiddenFieldAnnotation = getAnnotation(Hidden.class);

        parameters.setValue(hiddenFieldAnnotation.value());

        return new HiddenWidget(parameters);

    }
}
