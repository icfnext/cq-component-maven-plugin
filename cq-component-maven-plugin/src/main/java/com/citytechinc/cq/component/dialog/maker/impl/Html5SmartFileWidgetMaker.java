package com.citytechinc.cq.component.dialog.maker.impl;

import java.util.Map;

import org.codehaus.plexus.util.StringUtils;

import com.citytechinc.cq.component.annotations.widgets.Html5SmartFile;
import com.citytechinc.cq.component.dialog.DialogElement;
import com.citytechinc.cq.component.dialog.field.DialogFieldMember;
import com.citytechinc.cq.component.dialog.impl.Html5SmartFileWidget;
import com.citytechinc.cq.component.dialog.maker.AbstractWidgetMaker;

public class Html5SmartFileWidgetMaker extends AbstractWidgetMaker {

	public DialogElement make(DialogFieldMember field, String xtype, boolean useDotSlashInName) throws ClassNotFoundException {

		Html5SmartFile smartFileAnnotation = field.getAnnotation(Html5SmartFile.class);

		String name = getNameForField(smartFileAnnotation, field, useDotSlashInName);
		String fieldName = getFieldNameForField(field);
		String fieldLabel = getFieldLabelForField(field);
		String fieldDescription = getFieldDescriptionForField(field);
		Boolean isRequired = getIsRequiredForField(field);
		Map<String, String> additionalProperties = getAdditionalPropertiesForField(field);
		boolean hideLabel = getHideLabelForField(field);

		boolean allowFileNameEditing = getAllowFileNameEditingForField(smartFileAnnotation);
		boolean allowFileReference = getAllowFileReferenceForField(smartFileAnnotation);
		boolean allowUpload = getAllowUploadForField(smartFileAnnotation);
		String ddAccept = getDdAcceptForField(smartFileAnnotation);
		String ddGroups = getDdGroupsForField(smartFileAnnotation);
		String fileNameParameter = getFileNameParameterForField(smartFileAnnotation);
		String fileReferenceParameter = getFileReferenceParameterForField(smartFileAnnotation);
		String mimeTypes = getMimeTypesForField(smartFileAnnotation);
		String mimeTypesDescription = getMimeTypesDescriptionForField(smartFileAnnotation);
		int sizeLimit = getSizeLimitForField(smartFileAnnotation);

		Html5SmartFileWidget widget= new Html5SmartFileWidget(fieldLabel, fieldDescription, !isRequired, hideLabel, name, fieldName,
			additionalProperties, allowFileNameEditing, allowFileReference, allowUpload, ddAccept, ddGroups,
			fileNameParameter, fileReferenceParameter, mimeTypes, mimeTypesDescription, sizeLimit);

		setListeners(widget, field.getAnnotation().listeners());

		return widget;

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

	private String getNameForField(Html5SmartFile smartFileAnnotation, DialogFieldMember field, boolean useDotSlashInName) {
		if (StringUtils.isNotEmpty(smartFileAnnotation.name())) {
			return smartFileAnnotation.name();
		}
		return getNameForField(field, useDotSlashInName);
	}

}
