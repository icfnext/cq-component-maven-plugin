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
package com.citytechinc.cq.component.editconfig.util;

import java.io.File;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import javassist.CtClass;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.TransformerException;

import org.apache.commons.compress.archivers.zip.ZipArchiveOutputStream;

import com.citytechinc.cq.component.annotations.Component;
import com.citytechinc.cq.component.dialog.ComponentNameTransformer;
import com.citytechinc.cq.component.dialog.exception.InvalidComponentClassException;
import com.citytechinc.cq.component.dialog.exception.OutputFailureException;
import com.citytechinc.cq.component.editconfig.EditConfig;
import com.citytechinc.cq.component.editconfig.factory.EditConfigFactory;
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
	 */
	public static List<EditConfig> buildEditConfigFromClassList(List<CtClass> classList,
		ZipArchiveOutputStream zipOutputStream, Set<String> reservedNames, File buildDirectory,
		String componentPathBase, String defaultComponentPathSuffix, ComponentNameTransformer transformer)
		throws InvalidComponentClassException, TransformerException, ParserConfigurationException, IOException,
		OutputFailureException, ClassNotFoundException, IllegalArgumentException, SecurityException,
		IllegalAccessException, InvocationTargetException, NoSuchMethodException {

		List<EditConfig> builtEditConfigs = new ArrayList<EditConfig>();

		for (CtClass curClass : classList) {
			Component annotation = (Component) curClass.getAnnotation(Component.class);

			if (annotation != null && annotation.editConfig()) {
				EditConfig builtEditConfig = EditConfigFactory.make(curClass);

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

}
