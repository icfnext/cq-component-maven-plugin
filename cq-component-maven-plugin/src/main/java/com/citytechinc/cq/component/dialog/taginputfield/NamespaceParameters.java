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
package com.citytechinc.cq.component.dialog.taginputfield;

import com.citytechinc.cq.component.dialog.DefaultDialogElementParameters;

public class NamespaceParameters extends DefaultDialogElementParameters {
	private String name;
	private String maximum;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getMaximum() {
		return maximum;
	}

	public void setMaximum(String maximum) {
		this.maximum = maximum;
	}

	@Override
	public String getPrimaryType() {
		return Namespace.PRIMARY_TYPE;
	}

	@Override
	public void setPrimaryType(String primaryType) {
		throw new UnsupportedOperationException("PrimaryType is Static for Namespace");
	}
}
