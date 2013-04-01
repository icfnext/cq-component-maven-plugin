package com.citytechinc.cq.component.annotations.config;

public @interface Widget {
	String annotationClass() default "";
	String makerClass();
	String[] xtypes();
}
