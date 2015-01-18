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
 * Represents a Widget of type CQ.Ext.form.Checkbox in Classic UI
 *
 * Represents a Widget of type granite/ui/components/foundation/form/checkbox in Touch UI
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
	 * true if the checkbox should render initially checked
	 *
	 * @return boolean
	 */
	boolean checked() default false;

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
