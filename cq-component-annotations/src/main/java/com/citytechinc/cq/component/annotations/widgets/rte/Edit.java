package com.citytechinc.cq.component.annotations.widgets.rte;

public @interface Edit {

	public boolean cut() default true;

	public boolean copy() default true;

	public boolean pasteDefault() default true;

	public boolean pastePlaintext() default true;

	public boolean pasteWordhtml() default true;

}
