package com.citytechinc.cq.component.annotations.widgets.rte;

/**
 * @see com.citytechinc.cq.component.annotations.widgets.RichTextEditor#justify()
 */
public @interface Justify {

    boolean justifyleft() default true;

    boolean justifycenter() default true;

    boolean justifyright() default true;

}
