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
package com.citytechinc.cq.component.touchuidialog.widget.taginputfield.datasource;

import java.util.List;

import com.citytechinc.cq.component.touchuidialog.widget.datasource.DataSource;

public class TagsDataSource extends DataSource {

	public static final String RESOURCE_TYPE = "cq/gui/components/common/datasources/tags";

	private final List<String> namespaces;

	public TagsDataSource(TagsDataSourceParameters parameters) {
		super(parameters);

		namespaces = parameters.getNamespaces();
	}

	public List<String> getNamespaces() {
		return namespaces;
	}

}
