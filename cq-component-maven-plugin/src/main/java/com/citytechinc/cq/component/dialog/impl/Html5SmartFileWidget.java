package com.citytechinc.cq.component.dialog.impl;

import java.util.Map;

import com.citytechinc.cq.component.annotations.config.Widget;
import com.citytechinc.cq.component.annotations.widgets.Html5SmartFile;
import com.citytechinc.cq.component.dialog.AbstractWidget;
import com.citytechinc.cq.component.dialog.maker.impl.Html5SmartFileWidgetMaker;

@Widget(annotationClass = Html5SmartFile.class, makerClass = Html5SmartFileWidgetMaker.class, xtype = Html5SmartFileWidget.XTYPE)
public class Html5SmartFileWidget extends AbstractWidget {

	public static final String XTYPE = "html5smartfile";
	private static final String PRIMARY_TYPE = "cq:Widget";

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

	public Html5SmartFileWidget(String fieldLabel, String fieldDescription, boolean allowBlank, boolean hideLabel,
		String name, String fieldName, Map<String, String> additionalProperties, boolean allowFileNameEditing,
		boolean allowFileReference, boolean allowUpload, String ddAccept, String ddGroups, String fileNameParameter,
		String fileReferenceParameter, String mimeTypes, String mimeTypesDescription, int sizeLimit) {
		super(XTYPE, fieldLabel, fieldDescription, allowBlank, hideLabel, null, name, PRIMARY_TYPE, null, fieldName,
			additionalProperties, null);

		this.allowFileNameEditing = allowFileNameEditing;
		this.allowFileReference = allowFileReference;
		this.allowUpload = allowUpload;
		this.ddAccept = ddAccept;
		this.ddGroups = ddGroups;
		this.fileNameParameter = fileNameParameter;
		this.fileReferenceParameter = fileReferenceParameter;
		this.mimeTypes = mimeTypes;
		this.mimeTypesDescription = mimeTypesDescription;
		this.sizeLimit = sizeLimit;

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
