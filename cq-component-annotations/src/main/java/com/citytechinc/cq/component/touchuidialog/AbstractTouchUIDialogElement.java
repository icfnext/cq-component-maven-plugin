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
package com.citytechinc.cq.component.touchuidialog;

import com.citytechinc.cq.component.util.Constants;
import com.citytechinc.cq.component.xml.AbstractXmlElement;
import com.citytechinc.cq.component.xml.NameSpacedAttribute;

public abstract class AbstractTouchUIDialogElement extends AbstractXmlElement implements TouchUIDialogElement {

	private NameSpacedAttribute<String> resourceType;
	private double ranking;

	public AbstractTouchUIDialogElement(TouchUIDialogElementParameters parameters) {
		super(parameters);

		resourceType =
			new NameSpacedAttribute<String>(Constants.SLING_NS_URI, Constants.SLING_NS_PREFIX, "resourceType",
				parameters.getResourceType());
	}

	public double getRanking() {
		return ranking;
	}

	public void setRanking(double ranking) {
		this.ranking = ranking;
	}

	public NameSpacedAttribute<String> getSlingResourceType() {
		return resourceType;
	}

	public void setFieldName(String fieldName) {
		this.fieldName = fieldName;
	}
}
