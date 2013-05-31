package com.citytechinc.cq.component.dialog.maker.impl;

import java.lang.reflect.AccessibleObject;
import java.util.Map;

import javassist.CannotCompileException;
import javassist.ClassPool;
import javassist.CtClass;
import javassist.CtMember;
import javassist.NotFoundException;

import org.codehaus.plexus.util.StringUtils;

import com.citytechinc.cq.component.annotations.DialogField;
import com.citytechinc.cq.component.annotations.widgets.Html5SmartFile;
import com.citytechinc.cq.component.dialog.DialogElement;
import com.citytechinc.cq.component.dialog.exception.InvalidComponentFieldException;
import com.citytechinc.cq.component.dialog.impl.Html5SmartFileWidget;
import com.citytechinc.cq.component.dialog.maker.AbstractWidgetMaker;
import com.citytechinc.cq.component.dialog.maker.WidgetMaker;
import com.citytechinc.cq.component.maven.util.WidgetConfigHolder;

public class Html5SmartFileWidgetMaker extends AbstractWidgetMaker {

	@Override
	public DialogElement make(String xtype, AccessibleObject widgetField, CtMember ctWidgetField,
		Class<?> containingClass, CtClass ctContainingClass, Map<Class<?>, WidgetConfigHolder> xtypeMap,
		Map<String, WidgetMaker> xTypeToWidgetMakerMap, ClassLoader classLoader, ClassPool classPool,
		boolean useDotSlashInName) throws ClassNotFoundException, InvalidComponentFieldException,
		CannotCompileException, NotFoundException, SecurityException, NoSuchFieldException {

		Html5SmartFile smartFileAnnotation = (Html5SmartFile) ctWidgetField.getAnnotation(Html5SmartFile.class);
		DialogField dialogFieldAnnotation = (DialogField) ctWidgetField.getAnnotation(DialogField.class);

		String name = getNameForField(smartFileAnnotation, dialogFieldAnnotation, widgetField, useDotSlashInName);
		String fieldName = getFieldNameForField(dialogFieldAnnotation, widgetField);
		String fieldLabel = getFieldLabelForField(dialogFieldAnnotation, widgetField);
		String fieldDescription = getFieldDescriptionForField(dialogFieldAnnotation);
		Boolean isRequired = getIsRequiredForField(dialogFieldAnnotation);
		Map<String, String> additionalProperties = getAdditionalPropertiesForField(dialogFieldAnnotation);
		boolean hideLabel = dialogFieldAnnotation.hideLabel();

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
		
		setListeners(widget,dialogFieldAnnotation.listeners());
		
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

	private String getNameForField(Html5SmartFile smartFileAnnotation, DialogField dialogFieldAnnotation,
		AccessibleObject widgetField, boolean useDotSlashInName) {
		if (StringUtils.isNotEmpty(smartFileAnnotation.name())) {
			return smartFileAnnotation.name();
		}
		return getNameForField(dialogFieldAnnotation, widgetField, useDotSlashInName);
	}

}
