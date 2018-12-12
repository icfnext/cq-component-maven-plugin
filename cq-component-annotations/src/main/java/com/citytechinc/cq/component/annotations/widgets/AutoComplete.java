package com.citytechinc.cq.component.annotations.widgets;

import com.citytechinc.cq.component.annotations.Property;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Retention(RetentionPolicy.CLASS)
@Target({ ElementType.FIELD, ElementType.METHOD })
public @interface AutoComplete {

    boolean multiple() default false;

    String mode() default "contains";

    String datasourceResourceType() default "";

    String valuesResourceType() default "";

    String optionsResourceType() default "";

    Property[] datasourceProperties() default {};

    /**
     * Used for Touch UI Coral3 Only
     * <p>
     * Indicates if the user is forced to select only from the available choices.
     *
     * @return boolean
     */
    boolean forceSelection() default false;
}