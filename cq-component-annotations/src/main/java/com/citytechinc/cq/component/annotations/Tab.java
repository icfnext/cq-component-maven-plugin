package com.citytechinc.cq.component.annotations;

public @interface Tab {
	String title() default "";

	String path() default "";
}
