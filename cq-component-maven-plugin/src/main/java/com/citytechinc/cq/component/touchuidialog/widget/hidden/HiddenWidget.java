package com.citytechinc.cq.component.touchuidialog.widget.hidden;

import com.citytechinc.cq.component.annotations.config.TouchUIWidget;
import com.citytechinc.cq.component.annotations.widgets.Hidden;
import com.citytechinc.cq.component.touchuidialog.widget.AbstractTouchUIWidget;
import com.citytechinc.cq.component.touchuidialog.widget.DefaultTouchUIWidgetParameters;

@TouchUIWidget(annotationClass = Hidden.class, makerClass = HiddenWidgetMaker.class,
    resourceType = HiddenWidget.RESOURCE_TYPE, featureFlag = HiddenWidget.FEATURE_FLAG)
public class HiddenWidget extends AbstractTouchUIWidget {
    public static final String RESOURCE_TYPE = "granite/ui/components/coral/foundation/form/hidden";
    public static final String FEATURE_FLAG = "hiddenfieldwidget";

    private final String value;

    public HiddenWidget(DefaultTouchUIWidgetParameters parameters) {
        super(parameters);
        this.value = parameters.getValue();
    }

    public String getValue() {
        return value;
    }
}
