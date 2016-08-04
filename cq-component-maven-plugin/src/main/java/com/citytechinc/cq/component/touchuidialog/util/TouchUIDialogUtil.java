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
package com.citytechinc.cq.component.touchuidialog.util;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import javassist.ClassPool;
import javassist.CtClass;
import javassist.CtMember;
import javassist.CtMethod;
import javassist.NotFoundException;

import org.apache.commons.compress.archivers.zip.ZipArchiveOutputStream;
import org.codehaus.plexus.util.StringUtils;

import com.citytechinc.cq.component.annotations.Component;
import com.citytechinc.cq.component.annotations.DialogField;
import com.citytechinc.cq.component.annotations.IgnoreDialogField;
import com.citytechinc.cq.component.annotations.widgets.Selection;
import com.citytechinc.cq.component.dialog.ComponentNameTransformer;
import com.citytechinc.cq.component.dialog.DialogFieldConfig;
import com.citytechinc.cq.component.dialog.exception.InvalidComponentClassException;
import com.citytechinc.cq.component.dialog.exception.InvalidComponentFieldException;
import com.citytechinc.cq.component.dialog.util.DialogUtil;
import com.citytechinc.cq.component.maven.util.ComponentMojoUtil;
import com.citytechinc.cq.component.touchuidialog.TouchUIDialog;
import com.citytechinc.cq.component.touchuidialog.exceptions.TouchUIDialogGenerationException;
import com.citytechinc.cq.component.touchuidialog.exceptions.TouchUIDialogWriteException;
import com.citytechinc.cq.component.touchuidialog.factory.TouchUIDialogFactory;
import com.citytechinc.cq.component.touchuidialog.widget.maker.TouchUIWidgetMakerParameters;
import com.citytechinc.cq.component.touchuidialog.widget.radiogroup.RadioGroupWidget;
import com.citytechinc.cq.component.touchuidialog.widget.registry.TouchUIWidgetRegistry;
import com.citytechinc.cq.component.touchuidialog.widget.selection.options.OptionParameters;

public class TouchUIDialogUtil {
	private static final String OPTION_FIELD_NAME_PREFIX = "option";

	private TouchUIDialogUtil() {
	}

	public static List<TouchUIDialog> buildDialogsFromClassList(List<CtClass> classList, ClassLoader classLoader,
		ClassPool classPool, TouchUIWidgetRegistry widgetRegistry, ComponentNameTransformer transformer,
		File buildDirectory, String componentPathBase, String defaultComponentPathSuffix,
		ZipArchiveOutputStream archiveStream, Set<String> reservedNames) throws TouchUIDialogGenerationException,
		TouchUIDialogWriteException, ClassNotFoundException, NotFoundException, InvalidComponentClassException {

		List<TouchUIDialog> dialogList = new ArrayList<TouchUIDialog>();

		for (CtClass currentComponentClass : classList) {
			TouchUIDialog currentDialog =
				TouchUIDialogFactory.make(currentComponentClass, classLoader, classPool, widgetRegistry);

			if (currentDialog != null && isWidgetInComponentClass(currentComponentClass)) {
				File currentDialogOutput =
					writeDialogToFile(transformer, currentDialog, currentComponentClass, buildDirectory,
						componentPathBase, defaultComponentPathSuffix);
				writeDialogToArchiveFile(transformer, currentDialogOutput, currentComponentClass, archiveStream,
					reservedNames, componentPathBase, defaultComponentPathSuffix);
				dialogList.add(currentDialog);
			}
		}

		return dialogList;
	}

	public static File writeDialogToFile(ComponentNameTransformer transformer, TouchUIDialog dialog,
		CtClass componentClass, File buildDirectory, String componentPathBase, String defaultComponentPathSuffix)
		throws TouchUIDialogWriteException {
		try {
			return ComponentMojoUtil.writeElementToFile(transformer, dialog, componentClass, buildDirectory,
				componentPathBase, defaultComponentPathSuffix, dialog.getFileName());
		} catch (Exception e) {
			throw new TouchUIDialogWriteException("Exception encountered writing Dialog to File", e);
		}
	}

	public static void writeDialogToArchiveFile(ComponentNameTransformer transformer, File dialogFile,
		CtClass componentClass, ZipArchiveOutputStream archiveStream, Set<String> reservedNames,
		String componentPathBase, String defaultComponentPathSuffix) throws TouchUIDialogWriteException {

		try {
			ComponentMojoUtil.writeElementToArchiveFile(transformer, dialogFile, componentClass, archiveStream,
				reservedNames, componentPathBase, defaultComponentPathSuffix, "/" + dialogFile.getName());
		} catch (Exception e) {
			throw new TouchUIDialogWriteException("Exception encountered while writing Dialog File to Archive", e);
		}

	}

	public static List<TouchUIWidgetMakerParameters> getWidgetMakerParametersForComponentClass(CtClass componentClass,
		ClassLoader classLoader, ClassPool classPool, TouchUIWidgetRegistry widgetRegistry) throws NotFoundException,
		ClassNotFoundException, InvalidComponentClassException {

		List<TouchUIWidgetMakerParameters> widgetMakerParametersList = new ArrayList<TouchUIWidgetMakerParameters>();

		List<CtMember> fieldsAndMethods = new ArrayList<CtMember>();
		Component componentAnnotation = (Component) componentClass.getAnnotation(Component.class);
		fieldsAndMethods.addAll(ComponentMojoUtil.collectFields(componentClass,
			componentAnnotation.suppressFieldInheritanceForTouchUI()));
		fieldsAndMethods.addAll(ComponentMojoUtil.collectMethods(componentClass,
			componentAnnotation.suppressFieldInheritanceForTouchUI()));

		// Load the true class
		Class<?> trueComponentClass = classLoader.loadClass(componentClass.getName());

		// Iterate through all the fields creating configs for each and
		// preparing the necessary widget maker parameters
		for (CtMember member : fieldsAndMethods) {
			if (!member.hasAnnotation(IgnoreDialogField.class)) {
				DialogFieldConfig dialogFieldConfig = null;
				if (member instanceof CtMethod) {
					dialogFieldConfig = DialogUtil.getDialogFieldFromSuperClasses((CtMethod) member);
				} else {
					if (member.hasAnnotation(DialogField.class)) {
						dialogFieldConfig =
							new DialogFieldConfig((DialogField) member.getAnnotation(DialogField.class), member);
					}
				}

				if (dialogFieldConfig != null && !dialogFieldConfig.isSuppressTouchUI()) {
					TouchUIWidgetMakerParameters touchUIWidgetMakerParameters = new TouchUIWidgetMakerParameters();
					touchUIWidgetMakerParameters.setClassLoader(classLoader);
					touchUIWidgetMakerParameters.setContainingClass(trueComponentClass);
					touchUIWidgetMakerParameters.setDialogFieldConfig(dialogFieldConfig);
					touchUIWidgetMakerParameters.setClassPool(classPool);
					touchUIWidgetMakerParameters.setUseDotSlashInName(true);
					touchUIWidgetMakerParameters.setWidgetRegistry(widgetRegistry);
					widgetMakerParametersList.add(touchUIWidgetMakerParameters);
				}
			}
		}

		return widgetMakerParametersList;

	}

	public static boolean isWidgetInComponentClass(CtClass componentClass) throws NotFoundException,
		ClassNotFoundException, InvalidComponentClassException {

		List<CtMember> fieldsAndMethods = new ArrayList<CtMember>();
		Component componentAnnotation = (Component) componentClass.getAnnotation(Component.class);
		fieldsAndMethods.addAll(ComponentMojoUtil.collectFields(componentClass,
			componentAnnotation.suppressFieldInheritanceForTouchUI()));
		fieldsAndMethods.addAll(ComponentMojoUtil.collectMethods(componentClass,
			componentAnnotation.suppressFieldInheritanceForTouchUI()));

		// Iterate through all the fields creating configs for each and
		// preparing the necessary widget maker parameters
		for (CtMember member : fieldsAndMethods) {
			if (!member.hasAnnotation(IgnoreDialogField.class)) {
				DialogFieldConfig dialogFieldConfig = null;
				if (member instanceof CtMethod) {
					dialogFieldConfig = DialogUtil.getDialogFieldFromSuperClasses((CtMethod) member);
				} else {
					if (member.hasAnnotation(DialogField.class)) {
						dialogFieldConfig =
							new DialogFieldConfig((DialogField) member.getAnnotation(DialogField.class), member);
					}
				}

				if (dialogFieldConfig != null && !dialogFieldConfig.isSuppressTouchUI()) {
					return true;
				}
			}
		}

		return false;

	}

	public static final List<com.citytechinc.cq.component.touchuidialog.widget.selection.options.Option>
		getOptionsForSelection(Selection selectionAnnotation, Class<?> type, ClassLoader classLoader,
			ClassPool classPool) throws InvalidComponentFieldException {
		List<com.citytechinc.cq.component.touchuidialog.widget.selection.options.Option> options =
			new ArrayList<com.citytechinc.cq.component.touchuidialog.widget.selection.options.Option>();

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
				OptionParameters optionParameters = new OptionParameters();
				optionParameters.setText(curOptionAnnotation.text());
				optionParameters.setValue(curOptionAnnotation.value());
				optionParameters.setSelected(curOptionAnnotation.selected());
				optionParameters.setFieldName(OPTION_FIELD_NAME_PREFIX + (i++));

				if (Selection.RADIO.equals(selectionAnnotation.type())) {
					optionParameters.setResourceType(RadioGroupWidget.RADIO_RESOURCE_TYPE);
				}

				options.add(new com.citytechinc.cq.component.touchuidialog.widget.selection.options.Option(
					optionParameters));
			}
		}

		/*
		 * If options were not specified by the annotation then we check to see
		 * if the field is an Enum and if so, the options are pulled from the
		 * Enum definition
		 */
		else if (type.isEnum()) {
			int i = 0;
			try {
				for (Object curEnumObject : classLoader.loadClass(type.getName()).getEnumConstants()) {
					Enum<?> curEnum = (Enum<?>) curEnumObject;
					options.add(buildSelectionOptionForEnum(selectionAnnotation, curEnum, classPool,
						OPTION_FIELD_NAME_PREFIX + (i++)));
				}
			} catch (Exception e) {
				throw new InvalidComponentFieldException("Error generating selection from enum", e);
			}
		}

		return options;
	}

	protected static final com.citytechinc.cq.component.touchuidialog.widget.selection.options.Option
		buildSelectionOptionForEnum(Selection selectionAnnotation, Enum<?> optionEnum, ClassPool classPool,
			String fieldName) throws SecurityException, NoSuchFieldException, NotFoundException, ClassNotFoundException {

		String text = optionEnum.name();
		String value = optionEnum.name();

		CtClass annotatedEnumClass = classPool.getCtClass(optionEnum.getDeclaringClass().getName());
		CtMember annotatedEnumField = annotatedEnumClass.getField(optionEnum.name());
		com.citytechinc.cq.component.annotations.Option optionAnnotation =
			(com.citytechinc.cq.component.annotations.Option) annotatedEnumField
				.getAnnotation(com.citytechinc.cq.component.annotations.Option.class);

		OptionParameters parameters = new OptionParameters();
		if (optionAnnotation != null) {
			if (StringUtils.isNotEmpty(optionAnnotation.text())) {
				text = optionAnnotation.text();
			}
			if (StringUtils.isNotEmpty(optionAnnotation.value())) {
				value = optionAnnotation.value();
			}
			parameters.setSelected(optionAnnotation.selected());
		}
		parameters.setFieldName(fieldName);
		parameters.setText(text);
		parameters.setValue(value);

		if (Selection.RADIO.equals(selectionAnnotation.type())) {
			parameters.setResourceType(RadioGroupWidget.RADIO_RESOURCE_TYPE);
		}

		return new com.citytechinc.cq.component.touchuidialog.widget.selection.options.Option(parameters);

	}

}
