package com.citytechinc.cq.component.annotations.widgets;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * Represents a Widget of type CQ.form.MultiField in Classic UI
 * <p>
 * Represents a Widget of type granite/ui/components/foundation/form/multifield
 * in Touch UI
 */
@Retention(RetentionPolicy.CLASS)
@Target({ ElementType.FIELD, ElementType.METHOD })
public @interface MultiField {

    String ADD_ITEM_LABEL_DEFAULT = "Add Item";

    boolean ORDERABLE_DEFAULT = true;

    /**
     * Used for Classic UI only
     * <p>
     * The label to display for the addItem control.
     *
     * @return String
     */
    String addItemLabel() default ADD_ITEM_LABEL_DEFAULT;

    /**
     * Used for Classic UI only
     * <p>
     * If the list of fields should be orderable and Up/Down buttons are
     * rendered
     *
     * @return boolean
     */
    boolean orderable() default ORDERABLE_DEFAULT;

    /**
     * Used for Touch UI Coral3 Only
     * <p>
     * Set true to handle the form content value as composite.
     *
     * @return boolean
     */
    boolean composite() default false;
}