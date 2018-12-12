package com.citytechinc.cq.component.annotations.widgets.rte;

/**
 * @see com.citytechinc.cq.component.annotations.widgets.RichTextEditor#links()
 */
public @interface Links {

	public boolean modifylink() default true;

	public boolean unlink() default true;

	public boolean anchor() default true;

}
