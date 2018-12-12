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
