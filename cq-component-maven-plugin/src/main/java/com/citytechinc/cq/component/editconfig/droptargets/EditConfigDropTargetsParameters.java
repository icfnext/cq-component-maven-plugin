package com.citytechinc.cq.component.editconfig.droptargets;

import com.citytechinc.cq.component.util.Constants;
import com.citytechinc.cq.component.xml.DefaultXmlElementParameters;

public class EditConfigDropTargetsParameters extends DefaultXmlElementParameters {
	@Override
	public String getNameSpace() {
		return Constants.CQ_NS_URI;
	}

	@Override
	public void setNameSpace(String nameSpace) {
		throw new UnsupportedOperationException("nameSpace is Static for EditConfigDropTargets");
	}

	@Override
	public String getFieldName() {
		return "cq:dropTargets";
	}

	@Override
	public void setFieldName(String fieldName) {
		throw new UnsupportedOperationException("fieldName is Static for EditConfigDropTargets");
	}

	@Override
	public String getPrimaryType() {
		return Constants.NT_UNSTRUCTURED;
	}

	@Override
	public void setPrimaryType(String primaryType) {
		throw new UnsupportedOperationException("PrimaryType is Static for EditConfigDropTargets");
	}
}
