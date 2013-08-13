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
