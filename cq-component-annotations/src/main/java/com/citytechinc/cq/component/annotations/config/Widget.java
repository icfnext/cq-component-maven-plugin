package com.citytechinc.cq.component.annotations.config;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Retention(RetentionPolicy.CLASS)
@Target(ElementType.TYPE)
public @interface Widget {
	String annotationClass() default "";

	String makerClass();

	String[] xtypes();
}
