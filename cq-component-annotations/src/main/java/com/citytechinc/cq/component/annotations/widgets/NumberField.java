package com.citytechinc.cq.component.annotations.widgets;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * Represents a Widget of type CQ.Ext.form.NumberField in Classic UI
 * <p>
 * Represents a Widget of type granite/ui/components/foundation/form/numberfield
 * in Touch UI
 */
@Retention(RetentionPolicy.CLASS)
@Target({ ElementType.FIELD, ElementType.METHOD })
public @interface NumberField {

    boolean ALLOW_DECIMALS_DEFAULT = true;

    boolean ALLOW_NEGATIVE_DEFAULT = true;

    int DECIMAL_PRECISION_DEFAULT = 2;

    String DECIMAL_SEPARATOR_DEFAULT = ".";

    /**
     * Used for Classic UI only
     * <p>
     * False to disallow decimal values
     *
     * @return boolean
     */
    boolean allowDecimals() default ALLOW_DECIMALS_DEFAULT;

    /**
     * False to prevent entering a negative sign
     * <p>
     * In Touch UI, if no min is set, setting allowNegative to false will set
     * min to 0.0.
     *
     * @return boolean
     */
    boolean allowNegative() default ALLOW_NEGATIVE_DEFAULT;

    /**
     * Used for Classic UI only
     * <p>
     * The maximum precision to display after the decimal separator
     *
     * @return int
     */
    int decimalPrecision() default DECIMAL_PRECISION_DEFAULT;

    /**
     * Character(s) to allow as the decimal separator
     *
     * @return String
     */
    String decimalSeparator() default DECIMAL_SEPARATOR_DEFAULT;

    /**
     * Used for Touch UI Only
     * <p>
     * Indicates the minimum allowed value in the field
     *
     * @return String
     */
    String min() default "";

    /**
     * Used for Touch UI Only
     * <p>
     * Indicates the maximum allowed value in the field
     *
     * @return String
     */
    String max() default "";

    /**
     * Used for Touch UI Only
     * <p>
     * Indicates the valid numeric increments for values in the field
     * <p>
     * Defaults to 1 - <em>NOTE</em> setting this to anything other than 1
     * really does not work due to Javascripts floating point arithmetic
     * limitations.
     *
     * @return String
     */
    double step() default 1;

    /**
     * Used for Coral 3 Touch UI Only
     * <p>
     * The value of SlingPostServlet @TypeHint
     *
     * @return String
     */
    String typeHint() default "";
}
