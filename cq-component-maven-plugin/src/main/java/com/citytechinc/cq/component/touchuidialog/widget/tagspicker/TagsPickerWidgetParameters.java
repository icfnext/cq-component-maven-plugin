package com.citytechinc.cq.component.touchuidialog.widget.tagspicker;

import com.citytechinc.cq.component.touchuidialog.widget.DefaultTouchUIWidgetParameters;

public class TagsPickerWidgetParameters extends DefaultTouchUIWidgetParameters {

    private String tagsPath;
    private String rootPath;

    @Override
    public String getResourceType() {
        return TagsPickerWidget.RESOURCE_TYPE;
    }

    @Override
    public void setResourceType(String resourceType) {
        throw new UnsupportedOperationException("resourceType is Static for TaksPickerWidget");
    }

    public String getTagsPath() {
        return tagsPath;
    }

    public void setTagsPath(String tagsPath) {
        this.tagsPath = tagsPath;
    }

    public String getRootPath() {
        return rootPath;
    }

    public void setRootPath(String rootPath) {
        this.rootPath = rootPath;
    }
}
