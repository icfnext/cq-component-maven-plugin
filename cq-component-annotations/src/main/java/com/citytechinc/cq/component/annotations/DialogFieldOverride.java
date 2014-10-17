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
 * The DialogFieldOverride annotation is used to override settings from a
 * DialogField annotation on an interface or overriden method.
 *
 */
@Retention(RetentionPolicy.CLASS)
@Target({ ElementType.FIELD, ElementType.METHOD })
public @interface DialogFieldOverride {

	/**
	 * The human-readable label for the input field which will be presented in a
	 * dialog.
	 *
	 * @return String
	 */
	public String fieldLabel() default "";

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
	 * A list of additional properties not already represented by properties of
	 * the annotation. Each additional property is output as a widget property
	 * in the dialog.
	 *
	 * @return FieldProperty[]
	 */
	public FieldProperty[] additionalProperties() default {};

	/**
	 * If this is set to true, the instead of overriding the additional
	 * properties, the additional properties from the parent will be merged with
	 * the ones from this annotation.
	 */
	public boolean mergeAdditionalProperties() default false;

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
	 * If this is set to true, the instead of overriding the listeners, the
	 * listeners from the parent will be merged with the ones from this
	 * annotation.
	 */
	public boolean mergeLiseners() default false;

	/**
	 * Used to order dialog widgets within a tab. Widgets representing
	 * authorable elements with a higher ranking appear lower in the tab than
	 * elements with a lower ranking. The ordering of elements of equal ranking
	 * is not guaranteed.
	 *
	 * @return double
	 */
	public double ranking() default 0;
}
