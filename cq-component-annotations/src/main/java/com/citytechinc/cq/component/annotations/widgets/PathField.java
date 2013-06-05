package com.citytechinc.cq.component.annotations.widgets;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Retention(RetentionPolicy.CLASS)
@Target({ ElementType.FIELD, ElementType.METHOD })
public @interface PathField {

	public static final boolean ESCAPE_AMP_DEFAULT = false;
	public static final boolean HIDE_TRIGGER_DEFAULT = false;
	public static final boolean PAR_BROWSE_DEFAULT = false;
	public static final String ROOT_PATH_DEFAULT = "/";
	public static final String ROOT_TITLE_DEFAULT = "Websites";
	public static final boolean SHOW_TITLE_IN_TREE_DEFAULT = true;

	boolean escapeAmp() default ESCAPE_AMP_DEFAULT;

	boolean hideTrigger() default HIDE_TRIGGER_DEFAULT;

	boolean parBrowse() default PAR_BROWSE_DEFAULT;

	String rootPath() default ROOT_PATH_DEFAULT;

	String rootTitle() default ROOT_TITLE_DEFAULT;

	boolean showTitleInTree() default SHOW_TITLE_IN_TREE_DEFAULT;
}
