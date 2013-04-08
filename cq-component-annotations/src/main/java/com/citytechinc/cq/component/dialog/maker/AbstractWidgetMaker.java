package com.citytechinc.cq.component.dialog.maker;

import java.lang.reflect.Field;
import java.util.HashMap;
import java.util.Map;

import org.codehaus.plexus.util.StringUtils;

import javassist.CannotCompileException;
import javassist.ClassPool;
import javassist.CtClass;
import javassist.CtField;
import javassist.NotFoundException;

import com.citytechinc.cq.component.annotations.DialogField;
import com.citytechinc.cq.component.annotations.FieldProperty;
import com.citytechinc.cq.component.dialog.DialogElement;
import com.citytechinc.cq.component.dialog.exception.InvalidComponentFieldException;
import com.citytechinc.cq.component.dialog.maker.WidgetMaker;

public abstract class AbstractWidgetMaker implements WidgetMaker {

	public abstract DialogElement make(String xtype, Field widgetField, CtField ctWidgetField,
			Class<?> containingClass, CtClass ctContainingClass, Map<Class<?>, String> xtypeMap,Map<String, WidgetMaker> xTypeToWidgetMakerMap,ClassLoader classLoader, ClassPool classPool,boolean useDotSlashInName) throws ClassNotFoundException, InvalidComponentFieldException, CannotCompileException, NotFoundException,SecurityException, NoSuchFieldException;


	protected String getNameForField(DialogField dialogFieldAnnotation, Field dialogField,boolean useDotSlash) {
		String overrideName = dialogFieldAnnotation.name();

		if (StringUtils.isNotEmpty(overrideName)) {
			return overrideName;
		}
		if(!useDotSlash){
			return dialogField.getName();
		}
		return "./" + dialogField.getName();
	}

	protected String getFieldNameForField(DialogField dialogFieldAnnotation, Field dialogField) {
		String overrideFieldName = dialogFieldAnnotation.fieldName();

		if (StringUtils.isNotEmpty(overrideFieldName)) {
			return overrideFieldName;
		}

		return dialogField.getName();
	}

	protected String getFieldLabelForField(DialogField dialogFieldAnnotation, Field dialogField) {
		String overrideLabel = dialogFieldAnnotation.fieldLabel();

		if (StringUtils.isNotEmpty(overrideLabel)) {
			return overrideLabel;
		}

		return null;
	}

	protected String getFieldDescriptionForField(DialogField dialogFieldAnnotation) {
		String overrideFieldDescription = dialogFieldAnnotation.fieldDescription();

		if (StringUtils.isNotEmpty(overrideFieldDescription)) {
			return overrideFieldDescription;
		}

		return null;
	}

	protected Boolean getIsRequiredForField(DialogField dialogFieldAnnotation) {
		return dialogFieldAnnotation.required() == true;
	}

	protected Map<String, String> getAdditionalPropertiesForField(DialogField dialogFieldAnnotation) {
		if (dialogFieldAnnotation.additionalProperties().length > 0) {
			Map<String, String> properties = new HashMap<String, String>();

			for (FieldProperty curProperty : dialogFieldAnnotation.additionalProperties()) {
				properties.put(curProperty.name(), curProperty.value());
			}

			return properties;
		}

		return null;
	}

	protected String getDefaultValueForField(DialogField dialogFieldAnnotation) {
		String defaultValue = dialogFieldAnnotation.defaultValue();

		if (StringUtils.isNotEmpty(defaultValue)) {
			return defaultValue;
		}

		return null;
	}
}
