package com.citytechinc.cq.component.touchuidialog.widget.registry;

import com.citytechinc.cq.component.util.TouchUIWidgetConfigHolder;

import java.util.Set;

public interface TouchUIWidgetRegistry {

    /**
     * @return The Widget Configuration associated with the specified annotation
     */
    TouchUIWidgetConfigHolder getWidgetForAnnotation(Class<?> annotation);

    /**
     * @return A set of all known Widget annotations
     */
    Set<Class<?>> getRegisteredAnnotations();

}
