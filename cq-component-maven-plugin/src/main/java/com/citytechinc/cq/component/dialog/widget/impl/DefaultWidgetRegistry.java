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
package com.citytechinc.cq.component.dialog.widget.impl;

import java.net.MalformedURLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javassist.ClassPool;
import javassist.NotFoundException;

import org.reflections.Reflections;

import com.citytechinc.cq.component.dialog.widget.WidgetRegistry;
import com.citytechinc.cq.component.maven.util.ComponentMojoUtil;
import com.citytechinc.cq.component.maven.util.LogSingleton;
import com.citytechinc.cq.component.util.WidgetConfigHolder;

public class DefaultWidgetRegistry implements WidgetRegistry {

	private final Map<Class<?>, WidgetConfigHolder> annotationToWidgetConfigMap;

	public DefaultWidgetRegistry(ClassPool classPool, ClassLoader classLoader, Reflections reflections)
		throws MalformedURLException, ClassNotFoundException, NotFoundException {

		LogSingleton LOG = LogSingleton.getInstance();

		List<WidgetConfigHolder> widgetConfigs =
			ComponentMojoUtil.getAllWidgetAnnotations(classPool, classLoader, reflections);

		LOG.debug(widgetConfigs.size() + " Widget Configurations found");

		this.annotationToWidgetConfigMap = new HashMap<Class<?>, WidgetConfigHolder>();

		for (WidgetConfigHolder curConfig : widgetConfigs) {
			LOG.debug("Widget Config -- " + curConfig.getWidgetClass() + " : " + curConfig.getMakerClass() + " : "
				+ curConfig.getAnnotationClass() + " : " + curConfig.getXtype());
			if (curConfig.getAnnotationClass() != null) {
				this.annotationToWidgetConfigMap.put(curConfig.getAnnotationClass(), curConfig);
			}
		}

	}

	public WidgetConfigHolder getWidgetForAnnotation(Class<?> annotation) {
		return this.annotationToWidgetConfigMap.get(annotation);
	}

	public Set<Class<?>> getRegisteredAnnotations() {
		return this.annotationToWidgetConfigMap.keySet();
	}

}
