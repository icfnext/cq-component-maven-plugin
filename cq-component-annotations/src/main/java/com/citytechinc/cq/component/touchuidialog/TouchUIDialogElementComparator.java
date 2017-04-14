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

import java.util.Comparator;

import com.citytechinc.cq.component.xml.XmlElement;

public class TouchUIDialogElementComparator implements Comparator<XmlElement> {

	public int compare(XmlElement xe1, XmlElement xe2) {
		TouchUIDialogElement dialogElement1 = (TouchUIDialogElement) xe1;
		TouchUIDialogElement dialogElement2 = (TouchUIDialogElement) xe2;

		return Double.compare(dialogElement1.getRanking(), dialogElement2.getRanking());
	}

}
