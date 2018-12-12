package com.citytechinc.cq.component.annotations.widgets;

import com.citytechinc.cq.component.annotations.Option;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * Specifies a selection type Widget. A selection may be one of
 *
 * <ol>
 * <li>select</li>
 * <li>radio</li>
 * <li>checkbox</li>
 * <li>combobox</li>
 * </ol>
 * <p>
 * Represents a Widget of type CQ.form.Selection in Classic UI
 * <p>
 * Represents a Widget of type granite/ui/components/foundation/form/select when
 * using the select type and granite/ui/components/foundation/form/radiogroup
 * when using the radio type in Touch UI
 * <p>
 * Checkbox and combobox are not currently supported in Touch UI rendering
 */
@Retention(RetentionPolicy.CLASS)
@Target({ ElementType.FIELD, ElementType.METHOD })
public @interface Selection {

    String SELECT = "select";

    String RADIO = "radio";

    String CHECKBOX = "checkbox";

    String COMBOBOX = "combobox";

    String ASC_SORT_DIR = "ASC";

    String DESC_SORT_DIR = "DESC";

    /**
     * An explicitly enumerated set of options for the selection. Note: if your
     * options are returned dynamically via a URL, use the optionsUrl property.
     *
     * @return
     */
    Option[] options() default {};

    /**
     * Used for Classic UI only
     * <p>
     * The URL from which options are to be pulled. Note: setting this to
     * anything other than an empty string overrides any settings made in the
     * options property.
     *
     * @return
     */
    String optionsUrl() default "";

    /**
     * Used for Classic UI only
     * <p>
     * A function or the name of a function that will be called on
     * processRecords to receive the options. The function must return an array
     * of options.
     *
     * @return String
     * @see <a
     * href="http://dev.day.com/docs/en/cq/5-6/widgets-api/output/CQ.form.Selection.html#options">Options</a>
     */
    String optionsProvider() default "";

    /**
     * Used for Classic UI only
     * <p>
     * The sort direction of the the options. If "ASC" or "DESC" the options
     * will be sorted by its (internationalized) text.
     *
     * @return String
     */
    String sortDir() default "";

    /**
     * The type of the selection. One of "checkbox", "radio", "select" or
     * "combobox". "checkbox" is the only type that supports mulitple values.
     *
     * @return String
     */
    String type() default RADIO;

    /**
     * Used for Touch UI only
     * <p>
     * Indicates whether multiple values may be picked for the selection
     *
     * @return boolean
     */
    boolean multiple() default false;

    /**
     * Used for Touch UI only
     * <p>
     * Indicates the sling:resourceType of the data source provider. For more
     * information on DataSources, see <a href=
     * "http://docs.adobe.com/docs/en/cq/current/touch-ui/granite-reference.html#Datasource"
     * >http://docs.adobe.com/docs/en/cq/current/touch-ui/granite-reference.html
     * #Datasource</a>
     *
     * @return String
     */
    String dataSource() default "";
}
