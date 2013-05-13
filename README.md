cq-component-maven-plugin
=========================

The CQ Component Plugin generates many of the artifacts necessary for the creation of a CQ Component based on the information provided
by the Component's backing Java Class.

POM Configuration
-----------------

The cq-component-maven-plugin expects that an installable CQ Package .zip file has been created during
the build process prior to execution.  As such, the plugin should be run as part of or after the `package`
lifecycle phase and, if running during the `package` lifecycle phase, should be configured after the
plugin creating the aforementioned .zip file.

```xml
<plugin>
	<groupId>com.citytechinc.cq.cq-component-plugin</groupId>
	<artifactId>cq-component-maven-plugin</artifactId>
	<version>1.0.0</version>
	<extensions>true</extensions>
	<executions>
		<execution>
			<goals>
				<goal>component</goal>
			</goals>
		</execution>
	</executions>
	<configuration>
		<componentPathBase>jcr_root/apps/client/components</componentPathBase>
		<defaultComponentGroup>Client Group</defaultComponentGroup>
	</configuration>
</plugin>

```

<table>
  <thead>
    <tr>
      <th>Element</th>
      <th>Type</th>
      <th>Default</th>
      <th>Description</th>
    </tr>
  </thead>
  <tbody>
    <tr>
      <td>componentPathBase</td>
      <td>String</td>
      <td></td>
      <td>The content path to your project's components within the apps content tree.  The full path to which
          files for an individual component will be written is:
          <span style="font-family:courier">componentPathBase + / + componentPathSuffix + / +</span> component name (annotated per component or based
          on the name of the component class if not annotated).  The component path as a whole can be overridden
          at the component level via the <span style="font-family:courier">path</span> property of the <span style="font-family:courier">@Component</span> annotation.</td>
    </tr>
    <tr>
      <td>componentPathSuffix</td>
      <td>String</td>
      <td>content</td>
      <td>See the description of <span style="font-family:courier">componentPathBase</span> for an explanation of how this property is used in
          the construction of a path to which component files will be written.</td>
    </tr>
    <tr>
      <td>defaultComponentGroup</td>
      <td>String</td>
      <td></td>
      <td>The group to which Components whose files are generated via this plugin will be added in the
          CQ Sidekick.  This can be overridden at the component level via the <span style="font-family:courier">group</span> property of the
          <span style="font-family:courier">@Component</span> annotation.</td>
    </tr>
  </tbody>
</table>

Annotations
-----------

This plugin will search through the classes built as part of your project along with those contained in any
dependencies specified in the includeDependencies plugin configuration looking the `@Component` annotation and
generating .content.xml, _cq_editConfig, and dialog.xml files based on said annotation, the class itself,
and the fields of the class which are annotated with `@DialogField` annotations.  The plugin will attempt to
default most configuration present in these generated files based on the class and fields.  These default
choices can be overridden via properties of the annotations.

Specific files will only be generated if such files do not already exist for the component.  For example,
if you have created a dialog.xml file for the component already, this plugin will not overwrite your dialog.xml,
as it is assumed that you created yours for a reason and which to keep it.





