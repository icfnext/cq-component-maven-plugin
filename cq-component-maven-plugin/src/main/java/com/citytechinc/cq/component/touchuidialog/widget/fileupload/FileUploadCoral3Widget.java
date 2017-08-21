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

import com.citytechinc.cq.component.annotations.config.TouchUIWidget;
import com.citytechinc.cq.component.annotations.widgets.Html5SmartFile;
import com.citytechinc.cq.component.touchuidialog.widget.AbstractTouchUIWidget;

import java.util.List;

@TouchUIWidget(annotationClass = Html5SmartFile.class, makerClass = FileUploadWidgetMaker.class,
	resourceType = FileUploadCoral3Widget.RESOURCE_TYPE)
public class FileUploadCoral3Widget extends AbstractTouchUIWidget {

	/*
	At the moment, coral 3 fileupload supports only uploads from local machine,
	drag&drop from DAM doesn't work.

	TODO: Update resource type when fileupload works as expected
	public static final String RESOURCE_TYPE = "granite/ui/components/coral/foundation/form/fileupload";
	*/
	public static final String RESOURCE_TYPE = "cq/gui/components/authoring/dialog/fileupload";

	private final boolean async;
	private final String text;
	private boolean hideText;
	private final String icon;
	private final String iconSize;
	private final String size;
	private final boolean multiple;
	private final String uploadUrl;
	private final Long sizeLimit;
	private final boolean autoStart;
	// TODO: Event handling ?
	private final List<String> mimeTypes;
	private final String fileNameParameter;
	private final String fileReferenceParameter;
	private final boolean allowUpload;

	public FileUploadCoral3Widget(FileUploadWidgetParameters parameters) {
		super(parameters);
		async = parameters.isAsync();
		text = parameters.getText();
		hideText = parameters.isHideText();
		icon = parameters.getIcon();
		iconSize = parameters.getIconSize();
		size = parameters.getSize();
		multiple = parameters.isMultiple();
		uploadUrl = parameters.getUploadUrl();
		sizeLimit = parameters.getSizeLimit();
		autoStart = parameters.isAutoStart();
		fileNameParameter = parameters.getFileNameParameter();
		fileReferenceParameter = parameters.getFileReferenceParameter();
		mimeTypes = parameters.getMimeTypes();
		allowUpload = parameters.isAllowUpload();
	}

	public boolean isAsync() {
		return async;
	}

	public String getText() {
		return text;
	}

	public boolean isHideText() {
		return hideText;
	}

	public String getIcon() {
		return icon;
	}

	public String getIconSize() {
		return iconSize;
	}

	public String getSize() {
		return size;
	}

	public boolean isMultiple() {
		return multiple;
	}

	public String getUploadUrl() {
		return uploadUrl;
	}

	public Long getSizeLimit() {
		return sizeLimit;
	}

	public boolean isAutoStart() {
		return autoStart;
	}

	public List<String> getMimeTypes() {
		return mimeTypes;
	}

	public String getFileNameParameter() {
		return fileNameParameter;
	}

	public String getFileReferenceParameter() {
		return fileReferenceParameter;
	}

	public boolean isAllowUpload() {
		return allowUpload;
	}
}