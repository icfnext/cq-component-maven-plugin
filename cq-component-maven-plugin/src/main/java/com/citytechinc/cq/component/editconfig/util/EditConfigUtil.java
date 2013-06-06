package com.citytechinc.cq.component.editconfig.util;

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
import com.citytechinc.cq.component.dialog.ComponentNameTransformer;
import com.citytechinc.cq.component.dialog.exception.InvalidComponentClassException;
import com.citytechinc.cq.component.dialog.exception.OutputFailureException;
import com.citytechinc.cq.component.editconfig.EditConfig;
import com.citytechinc.cq.component.editconfig.factory.EditConfigFactory;
import com.citytechinc.cq.component.editconfig.xml.EditConfigXmlWriter;
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
		String editConfigFilePath = ComponentMojoUtil.getComponentBasePathForComponentClass(componentClass,
			componentPathBase)
			+ "/"
			+ ComponentMojoUtil.getComponentPathSuffixForComponentClass(componentClass, defaultComponentPathSuffix)
			+ "/"
			+ ComponentMojoUtil.getComponentNameForComponentClass(transformer, componentClass)
			+ "/_cq_editConfig.xml";

		ComponentMojoUtil.getLog().debug("Archiving edit config file " + editConfigFilePath);

		if (!reservedNames.contains(editConfigFilePath.toLowerCase())) {

			ZipArchiveEntry entry = new ZipArchiveEntry(editConfigFile, editConfigFilePath);

			archiveStream.putArchiveEntry(entry);

			IOUtils.copy(new FileInputStream(editConfigFile), archiveStream);

			archiveStream.closeArchiveEntry();

		} else {
			ComponentMojoUtil.getLog().debug("Existing file found at " + editConfigFilePath);
		}
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
	 */
	public static File writeEditConfigToFile(ComponentNameTransformer transformer, EditConfig editConfig,
		CtClass componentClass, File buildDirectory, String componentPathBase, String defaultComponentPathSuffix)
		throws TransformerException, ParserConfigurationException, IOException, OutputFailureException,
		ClassNotFoundException {
		File componentOutputDirectory = ComponentMojoUtil.getOutputDirectoryForComponentClass(transformer,
			componentClass, buildDirectory, componentPathBase, defaultComponentPathSuffix);

		File editConfigFile = new File(componentOutputDirectory, "_cq_editConfig.xml");

		if (editConfigFile.exists()) {
			editConfigFile.delete();
		}

		editConfigFile.createNewFile();

		EditConfigXmlWriter.writeEditConfig(editConfig, new FileOutputStream(editConfigFile));

		return editConfigFile;
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
	 */
	public static List<EditConfig> buildEditConfigFromClassList(List<CtClass> classList,
		ZipArchiveOutputStream zipOutputStream, Set<String> reservedNames, File buildDirectory,
		String componentPathBase, String defaultComponentPathSuffix, ComponentNameTransformer transformer)
		throws InvalidComponentClassException, TransformerException, ParserConfigurationException, IOException,
		OutputFailureException, ClassNotFoundException {

		List<EditConfig> builtEditConfigs = new ArrayList<EditConfig>();

		for (CtClass curClass : classList) {
			Component annotation = (Component) curClass.getAnnotation(Component.class);

			if (annotation != null && annotation.editConfig()) {
				EditConfig builtEditConfig = EditConfigFactory.make(curClass);

				builtEditConfigs.add(builtEditConfig);

				File editConfigFile = writeEditConfigToFile(transformer, builtEditConfig, curClass, buildDirectory,
					componentPathBase, defaultComponentPathSuffix);
				writeEditConfigToArchiveFile(transformer, editConfigFile, curClass, zipOutputStream, reservedNames,
					componentPathBase, defaultComponentPathSuffix);
			}
		}

		return builtEditConfigs;
	};

}
