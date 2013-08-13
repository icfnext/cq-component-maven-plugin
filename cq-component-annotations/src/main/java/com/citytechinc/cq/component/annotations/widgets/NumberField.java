package com.citytechinc.cq.component.annotations.widgets;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * Represents a Widget of type CQ.Ext.form.NumberField
 *
 */
@Retention(RetentionPolicy.CLASS)
@Target({ ElementType.FIELD, ElementType.METHOD })
public @interface NumberField {

	public static final boolean ALLOW_DECIMALS_DEFAULT = true;
	public static final boolean ALLOW_NEGATIVE_DEFAULT = true;
	public static final int DECIMAL_PRECISION_DEFAULT = 2;
	public static final String DECIMAL_SEPARATOR_DEFAULT = ".";

	/**
	 * False to disallow decimal values
	 *
	 * @return boolean
	 */
	boolean allowDecimals() default true;

	/**
	 * False to prevent entering a negative sign
	 *
	 * @return boolean
	 */
	boolean allowNegative() default true;

	/**
	 * The maximum precision to display after the decimal separator
	 *
	 * @return int
	 */
	int decimalPrecision() default 2;

	/**
	 * Character(s) to allow as the decimal separator
	 *
	 * @return String
	 */
	String decimalSeparator() default ".";
}
