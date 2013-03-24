package com.citytechinc.cq.component.dialog.maker.smartimage;

import java.lang.reflect.Field;
import java.util.Map;

import javassist.CtClass;
import javassist.CtField;

import com.citytechinc.cq.component.annotations.DialogField;
import com.citytechinc.cq.component.annotations.widgets.Html5SmartImage;
import com.citytechinc.cq.component.dialog.Widget;
import com.citytechinc.cq.component.dialog.impl.SimpleHtml5SmartImageWidget;
import com.citytechinc.cq.component.dialog.maker.WidgetMaker;
import com.citytechinc.cq.component.dialog.maker.parent.AbstractWidgetMaker;

public class Html5SmartImageWidgetMaker extends AbstractWidgetMaker implements
		WidgetMaker {

	@Override
	public Widget make(String xtype, Field widgetField, CtField ctWidgetField,
			Class<?> containingClass, CtClass ctContainingClass, Map<Class<?>, String> xtypeMap)
			throws ClassNotFoundException {

		Html5SmartImage smartImageAnnotation = (Html5SmartImage) ctWidgetField.getAnnotation(Html5SmartImage.class);
		DialogField dialogFieldAnnotation = (DialogField) ctWidgetField.getAnnotation(DialogField.class);

		String name = getNameForField(dialogFieldAnnotation, widgetField);
		Boolean isRequired = getIsRequiredForField(dialogFieldAnnotation);
		String fieldName = getFieldNameForField(dialogFieldAnnotation, widgetField);
		String fieldLabel = getFieldLabelForField(dialogFieldAnnotation, widgetField);
		String fieldDescription = getFieldDescriptionForField(dialogFieldAnnotation);

		boolean disableFlush=smartImageAnnotation.disableFlush();
		boolean disableInfo=smartImageAnnotation.disableInfo();
		boolean disableZoom=smartImageAnnotation.disableZoom();
		String cropParameter=smartImageAnnotation.cropParameter();
		String fileNameParameter=smartImageAnnotation.fileNameParameter();
		String fileReferenceParameter=smartImageAnnotation.fileReferenceParameter();
		String mapParameter=smartImageAnnotation.mapParameter();
		String rotateParameter=smartImageAnnotation.rotateParameter();
		String uploadUrl=smartImageAnnotation.uploadUrl();
		String ddGroups=smartImageAnnotation.ddGroups();
		boolean allowUpload=smartImageAnnotation.allowUpload();
		int height=smartImageAnnotation.height();
		String title=dialogFieldAnnotation.tab();

		return new SimpleHtml5SmartImageWidget(
				name,
				title,
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
				fieldLabel,
				fieldName,
				fieldDescription,
				height,
				smartImageAnnotation.tab());

	}

}
