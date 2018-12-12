package com.citytechinc.cq.component.annotations.widgets.rte;

/**
 * @see com.citytechinc.cq.component.annotations.widgets.RichTextEditor#table()
 */
public @interface Table {

    boolean table() default true;

    boolean removetable() default true;

    boolean insertrow() default true;

    boolean removerow() default true;

    boolean insertcolumn() default true;

    boolean removecolumn() default true;

    boolean cellprops() default true;

    boolean mergecells() default true;

    boolean splitcell() default true;

    boolean selectrow() default true;

    boolean selectcolumns() default true;

    Style[] tableStyles() default {};

    Style[] cellStyles() default {};

}
