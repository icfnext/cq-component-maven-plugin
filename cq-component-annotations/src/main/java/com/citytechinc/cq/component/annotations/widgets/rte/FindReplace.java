package com.citytechinc.cq.component.annotations.widgets.rte;

public @interface FindReplace {

	public boolean find() default true;

	public boolean replace() default true;

}
