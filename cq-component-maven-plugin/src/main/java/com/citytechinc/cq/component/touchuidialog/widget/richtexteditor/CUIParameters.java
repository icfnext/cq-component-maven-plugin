package com.citytechinc.cq.component.touchuidialog.widget.richtexteditor;

import com.citytechinc.cq.component.touchuidialog.DefaultTouchUIDialogElementParameters;

public class CUIParameters extends DefaultTouchUIDialogElementParameters {

    @Override
    public String getPrimaryType() {
        return CUI.PRIMARY_TYPE;
    }

    @Override
    public void setPrimaryType(String primaryType) {
        throw new UnsupportedOperationException("PrimaryType is Static for CUI");
    }

    @Override
    public String getFieldName() {
        return CUI.FIELD_NAME;
    }

    @Override
    public void setFieldName(String fieldName) {
        throw new UnsupportedOperationException("PrimaryType is Static for CUI");
    }
}
