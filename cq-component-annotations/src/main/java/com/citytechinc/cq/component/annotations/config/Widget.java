package com.citytechinc.cq.component.annotations.config;

import java.lang.annotation.Annotation;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import com.citytechinc.cq.component.dialog.maker.AbstractWidgetMaker;

@Retention(RetentionPolicy.CLASS)
@Target(ElementType.TYPE)
public @interface Widget {
	Class<? extends Annotation>[] annotationClass() default {};

	Class<? extends AbstractWidgetMaker> makerClass();

	String[] xtypes();
}
