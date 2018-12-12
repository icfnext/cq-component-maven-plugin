package com.citytechinc.cq.component.dialog.richtexteditor;

import com.citytechinc.cq.component.dialog.DefaultDialogElementParameters;
import com.citytechinc.cq.component.dialog.DialogElement;

import java.util.List;

public class RtePluginsParameters extends DefaultDialogElementParameters {

    private List<DialogElement> rtePlugins;

    public List<DialogElement> getRtePlugins() {
        return rtePlugins;
    }

    public void setRtePlugins(List<DialogElement> rtePlugins) {
        this.rtePlugins = rtePlugins;
    }

    @Override
    public String getPrimaryType() {
        return RtePlugins.PRIMARY_TYPE;
    }

    @Override
    public void setPrimaryType(String primaryType) {
        throw new UnsupportedOperationException("PrimaryType is Static for RtePlugins");
    }

    @Override
    public String getFieldName() {
        return RtePlugins.FIELD_NAME;
    }

    @Override
    public void setFieldName(String fieldName) {
        throw new UnsupportedOperationException("PrimaryType is Static for RtePlugins");
    }
}
