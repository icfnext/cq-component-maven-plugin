package com.citytechinc.cq.component.annotations.widgets.rte;

/**
 * @see com.citytechinc.cq.component.annotations.widgets.RichTextEditor#subsuperscript()
 */
public @interface SubSuperscript {

    boolean subscript() default true;

    boolean superscript() default true;

}
