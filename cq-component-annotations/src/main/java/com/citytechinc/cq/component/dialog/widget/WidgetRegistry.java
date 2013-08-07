package com.citytechinc.cq.component.dialog.widget;

import java.util.Set;

import com.citytechinc.cq.component.util.WidgetConfigHolder;

/**
 * The WidgetRegistry exposes lookup mechanisms for Widget definitions based on
 * widget annotations.
 */
public interface WidgetRegistry {

    /**
     *
     * @param annotation
     * @return The Widget Configuration associated with the specified annotation
     */
	public WidgetConfigHolder getWidgetForAnnotation(Class<?> annotation);

	/**
	 *
	 * @return A set of all known Widget annotations
	 */
	public Set<Class<?>> getRegisteredAnnotations();

}
