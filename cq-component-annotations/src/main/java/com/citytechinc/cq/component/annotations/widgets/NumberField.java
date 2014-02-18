/**
 *    Copyright 2013 CITYTECH, Inc.
 *
 *    Licensed under the Apache License, Version 2.0 (the "License");
 *    you may not use this file except in compliance with the License.
 *    You may obtain a copy of the License at
 *
 *        http://www.apache.org/licenses/LICENSE-2.0
 *
 *    Unless required by applicable law or agreed to in writing, software
 *    distributed under the License is distributed on an "AS IS" BASIS,
 *    WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *    See the License for the specific language governing permissions and
 *    limitations under the License.
 */
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
	boolean allowDecimals() default ALLOW_DECIMALS_DEFAULT;

	/**
	 * False to prevent entering a negative sign
	 * 
	 * @return boolean
	 */
	boolean allowNegative() default ALLOW_NEGATIVE_DEFAULT;

	/**
	 * The maximum precision to display after the decimal separator
	 * 
	 * @return int
	 */
	int decimalPrecision() default DECIMAL_PRECISION_DEFAULT;

	/**
	 * Character(s) to allow as the decimal separator
	 * 
	 * @return String
	 */
	String decimalSeparator() default DECIMAL_SEPARATOR_DEFAULT;
}
