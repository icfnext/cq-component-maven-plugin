package com.citytechinc.cq.component.annotations.widgets.rte;

/**
 * @see com.citytechinc.cq.component.annotations.widgets.RichTextEditor#format()
 */
public @interface Format {

	public boolean bold() default true;

	public boolean italic() default true;

	public boolean underline() default true;

}
