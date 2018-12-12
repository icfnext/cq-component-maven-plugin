package com.citytechinc.cq.component.dialog.maker.impl;

import com.citytechinc.cq.component.dialog.DialogElement;
import com.citytechinc.cq.component.dialog.impl.SimpleWidget;
import com.citytechinc.cq.component.dialog.maker.AbstractWidgetMaker;
import com.citytechinc.cq.component.dialog.maker.WidgetMakerParameters;
import com.citytechinc.cq.component.dialog.widget.DefaultWidgetParameters;

public class DefaultWidgetMaker extends AbstractWidgetMaker<DefaultWidgetParameters> {

    public DefaultWidgetMaker(WidgetMakerParameters parameters) {
        super(parameters);
    }

    @Override
    public DialogElement make(DefaultWidgetParameters widgetParameters) {

        widgetParameters.setXtype(parameters.getXtype());

        return new SimpleWidget(widgetParameters);

    }

}
