##Introduction

The CQ Component Plugin mitigates the effort required in Adobe CQ component development by generating, at built time, .content.xml, _cq_editConfig.xml, and dialog.xml files for your components, leaving you free to focus on functionality.

##Motivations

Adobe CQ components are comprised of a number of standard parts including a jsp file, a backing Java class, a dialog.xml file, a .content.xml file, and a _cq_editConfig.xml file. In practice, there is a great deal of duplicate information between the Java class and many of the configuration files. Creating the dialog.xml file alone is often a grueling task, especially when working with more complex widget xtypes such as the smart image and the rich text editor. As this artifact is without schema, the developer must remember, or copy from a prior dialog, the oft esoteric incantation necessary to effect their intended outcome, namely the presentation of a particular widget in a dialog which will save properties to a particular location.

The CQ Component Maven Plugin addresses these three areas by generating, as part of the build process, the dialog.xml, .content.xml, and _cq_editConfig.xml files based on information provided by a component's backing Java class. Details concerning dialog.xml configuration are abstracted by way of idiomatic annotations on the same component backing Java class, making even the most complicated xtypes straightforward to configure. Information is no longer duplicated as the Java class is being used directly in the generation of the xml configuration files instead of requiring manual propagation on the part of the developer. Finally, the number of artifacts necessary to construct a component is reduced from 5 to 2, reducing complexity and significantly sanitizing your source code.

##Goals

The CQ Component Plugin has one goal:

* `component` generates .content.xml, _cq_editConfig.xml, and dialog.xml files as necessary for Components it finds in project dependencies.

##Usage

General instructions on how to use the CQ Component Plugin can be found on the [usage page](usage.html).  Instructions for
extending the plugin can be found on the [Adding a Transformer](adding-a-transformer.html) and [Adding a Widget](adding-a-widget.html)
pages.

