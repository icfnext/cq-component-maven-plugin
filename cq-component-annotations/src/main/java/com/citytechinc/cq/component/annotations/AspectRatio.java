package com.citytechinc.cq.component.annotations;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Retention(RetentionPolicy.CLASS)
@Target({ ElementType.FIELD, ElementType.METHOD })
public @interface AspectRatio {
	/**
	 * The text to display for the aspect ratio
	 * 
	 * @return String
	 */
	String text();

	/**
	 * The width part of the aspect ratio
	 * 
	 * @return int
	 */
	int width();

	/**
	 * The height part of the aspect ratio
	 * 
	 * @return int
	 */
	int height();
}
