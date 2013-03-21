package com.citytechinc.cq.component.annotations.widgets;

public @interface Html5SmartImage {
	public boolean disableFlush() default false;
	public boolean disableInfo() default false;
	public boolean disableZoom() default false;
	public String cropParameter() default "";
	public String fileNameParameter() default "./fileName";
	public String fileReferenceParameter() default "./fileReference";
	public String mapParameter() default "";
	public String rotateParameter() default "";
	public String uploadUrl() default "/tmp/upload/*";
	public String ddGroups() default "media";
	public boolean tab() default true;
	public int height() default 0;
	public boolean allowUpload() default true;
}
