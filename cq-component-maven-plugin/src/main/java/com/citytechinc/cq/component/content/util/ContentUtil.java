package com.citytechinc.cq.component.content.util;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import javassist.CtClass;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.TransformerException;

import org.apache.commons.compress.archivers.zip.ZipArchiveEntry;
import org.apache.commons.compress.archivers.zip.ZipArchiveOutputStream;
import org.apache.commons.io.IOUtils;

import com.citytechinc.cq.component.annotations.Component;
import com.citytechinc.cq.component.content.Content;
import com.citytechinc.cq.component.content.factory.ContentFactory;
import com.citytechinc.cq.component.content.xml.ContentXmlWriter;
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
	 */
	public static File writeContentToFile(ComponentNameTransformer transformer, Content content,
		CtClass componentClass, File buildDirectory, String componentPathBase, String defaultComponentPathSuffix)
		throws TransformerException, ParserConfigurationException, IOException, OutputFailureException,
		ClassNotFoundException {
		File componentOutputDirectory = ComponentMojoUtil.getOutputDirectoryForComponentClass(transformer,
			componentClass, buildDirectory, componentPathBase, defaultComponentPathSuffix);

		File contentFile = new File(componentOutputDirectory, ".content.xml");

		if (contentFile.exists()) {
			contentFile.delete();
		}

		contentFile.createNewFile();

		ContentXmlWriter.writeContent(content, new FileOutputStream(contentFile));

		return contentFile;
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
		String contentFilePath = componentPathBase + "/"
			+ ComponentMojoUtil.getComponentPathSuffixForComponentClass(componentClass, defaultComponentPathSuffix)
			+ "/" + ComponentMojoUtil.getComponentNameForComponentClass(transformer, componentClass) + "/.content.xml";

		ComponentMojoUtil.getLog().debug("Archiving content file " + contentFilePath);

		if (!reservedNames.contains(contentFilePath.toLowerCase())) {

			ZipArchiveEntry entry = new ZipArchiveEntry(contentFile, contentFilePath);

			archiveStream.putArchiveEntry(entry);

			IOUtils.copy(new FileInputStream(contentFile), archiveStream);

			archiveStream.closeArchiveEntry();

		} else {
			ComponentMojoUtil.getLog().debug("Existing file found at " + contentFilePath);
		}
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
	 */
	public static List<Content> buildContentFromClassList(List<CtClass> classList,
		ZipArchiveOutputStream zipOutputStream, Set<String> reservedNames, File buildDirectory,
		String componentPathBase, String defaultComponentPathSuffix, String defaultComponentGroup,
		ComponentNameTransformer transformer) throws InvalidComponentClassException, TransformerException,
		ParserConfigurationException, IOException, OutputFailureException, ClassNotFoundException {

		List<Content> builtContents = new ArrayList<Content>();

		for (CtClass curClass : classList) {
			ComponentMojoUtil.getLog().debug("Checking class for Component annotation " + curClass);

			Component annotation = (Component) curClass.getAnnotation(Component.class);

			ComponentMojoUtil.getLog().debug("Annotation : " + annotation);

			if (annotation != null) {
				ComponentMojoUtil.getLog().debug("Processing Component Class " + curClass);

				Content builtContent = ContentFactory.make(curClass, defaultComponentGroup);

				builtContents.add(builtContent);

				File contentFile = writeContentToFile(transformer, builtContent, curClass, buildDirectory,
					componentPathBase, defaultComponentPathSuffix);
				writeContentToArchiveFile(transformer, contentFile, curClass, zipOutputStream, reservedNames,
					componentPathBase, defaultComponentPathSuffix);
			}
		}

		return builtContents;

	}

}
