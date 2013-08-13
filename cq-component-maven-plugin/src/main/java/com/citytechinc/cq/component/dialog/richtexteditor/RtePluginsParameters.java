package com.citytechinc.cq.component.dialog.richtexteditor;

import java.util.List;

import com.citytechinc.cq.component.dialog.DialogElement;
import com.citytechinc.cq.component.dialog.DialogElementParameters;

public class RtePluginsParameters extends DialogElementParameters {

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
