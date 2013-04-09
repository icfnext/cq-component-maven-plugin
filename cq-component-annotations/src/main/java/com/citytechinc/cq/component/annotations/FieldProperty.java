package com.citytechinc.cq.component.annotations;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 */
@Retention(RetentionPolicy.CLASS)
@Target(ElementType.FIELD)
public @interface FieldProperty {

	/**
	 * The name of the property on the field
	 * 
	 * @return String
	 */
	String name();

	/**
	 * The value of the property on the field
	 * 
	 * @return String
	 */
	String value();

}
