package com.citytechinc.cq.component.dialog.tab;

import com.citytechinc.cq.component.dialog.DefaultDialogElementParameters;
import com.citytechinc.cq.component.util.Constants;

public class TabParameters extends DefaultDialogElementParameters {

    private String title;

    public String getTitle() {
        if (title == null) {
            return "";
        }
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    @Override
    public void setPrimaryType(String primaryType) {
        throw new UnsupportedOperationException("PrimaryType is Static for HiddenWidget");
    }

    @Override
    public String getPrimaryType() {
        return Constants.CQ_WIDGET;
    }

    @Override
    public String getFieldName() {
        return getTitle().toLowerCase();
    }

    @Override
    public void setFieldName(String fieldName) {
        throw new UnsupportedOperationException("FieldNAme is based on title for Tab");
    }

}
