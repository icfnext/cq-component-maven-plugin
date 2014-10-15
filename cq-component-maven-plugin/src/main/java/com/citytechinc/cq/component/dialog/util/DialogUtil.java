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
package com.citytechinc.cq.component.dialog.util;

import java.io.File;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import javassist.CannotCompileException;
import javassist.ClassPool;
import javassist.CtClass;
import javassist.CtField;
import javassist.CtMember;
import javassist.CtMethod;
import javassist.NotFoundException;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.TransformerException;

import org.apache.commons.compress.archivers.zip.ZipArchiveOutputStream;
import org.codehaus.plexus.util.StringUtils;

import com.citytechinc.cq.component.annotations.Component;
import com.citytechinc.cq.component.annotations.DialogField;
import com.citytechinc.cq.component.annotations.IgnoreDialogField;
import com.citytechinc.cq.component.annotations.Tab;
import com.citytechinc.cq.component.dialog.ComponentNameTransformer;
import com.citytechinc.cq.component.dialog.Dialog;
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
	 * @param xtypeMap
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
			ComponentMojoUtil.getLog().debug("Checking class for Component annotation " + curClass);

			boolean hasDialogFieldOrCQIncludeTab = false;
			for (CtField curField : ComponentMojoUtil.collectFields(curClass)) {
				if (curField.hasAnnotation(DialogField.class)) {
					hasDialogFieldOrCQIncludeTab = true;
					break;
				}
			}
			if (!hasDialogFieldOrCQIncludeTab) {
				for (CtMethod curMethod : ComponentMojoUtil.collectMethods(curClass)) {
					if (curMethod.hasAnnotation(DialogField.class)) {
						hasDialogFieldOrCQIncludeTab = true;
						break;
					}
				}
			}
			if (!hasDialogFieldOrCQIncludeTab) {
				Component componentAnnotation = (Component) curClass.getAnnotation(Component.class);
				for (Tab tab : componentAnnotation.tabs()) {
					if (StringUtils.isNotEmpty(tab.path())) {
						hasDialogFieldOrCQIncludeTab = true;
						break;
					}
				}
			}
			if (hasDialogFieldOrCQIncludeTab) {
				ComponentMojoUtil.getLog().debug("Processing Component Class " + curClass);
				Dialog builtDialog = DialogFactory.make(curClass, widgetRegistry, classLoader, classPool);
				dialogList.add(builtDialog);
				File dialogFile = writeDialogToFile(transformer, builtDialog, curClass, buildDirectory,
					componentPathBase, defaultComponentPathSuffix);
				writeDialogToArchiveFile(transformer, dialogFile, curClass, zipOutputStream, reservedNames,
					componentPathBase, defaultComponentPathSuffix);
			}
		}

		return dialogList;

	}

	public static CtMember getMemberForAnnotatedInterfaceMethod(CtMember member) throws InvalidComponentClassException,
		ClassNotFoundException, NotFoundException {
		CtMember newMember = null;
		if (member instanceof CtMethod && !member.hasAnnotation(IgnoreDialogField.class)
			&& member.getDeclaringClass().getInterfaces().length > 0) {
			CtMethod methodMember = (CtMethod) member;
			for (CtClass ctclass : methodMember.getDeclaringClass().getInterfaces()) {
				try {
					CtMethod newMethodMember = ctclass.getDeclaredMethod(methodMember.getName(),
						methodMember.getParameterTypes());
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
		}
		return newMember;
	}

}
