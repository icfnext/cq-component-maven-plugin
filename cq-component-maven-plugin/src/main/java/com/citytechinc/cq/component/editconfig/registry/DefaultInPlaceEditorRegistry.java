package com.citytechinc.cq.component.editconfig.registry;

import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javassist.ClassPool;
import javassist.NotFoundException;

import org.reflections.Reflections;

import com.citytechinc.cq.component.maven.util.ComponentMojoUtil;
import com.citytechinc.cq.component.maven.util.LogSingleton;
import com.citytechinc.cq.component.util.InPlaceEditorConfigHolder;

public class DefaultInPlaceEditorRegistry implements InPlaceEditorRegistry {
	private static final String DEFAULT_COMPONENT_PACKAGE = "com.citytechinc.cq.component.editconfig";

	private final Map<Class<?>, InPlaceEditorConfigHolder> annotationToInPlaceEditorConfigMap;

	public DefaultInPlaceEditorRegistry(ClassPool classPool, ClassLoader classLoader, Reflections reflections)
		throws NotFoundException, ClassNotFoundException {
		annotationToInPlaceEditorConfigMap = new HashMap<Class<?>, InPlaceEditorConfigHolder>();

		List<InPlaceEditorConfigHolder> inPlaceEditorConfigurations =
			ComponentMojoUtil.getInPlaceEditorAnnotations(classPool, classLoader, reflections);

		/*
		 * When two InPlaceEditor types are configured for the same
		 * InPlaceEditor Annotation, this sort will order using the following
		 * rules.
		 * 
		 * 1) InPlaceEditors in the default package will come before
		 * InPlaceEditors in external packages 2) When two InPlaceEditors are in
		 * the default package, ones with Feature Flags will be ordered after
		 * ones without 3) When two InPlaceEditors are in external packages,
		 * ones with Feature Flags will be ordered after ones without
		 * 
		 * When adding the inPlaceEditor configurations to the configuration
		 * Map, InPlaceEditors later in the list will override inPlaceEditors
		 * prior in the list.
		 */
		Collections.sort(inPlaceEditorConfigurations, new Comparator<InPlaceEditorConfigHolder>() {

			@Override
			public int compare(InPlaceEditorConfigHolder inPlaceEditorConfig1,
				InPlaceEditorConfigHolder inPlaceEditorConfig2) {
				if (inPlaceEditorConfig1.getAnnotationClass() != null
					&& inPlaceEditorConfig2.getAnnotationClass() != null) {
					if (inPlaceEditorConfig1.getAnnotationClass().equals(inPlaceEditorConfig2.getAnnotationClass())) {
						if (inPlaceEditorConfig1.getInPlaceEditorClass().getName()
							.startsWith(DEFAULT_COMPONENT_PACKAGE)) {
							return -1;
						} else {
							if (inPlaceEditorConfig2.getInPlaceEditorClass().getName()
								.startsWith(DEFAULT_COMPONENT_PACKAGE)) {
								return 1;
							}
						}
					}
					return inPlaceEditorConfig1.getAnnotationClass().getName()
						.compareTo(inPlaceEditorConfig2.getAnnotationClass().getName());
				} else {
					if (inPlaceEditorConfig1.getAnnotationClass() == null) {
						return -1;
					}

					return 1;
				}
			}
		});

		LogSingleton.getInstance().debug(
			"DefaultTouchUIInPlaceEditorRegistry - Sorted unmapped list of touch UI inPlaceEditors");
		for (InPlaceEditorConfigHolder currentInPlaceEditorConfiguration : inPlaceEditorConfigurations) {
			LogSingleton.getInstance().debug(
				"DefaultInPlaceEditorRegistry - InPlaceEditor "
					+ currentInPlaceEditorConfiguration.getInPlaceEditorClass().getName() + " for Annotation "
					+ currentInPlaceEditorConfiguration.getAnnotationClass().getName());
			if (currentInPlaceEditorConfiguration.getAnnotationClass() != null) {
				annotationToInPlaceEditorConfigMap.put(currentInPlaceEditorConfiguration.getAnnotationClass(),
					currentInPlaceEditorConfiguration);
			}
		}
	}

	@Override
	public InPlaceEditorConfigHolder getInPlaceEditorForAnnotation(Class<?> annotation) {
		return annotationToInPlaceEditorConfigMap.get(annotation);
	}

	@Override
	public Set<Class<?>> getRegisteredAnnotations() {
		return annotationToInPlaceEditorConfigMap.keySet();
	}

}
