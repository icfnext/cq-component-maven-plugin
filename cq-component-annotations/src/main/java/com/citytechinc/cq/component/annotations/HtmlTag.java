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

/**
 * Represents the cq:htmlTag node which will be rendered as a direct child of
 * the component's defining node. The attributes on this node give you some
 * level of control over the DOM element with which AEM wraps your component
 * instance when decoration is not disabled.
 */
public @interface HtmlTag {

	/**
	 * The DOM element type to be used when wrapping the component
	 *
	 * @return The DOM element type to be used when wrapping the component.
	 *         Defaults to div
	 */
	public String tagName() default "div";

	/**
	 * The CSS Class to apply to the component wrapper. This will override the
	 * classes applied by the wrapping mechanism but will be rendered in
	 * addition to the cell name of the component.
	 *
	 * @return The CSS Class to apply to the component wrapper
	 */
	public String cssClass() default "";

	/**
	 * The ID to apply to the component wrapper.
	 *
	 * @return The ID to apply to the component wrapper
	 */
	public String id() default "";

}
