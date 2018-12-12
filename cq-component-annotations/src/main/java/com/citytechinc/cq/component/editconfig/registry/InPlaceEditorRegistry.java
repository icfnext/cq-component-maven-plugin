package com.citytechinc.cq.component.editconfig.registry;

import java.util.Set;

import com.citytechinc.cq.component.util.InPlaceEditorConfigHolder;

public interface InPlaceEditorRegistry {

	/**
	 *
	 * @return The In Place Editor Configuration associated with the specified
	 *         annotation
	 */
	public InPlaceEditorConfigHolder getInPlaceEditorForAnnotation(Class<?> annotation);

	/**
	 *
	 * @return A set of all known In Place Editor annotations
	 */
	public Set<Class<?>> getRegisteredAnnotations();

}
