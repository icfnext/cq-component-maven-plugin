package com.citytechinc.cq.component.touchuidialog;

import com.citytechinc.cq.component.util.Constants;
import com.citytechinc.cq.component.xml.NameSpacedAttribute;

public class TouchUIDialog extends AbstractTouchUIDialogElement {

	public static final String RESOURCE_TYPE = "cq/gui/components/authoring/dialog";
	public static final String PRIMARY_TYPE = "nt:unstructured";

	private String fileName;
	private NameSpacedAttribute<String> title;
	private String helpPath;
	private String[] extraClientlibs;

	public TouchUIDialog(TouchUIDialogParameters parameters) {
		super(parameters);

		this.fileName = parameters.getFileName();
		this.title =
			new NameSpacedAttribute<String>(Constants.JCR_NS_URI, Constants.JCR_NS_PREFIX, parameters.getTitle());
		this.helpPath = parameters.getHelpPath();
		this.extraClientlibs = parameters.getExtraClientlibs();
	}

	public String getFileName() {
		return fileName + ".xml";
	}

	public NameSpacedAttribute<String> getTitle() {
		return title;
	}

	public String getHelpPath() {
		return helpPath;
	}

	public String getMode() {
		return "edit";
	}

	public String[] getExtraClientlibs() {
		return extraClientlibs;
	}

}
