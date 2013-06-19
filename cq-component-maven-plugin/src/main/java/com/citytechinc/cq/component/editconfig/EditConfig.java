package com.citytechinc.cq.component.editconfig;

import com.citytechinc.cq.component.xml.NameSpacedAttribute;
import com.citytechinc.cq.component.xml.XmlElement;

public interface EditConfig extends XmlElement {

	public static final String FLOATING_DIALOG_MODE = "floating";
	public static final String INLINE_DIALOG_MODE = "inline";
	public static final String AUTO_DIALOG_MODE = "auto";

	public static final String EDIT_BAR_LAYOUT = "editbar";
	public static final String ROLLOVER_LAYOUT = "rollover";
	public static final String AUTO_LAYOUT = "auto";

	public NameSpacedAttribute<String> getActions();

	public NameSpacedAttribute<String> getDialogMode();

	public NameSpacedAttribute<String> getLayout();

	public NameSpacedAttribute<String> getEmptyText();

	public NameSpacedAttribute<Boolean> isInherit();
}
