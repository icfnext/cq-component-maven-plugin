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
package com.citytechinc.cq.component.gradle

import javassist.ClassPool
import javassist.CtClass

import org.gradle.api.DefaultTask
import org.gradle.api.GradleException
import org.gradle.api.tasks.TaskAction
import org.reflections.Reflections

import com.citytechinc.cq.component.dialog.ComponentNameTransformer
import com.citytechinc.cq.component.dialog.widget.WidgetRegistry
import com.citytechinc.cq.component.dialog.widget.impl.DefaultWidgetRegistry
import com.citytechinc.cq.component.maven.util.ComponentMojoUtil
import com.citytechinc.cq.component.maven.util.LogSingleton

class GenerateComponentsTask extends DefaultTask{

	@TaskAction
	def generateComponents(){
		LogSingleton.instance.logger=new GradleMavenLog(this.logger)
		def urls=[]
		urls.addAll(project.sourceSets.main.runtimeClasspath.collect { it.toURI().toURL() }.flatten())
		ClassLoader classLoader = new URLClassLoader(urls as URL[],this.class.classLoader)

		ClassPool classPool = ComponentMojoUtil.getClassPool(classLoader)

		Reflections reflections = ComponentMojoUtil.getReflections(classLoader)

		//TODO: Implement excludedDependences
		List<CtClass> classList = ComponentMojoUtil.getAllComponentAnnotations(classPool, reflections, [] as Set)

		WidgetRegistry widgetRegistry = new DefaultWidgetRegistry(classPool, classLoader, reflections)

		Map<String, ComponentNameTransformer> transformers = ComponentMojoUtil.getAllTransformers(classPool,
				reflections)

		ComponentNameTransformer transformer = transformers.get(project.componentPlugin.transformerName)

		if (transformer == null) {
			throw new GradleException("The configured transformer wasn't found")
		}

		ComponentMojoUtil.buildArchiveFileForProjectAndClassList(classList, widgetRegistry, classLoader, classPool,
				project.buildDir, project.componentPlugin.componentPathBase, project.componentPlugin.componentPathSuffix,
				project.componentPlugin.defaultComponentGroup, getArchiveFileForProject(), getTempArchiveFileForProject(), transformer)
	}

	def File getArchiveFileForProject() {
		File buildDirectory = new File(project.buildDir,"distributions")

		String zipFileName = project.name + "-" + project.version + ".zip"

		return new File(buildDirectory, zipFileName)
	}

	def File getTempArchiveFileForProject() {

		String zipFileName = project.name + "-" + project.version + "-temp.zip"

		return new File(project.buildDir, zipFileName)
	}
}
