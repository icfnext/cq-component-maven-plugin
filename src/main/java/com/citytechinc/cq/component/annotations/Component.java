package com.citytechinc.cq.component.annotations;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * The Component marks your class as a CQ Component.  Tools will use this annotation
 * to determine whether to perform an operation on the class or not.
 *
 * @author paulmichelotti
 *
 */
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.TYPE)
public @interface Component {

	/**
	 * The path to the component definition within the context of a CQ content tree.  If not
	 * set the path will default to the fully qualified package name for the annotated class
	 * with "." replaced with a file separator character.
	 *
	 * @return
	 */
	String path() default "";

	/**
	 * The name which will identify this component.  For output purposes this is the directory name
	 * under which the configuration files will be placed.
	 *
	 * @return
	 */
	String name() default "";

	String title() default "Component";

	/**
	 * The component group into which this component will be placed.  This overrides any default group
	 * established elsewhere.
	 *
	 * @return
	 */
	String group() default "";

	/**
	 * Indication of whether this component is a container for other components
	 *
	 * @return
	 */
	String isContainer() default "false";

	String[] tabs() default {};

	String[] actions() default {};

	String dialogMode() default "floating";

	String layout() default "editbar";
}
