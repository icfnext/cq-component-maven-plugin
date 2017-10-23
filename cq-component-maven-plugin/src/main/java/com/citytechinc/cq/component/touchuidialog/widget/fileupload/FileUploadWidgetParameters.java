/**
 * Copyright 2017 ICF Olson
 * <p>
 * Licensed under the Apache License, Version 2.0 (the "License"); you may not use this file except in compliance with
 * the License. You may obtain a copy of the License at
 * <p>
 * http://www.apache.org/licenses/LICENSE-2.0
 * <p>
 * Unless required by applicable law or agreed to in writing, software distributed under the License is distributed on
 * an "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied. See the License for the
 * specific language governing permissions and limitations under the License.
 */
package com.citytechinc.cq.component.touchuidialog.widget.fileupload;

import com.citytechinc.cq.component.touchuidialog.widget.DefaultTouchUIWidgetParameters;

import java.util.List;

public class FileUploadWidgetParameters extends DefaultTouchUIWidgetParameters {

    private String title;

    private String text;

    private String icon;

    private boolean multiple;

    private boolean allowUpload;

    private String uploadUrl;

    private String uploadUrlBuilder;

    private Long sizeLimit;

    private boolean autoStart;

    private boolean useHTML5;

    private String dropZone;

    // TODO: Event handling
    private List<String> mimeTypes;

    @Override
    public String getTitle() {
        return title;
    }

    @Override
    public void setTitle(String title) {
        this.title = title;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public String getIcon() {
        return icon;
    }

    public void setIcon(String icon) {
        this.icon = icon;
    }

    public boolean isMultiple() {
        return multiple;
    }

    public void setMultiple(boolean multiple) {
        this.multiple = multiple;
    }

    public boolean isAllowUpload() {
        return allowUpload;
    }

    public void setAllowUpload(boolean allowUpload) {
        this.allowUpload = allowUpload;
    }

    public String getUploadUrl() {
        return uploadUrl;
    }

    public void setUploadUrl(String uploadUrl) {
        this.uploadUrl = uploadUrl;
    }

    public String getUploadUrlBuilder() {
        return uploadUrlBuilder;
    }

    public void setUploadUrlBuilder(String uploadUrlBuilder) {
        this.uploadUrlBuilder = uploadUrlBuilder;
    }

    public Long getSizeLimit() {
        return sizeLimit;
    }

    public void setSizeLimit(Long sizeLimit) {
        this.sizeLimit = sizeLimit;
    }

    public boolean isAutoStart() {
        return autoStart;
    }

    public void setAutoStart(boolean autoStart) {
        this.autoStart = autoStart;
    }

    public boolean isUseHTML5() {
        return useHTML5;
    }

    public void setUseHTML5(boolean useHTML5) {
        this.useHTML5 = useHTML5;
    }

    public String getDropZone() {
        return dropZone;
    }

    public void setDropZone(String dropZone) {
        this.dropZone = dropZone;
    }

    public List<String> getMimeTypes() {
        return mimeTypes;
    }

    public void setMimeTypes(List<String> mimeTypes) {
        this.mimeTypes = mimeTypes;
    }

    @Override
    public String getResourceType() {
        return FileUploadWidget.RESOURCE_TYPE;
    }

    @Override
    public void setResourceType(String resourceType) {
        throw new UnsupportedOperationException("resourceType is Static for FileUploadWidget");
    }

}
