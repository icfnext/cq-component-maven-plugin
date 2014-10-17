/**
 *    Copyright 2013 CITYTECH, Inc.
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
package com.citytechinc.cq.component.dialog.maker;

import java.util.HashMap;
import java.util.Map;

import javassist.CtClass;
import javassist.CtField;
import javassist.NotFoundException;

import org.codehaus.plexus.util.StringUtils;

import com.citytechinc.cq.component.annotations.FieldProperty;
import com.citytechinc.cq.component.annotations.Listener;
import com.citytechinc.cq.component.dialog.Listeners;
import com.citytechinc.cq.component.dialog.ListenersParameters;
import com.citytechinc.cq.component.dialog.exception.InvalidComponentFieldException;
import com.citytechinc.cq.component.util.ComponentUtil;

/**
 *
 *
 */
public abstract class AbstractWidgetMaker implements WidgetMaker {

	protected final WidgetMakerParameters parameters;

	/**
	 * Widget Makers will take, as input to their constructor, Widget parameters
	 * which they can later use as they make their intended Widget.
	 *
	 * @param parameters
	 */
	public AbstractWidgetMaker(WidgetMakerParameters parameters) {
		this.parameters = parameters;
	}

	/**
	 * <p>
	 * Determines and returns the name for the dialog field. The name is the
	 * relative path to where any authored input using the resultant widget will
	 * be housed in the content repository.
	 * </p>
	 * <p>
	 * When useDotSlashInName is true, the string './' will be prepended to the
	 * determined name.
	 * </p>
	 *
	 * @return The name property of the DialogField annotation if one is
	 *         provided, otherwise the result of the {@link #getName()} method.
	 */
	protected String getNameForField() {
		String overrideName = parameters.getDialogField().getName();

		if (StringUtils.isNotEmpty(overrideName)) {
			return overrideName;
		}
		if (parameters.isUseDotSlashInName()) {
			return "./" + getName();
		}
		return getName();
	}

	/**
	 *
	 * @return The fieldName property of the DialogField annotation if one is
	 *         provided, the result of the {@link #getName()} method otherwise.
	 */
	protected String getFieldNameForField() {
		String overrideFieldName = parameters.getDialogField().getFieldName();

		if (StringUtils.isNotEmpty(overrideFieldName)) {
			return overrideFieldName;
		}

		return getName();
	}

	/**
	 *
	 * @return The fieldLabel property of the DialogField annotation if one is
	 *         provided, null otherwise.
	 */
	protected String getFieldLabelForField() {
		String overrideLabel = parameters.getDialogField().getFieldLabel();

		if (StringUtils.isNotEmpty(overrideLabel)) {
			return overrideLabel;
		}

		return null;
	}

	/**
	 *
	 * @return The fieldDescription property of the DialogField annotation if
	 *         one is provided, null otherwise.
	 */
	protected String getFieldDescriptionForField() {
		String overrideFieldDescription = parameters.getDialogField().getFieldDescription();

		if (StringUtils.isNotEmpty(overrideFieldDescription)) {
			return overrideFieldDescription;
		}

		return null;
	}

	/**
	 *
	 * @return required property of the DialogField annotation.
	 */
	protected Boolean getIsRequiredForField() {
		return parameters.getDialogField().isRequired();
	}

	/**
	 *
	 * @return Name Value pairs represented by the additional properties tied to
	 *         the DialogField annotation, or null if no such properties are
	 *         defined.
	 */
	protected Map<String, String> getAdditionalPropertiesForField() {
		if (parameters.getDialogField().getAdditionalProperties().length > 0) {
			Map<String, String> properties = new HashMap<String, String>();

			for (FieldProperty curProperty : parameters.getDialogField().getAdditionalProperties()) {
				properties.put(curProperty.name(), curProperty.value());
			}

			return properties;
		}

		return null;
	}

	/**
	 *
	 * @return The defaultValue property of the DialogField annotation if one is
	 *         provided, null otherwise.
	 */
	protected String getDefaultValueForField() {
		String defaultValue = parameters.getDialogField().getDefaultValue();

		if (StringUtils.isNotEmpty(defaultValue)) {
			return defaultValue;
		}

		return null;
	}

	/**
	 *
	 * @return The hideLabel property of the DialogField annotation.
	 */
	protected boolean getHideLabelForField() {
		return parameters.getDialogField().isHideLabel();
	}

	/**
	 *
	 * @return The Listeners object configured via the listeners property of the
	 *         DialogField annotation or null if no such configuration is
	 *         defined.
	 */
	protected Listeners getListeners() {
		Listener[] listeners = parameters.getDialogField().getListeners();
		if (listeners.length > 0) {
			ListenersParameters parameters = new ListenersParameters();
			parameters.setListenerAnnotations(listeners);
			return new Listeners(parameters);
		}
		return null;
	}

	/**
	 *
	 * @return When the widget is represented by a field in the Java class, this
	 *         method will return the name of the field. When the widget is
	 *         represented by a method in the Java Class, the string 'is' or
	 *         'get' is stripped from the method name if it starts with either
	 *         of these strings, and then returns the resultant string.
	 */
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

	/**
	 *
	 * @return True if the Widget is represented by a field in the Component
	 *         class, false otherwise.
	 */
	protected boolean isField() {
		if (parameters.getCtMember() instanceof CtField) {
			return true;
		} else {
			return false;
		}
	}

	/**
	 *
	 * @return True if the Widget is represented by a method in the Component
	 *         class, false otherwise.
	 */
	protected boolean isMethod() {
		return !isField();
	}

	/**
	 *
	 * @param annotationClass The type of annotation to look for on the Widget
	 *            element
	 * @return An Annotation of the type requested if one is associated with the
	 *         field or method representing the Widget being made, null
	 *         otherwise.
	 * @throws ClassNotFoundException
	 */
	@SuppressWarnings("unchecked")
	public <T> T getAnnotation(Class<T> annotationClass) throws ClassNotFoundException {
		if (parameters.getCtMember().hasAnnotation(annotationClass)) {
			return (T) parameters.getCtMember().getAnnotation(annotationClass);
		}
		return null;
	}

	/**
	 *
	 * @param annotationClass The type of annotation to look for on the Widget
	 *            element
	 * @return True if an annotation of the type specified is associated with
	 *         the field or method representing the Widget being made, false
	 *         otherwise.
	 */
	public boolean hasAnnotation(Class<?> annotationClass) {
		return parameters.getCtMember().hasAnnotation(annotationClass);
	}

	/**
	 *
	 * @return THe CtType of the field or method representing the Widget
	 * @throws NotFoundException
	 * @throws InvalidComponentFieldException
	 */
	public CtClass getCtType() throws NotFoundException, InvalidComponentFieldException {
		return parameters.getClassPool().getCtClass(getType().getName());
	}

	/**
	 *
	 * @return The Class of the field or method representing the Widget
	 * @throws InvalidComponentFieldException
	 */
	public Class<?> getType() throws InvalidComponentFieldException {
		return ComponentUtil.getTypeForMember(parameters.getCtMember(), parameters.getContainingClass());
	}
}
