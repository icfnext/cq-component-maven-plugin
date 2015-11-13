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
package com.citytechinc.cq.component.content.util;

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
import com.citytechinc.cq.component.content.Content;
import com.citytechinc.cq.component.content.factory.ContentFactory;
import com.citytechinc.cq.component.dialog.ComponentNameTransformer;
import com.citytechinc.cq.component.dialog.exception.InvalidComponentClassException;
import com.citytechinc.cq.component.dialog.exception.OutputFailureException;
import com.citytechinc.cq.component.maven.util.ComponentMojoUtil;

public class ContentUtil {
	private ContentUtil() {
	};

	/**
	 * Write the content.xml to an output file, the path of which is determined
	 * by the component class
	 * 
	 * @param content
	 * @param componentClass
	 * @return The written file
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
	public static File writeContentToFile(ComponentNameTransformer transformer, Content content,
		CtClass componentClass, File buildDirectory, String componentPathBase, String defaultComponentPathSuffix)
		throws TransformerException, ParserConfigurationException, IOException, OutputFailureException,
		ClassNotFoundException, IllegalArgumentException, SecurityException, IllegalAccessException,
		InvocationTargetException, NoSuchMethodException {

		return ComponentMojoUtil.writeElementToFile(transformer, content, componentClass, buildDirectory,
			componentPathBase, defaultComponentPathSuffix, ".content.xml");
	}

	/**
	 * Writes a provided content file to a provided archive output stream at a
	 * path determined by the class of the component.
	 * 
	 * @param contentFile
	 * @param componentClass
	 * @param archiveStream
	 * @param reservedNames A list of files which already exist within the Zip
	 *            Archive. If a .content.xml file already exists for a
	 *            particular component, it is left untouched.
	 * @param componentPathBase
	 * @throws IOException
	 * @throws ClassNotFoundException
	 */
	public static void writeContentToArchiveFile(ComponentNameTransformer transformer, File contentFile,
		CtClass componentClass, ZipArchiveOutputStream archiveStream, Set<String> reservedNames,
		String componentPathBase, String defaultComponentPathSuffix) throws IOException, ClassNotFoundException {

		ComponentMojoUtil.writeElementToArchiveFile(transformer, contentFile, componentClass, archiveStream,
			reservedNames, componentPathBase, defaultComponentPathSuffix, "/.content.xml");
	}

	/**
	 * Constructs a list of Content objects representing .content.xml files from
	 * a list of Classes. For each Class annotated with a Component annotation a
	 * Content object is constructed.
	 * 
	 * @param classList
	 * @param zipOutputStream
	 * @param reservedNames
	 * @return The constructed Content objects
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
	public static List<Content> buildContentFromClassList(List<CtClass> classList,
		ZipArchiveOutputStream zipOutputStream, Set<String> reservedNames, File buildDirectory,
		String componentPathBase, String defaultComponentPathSuffix, String defaultComponentGroup,
		ComponentNameTransformer transformer) throws InvalidComponentClassException, TransformerException,
		ParserConfigurationException, IOException, OutputFailureException, ClassNotFoundException,
		IllegalArgumentException, SecurityException, IllegalAccessException, InvocationTargetException,
		NoSuchMethodException {

		List<Content> builtContents = new ArrayList<Content>();

		for (CtClass curClass : classList) {
			ComponentMojoUtil.getLog().debug("Checking class for Component annotation " + curClass);

			Component annotation = (Component) curClass.getAnnotation(Component.class);

			ComponentMojoUtil.getLog().debug("Annotation : " + annotation);

			if (annotation != null) {
				ComponentMojoUtil.getLog().debug("Processing Component Class " + curClass);

				Content builtContent = ContentFactory.make(curClass, defaultComponentGroup);

				builtContents.add(builtContent);

				File contentFile =
					writeContentToFile(transformer, builtContent, curClass, buildDirectory, componentPathBase,
						defaultComponentPathSuffix);
				writeContentToArchiveFile(transformer, contentFile, curClass, zipOutputStream, reservedNames,
					componentPathBase, defaultComponentPathSuffix);
			}
		}

		return builtContents;

	}

}
