package com.citytechinc.cq.component.annotations.widgets;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * Represents a Widget of type CQ.form.PathField in Classic UI
 * <p>
 * Represents a Widget of type granite/ui/components/foundation/form/pathbrowser
 * in Touch UI
 */
@Retention(RetentionPolicy.CLASS)
@Target({ ElementType.FIELD, ElementType.METHOD })
public @interface PathField {

    boolean ESCAPE_AMP_DEFAULT = false;

    boolean HIDE_TRIGGER_DEFAULT = false;

    boolean PAR_BROWSE_DEFAULT = false;

    String ROOT_PATH_DEFAULT = "/";

    String ROOT_TITLE_DEFAULT = "Websites";

    boolean SHOW_TITLE_IN_TREE_DEFAULT = true;

    String DEFAULT_FILTER_VALUE = "hierarchyNotFile";

    /**
     * Used for Classic UI only
     * <p>
     * True to url-encode the ampersand character (&amp;) to %26.
     *
     * @return boolean
     */
    boolean escapeAmp() default ESCAPE_AMP_DEFAULT;

    /**
     * Used for Classic UI only
     * <p>
     * True to disable the option to open the browse dialog
     *
     * @return boolean
     */
    boolean hideTrigger() default HIDE_TRIGGER_DEFAULT;

    /**
     * Used for Classic UI only
     * <p>
     * True to allow paragraph browsing and section in a grid next to the tree
     * panel in the browse dialog. If this is enabled, it is recommended to use
     * a predicate like 'hierarchy' to have pages as leaf nodes in the tree.
     *
     * @return boolean
     */
    boolean parBrowse() default PAR_BROWSE_DEFAULT;

    /**
     * The root path where completion and browsing starts. Use the empty string
     * for the repository root
     *
     * @return String
     */
    String rootPath() default ROOT_PATH_DEFAULT;

    /**
     * Used for Classic UI only
     * <p>
     * Custom title for the root path
     *
     * @return String
     */
    String rootTitle() default ROOT_TITLE_DEFAULT;

    /**
     * Used for Classic UI only
     * <p>
     * Whether to show the (jcr:)titles as names of the tree nodes or the plain
     * jcr node name
     *
     * @return boolean
     */
    boolean showTitleInTree() default SHOW_TITLE_IN_TREE_DEFAULT;

    /**
     * Used for Coral 2 Touch UI only
     * <p>
     * Javascript source code for an option loader callback function. Takes two
     * arguments: (path, callback). See the default option loader implementation
     * for more details on how to use this. If not given, a default option
     * loader will be used that just returns paths from the current repository.
     * <p>
     * The default option loader can be found in
     * /libs/granite/ui/components/foundation/form/pathbrowser/render.jsp
     *
     * @return String
     */
    String optionLoader() default "";

    /**
     * Used for Coral 2 Touch UI only
     * <p>
     * Javascript source code for callback function that gets an option value
     * object as parameter and should return a stringified value for this
     * option.
     *
     * @return String
     */
    String optionValueReader() default "";

    /**
     * Used for Coral 2 Touch UI only
     * <p>
     * Javascript source code for callback function that gets an option value
     * object as parameter and should return a stringified title for this
     * option.
     *
     * @return String
     */
    String optionTitleReader() default "";

    /**
     * Used for Coral 3 Touch UI only.
     * <p>
     * Indicates if the user must only select from the list of given options. If it is not forced, the user can enter arbitrary value
     *
     * @return boolean
     */
    boolean forceSelection() default false;

    /**
     * Used for Coral 3 Touch UI only.
     * <p>
     * The filter applied to suggestion and picker.
     * <p>
     * The default value is hierarchyNotFile.
     *
     * <ul>
     * Valid values are:
     * <li>folder - Shows only nt:folder nodes</li>
     * <li>hierarchy - Shows only nt:hierarchyNode nodes</li>
     * <li>hierarchyNotFile - Shows only nt:hierarchyNode nodes that are not nt:file</li>
     * <li>nosystem - Shows non-system nodes: !node.getName().startsWith("rep:") && !node.getName().equals("jcr:system")</li>
     * </ul>
     *
     * @return String
     */
    String filter() default DEFAULT_FILTER_VALUE;
}
