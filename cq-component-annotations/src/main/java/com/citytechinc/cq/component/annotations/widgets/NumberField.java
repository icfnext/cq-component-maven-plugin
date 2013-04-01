package com.citytechinc.cq.component.annotations.widgets;

public @interface NumberField {
	boolean allowDecimals() default true;
	boolean allowNegative() default true;
	int decimalPrecision() default 2;
	String decimalSeparator() default ".";
}
