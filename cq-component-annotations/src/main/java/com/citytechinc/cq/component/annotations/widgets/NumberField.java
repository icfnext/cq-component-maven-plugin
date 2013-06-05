package com.citytechinc.cq.component.annotations.widgets;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Retention(RetentionPolicy.CLASS)
@Target({ ElementType.FIELD, ElementType.METHOD })
public @interface NumberField {

	public static final boolean ALLOW_DECIMALS_DEFAULT = true;
	public static final boolean ALLOW_NEGATIVE_DEFAULT = true;
	public static final int DECIMAL_PRECISION_DEFAULT = 2;
	public static final String DECIMAL_SEPARATOR_DEFAULT = ".";

	boolean allowDecimals() default true;

	boolean allowNegative() default true;

	int decimalPrecision() default 2;

	String decimalSeparator() default ".";
}
