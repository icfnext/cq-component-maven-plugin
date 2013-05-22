package com.citytechinc.cq.component.annotations.widgets;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Retention(RetentionPolicy.CLASS)
@Target({ElementType.FIELD,ElementType.METHOD})
public @interface SizeField {

	public String heightParameter() default "./height";

	public String heightPrefix() default "x";

	public String heightSuffix() default "px";

	public String widthParameter() default "./width";

	public String widthPrefix() default "";

	public String widthSuffix() default "";

	public int fieldWidth() default 40;

}
