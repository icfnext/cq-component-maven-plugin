/**
 *    Copyright 2013 CITYTECH, Inc.
 *
 *    Licensed under the Apache License, Version 2.0 (the "License");
 *    you may not use this file except in compliance with the License.
 *    You may obtain a copy of the License at
 *
 *        http://www.apache.org/licenses/LICENSE-2.0
 *
 *    Unless required by applicable law or agreed to in writing, software
 *    distributed under the License is distributed on an "AS IS" BASIS,
 *    WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *    See the License for the specific language governing permissions and
 *    limitations under the License.
 */
package com.citytechinc.cq.component.touchuidialog.widget.registry;

import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import com.citytechinc.cq.component.maven.util.LogSingleton;
import javassist.ClassPool;
import javassist.NotFoundException;

import org.codehaus.plexus.util.StringUtils;
import org.reflections.Reflections;

import com.citytechinc.cq.component.maven.util.ComponentMojoUtil;
import com.citytechinc.cq.component.util.TouchUIWidgetConfigHolder;

public class DefaultTouchUIWidgetRegistry implements TouchUIWidgetRegistry {
	private static final String DEFAULT_COMPONENT_PACKAGE = "com.citytechinc.cq.component.touchuidialog";

	private final Map<Class<?>, TouchUIWidgetConfigHolder> annotationToWidgetConfigMap;

	public DefaultTouchUIWidgetRegistry(ClassPool classPool, ClassLoader classLoader, Reflections reflections,
		List<String> additionalFeatures) throws NotFoundException, ClassNotFoundException {
		annotationToWidgetConfigMap = new HashMap<Class<?>, TouchUIWidgetConfigHolder>();

		List<TouchUIWidgetConfigHolder> widgetConfigurations =
			ComponentMojoUtil.getAllTouchUIWidgetAnnotations(classPool, classLoader, reflections);

		/*
		 * When two Widget types are configured for the same Widget Annotation, this sort will order using the
		 * following rules.
		 *
		 * 1) Widgets in the default package will come before Widgets in external packages
		 * 2) When two Widgets are in the default package, ones with Feature Flags will be ordered after ones without
		 * 3) When two Widgets are in external packages, ones with Feature Flags will be ordered after ones without
		 *
		 * When adding the widget configurations to the configuration Map, Widgets later in the list will
		 * override widgets prior in the list.
		 */
		Collections.sort(widgetConfigurations, new Comparator<TouchUIWidgetConfigHolder>() {

			@Override
			public int compare(TouchUIWidgetConfigHolder widgetConfig1, TouchUIWidgetConfigHolder widgetConfig2) {
				if (widgetConfig1.getAnnotationClass() != null && widgetConfig2.getAnnotationClass() != null) {
					if (widgetConfig1.getAnnotationClass().equals(widgetConfig2.getAnnotationClass())) {
						if (widgetConfig1.getWidgetClass().getName().startsWith(DEFAULT_COMPONENT_PACKAGE)) {
							if (widgetConfig2.getWidgetClass().getName().startsWith(DEFAULT_COMPONENT_PACKAGE)) {
								if (StringUtils.isNotBlank(widgetConfig1.getFeatureFlag())) {
									return 1;
								}
								if (StringUtils.isNotBlank(widgetConfig2.getFeatureFlag())) {
									return -1;
								}
							} else {
								return -1;
							}
						} else {
							if (widgetConfig2.getWidgetClass().getName().startsWith(DEFAULT_COMPONENT_PACKAGE)) {
								return 1;
							}
							if (StringUtils.isNotBlank(widgetConfig1.getFeatureFlag())) {
								return 1;
							}
							if (StringUtils.isNotBlank(widgetConfig2.getFeatureFlag())) {
								return -1;
							}
						}
					}
					return widgetConfig1.getAnnotationClass().getName().compareTo(widgetConfig2.getAnnotationClass().getName());
				} else {
					if (widgetConfig1.getAnnotationClass() == null) {
						return -1;
					}

					return 1;
				}
			}

				//If the widgets do not have the same annotation class we don't care what order they get sorted in.


				/*
				if (widgetConfig1.getWidgetClass().getName().startsWith(DEFAULT_COMPONENT_PACKAGE)) {
					return -1;
				} else {
					return 1;
				}
				*/
		});

		LogSingleton.getInstance().debug("DefaultTouchUIWidgetRegistry - Sorted unmapped list of touch UI widgets");
		for (TouchUIWidgetConfigHolder currentWidgetConfiguration : widgetConfigurations) {
			LogSingleton.getInstance().debug("DefaultTouchUIWidgetRegistry - Widget " + currentWidgetConfiguration.getWidgetClass().getName() + " for Annotation " + currentWidgetConfiguration.getAnnotationClass().getName() + " Using Feature " + currentWidgetConfiguration.getFeatureFlag());
			if (currentWidgetConfiguration.getAnnotationClass() != null
				&& (StringUtils.isEmpty(currentWidgetConfiguration.getFeatureFlag()) || additionalFeatures
					.contains(currentWidgetConfiguration.getFeatureFlag()))) {
				annotationToWidgetConfigMap.put(currentWidgetConfiguration.getAnnotationClass(),
					currentWidgetConfiguration);
			}
		}
	}

	@Override
	public TouchUIWidgetConfigHolder getWidgetForAnnotation(Class<?> annotation) {
		return annotationToWidgetConfigMap.get(annotation);
	}

	@Override
	public Set<Class<?>> getRegisteredAnnotations() {
		return annotationToWidgetConfigMap.keySet();
	}

}
