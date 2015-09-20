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

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * The DialogField annotation is used to mark a Field or Method in a Component
 * Class as an authorable element. Authorable elements are represented in the
 * dialog of the Component to which they belong.
 *
 */
@Retention(RetentionPolicy.CLASS)
@Target({ ElementType.FIELD, ElementType.METHOD })
public @interface DialogField {

	/**
	 * Allows for explicit definition of a dialog widget xtype associated with
	 * the element annotated.
	 *
	 * @return String
	 */
	public String xtype() default "";

	/**
	 * Applicable to Touch-UI only
	 *
	 * Allows for explicit definition of a dialog field's sling:resourceType.
	 *
	 * @return String
	 */
	public String resourceType() default "";

	/**
	 * The path to which the value for the authorable element will be saved
	 * during content authoring.
	 *
	 * @return String
	 */
	public String name() default "";

	/**
	 * The human-readable label for the input field which will be presented in a
	 * dialog.
	 *
	 * @return String
	 */
	public String fieldLabel() default "";

	/**
	 * While not technically part of the dialog property, the field name can be
	 * used to define a unique name for the dialog property within your dialog.
	 * This field name is used as the element name of the XML element
	 * representing this authorable element in the dialog.
	 *
	 * @return String
	 */
	public String fieldName() default "";

	/**
	 * Populates the fieldDescription widget property in the dialog.
	 *
	 * @return String
	 */
	public String fieldDescription() default "";

	/**
	 * Indicates that population of the widget input in the dialog is required.
	 * Used to set the allowBlank widget property in the dialog.
	 *
	 * @return boolean
	 */
	public boolean required() default false;

	/**
	 * Used to set the hideLabel widget property in the dialog.
	 *
	 * @return boolean
	 */
	public boolean hideLabel() default false;

	/**
	 * Used to set the defaultValue widget property in the dialog.
	 *
	 * @return String
	 */
	public String defaultValue() default "";

	/**
	 * The index number, starting at 1, of the tab in which to place the dialog
	 * widget representing this authorable element.
	 *
	 *
	 * @return int
	 */
	public int tab() default 1;
	
	/**
	 * The tab title of the tab in which to place the dialog widget representing 
	 * the authorable element.
	 * <p>
	 * Overrides {@link #tab()} if set. Falls back to {@link #tab()} if tab matching
	 * title cannot be found.
	 * 
	 * @return String
	 */
	public String tabTitle() default "";

	/**
	 * A list of additional properties not already represented by properties of
	 * the annotation. Each additional property is output as a widget property
	 * in the dialog.
	 *
	 * @return FieldProperty[]
	 */
	public Property[] additionalProperties() default {};

	/**
	 * The set of listeners which will be attributed to the dialog widget
	 * associated with the authorable element. Listeners are output as
	 * properties in the listeners XML node which is a child of the XML node
	 * representing the widget in the dialog.
	 *
	 * @return Listener[]
	 */
	public Listener[] listeners() default {};

	/**
	 * Used to order dialog widgets within a tab. Widgets representing
	 * authorable elements with a higher ranking appear lower in the tab than
	 * elements with a lower ranking. The ordering of elements of equal ranking
	 * is not guaranteed.
	 *
	 * @return double
	 */
	public double ranking() default 0;

	/**
	 * For Touch-UI Only
	 *
	 * Many of the Touch UI widgets react to an underlying value being set for
	 * the field.
	 *
	 * @return String
	 */
	public String value() default "";

	/**
	 * For Touch-UI Only
	 *
	 * @return String
	 */
	public String title() default "";

	/**
	 * Indicates that the particular field should be rendered in a disabled
	 * state
	 *
	 * @return boolean
	 */
	public boolean disabled() default false;

	/**
	 * For Touch-UI Only
	 *
	 * Additional css classes to attribute to the rendered field
	 *
	 * @return String
	 */
	public String cssClass() default "";

	/**
	 * Indicates that a Touch UI widget should not be produced for the annotated
	 * member
	 *
	 * @return boolean
	 */
	public boolean suppressTouchUI() default false;

	/**
	 * Indicates that a Touch UI widget should also render as read only
	 *
	 * @return boolean
	 */
	public boolean renderReadOnly() default true;

	/**
	 * Indicates that a Touch UI widget should show on page creation
	 *
	 * @return boolean
	 */
	public boolean showOnCreate() default true;

}
