package com.citytechinc.cq.component.dialog.pathfield;

import com.citytechinc.cq.component.dialog.widget.DefaultWidgetParameters;
import com.citytechinc.cq.component.util.Constants;

public class PathFieldWidgetParameters extends DefaultWidgetParameters {

    private boolean escapeAmp;

    private boolean hideTrigger;

    private boolean parBrowse;

    private String rootPath;

    private String rootTitle;

    private boolean showTitleInTree;

    public boolean isEscapeAmp() {
        return escapeAmp;
    }

    public void setEscapeAmp(boolean escapeAmp) {
        this.escapeAmp = escapeAmp;
    }

    public boolean isHideTrigger() {
        return hideTrigger;
    }

    public void setHideTrigger(boolean hideTrigger) {
        this.hideTrigger = hideTrigger;
    }

    public boolean isParBrowse() {
        return parBrowse;
    }

    public void setParBrowse(boolean parBrowse) {
        this.parBrowse = parBrowse;
    }

    public String getRootPath() {
        return rootPath;
    }

    public void setRootPath(String rootPath) {
        this.rootPath = rootPath;
    }

    public String getRootTitle() {
        return rootTitle;
    }

    public void setRootTitle(String rootTitle) {
        this.rootTitle = rootTitle;
    }

    public boolean isShowTitleInTree() {
        return showTitleInTree;
    }

    public void setShowTitleInTree(boolean showTitleInTree) {
        this.showTitleInTree = showTitleInTree;
    }

    @Override
    public String getPrimaryType() {
        return Constants.CQ_WIDGET;
    }

    @Override
    public void setPrimaryType(String primaryType) {
        throw new UnsupportedOperationException("PrimaryType is Static for PathFieldWidget");
    }

    @Override
    public String getXtype() {
        return PathFieldWidget.XTYPE;
    }

    @Override
    public void setXtype(String xtype) {
        throw new UnsupportedOperationException("xtype is Static for PathFieldWidget");
    }
}
