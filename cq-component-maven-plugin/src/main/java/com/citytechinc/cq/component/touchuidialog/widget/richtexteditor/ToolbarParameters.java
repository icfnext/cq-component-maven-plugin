package com.citytechinc.cq.component.touchuidialog.widget.richtexteditor;

import com.citytechinc.cq.component.touchuidialog.DefaultTouchUIDialogElementParameters;

public class ToolbarParameters extends DefaultTouchUIDialogElementParameters {

    private String[] toolbar;

    @Override
    public String getPrimaryType() {
        return Toolbar.PRIMARY_TYPE;
    }

    @Override
    public void setPrimaryType(String primaryType) {
        throw new UnsupportedOperationException("PrimaryType is Static for Toolbar");
    }

    public String[] getToolbar() {
        return toolbar;
    }

    public void setToolbar(String[] toolbar) {
        this.toolbar = toolbar;
    }
}
