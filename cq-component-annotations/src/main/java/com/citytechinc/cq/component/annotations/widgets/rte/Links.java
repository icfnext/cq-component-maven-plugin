package com.citytechinc.cq.component.annotations.widgets.rte;

/**
 * @see com.citytechinc.cq.component.annotations.widgets.RichTextEditor#links()
 */
public @interface Links {

    boolean modifylink() default true;

    boolean unlink() default true;

    boolean anchor() default true;

}
