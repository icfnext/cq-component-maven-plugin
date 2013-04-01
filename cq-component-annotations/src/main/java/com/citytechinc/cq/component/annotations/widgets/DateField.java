package com.citytechinc.cq.component.annotations.widgets;

public @interface DateField {
	int startDay() default 0;
	boolean showToday() default true;
	String format() default "m/d/Y";
}
