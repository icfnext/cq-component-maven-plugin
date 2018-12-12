package com.citytechinc.cq.component.dialog.widget;

import com.citytechinc.cq.component.util.WidgetConfigHolder;

import java.util.Set;

/**
 * The WidgetRegistry exposes lookup mechanisms for Widget definitions based on
 * widget annotations.
 */
public interface WidgetRegistry {

    /**
     * @param annotation
     * @return The Widget Configuration associated with the specified annotation
     */
    WidgetConfigHolder getWidgetForAnnotation(Class<?> annotation);

    /**
     * @return A set of all known Widget annotations
     */
    Set<Class<?>> getRegisteredAnnotations();

}
