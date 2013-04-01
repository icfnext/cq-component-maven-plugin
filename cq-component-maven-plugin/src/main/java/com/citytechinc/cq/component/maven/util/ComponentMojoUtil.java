package com.citytechinc.cq.component.maven.util;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLClassLoader;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javassist.CannotCompileException;
import javassist.ClassPool;
import javassist.CtClass;
import javassist.CtField;
import javassist.LoaderClassPath;
import javassist.NotFoundException;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.TransformerException;

import org.apache.commons.compress.archivers.jar.JarArchiveEntry;
import org.apache.commons.compress.archivers.jar.JarArchiveInputStream;
import org.apache.commons.compress.archivers.zip.ZipArchiveEntry;
import org.apache.commons.compress.archivers.zip.ZipArchiveInputStream;
import org.apache.commons.compress.archivers.zip.ZipArchiveOutputStream;
import org.apache.commons.io.FileUtils;
import org.apache.commons.io.IOUtils;
import org.apache.maven.artifact.Artifact;
import org.apache.maven.project.MavenProject;
import org.codehaus.plexus.util.StringUtils;
import org.reflections.Reflections;
import org.reflections.scanners.SubTypesScanner;
import org.reflections.scanners.TypeAnnotationsScanner;
import org.reflections.util.ClasspathHelper;
import org.reflections.util.ConfigurationBuilder;
import org.reflections.util.FilterBuilder;

import com.citytechinc.cq.component.annotations.Component;
import com.citytechinc.cq.component.annotations.DialogField;
import com.citytechinc.cq.component.annotations.config.Widget;
import com.citytechinc.cq.component.content.Content;
import com.citytechinc.cq.component.content.factory.ContentFactory;
import com.citytechinc.cq.component.content.xml.ContentXmlWriter;
import com.citytechinc.cq.component.dialog.AbstractWidget;
import com.citytechinc.cq.component.dialog.impl.Dialog;
import com.citytechinc.cq.component.dialog.maker.WidgetMaker;
import com.citytechinc.cq.component.dialog.exception.InvalidComponentClassException;
import com.citytechinc.cq.component.dialog.exception.InvalidComponentFieldException;
import com.citytechinc.cq.component.dialog.exception.OutputFailureException;
import com.citytechinc.cq.component.dialog.factory.DialogFactory;
import com.citytechinc.cq.component.dialog.xml.DialogXmlWriter;
import com.citytechinc.cq.component.editconfig.EditConfig;
import com.citytechinc.cq.component.editconfig.factory.EditConfigFactory;
import com.citytechinc.cq.component.editconfig.xml.EditConfigXmlWriter;
import com.citytechinc.cq.component.maven.Dependency;
import com.google.common.base.Predicate;

public class ComponentMojoUtil {

	private static final String OUTPUT_PATH = "tempComponentConfig";
	private static final String CITYTECH_PACKAGE="com.citytechinc.cq.component.dialog.impl";
	private static final LogSingleton getLog() {
		return LogSingleton.getInstance();
	}

	public static ClassLoader getClassLoader(List<String> paths, ClassLoader mojoClassLoader) throws MalformedURLException {
		final List<URL> pathURLs = new ArrayList<URL>();

		for (String curPath : paths) {

			URL newClassPathURL = new File(curPath).toURI().toURL();

			getLog().debug("Adding " + newClassPathURL.toString() + " to class loader");

			pathURLs.add(newClassPathURL);

		}

		return new URLClassLoader(pathURLs.toArray(new URL[0]), mojoClassLoader);
	}

	/**
	 * Constructs as Javassist ClassPool which pulls resources based on the paths provided
	 * by the passed in ClassLoader
	 *
	 * @param classLoader
	 * @return The constructed ClassPool
	 */
	public static ClassPool getClassPool(ClassLoader classLoader) {
		ClassPool classPool = new ClassPool();
		classPool.appendClassPath(new LoaderClassPath(classLoader));
		return classPool;
	}

	/**
	 * Given a set of configured xtype mappings, construct a mapping from Class objects to xtype Strings
	 *
	 * @param classLoader
	 * @param xtypeMappings
	 * @return The constructed mapping
	 *
	 * @throws ClassNotFoundException
	 */
	public static Map<Class<?>, String> getXTypeMapForCustomXTypeMapping(List<WidgetConfigHolder> widgetConfigs) throws ClassNotFoundException {
		Map<Class<?>, String> retMap = new HashMap<Class<?>, String>();

		for(WidgetConfigHolder widgetConfig:widgetConfigs){
			if(widgetConfig.getAnnotationClass()!=null){
				for(String xtype:widgetConfig.getXtypes()){
					retMap.put(widgetConfig.getAnnotationClass(), xtype);
				}
			}
		}

		return retMap;
	}

	public static Map<String, WidgetMaker> getXTypeToWidgetMakerMap(List<WidgetConfigHolder> widgetConfigs)
			throws InstantiationException, IllegalAccessException, ClassNotFoundException {
		Map<String, WidgetMaker> xTypeToWidgetMakerMap = new HashMap<String, WidgetMaker>();

		for(WidgetConfigHolder widgetConfig:widgetConfigs){
			for(String xtype:widgetConfig.getXtypes()){
				xTypeToWidgetMakerMap.put(xtype, widgetConfig.getMakerClass().newInstance());
			}
		}

		return xTypeToWidgetMakerMap;
	}

	/**
	 * Constructs a list of all Classes which are to be Scanned for component annotations.
	 *
	 * @param classPool
	 * @param classPaths
	 * @param includedDependencies
	 * @param projectArtifacts
	 * @return The constructed Class list
	 * @throws ClassNotFoundException
	 * @throws IOException
	 * @throws NotFoundException
	 */
	public static List<CtClass> getCompiledClasses(
			ClassPool classPool,
			List<String> classPaths,
			List<Dependency> includedDependencies,
			Set<Artifact> projectArtifacts)
					throws ClassNotFoundException, IOException, NotFoundException {

		final List<CtClass> classList = new ArrayList<CtClass>();

		String[] extensions = { "class" };

		for (String curClassPath : classPaths) {
			getLog().debug("Current Class Path : " + curClassPath);

			File curClassPathFile = new File(curClassPath);

			/*
			 * Handle loading of those classes compiled as part of this project
			 */
			if (curClassPathFile.isDirectory()) {

				Collection<File> classFiles = FileUtils.listFiles(curClassPathFile, extensions, true );

				for (File curClassFile : classFiles) {
					String curClassString = classNameFromFilePath(curClassFile.getPath(), curClassPath);

					getLog().debug("Loading class : " + curClassString);

					classList.add(classPool.getCtClass(curClassString));
				}

			}

		}

		/*
		 * Look through the project artifacts to find any matching the dependency definition
		 * given in the project parameters.  If one does, look through it for classes to add to our
		 * class list.
		 */
		if (includedDependencies != null && !includedDependencies.isEmpty()) {
			for (Artifact curArtifact : projectArtifacts) {

				if (includeJarClasses(curArtifact, includedDependencies)) {
					classList.addAll(getClassListForJarFile(curArtifact.getFile(), classPool));
				}

			}
		}

		return classList;
	}

	/**
	 * Determines whether a particular JAR should be included in the component scan based on the configured
	 * set of dependencies.
	 *
	 * @param jarArtifact
	 * @param dependencies
	 * @return True if the provided artifact should be included, False otherwise
	 */
	protected static Boolean includeJarClasses(Artifact jarArtifact, List<Dependency> dependencies) {
		for (Dependency curDependency : dependencies) {
			if (
					jarArtifact.getArtifactId().equals(curDependency.getArtifactId()) &&
					jarArtifact.getGroupId().equals(curDependency.getGroupId())) {

				getLog().debug("Including artifact " + jarArtifact.getArtifactId() + " : " + jarArtifact.getGroupId());

				return true;
			}
		}

		return false;
	}

	/**
	 * Constructs the list of classes present in a given JAR file
	 *
	 * @param jarFile
	 * @param classPool
	 * @return The list of classes present in the provided JAR file
	 * @throws IOException
	 * @throws ClassNotFoundException
	 * @throws NotFoundException
	 */
	protected static List<CtClass> getClassListForJarFile(File jarFile, ClassPool classPool)
			throws IOException, ClassNotFoundException, NotFoundException {
		List<CtClass> classList = new ArrayList<CtClass>();

		getLog().debug("Searching JAR " + jarFile.getName() + " for classes");

		JarArchiveInputStream jarInputStream = new JarArchiveInputStream(new FileInputStream(jarFile));

		JarArchiveEntry curJarEntry;

		while ((curJarEntry = jarInputStream.getNextJarEntry()) != null) {
			if (curJarEntry.getName().endsWith(".class")) {
				getLog().debug("Found class " + curJarEntry.getName());

				String qualifiedClassname = classNameFromFilePath(curJarEntry.getName(), null);

				classList.add(classPool.getCtClass(qualifiedClassname));
			}
		}

		return classList;
	}

	/**
	 * Constructs a fully qualified class name based on the path to the class file
	 *
	 * @param filePath
	 * @param rootPath
	 * @return The constructed class name
	 */
	protected static String classNameFromFilePath(String filePath, String rootPath) {
		String placeholder = filePath;

		if (StringUtils.isNotEmpty(rootPath) && placeholder.startsWith(rootPath)) {
			placeholder = placeholder.replace(rootPath, "");
		}

		if (placeholder.startsWith("/")) {
			placeholder = placeholder.substring(1);
		}

		if (placeholder.endsWith(".class")) {
			placeholder = placeholder.substring(0, placeholder.length() - ".class".length());
		}

		getLog().debug("Class pre replace " + placeholder);

		return placeholder.replace('/', '.');
	}

	/**
	 * Add files to the already constructed Archive file by creating a new Archive file, appending the contents
	 * of the existing Archive file to it, and then adding additional entries for the newly constructed artifacts.
	 *
	 * @param classList
	 * @param xtypeMap
	 * @param classLoader
	 * @param classPool
	 * @param buildDirectory
	 * @param componentPathBase
	 * @param defaultComponentPathSuffix
	 * @param defaultComponentGroup
	 * @param existingArchiveFile
	 * @param tempArchiveFile
	 * @throws OutputFailureException
	 * @throws IOException
	 * @throws InvalidComponentClassException
	 * @throws InvalidComponentFieldException
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
	 */
	public static void buildArchiveFileForProjectAndClassList(
			List<CtClass> classList,
			Map<Class<?>, String> xtypeMap,
			Map<String, WidgetMaker> widgetMakerMap,
			ClassLoader classLoader,
			ClassPool classPool,
			File buildDirectory,
			String componentPathBase,
			String defaultComponentPathSuffix,
			String defaultComponentGroup,
			File existingArchiveFile,
			File tempArchiveFile)
					throws OutputFailureException, IOException, InvalidComponentClassException, InvalidComponentFieldException, ParserConfigurationException, TransformerException, ClassNotFoundException, CannotCompileException, NotFoundException, SecurityException, NoSuchFieldException, IllegalArgumentException, IllegalAccessException, InvocationTargetException, NoSuchMethodException {

		if (!existingArchiveFile.exists()) {
			throw new  OutputFailureException("Archive file does not exist");
		}

		if (tempArchiveFile.exists()) {
			throw new OutputFailureException("Temporary file already exists");
		}

		tempArchiveFile.createNewFile();

		/*
		 * Create archive input stream
		 */
		ZipArchiveInputStream existingInputStream = new ZipArchiveInputStream(new FileInputStream(existingArchiveFile));

		/*
		 * Create a zip archive output stream for the temp file
		 */
		ZipArchiveOutputStream tempOutputStream = new ZipArchiveOutputStream(tempArchiveFile);

		/*
		 * Iterate through all existing entries adding them to the new archive
		 */
		ZipArchiveEntry curArchiveEntry;

		Set<String> existingArchiveEntryNames = new HashSet<String>();

		while ((curArchiveEntry = existingInputStream.getNextZipEntry()) != null) {
			existingArchiveEntryNames.add(curArchiveEntry.getName().toLowerCase());
			getLog().debug("Current File Name: " + curArchiveEntry.getName());
			tempOutputStream.putArchiveEntry(curArchiveEntry);
			IOUtils.copy(existingInputStream, tempOutputStream);
			tempOutputStream.closeArchiveEntry();
		}

		/*
		 * Create content.xml within temp archive
		 */
		buildContentFromClassList(classList, tempOutputStream, existingArchiveEntryNames, buildDirectory, componentPathBase, defaultComponentPathSuffix, defaultComponentGroup);

		/*
		 * Create Dialogs within temp archive
		 */
		buildDialogsFromClassList(classList, tempOutputStream, existingArchiveEntryNames, xtypeMap, widgetMakerMap, classLoader, classPool, buildDirectory, componentPathBase, defaultComponentPathSuffix);

		/*
		 * Create edit config within temp archive
		 */
		buildEditConfigFromClassList(classList, tempOutputStream, existingArchiveEntryNames, buildDirectory, componentPathBase, defaultComponentPathSuffix);

		/*
		 * Copy temp archive to the original archive position
		 */
		tempOutputStream.finish();
		existingInputStream.close();
		tempOutputStream.close();

		existingArchiveFile.delete();
		tempArchiveFile.renameTo(existingArchiveFile);

	}

	/**
	 * For each class in the provided classList which is annotated with a Component annotation, an EditConfig object
	 * is built and added to the returned list. Classes which are not thusly annotated are ignored.
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
	protected static List<EditConfig> buildEditConfigFromClassList(List<CtClass> classList, ZipArchiveOutputStream zipOutputStream, Set<String> reservedNames, File buildDirectory, String componentPathBase, String defaultComponentPathSuffix) throws InvalidComponentClassException, TransformerException, ParserConfigurationException, IOException, OutputFailureException, ClassNotFoundException {

		List<EditConfig> builtEditConfigs = new ArrayList<EditConfig>();

		for (CtClass curClass : classList) {
			Component annotation = (Component) curClass.getAnnotation(Component.class);

			if (annotation != null && annotation.editConfig()) {
				EditConfig builtEditConfig = EditConfigFactory.make(curClass);

				builtEditConfigs.add(builtEditConfig);

				File editConfigFile = writeEditConfigToFile(builtEditConfig, curClass, buildDirectory, componentPathBase, defaultComponentPathSuffix);
				writeEditConfigToArchiveFile(editConfigFile, curClass, zipOutputStream, reservedNames, componentPathBase, defaultComponentPathSuffix);
			}
		}

		return builtEditConfigs;
	}

	/**
	 * Determines the name of the edit config file to be written and writes the the edit config xml which the provided
	 * EditConfig object represents to that determined file.
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
	protected static File writeEditConfigToFile(EditConfig editConfig, CtClass componentClass, File buildDirectory, String componentPathBase, String defaultComponentPathSuffix)
			throws TransformerException, ParserConfigurationException, IOException, OutputFailureException, ClassNotFoundException {
		File componentOutputDirectory = getOutputDirectoryForComponentClass(componentClass, buildDirectory, componentPathBase, defaultComponentPathSuffix);

		File editConfigFile = new File(componentOutputDirectory, "_cq_editConfig.xml");

		if (editConfigFile.exists()) {
			editConfigFile.delete();
		}

		editConfigFile.createNewFile();

		EditConfigXmlWriter.writeEditConfig(editConfig, new FileOutputStream(editConfigFile));

		return editConfigFile;
	}


	/**
	 * Writes a provided file to a provided archive output stream at a path determined by the class of the component.
	 *
	 * @param editConfigFile
	 * @param componentClass
	 * @param archiveStream
	 * @param reservedNames A list of files which already exist within the Zip Archive.  If an edit config file already
	 *                      exists for a particular component, it is left untouched.
	 * @param componentPathBase
	 * @throws IOException
	 * @throws ClassNotFoundException
	 */
	protected static void writeEditConfigToArchiveFile(File editConfigFile, CtClass componentClass, ZipArchiveOutputStream archiveStream, Set<String> reservedNames, String componentPathBase, String defaultComponentPathSuffix) throws IOException, ClassNotFoundException {
		String editConfigFilePath = componentPathBase +
				"/" +
				getComponentPathSuffixForComponentClass(componentClass, defaultComponentPathSuffix) +
				"/" +
				getComponentNameForComponentClass(componentClass) +
				"/_cq_editConfig.xml";

		getLog().debug("Archiving edit config file " + editConfigFilePath);

		if (!reservedNames.contains(editConfigFilePath.toLowerCase())) {

			ZipArchiveEntry entry = new ZipArchiveEntry(editConfigFile, editConfigFilePath);

			archiveStream.putArchiveEntry(entry);

			IOUtils.copy(new FileInputStream(editConfigFile), archiveStream);

			archiveStream.closeArchiveEntry();

		}
		else {
			getLog().debug("Existing file found at " + editConfigFilePath);
		}
	}

	/**
	 * Constructs a list of Content objects representing .content.xml files from a list of Classes.  For each Class
	 * annotated with a Component annotation a Content object is constructed.
	 * @param classList
	 * @param zipOutputStream
	 * @param reservedNames
	 * @return
	 * @throws InvalidComponentClassException
	 * @throws TransformerException
	 * @throws ParserConfigurationException
	 * @throws IOException
	 * @throws OutputFailureException
	 * @throws ClassNotFoundException
	 */
	protected static List<Content> buildContentFromClassList(List<CtClass> classList, ZipArchiveOutputStream zipOutputStream, Set<String> reservedNames, File buildDirectory, String componentPathBase, String defaultComponentPathSuffix, String defaultComponentGroup) throws InvalidComponentClassException, TransformerException, ParserConfigurationException, IOException, OutputFailureException, ClassNotFoundException {

		List<Content> builtContents = new ArrayList<Content>();

		for (CtClass curClass : classList) {
			getLog().debug("Checking class for Component annotation " + curClass);

			Component annotation = (Component) curClass.getAnnotation(Component.class);

			getLog().debug("Annotation : " + annotation);

			if (annotation != null) {
				getLog().debug("Processing Component Class " + curClass);

				Content builtContent = ContentFactory.make(curClass, defaultComponentGroup);

				builtContents.add(builtContent);

				File contentFile = writeContentToFile(builtContent, curClass, buildDirectory, componentPathBase, defaultComponentPathSuffix);
				writeContentToArchiveFile(contentFile, curClass, zipOutputStream, reservedNames, componentPathBase, defaultComponentPathSuffix);
			}
		}

		return builtContents;

	}

	/**
	 * Write the content.xml to an output file, the path of which is determined by the component class
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
	protected static File writeContentToFile(Content content, CtClass componentClass, File buildDirectory, String componentPathBase, String defaultComponentPathSuffix)
			throws TransformerException, ParserConfigurationException, IOException, OutputFailureException, ClassNotFoundException {
		File componentOutputDirectory = getOutputDirectoryForComponentClass(componentClass, buildDirectory, componentPathBase, defaultComponentPathSuffix);

		File contentFile = new File(componentOutputDirectory, ".content.xml");

		if (contentFile.exists()) {
			contentFile.delete();
		}

		contentFile.createNewFile();

		ContentXmlWriter.writeContent(content, new FileOutputStream(contentFile));

		return contentFile;
	}

	/**
	 * Writes a provided content file to a provided archive output stream at a path determined by the class of the component.
	 *
	 * @param contentFile
	 * @param componentClass
	 * @param archiveStream
	 * @param reservedNames A list of files which already exist within the Zip Archive.  If a .content.xml file already
	 *                      exists for a particular component, it is left untouched.
	 * @param componentPathBase
	 * @throws IOException
	 * @throws ClassNotFoundException
	 */
	protected static void writeContentToArchiveFile(File contentFile, CtClass componentClass, ZipArchiveOutputStream archiveStream, Set<String> reservedNames, String componentPathBase, String defaultComponentPathSuffix) throws IOException, ClassNotFoundException {
		String contentFilePath = componentPathBase +
				"/" +
				getComponentPathSuffixForComponentClass(componentClass, defaultComponentPathSuffix) +
				"/" +
				getComponentNameForComponentClass(componentClass) +
				"/.content.xml";

		getLog().debug("Archiving content file " + contentFilePath);

		if (!reservedNames.contains(contentFilePath.toLowerCase())) {

			ZipArchiveEntry entry = new ZipArchiveEntry(contentFile, contentFilePath);

			archiveStream.putArchiveEntry(entry);

			IOUtils.copy(new FileInputStream(contentFile), archiveStream);

			archiveStream.closeArchiveEntry();

		}
		else {
			getLog().debug("Existing file found at " + contentFilePath);
		}
	}

	/**
	 * Constructs a list of Dialog objects based on Classes annotated by Component annotations.  Scans the provided
	 * list of classes constructing a Dialog object for each one annotated with the Component annotation.  Any classes
	 * provided in the class list which are not thusly annotated are ignored.
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
	 */
	protected static List<Dialog> buildDialogsFromClassList(
			List<CtClass> classList,
			ZipArchiveOutputStream zipOutputStream,
			Set<String> reservedNames,
			Map<Class<?>, String> xtypeMap,
			Map<String, WidgetMaker> widgetMakerMap,
			ClassLoader classLoader,
			ClassPool classPool,
			File buildDirectory,
			String componentPathBase,
			String defaultComponentPathSuffix)
					throws InvalidComponentClassException, InvalidComponentFieldException, OutputFailureException, IOException, ParserConfigurationException, TransformerException, ClassNotFoundException, CannotCompileException, NotFoundException, SecurityException, NoSuchFieldException, IllegalArgumentException, IllegalAccessException, InvocationTargetException, NoSuchMethodException {

		final List<Dialog> dialogList = new ArrayList<Dialog>();

		for (CtClass curClass : classList) {
			getLog().debug("Checking class for Component annotation " + curClass);

			Component annotation = (Component) curClass.getAnnotation(Component.class);

			getLog().debug("Annotation : " + annotation);

			if (annotation != null) {
				boolean hasField=false;
				for (CtField curField : curClass.getDeclaredFields()) {
					if(curField.hasAnnotation(DialogField.class)){
						hasField=true;
						break;
					}
				}
				if(hasField){
					getLog().debug("Processing Component Class " + curClass);
					Dialog builtDialog = DialogFactory.make(curClass, xtypeMap, widgetMakerMap, classLoader, classPool);
					dialogList.add(builtDialog);
					File dialogFile = writeDialogToFile(builtDialog, curClass, buildDirectory, componentPathBase, defaultComponentPathSuffix);
					writeDialogToArchiveFile(dialogFile, curClass, zipOutputStream, reservedNames, componentPathBase, defaultComponentPathSuffix);
				}
			}
		}

		return dialogList;

	}

	/**
	 * Writes a dialog.xml file, the path of which being based on the component Class.
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
	protected static File writeDialogToFile(Dialog dialog, CtClass componentClass,  File buildDirectory, String componentPathBase, String defaultComponentPathSuffix)
			throws OutputFailureException, IOException, ParserConfigurationException, TransformerException, ClassNotFoundException, IllegalArgumentException, SecurityException, IllegalAccessException, InvocationTargetException, NoSuchMethodException {
		File componentOutputDirectory = getOutputDirectoryForComponentClass(componentClass, buildDirectory, componentPathBase, defaultComponentPathSuffix);

		File dialogFile = new File(componentOutputDirectory, dialog.getFileName());

		if (dialogFile.exists()) {
			dialogFile.delete();
		}

		dialogFile.createNewFile();

		DialogXmlWriter.writeDialog(dialog, new FileOutputStream(dialogFile));

		return dialogFile;
	}


	/**
	 * Writes a provided dialog file to a provided archive output stream at a path determined by the class of the component.
	 *
	 * @param dialogFile
	 * @param componentClass
	 * @param archiveStream
	 * @param reservedNames A list of files which already exist within the Zip Archive.  If a dialog.xml file already
	 *                      exists for a particular component, it is left untouched.
	 * @param componentPathBase
	 * @throws IOException
	 * @throws ClassNotFoundException
	 */
	protected static void writeDialogToArchiveFile(File dialogFile, CtClass componentClass, ZipArchiveOutputStream archiveStream, Set<String> reservedNames, String componentPathBase, String defaultComponentPathSuffix) throws IOException, ClassNotFoundException {
		String dialogFilePath = componentPathBase +
				"/" +
				getComponentPathSuffixForComponentClass(componentClass, defaultComponentPathSuffix) +
				"/" +
				getComponentNameForComponentClass(componentClass) +
				"/" +
				dialogFile.getName();

		getLog().debug("Archiving dialog file " + dialogFilePath);

		//TODO: I'd like to move this check somewhere before we go through the trouble of creating the dialog object itself
		if (!reservedNames.contains(dialogFilePath.toLowerCase())) {

			ZipArchiveEntry entry = new ZipArchiveEntry(dialogFile, dialogFilePath);

			archiveStream.putArchiveEntry(entry);

			IOUtils.copy(new FileInputStream(dialogFile), archiveStream);

			archiveStream.closeArchiveEntry();

		}
		else {
			getLog().debug("Existing file found at " + dialogFilePath);
		}
	}

	/**
	 * Finds and retrieves the constructed CQ Package archive file for the project
	 *
	 * @param project
	 * @return
	 */
	protected static File getArchiveFileForProject(MavenProject project) {
		File buildDirectory = new File(project.getBuild().getDirectory());

		String zipFileName = project.getArtifactId() + "-" + project.getVersion() + ".zip";

		getLog().debug("Determined ZIP file name to be " + zipFileName);

		return new File(buildDirectory, zipFileName);
	}

	/**
	 * Create a temporary archive file which will live alongside the constructed project CQ5 Package archive.
	 *
	 * @param project
	 * @return
	 */
	protected static File getTempArchiveFileForProject(MavenProject project) {
		File buildDirectory = new File(project.getBuild().getDirectory());

		String zipFileName = project.getArtifactId() + "-" + project.getVersion() + "-temp.zip";

		getLog().debug("Temp archive file name " + zipFileName);

		return new File(buildDirectory, zipFileName);
	}

	/**
	 * Determine the appropriate output directory for a component's artifacts based on the component class as well as
	 * POM configuration.
	 *
	 * @param componentClass
	 * @param project
	 * @param componentPathBase
	 * @return
	 * @throws OutputFailureException
	 * @throws ClassNotFoundException
	 */
	protected static File getOutputDirectoryForComponentClass(CtClass componentClass, File buildDirectory, String componentPathBase, String defaultComponentPathSuffix) throws OutputFailureException, ClassNotFoundException {
		//File buildDirectory = new File(project.getBuild().getDirectory());

		String dialogFilePath = OUTPUT_PATH + "/" + componentPathBase + "/" + getComponentPathSuffixForComponentClass(componentClass, defaultComponentPathSuffix) + "/" + getComponentNameForComponentClass(componentClass);

		File componentOutputDirectory = new File(buildDirectory, dialogFilePath);

		if (!componentOutputDirectory.exists()) {
			if (!componentOutputDirectory.mkdirs()) {
				throw new OutputFailureException("Failure creating output directory for Component");
			}
		}

		return componentOutputDirectory;
	}

	/**
	 * Determines the suffix portion of the path leading to the artifacts of a particular component
	 *
	 * @param componentClass
	 * @param defaultComponentPathSuffix
	 * @return
	 * @throws ClassNotFoundException
	 */
	protected static String getComponentPathSuffixForComponentClass(CtClass componentClass, String defaultComponentPathSuffix) throws ClassNotFoundException {
		Component componentAnnotation = (Component) componentClass.getAnnotation(Component.class);

		if (componentAnnotation != null) {
			String path = componentAnnotation.path();

			if (StringUtils.isNotEmpty(path)) {
				return path;
			}
		}

		return defaultComponentPathSuffix;
	}

	/**
	 * Determines the name of the component class for use in constructing file paths
	 *
	 * @param componentClass
	 * @return
	 * @throws ClassNotFoundException
	 */
	protected static String getComponentNameForComponentClass(CtClass componentClass) throws ClassNotFoundException {
		Component componentAnnotation = (Component) componentClass.getAnnotation(Component.class);

		if (componentAnnotation != null) {
			String name = componentAnnotation.name();

			if (StringUtils.isNotEmpty(name)) {
				return name;
			}
		}

		return StringUtils.uncapitalise(componentClass.getSimpleName());
	}

	public static List<WidgetConfigHolder> getAllWidgetAnnotations(ClassPool classPool,ClassLoader classLoader) throws ClassNotFoundException, NotFoundException, MalformedURLException{
		List<WidgetConfigHolder> builtInWidgets=new ArrayList<WidgetConfigHolder>();
		List<WidgetConfigHolder> extendedWidgets=new ArrayList<WidgetConfigHolder>();
		
		Predicate<String> filter = new FilterBuilder().include("com.citytechinc.cq.component.dialog.AbstractWidget\\$.*");
		Reflections reflections=new Reflections(new ConfigurationBuilder()
			.setUrls(ClasspathHelper.forClassLoader(new ClassLoader[]{Thread.currentThread().getContextClassLoader(),classPool.getClassLoader(),classLoader}))
			.setScanners(new SubTypesScanner().filterResultsBy(filter), new TypeAnnotationsScanner()));

		for(Class<?> c:reflections.getTypesAnnotatedWith(Widget.class)){
			CtClass clazz=classPool.getCtClass(c.getName());
			Widget widgetAnnotation=(Widget)clazz.getAnnotation(Widget.class);
			Class<?> annotationClass=null;
			if(!StringUtils.isEmpty(widgetAnnotation.annotationClass())){
				annotationClass=Class.forName(widgetAnnotation.annotationClass());
			}
			Class<? extends WidgetMaker> makerClass=Class.forName(widgetAnnotation.makerClass()).asSubclass(WidgetMaker.class);
			Class<? extends AbstractWidget> widgetClass=Class.forName(clazz.getName()).asSubclass(AbstractWidget.class);
			WidgetConfigHolder widgetConfig=new WidgetConfigHolder(annotationClass,widgetClass, makerClass,widgetAnnotation.xtypes());
			if(clazz.getPackageName().equals(CITYTECH_PACKAGE)){
				builtInWidgets.add(widgetConfig);
			}else{
				extendedWidgets.add(widgetConfig);
			}
		}
		builtInWidgets.addAll(extendedWidgets);
		return builtInWidgets;
	}


}
