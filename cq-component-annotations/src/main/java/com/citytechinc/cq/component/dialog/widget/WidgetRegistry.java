package com.citytechinc.cq.component.dialog.widget;

import java.util.Set;

import com.citytechinc.cq.component.maven.util.WidgetConfigHolder;

public interface WidgetRegistry {

	public WidgetConfigHolder getWidgetForAnnotation(Class<?> annotation);

	public Set<Class<?>> getRegisteredAnnotations();

}
