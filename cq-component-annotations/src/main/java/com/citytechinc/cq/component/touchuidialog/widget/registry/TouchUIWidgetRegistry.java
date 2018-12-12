package com.citytechinc.cq.component.touchuidialog.widget.registry;

import java.util.Set;

import com.citytechinc.cq.component.util.TouchUIWidgetConfigHolder;

public interface TouchUIWidgetRegistry {

	/**
	 *
	 * @return The Widget Configuration associated with the specified annotation
	 */
	public TouchUIWidgetConfigHolder getWidgetForAnnotation(Class<?> annotation);

	/**
	 *
	 * @return A set of all known Widget annotations
	 */
	public Set<Class<?>> getRegisteredAnnotations();

}
