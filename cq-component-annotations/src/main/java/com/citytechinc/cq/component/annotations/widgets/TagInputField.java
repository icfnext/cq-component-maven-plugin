package com.citytechinc.cq.component.annotations.widgets;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import com.citytechinc.cq.component.annotations.TagNameSpace;

/**
 * Represents a Widget of type CQ.tagging.TagInputField
 *
 */
@Retention(RetentionPolicy.CLASS)
@Target({ ElementType.FIELD, ElementType.METHOD })
public @interface TagInputField {

    /**
     * Whether to display tag titles instead of the pure tag IDs in the input field, autocompletion, tree or cloud view.
     *
     * @return boolean
     */
	boolean displayTitles() default true;

	/**
	 * A list of the tag namespaces that should be displayed and allowed. If empty, all available namespaces will be allowed.
	 *
	 * @return TagNameSpace[]
	 */
	TagNameSpace[] namespaces() default {};
}
