package com.citytechinc.cq.component.annotations;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * To be used when specifying an option for a selection property type.
 *
 * @author paulmichelotti
 *
 */
@Retention(RetentionPolicy.CLASS)
@Target(ElementType.FIELD)
public @interface Option {

	String text() default "";

	String value() default "";

	String qtip() default "";

}
