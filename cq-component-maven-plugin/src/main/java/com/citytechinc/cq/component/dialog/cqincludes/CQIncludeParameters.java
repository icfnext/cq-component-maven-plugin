package com.citytechinc.cq.component.dialog.cqincludes;

import com.citytechinc.cq.component.dialog.DialogElementParameters;
import com.citytechinc.cq.component.util.Constants;

public class CQIncludeParameters extends DialogElementParameters {
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
