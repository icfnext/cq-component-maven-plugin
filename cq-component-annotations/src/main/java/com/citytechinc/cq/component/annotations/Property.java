package com.citytechinc.cq.component.annotations;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * Defines a property to be written to a dialog widget's XML node in the
 * Component's dialog.xml.
 */
@Retention(RetentionPolicy.CLASS)
@Target({ ElementType.FIELD, ElementType.METHOD })
public @interface Property {

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
	
	enum RenderValue {
		BOTH, CLASSIC, TOUCH
	}
	
	/**
	 * When used in DialogField.additionalProperties this field will determine 
	 * whether this property should be rendered for touch UI, classic UI, or both.
	 *
	 * @return RenderValue
	 */
	RenderValue renderIn() default RenderValue.BOTH;

}
