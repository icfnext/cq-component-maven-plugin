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
 * Represents a Widget of type CQ.form.SizeField
 *
 */
@Retention(RetentionPolicy.CLASS)
@Target({ ElementType.FIELD, ElementType.METHOD })
public @interface SizeField {

	/**
	 * The name of the height parameter.
	 *
	 * @return String
	 */
	public String heightParameter() default "./height";

	/**
	 * The string to add before the height field.
	 *
	 * @return String
	 */
	public String heightPrefix() default "x";

	/**
	 * The string to add after the height field.
	 *
	 * @return String
	 */
	public String heightSuffix() default "px";

	/**
	 * The name of the width parameter.
	 *
	 * @return String
	 */
	public String widthParameter() default "./width";

	/**
	 * The string to add before the width field.
	 *
	 * @return String
	 */
	public String widthPrefix() default "";

	/**
	 * The string to add after the width field.
	 *
	 * @return String
	 */
	public String widthSuffix() default "";

	/**
	 * The width of the fields in pixels.
	 *
	 * @return int
	 */
	public int fieldWidth() default 40;

}
