package com.citytechinc.cq.component.touchuidialog.widget.switchwidget;

import com.citytechinc.cq.component.touchuidialog.TouchUIDialogType;
import com.citytechinc.cq.component.touchuidialog.widget.DefaultTouchUIWidgetParameters;

public class SwitchWidgetParameters extends DefaultTouchUIWidgetParameters {

    private String onText;

    private String offText;

    private Boolean checked;

    private boolean ignoreData;

    public String getOnText() {
        return onText;
    }

    public void setOnText(String onText) {
        this.onText = onText;
    }

    public String getOffText() {
        return offText;
    }

    public void setOffText(String offText) {
        this.offText = offText;
    }

    public Boolean getChecked() {
        return checked;
    }

    public void setChecked(Boolean checked) {
        this.checked = checked;
    }

    public boolean isIgnoreData() {
        return ignoreData;
    }

    public void setIgnoreData(boolean ignoreData) {
        this.ignoreData = ignoreData;
    }

    @Override
    public String getResourceType() {
        if (TouchUIDialogType.CORAL3.isOfType(getTouchUIDialogType())) {
            return SwitchCoral3Widget.RESOURCE_TYPE;
        }
        return SwitchWidget.RESOURCE_TYPE;
    }

    @Override
    public void setResourceType(String resourceType) {
        throw new UnsupportedOperationException("resourceType is Static for SwitchWidget");
    }

}
