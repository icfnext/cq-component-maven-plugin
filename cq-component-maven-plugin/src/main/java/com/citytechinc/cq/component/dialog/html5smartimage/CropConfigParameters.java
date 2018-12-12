package com.citytechinc.cq.component.dialog.html5smartimage;

import com.citytechinc.cq.component.dialog.DefaultDialogElementParameters;

public class CropConfigParameters extends DefaultDialogElementParameters {

    private static final String PRIMARY_TYPE = "nt:unstructured";

    private static final String FIELD_NAME = "cropConfig";

    @Override
    public String getFieldName() {
        return FIELD_NAME;
    }

    @Override
    public void setFieldName(String fieldName) {
        throw new UnsupportedOperationException("FieldName is static for CropConfig");
    }

    @Override
    public String getPrimaryType() {
        return PRIMARY_TYPE;
    }

    @Override
    public void setPrimaryType(String primaryType) {
        throw new UnsupportedOperationException("PrimaryType is Static for CropConfig");
    }

}
