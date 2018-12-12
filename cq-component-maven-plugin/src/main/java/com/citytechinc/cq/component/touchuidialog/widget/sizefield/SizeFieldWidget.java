package com.citytechinc.cq.component.touchuidialog.widget.sizefield;

import com.citytechinc.cq.component.annotations.config.TouchUIWidget;
import com.citytechinc.cq.component.annotations.widgets.SizeField;
import com.citytechinc.cq.component.touchuidialog.widget.AbstractTouchUIWidget;

@TouchUIWidget(annotationClass = SizeField.class, makerClass = SizeFieldWidgetMaker.class,
        resourceType = SizeFieldWidget.RESOURCE_TYPE)
public class SizeFieldWidget extends AbstractTouchUIWidget {

    public static final String RESOURCE_TYPE = "cq/gui/components/authoring/dialog/sizefield";

    public SizeFieldWidget(SizeFieldWidgetParameters parameters) {
        super(parameters);
    }

}
