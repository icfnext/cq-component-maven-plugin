package com.citytechinc.cq.component.annotations;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * The Component marks your class as a CQ Component. Tools will use this
 * annotation to determine whether to perform an operation on the class or not.
 * 
 * 
 * 
 */
@Retention(RetentionPolicy.CLASS)
@Target(ElementType.TYPE)
public @interface Component {

	/**
	 * The path to the component definition within the context of a CQ content
	 * tree. This is meant to override any default path specified for the
	 * project as a whole.
	 * 
	 * 
	 * @return String
	 */
	String path() default "";

	/**
	 * The name which will identify this component. For output purposes this is
	 * the directory name under which the configuration files will be placed.
	 * 
	 * 
	 * @return String
	 */
	String name() default "";

	/**
	 * The jcr:title of the Component
	 * 
	 * @return String
	 */
	String value();

	/**
	 * The component group into which this component will be placed. This
	 * overrides any default group established elsewhere.
	 * 
	 * 
	 * @return String
	 */
	String group() default "";

	/**
	 * Indication of whether this component is a container for other components
	 * 
	 * 
	 * @return boolean
	 */
	boolean isContainer() default false;

	/**
	 * An array to order the tabs used in DialogField annotations
	 * 
	 * @return String[]
	 */
	String[] tabs() default {};

	/**
	 * The cq:actions for the edit config
	 * 
	 * @return String[]
	 */
	String[] actions() default {};

	/**
	 * The cq:dialogMode for the edit config
	 * 
	 * @return String
	 */
	String dialogMode() default "floating";

	/**
	 * The cq:layout for the edit config
	 * 
	 * @return String
	 */
	String layout() default "editbar";

	/**
	 * Listeners inside the edit config
	 * 
	 * @return Listener[]
	 */
	Listener[] listeners() default {};

	/**
	 * The sling:resourceSuperType of this component
	 * 
	 * @return String
	 */
	String resourceSuperType() default "";

	/**
	 * The name of the xml file to store this dialog under (with out the .xml
	 * suffix)
	 * 
	 * @return String
	 */
	String fileName() default "dialog";

	/**
	 * boolean setting to generate an edit config
	 * 
	 * @return boolean
	 */
	boolean editConfig() default true;

	/**
	 * The width of the dialog
	 * 
	 * @return int
	 */
	int dialogWidth() default -1;

	/**
	 * The height of the dialog
	 * 
	 * @return int
	 */
	int dialogHeight() default -1;
}
