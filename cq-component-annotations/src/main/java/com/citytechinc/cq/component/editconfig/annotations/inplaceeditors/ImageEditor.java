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
