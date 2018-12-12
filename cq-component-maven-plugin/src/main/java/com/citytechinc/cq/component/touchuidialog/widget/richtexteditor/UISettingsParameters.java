package com.citytechinc.cq.component.touchuidialog.widget.richtexteditor;

import com.citytechinc.cq.component.touchuidialog.DefaultTouchUIDialogElementParameters;

public class UISettingsParameters extends DefaultTouchUIDialogElementParameters {

    @Override
    public String getPrimaryType() {
        return UISettings.PRIMARY_TYPE;
    }

    @Override
    public void setPrimaryType(String primaryType) {
        throw new UnsupportedOperationException("PrimaryType is Static for UISettings");
    }

    @Override
    public String getFieldName() {
        return UISettings.FIELD_NAME;
    }

    @Override
    public void setFieldName(String fieldName) {
        throw new UnsupportedOperationException("PrimaryType is Static for UISettings");
    }
}
