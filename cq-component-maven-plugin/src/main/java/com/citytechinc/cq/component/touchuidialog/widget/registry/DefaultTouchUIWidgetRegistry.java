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

import com.citytechinc.cq.component.maven.util.ComponentMojoUtil;
import com.citytechinc.cq.component.util.TouchUIWidgetConfigHolder;
import javassist.ClassPool;
import javassist.NotFoundException;
import org.reflections.Reflections;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class DefaultTouchUIWidgetRegistry implements TouchUIWidgetRegistry {

    private final Map<Class<?>, TouchUIWidgetConfigHolder> annotationToWidgetConfigMap;

    public DefaultTouchUIWidgetRegistry(ClassPool classPool, ClassLoader classLoader, Reflections reflections) throws NotFoundException, ClassNotFoundException {
        annotationToWidgetConfigMap = new HashMap<Class<?>, TouchUIWidgetConfigHolder>();

        List<TouchUIWidgetConfigHolder> widgetConfigurations = ComponentMojoUtil.getAllTouchUIWidgetAnnotations(classPool, classLoader, reflections);

        for (TouchUIWidgetConfigHolder currentWidgetConfiguration : widgetConfigurations) {
            if (currentWidgetConfiguration.getAnnotationClass() != null) {
                annotationToWidgetConfigMap.put(currentWidgetConfiguration.getAnnotationClass(), currentWidgetConfiguration);
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
