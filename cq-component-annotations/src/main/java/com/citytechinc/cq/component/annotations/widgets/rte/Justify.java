package com.citytechinc.cq.component.annotations.widgets.rte;

/**
 * @see com.citytechinc.cq.component.annotations.widgets.RichTextEditor#justify()
 */
public @interface Justify {

	public boolean justifyleft() default true;

	public boolean justifycenter() default true;

	public boolean justifyright() default true;

}
