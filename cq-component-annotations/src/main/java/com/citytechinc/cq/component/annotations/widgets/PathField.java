package com.citytechinc.cq.component.annotations.widgets;

public @interface PathField {
	boolean escapeAmp() default false;

	boolean hideTrigger() default false;

	boolean parBrowse() default false;

	String rootPath() default "/";

	String rootTitle() default "Websites";

	boolean showTitleInTree() default true;
}
