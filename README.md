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
		<xtypeMappings>
			<xtypeMapping>
				<className>com.example.cq.types.Color</className>
				<className>colorfield</className>
			</xtypeMapping>
		</xtypeMappings>
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
    <tr>
      <td>includeDependencies</td>
      <td>List&lt;dependency&gt;</td>
      <td>Empty List</td>
      <td>An optional list of Dependencies whose classes should be included in the plugins search for
          annotated components.  Each dependency included in the list has two required child elements,
          <span style="font-family:courier">artifactId</span> and <span style="font-family:courier">groupId</span>.  These should match the <span style="font-family:courier">artifactId</span> and <span style="font-family:courier">groupId</span> for the dependency
          as specified in the dependency section of your POM.</td>
    </tr>
    <tr>
      <td>xtypeMappings</td>
      <td>List&lt;xtypeMapping&gt;</td>
      <td>Empty List</td>
      <td>
        An optional mapping of Classes to xtypes.  If you are using custom Classes to represent your dialog fields this configuration
        can be used to define a mapping between your Classes and xtypes which will remove the need to annotate the xtype of the dialog field
        wherever the Class is used.
      </td>
    </tr>
  </tbody>
</table>

Annotations
-----------

This plugin will search through the classes built as part of your project along with those contained in any
dependencies specified in the includeDependencies plugin configuration looking the @Component annotation and
generating .content.xml, _cq_editConfig, and dialog.xml files based on said annotation, the class itself,
and the fields of the class which are annotated with @DialogField annotations.  The plugin will attempt to
default most configuration present in these generated files based on the class and fields.  These default
choices can be overridden via properties of the annotations.

Specific files will only be generated if such files do not already exist for the component.  For example,
if you have created a dialog.xml file for the component already, this plugin will not overwrite your dialog.xml,
as it is assumed that you created yours for a reason and which to keep it.

Annotations are defined under the `com.citytechinc.cq.component.annotations` package within the
cq-component-annotations module. The cq-component-annotations artifact will necessarily be a dependency of
the project module(s) wherein you are defining the Java objects representing your components.

<table>
  <thead>
    <tr>
      <th>Annotation</th>
      <th>Description</th>
      <th>Properties</th>
    </tr>
  </thead>
  <tbody>
    <tr>
      <td>Component</td>
      <td>This annotation marks a class as a CQ component.</td>
      <td>
        <table>
          <tr>
            <td>path</td>
            <td>The path to the component in the repository.  Setting this will override the path generated
                via the algorithm described above.</td>
          </tr>
          <tr>
            <td>name</td>
            <td>A unique name for the component.  In the path generating algorithm, this name is used to
                indicate the component folder into which the component files will be placed.</td>
          </tr>
          <tr>
            <td>title - <em>required</em></td>
            <td>The title of the component which will be presented in the Sidekick and Edit Bar (presuming
                an editbar layout).</td>
          </tr>
          <tr>
            <td>group</td>
            <td>The component group into which this component should be placed.  This will override the
                group set via the plugin configuration in the POM file.</td>
          </tr>
          <tr>
            <td>isContainer</td>
            <td>A flag indicating whether this component is a container for other components. <br/>
                <strong>Default: </strong> false.
            </td>
          </tr>
          <tr>
            <td>tabs</td>
            <td>An ordered list of strings defining the tabs which will appear within the eventually
                rendered dialog.  Each string in the list is the title of the rendered tab.  The order
                of the list represents the final ordering of the tabs in the dialog.  While tabs can
                be defined within the context of individual fields (see the tab property of DialogField),
                this property can be used to enforce a tab ordering when necessary. <br />
                <strong>Default: </strong> empty list.
            </td>
          </tr>
          <tr>
            <td>actions</td>
            <td>An ordered list of strings defining the edit actions which will be available for the
                component. <br/>
                <strong>Default: </strong> { &quot;text:[title]&quot;,&quot;-&quot;,&quot;edit&quot;,&quot;copymove&quot;,&quot;delete&quot;,&quot;-&quot;,&quot;insert&quot; }
            </td>
          </tr>
          <tr>
            <td>dialogMode</td>
            <td>Defines the way in which the component editable is presented in CQ author.
                One of &quot;editbar&quot;, &quot;rollover&quot;, or &quot;auto&quot;. <br/>
                <strong>Default: </strong> editbar
            </td>
          </tr>
          <tr>
            <td>layout</td>
            <td>Defines the way in which the dialog is presented in CQ author.  One of &quot;floating&quot;,
                &quot;fixed&quot;, or &quot;auto&quot;. <br/>
                <strong>Default: </strong> floating
            </td>
          </tr>
        </table>
      </td>
    </tr>
    <tr>
      <td>DialogField</td>
      <td>Indicates that a member of a class is expected to be populated via author dialog input and as such,
          a field for the member should be created in any dynamically created dialog.</td>
      <td>
        <table>
          <tr>
            <td>xtype</td>
            <td>The xtype of the widget which will be rendered for the field.  Specifying the xtype
                overrides any default determined by the plugin.  The following are the current defaults
                by field type.
                <ul>
                  <li>String : textfield</li>
                  <li>int / double / float and their respective classes : numberfield</li>
                  <li>URI/URL : pathfield</li>
                  <li>Enum : selection</li>
                  <li>List : multifield</li>
                </ul>
            </td>
          </tr>
          <tr>
            <td>name</td>
            <td>The name of the property in the content repository (ie, the relative path to which the
                property value will be saved upon submission of the dialog).
            </td>
          </tr>
          <tr>
            <td>fieldLabel</td>
            <td>The label to apply to the field in the rendered dialog</td>
          </tr>
          <tr>
            <td>fieldName</td>
            <td>A unique name for the field.  While not part of the dialog this field is used to override
                the name applied to the resource representing the dialog field itself.</td>
          </tr>
          <tr>
            <td>fieldDescription</td>
            <td>The description to apply to the field in the rendered dialog</td>
          </tr>
          <tr>
            <td>required</td>
            <td>A boolean representing whether population of the field by an author is required.</td>
          </tr>
          <tr>
            <td>tab</td>
            <td>The title of the tab into which this field should be placed.  Tabs will be created in the
                rendered dialog for each unique tab title specified either within DialogField annotations or
                in the tabs property of the Component annotation.</td>
          </tr>
          <tr>
            <td>selectionOptions</td>
            <td>An ordered list of Option annotations which define the valid values of the field. Currently
                this only affects selection type fields.</td>
          </tr>
          <tr>
            <td>selectionType</td>
            <td>One of the valid SelectionType enums indicating what type of selection widget to render.</td>
          </tr>
          <tr>
            <td>fieldConfigs</td>
            <td>A list of FieldConfig annotations indicating the configuration of inner dialog fields in
                the case of multi valued properties.  Currently this property only affects multifield type
                fields.</td>
          </tr>
          <tr>
            <td>additionalProperties</td>
            <td>A list of FieldProperty annotations which should be applied to the dialog widget representing
                this field.</td>
          </tr>
        </table>
      </td>
    </tr>
    <tr>
      <td>FieldConfig</td>
      <td>Used to configure the dialog field type of multi-valued fields.  Specifically, this can be used
          to override the xtype rendered for collection member fields.  For example, the dialog for a member of type
          List<String> will default to being a multifield with field configs of type textfield.  If you
          want something other than textfield as the inner field type, this annotation can be used in the
          context of the DialogField annotation's fieldConfigs property.</td>
      <td>
        <table>
          <tr>
            <td>xtype</td>
            <td>The widget xtype to be applied to the field.</td>
          </tr>
        </table>
      </td>
    </tr>
    <tr>
      <td>FieldProperty</td>
      <td>A generic and arbitrary name-value pair which should be applied to a dialog field.  This annotation
          may be used in the context of the DialogField's additionalProperties property.</td>
      <td>
        <table>
          <tr>
            <td>name</td>
            <td>The name of the property.</td>
          </tr>
          <tr>
            <td>value</td>
            <td>The value of the property.</td>
          </tr>
        </table>
      </td>
    </tr>
    <tr>
      <td>Option</td>
      <td>Used to define the valid values of a particular field.  This would most commonly be used in the
          case of a selection type field.  The Option annotation can be used within the context of the
          selectionOptions property of the DialogField annotation or applied directly to Enums to customize
          the text and/or value associated during rendering.</td>
      <td>
        <table>
          <tr>
            <td>text</td>
            <td>A text label which will be associated with the valid value in a dialog widget.</td>
          </tr>
          <tr>
            <td>value</td>
            <td>The concrete value which this option represents.</td>
          </tr>
        </table>
      </td>
  </tbody>
</table>




