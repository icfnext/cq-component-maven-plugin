cq-component-maven-plugin
======================

Some introduction should probably go here.

Configuration
-------------

The cq-component-maven-plugin expects that an installable CQ Package .zip file has been created during
the build process prior to execution.  As such, the plugin should be run as part of or after the `package`
lifecycle phase and, if running during the `package` lifecycle phase, should be configured after the
plugin creating the aforementioned .zip file.

```xml
<plugin>
	<groupId>com.citytechinc.cq</groupId>
	<artifactId>cq-component-maven-plugin</artifactId>
	<version>1.0.0-SNAPSHOT</version>
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
		<includeDependencies>
			<dependency>
				<artifactId>client-core</artifactId>
				<groupId>com.client.web</groupId>
			</dependency>
		</includeDependencies>
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
          `componentPathBase + / + componentPathSuffix + / +` component name (annotated per component or based
          on the name of the component class if not annotated).  The component path as a whole can be overridden
          at the component level via the `path` property of the `@Component` annotation.</td>
    </tr>
    <tr>
      <td>componentPathSuffix</td>
      <td>String</td>
      <td>content</td>
      <td>See the description of `componentPathBase` for an explanation of how this property is used in
          the construction of a path to which component files will be written.</td>
    </tr>
    <tr>
      <td>defaultComponentGroup</td>
      <td>String</td>
      <td></td>
      <td>The group to which Components whose files are generated via this plugin will be added in the
          CQ Sidekick.  This can be overridden at the component level via the `group` property of the
          `@Component` annotation.</td>
    </tr>
    <tr>
      <td>includeDependencies</td>
      <td>List&lt;Dependency&gt;</td>
      <td>Empty List</td>
      <td>An optional list of Dependencies whose classes should be included in the plugins search for
          annotated components.  Each dependency included in the list has two required child elements,
          `artifactId` and `groupId`.  These should match the `artifactId` and `groupId` for the dependency
          as specified in the dependency section of your POM.</td>
    </tr>
  </tbody>
</table>

Annotations
-----------

As the heading suggests, this is where documentation about available annotations will go.




