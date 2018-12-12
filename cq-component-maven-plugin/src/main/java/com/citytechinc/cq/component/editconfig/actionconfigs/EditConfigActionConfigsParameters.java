package com.citytechinc.cq.component.editconfig.actionconfigs;

import com.citytechinc.cq.component.util.Constants;
import com.citytechinc.cq.component.xml.DefaultXmlElementParameters;

public class EditConfigActionConfigsParameters extends DefaultXmlElementParameters {

    @Override
    public String getNameSpace() {
        return Constants.CQ_NS_URI;
    }

    @Override
    public void setNameSpace(String nameSpace) {
        throw new UnsupportedOperationException("nameSpace is Static for EditConfigActionConfigs");
    }

    @Override
    public String getFieldName() {
        return "cq:actionConfigs";
    }

    @Override
    public void setFieldName(String fieldName) {
        throw new UnsupportedOperationException("fieldName is Static for EditConfigActionConfigs");
    }

    @Override
    public String getPrimaryType() {
        return Constants.NT_UNSTRUCTURED;
    }

    @Override
    public void setPrimaryType(String primaryType) {
        throw new UnsupportedOperationException("PrimaryType is Static for EditConfigActionConfigs");
    }
}
