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
 * Represents a Widget of type CQ.form.PathField
 *
 */
@Retention(RetentionPolicy.CLASS)
@Target({ ElementType.FIELD, ElementType.METHOD })
public @interface PathField {

	public static final boolean ESCAPE_AMP_DEFAULT = false;
	public static final boolean HIDE_TRIGGER_DEFAULT = false;
	public static final boolean PAR_BROWSE_DEFAULT = false;
	public static final String ROOT_PATH_DEFAULT = "/";
	public static final String ROOT_TITLE_DEFAULT = "Websites";
	public static final boolean SHOW_TITLE_IN_TREE_DEFAULT = true;

	/**
	 * True to url-encode the ampersand character (&amp;) to %26.
	 *
	 * @return boolean
	 */
	boolean escapeAmp() default ESCAPE_AMP_DEFAULT;

	/**
	 * True to disable the option to open the browse dialog
	 *
	 * @return boolean
	 */
	boolean hideTrigger() default HIDE_TRIGGER_DEFAULT;

	/**
	 * True to allow paragraph browsing and section in a grid next to the tree
	 * panel in the browse dialog. If this is enabled, it is recommended to use
	 * a predicate like 'hierarchy' to have pages as leaf nodes in the tree.
	 *
	 * @return boolean
	 */
	boolean parBrowse() default PAR_BROWSE_DEFAULT;

	/**
	 * The root path where completion and browsing starts. Use the empty string
	 * for the repository root
	 *
	 * @return String
	 */
	String rootPath() default ROOT_PATH_DEFAULT;

	/**
	 * Custom title for the root path
	 *
	 * @return String
	 */
	String rootTitle() default ROOT_TITLE_DEFAULT;

	/**
	 * Whether to show the (jcr:)titles as names of the tree nodes or the plain
	 * jcr node name
	 *
	 * @return boolean
	 */
	boolean showTitleInTree() default SHOW_TITLE_IN_TREE_DEFAULT;
}
