package com.citytechinc.cq.component.annotations.widgets;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * Represents a Widget of type CQ.form.SizeField
 */
@Retention(RetentionPolicy.CLASS)
@Target({ ElementType.FIELD, ElementType.METHOD })
public @interface SizeField {

    /**
     * The name of the height parameter.
     *
     * @return String
     */
    String heightParameter() default "./height";

    /**
     * The string to add before the height field.
     *
     * @return String
     */
    String heightPrefix() default "x";

    /**
     * The string to add after the height field.
     *
     * @return String
     */
    String heightSuffix() default "px";

    /**
     * The name of the width parameter.
     *
     * @return String
     */
    String widthParameter() default "./width";

    /**
     * The string to add before the width field.
     *
     * @return String
     */
    String widthPrefix() default "";

    /**
     * The string to add after the width field.
     *
     * @return String
     */
    String widthSuffix() default "";

    /**
     * The width of the fields in pixels.
     *
     * @return int
     */
    int fieldWidth() default 40;

}
