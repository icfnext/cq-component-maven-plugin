package com.citytechinc.cq.component.editconfig.registry;

import com.citytechinc.cq.component.util.InPlaceEditorConfigHolder;

import java.util.Set;

public interface InPlaceEditorRegistry {

    /**
     * @return The In Place Editor Configuration associated with the specified
     * annotation
     */
    InPlaceEditorConfigHolder getInPlaceEditorForAnnotation(Class<?> annotation);

    /**
     * @return A set of all known In Place Editor annotations
     */
    Set<Class<?>> getRegisteredAnnotations();

}
