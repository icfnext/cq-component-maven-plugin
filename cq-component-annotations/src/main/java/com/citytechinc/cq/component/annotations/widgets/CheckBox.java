package com.citytechinc.cq.component.annotations.widgets;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * Represents a Widget of type CQ.Ext.form.Checkbox
 *
 */
@Retention(RetentionPolicy.CLASS)
@Target({ ElementType.FIELD, ElementType.METHOD })
public @interface CheckBox {

    /**
     * The value that should go into the generated input element's value attribute
     *
     * @return String
     */
	String inputValue() default "on";

	/**
	 * true if the checkbox should render initially checked
	 *
	 * @return boolean
	 */
	boolean checked() default false;
}
