package com.citytechinc.cq.component.annotations.widgets.rte;

/**
 * @see com.citytechinc.cq.component.annotations.widgets.RichTextEditor#paraformat()
 */
public @interface ParaFormat {

	public ParaFormatFormat[] formats() default {};

}
