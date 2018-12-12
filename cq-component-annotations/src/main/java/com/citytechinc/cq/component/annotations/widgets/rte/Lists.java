package com.citytechinc.cq.component.annotations.widgets.rte;

/**
 * @see com.citytechinc.cq.component.annotations.widgets.RichTextEditor#lists()
 */
public @interface Lists {

    boolean ordered() default true;

    boolean unordered() default true;

    boolean indent() default true;

    boolean outdent() default true;

}
