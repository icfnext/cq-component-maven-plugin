package com.citytechinc.cq.component.annotations;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * Used to flag a field that represents a child resource for the composite Multifield.
 * Used for Touch UI Coral3 Only
 */
@Retention(RetentionPolicy.CLASS)
@Target({ ElementType.FIELD })
public @interface MultiFieldChildResource {

}
