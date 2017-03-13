/**
 *    Copyright 2017 ICF Olson
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
package com.citytechinc.cq.component.annotations.widgets;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * Represents a Widget of type CQ.Ext.form.DialogFieldSet
 *
 */
@Retention(RetentionPolicy.CLASS)
@Target({ ElementType.FIELD, ElementType.METHOD })
public @interface DialogFieldSet {

	/**
	 * true to make sure the collapse/expand toggle button always renders first
	 * (to the left of) any other tools in the panel's title bar, false to
	 * render it last.
	 *
	 * @return boolean
	 */
	boolean collapseFirst() default true;

	/**
	 * True to make the panel collapsible and have the expand/collapse toggle
	 * button automatically rendered into the header tool button area, false to
	 * keep the panel statically sized with no button.
	 *
	 * @return boolean
	 */
	boolean collapsible() default false;

	/**
	 * true to render the panel collapsed, false to render it expanded.
	 *
	 * @return boolean
	 */
	boolean collapsed() default false;

	/**
	 * True to display the borders of the panel's body element, false to hide
	 * them.
	 *
	 * @return boolean
	 */
	boolean border() default true;

	/**
	 * The title text to be used as innerHTML (html tags are accepted) to
	 * display in the panel header (defaults to ''). When a title is specified
	 * the header element will automatically be created and displayed unless
	 * header is explicitly set to false. If you do not want to specify a title
	 * at config time, but you may want one later, you must either specify a
	 * non-empty title (a blank space ' ' will do) or header:true so that the
	 * container element will get created.
	 *
	 * @return Title String
	 */
	String title() default "";

	/**
	 * Used to prefix names of widgets contained within the DialogFieldSet. This
	 * allows for reuse of the DialogFieldSet within a single Component without
	 * field name collisions.
	 *
	 * @return The specified name prefix.
	 */
	String namePrefix() default "";
}
