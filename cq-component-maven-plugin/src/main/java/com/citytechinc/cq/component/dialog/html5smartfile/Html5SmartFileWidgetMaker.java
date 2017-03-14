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
package com.citytechinc.cq.component.dialog.html5smartfile;

import org.codehaus.plexus.util.StringUtils;

import com.citytechinc.cq.component.annotations.widgets.Html5SmartFile;
import com.citytechinc.cq.component.dialog.DialogElement;
import com.citytechinc.cq.component.dialog.maker.AbstractWidgetMaker;
import com.citytechinc.cq.component.dialog.maker.WidgetMakerParameters;

public class Html5SmartFileWidgetMaker extends AbstractWidgetMaker<Html5SmartFileWidgetParameters> {

	public Html5SmartFileWidgetMaker(WidgetMakerParameters parameters) {
		super(parameters);
	}

	@Override
	public DialogElement make(Html5SmartFileWidgetParameters parameters) throws ClassNotFoundException {

		Html5SmartFile smartFileAnnotation = getAnnotation(Html5SmartFile.class);

		parameters.setName(getNameForField(smartFileAnnotation));

		parameters.setAllowFileNameEditing(getAllowFileNameEditingForField(smartFileAnnotation));
		parameters.setAllowFileReference(getAllowFileReferenceForField(smartFileAnnotation));
		parameters.setAllowUpload(getAllowUploadForField(smartFileAnnotation));
		parameters.setDdAccept(getDdAcceptForField(smartFileAnnotation));
		parameters.setDdGroups(getDdGroupsForField(smartFileAnnotation));
		parameters.setFileNameParameter(getFileNameParameterForField(smartFileAnnotation));
		parameters.setFileReferenceParameter(getFileReferenceParameterForField(smartFileAnnotation));
		parameters.setMimeTypes(getMimeTypesForField(smartFileAnnotation));
		parameters.setMimeTypesDescription(getMimeTypesDescriptionForField(smartFileAnnotation));
		parameters.setSizeLimit(getSizeLimitForField(smartFileAnnotation));

		return new Html5SmartFileWidget(parameters);

	}

	private int getSizeLimitForField(Html5SmartFile smartFileAnnotation) {
		return smartFileAnnotation.sizeLimit();
	}

	private String getMimeTypesDescriptionForField(Html5SmartFile smartFileAnnotation) {
		if (StringUtils.isNotEmpty(smartFileAnnotation.mimeTypesDescription())) {
			return smartFileAnnotation.mimeTypesDescription();
		}
		return null;
	}

	private String getMimeTypesForField(Html5SmartFile smartFileAnnotation) {
		if (StringUtils.isNotEmpty(smartFileAnnotation.mimeTypes())) {
			return smartFileAnnotation.mimeTypes();
		}
		return null;
	}

	private String getFileReferenceParameterForField(Html5SmartFile smartFileAnnotation) {
		return smartFileAnnotation.fileReferenceParameter();
	}

	private String getFileNameParameterForField(Html5SmartFile smartFileAnnotation) {
		return smartFileAnnotation.fileNameParameter();
	}

	private String getDdGroupsForField(Html5SmartFile smartFileAnnotation) {
		if (smartFileAnnotation.ddGroups().length != 0) {
			return "[" + StringUtils.join(smartFileAnnotation.ddGroups(), ",") + "]";
		}
		return null;
	}

	private String getDdAcceptForField(Html5SmartFile smartFileAnnotation) {
		if (StringUtils.isNotEmpty(smartFileAnnotation.ddAccept())) {
			return smartFileAnnotation.ddAccept();
		}
		return null;
	}

	private boolean getAllowFileReferenceForField(Html5SmartFile smartFileAnnotation) {
		return smartFileAnnotation.allowFileReference();
	}

	private boolean getAllowUploadForField(Html5SmartFile smartFileAnnotation) {
		return smartFileAnnotation.allowUpload();
	}

	private boolean getAllowFileNameEditingForField(Html5SmartFile smartFileAnnotation) {
		return smartFileAnnotation.allowFileNameEditing();
	}

	private String getNameForField(Html5SmartFile smartFileAnnotation) {
		if (StringUtils.isNotEmpty(smartFileAnnotation.name())) {
			return smartFileAnnotation.name();
		}
		return getNameForField();
	}

}
