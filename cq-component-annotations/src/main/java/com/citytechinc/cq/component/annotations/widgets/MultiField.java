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
 * Represents a Widget of type CQ.form.MultiField
 *
 */
@Retention(RetentionPolicy.CLASS)
@Target({ ElementType.FIELD, ElementType.METHOD })
public @interface MultiField {

	public static final String ADD_ITEM_LABEL_DEFAULT = "Add Item";
	public static final boolean ORDERABLE_DEFAULT = true;

	/**
	 * The label to display for the addItem control.
	 *
	 * @return String
	 */
	String addItemLabel() default ADD_ITEM_LABEL_DEFAULT;

	/**
	 * If the list of fields should be orderable and Up/Down buttons are rendered
	 *
	 * @return boolean
	 */
	boolean orderable() default ORDERABLE_DEFAULT;

}
