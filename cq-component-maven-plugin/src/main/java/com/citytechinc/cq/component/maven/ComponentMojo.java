package com.citytechinc.cq.component.maven;

import java.io.File;
import java.util.ArrayList;
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

import com.citytechinc.cq.component.dialog.ComponentNameTransformer;
import com.citytechinc.cq.component.dialog.maker.WidgetMaker;
import com.citytechinc.cq.component.maven.util.ComponentMojoUtil;
import com.citytechinc.cq.component.maven.util.LogSingleton;
import com.citytechinc.cq.component.maven.util.WidgetConfigHolder;

@Mojo(name = "component", defaultPhase = LifecyclePhase.PACKAGE, requiresDependencyResolution = ResolutionScope.COMPILE)
public class ComponentMojo extends AbstractMojo {

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

	@Parameter ( required = false )
    private List<Dependency> excludeDependencies;

	public void execute() throws MojoExecutionException, MojoFailureException {

		LogSingleton.getInstance().setLogger(getLog());

		try {
		    List<String> classpathElements = getClasspathElements();

			ClassLoader classLoader = ComponentMojoUtil.getClassLoader(classpathElements, this
				.getClass().getClassLoader());

			ClassPool classPool = ComponentMojoUtil.getClassPool(classLoader);

			Reflections reflections = ComponentMojoUtil.getReflections(classLoader);

			List<CtClass> classList = ComponentMojoUtil.getAllComponentAnnotations(classPool, reflections);

			List<WidgetConfigHolder> widgetConfigs = ComponentMojoUtil.getAllWidgetAnnotations(classPool, classLoader,
				reflections);

			Map<Class<?>, WidgetConfigHolder> classToXTypeMap = ComponentMojoUtil
				.getXTypeMapForCustomXTypeMapping(widgetConfigs);

			Map<String, WidgetMaker> xTypeToWidgetMakerMap = ComponentMojoUtil.getXTypeToWidgetMakerMap(widgetConfigs);

			Map<String, ComponentNameTransformer> transformers = ComponentMojoUtil.getAllTransformers(classPool,
				reflections);

			ComponentNameTransformer transformer = transformers.get(transformerName);

			if (transformer == null) {
				throw new ConfigurationException("The configured transformer wasn't found");
			}

			ComponentMojoUtil.buildArchiveFileForProjectAndClassList(classList, classToXTypeMap, xTypeToWidgetMakerMap,
				classLoader, classPool, new File(project.getBuild().getDirectory()), componentPathBase,
				componentPathSuffix, defaultComponentGroup, getArchiveFileForProject(), getTempArchiveFileForProject(),
				transformer);

		} catch (Exception e) {
			getLog().error(e.getMessage(), e);
			throw new MojoExecutionException(e.getMessage(), e);
		}

	}

	/**
	 * Returns a list of paths to elements of the classpath for the project.  If
	 * dependencies are specified for exclusion via the excludeDependencies POM configuration,
	 * the classpath elements related to the excluded dependencies are not included in
	 * the resultant list.
	 *
	 * @return
	 * @throws DependencyResolutionRequiredException
	 */
    @SuppressWarnings("unchecked")
    private List<String> getClasspathElements() throws DependencyResolutionRequiredException {

        if (excludeDependencies != null && !excludeDependencies.isEmpty()) {
            List<Artifact> compileArtifacts = project.getCompileArtifacts();

            List<String> classpathElements = new ArrayList<String>();

            classpathElements.add(project.getBuild().getOutputDirectory());

            /*
             * Construct a set representation of the dependency exclusions mapped by
             * group id and artifact id for easy lookup
             */
            Set<String> excludedArtifactIdentifiers = new HashSet<String>();

            for (Dependency curDependency : excludeDependencies) {
                excludedArtifactIdentifiers.add(curDependency.getGroupId() + ":" + curDependency.getArtifactId());
            }

            for (Artifact curArtifact : compileArtifacts) {
                String referenceIdentifier = curArtifact.getGroupId() + ":" + curArtifact.getArtifactId();

                if (!excludedArtifactIdentifiers.contains(referenceIdentifier)) {
                    MavenProject identifiedProject = (MavenProject) project.getProjectReferences().get(referenceIdentifier);
                    if (identifiedProject != null)
                    {
                        classpathElements.add(identifiedProject.getBuild().getOutputDirectory());
                    }
                    else
                    {
                        File file = curArtifact.getFile();
                        if (file == null)
                        {
                            throw new DependencyResolutionRequiredException(curArtifact);
                        }
                        classpathElements.add(file.getPath());
                    }
                }
            }

            return classpathElements;
        }

        return project.getCompileClasspathElements();

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

}
