package com.citytechinc.cq.component.touchuidialog.widget.switchwidget;

import com.citytechinc.cq.component.annotations.config.TouchUIWidget;
import com.citytechinc.cq.component.annotations.widgets.Switch;
import com.citytechinc.cq.component.touchuidialog.widget.AbstractTouchUIWidget;

@TouchUIWidget(annotationClass = Switch.class, makerClass = SwitchWidgetMaker.class,
    resourceType = SwitchCoral3Widget.RESOURCE_TYPE)
public class SwitchCoral3Widget extends AbstractTouchUIWidget {

    public static final String RESOURCE_TYPE = "granite/ui/components/coral/foundation/form/switch";

    private final String onText;

    private final String offText;

    private final Boolean checked;

    private final boolean ignoreData;

    public SwitchCoral3Widget(SwitchWidgetParameters parameters) {
        super(parameters);
        this.onText = parameters.getOnText();
        this.offText = parameters.getOffText();
        this.checked = parameters.getChecked();
        this.ignoreData = parameters.isIgnoreData();
    }

    public String getOnText() {
        return onText;
    }

    public String getOffText() {
        return offText;
    }

    public Boolean getChecked() {
        return checked;
    }

    public boolean isIgnoreData() {
        return ignoreData;
    }

}
