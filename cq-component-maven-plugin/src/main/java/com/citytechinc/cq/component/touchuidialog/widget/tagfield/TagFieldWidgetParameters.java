package com.citytechinc.cq.component.touchuidialog.widget.tagfield;

import com.citytechinc.cq.component.touchuidialog.widget.DefaultTouchUIWidgetParameters;

public class TagFieldWidgetParameters extends DefaultTouchUIWidgetParameters {

    private String rootPath;

    private boolean multiple;

    @Override
    public String getResourceType() {
        return TagFieldWidget.RESOURCE_TYPE;
    }

    @Override
    public void setResourceType(String resourceType) {
        throw new UnsupportedOperationException();
    }

    public String getRootPath() {
        return rootPath;
    }

    public void setRootPath(String rootPath) {
        this.rootPath = rootPath;
    }

    public boolean isMultiple() {
        return multiple;
    }

    public void setMultiple(final boolean multiple) {
        this.multiple = multiple;
    }
}
