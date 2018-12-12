package com.citytechinc.cq.component.touchuidialog.widget.smartimage;

import com.citytechinc.cq.component.touchuidialog.widget.fileupload.FileUploadWidgetParameters;

public class SmartImageWidgetParameters extends FileUploadWidgetParameters {

    private boolean isSelf;

    public boolean isSelf() {
        return isSelf;
    }

    public void setIsSelf(boolean isSelf) {
        this.isSelf = isSelf;
    }
}
