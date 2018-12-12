package com.citytechinc.cq.component.annotations;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * The DialogFieldOverride annotation is used to override settings from a
 * DialogField annotation on an interface or overriden method.
 */
@Retention(RetentionPolicy.CLASS)
@Target({ ElementType.FIELD, ElementType.METHOD })
public @interface DialogFieldOverride {

    /**
     * The path to which the value for the authorable element will be saved
     * during content authoring.
     *
     * @return String
     */
    String name() default "";

    /**
     * The human-readable label for the input field which will be presented in a
     * dialog.
     *
     * @return String
     */
    String fieldLabel() default "";

    /**
     * Populates the fieldDescription widget property in the dialog.
     *
     * @return String
     */
    String fieldDescription() default "";

    /**
     * Indicates that population of the widget input in the dialog is required.
     * Used to set the allowBlank widget property in the dialog. Unlike
     * DialogField this is required because we can't default to the value set on
     * DialogField
     *
     * @return boolean
     */
    boolean required();

    /**
     * Used to set the hideLabel widget property in the dialog. Unlike
     * DialogField this is required because we can't default to the value set on
     * DialogField
     *
     * @return boolean
     */
    boolean hideLabel();

    /**
     * Used to set the defaultValue widget property in the dialog.
     *
     * @return String
     */
    String defaultValue() default "";

    /**
     * The index number, starting at 1, of the tab in which to place the dialog
     * widget representing this authorable element.
     *
     * @return int
     */
    int tab() default 1;

    /**
     * A list of additional properties not already represented by properties of
     * the annotation. Each additional property is output as a widget property
     * in the dialog.
     *
     * @return FieldProperty[]
     */
    Property[] additionalProperties() default {};

    /**
     * If this is set to true, the instead of overriding the additional
     * properties, the additional properties from the parent will be merged with
     * the ones from this annotation.
     */
    boolean mergeAdditionalProperties() default false;

    /**
     * The set of listeners which will be attributed to the dialog widget
     * associated with the authorable element. Listeners are output as
     * properties in the listeners XML node which is a child of the XML node
     * representing the widget in the dialog.
     *
     * @return Listener[]
     */
    Listener[] listeners() default {};

    /**
     * If this is set to true, the instead of overriding the listeners, the
     * listeners from the parent will be merged with the ones from this
     * annotation.
     */
    boolean mergeLiseners() default false;

    /**
     * Used to order dialog widgets within a tab. Widgets representing
     * authorable elements with a higher ranking appear lower in the tab than
     * elements with a lower ranking. The ordering of elements of equal ranking
     * is not guaranteed.
     *
     * @return double
     */
    double ranking() default 0;

    /**
     * For Touch-UI Only
     * <p>
     * Many of the Touch UI widgets react to an underlying value being set for
     * the field.
     *
     * @return String
     */
    String value() default "";

    /**
     * For Touch-UI Only
     *
     * @return String
     */
    String title() default "";

    /**
     * For Touch-UI Only
     * <p>
     * Indicates that the particular field should be rendered in a disabled
     * state
     *
     * @return boolean
     */
    boolean disabled() default false;

    /**
     * For Touch-UI Only
     * <p>
     * Additional css classes to attribute to the rendered field
     *
     * @return String
     */
    String cssClass() default "";

    /**
     * Indicates that a Touch UI widget should not be produced for the annotated
     * member
     *
     * @return boolean
     */
    boolean suppressTouchUI() default false;
}
