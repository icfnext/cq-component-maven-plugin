package com.citytechinc.cq.component.annotations.widgets;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import com.citytechinc.cq.component.annotations.Option;


@Retention(RetentionPolicy.CLASS)
@Target(ElementType.FIELD)
public @interface Selection {
	public static final String SELECT="select";
	public static final String RADIO="radio";
	public static final String CHECKBOX="checkbox";
	public static final String COMBOBOX="combobox";
	
	
	public Option[] options() default {};

	public String type() default SELECT;
}
