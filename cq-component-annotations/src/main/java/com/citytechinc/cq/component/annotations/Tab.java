package com.citytechinc.cq.component.annotations;

/**
 * Defines a Tab in a Component dialog.
 */
public @interface Tab {

    /**
     * Title of the dialog Tab.
     *
     * @return String
     */
	String title() default "";

	/**
	 * Defines the path to the Tab definition in the content repository.  This property may be used
	 * when the Tab is to be populated in the rendered dialog using an existing definition in the repository.
	 *
	 * @return String
	 */
	String path() default "";
}
