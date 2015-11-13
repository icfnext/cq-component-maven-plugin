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
 * Represents a Widget of type CQ.Static
 */
@Retention(RetentionPolicy.CLASS)
@Target({ ElementType.FIELD, ElementType.METHOD })
public @interface Static {
	/**
	 * True to use bold font (defaults to false).
	 *
	 * @return boolean
	 */
	boolean bold() default false;

	/**
	 * True to use the default bottom margin of Static (defaults to false).
	 *
	 * @return boolean
	 */
	boolean bottommargin() default false;

	/**
	 * The hypertext reference for links.
	 *
	 * @return String
	 */
	String href() default "";

	/**
	 * The HTML to display.  Displayed only if text is empty.
	 *
	 * @return String
	 */
	String html() default "";

	/**
	 * True to italicize font (defaults to false).
	 *
	 * @return boolean
	 */
	boolean italic() default false;

	/**
	 * True to avoid wrapping at any white space (defaults to false).
	 *
	 * @return boolean
	 */
	boolean noWrap() default false;

	/**
	 * True to use a small font (defaults to false).
	 *
	 * @return boolean
	 */
	boolean small() default false;

	/**
	 * The tag name (defaults to "div" or "a" if href is set).
	 *
	 * @return String
	 */
	String tag() default "";

	/**
	 * The target for links.
	 *
	 * @return String
	 */
	String target() default "";

	/**
	 * The text to display.  It will be HTML encoded before displayed.
	 *
	 * @return String
	 */
	String text() default "";

	/**
	 * True to use the default top margin of Static (defaults to false).
	 *
	 * @return boolean
	 */
	boolean topmargin() default false;
}