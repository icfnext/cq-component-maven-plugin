package com.citytechinc.cq.component.annotations.widgets.rte;

/**
 * @see com.citytechinc.cq.component.annotations.widgets.RichTextEditor#subsuperscript()
 */
public @interface SubSuperscript {

	public boolean subscript() default true;

	public boolean superscript() default true;

}
