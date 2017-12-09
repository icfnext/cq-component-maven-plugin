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
	resourceType = FileUploadWidget.RESOURCE_TYPE)
public class FileUploadWidget extends AbstractTouchUIWidget {

	public static final String RESOURCE_TYPE = "granite/ui/components/foundation/form/fileupload";

	private final String title;
	private final String text;
	private final String icon;
	private final boolean multiple;
	private final boolean allowUpload;
	private final String uploadUrl;
	private final String uploadUrlBuilder;
	private final Long sizeLimit;
	private final boolean autoStart;
	private final boolean useHTML5;
	private final String dropZone;
	private final String fileNameParameter;
	private final String fileReferenceParameter;
	// TODO: Event handling ?
	private final List<String> mimeTypes;

	public FileUploadWidget(FileUploadWidgetParameters parameters) {
		super(parameters);
		title = parameters.getTitle();
		text = parameters.getText();
		icon = parameters.getIcon();
		multiple = parameters.isMultiple();
		allowUpload = parameters.isAllowUpload();
		uploadUrl = parameters.getUploadUrl();
		uploadUrlBuilder = parameters.getUploadUrlBuilder();
		sizeLimit = parameters.getSizeLimit();
		autoStart = parameters.isAutoStart();
		useHTML5 = parameters.isUseHTML5();
		dropZone = parameters.getDropZone();
		fileNameParameter = parameters.getFileNameParameter();
		fileReferenceParameter = parameters.getFileReferenceParameter();
		mimeTypes = parameters.getMimeTypes();
	}

	@Override
	public String getTitle() {
		return title;
	}

	public String getText() {
		return text;
	}

	public String getIcon() {
		return icon;
	}

	public boolean isAllowUpload() {
		return allowUpload;
	}

	public boolean isMultiple() {
		return multiple;
	}

	public String getUploadUrl() {
		return uploadUrl;
	}

	public String getUploadUrlBuilder() {
		return uploadUrlBuilder;
	}

	public Long getSizeLimit() {
		return sizeLimit;
	}

	public boolean isAutoStart() {
		return autoStart;
	}

	public boolean isUseHTML5() {
		return useHTML5;
	}

	public String getDropZone() {
		return dropZone;
	}

	public String getFileNameParameter() {
		return fileNameParameter;
	}

	public String getFileReferenceParameter() {
		return fileReferenceParameter;
	}

	public List<String> getMimeTypes() {
		return mimeTypes;
	}
}
