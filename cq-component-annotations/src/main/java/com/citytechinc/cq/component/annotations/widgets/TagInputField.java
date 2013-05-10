package com.citytechinc.cq.component.annotations.widgets;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import com.citytechinc.cq.component.annotations.TagNameSpace;

@Retention(RetentionPolicy.CLASS)
@Target(ElementType.FIELD)
public @interface TagInputField {
	boolean displayTitles() default true;

	TagNameSpace[] namespaces() default {};
}
