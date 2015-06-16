/**
 *    Copyright 2013 CITYTECH, Inc.
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

import java.util.Arrays;
import java.util.List;

import org.codehaus.plexus.util.StringUtils;

import com.citytechinc.cq.component.annotations.widgets.Html5SmartFile;
import com.citytechinc.cq.component.dialog.exception.InvalidComponentFieldException;
import com.citytechinc.cq.component.touchuidialog.TouchUIDialogElement;
import com.citytechinc.cq.component.touchuidialog.exceptions.TouchUIDialogGenerationException;
import com.citytechinc.cq.component.touchuidialog.widget.maker.AbstractTouchUIWidgetMaker;
import com.citytechinc.cq.component.touchuidialog.widget.maker.TouchUIWidgetMakerParameters;

public class FileUploadWidgetMaker extends AbstractTouchUIWidgetMaker<FileUploadWidgetParameters> {

	public FileUploadWidgetMaker(TouchUIWidgetMakerParameters parameters) {
		super(parameters);
	}

	@Override
	public TouchUIDialogElement make(FileUploadWidgetParameters widgetParameters) throws ClassNotFoundException,
		InvalidComponentFieldException, TouchUIDialogGenerationException {
		Html5SmartFile smartFileAnnotation = getAnnotation(Html5SmartFile.class);

		widgetParameters.setTitle(getTitleForField(smartFileAnnotation));
		widgetParameters.setText(getTextForField(smartFileAnnotation));
		widgetParameters.setIcon(getIconForField(smartFileAnnotation));
		widgetParameters.setMultiple(getMultipleForField(smartFileAnnotation));
		widgetParameters.setFileNameParameter(getFileNameParameterForField(smartFileAnnotation));
		widgetParameters.setUploadUrl(getUploadUrlForField(smartFileAnnotation));
		widgetParameters.setUploadUrlBuilder(getUploadUrlBuilderForField(smartFileAnnotation));
		widgetParameters.setSizeLimit(getSizeLimitForField(smartFileAnnotation));
		widgetParameters.setAutoStart(getAutoStartForField(smartFileAnnotation));
		widgetParameters.setUseHTML5(getUseHTML5ForField(smartFileAnnotation));
		widgetParameters.setDropZone(getDropZoneForField(smartFileAnnotation));
		widgetParameters.setMimeTypes(getMimeTypesForField(smartFileAnnotation));
		widgetParameters.setFilereferenceparameter(getFileReferenceForField(smartFileAnnotation));

		return new FileUploadWidget(widgetParameters);
	}

	private String getFileReferenceForField(Html5SmartFile annotation) {
		if (annotation != null && StringUtils.isNotBlank(annotation.fileReferenceParameter())) {
			return annotation.fileReferenceParameter();
		}

		return null;
	}

	public String getTitleForField(Html5SmartFile annotation) {
		if (annotation != null && StringUtils.isNotBlank(annotation.title())) {
			return annotation.title();
		}

		return null;
	}

	public String getTextForField(Html5SmartFile annotation) {
		if (annotation != null && StringUtils.isNotBlank(annotation.text())) {
			return annotation.text();
		}

		return null;
	}

	public String getIconForField(Html5SmartFile annotation) {
		if (annotation != null && StringUtils.isNotBlank(annotation.icon())) {
			return annotation.icon();
		}

		return null;
	}

	public boolean getMultipleForField(Html5SmartFile annotation) {
		if (annotation != null) {
			return annotation.multiple();
		}

		return false;
	}

	public String getFileNameParameterForField(Html5SmartFile annotation) {
		if (annotation != null && StringUtils.isNotBlank(annotation.fileNameParameter())) {
			return annotation.fileNameParameter();
		}

		return null;
	}

	public String getUploadUrlForField(Html5SmartFile annotation) {
		if (annotation != null && StringUtils.isNotBlank(annotation.uploadUrl())) {
			return annotation.uploadUrl();
		}

		return null;
	}

	public String getUploadUrlBuilderForField(Html5SmartFile annotation) {
		if (annotation != null && StringUtils.isNotBlank(annotation.uploadUrlBuilder())) {
			return annotation.uploadUrlBuilder();
		}

		return null;
	}

	public Long getSizeLimitForField(Html5SmartFile annotation) {
		if (annotation != null && annotation.sizeLimit() != 0) {
			return Long.valueOf(annotation.sizeLimit());
		}

		return null;
	}

	public boolean getAutoStartForField(Html5SmartFile annotation) {
		if (annotation != null) {
			return annotation.autoStart();
		}

		return false;
	}

	public boolean getUseHTML5ForField(Html5SmartFile annotation) {
		if (annotation != null) {
			return annotation.useHtml5();
		}

		return true;
	}

	public String getDropZoneForField(Html5SmartFile annotation) {
		if (annotation != null && StringUtils.isNotBlank(annotation.dropZone())) {
			return annotation.dropZone();
		}

		return null;
	}

	public List<String> getMimeTypesForField(Html5SmartFile annotation) {
		if (annotation != null && annotation.touchUIMimeTypes().length > 0) {
			return Arrays.asList(annotation.touchUIMimeTypes());
		}

		return null;
	}
}
