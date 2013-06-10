package com.citytechinc.cq.component.maven;

import java.io.File;
import java.util.List;
import java.util.Map;

import javassist.ClassPool;
import javassist.CtClass;

import javax.naming.ConfigurationException;

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
import com.citytechinc.cq.component.dialog.widget.WidgetRegistry;
import com.citytechinc.cq.component.dialog.widget.impl.DefaultWidgetRegistry;
import com.citytechinc.cq.component.maven.util.ComponentMojoUtil;
import com.citytechinc.cq.component.maven.util.LogSingleton;

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

	@SuppressWarnings({ "unchecked" })
	public void execute() throws MojoExecutionException, MojoFailureException {

		LogSingleton.getInstance().setLogger(getLog());

		try {
			ClassLoader classLoader = ComponentMojoUtil.getClassLoader(project.getCompileClasspathElements(), this
				.getClass().getClassLoader());

			ClassPool classPool = ComponentMojoUtil.getClassPool(classLoader);

			Reflections reflections = ComponentMojoUtil.getReflections(classLoader);

			List<CtClass> classList = ComponentMojoUtil.getAllComponentAnnotations(classPool, reflections);

			WidgetRegistry widgetRegistry = new DefaultWidgetRegistry(classPool, classLoader, reflections);

			Map<String, ComponentNameTransformer> transformers = ComponentMojoUtil.getAllTransformers(classPool,
				reflections);

			ComponentNameTransformer transformer = transformers.get(transformerName);

			if (transformer == null) {
				throw new ConfigurationException("The configured transformer wasn't found");
			}

			ComponentMojoUtil.buildArchiveFileForProjectAndClassList(classList, widgetRegistry, classLoader, classPool,
				new File(project.getBuild().getDirectory()), componentPathBase, componentPathSuffix,
				defaultComponentGroup, getArchiveFileForProject(), getTempArchiveFileForProject(), transformer);

		} catch (Exception e) {
			getLog().error(e.getMessage(), e);
			throw new MojoExecutionException(e.getMessage(), e);
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

}
