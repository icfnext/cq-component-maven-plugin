package com.citytechinc.cq.component.annotations;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * To be used when specifying an option for a selection property type.
 */
@Retention(RetentionPolicy.CLASS)
@Target({ ElementType.FIELD, ElementType.METHOD })
public @interface Option {

    /**
     * Author facing text for the selection option.
     *
     * @return String
     */
	String text() default "";

	/**
	 * Value of the selection option.
	 *
	 * @return String
	 */
	String value() default "";

	/**
	 * QTip of the selection option.
	 *
	 * @return String
	 */
	String qtip() default "";

}
