/**
 *    Copyright 2017 ICF Olson
 *
 *    Licensed under the Apache License, Version 2.0 (the "License");
 *    you may not use this file except in compliance with the License.
 *    You may obtain a copy of the License at
 *
 *        http://www.apache.org/licenses/LICENSE-2.0
 *
 *    Unless required by applicable law or agreed to in writing, software
 *    distributed under the License is distributed on an "AS IS" BASIS,
 *    WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *    See the License for the specific language governing permissions and
 *    limitations under the License.
 */
package com.citytechinc.cq.component.touchuidialog.widget.fileupload;

import com.citytechinc.cq.component.touchuidialog.TouchUIDialogType;
import com.citytechinc.cq.component.touchuidialog.widget.DefaultTouchUIWidgetParameters;

import java.util.List;

public class FileUploadWidgetParameters extends DefaultTouchUIWidgetParameters {

	private String title;
	private boolean async;
	private String text;
	private boolean hideText;
	private String icon;
	private String iconSize;
	private String size;
	private boolean multiple;
	private String uploadUrl;
	private String uploadUrlBuilder;
	private Long sizeLimit;
	private boolean autoStart;
	private boolean useHTML5;
	private String dropZone;
	// TODO: Event handling
	private List<String> mimeTypes;
	private boolean allowUpload;
	private String fileNameParameter;
	private String fileReferenceParameter;

	@Override
	public String getTitle() {
		return title;
	}

	@Override
	public void setTitle(String title) {
		this.title = title;
	}

	public boolean isAsync() {
		return async;
	}

	public void setAsync(boolean async) {
		this.async = async;
	}

	public String getText() {
		return text;
	}

	public void setText(String text) {
		this.text = text;
	}

	public boolean isHideText() {
		return hideText;
	}

	public void setHideText(boolean hideText) {
		this.hideText = hideText;
	}

	public String getIcon() {
		return icon;
	}

	public void setIcon(String icon) {
		this.icon = icon;
	}

	public String getIconSize() {
		return iconSize;
	}

	public void setIconSize(String iconSize) {
		this.iconSize = iconSize;
	}

	public String getSize() {
		return size;
	}

	public void setSize(String size) {
		this.size = size;
	}

	public boolean isMultiple() {
		return multiple;
	}

	public void setMultiple(boolean multiple) {
		this.multiple = multiple;
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

	public boolean isAllowUpload() {
		return allowUpload;
	}

	public void setAllowUpload(boolean allowUpload) {
		this.allowUpload = allowUpload;
	}

	public String getFileNameParameter() {
		return fileNameParameter;
	}

	public void setFileNameParameter(String fileNameParameter) {
		this.fileNameParameter = fileNameParameter;
	}

	public String getFileReferenceParameter() {
		return fileReferenceParameter;
	}

	public void setFileReferenceParameter(String fileReferenceParameter) {
		this.fileReferenceParameter = fileReferenceParameter;
	}

	@Override
	public String getResourceType() {
		if(TouchUIDialogType.CORAL3.isOfType(getTouchUIDialogType())) {
			return FileUploadCoral3Widget.RESOURCE_TYPE;
		}
		return FileUploadWidget.RESOURCE_TYPE;
	}

	@Override
	public void setResourceType(String resourceType) {
		throw new UnsupportedOperationException("resourceType is Static for FileUploadWidget");
	}
}