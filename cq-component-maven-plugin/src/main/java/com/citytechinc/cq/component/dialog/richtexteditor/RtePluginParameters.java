package com.citytechinc.cq.component.dialog.richtexteditor;

import com.citytechinc.cq.component.dialog.DefaultDialogElementParameters;

public class RtePluginParameters extends DefaultDialogElementParameters {

    private String features;

    public String getFeatures() {
        return features;
    }

    public void setFeatures(String features) {
        this.features = features;
    }

    @Override
    public String getPrimaryType() {
        return RtePlugin.PRIMARY_TYPE;
    }

    @Override
    public void setPrimaryType(String primaryType) {
        throw new UnsupportedOperationException("PrimaryType is Static for RtePlugin");
    }
}
