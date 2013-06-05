package com.citytechinc.cq.component.annotations.widgets;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Retention(RetentionPolicy.CLASS)
@Target({ ElementType.FIELD, ElementType.METHOD })
public @interface MultiField {

	public static final String ADD_ITEM_LABEL_DEFAULT = "Add Item";
	public static final boolean ORDERABLE_DEFAULT = true;

	String addItemLabel() default ADD_ITEM_LABEL_DEFAULT;

	boolean orderable() default ORDERABLE_DEFAULT;

}
