package com.citytechinc.cq.component.annotations.widgets.rte;

/**
 * @see com.citytechinc.cq.component.annotations.widgets.RichTextEditor#findreplace()
 */
public @interface FindReplace {

    boolean find() default true;

    boolean replace() default true;

}
