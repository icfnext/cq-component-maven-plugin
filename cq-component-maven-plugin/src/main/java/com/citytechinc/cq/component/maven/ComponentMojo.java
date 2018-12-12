package com.citytechinc.cq.component.maven;

import java.io.File;
import java.net.MalformedURLException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javassist.ClassPool;
import javassist.CtClass;

import javax.naming.ConfigurationException;

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
import org.reflections.Reflections;

import com.citytechinc.cq.component.annotations.Component;
import com.citytechinc.cq.component.dialog.ComponentNameTransformer;
import com.citytechinc.cq.component.dialog.widget.WidgetRegistry;
import com.citytechinc.cq.component.dialog.widget.impl.DefaultWidgetRegistry;
import com.citytechinc.cq.component.editconfig.registry.DefaultInPlaceEditorRegistry;
import com.citytechinc.cq.component.editconfig.registry.InPlaceEditorRegistry;
import com.citytechinc.cq.component.maven.util.ComponentMojoUtil;
import com.citytechinc.cq.component.maven.util.LogSingleton;
import com.citytechinc.cq.component.touchuidialog.widget.registry.DefaultTouchUIWidgetRegistry;
import com.citytechinc.cq.component.touchuidialog.widget.registry.TouchUIWidgetRegistry;

@Mojo(name = "component", defaultPhase = LifecyclePhase.PACKAGE, requiresDependencyResolution = ResolutionScope.COMPILE)
public class ComponentMojo extends AbstractMojo {
	private static final String TEMP_FILENAME_SUFFIX = "-temp";
	private static final String PACKAGE_EXTENSION = ".zip";

	@Parameter(property = "aem.package.fileName", defaultValue = "${project.build.finalName}")
	private String packageFileName;

	@Parameter(defaultValue = "${project}")
	private MavenProject project;

	@Parameter
	private String componentPathBase;

	@Parameter(defaultValue = "content")
	private String componentPathSuffix;

	@Parameter(defaultValue = "Components")
	private String defaultComponentGroup;

	@Parameter(defaultValue = "camel-case")
	private String transformerName;

	@Parameter(required = false)
	private List<Dependency> excludeDependencies;

	@Parameter
	private List<Dependency> includeDependencies;

	@Parameter(defaultValue = "true")
	private boolean generateTouchUiDialogs;

	@Parameter(defaultValue = "true")
	private boolean generateClassicUiDialogs;

	@Parameter(required = false)
	private List<String> additionalFeatures;

	@Override
	public void execute() throws MojoExecutionException, MojoFailureException {
		LogSingleton.getInstance().setLogger(getLog());

		try {

			@SuppressWarnings("unchecked")
			List<String> classpathElements = project.getCompileClasspathElements();

			ClassLoader classLoader =
				ComponentMojoUtil.getClassLoader(classpathElements, this.getClass().getClassLoader());

			ClassPool classPool = ComponentMojoUtil.getClassPool(classLoader);

			Reflections reflections = ComponentMojoUtil.getReflections(classLoader);

			List<CtClass> classList =
				ComponentMojoUtil.getAllComponentAnnotations(
					classPool,
					reflections,
					getExcludedClasses(),
					getIncludedClasses()
				);

			WidgetRegistry widgetRegistry =
				new DefaultWidgetRegistry(classPool, classLoader, reflections, getAdditionalFeatures());

			TouchUIWidgetRegistry touchUIWidgetRegistry =
				new DefaultTouchUIWidgetRegistry(classPool, classLoader, reflections, getAdditionalFeatures());

			InPlaceEditorRegistry inPlaceEditorRegistry =
				new DefaultInPlaceEditorRegistry(classPool, classLoader, reflections);

			Map<String, ComponentNameTransformer> transformers =
				ComponentMojoUtil.getAllTransformers(classPool, reflections);

			ComponentNameTransformer transformer = transformers.get(transformerName);

			if (transformer == null) {
				throw new ConfigurationException("The configured transformer wasn't found");
			}

			ComponentMojoUtil.buildArchiveFileForProjectAndClassList(classList, widgetRegistry, touchUIWidgetRegistry,
				inPlaceEditorRegistry, classLoader, classPool, new File(project.getBuild().getDirectory()),
				componentPathBase, componentPathSuffix, defaultComponentGroup, getArchiveFileForProject(),
				getTempArchiveFileForProject(), transformer, generateTouchUiDialogs, generateClassicUiDialogs);

		} catch (Exception e) {
			getLog().error(e.getMessage(), e);
			throw new MojoExecutionException(e.getMessage(), e);
		}

	}

	private Set<String> getExcludedClasses() throws DependencyResolutionRequiredException, MalformedURLException {
		getLog().debug("Constructing set of excluded Class names");
		if (!hasIncludeDependencies() && hasExcludeDependencies()) {
			List<String> dependencyPaths = getDependencyPaths(excludeDependencies);
			if (!dependencyPaths.isEmpty()) {
				return getClassNames(dependencyPaths);
			}
		} else {
			getLog().debug("Excluded Class names skipped");
		}
		return Collections.emptySet();
	}

	private Set<String> getIncludedClasses() throws DependencyResolutionRequiredException, MalformedURLException {
		getLog().debug("Constructing set of included Class names");
		if (hasIncludeDependencies()) {
			List<String> dependencyPaths = getDependencyPaths(includeDependencies);
			if (!dependencyPaths.isEmpty()) {
				return getClassNames(dependencyPaths);
			}
		} else {
			getLog().debug("Included Class names skipped");
		}
		return Collections.emptySet();
	}

	/**
	 * Retrieve class names annotated with {@link Component} from specified dependency paths
	 * @param dependencyPaths to obtain available class names
	 * @return set of available class names
	 * @throws MalformedURLException if error occurs
	 */
	private Set<String> getClassNames(List<String> dependencyPaths) throws MalformedURLException {
		ClassLoader exclusionClassLoader =
			ComponentMojoUtil.getClassLoader(dependencyPaths, this.getClass().getClassLoader());

		Reflections reflections = ComponentMojoUtil.getReflections(exclusionClassLoader);

		Set<String> excludedClassNames = reflections.getStore()
			.getTypesAnnotatedWith(Component.class.getName());

		return excludedClassNames;
	}

	@SuppressWarnings("unchecked")
	private List<String> getDependencyPaths(List<Dependency> dependencies) throws DependencyResolutionRequiredException {
		if (dependencies != null && !dependencies.isEmpty()) {
			getLog().debug("Exclusions Found");

			List<Artifact> compileArtifacts = project.getCompileArtifacts();

			List<String> excludedClasspathElements = new ArrayList<String>();

			Set<String> excludedArtifactIdentifiers = new HashSet<String>();

			for (Dependency curDependency : dependencies) {
				excludedArtifactIdentifiers.add(curDependency.getGroupId() + ":" + curDependency.getArtifactId());
			}

			for (Artifact curArtifact : compileArtifacts) {
				String referenceIdentifier = curArtifact.getGroupId() + ":" + curArtifact.getArtifactId();

				if (excludedArtifactIdentifiers.contains(referenceIdentifier)) {
					MavenProject identifiedProject =
						(MavenProject) project.getProjectReferences().get(referenceIdentifier);
					if (identifiedProject != null) {
						excludedClasspathElements.add(identifiedProject.getBuild().getOutputDirectory());
						getLog().debug("Excluding " + identifiedProject.getBuild().getOutputDirectory());
					} else {
						File file = curArtifact.getFile();
						if (file == null) {
							throw new DependencyResolutionRequiredException(curArtifact);
						}
						excludedClasspathElements.add(file.getPath());
						getLog().debug("Excluding " + file.getPath());
					}
				}
			}

			return excludedClasspathElements;
		}

		return Collections.emptyList();

	}

	private File getArchiveFileForProject() {
		File buildDirectory = new File(project.getBuild().getDirectory());

		getLog().debug("Archive file name configured to be " + packageFileName + PACKAGE_EXTENSION);

		return new File(buildDirectory, packageFileName + PACKAGE_EXTENSION);
	}

	private File getTempArchiveFileForProject() {
		File buildDirectory = new File(project.getBuild().getDirectory());

		String tempPackageFileName = packageFileName + TEMP_FILENAME_SUFFIX + PACKAGE_EXTENSION;

		getLog().debug("Temp archive file name configured to be " + tempPackageFileName);

		return new File(buildDirectory, tempPackageFileName);
	}

	private List<String> getAdditionalFeatures() {
		if (additionalFeatures == null) {
			return new ArrayList<String>();
		}

		return additionalFeatures;
	}

	private boolean hasIncludeDependencies(){
		return includeDependencies != null && !includeDependencies.isEmpty();
	}

	private boolean hasExcludeDependencies(){
		return excludeDependencies != null && !excludeDependencies.isEmpty();
	}
}
