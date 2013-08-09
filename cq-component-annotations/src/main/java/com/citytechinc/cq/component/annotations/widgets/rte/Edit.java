package com.citytechinc.cq.component.annotations.widgets.rte;

/**
 * @see com.citytechinc.cq.component.annotations.widgets.RichTextEditor#edit()
 */
public @interface Edit {

	public boolean cut() default true;

	public boolean copy() default true;

	public boolean pasteDefault() default true;

	public boolean pastePlaintext() default true;

	public boolean pasteWordhtml() default true;

}
