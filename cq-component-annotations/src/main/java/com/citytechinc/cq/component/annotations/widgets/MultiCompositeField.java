package com.citytechinc.cq.component.annotations.widgets;

public @interface MultiCompositeField {
	boolean matchBaseName() default false;

	String prefix() default "./";
}
