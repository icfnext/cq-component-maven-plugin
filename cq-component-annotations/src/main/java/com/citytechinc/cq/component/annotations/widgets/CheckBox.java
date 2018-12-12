package com.citytechinc.cq.component.annotations.widgets;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * Represents a Widget of type CQ.Ext.form.Checkbox in Classic UI
 *
 * Represents a Widget of type granite/ui/components/foundation/form/checkbox in
 * Touch UI
 */
@Retention(RetentionPolicy.CLASS)
@Target({ ElementType.FIELD, ElementType.METHOD })
public @interface CheckBox {

	/**
	 * Used for Classic UI only
	 *
	 * The value that should go into the generated input element's value
	 * attribute
	 *
	 * @return String
	 */
	String inputValue() default "on";

	/**
	 * Used for Classis UI only
	 * 
	 * True if the checkbox should render initially checked
	 *
	 * @return boolean
	 */
	boolean checked() default false;
	
	/**
	 * Used for Touch UI Only
	 * 
	 * Indicates if the checkbox is checked. This will emply ignoreData to be true.
	 * To set this to be true or false add a boolean with the desired value. To not
	 * set this at all (and thereby cause ignoreData to remain false) dont set this
	 * field, or suppy an empoty array.
	 *
	 * @return boolean
	 */
	boolean[] touchUIChecked() default {};

	/**
	 * Used for Touch UI only
	 *
	 * @return String
	 */
	String text() default "";

	/**
	 * Used for Touch UI only
	 *
	 * @return String
	 */
	String title() default "";
}
