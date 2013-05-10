package com.citytechinc.cq.component.annotations.widgets;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Retention(RetentionPolicy.CLASS)
@Target(ElementType.FIELD)
public @interface NumberField {
	boolean allowDecimals() default true;

	boolean allowNegative() default true;

	int decimalPrecision() default 2;

	String decimalSeparator() default ".";
}
