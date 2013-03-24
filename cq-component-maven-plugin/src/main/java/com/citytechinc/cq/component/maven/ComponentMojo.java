package com.citytechinc.cq.component.maven;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
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
import org.apache.maven.artifact.DependencyResolutionRequiredException;
import org.apache.maven.plugin.AbstractMojo;
import org.apache.maven.plugin.MojoExecutionException;
import org.apache.maven.plugin.MojoFailureException;
import org.apache.maven.plugins.annotations.LifecyclePhase;
import org.apache.maven.plugins.annotations.Mojo;
import org.apache.maven.plugins.annotations.Parameter;
import org.apache.maven.plugins.annotations.ResolutionScope;
import org.apache.maven.project.MavenProject;
import org.codehaus.plexus.util.StringUtils;

import com.citytechinc.cq.component.annotations.Component;
import com.citytechinc.cq.component.content.Content;
import com.citytechinc.cq.component.content.factory.ContentFactory;
import com.citytechinc.cq.component.content.xml.ContentXmlWriter;
import com.citytechinc.cq.component.dialog.Dialog;
import com.citytechinc.cq.component.dialog.exception.InvalidComponentClassException;
import com.citytechinc.cq.component.dialog.exception.InvalidComponentFieldException;
import com.citytechinc.cq.component.dialog.exception.OutputFailureException;
import com.citytechinc.cq.component.dialog.factory.DialogFactory;
import com.citytechinc.cq.component.dialog.factory.WidgetFactory;
import com.citytechinc.cq.component.dialog.maker.WidgetMaker;
import com.citytechinc.cq.component.dialog.maker.multifield.MultifieldWidgetMaker;
import com.citytechinc.cq.component.dialog.maker.selection.SelectionWidgetMaker;
import com.citytechinc.cq.component.dialog.maker.simple.SimpleWidgetMaker;
import com.citytechinc.cq.component.dialog.maker.smartimage.Html5SmartImageWidgetMaker;
import com.citytechinc.cq.component.dialog.xml.DialogXmlWriter;
import com.citytechinc.cq.component.editconfig.EditConfig;
import com.citytechinc.cq.component.editconfig.factory.EditConfigFactory;
import com.citytechinc.cq.component.editconfig.xml.EditConfigXmlWriter;

@Mojo( name="component", defaultPhase = LifecyclePhase.PACKAGE, requiresDependencyResolution = ResolutionScope.COMPILE )
public class ComponentMojo extends AbstractMojo {

	private static final String OUTPUT_PATH = "tempComponentConfig";

	@Parameter ( defaultValue = "${project}" )
	private MavenProject project;

	@Parameter
	private String componentPathBase;

	@Parameter ( defaultValue = "content" )
	private String componentPathSuffix;

	@Parameter ( defaultValue = "Components" )
	private String defaultComponentGroup;

	@Parameter ( required = false )
	private List<Dependency> includeDependencies;

	@Parameter ( required = false )
	private List<XtypeMapping> xtypeMappings;

	@Parameter ( required = false )
	private List<WidgetMakerMapping> widgetMakerMappings;

	@SuppressWarnings({ "unchecked" })
	public void execute() throws MojoExecutionException, MojoFailureException {

		try {
			ClassLoader classLoader = getClassLoader(project.getCompileClasspathElements());
			ClassPool classPool = getClassPool(classLoader);
			List<CtClass> compiledClasses = getCompiledClasses(classPool, project.getCompileClasspathElements());
			Map<Class<?>, String> classToXTypeMap = getXTypeMapForCustomXTypeMapping(classLoader);
			Map<String, WidgetMaker> xTypeToWidgetMakerMap = getXTypeToWidgetMakerMap(classLoader);
			buildArchiveFileForProjectAndClassList(compiledClasses, classToXTypeMap, xTypeToWidgetMakerMap, classLoader, classPool);
		} catch (MalformedURLException e) {
			getLog().error(e);
		} catch (DependencyResolutionRequiredException e) {
			getLog().error(e);
		} catch (ClassNotFoundException e) {
			getLog().error(e);
		} catch (InvalidComponentClassException e) {
			getLog().error(e);
		} catch (InvalidComponentFieldException e) {
			getLog().error(e);
		} catch (UnsupportedEncodingException e) {
			getLog().error(e);
		} catch (ParserConfigurationException e) {
			getLog().error(e);
		} catch (TransformerException e) {
			getLog().error(e);
		} catch (OutputFailureException e) {
			getLog().error(e);
		} catch (IOException e) {
			getLog().error(e);
		} catch (NotFoundException e) {
			getLog().error(e);
		} catch (CannotCompileException e) {
			getLog().error(e);
		} catch (SecurityException e) {
			getLog().error(e);
		} catch (NoSuchFieldException e) {
			getLog().error(e);
		} catch (InstantiationException e) {
			getLog().error(e);
		} catch (IllegalAccessException e) {
			getLog().error(e);
		}


	}

	private Map<String, WidgetMaker> getXTypeToWidgetMakerMap(ClassLoader classLoader)
			throws InstantiationException, IllegalAccessException, ClassNotFoundException {
		Map<String, WidgetMaker> xTypeToWidgetMakerMap = new HashMap<String, WidgetMaker>();

		WidgetMaker defaultWidgetMaker = new SimpleWidgetMaker();
		WidgetMaker html5SmartImageWidgetMaker = new Html5SmartImageWidgetMaker();
		WidgetMaker selectionWidgetMaker = new SelectionWidgetMaker();
		WidgetMaker multifieldWidgetMaker = new MultifieldWidgetMaker();

		/*
		 * Set the Default Widget Makers.  This may be overridden by configured makers
		 * if makers are configured for the same xtype
		 */
		xTypeToWidgetMakerMap.put(WidgetFactory.TEXTFIELD_XTYPE, defaultWidgetMaker);
		xTypeToWidgetMakerMap.put(WidgetFactory.NUMBERFIELD_XTYPE, defaultWidgetMaker);
		xTypeToWidgetMakerMap.put(WidgetFactory.PATHFIELD_XTYPE, defaultWidgetMaker);
		xTypeToWidgetMakerMap.put(WidgetFactory.HTML5SMARTIMAGE_XTYPE, html5SmartImageWidgetMaker);
		xTypeToWidgetMakerMap.put(WidgetFactory.SELECTION_XTYPE, selectionWidgetMaker);
		xTypeToWidgetMakerMap.put(WidgetFactory.MULTIFIELD_XTYPE, multifieldWidgetMaker);

		for (WidgetMakerMapping curWidgetMakerMapping : widgetMakerMappings) {
			xTypeToWidgetMakerMap.put(curWidgetMakerMapping.getXtype(), curWidgetMakerMapping.getMaker(classLoader));
		}

		return xTypeToWidgetMakerMap;
	}

	private Map<Class<?>, String> getXTypeMapForCustomXTypeMapping(ClassLoader classLoader) throws ClassNotFoundException {
		Map<Class<?>, String> retMap = new HashMap<Class<?>, String>();

		for (XtypeMapping curXTypeMapping : xtypeMappings) {
			retMap.put(curXTypeMapping.getClassObject(classLoader), curXTypeMapping.getXtype());
		}

		return retMap;
	}

	private ClassLoader getClassLoader(List<String> paths) throws MalformedURLException {
		final List<URL> pathURLs = new ArrayList<URL>();

		for (String curPath : paths) {

			URL newClassPathURL = new File(curPath).toURI().toURL();

			getLog().debug("Adding " + newClassPathURL.toString() + " to class loader");

			pathURLs.add(newClassPathURL);

		}

		return new URLClassLoader(pathURLs.toArray(new URL[0]), this.getClass().getClassLoader());
	}

	private ClassPool getClassPool(ClassLoader classLoader) {
		ClassPool classPool = new ClassPool();
		classPool.appendClassPath(new LoaderClassPath(classLoader));
		return classPool;
	}

	private List<CtClass> getCompiledClasses(ClassPool classPool, List<String> classPaths)
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

		@SuppressWarnings("unchecked")
		Set<Artifact> artifacts = project.getArtifacts();

		/*
		 * Look through the project artifacts to find any matching the dependency definition
		 * given in the project parameters.  If one does, look through it for classes to add to our
		 * class list.
		 */
		if (this.includeDependencies != null && !this.includeDependencies.isEmpty()) {
			for (Artifact curArtifact : artifacts) {

				if (includeJarClasses(curArtifact)) {
					classList.addAll(getClassListForJarFile(curArtifact.getFile(), classPool));
				}

			}
		}

		return classList;
	}

	private Boolean includeJarClasses(Artifact jarArtifact) {
		for (Dependency curDependency : this.includeDependencies) {
			if (
					jarArtifact.getArtifactId().equals(curDependency.getArtifactId()) &&
					jarArtifact.getGroupId().equals(curDependency.getGroupId())) {

				getLog().debug("Including artifact " + jarArtifact.getArtifactId() + " : " + jarArtifact.getGroupId());

				return true;
			}
		}

		return false;
	}

	private List<CtClass> getClassListForJarFile(File jarFile, ClassPool classPool)
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

	private String classNameFromFilePath(String filePath, String rootPath) {
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
	 * @throws OutputFailureException
	 * @throws IOException
	 * @throws TransformerException
	 * @throws ParserConfigurationException
	 * @throws InvalidComponentFieldException
	 * @throws InvalidComponentClassException
	 * @throws ClassNotFoundException
	 * @throws NotFoundException
	 * @throws CannotCompileException
	 * @throws NoSuchFieldException
	 * @throws SecurityException
	 */
	private void buildArchiveFileForProjectAndClassList(List<CtClass> classList, Map<Class<?>, String> classToXTypeMap, Map<String, WidgetMaker> xTypeToWidgetMakerMap, ClassLoader classLoader, ClassPool classPool)
			throws OutputFailureException, IOException, InvalidComponentClassException, InvalidComponentFieldException, ParserConfigurationException, TransformerException, ClassNotFoundException, CannotCompileException, NotFoundException, SecurityException, NoSuchFieldException {

		/*
		 * Get existing archive file
		 */
		File existingArchiveFile = getArchiveFileForProject();

		if (!existingArchiveFile.exists()) {
			throw new  OutputFailureException("Archive file does not exist");
		}

		/*
		 * Establish a temporary file where the new archive will be written to
		 */
		File tempArchiveFile = getTempArchiveFileForProject();

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
		buildContentFromClassList(classList, tempOutputStream, existingArchiveEntryNames);

		/*
		 * Create Dialogs within temp archive
		 */
		buildDialogsFromClassList(classList, tempOutputStream, existingArchiveEntryNames, classToXTypeMap, xTypeToWidgetMakerMap, classLoader, classPool);

		/*
		 * Create edit config within temp archive
		 */
		buildEditConfigFromClassList(classList, tempOutputStream, existingArchiveEntryNames);

		/*
		 * Copy temp archive to the original archive position
		 */
		tempOutputStream.finish();
		existingInputStream.close();
		tempOutputStream.close();

		existingArchiveFile.delete();
		tempArchiveFile.renameTo(existingArchiveFile);

	}

	private List<EditConfig> buildEditConfigFromClassList(List<CtClass> classList, ZipArchiveOutputStream zipOutputStream, Set<String> reservedNames) throws InvalidComponentClassException, TransformerException, ParserConfigurationException, IOException, OutputFailureException, ClassNotFoundException {

		List<EditConfig> builtEditConfigs = new ArrayList<EditConfig>();

		for (CtClass curClass : classList) {
			Component annotation = (Component) curClass.getAnnotation(Component.class);

			if (annotation != null) {
				EditConfig builtEditConfig = buildEditConfigFromClass(curClass, annotation);

				builtEditConfigs.add(builtEditConfig);

				File editConfigFile = writeEditConfigToFile(builtEditConfig, curClass);
				writeEditConfigToArchiveFile(editConfigFile, curClass, zipOutputStream, reservedNames);
			}
		}

		return builtEditConfigs;
	}

	private EditConfig buildEditConfigFromClass(CtClass editConfigClass, Component componentAnnotation)
			throws InvalidComponentClassException, ClassNotFoundException {
		return EditConfigFactory.make(editConfigClass);
	}

	private File writeEditConfigToFile(EditConfig editConfig, CtClass componentClass)
			throws TransformerException, ParserConfigurationException, IOException, OutputFailureException, ClassNotFoundException {
		File componentOutputDirectory = getOutputDirectoryForComponentClass(componentClass);

		File editConfigFile = new File(componentOutputDirectory, "_cq_editConfig.xml");

		if (editConfigFile.exists()) {
			editConfigFile.delete();
		}

		editConfigFile.createNewFile();

		EditConfigXmlWriter.writeEditConfig(editConfig, new FileOutputStream(editConfigFile));

		return editConfigFile;
	}

	private void writeEditConfigToArchiveFile(File editConfigFile, CtClass componentClass, ZipArchiveOutputStream archiveStream, Set<String> reservedNames) throws IOException, ClassNotFoundException {
		String editConfigFilePath = componentPathBase +
				"/" +
				getComponentPathSuffixForComponentClass(componentClass) +
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

	private List<Content> buildContentFromClassList(List<CtClass> classList, ZipArchiveOutputStream zipOutputStream, Set<String> reservedNames) throws InvalidComponentClassException, TransformerException, ParserConfigurationException, IOException, OutputFailureException, ClassNotFoundException {

		List<Content> builtContents = new ArrayList<Content>();

		for (CtClass curClass : classList) {
			getLog().debug("Checking class for Component annotation " + curClass);

			Component annotation = (Component) curClass.getAnnotation(Component.class);

			getLog().debug("Annotation : " + annotation);

			if (annotation != null) {
				getLog().debug("Processing Component Class " + curClass);

				Content builtContent = buildContentFromClass(curClass, annotation);

				builtContents.add(builtContent);

				File contentFile = writeContentToFile(builtContent, curClass);
				writeContentToArchiveFile(contentFile, curClass, zipOutputStream, reservedNames);
			}
		}

		return builtContents;

	}

	private Content buildContentFromClass(CtClass componentClass, Component contentAnnotation)
			throws InvalidComponentClassException, ClassNotFoundException {
		return ContentFactory.make(componentClass, defaultComponentGroup);
	}

	private File writeContentToFile(Content content, CtClass componentClass)
			throws TransformerException, ParserConfigurationException, IOException, OutputFailureException, ClassNotFoundException {
		File componentOutputDirectory = getOutputDirectoryForComponentClass(componentClass);

		File contentFile = new File(componentOutputDirectory, ".content.xml");

		if (contentFile.exists()) {
			contentFile.delete();
		}

		contentFile.createNewFile();

		ContentXmlWriter.writeContent(content, new FileOutputStream(contentFile));

		return contentFile;
	}

	private void writeContentToArchiveFile(File contentFile, CtClass componentClass, ZipArchiveOutputStream archiveStream, Set<String> reservedNames) throws IOException, ClassNotFoundException {
		String contentFilePath = componentPathBase +
				"/" +
				getComponentPathSuffixForComponentClass(componentClass) +
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


	private List<Dialog> buildDialogsFromClassList(List<CtClass> classList, ZipArchiveOutputStream zipOutputStream, Set<String> reservedNames, Map<Class<?>, String> classToXTypeMap, Map<String, WidgetMaker> xTypeToWidgetMakerMap, ClassLoader classLoader, ClassPool classPool)
			throws InvalidComponentClassException, InvalidComponentFieldException, OutputFailureException, IOException, ParserConfigurationException, TransformerException, ClassNotFoundException, CannotCompileException, NotFoundException, SecurityException, NoSuchFieldException {

		final List<Dialog> dialogList = new ArrayList<Dialog>();

		for (CtClass curClass : classList) {
			getLog().debug("Checking class for Component annotation " + curClass.getName());

			Component annotation = (Component) curClass.getAnnotation(Component.class);

			getLog().debug("Annotation : " + annotation);

			if (annotation != null) {
				getLog().debug("Processing Component Class " + curClass);
				Dialog builtDialog = buildDialogFromClass(curClass, classToXTypeMap, xTypeToWidgetMakerMap, classLoader, classPool);
				dialogList.add(builtDialog);
				File dialogFile = writeDialogeToFile(builtDialog, curClass);
				writeDialogToArchiveFile(dialogFile, curClass, zipOutputStream, reservedNames);
			}
		}

		return dialogList;

	}

	private Dialog buildDialogFromClass(
			CtClass curClass,
			Map<Class<?>, String> classToXTypeMap,
			Map<String, WidgetMaker> xTypeToWidgetMakerMap,
			ClassLoader classLoader,
			ClassPool classPool)
			throws InvalidComponentClassException, InvalidComponentFieldException, ClassNotFoundException, CannotCompileException, NotFoundException, SecurityException, NoSuchFieldException {

		return DialogFactory.make(curClass, classToXTypeMap, xTypeToWidgetMakerMap, classLoader, classPool);

	}

	private File writeDialogeToFile(Dialog dialog, CtClass componentClass)
			throws OutputFailureException, IOException, ParserConfigurationException, TransformerException, ClassNotFoundException {
		File componentOutputDirectory = getOutputDirectoryForComponentClass(componentClass);

		File dialogFile = new File(componentOutputDirectory, "dialog.xml");

		if (dialogFile.exists()) {
			dialogFile.delete();
		}

		dialogFile.createNewFile();

		DialogXmlWriter.writeDialog(dialog, new FileOutputStream(dialogFile));

		return dialogFile;
	}

	/*
	 * http://developer-tips.hubpages.com/hub/Zipping-and-Unzipping-Nested-Directories-in-Java-using-Apache-Commons-Compress
	 */
	private void writeDialogToArchiveFile(File dialogFile, CtClass componentClass, ZipArchiveOutputStream archiveStream, Set<String> reservedNames) throws IOException, ClassNotFoundException {
		String dialogFilePath = componentPathBase +
								"/" +
								getComponentPathSuffixForComponentClass(componentClass) +
								"/" +
								getComponentNameForComponentClass(componentClass) +
								"/dialog.xml";

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

	private File getArchiveFileForProject() {
		File buildDirectory = new File(project.getBuild().getDirectory());

		String zipFileName = project.getArtifactId() + "-" + project.getVersion() + ".zip";

		getLog().debug("Determined ZIP file name to be " + zipFileName);

		return new File(buildDirectory, zipFileName);
	}

	private File getTempArchiveFileForProject() {
		File buildDirectory = new File(project.getBuild().getDirectory());

		String zipFileName = project.getArtifactId() + "-" + project.getVersion() + "-temp.zip";

		getLog().debug("Temp archive file name " + zipFileName);

		return new File(buildDirectory, zipFileName);
	}

	private File getOutputDirectoryForComponentClass(CtClass componentClass) throws OutputFailureException, ClassNotFoundException {
		File buildDirectory = new File(project.getBuild().getDirectory());

		String dialogFilePath = OUTPUT_PATH + "/" + componentPathBase + "/" + getComponentPathSuffixForComponentClass(componentClass) + "/" + getComponentNameForComponentClass(componentClass);

		File componentOutputDirectory = new File(buildDirectory, dialogFilePath);

		if (!componentOutputDirectory.exists()) {
			if (!componentOutputDirectory.mkdirs()) {
				throw new OutputFailureException("Failure creating output directory for Component");
			}
		}

		return componentOutputDirectory;
	}

	private String getComponentPathSuffixForComponentClass(CtClass componentClass) throws ClassNotFoundException {
		Component componentAnnotation = (Component) componentClass.getAnnotation(Component.class);

		if (componentAnnotation != null) {
			String path = componentAnnotation.path();

			if (StringUtils.isNotEmpty(path)) {
				return path;
			}
		}

		return componentPathSuffix;
	}

	private String getComponentNameForComponentClass(CtClass componentClass) throws ClassNotFoundException {
		Component componentAnnotation = (Component) componentClass.getAnnotation(Component.class);

		if (componentAnnotation != null) {
			String name = componentAnnotation.name();

			if (StringUtils.isNotEmpty(name)) {
				return name;
			}
		}

		return StringUtils.uncapitalise(componentClass.getSimpleName());
	}

}
