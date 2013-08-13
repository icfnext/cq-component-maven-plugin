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

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import com.citytechinc.cq.component.xml.AbstractXmlElement;
import com.citytechinc.cq.component.xml.XmlElement;

public abstract class AbstractDialogElement extends AbstractXmlElement implements DialogElement {
	private double ranking;

	public AbstractDialogElement(DialogElementParameters parameters) {
		super(parameters);
		if (containedElements != null) {
			Collections.sort(containedElements, new DialogElementComparator());
		}
		if (parameters.getListeners() != null) {
			List<XmlElement> newElements = new ArrayList<XmlElement>();
			if (containedElements != null) {
				newElements.addAll(containedElements);
			}
			newElements.add(parameters.getListeners());
			containedElements = newElements;
		}
	}

	public void setFieldName(String fieldName) {
		this.fieldName = fieldName;
	}

	public void setRanking(double ranking) {
		this.ranking = ranking;
	}

	public double getRanking() {
		return ranking;
	}
}
