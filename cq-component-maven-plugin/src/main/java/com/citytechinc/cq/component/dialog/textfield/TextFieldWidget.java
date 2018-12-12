package com.citytechinc.cq.component.dialog.textfield;

import com.citytechinc.cq.component.annotations.config.Widget;
import com.citytechinc.cq.component.annotations.widgets.TextField;
import com.citytechinc.cq.component.dialog.AbstractWidget;
import com.citytechinc.cq.component.dialog.maker.impl.DefaultWidgetMaker;
import com.citytechinc.cq.component.dialog.widget.DefaultWidgetParameters;

@Widget(annotationClass = TextField.class, makerClass = DefaultWidgetMaker.class, xtype = TextFieldWidget.XTYPE)
public class TextFieldWidget extends AbstractWidget {

    public static final String XTYPE = "textfield";

    public TextFieldWidget(DefaultWidgetParameters parameters) {
        super(parameters);
    }
}
