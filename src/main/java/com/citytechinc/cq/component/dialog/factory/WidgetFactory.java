package com.citytechinc.cq.component.dialog.factory;

import java.lang.reflect.Field;
import java.lang.reflect.ParameterizedType;
import java.net.URI;
import java.net.URL;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javassist.CannotCompileException;
import javassist.CtClass;
import javassist.CtField;
import javassist.NotFoundException;

import org.codehaus.plexus.util.StringUtils;

import com.citytechinc.cq.component.annotations.DialogField;
import com.citytechinc.cq.component.annotations.DialogField.BooleanEnum;
import com.citytechinc.cq.component.annotations.FieldConfig;
import com.citytechinc.cq.component.annotations.FieldProperty;
import com.citytechinc.cq.component.dialog.MultiValueWidget;
import com.citytechinc.cq.component.dialog.Option;
import com.citytechinc.cq.component.dialog.SelectionWidget;
import com.citytechinc.cq.component.dialog.Widget;
import com.citytechinc.cq.component.dialog.exception.InvalidComponentFieldException;
import com.citytechinc.cq.component.dialog.impl.BasicFieldConfig;
import com.citytechinc.cq.component.dialog.impl.SimpleMultiValueWidget;
import com.citytechinc.cq.component.dialog.impl.SimpleOption;
import com.citytechinc.cq.component.dialog.impl.SimpleSelectionWidget;
import com.citytechinc.cq.component.dialog.impl.SimpleWidget;

public class WidgetFactory {

	public static final String TEXTFIELD_XTYPE = "textfield";
	public static final String NUMBERFIELD_XTYPE = "numberfield";
	public static final String PATHFIELD_XTYPE = "pathfield";
	public static final String SELECTION_XTYPE = "selection";
	public static final String MULTIFIELD_XTYPE = "multifield";

	public static Widget make(CtClass componentClass, CtField widgetField, Map<Class<?>, String> xtypeMap, ClassLoader classLoader)
			throws InvalidComponentFieldException, ClassNotFoundException, CannotCompileException, NotFoundException {

		DialogField propertyAnnotation = (DialogField) widgetField.getAnnotation(DialogField.class);

		if (propertyAnnotation == null) {
			throw new InvalidComponentFieldException();
		}

		String xtype = getXTypeForField(widgetField, propertyAnnotation, xtypeMap, classLoader);
		String name = getNameForField(widgetField, propertyAnnotation);
		String fieldName = getFieldNameForField(widgetField, propertyAnnotation);
		String fieldLabel = getFieldLabelForField(widgetField, propertyAnnotation);
		String fieldDescription = getFieldDescriptionForField(widgetField, propertyAnnotation);
		Boolean isRequired = getIsRequiredPropertyForField(widgetField, propertyAnnotation);
		Map<String, String> additionalProperties = getAdditionalPropertiesForField(widgetField, propertyAnnotation);

		if (xtype.equals(SELECTION_XTYPE)) {

			return buildSelectionWidget(
					componentClass,
					widgetField,
					propertyAnnotation,
					name,
					fieldName,
					fieldLabel,
					fieldDescription,
					isRequired,
					additionalProperties,
					classLoader);

		}

		if (xtype.equals(MULTIFIELD_XTYPE)) {

			return buildMultiFieldWidget(
					componentClass,
					widgetField,
					propertyAnnotation,
					name,
					fieldName,
					fieldLabel,
					fieldDescription,
					isRequired,
					additionalProperties,
					xtypeMap);
		}

		return new SimpleWidget(xtype, name, fieldName, fieldLabel, fieldDescription, isRequired, additionalProperties);

	}

	private static final Map<String, String> getAdditionalPropertiesForField(CtField widgetField, DialogField propertyAnnotation) {

		if (propertyAnnotation.additionalProperties().length > 0) {
			Map<String, String> properties = new HashMap<String, String>();

			for (FieldProperty curProperty : propertyAnnotation.additionalProperties()) {
				properties.put(curProperty.name(), curProperty.value());
			}

			return properties;
		}

		return null;

	}

	private static final Boolean getIsRequiredPropertyForField(CtField widgetField, DialogField propertyAnnotation) {
		return propertyAnnotation.required().equals(BooleanEnum.TRUE);
	}

	private static final String getNameForField(CtField widgetField, DialogField propertyAnnotation) {

		String overrideName = propertyAnnotation.name();

		if (StringUtils.isNotEmpty(overrideName)) {
			return overrideName;
		}

		return "./" + widgetField.getName();
	}

	private static final String getFieldLabelForField(CtField widgetField, DialogField propertyAnnotation) {

		String overrideLabel = propertyAnnotation.fieldLabel();

		if (StringUtils.isNotEmpty(overrideLabel)) {
			return overrideLabel;
		}

		return widgetField.getName();
	}

	private static final String getFieldNameForField(CtField widgetField, DialogField propertyAnnotation) {

		String overrideFieldName = propertyAnnotation.fieldName();

		if (StringUtils.isNotEmpty(overrideFieldName)) {
			return overrideFieldName;
		}

		return widgetField.getName();
	}

	private static final String getFieldDescriptionForField(CtField widgetField, DialogField propertyAnnotation) {

		String overrideFieldDescription = propertyAnnotation.fieldDescription();

		if (StringUtils.isNotEmpty(overrideFieldDescription)) {
			return overrideFieldDescription;
		}

		return null;
	}

	/*
	return buildMultiFieldWidget(
			componentClass,
			widgetField,
			propertyAnnotation,
			name,
			fieldName,
			fieldLabel,
			fieldDescription,
			isRequired,
			additionalProperties);
*/

	private static final MultiValueWidget buildMultiFieldWidget(
			CtClass componentClass,
			CtField widgetField,
			DialogField fieldAnnotation,
			String name,
			String fieldName,
			String fieldLabel,
			String fieldDescription,
			Boolean isRequired,
			Map<String, String> additionalProperties,
			Map<Class<?>, String> xtypeMap) throws InvalidComponentFieldException {

		String innerXType = getInnerXTypeForMultiField(widgetField, fieldAnnotation, xtypeMap);

		if (innerXType == null) {
			throw new InvalidComponentFieldException("Invalid or unsupported field annotation on a multi valued field");
		}

		Widget fieldConfig = new BasicFieldConfig(innerXType);

		List<Widget> fieldConfigs = new ArrayList<Widget>();

		fieldConfigs.add(fieldConfig);

		return new SimpleMultiValueWidget(MULTIFIELD_XTYPE, name, fieldName, fieldLabel, fieldDescription, isRequired, additionalProperties, fieldConfigs);
	}

	private static final String getInnerXTypeForMultiField(CtField widgetField, DialogField fieldAnnotation, Map<Class<?>, String> xtypeMap) throws InvalidComponentFieldException {

		List<FieldConfig> fieldConfigs = Arrays.asList(fieldAnnotation.fieldConfigs());

		/*
		 * Check to see if a field configuration annotation is present.  If so, use that
		 */
		if (!fieldConfigs.isEmpty()) {
			return fieldConfigs.get(0).xtype();
		}

		/*
		 * Otherwise, see if we can derive the xtype from the parameterized field
		 */
		String innerXType = getInnerXTypeForParameterizedField(widgetField, xtypeMap);

		if (innerXType != null) {
			return innerXType;
		}

		return null;
	}

	private static final SelectionWidget buildSelectionWidget(
			CtClass componentClass,
			CtField widgetField,
			DialogField fieldAnnotation,
			String name,
			String fieldName,
			String fieldLabel,
			String fieldDescription,
			Boolean isRequired,
			Map<String, String> additionalProperties,
			ClassLoader classLoader) throws InvalidComponentFieldException, CannotCompileException, NotFoundException, ClassNotFoundException {

		List<Option> options = buildSelectionOptionsForField(widgetField, fieldAnnotation, classLoader);

		String selectionType = getSelectionTypeForField(widgetField, fieldAnnotation);

		return new SimpleSelectionWidget(selectionType, name, fieldLabel, fieldName, fieldDescription, isRequired, additionalProperties, options);

	}

	private static final String getSelectionTypeForField(CtField widgetField, DialogField fieldAnnotation) {
		return fieldAnnotation.selectionType().name().toLowerCase();
	}

	private static final List<Option> buildSelectionOptionsForField(CtField widgetField, DialogField fieldAnnotation, ClassLoader classLoader) throws InvalidComponentFieldException, CannotCompileException, NotFoundException, ClassNotFoundException {

		List<Option> options = new ArrayList<Option>();

		/*
		 * Options specified in the annotation take precedence
		 */
		if (fieldAnnotation.selectionOptions().length > 0) {
			for(com.citytechinc.cq.component.annotations.Option curOptionAnnotation : fieldAnnotation.selectionOptions()) {
				if (StringUtils.isEmpty(curOptionAnnotation.text()) || StringUtils.isEmpty(curOptionAnnotation.value())) {
					throw new InvalidComponentFieldException("Selection Options specified in the selectionOptions Annotation property must include a non-empty text and value attribute");
				}
				options.add(new SimpleOption(curOptionAnnotation.text(), curOptionAnnotation.value()));
			}
		}
		/*
		 * If options were not specified by the annotation then we check
		 * to see if the field is an Enum and if so, the options are pulled
		 * from the Enum definition
		 */
		else if (widgetField.getType().isEnum()) {
			for(Object curEnumObject : classLoader.loadClass(widgetField.getType().getName()).getEnumConstants()) {
				Enum<?> curEnum = (Enum<?>) curEnumObject;
				try {
					options.add(buildSelectionOptionForEnum(curEnum));
				} catch (SecurityException e) {
					throw new InvalidComponentFieldException("Invalid Enum Field", e);
				} catch (NoSuchFieldException e) {
					throw new InvalidComponentFieldException("Invalid Enum Field", e);
				}
			}
		}

		return options;

	}

	private static final Option buildSelectionOptionForEnum(Enum<?> optionEnum)
			throws SecurityException, NoSuchFieldException {

		String text = optionEnum.name();
		String value = optionEnum.name();

		com.citytechinc.cq.component.annotations.Option optionAnnotation = optionEnum.getDeclaringClass().getField(optionEnum.name()).getAnnotation(com.citytechinc.cq.component.annotations.Option.class);

		if (optionAnnotation instanceof com.citytechinc.cq.component.annotations.Option) {
			if (StringUtils.isNotEmpty(optionAnnotation.text())) {
				text = optionAnnotation.text();
			}
			if (StringUtils.isNotEmpty(optionAnnotation.value())) {
				value = optionAnnotation.value();
			}
		}

		return new SimpleOption(text, value);

	}

	private static final String getXTypeForField(CtField widgetField, DialogField propertyAnnotation, Map<Class<?>, String> xtypeMap, ClassLoader classLoader) throws InvalidComponentFieldException, CannotCompileException, NotFoundException, ClassNotFoundException {

		/*
		 * Handle annotated xtypes
		 */
		String overrideXType = propertyAnnotation.xtype();

		if (StringUtils.isNotEmpty(overrideXType)) {
			return overrideXType;
		}

		Class<?> fieldClass = classLoader.loadClass(widgetField.getType().getName());

		/*
		 * Handle custom types
		 */
		for (Class<?> curCustomClass : xtypeMap.keySet()) {
			if (curCustomClass.isAssignableFrom(fieldClass)) {
				return xtypeMap.get(curCustomClass);
			}
		}

		/*
		 * Handle standard types
		 */

		/*
		 * numberfield
		 */
		if (
				Number.class.isAssignableFrom(fieldClass) ||
				fieldClass.equals(int.class) ||
				fieldClass.equals(double.class) ||
				fieldClass.equals(float.class)) {
			return NUMBERFIELD_XTYPE;
		}

		/*
		 * textfield
		 */
		if (fieldClass.equals(String.class)) {
			return TEXTFIELD_XTYPE;
		}

		/*
		 *  pathfield
		 */
		if (URI.class.isAssignableFrom(fieldClass) || URL.class.isAssignableFrom(fieldClass)) {
			return PATHFIELD_XTYPE;
		}

		/*
		 * selection
		 */
		if (fieldClass.isEnum()) {
			return SELECTION_XTYPE;
		}

		if (List.class.isAssignableFrom(fieldClass)) {

			String simpleXtype = getInnerXTypeForParameterizedField(widgetField, xtypeMap);

			/*
			 * TODO: This is where the multicompositefield would end up being selected once implemented
			 */
			if (simpleXtype == null) {
				throw new InvalidComponentFieldException("Parameterized class for List is not of a supported type.  Currently supported types are numbers, strings, and links");
			}

			return MULTIFIELD_XTYPE;

		}

		/*
		 * If we could not determine an xtype, return textfield
		 *
		 * TODO: Determine if this is appropriate or if this should throw an Exception
		 */
		return TEXTFIELD_XTYPE;
	}

	private static final String getInnerXTypeForParameterizedField(CtField widgetField, Map<Class<?>, String> xtypeMap) throws InvalidComponentFieldException {

		return "textfield";

		/*
		 * TODO: This all would need to be re-implemented
		ParameterizedType parameterizedType = (ParameterizedType) widgetField.getGenericType();

		if (parameterizedType.getActualTypeArguments().length == 0 || parameterizedType.getActualTypeArguments().length > 1) {
			throw new InvalidComponentFieldException("List dialog property found with a paramaterized type count not equal to 1");
		}

		String simpleXtype = getSimpleXTypeForClass((Class<?>) parameterizedType.getActualTypeArguments()[0], xtypeMap);

		return simpleXtype;
		*/
	}

	private static final String getSimpleXTypeForClass(Class<?> fieldClass, Map<Class<?>, String> xtypeMap) {

		/*
		 * Handle custom types
		 */
		for (Class<?> curCustomClass : xtypeMap.keySet()) {
			if (curCustomClass.isAssignableFrom(fieldClass)) {
				return xtypeMap.get(curCustomClass);
			}
		}

		/*
		 * numberfield
		 */
		if (
				Number.class.isAssignableFrom(fieldClass) ||
				fieldClass.equals(int.class) ||
				fieldClass.equals(double.class) ||
				fieldClass.equals(float.class)) {
			return NUMBERFIELD_XTYPE;
		}

		/*
		 * textfield
		 */
		if (fieldClass.equals(String.class)) {
			return TEXTFIELD_XTYPE;
		}

		/*
		 *  pathfield
		 */
		if (URI.class.isAssignableFrom(fieldClass) || URL.class.isAssignableFrom(fieldClass)) {
			return PATHFIELD_XTYPE;
		}

		return null;

	}
}
