package com.citytechinc.cq.component.dialog.multifield;

import com.citytechinc.cq.component.dialog.widget.DefaultWidgetParameters;
import com.citytechinc.cq.component.util.Constants;

public class MultiFieldWidgetParameters extends DefaultWidgetParameters {

    private boolean orderable;

    private String addItemLabel;

    public boolean isOrderable() {
        return orderable;
    }

    public void setOrderable(boolean orderable) {
        this.orderable = orderable;
    }

    public String getAddItemLabel() {
        return addItemLabel;
    }

    public void setAddItemLabel(String addItemLabel) {
        this.addItemLabel = addItemLabel;
    }

    @Override
    public String getPrimaryType() {
        return Constants.CQ_WIDGET;
    }

    @Override
    public void setPrimaryType(String primaryType) {
        throw new UnsupportedOperationException("PrimaryType is Static for MultiFieldWidget");
    }

    @Override
    public String getXtype() {
        return MultiFieldWidget.XTYPE;
    }

    @Override
    public void setXtype(String xtype) {
        throw new UnsupportedOperationException("xtype is Static for MultiFieldWidget");
    }
}
