package com.citytechinc.cq.component.annotations.widgets;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import com.citytechinc.cq.component.annotations.Property;

@Retention(RetentionPolicy.CLASS)
@Target({ ElementType.FIELD, ElementType.METHOD })
public @interface AutoComplete {
	boolean multiple() default false;

	String mode() default "contains";

	String datasourceResourceType() default "";

	String valuesResourceType() default "";

	String optionsResourceType() default "";

	Property[] datasourceProperties() default {};
}
