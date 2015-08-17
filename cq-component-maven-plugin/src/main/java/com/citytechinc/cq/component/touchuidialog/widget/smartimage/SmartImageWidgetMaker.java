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
package com.citytechinc.cq.component.touchuidialog.widget.smartimage;

import java.util.Arrays;

import org.codehaus.plexus.util.StringUtils;

import com.citytechinc.cq.component.annotations.widgets.Html5SmartImage;
import com.citytechinc.cq.component.dialog.exception.InvalidComponentFieldException;
import com.citytechinc.cq.component.dialog.html5smartimage.Html5SmartImageWidgetMaker;
import com.citytechinc.cq.component.maven.util.LogSingleton;
import com.citytechinc.cq.component.touchuidialog.TouchUIDialogElement;
import com.citytechinc.cq.component.touchuidialog.exceptions.TouchUIDialogGenerationException;
import com.citytechinc.cq.component.touchuidialog.widget.maker.AbstractTouchUIWidgetMaker;
import com.citytechinc.cq.component.touchuidialog.widget.maker.TouchUIWidgetMakerParameters;

public class SmartImageWidgetMaker extends AbstractTouchUIWidgetMaker<SmartImageWidgetParameters> {
	private static final String[] MIME_TYPES = { "image" };
	private final TouchUIWidgetMakerParameters parameters;

	public SmartImageWidgetMaker(TouchUIWidgetMakerParameters parameters) {
		super(parameters);
		this.parameters = parameters;
	}

	@Override
	public TouchUIDialogElement make(SmartImageWidgetParameters widgetParameters) throws ClassNotFoundException,
		InvalidComponentFieldException, TouchUIDialogGenerationException {
		LogSingleton
			.getInstance()
			.warn(
				"There is no HTML5 Smart Image analog in the Touch UI. This field is being rendered as a fileupload however this is most likely not what you want. Use the image inline editor instead for this field.");

		Html5SmartImage smartFileAnnotation = getAnnotation(Html5SmartImage.class);

        widgetParameters.setName(getNameForField());
        widgetParameters.setFileName(getFileNameForField(smartFileAnnotation));
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
		widgetParameters.setMimeTypes(Arrays.asList(MIME_TYPES));
		widgetParameters.setFilereferenceparameter(Html5SmartImageWidgetMaker.FILE_REFERENCE_PARAMETER);

		return new SmartImageWidget(widgetParameters);
	}

    @Override
    protected String getNameForField() {
        String originalName = getName();

        if (originalName.startsWith("./")) {
            return originalName.substring(2);
        }

        return originalName;
    }


    public String getFileNameForField(Html5SmartImage annotation) {
        if (annotation != null & StringUtils.isNotBlank(annotation.fileName())) {
            return annotation.fileName();
        }

        return null;
    }

	public String getTitleForField(Html5SmartImage annotation) {
		if (annotation != null && StringUtils.isNotBlank(annotation.title())) {
			return annotation.title();
		}

		return null;
	}

	public String getTextForField(Html5SmartImage annotation) {
		if (annotation != null && StringUtils.isNotBlank(annotation.text())) {
			return annotation.text();
		}

		return null;
	}

	public String getIconForField(Html5SmartImage annotation) {
		if (annotation != null && StringUtils.isNotBlank(annotation.icon())) {
			return annotation.icon();
		}

		return null;
	}

	public boolean getMultipleForField(Html5SmartImage annotation) {
		if (annotation != null) {
			return annotation.multiple();
		}

		return false;
	}

	public String getFileNameParameterForField(Html5SmartImage annotation) {
		if (annotation != null && StringUtils.isNotBlank(annotation.fileNameParameter())) {
			return annotation.fileNameParameter();
		}

		return null;
	}

	public String getUploadUrlForField(Html5SmartImage annotation) {
		if (annotation != null && StringUtils.isNotBlank(annotation.uploadUrl())) {
			return annotation.touchUIUploadUrl();
		}

		return null;
	}

	public String getUploadUrlBuilderForField(Html5SmartImage annotation) {
		if (annotation != null && StringUtils.isNotBlank(annotation.uploadUrlBuilder())) {
			return annotation.uploadUrlBuilder();
		}

		return null;
	}

	public Long getSizeLimitForField(Html5SmartImage annotation) {
		if (annotation != null && annotation.sizeLimit() != 0) {
			return Long.valueOf(annotation.sizeLimit());
		}

		return null;
	}

	public boolean getAutoStartForField(Html5SmartImage annotation) {
		if (annotation != null) {
			return annotation.autoStart();
		}

		return false;
	}

	public boolean getUseHTML5ForField(Html5SmartImage annotation) {
		if (annotation != null) {
			return annotation.useHtml5();
		}

		return true;
	}

	public String getDropZoneForField(Html5SmartImage annotation) {
		if (annotation != null && StringUtils.isNotBlank(annotation.dropZone())) {
			return annotation.dropZone();
		}

		return null;
	}

}
