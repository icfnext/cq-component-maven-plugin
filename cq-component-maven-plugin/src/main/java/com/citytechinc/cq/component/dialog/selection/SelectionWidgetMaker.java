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
package com.citytechinc.cq.component.dialog.selection;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import javassist.ClassPool;
import javassist.CtClass;
import javassist.CtMember;
import javassist.NotFoundException;

import org.codehaus.plexus.util.StringUtils;

import com.citytechinc.cq.component.annotations.widgets.Selection;
import com.citytechinc.cq.component.dialog.DialogElement;
import com.citytechinc.cq.component.dialog.exception.InvalidComponentFieldException;
import com.citytechinc.cq.component.dialog.maker.AbstractWidgetMaker;
import com.citytechinc.cq.component.dialog.maker.WidgetMakerParameters;
import com.citytechinc.cq.component.dialog.widgetcollection.WidgetCollection;
import com.citytechinc.cq.component.dialog.widgetcollection.WidgetCollectionParameters;

/**
 * Builds a SelectionWidget from an annotated field. This maker will operate
 * with or without a stacked Selection annotation.
 * 
 * 
 * 
 */
public class SelectionWidgetMaker extends AbstractWidgetMaker {
	public SelectionWidgetMaker(WidgetMakerParameters parameters) {
		super(parameters);
	}

	private static final String OPTION_FIELD_NAME_PREFIX = "option";

	@Override
	public DialogElement make() throws ClassNotFoundException, InvalidComponentFieldException, NotFoundException {

		Selection selectionAnnotation = getAnnotation(Selection.class);
		SelectionWidgetParameters parameters = new SelectionWidgetParameters();
		parameters.setName(getNameForField());
		parameters.setFieldName(getFieldNameForField());
		parameters.setFieldLabel(getFieldLabelForField());
		parameters.setFieldDescription(getFieldDescriptionForField());
		parameters.setAllowBlank(!getIsRequiredForField());
		parameters.setAdditionalProperties(getAdditionalPropertiesForField());
		parameters.setDefaultValue(getDefaultValueForField());
		parameters.setHideLabel(getHideLabelForField());
		parameters.setListeners(getListeners());

		List<DialogElement> options = buildSelectionOptionsForField(selectionAnnotation);
		parameters.setType(getSelectionTypeForField(selectionAnnotation));
		parameters.setOptions(getOptionsUrlForField(selectionAnnotation));
		parameters.setOptionsProvider(getOptionsProviderForField(selectionAnnotation));
		parameters.setSortDir(getSortDirForField(selectionAnnotation));

		List<DialogElement> optionsList = null;

		if (options.size() > 0) {
			WidgetCollectionParameters wcp = new WidgetCollectionParameters();
			wcp.setContainedElements(options);
			wcp.setFieldName("options");
			optionsList = Arrays.asList(new DialogElement[] { new WidgetCollection(wcp) });
		}
		parameters.setContainedElements(optionsList);

		return new SelectionWidget(parameters);

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
				OptionParameters parameters = new OptionParameters();
				parameters.setFieldName(OPTION_FIELD_NAME_PREFIX + (i++));
				parameters.setText(curOptionAnnotation.text());
				parameters.setValue(curOptionAnnotation.value());
				parameters.setQtip(qtip);
				options.add(new Option(parameters));
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
		OptionParameters parameters = new OptionParameters();
		parameters.setFieldName(fieldName);
		parameters.setText(text);
		parameters.setValue(value);
		parameters.setQtip(qtip);
		return new Option(parameters);

	}
}
