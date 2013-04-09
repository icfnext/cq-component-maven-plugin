package com.citytechinc.cq.component.annotations.widgets;

import com.citytechinc.cq.component.annotations.TagNameSpace;

public @interface TagInputField {
	boolean displayTitles() default true;

	TagNameSpace[] namespaces() default {};
}
