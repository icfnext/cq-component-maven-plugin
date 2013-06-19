package com.citytechinc.cq.component.annotations.editconfig;

public @interface ActionConfig {
	String xtype() default "";

	String handler() default "";

	String text() default "";

	ActionConfigProperty[] additionalProperties() default {};
}
