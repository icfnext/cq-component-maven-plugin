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

import com.citytechinc.cq.component.annotations.config.Widget;
import com.citytechinc.cq.component.annotations.widgets.Html5SmartFile;
import com.citytechinc.cq.component.dialog.AbstractWidget;

@Widget(annotationClass = Html5SmartFile.class, makerClass = Html5SmartFileWidgetMaker.class,
	xtype = Html5SmartFileWidget.XTYPE)
public class Html5SmartFileWidget extends AbstractWidget {

	public static final String XTYPE = "html5smartfile";

	private final boolean allowFileNameEditing;
	private final boolean allowFileReference;
	private final boolean allowUpload;
	private final String ddAccept;
	private final String ddGroups;
	private final String fileNameParameter;
	private final String fileReferenceParameter;
	private final String mimeTypes;
	private final String mimeTypesDescription;
	private final int sizeLimit;

	public Html5SmartFileWidget(Html5SmartFileWidgetParameters parameters) {
		super(parameters);

		this.allowFileNameEditing = parameters.isAllowFileNameEditing();
		this.allowFileReference = parameters.isAllowFileReference();
		this.allowUpload = parameters.isAllowUpload();
		this.ddAccept = parameters.getDdAccept();
		this.ddGroups = parameters.getDdGroups();
		this.fileNameParameter = parameters.getFileNameParameter();
		this.fileReferenceParameter = parameters.getFileReferenceParameter();
		this.mimeTypes = parameters.getMimeTypes();
		this.mimeTypesDescription = parameters.getMimeTypesDescription();
		this.sizeLimit = parameters.getSizeLimit();

	}

	public boolean isAllowFileNameEditing() {
		return allowFileNameEditing;
	}

	public boolean isAllowFileReference() {
		return allowFileReference;
	}

	public boolean isAllowUpload() {
		return allowUpload;
	}

	public String getDdAccept() {
		return ddAccept;
	}

	public String getDdGroups() {
		return ddGroups;
	}

	public String getFileNameParameter() {
		return fileNameParameter;
	}

	public String getFileReferenceParameter() {
		return fileReferenceParameter;
	}

	public String getMimeTypes() {
		return mimeTypes;
	}

	public String getMimeTypesDescription() {
		return mimeTypesDescription;
	}

	public int getSizeLimit() {
		return sizeLimit;
	}

}
