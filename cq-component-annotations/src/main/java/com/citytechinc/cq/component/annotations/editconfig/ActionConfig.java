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
package com.citytechinc.cq.component.annotations.editconfig;

/**
 * Defines a single Action Configuration for a Component. For more information
 * on using Action Configurations see the <a href=
 * "http://dev.day.com/docs/en/cq/current/developing/components/edit_config.html#cq:actionConfigs"
 * >Configuring Edit Behavior Documentation</a>.
 *
 */
public @interface ActionConfig {

	/**
	 * The xtype to use for the Action Configuration.
	 *
	 * @return String
	 */
	String xtype() default "";

	/**
	 * The handler for the Action Configuration.
	 *
	 * @return String
	 */
	String handler() default "";

	/**
	 * The text to associate with the action.
	 *
	 * @return String
	 */
	String text() default "";

	/**
	 * A set of properties which are written to the Action Configuration xml
	 * node.
	 *
	 * @return ActionConfigProperty[]
	 */
	ActionConfigProperty[] additionalProperties() default {};
}
