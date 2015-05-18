## Usage

The cq-component-maven-plugin generates .content.xml, _cq_editConfig.xml, and dialog.xml files for Components
and injects them into a previously created CQ Package archive file.  As such, the plugin should be run as part of
or after the `package` lifecycle phase and, if running during the `package` lifecycle phase, should be configured after the
plugin creating the aforementioned archive file.

## Annotations

This plugin will search through the classes built as part of your project along with those contained in any
dependencies and transitive dependencies which are not included in the excludedDependencies POM configuration
looking for those annotated with the `@Component` annotation and generating .content.xml, _cq_editConfig, and dialog.xml files based on
said annotation, the class itself, and the fields and methods of the class which are annotated with `@DialogField` annotations.
The plugin will attempt to default most configuration present in these generated files based on information provided by the classes,
fields, and methods themselves.  These default choices can be overridden via properties of the annotations and stacked annotations.

Specific files will only be generated if such files do not already exist for the component.  For example,
if you have created a dialog.xml file for the component already, this plugin will not overwrite it.

### Component
[com.citytechinc.cq.component.annotations.Component](apidocs/com/citytechinc/cq/component/annotations/Component.html)

This is the annotation that indicates to the plugin that a class represents a component.  It contains configuration for the _cq_editConfig.xml, .content.xml, and dialog.xml files.

### DialogField
[com.citytechinc.cq.component.annotations.DialogField](apidocs/com/citytechinc/cq/component/annotations/DialogField.html)

This annotation marks a field or method as an authorable element.  Authorable elements are represented in CQ Component Dialogs.  How they are represented is based on the type of the field or method, information provided in the `@DialogField` annotation, and information provided in any stacked annotations.

Determination of the xtype to be rendered for an authorable element is based on the following process:

1. If the `xtype` property of the `@DialogField` annotation is populated, its value is used.
2. If a stacked Widget annotation is also associated with the element the xtype associated with the stacked annotation is used.

The name used will be based off the field name or the method name using Java bean standards and can be overridden using the `name` property of the annotation.

#### Dialog Field Examples
Textfield saved at ./title

	@DialogField(fieldLabel="Title")
	private String title;

TextArea saved at ./text

    @DialogField(fieldLabel="Text", xtype="textarea")
    private String text;

Pathfield saved at ./simplePath

	@DialogField(fieldLabel="Title",name="./simplePath")
	private URI path;

### Widgets
Widget annotations are used in conjunction with a DialogField annotation to set the correct xtype and to allow configuration of additional properties germane to the widget type.

All of the default widget annotations can be found [here](apidocs/com/citytechinc/cq/component/annotations/widgets/package-summary.html).

#### Widget Examples
Datefield saved at ./date

    @DialogField(fieldLabel="Title")
    @DateField
    private Date date;

Pathfield saved at ./mainpath

    @DialogField(fieldLabel="Main Path")
    @PathField
    private String mainpath;

Numberfield saved as ./quantity

    @DialogField(fieldLabel="Quantity")
    @NumberField(allowNegative=false, allowDecimals=true)
    private Double quantity;

