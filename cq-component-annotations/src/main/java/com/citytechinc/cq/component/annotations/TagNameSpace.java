package com.citytechinc.cq.component.annotations;

/**
 *
 * @see com.citytechinc.cq.component.annotations.widgets.TagInputField
 */
public @interface TagNameSpace {
	String value();

	int maximum() default -1;
}
