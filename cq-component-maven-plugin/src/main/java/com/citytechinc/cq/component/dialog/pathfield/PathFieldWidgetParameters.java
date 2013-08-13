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
package com.citytechinc.cq.component.dialog.pathfield;

import com.citytechinc.cq.component.dialog.widget.WidgetParameters;
import com.citytechinc.cq.component.util.Constants;

public class PathFieldWidgetParameters extends WidgetParameters {
	private boolean escapeAmp;
	private boolean hideTrigger;
	private boolean parBrowse;
	private String rootPath;
	private String rootTitle;
	private boolean showTitleInTree;

	public boolean isEscapeAmp() {
		return escapeAmp;
	}

	public void setEscapeAmp(boolean escapeAmp) {
		this.escapeAmp = escapeAmp;
	}

	public boolean isHideTrigger() {
		return hideTrigger;
	}

	public void setHideTrigger(boolean hideTrigger) {
		this.hideTrigger = hideTrigger;
	}

	public boolean isParBrowse() {
		return parBrowse;
	}

	public void setParBrowse(boolean parBrowse) {
		this.parBrowse = parBrowse;
	}

	public String getRootPath() {
		return rootPath;
	}

	public void setRootPath(String rootPath) {
		this.rootPath = rootPath;
	}

	public String getRootTitle() {
		return rootTitle;
	}

	public void setRootTitle(String rootTitle) {
		this.rootTitle = rootTitle;
	}

	public boolean isShowTitleInTree() {
		return showTitleInTree;
	}

	public void setShowTitleInTree(boolean showTitleInTree) {
		this.showTitleInTree = showTitleInTree;
	}

	@Override
	public String getPrimaryType() {
		return Constants.CQ_WIDGET;
	}

	@Override
	public void setPrimaryType(String primaryType) {
		throw new UnsupportedOperationException("PrimaryType is Static for PathFieldWidget");
	}

	@Override
	public String getXtype() {
		return PathFieldWidget.XTYPE;
	}

	@Override
	public void setXtype(String xtype) {
		throw new UnsupportedOperationException("xtype is Static for PathFieldWidget");
	}
}
