package com.citytechinc.cq.component.annotations.widgets;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Retention(RetentionPolicy.CLASS)
@Target({ ElementType.FIELD, ElementType.METHOD })
public @interface DialogFieldSet {
	boolean collapseFirst() default true;

	boolean collapsible() default false;

	boolean collapsed() default false;

	boolean border() default true;

	String title() default "";
}
