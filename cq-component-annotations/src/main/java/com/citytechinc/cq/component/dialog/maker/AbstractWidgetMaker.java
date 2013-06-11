package com.citytechinc.cq.component.dialog.maker;

import java.util.HashMap;
import java.util.Map;

import javassist.CtClass;
import javassist.CtField;
import javassist.NotFoundException;

import org.codehaus.plexus.util.StringUtils;

import com.citytechinc.cq.component.annotations.FieldProperty;
import com.citytechinc.cq.component.annotations.Listener;
import com.citytechinc.cq.component.dialog.AbstractWidget;
import com.citytechinc.cq.component.dialog.Listeners;
import com.citytechinc.cq.component.dialog.exception.InvalidComponentFieldException;
import com.citytechinc.cq.component.util.ComponentUtil;

public abstract class AbstractWidgetMaker implements WidgetMaker {
	protected final WidgetMakerParameters parameters;

	public AbstractWidgetMaker(WidgetMakerParameters parameters) {
		this.parameters = parameters;
	}

	protected String getNameForField() {
		String overrideName = parameters.getAnnotation().name();

		if (StringUtils.isNotEmpty(overrideName)) {
			return overrideName;
		}
		if (parameters.isUseDotSlashInName()) {
			return "./" + getName();
		}
		return getName();
	}

	protected String getFieldNameForField() {
		String overrideFieldName = parameters.getAnnotation().fieldName();

		if (StringUtils.isNotEmpty(overrideFieldName)) {
			return overrideFieldName;
		}

		return getName();
	}

	protected String getFieldLabelForField() {
		String overrideLabel = parameters.getAnnotation().fieldLabel();

		if (StringUtils.isNotEmpty(overrideLabel)) {
			return overrideLabel;
		}

		return null;
	}

	protected String getFieldDescriptionForField() {
		String overrideFieldDescription = parameters.getAnnotation().fieldDescription();

		if (StringUtils.isNotEmpty(overrideFieldDescription)) {
			return overrideFieldDescription;
		}

		return null;
	}

	protected Boolean getIsRequiredForField() {
		return parameters.getAnnotation().required();
	}

	protected Map<String, String> getAdditionalPropertiesForField() {
		if (parameters.getAnnotation().additionalProperties().length > 0) {
			Map<String, String> properties = new HashMap<String, String>();

			for (FieldProperty curProperty : parameters.getAnnotation().additionalProperties()) {
				properties.put(curProperty.name(), curProperty.value());
			}

			return properties;
		}

		return null;
	}

	protected String getDefaultValueForField() {
		String defaultValue = parameters.getAnnotation().defaultValue();

		if (StringUtils.isNotEmpty(defaultValue)) {
			return defaultValue;
		}

		return null;
	}

	protected boolean getHideLabelForField() {
		return parameters.getAnnotation().hideLabel();
	}

	// TODO: figure out how this is being used
	protected void setListeners(AbstractWidget widget) {
		Listener[] listeners = parameters.getAnnotation().listeners();
		if (listeners.length > 0) {
			widget.setListeners(new Listeners(listeners));
		}
	}

	protected String getName() {
		if (isField()) {
			return parameters.getCtMember().getName();
		} else {
			String tempName = parameters.getCtMember().getName();
			if (tempName.startsWith("is")) {
				return StringUtils.uncapitalise(tempName.substring(2));
			} else if (tempName.startsWith("get")) {
				return StringUtils.uncapitalise(tempName.substring(3));
			} else {
				return StringUtils.uncapitalise(tempName);
			}
		}
	}

	protected boolean isField() {
		if (parameters.getCtMember() instanceof CtField) {
			return true;
		} else {
			return false;
		}
	}

	protected boolean isMethod() {
		return !isField();
	}

	@SuppressWarnings("unchecked")
	public <T> T getAnnotation(Class<T> annotationClass) throws ClassNotFoundException {
		if (parameters.getCtMember().hasAnnotation(annotationClass)) {
			return (T) parameters.getCtMember().getAnnotation(annotationClass);
		}
		return null;
	}

	public boolean hasAnnotation(Class<?> annotationClass) {
		return parameters.getCtMember().hasAnnotation(annotationClass);
	}

	public CtClass getCtType() throws NotFoundException, InvalidComponentFieldException {
		return parameters.getClassPool().getCtClass(getType().getName());
	}

	public Class<?> getType() throws InvalidComponentFieldException {
		return ComponentUtil.getTypeForMember(parameters.getCtMember(), parameters.getContainingClass());
	}
}
