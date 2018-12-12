package com.citytechinc.cq.component.annotations.widgets;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * Represents a Widget of type CQ.form.DateTime in Classic UI
 *
 * Represents a Widget of type granite/ui/components/foundation/form/datepicker
 * in Touch UI
 */
@Retention(RetentionPolicy.CLASS)
@Target({ ElementType.FIELD, ElementType.METHOD })
public @interface DateTime {

	/**
	 * For Touch-UI only
	 *
	 * Similar to format but uses a different standard for specifying date
	 * formats.
	 *
	 * @return String
	 */
	String storedFormat() default "";

	/**
	 * For Touch-UI Coral3 only
	 *
	 * Similar to format but uses a different standard for specifying date
	 * formats.
	 *
	 * @return String
	 */
	String valueFormat() default "YYYY-MM-DD[T]HH:mm:ss.000Z";

	/**
	 * For Touch-UI only
	 *
	 * Display format for the date selected
	 *
	 * @return String
	 */
	String displayedFormat() default "";

	/**
	 * For Touch-UI only
	 *
	 * The minimum selectable date
	 *
	 * @return String
	 */
	String minDate() default "";

	/**
	 * For Touch-UI only
	 *
	 * The maximum selectable date
	 *
	 * @return String
	 */
	String maxDate() default "";

}
