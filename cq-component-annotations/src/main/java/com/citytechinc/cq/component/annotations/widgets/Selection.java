package com.citytechinc.cq.component.annotations.widgets;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import com.citytechinc.cq.component.annotations.Option;

/**
 * Specifies a selection type Widget.  A selection may be one of
 *
 * <ol>
 *   <li>select</li>
 *   <li>radio</li>
 *   <li>checkbox</li>
 *   <li>combobox</li>
 * </ol>
 *
 * @author paulmichelotti
 *
 */
@Retention(RetentionPolicy.CLASS)
@Target(ElementType.FIELD)
public @interface Selection {
	public static final String SELECT = "select";
	public static final String RADIO = "radio";
	public static final String CHECKBOX = "checkbox";
	public static final String COMBOBOX = "combobox";

	public static final String ASC_SORT_DIR = "ASC";
	public static final String DESC_SORT_DIR = "DESC";

	/**
	 * An explicitly enumerated set of options for the selection.  Note: if your options are returned dynamically via
	 * a URL, use the optionsUrl property.
	 *
	 * @return
	 */
	public Option[] options() default {};

	/**
	 * The URL from which options are to be pulled.  Note: setting this to anything other than an empty string overrides
	 * any settings made in the options property.
	 *
	 * @return
	 */
	public String optionsUrl() default "";

	public String optionsProvider() default "";

	public String sortDir() default "";

	public String type() default RADIO;
}
