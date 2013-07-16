
The cq-component-maven-plugin expects that an installable CQ Package .zip file has been created during
the build process prior to execution.  As such, the plugin should be run as part of or after the `package`
lifecycle phase and, if running during the `package` lifecycle phase, should be configured after the
plugin creating the aforementioned .zip file.

## Annotations

This plugin will search through the classes built as part of your project along with those contained in any
dependencies specified in the includeDependencies plugin configuration looking the `@Component` annotation and
generating .content.xml, _cq_editConfig, and dialog.xml files based on said annotation, the class itself,
and the fields of the class which are annotated with `@DialogField` annotations.  The plugin will attempt to
default most configuration present in these generated files based on the class and fields.  These default
choices can be overridden via properties of the annotations.

Specific files will only be generated if such files do not already exist for the component.  For example,
if you have created a dialog.xml file for the component already, this plugin will not overwrite your dialog.xml,
as it is assumed that you created yours for a reason and which to keep it.

### Component
[com.citytechinc.cq.component.annotations.Component](apidocs/com/citytechinc/cq/component/annotations/Component.html)

This is the annotation that signals the plugin that a class represents a component.  It contains configuration for the _cq_editConfig.xml, .content.xml, and dialog.xml files.

### DialogField
[com.citytechinc.cq.component.annotations.DialogField](apidocs/com/citytechinc/cq/component/annotations/DialogField.html)

This annotation marks a field or method as a field that should appear in the dialog.xml for the component.  It can be used alone or in conjunction with
one or multiple widget annotations

If used alone, and no xtype is configured the plugin can fall back on defualts based on what class the marked field is or what return type the marked method is.

The name used will be based off the field name or the method name using java bean format and can be overridden using the name property of the annotation.

<table class="table table-striped break-words-table">
	<thead>
		<tr>
			<th>Class/Primitive Type</th>
			<th>Default xtype</th>
		</tr>
	</thead>
	<tbody>
		<tr>
			<td>? Extends java.lang.Number</td>
			<td>numberfield</td>
		</tr>
		<tr>
			<td>int</td>
			<td>numberfield</td>
		</tr>
		<tr>
			<td>double</td>
			<td>numberfield</td>
		</tr>
		<tr>
			<td>java.lang.String</td>
			<td>textfield</td>
		</tr>
		<tr>
			<td>java.net.URI</td>
			<td>pathfield</td>
		</tr>
		<tr>
			<td>java.net.URL</td>
			<td>pathfield</td>
		</tr>
	</tbody>
</table>

#### Examples
Pathfield saved at ./title

	@DialogField(fieldLabel="Title")
	private String title;

Datefield saved at ./date

	@DialogField(fieldLabel="Title")
	@DateField
	private Date date;


Pathfield saved at ./otherTitle

	@DialogField(fieldLabel="Title",name="./otherTitle")
	private String title;



