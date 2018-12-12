package com.citytechinc.cq.component.annotations.widgets.rte;

/**
 * @see com.citytechinc.cq.component.annotations.widgets.RichTextEditor#format()
 */
public @interface Format {

    boolean bold() default true;

    boolean italic() default true;

    boolean underline() default true;

}
