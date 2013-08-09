package com.citytechinc.cq.component.annotations.widgets;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * Represents a Widget of type CQ.Ext.form.DateField
 *
 */
@Retention(RetentionPolicy.CLASS)
@Target({ ElementType.FIELD, ElementType.METHOD })
public @interface DateField {

    /**
     * Day index at which the week should begin, 0-based
     *
     * @return int
     */
	int startDay() default 0;

	/**
	 * false to hide the footer area of the DatePicker containing the Today button and disable the keyboard handler for spacebar that selects the current date
	 *
	 * @return boolean
	 */
	boolean showToday() default true;

	/**
	 * The default date format string which can be overriden for localization support. The format must be valid according to <a href="http://dev.day.com/docs/en/cq/5-6/widgets-api/output/Date.html#parseDate">Date.parseDate</a>
	 *
	 * @return String
	 */
	String format() default "m/d/Y";
}
