package com.citytechinc.cq.component.touchuidialog.layout;

import com.citytechinc.cq.component.touchuidialog.DefaultTouchUIDialogElementParameters;

public class LayoutElementParameters extends DefaultTouchUIDialogElementParameters {

    @Override
    public String getFieldName() {
        return AbstractLayoutElement.ELEMENT_NAME;
    }

    @Override
    public void setFieldName(String fieldName) {
        throw new UnsupportedOperationException("field name is Static for Layout Element");
    }

    @Override
    public String getPrimaryType() {
        return AbstractLayoutElement.PRIMARY_TYPE;
    }

    @Override
    public void setPrimaryType(String fieldName) {
        throw new UnsupportedOperationException("primary type is Static for Layout Element");
    }

}
