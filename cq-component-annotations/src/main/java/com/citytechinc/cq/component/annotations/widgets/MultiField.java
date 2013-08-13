package com.citytechinc.cq.component.annotations.widgets;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * Represents a Widget of type CQ.form.MultiField
 *
 */
@Retention(RetentionPolicy.CLASS)
@Target({ ElementType.FIELD, ElementType.METHOD })
public @interface MultiField {

	public static final String ADD_ITEM_LABEL_DEFAULT = "Add Item";
	public static final boolean ORDERABLE_DEFAULT = true;

	/**
	 * The label to display for the addItem control.
	 *
	 * @return String
	 */
	String addItemLabel() default ADD_ITEM_LABEL_DEFAULT;

	/**
	 * If the list of fields should be orderable and Up/Down buttons are rendered
	 *
	 * @return boolean
	 */
	boolean orderable() default ORDERABLE_DEFAULT;

}
