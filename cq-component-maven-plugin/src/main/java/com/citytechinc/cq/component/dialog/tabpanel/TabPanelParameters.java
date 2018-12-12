package com.citytechinc.cq.component.dialog.tabpanel;

import com.citytechinc.cq.component.dialog.DefaultDialogElementParameters;
import com.citytechinc.cq.component.dialog.DialogElement;
import com.citytechinc.cq.component.xml.XmlElement;
import org.codehaus.plexus.util.StringUtils;

import java.util.ArrayList;
import java.util.List;

public class TabPanelParameters extends DefaultDialogElementParameters {

    private static final String DEFAULT_FIELD_NAME = "tabs";

    private static final String PRIMARY_TYPE = "cq:TabPanel";

    @Override
    public void setPrimaryType(String primaryType) {
        throw new UnsupportedOperationException("PrimaryType is Static for TabPanel");
    }

    @Override
    public String getPrimaryType() {
        return PRIMARY_TYPE;
    }

    @Override
    public String getFieldName() {
        if (StringUtils.isEmpty(fieldName)) {
            return DEFAULT_FIELD_NAME;
        }
        return fieldName;
    }

    @Override
    public List<? extends XmlElement> getContainedElements() {
        if (containedElements == null) {
            containedElements = new ArrayList<DialogElement>();
        }
        return containedElements;
    }
}
