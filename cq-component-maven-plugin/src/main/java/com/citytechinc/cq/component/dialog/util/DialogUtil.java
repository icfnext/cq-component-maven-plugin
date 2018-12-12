/**
 *    Copyright 2017 ICF Olson
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
package com.citytechinc.cq.component.dialog.util;

import java.io.File;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Set;

import com.citytechinc.cq.component.annotations.*;
import javassist.CannotCompileException;
import javassist.ClassPool;
import javassist.CtClass;
import javassist.CtMember;
import javassist.CtMethod;
import javassist.NotFoundException;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.TransformerException;

import org.apache.commons.compress.archivers.zip.ZipArchiveOutputStream;
import org.codehaus.plexus.util.StringUtils;

import com.citytechinc.cq.component.dialog.ComponentNameTransformer;
import com.citytechinc.cq.component.dialog.Dialog;
import com.citytechinc.cq.component.dialog.DialogFieldConfig;
import com.citytechinc.cq.component.dialog.exception.InvalidComponentClassException;
import com.citytechinc.cq.component.dialog.exception.InvalidComponentFieldException;
import com.citytechinc.cq.component.dialog.exception.OutputFailureException;
import com.citytechinc.cq.component.dialog.factory.DialogFactory;
import com.citytechinc.cq.component.dialog.widget.WidgetRegistry;
import com.citytechinc.cq.component.maven.util.ComponentMojoUtil;

public class DialogUtil {
	private DialogUtil() {
	};

	/**
	 * Writes a dialog.xml file, the path of which being based on the component
	 * Class.
	 * 
	 * @param dialog
	 * @param componentClass
	 * @return The written file
	 * @throws OutputFailureException
	 * @throws IOException
	 * @throws ParserConfigurationException
	 * @throws TransformerException
	 * @throws ClassNotFoundException
	 * @throws IllegalArgumentException
	 * @throws SecurityException
	 * @throws IllegalAccessException
	 * @throws InvocationTargetException
	 * @throws NoSuchMethodException
	 */
	public static File writeDialogToFile(ComponentNameTransformer transformer, Dialog dialog, CtClass componentClass,
		File buildDirectory, String componentPathBase, String defaultComponentPathSuffix)
		throws OutputFailureException, IOException, ParserConfigurationException, TransformerException,
		ClassNotFoundException, IllegalArgumentException, SecurityException, IllegalAccessException,
		InvocationTargetException, NoSuchMethodException {

		return ComponentMojoUtil.writeElementToFile(transformer, dialog, componentClass, buildDirectory,
			componentPathBase, defaultComponentPathSuffix, dialog.getFileName());

	}

	/**
	 * Writes a provided dialog file to a provided archive output stream at a
	 * path determined by the class of the component.
	 * 
	 * @param dialogFile
	 * @param componentClass
	 * @param archiveStream
	 * @param reservedNames A list of files which already exist within the Zip
	 *            Archive. If a dialog.xml file already exists for a particular
	 *            component, it is left untouched.
	 * @param componentPathBase
	 * @throws IOException
	 * @throws ClassNotFoundException
	 */
	public static void writeDialogToArchiveFile(ComponentNameTransformer transformer, File dialogFile,
		CtClass componentClass, ZipArchiveOutputStream archiveStream, Set<String> reservedNames,
		String componentPathBase, String defaultComponentPathSuffix) throws IOException, ClassNotFoundException {

		ComponentMojoUtil.writeElementToArchiveFile(transformer, dialogFile, componentClass, archiveStream,
			reservedNames, componentPathBase, defaultComponentPathSuffix, "/" + dialogFile.getName());
	}

	/**
	 * Constructs a list of Dialog objects based on Classes annotated by
	 * Component annotations. Scans the provided list of classes constructing a
	 * Dialog object for each one annotated with the Component annotation. Any
	 * classes provided in the class list which are not thusly annotated are
	 * ignored.
	 * 
	 * @param classList
	 * @param zipOutputStream
	 * @param reservedNames
	 * @param classLoader
	 * @param classPool
	 * @return A list of constructed Dialog objects
	 * @throws InvalidComponentClassException
	 * @throws InvalidComponentFieldException
	 * @throws OutputFailureException
	 * @throws IOException
	 * @throws ParserConfigurationException
	 * @throws TransformerException
	 * @throws ClassNotFoundException
	 * @throws CannotCompileException
	 * @throws NotFoundException
	 * @throws SecurityException
	 * @throws NoSuchFieldException
	 * @throws IllegalArgumentException
	 * @throws IllegalAccessException
	 * @throws InvocationTargetException
	 * @throws NoSuchMethodException
	 * @throws InstantiationException
	 */
	public static List<Dialog> buildDialogsFromClassList(ComponentNameTransformer transformer, List<CtClass> classList,
		ZipArchiveOutputStream zipOutputStream, Set<String> reservedNames, WidgetRegistry widgetRegistry,
		ClassLoader classLoader, ClassPool classPool, File buildDirectory, String componentPathBase,
		String defaultComponentPathSuffix) throws InvalidComponentClassException, InvalidComponentFieldException,
		OutputFailureException, IOException, ParserConfigurationException, TransformerException,
		ClassNotFoundException, CannotCompileException, NotFoundException, SecurityException, NoSuchFieldException,
		IllegalArgumentException, IllegalAccessException, InvocationTargetException, NoSuchMethodException,
		InstantiationException {

		final List<Dialog> dialogList = new ArrayList<Dialog>();

		for (CtClass curClass : classList) {
			if (isWidgetInComponentClass(curClass)) {
				ComponentMojoUtil.getLog().debug("Processing Component Class " + curClass);
				Dialog builtDialog = DialogFactory.make(curClass, widgetRegistry, classLoader, classPool);
				if (builtDialog != null && isWidgetInComponentClass(curClass)) {
					dialogList.add(builtDialog);
					File dialogFile =
						writeDialogToFile(transformer, builtDialog, curClass, buildDirectory, componentPathBase,
							defaultComponentPathSuffix);
					writeDialogToArchiveFile(transformer, dialogFile, curClass, zipOutputStream, reservedNames,
						componentPathBase, defaultComponentPathSuffix);
					dialogList.add(builtDialog);
				}
			}
		}

		return dialogList;

	}

	private static CtMember getMemberForAnnotatedInterfaceMethod(CtMethod member)
		throws InvalidComponentClassException, ClassNotFoundException, NotFoundException {
		CtMethod newMember = null;
		List<CtClass> interfaces = new ArrayList<CtClass>();
		CtClass clazz = member.getDeclaringClass();
		while (clazz != null) {
			interfaces.addAll(Arrays.asList(clazz.getInterfaces()));
			clazz = clazz.getSuperclass();
		}
		for (CtClass ctclass : interfaces) {
			try {
				CtMethod newMethodMember = ctclass.getDeclaredMethod(member.getName(), member.getParameterTypes());
				DialogField tempDialogProperty = (DialogField) newMethodMember.getAnnotation(DialogField.class);
				if (tempDialogProperty != null) {
					if (newMember == null) {
						newMember = newMethodMember;
					} else {
						throw new InvalidComponentClassException(
							"Class has multiple interfaces that have the same method signature annotated");
					}
				}
			} catch (NotFoundException e) {
			}
		}
		if (newMember != null) {
			member = newMember;
		}
		return newMember;
	}

	public static DialogFieldConfig getDialogFieldFromSuperClasses(CtMethod method) throws NotFoundException,
		ClassNotFoundException, InvalidComponentClassException {
		DialogFieldConfig dialogFieldConfig = null;
		List<CtClass> classes = new ArrayList<CtClass>();
		CtClass clazz = method.getDeclaringClass();
		classes.add(clazz);
		while (clazz.getSuperclass() != null) {
			classes.add(clazz.getSuperclass());
			clazz = clazz.getSuperclass();
		}
		Collections.reverse(classes);
		CtMember interfaceMember = getMemberForAnnotatedInterfaceMethod(method);
		if (interfaceMember != null) {
			dialogFieldConfig =
				new DialogFieldConfig((DialogField) interfaceMember.getAnnotation(DialogField.class), interfaceMember);
		}
		for (CtClass ctclass : classes) {
			try {
				CtMethod superClassMethod = ctclass.getDeclaredMethod(method.getName(), method.getParameterTypes());
				if (superClassMethod.hasAnnotation(DialogField.class)) {
					dialogFieldConfig =
						new DialogFieldConfig((DialogField) superClassMethod.getAnnotation(DialogField.class),
							superClassMethod);
				} else if (superClassMethod.hasAnnotation(DialogFieldOverride.class)) {
					mergeDialogFields(dialogFieldConfig, superClassMethod);
					//TODO: Evaluate if we want to allow overriding of an already hidden field
					if (dialogFieldConfig != null) {
						dialogFieldConfig.setHideDialogField(false);
					}
				} else if (superClassMethod.hasAnnotation(HideDialogField.class) && dialogFieldConfig != null) {
					dialogFieldConfig.setHideDialogField(true);
				}
			} catch (NotFoundException e) {
			}
		}
		return dialogFieldConfig;
	}

	private static void mergeDialogFields(DialogFieldConfig dialogFieldConfig, CtMethod method)
		throws ClassNotFoundException {
		if (dialogFieldConfig != null && method.hasAnnotation(DialogFieldOverride.class)) {
			DialogFieldOverride dialogField = (DialogFieldOverride) method.getAnnotation(DialogFieldOverride.class);
			if (StringUtils.isNotEmpty(dialogField.fieldLabel())) {
				dialogFieldConfig.setFieldLabel(dialogField.fieldLabel());
			}

			if (StringUtils.isNotEmpty(dialogField.fieldDescription())) {
				dialogFieldConfig.setFieldDescription(dialogField.fieldDescription());
			}

			dialogFieldConfig.setRequired(dialogField.required());

			dialogFieldConfig.setHideLabel(dialogField.hideLabel());

			if (StringUtils.isNotEmpty(dialogField.defaultValue())) {
				dialogFieldConfig.setDefaultValue(dialogField.defaultValue());
			}

			if (StringUtils.isNotEmpty(dialogField.name())) {
				dialogFieldConfig.setName(dialogField.name());
			}

			dialogFieldConfig.setTab(dialogField.tab());

			dialogFieldConfig.setRanking(dialogField.ranking());

			if (dialogField.additionalProperties().length > 0) {
				List<Property> properties = new ArrayList<Property>();
				properties.addAll(Arrays.asList(dialogField.additionalProperties()));
				if (dialogField.mergeAdditionalProperties()) {
					properties.addAll(Arrays.asList(dialogFieldConfig.getAdditionalProperties()));
				}
				dialogFieldConfig.setAdditionalProperties(properties.toArray(new Property[properties.size()]));
			}

			if (dialogField.listeners().length > 0) {
				List<Listener> listeners = new ArrayList<Listener>();
				listeners.addAll(Arrays.asList(dialogField.listeners()));
				if (dialogField.mergeAdditionalProperties()) {
					listeners.addAll(Arrays.asList(dialogFieldConfig.getListeners()));
				}
				dialogFieldConfig.setListeners(listeners.toArray(new Listener[listeners.size()]));
			}

			if (StringUtils.isNotBlank(dialogField.title())) {
				dialogFieldConfig.setTitle(dialogField.title());
			}

			if (StringUtils.isNotBlank(dialogField.value())) {
				dialogFieldConfig.setValue(dialogField.value());
			}

			dialogFieldConfig.setDisabled(dialogField.disabled());

			if (StringUtils.isNotBlank(dialogField.cssClass())) {
				dialogFieldConfig.setCssClass(dialogField.cssClass());
			}

			dialogFieldConfig.setSuppressTouchUI(dialogField.suppressTouchUI());
		}

	}

	private static boolean isWidgetInComponentClass(CtClass componentClass) throws NotFoundException,
		ClassNotFoundException, InvalidComponentClassException {

		List<CtMember> fieldsAndMethods = new ArrayList<CtMember>();
		fieldsAndMethods.addAll(ComponentMojoUtil.collectFields(componentClass));
		fieldsAndMethods.addAll(ComponentMojoUtil.collectMethods(componentClass));

		/*
		 * Iterate through all fields establishing proper widgets for each
		 */
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

				if (dialogFieldConfig != null) {
					return true;

				}
			}
		}
		return false;
	}
}
