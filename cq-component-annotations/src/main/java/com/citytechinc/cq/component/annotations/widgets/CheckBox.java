package com.citytechinc.cq.component.annotations.widgets;

public @interface CheckBox {
	String inputValue() default "on";

	boolean checked() default false;
}
