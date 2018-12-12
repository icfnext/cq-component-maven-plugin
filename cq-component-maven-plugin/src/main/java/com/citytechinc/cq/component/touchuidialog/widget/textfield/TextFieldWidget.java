package com.citytechinc.cq.component.touchuidialog.widget.textfield;

import com.citytechinc.cq.component.annotations.config.TouchUIWidget;
import com.citytechinc.cq.component.annotations.widgets.TextField;
import com.citytechinc.cq.component.touchuidialog.widget.AbstractTouchUIWidget;
import com.citytechinc.cq.component.touchuidialog.widget.DefaultTouchUIWidgetParameters;

@TouchUIWidget(annotationClass = TextField.class, makerClass = TextFieldWidgetMaker.class,
    resourceType = TextFieldWidget.RESOURCE_TYPE)
public class TextFieldWidget extends AbstractTouchUIWidget {

    public static final String RESOURCE_TYPE = "granite/ui/components/foundation/form/textfield";

    public TextFieldWidget(DefaultTouchUIWidgetParameters parameters) {
        super(parameters);
    }

}
