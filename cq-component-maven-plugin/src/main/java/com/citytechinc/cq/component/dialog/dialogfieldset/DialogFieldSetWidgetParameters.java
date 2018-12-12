package com.citytechinc.cq.component.dialog.dialogfieldset;

import com.citytechinc.cq.component.dialog.widget.DefaultWidgetParameters;
import com.citytechinc.cq.component.util.Constants;

public class DialogFieldSetWidgetParameters extends DefaultWidgetParameters {

    private boolean collapseFirst;

    private boolean collapsible;

    private boolean collapsed;

    private boolean border;

    private String title;

    public boolean isCollapseFirst() {
        return collapseFirst;
    }

    public void setCollapseFirst(boolean collapseFirst) {
        this.collapseFirst = collapseFirst;
    }

    public boolean isCollapsible() {
        return collapsible;
    }

    public void setCollapsible(boolean collapsible) {
        this.collapsible = collapsible;
    }

    public boolean isCollapsed() {
        return collapsed;
    }

    public void setCollapsed(boolean collapsed) {
        this.collapsed = collapsed;
    }

    public boolean isBorder() {
        return border;
    }

    public void setBorder(boolean border) {
        this.border = border;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    @Override
    public String getPrimaryType() {
        return Constants.CQ_WIDGET;
    }

    @Override
    public void setPrimaryType(String primaryType) {
        throw new UnsupportedOperationException("PrimaryType is Static for DialogFieldSetWidget");
    }

    @Override
    public String getXtype() {
        return DialogFieldSetWidget.XTYPE;
    }

    @Override
    public void setXtype(String xtype) {
        throw new UnsupportedOperationException("xtype is Static for DialogFieldSetWidget");
    }
}
