package com.citytechinc.cq.component.gradle

import org.gradle.api.Plugin
import org.gradle.api.Project

class ComponentPlugin implements Plugin<Project> {
    void apply(Project project) {
        project.extensions.create("componentPlugin", ComponentPluginExtension)
        def component = project.task('generateComponents', type: GenerateComponentsTask)
    }
}
