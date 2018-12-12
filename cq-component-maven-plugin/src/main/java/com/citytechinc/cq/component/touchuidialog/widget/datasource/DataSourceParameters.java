package com.citytechinc.cq.component.touchuidialog.widget.datasource;

import com.citytechinc.cq.component.touchuidialog.DefaultTouchUIDialogElementParameters;

public class DataSourceParameters extends DefaultTouchUIDialogElementParameters {

    public static final String FIELD_NAME = "datasource";

    @Override
    public String getFieldName() {
        return FIELD_NAME;
    }

    @Override
    public void setFieldName(String fieldName) {
        throw new UnsupportedOperationException("Field Name is static for DataSource");
    }

    @Override
    public String getPrimaryType() {
        return "nt:unstructured";
    }

    @Override
    public void setPrimaryType(String primaryType) {
        throw new UnsupportedOperationException("Primary Type is static for DataSource");
    }

}
