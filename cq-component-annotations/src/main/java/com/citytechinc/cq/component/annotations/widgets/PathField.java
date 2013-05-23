package com.citytechinc.cq.component.annotations.widgets;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Retention(RetentionPolicy.CLASS)
@Target({ ElementType.FIELD, ElementType.METHOD })
public @interface PathField {
	boolean escapeAmp() default false;

	boolean hideTrigger() default false;

	boolean parBrowse() default false;

	String rootPath() default "/";

	String rootTitle() default "Websites";

	boolean showTitleInTree() default true;
}
