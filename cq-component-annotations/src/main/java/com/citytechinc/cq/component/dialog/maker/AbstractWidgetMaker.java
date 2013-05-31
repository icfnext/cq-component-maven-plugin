package com.citytechinc.cq.component.dialog.maker;

import java.lang.reflect.AccessibleObject;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;

import org.codehaus.plexus.util.StringUtils;

import javassist.CannotCompileException;
import javassist.ClassPool;
import javassist.CtClass;
import javassist.CtMember;
import javassist.NotFoundException;

import com.citytechinc.cq.component.annotations.DialogField;
import com.citytechinc.cq.component.annotations.FieldProperty;
import com.citytechinc.cq.component.annotations.Listener;
import com.citytechinc.cq.component.dialog.AbstractWidget;
import com.citytechinc.cq.component.dialog.DialogElement;
import com.citytechinc.cq.component.dialog.Listeners;
import com.citytechinc.cq.component.dialog.exception.InvalidComponentFieldException;
import com.citytechinc.cq.component.maven.util.WidgetConfigHolder;

public abstract class AbstractWidgetMaker implements WidgetMaker {

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.citytechinc.cq.component.dialog.maker.WidgetMaker#make(java.lang.
	 * String, java.lang.reflect.AccessibleObject, javassist.CtField,
	 * java.lang.Class, javassist.CtClass, java.util.Map, java.util.Map,
	 * java.lang.ClassLoader, javassist.ClassPool, boolean)
	 */
	public abstract DialogElement make(String xtype, AccessibleObject widgetField, CtMember ctWidgetField,
		Class<?> containingClass, CtClass ctContainingClass, Map<Class<?>, WidgetConfigHolder> xtypeMap,
		Map<String, WidgetMaker> xTypeToWidgetMakerMap, ClassLoader classLoader, ClassPool classPool,
		boolean useDotSlashInName) throws ClassNotFoundException, InvalidComponentFieldException,
		CannotCompileException, NotFoundException, SecurityException, NoSuchFieldException;

	protected String getNameForField(DialogField dialogFieldAnnotation, AccessibleObject dialogField,
		boolean useDotSlash) {
		String overrideName = dialogFieldAnnotation.name();

		if (StringUtils.isNotEmpty(overrideName)) {
			return overrideName;
		}
		String name = null;
		if (dialogField instanceof Field) {
			name = ((Field) dialogField).getName();
		} else {
			name = getFieldNameFromMethodName(((Method) dialogField).getName());
		}
		if (!useDotSlash) {
			return name;
		}
		return "./" + name;
	}

	protected String getFieldNameForField(DialogField dialogFieldAnnotation, AccessibleObject dialogField) {
		String overrideFieldName = dialogFieldAnnotation.fieldName();

		if (StringUtils.isNotEmpty(overrideFieldName)) {
			return overrideFieldName;
		}

		String name = null;
		if (dialogField instanceof Field) {
			name = ((Field) dialogField).getName();
		} else {
			name = getFieldNameFromMethodName(((Method) dialogField).getName());
		}
		return name;
	}

	protected String getFieldLabelForField(DialogField dialogFieldAnnotation, AccessibleObject dialogField) {
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
		return dialogFieldAnnotation.required();
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

	protected String getFieldNameFromMethodName(String methodName) {
		String toReturn = methodName;
		if (methodName.startsWith("is")) {
			toReturn = methodName.substring(2);
		} else if (methodName.startsWith("get")) {
			toReturn = methodName.substring(3);
		}
		return StringUtils.uncapitalise(toReturn);
	}
	
	protected void setListeners(AbstractWidget widget,Listener[] listeners){
		if(listeners.length>0){
			widget.setListeners(new Listeners(listeners));
		}
	}
}
