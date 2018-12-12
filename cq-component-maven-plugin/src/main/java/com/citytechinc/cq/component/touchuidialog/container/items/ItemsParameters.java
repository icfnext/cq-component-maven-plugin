package com.citytechinc.cq.component.touchuidialog.container.items;

import com.citytechinc.cq.component.touchuidialog.DefaultTouchUIDialogElementParameters;

public class ItemsParameters extends DefaultTouchUIDialogElementParameters {

    @Override
    public String getPrimaryType() {
        return Items.PRIMARY_TYPE;
    }

    @Override
    public void setPrimaryType(String primaryType) {
        throw new UnsupportedOperationException("primary type is Static for Items");
    }

}
