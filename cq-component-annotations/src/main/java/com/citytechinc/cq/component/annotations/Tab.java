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
     * Applicable to Classic-UI only
     * <p>
     * Defines the path to the Tab definition in the content repository. This
     * property may be used when the Tab is to be populated in the rendered
     * dialog using an existing definition in the repository.
     *
     * @return String
     */
    String classicUIPath() default "";

    /**
     * Applicable to Touch-UI only
     * <p>
     * Defines the path to the Tab definition in the content repository. This
     * property may be used when the Tab is to be populated in the rendered
     * dialog using an existing definition in the repository.
     */
    String touchUIPath() default "";

    /**
     * Applicable to Classic-UI only
     * <p>
     * The set of listeners which will be attributed to the tab. Listeners are
     * output as properties in the listeners XML node which is a child of the
     * XML node representing the dialog tab..
     *
     * @return Listener[]
     */
    Listener[] listeners() default {};

    /**
     * Applicable to Touch-UI only
     * <p>
     * Defines the sling:resourceType for the renderCondition node for this tab
     */
    String renderConditionResourceType() default "";

    /**
     * Applicable to Touch-UI only
     * <p>
     * Defines additional properties to be added to the renderCondition node
     * <p>
     * These will only be added if renderConditionResourceType is not empty
     */
    Property[] renderConditionProperties() default {};

    /**
     * Applicable to Touch-UI only
     * <p>
     * Controls whether or not the tab is shown in the create page dialog
     */
    boolean showOnCreate() default true;

    /**
     * Applicable to Touch-UI only
     * <p>
     * Controls whether or not the tab is hidden in the edit page dialog
     */
    boolean hideOnEdit() default false;

    /**
     * Applicable to Touch-UI only
     * <p>
     * Controls whether or not the tab container has a margin
     */
    boolean margin() default true;

    /**
     * Applicable to Touch-UI only
     * <p>
     * Allows direct control over the name of the node generated for the Tab.
     * This is useful in situations where you are supertyping a resource or you
     * plan for your resource to be supertyped as it allows for the dialog
     * structure to be shared by the inheriting components which is necessary
     * for the resource merger mechanisms to act as expected.
     * <p>
     * If unset the tab names will continue to default to a sanitized version of
     * the title.
     */
    String touchUINodeName() default "";

    /**
     * Applicable to Touch-UI only
     * <p>
     * Sets the sling:orderBefore property. This can be used when supertyping a
     * resource to reorder the tabs
     */
    String orderBefore() default "";
}
