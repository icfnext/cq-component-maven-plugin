package com.citytechinc.cq.component.touchuidialog.widget.selection.options;

import java.util.ArrayList;
import java.util.List;

import com.citytechinc.cq.component.touchuidialog.DefaultTouchUIDialogElementParameters;
import com.citytechinc.cq.component.xml.XmlElement;

public class OptionsParameters extends DefaultTouchUIDialogElementParameters {

	private List<Option> options;

	@Override
	public String getFieldName() {
		return Options.OPTIONS_FIELD_NAME;
	}

	@Override
	public void setFieldName(String fieldName) {
		throw new UnsupportedOperationException("Field Name is static for OptionsParameters");
	}

	@Override
	public String getPrimaryType() {
		return "nt:unstructured";
	}

	@Override
	public void setPrimaryType(String primaryType) {
		throw new UnsupportedOperationException("Primary Type is static for OptionsParameters");
	}

	public void setOptions(List<Option> options) {
		this.options = options;
	}

	@Override
	public List<? extends XmlElement> getContainedElements() {
		List<XmlElement> allContainedElements = new ArrayList<XmlElement>();

		allContainedElements.addAll(options);

		if (containedElements != null) {
			allContainedElements.addAll(containedElements);
		}

		return allContainedElements;
	}

}
