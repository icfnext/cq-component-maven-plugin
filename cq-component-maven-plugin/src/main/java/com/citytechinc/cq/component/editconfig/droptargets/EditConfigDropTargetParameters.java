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
package com.citytechinc.cq.component.editconfig.droptargets;

import com.citytechinc.cq.component.xml.DefaultXmlElementParameters;

public class EditConfigDropTargetParameters extends DefaultXmlElementParameters {
	private String[] accept;
	private String[] groups;
	private String propertyName;

	public String[] getAccept() {
		return accept;
	}

	public void setAccept(String[] accept) {
		this.accept = accept;
	}

	public String[] getGroups() {
		return groups;
	}

	public void setGroups(String[] groups) {
		this.groups = groups;
	}

	public String getPropertyName() {
		return propertyName;
	}

	public void setPropertyName(String propertyName) {
		this.propertyName = propertyName;
	}

	@Override
	public String getPrimaryType() {
		return "cq:DropTargetConfig";
	}

	@Override
	public void setPrimaryType(String primaryType) {
		throw new UnsupportedOperationException("PrimaryType is Static for EditConfigActionConfig");
	}
}
