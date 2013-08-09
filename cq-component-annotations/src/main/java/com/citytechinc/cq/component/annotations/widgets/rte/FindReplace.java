package com.citytechinc.cq.component.annotations.widgets.rte;

/**
 * @see com.citytechinc.cq.component.annotations.widgets.RichTextEditor#findreplace()
 */
public @interface FindReplace {

	public boolean find() default true;

	public boolean replace() default true;

}
