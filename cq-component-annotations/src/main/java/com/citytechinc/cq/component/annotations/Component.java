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
package com.citytechinc.cq.component.annotations;

import com.citytechinc.cq.component.annotations.editconfig.ActionConfig;
import com.citytechinc.cq.component.annotations.editconfig.DropTarget;
import com.citytechinc.cq.component.annotations.editconfig.FormParameter;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * The Component annotation is used to indicate that a Class represents a CQ
 * Component. Multiple properties which are universally applicable to all types
 * of components are exposed for configuration via this annotation.
 *
 */
@Retention(RetentionPolicy.CLASS)
@Target(ElementType.TYPE)
public @interface Component {

	/**
	 * Overrides the baseComponentPath
	 *
	 *
	 * @return String
	 */
	String basePath() default "";

	/**
	 * The path to the component definition within the context of a CQ content
	 * tree. This is meant to override any default path specified for the
	 * project as a whole.
	 *
	 *
	 * @return String
	 */
	String path() default "";

	/**
	 * The name which will identify this component. This property should be used
	 * if the name which will be automatically generated for the component based
	 * on the name of the component class is not appropriate for your purposes.
	 *
	 * @return String
	 */
	String name() default "";

	/**
	 * Populates the jcr:title of the Component's .content.xml
	 *
	 * @return String
	 */
	String value();

	/**
	 * Populates the jcr:description property of the Component's .content.xml
	 *
	 * @return String
	 */
	String description() default "";

	/**
	 * The component group into which this component will be placed. This
	 * overrides any default group established for your project's build.
	 *
	 * @return String
	 */
	String group() default "";

	/**
	 * Indication of whether this component is a container for other components.
	 * Populates the cq:isContainer property of the Component's .content.xml.
	 *
	 * @return boolean
	 */
	boolean isContainer() default false;

	/**
	 * Indication of whether the component should not be rendered within a
	 * container. Populates the cq:noDecoration property of the Component's
	 * .content.xml.
	 *
	 * @return boolean
	 */
	boolean noDecoration() default false;

	/**
	 * Populates the cq:templatePath property of the Component's .content.xml.
	 *
	 * @return String
	 */
	String templatePath() default "";

	/**
	 * Populates the dialogPath property of the Component's .content.xml.
	 *
	 * @return String
	 */
	String dialogPath() default "";

	/**
	 * Populates the jcr:created property of the Component's .content.xml.
	 *
	 * @return String
	 */
	String created() default "";

	/**
	 * Establishes the set of Tabs which will appear within a Component's
	 * authoring dialog. When a multi-tab dialog is needed, all tabs must be
	 * defined via this property. Individual dialog fields indicate which tab
	 * they are to be placed into by number, starting from '1'. For more detail
	 * concerning placing Dialog Fields into tabs, see
	 * {@link com.citytechinc.cq.component.annotations.DialogField#tab()}
	 *
	 * @return Tab[]
	 */
	Tab[] tabs() default {};

	/**
	 * The list of actions which will populate the cq:actions attribute of the
	 * _cq_editConfig.xml. For more information on valid actions see the <a
	 * href=
	 * "http://dev.day.com/docs/en/cq/current/developing/components/edit_config.html#cq:actions"
	 * >Configuring Edit Behavior Documentation</a>
	 *
	 * @return String[]
	 */
	String[] actions() default {};

	/**
	 * Populates the cq:dialogMode property of the _cq_editConfig.xml. For more
	 * information on valid dialog modes see the <a href=
	 * "http://dev.day.com/docs/en/cq/current/developing/components/edit_config.html#cq:dialogMode"
	 * >Configuring Edit Behavior Documentation</a>
	 *
	 * @return String
	 */
	String dialogMode() default "floating";

	/**
	 * Populates the cq:layout property of the _cq_editConfig.xml. For more
	 * information on valid layouts see the <a href=
	 * "http://dev.day.com/docs/en/cq/current/developing/components/edit_config.html#cq:layout"
	 * >Configuring Edit Behavior Documentation</a>
	 *
	 * @return String
	 */
	String layout() default "editbar";

	/**
	 * Establishes the set of edit listeners which will be associated with the
	 * Component. These populate the cq:listeners node of the
	 * _cq_editConfig.xml. For more information on the purpose of and
	 * configuration for listeners, see the <a href=
	 * "http://dev.day.com/docs/en/cq/current/developing/components/edit_config.html#cq:listeners"
	 * >Configuring Edit Behavior Documentation</a>
	 *
	 * @return Listener[]
	 */
	Listener[] listeners() default {};

	/**
	 * Establishes the sling:resourceSuperType of this component
	 *
	 * @return String
	 */
	String resourceSuperType() default "";

	/**
	 * Establishes the empty text presented for components which are drop
	 * targets.
	 *
	 * @return String
	 */
	String emptyText() default "Drag components or assets here";

	/**
	 * Indicates whether this component should inherit edit behavior from it's
	 * super type. Populates the cq:inherit property of the _cq_editConfig.xml.
	 * For more information on inheriting edit behavior configuration see the <a
	 * href=
	 * "http://dev.day.com/docs/en/cq/current/developing/components/edit_config.html#cq:inherit"
	 * >Configuring Edit Behavior Documentation</a>
	 *
	 * @return boolean
	 */
	boolean editConfigInherit() default false;

	/**
	 * The name, without extension, to use for the output dialog xml file. This
	 * can be used when you want to output a dialog type other than the edit
	 * dialog. For example, a design dialog may be written for a component by
	 * setting the fileName to 'design_dialog'.
	 *
	 * @return String
	 */
	String fileName() default "dialog";

	/**
	 * The name, without extension, to use for the output _cq_dialog xml file. This
	 * can be used when you want to output a dialog type other than the edit
	 * dialog.
	 *
	 * @return String
	 */
	String touchFileName() default "_cq_dialog";

	/**
	 * Indicates whether a _cq_editConfig file should be generated for the
	 * component defined by the annotated Java Class. When set to false, a
	 * _cq_editConfig file will not be generated regardless of whether one
	 * already exists for the Component. When set to true, a _cq_editConfig file
	 * will only be generated if one does not already exist for the Component.
	 *
	 * @return boolean
	 */
	boolean editConfig() default true;

	/**
	 * The width of the dialog popup widget. The default value -1 indicates that
	 * the default width should be used.
	 *
	 * @return int
	 */
	int dialogWidth() default -1;

	/**
	 * The height of the dialog popup widget. The default value -1 indicates
	 * that the default height should be used.
	 *
	 * @return int
	 */
	int dialogHeight() default -1;

	/**
	 * Establishes the components which are allowed to be children of this
	 * component. This list populates the allowedChildren property of
	 * .content.xml. For more information on the usage of allowedChildren see
	 * the <a href=
	 * "http://dev.day.com/docs/en/cq/current/developing/components.html#Properties and Child Nodes of a Component"
	 * >Component Documentation</a>.
	 *
	 * @return String[]
	 */
	String[] allowedChildren() default {};

	/**
	 * Establishes the components which are allowed to be parents of this
	 * component. This list populates the allowedParents property of
	 * .content.xml. For more information on the usage of allowedParents see the
	 * <a href=
	 * "http://dev.day.com/docs/en/cq/current/developing/components.html#Properties and Child Nodes of a Component"
	 * >Component Documentation</a>.
	 *
	 * @return String[]
	 */
	String[] allowedParents() default {};

	/**
	 * Populates the cq:cellName property of the .content.xml. For more
	 * information on the intended usage of the cq:cellName property, see the <a
	 * href=
	 * "http://dev.day.com/docs/en/cq/current/developing/components.html#Properties and Child Nodes of a Component"
	 * >Component Documentation</a>.
	 *
	 * @return String
	 */
	String cellName() default "";

	/**
	 * Establishes the set of Action Configurations which will be written to the
	 * cq:actionConfigs node of the _cq_editConfig.xml. For more information on
	 * Action Configurations see the <a href=
	 * "http://dev.day.com/docs/en/cq/current/developing/components/edit_config.html#cq:actionConfigs"
	 * >Configuring Edit Behavior Documentation</a>.
	 *
	 * @return ActionConfig[]
	 */
	ActionConfig[] actionConfigs() default {};

	/**
	 * Indicates whether in place editing should be active for this component.
	 * This property populates the active property of the cq:inplaceEditing node
	 * in the _cq_editConfig.xml. For more information on inplace editing, see
	 * the <a href=
	 * "http://dev.day.com/docs/en/cq/current/developing/components/edit_config.html#cq:inplaceEditing"
	 * >Configuring Edit Behavior Documentation</a>.
	 *
	 * @return boolean
	 */
	boolean inPlaceEditingActive() default true;

	/**
	 * Establishes the path to the inplace editing configuration. This property
	 * populates the configPath property of the cq:inplaceEditing node in the
	 * _cq_editConfig.xml. For more information on inplace editing, see the <a
	 * href=
	 * "http://dev.day.com/docs/en/cq/current/developing/components/edit_config.html#cq:inplaceEditing"
	 * >Configuring Edit Behavior Documentation</a>.
	 *
	 * @return String
	 */
	String inPlaceEditingConfigPath() default "";

	/**
	 * Establishes the type of inplace editor which will be used for the
	 * annotated component. This property populates the editorType property of
	 * the cq:inplaceEditing node in the _cq_editConfig.xml. For more
	 * information on inplace editing, see the <a href=
	 * "http://dev.day.com/docs/en/cq/current/developing/components/edit_config.html#cq:inplaceEditing"
	 * >Configuring Edit Behavior Documentation</a>.
	 *
	 * @return String
	 */
	String inPlaceEditingEditorType() default "";

	/**
	 * Establishes the set of form parameters written to the cq:formParameters
	 * node of the _cq_editConfig.xml. For more information on form parameters
	 * see the <a href=
	 * "http://dev.day.com/docs/en/cq/current/developing/components/edit_config.html#cq:formParameters"
	 * >Configuring Edit Behavior Documentation</a>.
	 *
	 * @return String
	 */
	FormParameter[] formParameters() default {};

	/**
	 * Establishes the list of drop targets written to the cq:dropTargets node
	 * of the _cq_editConfig.xml. For more information on drop targets and their
	 * usage, see the <a href=
	 * "http://dev.day.com/docs/en/cq/current/developing/components/edit_config.html#cq:dropTargets"
	 * >Configuring Edit Behavior Documentation</a>.
	 *
	 * @return DropTarget[]
	 */
	DropTarget[] dropTargets() default {};

	/**
	 * A set of properties which are written to the content XML node.
	 *
	 * @return ContentProperty[]
	 */
	ContentProperty[] contentAdditionalProperties() default {};

	/**
	 * A list of extra client libs to be included on the jcr:root node
	 *
	 * @return ComponentProperty[]
	 */
	String[] extraClientlibs() default {};

	/**
	 * Indicates whether the Target context menu should be disabled for the
	 * particular component. Populates the cq:disableTargeting property of the
	 * _cq_editConfig.xml. For information on this properties usage, see <a
	 * href="http://forums.adobe.com/message/5334713">this forum article</a>.
	 *
	 * @return boolean
	 */
	boolean disableTargeting() default false;

	/**
	 * Definition of the cq:htmlTag child node of the component definition node.
	 * See <a href=
	 * "http://docs.adobe.com/docs/en/aem/6-0/develop/components.html#Properties and Child Nodes of a Component"
	 * > this document</a> for more information.
	 *
	 * @return HtmlTag[]
	 */
	HtmlTag[] htmlTag() default {};

	/**
	 * Indicates whether writing of the cq:dialog.xml appropriate to the Touch
	 * UI AEM interface should be skipped for this component.
	 *
	 * @return boolean
	 */
	boolean suppressTouchUIDialog() default false;

	/**
	 * A path to be followed when a user clicks the help button in the Touch UI
	 * Authoring interface.
	 *
	 * @return String
	 */
	String helpPath() default "";

	/**
	 * Applicable to the Touch UI only
	 *
	 * If set to true, extended classes and implemented interfaces will not be interrogated when arriving
	 * at a list of Dialog Fields for a Touch UI dialog.  This intends to support situations where you are
	 * both extending a class and a sling:resourceType and thus will be using the dialog resource merger for
	 * the inclusion of the supertype's dialog fields as opposed to inclusion via class inheritance.
	 *
	 * @return boolean
	 */
	boolean suppressFieldInheritanceForTouchUI() default false;

}
