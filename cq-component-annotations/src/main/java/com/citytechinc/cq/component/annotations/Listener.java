package com.citytechinc.cq.component.annotations;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * Identifies a single listener to be associated with a dialog widget.
 */
@Retention(RetentionPolicy.CLASS)
@Target({ ElementType.FIELD, ElementType.METHOD })
public @interface Listener {

    /**
     * Name of the listener property
     *
     * @return String
     */
    String name();

    /**
     * Value of the listener property
     *
     * @return String
     */
    String value();
}
