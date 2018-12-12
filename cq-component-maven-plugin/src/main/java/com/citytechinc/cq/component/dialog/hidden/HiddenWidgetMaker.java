package com.citytechinc.cq.component.dialog.hidden;

import com.citytechinc.cq.component.annotations.widgets.Hidden;
import com.citytechinc.cq.component.dialog.DialogElement;
import com.citytechinc.cq.component.dialog.maker.AbstractWidgetMaker;
import com.citytechinc.cq.component.dialog.maker.WidgetMakerParameters;

public class HiddenWidgetMaker extends AbstractWidgetMaker<HiddenWidgetParameters> {

    public HiddenWidgetMaker(WidgetMakerParameters parameters) {
        super(parameters);
    }

    @Override
    public DialogElement make(HiddenWidgetParameters parameters) throws ClassNotFoundException {

        Hidden hiddenFieldAnnotation = getAnnotation(Hidden.class);

        parameters.setValue(hiddenFieldAnnotation.value());

        return new HiddenWidget(parameters);

    }
}
