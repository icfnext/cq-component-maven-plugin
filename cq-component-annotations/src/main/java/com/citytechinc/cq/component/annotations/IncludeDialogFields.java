package com.citytechinc.cq.component.annotations;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * Field annotation to include dialog fields on nested objects, i.e. to support composition of component classes as an
 * alternative to inheritance.
 */
@Retention(RetentionPolicy.CLASS)
@Target(ElementType.FIELD)
public @interface IncludeDialogFields {

}
