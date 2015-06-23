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
 * Defines a Tab in a Component dialog.
 */
public @interface Tab {

	/**
	 * Title of the dialog Tab.
	 *
	 * @return String
	 */
	String title() default "";

	/**
	 * Applicable to Classic-UI only
	 * 
	 * Defines the path to the Tab definition in the content repository. This
	 * property may be used when the Tab is to be populated in the rendered
	 * dialog using an existing definition in the repository.
	 *
	 * @return String
	 */
	String classicUIPath() default "";

	/**
	 * Applicable to Touch-UI only
	 * 
	 * Defines the path to the Tab definition in the content repository. This
	 * property may be used when the Tab is to be populated in the rendered
	 * dialog using an existing definition in the repository.
	 * 
	 */
	String touchUIPath() default "";

	/**
	 * Applicable to Classic-UI only
	 * 
	 * The set of listeners which will be attributed to the tab. Listeners are
	 * output as properties in the listeners XML node which is a child of the
	 * XML node representing the dialog tab..
	 *
	 * @return Listener[]
	 */
	public Listener[] listeners() default {};

	/**
	 * Applicable to Touch-UI only
	 * 
	 * Defines the sling:resourceType for the renderCondition node for this tab
	 * 
	 */
	public String renderConditionResourceType() default "";

	/**
	 * Applicable to Touch-UI only
	 * 
	 * Defines additional properties to be added to the renderCondition node
	 * 
	 * These will only be added if renderConditionResourceType is not empty
	 * 
	 */
	public Property[] renderConditionProperties() default {};
}
