/**
 *    Copyright 2017 ICF Olson
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
 * Represents a Widget of type granite/ui/components/foundation/form/switch in
 * Touch UI
 */
@Retention(RetentionPolicy.CLASS)
@Target({ ElementType.FIELD, ElementType.METHOD })
public @interface Switch {
	/**
	 * The text for “On”.
	 *
	 * @return String
	 */
	String offText() default "";

	/**
	 * The text for “Off”.
	 *
	 * @return String
	 */
	String onText() default "";

	/**
	 * Indicates if the checkbox is checked. Providing checked property (either
	 * true or false) will imply ignoreData to be true.
	 * 
	 * The only valid values are "true" and "false"
	 *
	 * @return String
	 */
	String checked() default "";

	/**
	 * If false, the checked status is based on matching the form values by name
	 * and value properties. Otherwise, the form values are not matched, and the
	 * checked status is based on checked property specified.
	 *
	 * @return boolean
	 */
	boolean ignoreData() default false;
}
