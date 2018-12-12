package com.citytechinc.cq.component.annotations;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * Applicable to the Touch UI only
 * <p>
 * Hides dialog fields during the resource merger of the current dialog and the dialog of a resourceSuperType
 */
@Retention(RetentionPolicy.CLASS)
@Target({ ElementType.METHOD })
public @interface HideDialogField {

}
