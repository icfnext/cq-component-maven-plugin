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
package com.citytechinc.cq.component.dialog.cqincludes;

import com.citytechinc.cq.component.dialog.DefaultDialogElementParameters;
import com.citytechinc.cq.component.util.Constants;

public class CQIncludeParameters extends DefaultDialogElementParameters {
	private String path;

	public String getPath() {
		if (path == null) {
			return "";
		}
		return path;
	}

	public void setPath(String path) {
		this.path = path;
	}

	@Override
	public void setPrimaryType(String primaryType) {
		throw new UnsupportedOperationException("PrimaryType is Static for CQInclude");
	}

	@Override
	public String getPrimaryType() {
		return Constants.CQ_WIDGET;
	}
}
