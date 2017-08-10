## Usage

The cq-component-maven-plugin generates .content.xml, _cq_editConfig.xml, _cq_dialog.xml, and dialog.xml files for Components
and injects them into a previously created CQ Package archive file.  As such, the plugin should be run as part of
or after the `package` lifecycle phase and, if running during the `package` lifecycle phase, should be configured after the
plugin creating the aforementioned archive file.

## Annotations

This plugin will search through the classes built as part of your project along with those contained in any
dependencies and transitive dependencies which are not included in the excludedDependencies POM configuration
looking for those annotated with the `@Component` annotation and generating .content.xml, _cq_editConfig, _cq_dialog.xml, and dialog.xml files based on
said annotation, the class itself, inherited classes and interfaces, and the fields and methods of the class or inherited classes and interfaces  which are annotated with `@DialogField` annotations.
The plugin will attempt to default most configuration present in these generated files based on information provided by the classes,
fields, and methods.  These default choices can be overridden via properties of the annotations and stacked annotations.

Specific files will only be generated if such files do not already exist for the component.  For example,
if you have created a dialog.xml file for the component already, this plugin will not overwrite it.

### Component
[com.citytechinc.cq.component.annotations.Component](apidocs/com/citytechinc/cq/component/annotations/Component.html)

This is the annotation that indicates to the plugin that a class represents a component.  It contains configuration for the _cq_editConfig.xml, .content.xml, _cq_dialog.xml, and dialog.xml files.

### DialogField
[com.citytechinc.cq.component.annotations.DialogField](apidocs/com/citytechinc/cq/component/annotations/DialogField.html)

This annotation marks a field or method as an authorable element.  Authorable elements are represented in CQ Component Dialogs.  How they are represented is based on information provided in the `@DialogField` annotation and information provided in any stacked annotations.

Determination of the xtype to be rendered for an authorable element in the Classic UI is based on the following process:

1. If the `xtype` property of the `@DialogField` annotation is populated, its value is used.
2. If a stacked Widget annotation is also associated with the element the xtype associated with the stacked annotation is used.

Determination of the resourceType to be rendered for an authorable element in the Touch UI is similarly:

1. If the `resourceType` property of the `@DialogField` annotation is populated, its value is used.
2. If a stacked Widget annotation is also associated with the element the resourceType associated with the stacked annotation is used.

The name used will be based off the field name or the method name using Java bean standards and can be overridden using the `name` property of the annotation.

#### Dialog Field Examples
Textfield saved at ./title

	@DialogField(fieldLabel="Title")
	@TextField
	private String title;

TextArea saved at ./text using explicitly set xtypes and resourceTypes

    @DialogField(fieldLabel="Text", xtype="textarea", resourceType="granite/ui/components/foundation/form/textarea")
    private String text;
    
TextArea saved at ./text using Stacked Annotations (recommended) 

    @DialogField(fieldLabel="Text")
    @TextArea
    private String text;

Pathfield saved at ./simplePath

	@DialogField(fieldLabel="Title", name="./simplePath")
	@PathField
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

### In Place Editors
In Place Editor specific annotations are used to configure in place editors inside the cq:editConfig.xml.  If multiple in place editor annotations are found for a given class, a hybrid in place editor is configured using the provided individual configurations.

All of the default in place editor annotations can be found [here](apidocs/com/citytechinc/cq/component/editconfig/annotations/inplaceeditors/package-summary.html).

#### In Place Editor Examples
A Rich Text In Place Editor saved at ./text

    @TextEditor
	String text

An Image In Place Editor saved at ./image (with the additional widget annotations so that it functions)

    @DialogField(fieldLabel = "Image")
	@Html5SmartImage(tab=false)
	@ImageEditor(title="Image")
	Image image

## Inheritance

Dialog Fields will be inherited from any classes or interfaces extended or implemented respectively by a component's backing class.  

    public interface ClassifiableComponent {
        @DialogField(fieldLabel = "Classifications", ranking = 1000)
        @TagInputField
        public List<Tag> getClassifications();
    }

    @Component("Concrete Component")
    public class ConcreteComponent implements ClassifiableComponent {
    
        public List<Tag> getClassifications() {
            ...
        }
    
    }

### DialogFieldOverride
[com.citytechinc.cq.component.annotations.DialogFieldOverride](apidocs/com/citytechinc/cq/component/annotations/DialogFieldOverride.html)

The `@DialogFieldOverride` annotation allows for granular overrides to `@DialogField` properties which would be otherwise inherited without 
the respecification of the entire `@DialogField` annotation.

    public interface ClassifiableComponent {
        @DialogField(fieldLabel = "Classifications", ranking = 1000)
        @TagInputField
        public List<Tag> getClassifications();
    }

    @Component("Concrete Component")
    public class ConcreteComponent implements ClassifiableComponent {
    
        @DialogFieldOverride(ranking = 50, required = false, hideLabel = false)
        public List<Tag> getClassifications() {
            ...
        }
    
    }

Due to limitations with Java Annotations, overridable boolean attributes are required in the `@DialogFieldOverride` annotation.  These 
attributes are the `required` and `hideLabel`.  

### IgnoreDialogField
[com.citytechinc.cq.component.annotations.IgnoreDialogField](apidocs/com/citytechinc/cq/component/annotations/IgnoreDialogField.html)

The `@IgnoreDialogField` annotation is used to suppress the output of an otherwise inherited field.

    public interface ClassifiableComponent {
        @DialogField(fieldLabel = "Classifications", ranking = 1000)
        @TagInputField
        public List<Tag> getClassifications();
    }

    @Component("Concrete Component")
    public class ConcreteComponent implements ClassifiableComponent {
    
        @IgnoreDialogField
        public List<Tag> getClassifications() {
            //Returns some non-authored list of Tags
            ...
        }
    
    }

## Touch UI and Classic UI

Introduced in AEM 6.0, the Touch UI is the default page authoring user interface.  Authors who wish to or need to based on 
functionality may fall back to the Classic UI.  This means that developers often need to support authorability via both the 
Touch UI and the Classic UI.  By default the CQ Component Plugin will generate both the _cq_dialog.xml file used by the 
Touch UI and the dialog.xml file used by the Classic UI using a single set of annotations.
  
### Disabling Touch UI 

Generation of the Touch UI dialogs can be suppressed at a project, component, or field level.  To suppress generation at a 
project level set the `generateTouchUiDialogs` POM configuration to `false`.  To suppress generation at a component 
level, set the `suppressTouchUIDialog` attribute of the `@Component` annotation to `false`.  To suppress generation at 
a field level, set the `suppressTouchUI` attribute of the `@DialogField` annotation to `false`. 

### Disabling Classic UI 

Generation of the Classic UI dialogs can be suppressed at a project, component, or field level.  To suppress generation at a 
project level set the `generateClassicUiDialogs` POM configuration to `false`.  To suppress generation at a component 
level, set the `suppressClassicUIDialog` attribute of the `@Component` annotation to `false`.

### Mode-Exclusive Widgets

It is possible to create widgets which work exclusively in Touch UI or Classic UI.  An example of such a widget is 
that enabled by the `@Switch` annotation.  Currently this annotation will not be recognized by the Classic UI widget 
rendering mechanisms.  Similarly, custom widgets you create may only have support for one or the other interface 
depending on your requirements, there is not a systematic requirement to support both.  

### Suppressing Field Inheritance

Sling's resource merger, used in the rendering of Dialogs in the Touch UI, merges properties of child resource type 
dialogs with those of the parent.  To avoid duplicative and potentially invalid definition of fields in the 
Touch UI dialog XML, the `suppressFieldInheritanceForTouchUI` flag can be enabled.  Enabling this flag will keep 
the Touch UI widget rendering mechanisms from looking up the Java class hierarchy when collecting dialog field 
widgets.  