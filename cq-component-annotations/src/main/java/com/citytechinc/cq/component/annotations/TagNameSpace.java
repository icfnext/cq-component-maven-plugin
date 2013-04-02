package com.citytechinc.cq.component.annotations;

public @interface TagNameSpace {
	String value();
	int maximum() default -1;
}
