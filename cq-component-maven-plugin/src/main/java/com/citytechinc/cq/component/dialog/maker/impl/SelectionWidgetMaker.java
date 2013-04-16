package com.citytechinc.cq.component.dialog.maker.impl;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.codehaus.plexus.util.StringUtils;

import javassist.CannotCompileException;
import javassist.ClassPool;
import javassist.CtClass;
import javassist.CtField;
import javassist.NotFoundException;

import com.citytechinc.cq.component.annotations.DialogField;
import com.citytechinc.cq.component.annotations.widgets.Selection;
import com.citytechinc.cq.component.dialog.DialogElement;
import com.citytechinc.cq.component.dialog.exception.InvalidComponentFieldException;
import com.citytechinc.cq.component.dialog.impl.Option;
import com.citytechinc.cq.component.dialog.impl.SelectionWidget;
import com.citytechinc.cq.component.dialog.maker.AbstractWidgetMaker;
import com.citytechinc.cq.component.dialog.maker.WidgetMaker;
import com.citytechinc.cq.component.maven.util.WidgetConfigHolder;

/**
 * Builds a SelectionWidget from an annotated field. This maker will operate with or without a stacked
 * Selection annotation.
 *
 * @author paulmichelotti
 *
 */
public class SelectionWidgetMaker extends AbstractWidgetMaker {

	/* (non-Javadoc)
	 * @see com.citytechinc.cq.component.dialog.maker.AbstractWidgetMaker#make(java.lang.String, java.lang.reflect.Field, javassist.CtField, java.lang.Class, javassist.CtClass, java.util.Map, java.util.Map, java.lang.ClassLoader, javassist.ClassPool, boolean)
	 */
	public DialogElement make(String xtype, Field widgetField, CtField ctWidgetField, Class<?> containingClass,
		CtClass ctContainingClass, Map<Class<?>, WidgetConfigHolder> xtypeMap, Map<String, WidgetMaker> xTypeToWidgetMakerMap,
		ClassLoader classLoader, ClassPool classPool, boolean useDotSlashInName) throws ClassNotFoundException,
		InvalidComponentFieldException, CannotCompileException, NotFoundException {

		DialogField dialogFieldAnnotation = (DialogField) ctWidgetField.getAnnotation(DialogField.class);
		Selection selectionAnnotation = (Selection) ctWidgetField.getAnnotation(Selection.class);

		String name = getNameForField(dialogFieldAnnotation, widgetField, useDotSlashInName);
		String fieldName = getFieldNameForField(dialogFieldAnnotation, widgetField);
		String fieldLabel = getFieldLabelForField(dialogFieldAnnotation, widgetField);
		String fieldDescription = getFieldDescriptionForField(dialogFieldAnnotation);
		Boolean isRequired = getIsRequiredForField(dialogFieldAnnotation);
		Map<String, String> additionalProperties = getAdditionalPropertiesForField(dialogFieldAnnotation);
		String defaultValue = getDefaultValueForField(dialogFieldAnnotation);
		boolean hideLabel = dialogFieldAnnotation.hideLabel();

		List<DialogElement> options = buildSelectionOptionsForField(ctWidgetField, selectionAnnotation, classLoader,
			classPool);
		String selectionType = getSelectionTypeForField(ctWidgetField, selectionAnnotation);
		String optionsUrl = getOptionsUrlForField(ctWidgetField, selectionAnnotation);
		String optionsProvider = getOptionsProviderForField(ctWidgetField, selectionAnnotation);
		String sortDir = getSortDirForField(ctWidgetField, selectionAnnotation);

		return new SelectionWidget(selectionType, name, fieldLabel, fieldName, fieldDescription, isRequired, hideLabel,
			defaultValue, additionalProperties, options, optionsUrl, optionsProvider, sortDir);

	}

	private static final String getOptionsUrlForField(CtField widgetField, Selection fieldAnnotation) {
		if (fieldAnnotation != null && StringUtils.isNotEmpty(fieldAnnotation.optionsUrl())) {
			return fieldAnnotation.optionsUrl();
		}
		return null;
	}

	private static final String getOptionsProviderForField(CtField widgetField, Selection fieldAnnotation) {
		if (fieldAnnotation != null && StringUtils.isNotEmpty(fieldAnnotation.optionsProvider())) {
			return fieldAnnotation.optionsProvider();
		}
		return null;
	}

	private static final String getSortDirForField(CtField widgetField, Selection fieldAnnotation) {
		if (fieldAnnotation != null && StringUtils.isNotEmpty(fieldAnnotation.sortDir())) {
			return fieldAnnotation.sortDir();
		}
		return null;
	}

	private static final String getSelectionTypeForField(CtField widgetField, Selection fieldAnnotation) {
		if (fieldAnnotation != null
			&& (fieldAnnotation.type().equals(Selection.CHECKBOX) || fieldAnnotation.type().equals(Selection.COMBOBOX)
				|| fieldAnnotation.type().equals(Selection.RADIO) || fieldAnnotation.type().equals(Selection.SELECT))) {
			return fieldAnnotation.type();
		} else {
			return Selection.RADIO;
		}
	}

	private static final List<DialogElement> buildSelectionOptionsForField(CtField widgetField,
		Selection fieldAnnotation, ClassLoader classLoader, ClassPool classPool) throws InvalidComponentFieldException,
		CannotCompileException, NotFoundException, ClassNotFoundException {

		List<DialogElement> options = new ArrayList<DialogElement>();

		/*
		 * Options specified in the annotation take precedence
		 */
		if (fieldAnnotation != null && fieldAnnotation.options().length > 0) {
			for (com.citytechinc.cq.component.annotations.Option curOptionAnnotation : fieldAnnotation.options()) {
				if (StringUtils.isEmpty(curOptionAnnotation.value())) {
					throw new InvalidComponentFieldException(
						"Selection Options specified in the selectionOptions Annotation property must include a non-empty text and value attribute");
				}
				String qtip = null;
				if (StringUtils.isNotEmpty(curOptionAnnotation.qtip())) {
					qtip = curOptionAnnotation.qtip();
				}
				options.add(new Option(curOptionAnnotation.text(), curOptionAnnotation.value(), qtip));
			}
		}
		/*
		 * If options were not specified by the annotation then we check to see
		 * if the field is an Enum and if so, the options are pulled from the
		 * Enum definition
		 */
		else if (widgetField.getType().isEnum()) {
			for (Object curEnumObject : classLoader.loadClass(widgetField.getType().getName()).getEnumConstants()) {
				Enum<?> curEnum = (Enum<?>) curEnumObject;
				try {
					options.add(buildSelectionOptionForEnum(curEnum, classPool));
				} catch (SecurityException e) {
					throw new InvalidComponentFieldException("Invalid Enum Field", e);
				} catch (NoSuchFieldException e) {
					throw new InvalidComponentFieldException("Invalid Enum Field", e);
				}
			}
		}

		return options;

	}

	private static final Option buildSelectionOptionForEnum(Enum<?> optionEnum, ClassPool classPool)
		throws SecurityException, NoSuchFieldException, NotFoundException, ClassNotFoundException {

		String text = optionEnum.name();
		String value = optionEnum.name();
		String qtip = null;

		CtClass annotatedEnumClass = classPool.getCtClass(optionEnum.getDeclaringClass().getName());
		CtField annotatedEnumField = annotatedEnumClass.getField(optionEnum.name());
		com.citytechinc.cq.component.annotations.Option optionAnnotation = (com.citytechinc.cq.component.annotations.Option) annotatedEnumField
			.getAnnotation(com.citytechinc.cq.component.annotations.Option.class);

		if (optionAnnotation != null) {
			if (StringUtils.isNotEmpty(optionAnnotation.text())) {
				text = optionAnnotation.text();
			}
			if (StringUtils.isNotEmpty(optionAnnotation.value())) {
				value = optionAnnotation.value();
			}
			if (StringUtils.isNotEmpty(optionAnnotation.qtip())) {
				qtip = optionAnnotation.qtip();
			}
		}

		return new Option(text, value, qtip);

	}

}
