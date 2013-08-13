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
package com.citytechinc.cq.component.dialog;

/**
 * During execution of the CQ Component Plugin, a Component Name Transformer will
 * transform the Java Class name of a Component into a name applicable for a component
 * node in the content repository.  This name ends up being the terminal part of the
 * content path to the Component definition.
 *
 */
public interface ComponentNameTransformer {

    /**
     *
     * @param className The unqualified, or simple, name of the Java Class to transform
     * @return The transformed name
     */
	public String transform(String className);
}
