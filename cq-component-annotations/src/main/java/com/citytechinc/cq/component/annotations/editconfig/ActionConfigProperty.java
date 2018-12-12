package com.citytechinc.cq.component.annotations.editconfig;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * Defines a property to be written to an Action Configuration in a Component's
 * _cq_editConfig.xml.
 */
@Retention(RetentionPolicy.CLASS)
@Target({ ElementType.TYPE })
public @interface ActionConfigProperty {

    /**
     * The name of the property on the action config
     *
     * @return String
     */
    String name();

    /**
     * The value of the property on the field
     *
     * @return String
     */
    String value();

}
