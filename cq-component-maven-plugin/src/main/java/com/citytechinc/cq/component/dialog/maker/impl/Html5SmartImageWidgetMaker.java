package com.citytechinc.cq.component.dialog.maker.impl;

import java.lang.reflect.Field;
import java.util.Map;

import org.codehaus.plexus.util.StringUtils;

import javassist.CtClass;
import javassist.CtField;

import com.citytechinc.cq.component.annotations.DialogField;
import com.citytechinc.cq.component.annotations.widgets.Html5SmartImage;
import com.citytechinc.cq.component.dialog.DialogElement;
import com.citytechinc.cq.component.dialog.impl.Html5SmartImageWidget;
import com.citytechinc.cq.component.dialog.maker.AbstractWidgetMaker;

public class Html5SmartImageWidgetMaker extends AbstractWidgetMaker{

	@Override
	public DialogElement make(String xtype, Field widgetField, CtField ctWidgetField,
			Class<?> containingClass, CtClass ctContainingClass, Map<Class<?>, String> xtypeMap)
			throws ClassNotFoundException {

		Html5SmartImage smartImageAnnotation = (Html5SmartImage) ctWidgetField.getAnnotation(Html5SmartImage.class);
		DialogField dialogFieldAnnotation = (DialogField) ctWidgetField.getAnnotation(DialogField.class);

		String name = null;
		if (StringUtils.isNotEmpty(smartImageAnnotation.name())) {
			name = smartImageAnnotation.name();
		}

		String cropParameter = null;
		if (StringUtils.isNotEmpty(smartImageAnnotation.cropParameter())) {
			cropParameter = smartImageAnnotation.cropParameter();
		}

		String fileNameParameter = null;
		if (StringUtils.isNotEmpty(smartImageAnnotation.fileNameParameter())) {
			fileNameParameter = smartImageAnnotation.fileNameParameter();
		}

		String fileReferenceParameter = null;
		if (StringUtils.isNotEmpty(smartImageAnnotation.fileReferenceParameter())) {
			fileReferenceParameter = smartImageAnnotation.fileReferenceParameter();
		}

		String mapParameter = null;
		if (StringUtils.isNotEmpty(smartImageAnnotation.mapParameter())) {
			mapParameter = smartImageAnnotation.mapParameter();
		}

		String rotateParameter = null;
		if (StringUtils.isNotEmpty(smartImageAnnotation.rotateParameter())) {
			rotateParameter = smartImageAnnotation.rotateParameter();
		}

		String uploadUrl = null;
		if (StringUtils.isNotEmpty(smartImageAnnotation.uploadUrl())) {
			uploadUrl = smartImageAnnotation.uploadUrl();
		}

		String ddGroups = null;
		if (StringUtils.isNotEmpty(smartImageAnnotation.ddGroups())) {
			ddGroups = smartImageAnnotation.ddGroups();
		}

		Integer height = null;
		if (smartImageAnnotation.height() != 0) {
			height = smartImageAnnotation.height();
		}

		Boolean isRequired = getIsRequiredForField(dialogFieldAnnotation);
		String fieldName = getFieldNameForField(dialogFieldAnnotation, widgetField);
		String fieldLabel = getFieldLabelForField(dialogFieldAnnotation, widgetField);
		String fieldDescription = getFieldDescriptionForField(dialogFieldAnnotation);
		boolean hideLabel=dialogFieldAnnotation.hideLabel();
		
		boolean disableFlush=smartImageAnnotation.disableFlush();
		boolean disableInfo=smartImageAnnotation.disableInfo();
		boolean disableZoom=smartImageAnnotation.disableZoom();
		boolean allowUpload=smartImageAnnotation.allowUpload();

		return new Html5SmartImageWidget(
				name,
				disableFlush,
				disableInfo,
				disableZoom,
				cropParameter,
				fileNameParameter,
				fileReferenceParameter,
				mapParameter,
				rotateParameter,
				uploadUrl,
				ddGroups,
				allowUpload,
				isRequired,
				hideLabel,
				fieldLabel,
				fieldName,
				fieldDescription,
				height,
				smartImageAnnotation.tab());

	}

}
