package com.citytechinc.cq.component.annotations.config;

import com.citytechinc.cq.component.touchuidialog.widget.maker.AbstractTouchUIWidgetMaker;

import java.lang.annotation.Annotation;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * Used to indicate that a Class represents a Touch UI Dialog Widget and makes explicit
 * the connection between the Widget, an annotation necessary to indicate that a
 * field or method is to be populated by a Dialog Widget of this type, and the
 * mechanism which will create the widget.
 */
@Retention(RetentionPolicy.CLASS)
@Target(ElementType.TYPE)
public @interface TouchUIWidget {

    /**
     * <p>
     * The stacked annotation which will be used to indicate that a field is to
     * be populated by a Dialog Widget of this type. This would be the
     * annotation <em>stacked</em> under the
     * {@link com.citytechinc.cq.component.annotations.DialogField DialogField}
     * annotation on a given Component field.
     * </p>
     * <p>
     * The annotationClass list may be left empty in which case the Widget ties
     * together resourceType and WidgetMaker directly. The annotationClass list may
     * <em>not</em> contain more than one Annotation class.
     * </p>
     */
    Class<? extends Annotation> annotationClass();

    /**
     * The class responsible for making instances of the annotated Widget Class.
     */
    Class<? extends AbstractTouchUIWidgetMaker<?>> makerClass();

    /**
     * The resourceType which will be rendered to the Dialog for a field populated by a
     * Dialog Widget of this type.
     */
    String resourceType();

    /**
     * Used in the rare cases where multiple annotations will be stacked under a
     * {@link com.citytechinc.cq.component.annotations.DialogField DialogField}
     * annotation. In such cases, ranking indicates which annotation will be
     * used in looking up an appropriate Widget type and Maker for the field in
     * question. A Widget with a higher ranking will take precedence over one
     * with a lower ranking. In the case of equal ranking values behavior can
     * not be guaranteed.
     */
    int ranking() default -1;

    /**
     * Feature flag which can be configured in a POMs additionalFeatures configuration
     * indicating the inclusion of this widget in the widget registry during dialog rendering.
     * This flag allows for three types of feature flag configuration.
     *
     * <ul>
     * <li>If left empty the widget will always be included.</li>
     * <li>If populated with a string which does not start with "!" the feature will only be included if an additionalFeature matching this string is configured in the using project's POM</li>
     * <li>If populated with a string which starts with "!" the feature will only be included if an additionalFeature matching the substring of this configuration starting at position 1 is NOT included in the project's POM</li>
     * </ul>
     */
    String featureFlag() default "";

}
