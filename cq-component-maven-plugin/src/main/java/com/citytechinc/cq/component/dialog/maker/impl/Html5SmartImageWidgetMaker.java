package com.citytechinc.cq.component.dialog.maker.impl;

import org.codehaus.plexus.util.StringUtils;

import com.citytechinc.cq.component.annotations.widgets.Html5SmartImage;
import com.citytechinc.cq.component.dialog.DialogElement;
import com.citytechinc.cq.component.dialog.field.DialogFieldMember;
import com.citytechinc.cq.component.dialog.impl.Html5SmartImageWidget;
import com.citytechinc.cq.component.dialog.maker.AbstractWidgetMaker;

public class Html5SmartImageWidgetMaker extends AbstractWidgetMaker {

	public DialogElement make(DialogFieldMember field, String xtype, boolean useDotSlashInName) throws ClassNotFoundException {

		Html5SmartImage smartImageAnnotation = field.getAnnotation(Html5SmartImage.class);

		String fieldName = getFieldNameForField(field);
		String fieldLabel = getFieldLabelForField(field);
		String fieldDescription = getFieldDescriptionForField(field);
		Boolean isRequired = getIsRequiredForField(field);
		boolean hideLabel = getHideLabelForField(field);

		String name = getNameForField(smartImageAnnotation);
		boolean disableFlush = getDisableFlushForField(smartImageAnnotation);
		boolean disableInfo = getDisableInfoForField(smartImageAnnotation);
		boolean disableZoom = getDisableZoomForField(smartImageAnnotation);
		String cropParameter = getCropParameterForField(smartImageAnnotation);
		String fileNameParameter = getFileNameParameterForField(smartImageAnnotation);
		String fileReferenceParameter = getFileReferenceParameterForField(smartImageAnnotation);
		String mapParameter = getMapParameterForField(smartImageAnnotation);
		String rotateParameter = getRotateParameterForField(smartImageAnnotation);
		String uploadUrl = getUploadUrlForField(smartImageAnnotation);
		String ddGroups = getDdGroupsForField(smartImageAnnotation);
		boolean allowUpload = getAllowUploadForField(smartImageAnnotation);
		Integer height = getHeightForField(smartImageAnnotation);

		Html5SmartImageWidget widget = new Html5SmartImageWidget(name, disableFlush, disableInfo, disableZoom, cropParameter,
			fileNameParameter, fileReferenceParameter, mapParameter, rotateParameter, uploadUrl, ddGroups, allowUpload,
			isRequired, hideLabel, fieldLabel, fieldName, fieldDescription, height, smartImageAnnotation.tab());

		setListeners(widget, field.getAnnotation().listeners());

		return widget;

	}


	protected String getNameForField(Html5SmartImage smartImageAnnotation) {
		String name = smartImageAnnotation.name();

		if (StringUtils.isNotEmpty(name)) {
			return name;
		}

		return null;
	}

	protected String getCropParameterForField(Html5SmartImage smartImageAnnotation) {
		String cropParameter = smartImageAnnotation.cropParameter();

		if (StringUtils.isNotEmpty(cropParameter)) {
			return cropParameter;
		}

		return null;
	}

	protected String getFileNameParameterForField(Html5SmartImage smartImageAnnotation) {
		String fileNameParameter = smartImageAnnotation.fileNameParameter();

		if (StringUtils.isNotEmpty(fileNameParameter)) {
			return fileNameParameter;
		}

		return null;
	}

	protected String getFileReferenceParameterForField(Html5SmartImage smartImageAnnotation) {
		String fileReferenceParameter = smartImageAnnotation.fileReferenceParameter();

		if (StringUtils.isNotEmpty(fileReferenceParameter)) {
			return fileReferenceParameter;
		}

		return null;
	}

	protected String getMapParameterForField(Html5SmartImage smartImageAnnotation) {
		String mapParameter = smartImageAnnotation.mapParameter();

		if (StringUtils.isNotEmpty(mapParameter)) {
			return mapParameter;
		}

		return null;
	}

	protected String getRotateParameterForField(Html5SmartImage smartImageAnnotation) {
		String rotateParameter = smartImageAnnotation.rotateParameter();
		if (StringUtils.isNotEmpty(rotateParameter)) {
			return rotateParameter;
		}

		return null;
	}

	protected String getUploadUrlForField(Html5SmartImage smartImageAnnotation) {
		String uploadUrl = smartImageAnnotation.uploadUrl();
		if (StringUtils.isNotEmpty(uploadUrl)) {
			return uploadUrl;
		}

		return null;
	}

	protected String getDdGroupsForField(Html5SmartImage smartImageAnnotation) {

		String ddGroups = smartImageAnnotation.ddGroups();
		if (StringUtils.isNotEmpty(ddGroups)) {
			return ddGroups;
		}

		return null;

	}

	protected Integer getHeightForField(Html5SmartImage smartImageAnnotation) {
		Integer height = smartImageAnnotation.height();

		if (height != 0) {
			return height;
		}

		return null;
	}

	protected boolean getDisableFlushForField(Html5SmartImage smartImageAnnotation) {
		return smartImageAnnotation.disableFlush();
	}

	protected boolean getDisableInfoForField(Html5SmartImage smartImageAnnotation) {
		return smartImageAnnotation.disableInfo();
	}

	protected boolean getDisableZoomForField(Html5SmartImage smartImageAnnotation) {
		return smartImageAnnotation.disableZoom();
	}

	protected boolean getAllowUploadForField(Html5SmartImage smartImageAnnotation) {
		return smartImageAnnotation.allowUpload();
	}

}
