package com.citytechinc.cq.component.annotations.widgets;

public @interface SizeField {

	public String heightParameter() default "./height";

	public String heightPrefix() default "x";

	public String heightSuffix() default "px";

	public String widthParameter() default "./width";

	public String widthPrefix() default "";

	public String widthSuffix() default "";

	public int fieldWidth() default 40;

}
