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
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.FIELD)
public @interface DialogField {

	public enum BooleanEnum {
		TRUE,
		FALSE
	}

	public enum SelectionType {
		SELECT,
		RADIO,
		CHECKBOX,
		COMBOBOX
	}

	/**
	 * Allows for explicit definition of a dialog xType associated with the field annotated.
	 * If this property is not present in the annotation, a reasonable default xtype may be chosen.
	 *
	 * @return
	 */
	public String xtype() default "";

	/**
	 * The name is the path to the configurable property relative to the related resource.
	 *
	 * @return
	 */
	public String name() default "";

	/**
	 * The human-readable label for the input field which will be presented in a dialog
	 *
	 * @return
	 */
	public String fieldLabel() default "";

	/**
	 * While not technically part of the dialog property, the field name can be used to define a unique
	 * name for the dialog property within your dialog.
	 *
	 * @return
	 */
	public String fieldName() default "";

	public String fieldDescription() default "";

	public BooleanEnum required() default BooleanEnum.FALSE;

	/**
	 * The title of the tab in which to place the widget.
	 *
	 * @return
	 */
	public String tab() default "";

	/**
	 * The weight of the property within the context of its containing tab.  Properties with a greater weight will
	 * "sink" to the bottom of the tab while others will "rise" to the top.  If the weight of two properties are equal,
	 * no guarantees are made concerning ordering between the two.
	 *
	 * @return
	 */
	public int weight() default 0;

	/**
	 * When specifying a selection xtype, this annotation property can be used to specify the valid
	 * Options of the field.
	 *
	 * @return
	 */
	public Option[] selectionOptions() default {};

	/**
	 * When specifying a selection xtype, this annotation property can be used to specify the type
	 * of selection to be used.  Valid types are:
	 *
	 * <ul>
	 *   <li>select</li>
	 *   <li>radio</li>
	 *   <li>checkbox</li>
	 *   <li>combobox</li>
	 * </ul>
	 *
	 * @return
	 */
	public SelectionType selectionType() default SelectionType.SELECT;

	public FieldConfig[] fieldConfigs() default {};

	/**
	 * A list of additional properties not already represented by properties of the annotation
	 *
	 * @return
	 */
	public FieldProperty[] additionalProperties() default {};
}
