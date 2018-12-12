package com.citytechinc.cq.component.touchuidialog.widget.textfield;

import com.citytechinc.cq.component.annotations.config.TouchUIWidget;
import com.citytechinc.cq.component.annotations.widgets.TextField;
import com.citytechinc.cq.component.touchuidialog.widget.AbstractTouchUIWidget;
import com.citytechinc.cq.component.touchuidialog.widget.DefaultTouchUIWidgetParameters;

@TouchUIWidget(annotationClass = TextField.class, makerClass = TextFieldWidgetMaker.class,
    resourceType = TextFieldCoral3Widget.RESOURCE_TYPE)
public class TextFieldCoral3Widget extends AbstractTouchUIWidget {

    public static final String RESOURCE_TYPE = "granite/ui/components/coral/foundation/form/textfield";

    public TextFieldCoral3Widget(DefaultTouchUIWidgetParameters parameters) {
        super(parameters);
    }
}