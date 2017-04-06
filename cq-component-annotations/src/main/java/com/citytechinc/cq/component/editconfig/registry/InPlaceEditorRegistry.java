/**
 *    Copyright 2017 ICF Olson
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
