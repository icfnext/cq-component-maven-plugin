package com.citytechinc.cq.component.dialog.cqincludes;

import com.citytechinc.cq.component.dialog.AbstractDialogElement;
import com.citytechinc.cq.component.dialog.TabbableDialogElement;

public class CQInclude extends AbstractDialogElement implements TabbableDialogElement {

    private static final String XTYPE = "cqinclude";

    private final String path;

    public CQInclude(CQIncludeParameters parameters) {
        super(parameters);
        this.path = parameters.getPath();
    }

    public String getPath() {
        return path;
    }

    public String getXtype() {
        return XTYPE;
    }

    public Boolean isTab() {
        return true;
    }

    public void setTitle(String title) {

    }

    public String getTitle() {
        return null;
    }
}
