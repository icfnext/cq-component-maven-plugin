package com.citytechinc.cq.component.dialog.widgetcollection;

import com.citytechinc.cq.component.dialog.DefaultDialogElementParameters;
import org.codehaus.plexus.util.StringUtils;

public class WidgetCollectionParameters extends DefaultDialogElementParameters {

    private static final String PRIMARY_TYPE = "cq:WidgetCollection";

    private static final String DEFAULT_FIELD_NAME = "items";

    @Override
    public String getPrimaryType() {
        return PRIMARY_TYPE;
    }

    @Override
    public void setPrimaryType(String primaryType) {
        throw new UnsupportedOperationException("PrimaryType is Static for TagInputFieldWidget");
    }

    @Override
    public String getFieldName() {
        if (StringUtils.isEmpty(fieldName)) {
            return DEFAULT_FIELD_NAME;
        }
        return fieldName;
    }
}
