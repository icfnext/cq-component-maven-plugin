package com.citytechinc.cq.component.annotations.widgets.rte;

public @interface Table {

	public boolean table() default true;

	public boolean removetable() default true;

	public boolean insertrow() default true;

	public boolean removerow() default true;

	public boolean insertcolumn() default true;

	public boolean removecolumn() default true;

	public boolean cellprops() default true;

	public boolean mergecells() default true;

	public boolean splitcell() default true;

	public boolean selectrow() default true;

	public boolean selectcolumns() default true;

	public Style[] tableStyles() default {};

	public Style[] cellStyles() default {};

}
