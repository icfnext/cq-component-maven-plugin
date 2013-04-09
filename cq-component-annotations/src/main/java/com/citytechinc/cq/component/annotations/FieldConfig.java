package com.citytechinc.cq.component.annotations;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * @author paulmichelotti
 */
@Retention(RetentionPolicy.CLASS)
@Target(ElementType.FIELD)
public @interface FieldConfig {

	/**
	 * The xtype for the field
	 * 
	 * @return String
	 */
	public String xtype();

}
