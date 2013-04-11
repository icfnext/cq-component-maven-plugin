package com.citytechinc.cq.component.annotations;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * Indicates that a Field is defined by a CQ Dialog input
 *
 * @author paulmichelotti
 *
 */
@Retention(RetentionPolicy.CLASS)
@Target(ElementType.FIELD)
public @interface DialogField {

	/**
	 * Allows for explicit definition of a dialog xType associated with the
	 * field annotated. If this property is not present in the annotation, a
	 * reasonable default xtype may be chosen.
	 *
	 *
	 * @return String
	 */
	public String xtype() default "";

	/**
	 * The name is the path to the configurable property relative to the related
	 * resource.
	 *
	 *
	 * @return String
	 */
	public String name() default "";

	/**
	 * The human-readable label for the input field which will be presented in a
	 * dialog
	 *
	 *
	 * @return String
	 */
	public String fieldLabel() default "";

	/**
	 * While not technically part of the dialog property, the field name can be
	 * used to define a unique name for the dialog property within your dialog.
	 *
	 *
	 * @return String
	 */
	public String fieldName() default "";

	/**
	 * The fieldDescription field for the widhet
	 *
	 * @return String
	 */
	public String fieldDescription() default "";

	/**
	 * boolean to show this widget is required to be filled out (required =true
	 * is equivalent to allowBlank=false)
	 *
	 * @return boolean
	 */
	public boolean required() default false;

	/**
	 * The hideLabel field for the widget
	 *
	 * @return boolean
	 */
	public boolean hideLabel() default false;

	/**
	 * The defaultValue field for the widget
	 *
	 * @return String
	 */
	public String defaultValue() default "";

	/**
	 * The title of the tab in which to place the widget.
	 *
	 *
	 * @return String
	 */
	public String tab() default "";

	/**
	 * Method fieldConfigs.
	 *
	 * @return FieldConfig[]
	 */
	public FieldConfig[] fieldConfigs() default {};

	/**
	 * A list of additional properties not already represented by properties of
	 * the annotation
	 *
	 *
	 * @return FieldProperty[]
	 */
	public FieldProperty[] additionalProperties() default {};
}
