package com.citytechinc.cq.component.annotations.widgets;

public @interface MultiField {
	String addItemLabel() default "Add Item";

	boolean orderable() default true;

}
