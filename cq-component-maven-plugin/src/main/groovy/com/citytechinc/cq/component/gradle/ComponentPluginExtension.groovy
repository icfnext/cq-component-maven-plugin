package com.citytechinc.cq.component.gradle


class ComponentPluginExtension {
    String componentPathBase
    String componentPathSuffix = "content"
    String defaultComponentGroup = "Components"
    String transformerName = "camel-case"
    boolean generateTouchUiDialogs = true
    boolean generateClassicUiDialogs = true
    //TODO: Implement excludedDependences
}
