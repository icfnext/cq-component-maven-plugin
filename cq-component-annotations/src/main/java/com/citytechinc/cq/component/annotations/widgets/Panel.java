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
package com.citytechinc.cq.component.annotations.widgets;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * Represents a Widget of type CQ.Ext.Panel
 *
 */
@Retention(RetentionPolicy.CLASS)
@Target({ ElementType.FIELD, ElementType.METHOD })
public @interface Panel {

    /**
     * true to make sure the collapse/expand toggle button always renders first (to the left of) any other tools in the panel's title bar, false to render it last
     *
     * @return boolean
     */
	boolean collapseFirst() default true;

	/**
	 * True to make the panel collapsible and have the expand/collapse toggle button automatically rendered into the header tool button area, false to keep the panel statically sized with no button
	 *
	 * @return boolean
	 */
	boolean collapsible() default false;

	/**
	 * true to render the panel collapsed, false to render it expanded
	 *
	 * @return boolean
	 */
	boolean collapsed() default false;

	/**
	 * True to display the borders of the panel's body element, false to hide them
	 *
	 * @return boolean
	 */
	boolean border() default true;

	/**
	 * The title text to be used as innerHTML (html tags are accepted) to display in the panel header
	 *
	 * @return String
	 */
	String title() default "";
}
