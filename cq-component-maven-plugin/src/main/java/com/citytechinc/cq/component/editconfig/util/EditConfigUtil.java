package com.citytechinc.cq.component.editconfig.util;

import java.io.File;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Set;

import javassist.ClassPool;
import javassist.CtClass;
import javassist.CtMethod;
import javassist.NotFoundException;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.TransformerException;

import org.apache.commons.compress.archivers.zip.ZipArchiveOutputStream;

import com.citytechinc.cq.component.annotations.Component;
import com.citytechinc.cq.component.annotations.editconfig.InPlaceEditorConfig;
import com.citytechinc.cq.component.dialog.ComponentNameTransformer;
import com.citytechinc.cq.component.dialog.exception.InvalidComponentClassException;
import com.citytechinc.cq.component.dialog.exception.OutputFailureException;
import com.citytechinc.cq.component.editconfig.EditConfig;
import com.citytechinc.cq.component.editconfig.factory.EditConfigFactory;
import com.citytechinc.cq.component.editconfig.registry.InPlaceEditorRegistry;
import com.citytechinc.cq.component.maven.util.ComponentMojoUtil;

public class EditConfigUtil {
	private EditConfigUtil() {
	}

	/**
	 * Writes a provided file to a provided archive output stream at a path
	 * determined by the class of the component.
	 * 
	 * @param editConfigFile
	 * @param componentClass
	 * @param archiveStream
	 * @param reservedNames A list of files which already exist within the Zip
	 *            Archive. If an edit config file already exists for a
	 *            particular component, it is left untouched.
	 * @param componentPathBase
	 * @throws IOException
	 * @throws ClassNotFoundException
	 */
	public static void writeEditConfigToArchiveFile(ComponentNameTransformer transformer, File editConfigFile,
		CtClass componentClass, ZipArchiveOutputStream archiveStream, Set<String> reservedNames,
		String componentPathBase, String defaultComponentPathSuffix) throws IOException, ClassNotFoundException {

		ComponentMojoUtil.writeElementToArchiveFile(transformer, editConfigFile, componentClass, archiveStream,
			reservedNames, componentPathBase, defaultComponentPathSuffix, "/_cq_editConfig.xml");

	}

	/**
	 * Determines the name of the edit config file to be written and writes the
	 * the edit config xml which the provided EditConfig object represents to
	 * that determined file.
	 * 
	 * @param editConfig
	 * @param componentClass
	 * @return The file written
	 * @throws TransformerException
	 * @throws ParserConfigurationException
	 * @throws IOException
	 * @throws OutputFailureException
	 * @throws ClassNotFoundException
	 * @throws NoSuchMethodException
	 * @throws InvocationTargetException
	 * @throws IllegalAccessException
	 * @throws SecurityException
	 * @throws IllegalArgumentException
	 */
	public static File writeEditConfigToFile(ComponentNameTransformer transformer, EditConfig editConfig,
		CtClass componentClass, File buildDirectory, String componentPathBase, String defaultComponentPathSuffix)
		throws TransformerException, ParserConfigurationException, IOException, OutputFailureException,
		ClassNotFoundException, IllegalArgumentException, SecurityException, IllegalAccessException,
		InvocationTargetException, NoSuchMethodException {

		return ComponentMojoUtil.writeElementToFile(transformer, editConfig, componentClass, buildDirectory,
			componentPathBase, defaultComponentPathSuffix, "_cq_editConfig.xml");
	}

	/**
	 * For each class in the provided classList which is annotated with a
	 * Component annotation, an EditConfig object is built and added to the
	 * returned list. Classes which are not thusly annotated are ignored.
	 * 
	 * @param classList
	 * @param zipOutputStream
	 * @param reservedNames
	 * @return The constructed list of EditConfig objects
	 * @throws InvalidComponentClassException
	 * @throws TransformerException
	 * @throws ParserConfigurationException
	 * @throws IOException
	 * @throws OutputFailureException
	 * @throws ClassNotFoundException
	 * @throws NoSuchMethodException
	 * @throws InvocationTargetException
	 * @throws IllegalAccessException
	 * @throws SecurityException
	 * @throws IllegalArgumentException
	 * @throws NotFoundException
	 * @throws InstantiationException
	 */
	public static List<EditConfig> buildEditConfigFromClassList(List<CtClass> classList,
		ZipArchiveOutputStream zipOutputStream, Set<String> reservedNames, InPlaceEditorRegistry inPlaceEditorRegistry,
		ClassLoader classLoader, ClassPool classPool, File buildDirectory, String componentPathBase,
		String defaultComponentPathSuffix, ComponentNameTransformer transformer) throws InvalidComponentClassException,
		TransformerException, ParserConfigurationException, IOException, OutputFailureException,
		ClassNotFoundException, IllegalArgumentException, SecurityException, IllegalAccessException,
		InvocationTargetException, NoSuchMethodException, NotFoundException, InstantiationException {

		List<EditConfig> builtEditConfigs = new ArrayList<EditConfig>();

		for (CtClass curClass : classList) {
			Component annotation = (Component) curClass.getAnnotation(Component.class);

			if (annotation != null && annotation.editConfig()) {
				EditConfig builtEditConfig =
					EditConfigFactory.make(curClass, inPlaceEditorRegistry, classLoader, classPool);

				builtEditConfigs.add(builtEditConfig);

				File editConfigFile =
					writeEditConfigToFile(transformer, builtEditConfig, curClass, buildDirectory, componentPathBase,
						defaultComponentPathSuffix);
				writeEditConfigToArchiveFile(transformer, editConfigFile, curClass, zipOutputStream, reservedNames,
					componentPathBase, defaultComponentPathSuffix);
			}
		}

		return builtEditConfigs;
	};

	public static InPlaceEditorConfig getInPlaceEditorFromSuperClasses(CtMethod method,
		Set<Class<?>> registeredAnnotations) throws NotFoundException, ClassNotFoundException,
		InvalidComponentClassException {
		InPlaceEditorConfig inPlaceEditorConfig = null;
		List<CtClass> classes = new ArrayList<CtClass>();
		CtClass clazz = method.getDeclaringClass();
		classes.add(clazz);
		while (clazz.getSuperclass() != null) {
			classes.add(clazz.getSuperclass());
			clazz = clazz.getSuperclass();
		}
		Collections.reverse(classes);
		inPlaceEditorConfig = getMemberForAnnotatedInterfaceMethod(method, registeredAnnotations);
		if (inPlaceEditorConfig == null) {
			for (CtClass ctclass : classes) {
				try {
					CtMethod superClassMethod = ctclass.getDeclaredMethod(method.getName(), method.getParameterTypes());
					for (Class<?> annotationClass : registeredAnnotations) {
						if (superClassMethod.hasAnnotation(annotationClass)) {
							inPlaceEditorConfig =
								new InPlaceEditorConfig(superClassMethod.getAnnotation(annotationClass),
									superClassMethod, annotationClass);
						}
					}
				} catch (NotFoundException e) {
				}
			}
		}
		return inPlaceEditorConfig;
	}

	private static InPlaceEditorConfig getMemberForAnnotatedInterfaceMethod(CtMethod member,
		Set<Class<?>> registeredAnnotations) throws InvalidComponentClassException, ClassNotFoundException,
		NotFoundException {
		InPlaceEditorConfig inPlaceEditorConfig = null;
		List<CtClass> interfaces = new ArrayList<CtClass>();
		CtClass clazz = member.getDeclaringClass();
		while (clazz != null) {
			interfaces.addAll(Arrays.asList(clazz.getInterfaces()));
			clazz = clazz.getSuperclass();
		}
		for (CtClass ctclass : interfaces) {
			try {
				CtMethod newMethodMember = ctclass.getDeclaredMethod(member.getName(), member.getParameterTypes());
				for (Class<?> annotationClass : registeredAnnotations) {
					Object ipeAnnotation = newMethodMember.getAnnotation(annotationClass);
					if (ipeAnnotation != null) {
						if (inPlaceEditorConfig == null) {
							inPlaceEditorConfig =
								new InPlaceEditorConfig(ipeAnnotation, newMethodMember, annotationClass);
						} else {
							throw new InvalidComponentClassException(
								"Class has multiple interfaces that have the same method signature annotated");
						}
					}
				}
			} catch (NotFoundException e) {
			}
		}
		return inPlaceEditorConfig;
	}

}
