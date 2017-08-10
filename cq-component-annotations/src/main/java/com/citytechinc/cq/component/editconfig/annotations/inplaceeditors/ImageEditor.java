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
package com.citytechinc.cq.component.editconfig.annotations.inplaceeditors;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import com.citytechinc.cq.component.annotations.AspectRatio;

@Retention(RetentionPolicy.CLASS)
@Target({ ElementType.FIELD, ElementType.METHOD })
public @interface ImageEditor {

	/**
	 * The title for image editor.
	 *
	 * @return String
	 */
	String title() default "";

	/**
	 * Whether or not the crop plugin is enabled
	 *
	 * @return boolean
	 */
	boolean enableCrop() default true;

	/**
	 * Whether or not the rotate plugin is enabled
	 *
	 * @return boolean
	 */
	boolean enableRotate() default true;

	/**
	 * Whether or not the map plugin is enabled
	 *
	 * @return boolean
	 */
	boolean enableMap() default true;

	/**
	 * Aspect Ratios for the crop plugin
	 *
	 * @return AspectRatio[]
	 */
	AspectRatio[] cropAspectRatios() default {};
}
