package com.citytechinc.cq.component.dialog.maker.impl;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

import javassist.ClassPool;
import javassist.CtClass;
import javassist.CtMember;
import javassist.NotFoundException;

import org.codehaus.plexus.util.StringUtils;

import com.citytechinc.cq.component.annotations.widgets.Selection;
import com.citytechinc.cq.component.dialog.DialogElement;
import com.citytechinc.cq.component.dialog.exception.InvalidComponentFieldException;
import com.citytechinc.cq.component.dialog.impl.Option;
import com.citytechinc.cq.component.dialog.impl.SelectionWidget;
import com.citytechinc.cq.component.dialog.impl.WidgetCollection;
import com.citytechinc.cq.component.dialog.maker.AbstractWidgetMaker;
import com.citytechinc.cq.component.dialog.maker.WidgetMakerParameters;

/**
 * Builds a SelectionWidget from an annotated field. This maker will operate
 * with or without a stacked Selection annotation.
 * 
 * @author paulmichelotti
 * 
 */
public class SelectionWidgetMaker extends AbstractWidgetMaker {
	public SelectionWidgetMaker(WidgetMakerParameters parameters) {
		super(parameters);
	}

	private static final String OPTION_FIELD_NAME_PREFIX = "option";

	public DialogElement make() throws ClassNotFoundException, InvalidComponentFieldException, NotFoundException {

		Selection selectionAnnotation = getAnnotation(Selection.class);

		String name = getNameForField();
		String fieldName = getFieldNameForField();
		String fieldLabel = getFieldLabelForField();
		String fieldDescription = getFieldDescriptionForField();
		Boolean isRequired = getIsRequiredForField();
		Map<String, String> additionalProperties = getAdditionalPropertiesForField();
		String defaultValue = getDefaultValueForField();
		boolean hideLabel = getHideLabelForField();

		List<DialogElement> options = buildSelectionOptionsForField(selectionAnnotation);
		String selectionType = getSelectionTypeForField(selectionAnnotation);
		String optionsUrl = getOptionsUrlForField(selectionAnnotation);
		String optionsProvider = getOptionsProviderForField(selectionAnnotation);
		String sortDir = getSortDirForField(selectionAnnotation);

		List<DialogElement> optionsList = null;

		if (StringUtils.isEmpty(optionsUrl)) {
			optionsList = Arrays.asList(new DialogElement[] { new WidgetCollection(options, "options") });
		}

		SelectionWidget widget = new SelectionWidget(selectionType, name, fieldLabel, fieldName, fieldDescription,
			isRequired, hideLabel, defaultValue, additionalProperties, optionsList, optionsUrl, optionsProvider,
			sortDir);

		setListeners(widget);

		return widget;

	}

	protected String getOptionsUrlForField(Selection fieldAnnotation) {
		if (fieldAnnotation != null && StringUtils.isNotEmpty(fieldAnnotation.optionsUrl())) {
			return fieldAnnotation.optionsUrl();
		}
		return null;
	}

	protected String getOptionsProviderForField(Selection fieldAnnotation) {
		if (fieldAnnotation != null && StringUtils.isNotEmpty(fieldAnnotation.optionsProvider())) {
			return fieldAnnotation.optionsProvider();
		}
		return null;
	}

	protected String getSortDirForField(Selection fieldAnnotation) {
		if (fieldAnnotation != null && StringUtils.isNotEmpty(fieldAnnotation.sortDir())) {
			return fieldAnnotation.sortDir();
		}
		return null;
	}

	protected String getSelectionTypeForField(Selection selectionAnnotation) {
		if (selectionAnnotation != null
			&& (selectionAnnotation.type().equals(Selection.CHECKBOX)
				|| selectionAnnotation.type().equals(Selection.COMBOBOX)
				|| selectionAnnotation.type().equals(Selection.RADIO) || selectionAnnotation.type().equals(
				Selection.SELECT))) {
			return selectionAnnotation.type();
		} else {
			return Selection.RADIO;
		}
	}

	protected List<DialogElement> buildSelectionOptionsForField(Selection selectionAnnotation)
		throws InvalidComponentFieldException, ClassNotFoundException, NotFoundException {

		List<DialogElement> options = new ArrayList<DialogElement>();

		/*
		 * Options specified in the annotation take precedence
		 */
		if (selectionAnnotation != null && selectionAnnotation.options().length > 0) {
			int i = 0;
			for (com.citytechinc.cq.component.annotations.Option curOptionAnnotation : selectionAnnotation.options()) {
				if (StringUtils.isEmpty(curOptionAnnotation.value())) {
					throw new InvalidComponentFieldException(
						"Selection Options specified in the selectionOptions Annotation property must include a non-empty text and value attribute");
				}
				String qtip = null;
				if (StringUtils.isNotEmpty(curOptionAnnotation.qtip())) {
					qtip = curOptionAnnotation.qtip();
				}
				options.add(new Option(curOptionAnnotation.text(), curOptionAnnotation.value(), qtip,
					OPTION_FIELD_NAME_PREFIX + (i++)));
			}
		}

		/*
		 * If options were not specified by the annotation then we check to see
		 * if the field is an Enum and if so, the options are pulled from the
		 * Enum definition
		 */
		else if (getType().isEnum()) {
			int i = 0;
			for (Object curEnumObject : parameters.getClassLoader().loadClass(getType().getName()).getEnumConstants()) {
				Enum<?> curEnum = (Enum<?>) curEnumObject;
				try {
					options.add(buildSelectionOptionForEnum(curEnum, parameters.getClassPool(),
						OPTION_FIELD_NAME_PREFIX + (i++)));
				} catch (SecurityException e) {
					throw new InvalidComponentFieldException("Invalid Enum Field", e);
				} catch (NoSuchFieldException e) {
					throw new InvalidComponentFieldException("Invalid Enum Field", e);
				}
			}
		}

		return options;
	}

	protected Option buildSelectionOptionForEnum(Enum<?> optionEnum, ClassPool classPool, String fieldName)
		throws SecurityException, NoSuchFieldException, NotFoundException, ClassNotFoundException {

		String text = optionEnum.name();
		String value = optionEnum.name();
		String qtip = null;

		CtClass annotatedEnumClass = classPool.getCtClass(optionEnum.getDeclaringClass().getName());
		CtMember annotatedEnumField = annotatedEnumClass.getField(optionEnum.name());
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

		return new Option(text, value, qtip, fieldName);

	}

}
