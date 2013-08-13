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
package com.citytechinc.cq.component.dialog.factory;

import com.citytechinc.cq.component.dialog.maker.WidgetMaker;

public class WidgetMakerContext {

	private final Class<? extends WidgetMaker> maker;
	private final String xtype;

	public WidgetMakerContext(Class<? extends WidgetMaker> maker, String xtype) {
		this.maker = maker;
		this.xtype = xtype;
	}

	public Class<? extends WidgetMaker> getWidgetMaker() {
		return maker;
	}

	public String getXtype() {
		return xtype;
	}

}
