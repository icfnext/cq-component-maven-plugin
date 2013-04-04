package com.citytechinc.cq.component.annotations.widgets.rte;

public @interface Lists {

	public boolean ordered() default true;

	public boolean unordered() default true;

	public boolean indent() default true;

	public boolean outdent() default true;

}
