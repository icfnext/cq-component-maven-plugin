package com.citytechinc.cq.component.dialog.widget.impl;

import java.net.MalformedURLException;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javassist.ClassPool;
import javassist.NotFoundException;

import org.codehaus.plexus.util.StringUtils;
import org.reflections.Reflections;

import com.citytechinc.cq.component.dialog.widget.WidgetRegistry;
import com.citytechinc.cq.component.maven.util.ComponentMojoUtil;
import com.citytechinc.cq.component.maven.util.LogSingleton;
import com.citytechinc.cq.component.util.WidgetConfigHolder;

public class DefaultWidgetRegistry implements WidgetRegistry {
	private static final String DEFAULT_COMPONENT_PACKAGE = "com.citytechinc.cq.component.dialog";

	private final Map<Class<?>, WidgetConfigHolder> annotationToWidgetConfigMap;

	public DefaultWidgetRegistry(ClassPool classPool, ClassLoader classLoader, Reflections reflections,
		List<String> additionalFeatures) throws MalformedURLException, ClassNotFoundException, NotFoundException {

		LogSingleton LOG = LogSingleton.getInstance();

		List<WidgetConfigHolder> widgetConfigs =
			ComponentMojoUtil.getAllWidgetAnnotations(classPool, classLoader, reflections);

		Collections.sort(widgetConfigs, new Comparator<WidgetConfigHolder>() {

			@Override
			public int compare(WidgetConfigHolder widgetConfig1, WidgetConfigHolder widgetConfig2) {
				if (widgetConfig1.getWidgetClass().getName().startsWith(DEFAULT_COMPONENT_PACKAGE)) {
					return -1;
				} else {
					return 1;
				}
			}
		});

		LOG.debug(widgetConfigs.size() + " Widget Configurations found");

		this.annotationToWidgetConfigMap = new HashMap<Class<?>, WidgetConfigHolder>();

		for (WidgetConfigHolder curConfig : widgetConfigs) {
			LOG.debug("Widget Config -- " + curConfig.getWidgetClass() + " : " + curConfig.getMakerClass() + " : "
				+ curConfig.getAnnotationClass() + " : " + curConfig.getXtype());
			if (curConfig.getAnnotationClass() != null
				&& (StringUtils.isEmpty(curConfig.getFeatureFlag()) || additionalFeatures.contains(curConfig
					.getFeatureFlag()))) {
				this.annotationToWidgetConfigMap.put(curConfig.getAnnotationClass(), curConfig);
			}
		}

	}

	@Override
	public WidgetConfigHolder getWidgetForAnnotation(Class<?> annotation) {
		return this.annotationToWidgetConfigMap.get(annotation);
	}

	@Override
	public Set<Class<?>> getRegisteredAnnotations() {
		return this.annotationToWidgetConfigMap.keySet();
	}

}
