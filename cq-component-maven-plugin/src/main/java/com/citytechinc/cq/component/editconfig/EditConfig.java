package com.citytechinc.cq.component.editconfig;

import java.util.List;
import java.util.Map;

public interface EditConfig {

	public static final String FLOATING_DIALOG_MODE = "floating";
	public static final String INLINE_DIALOG_MODE = "inline";
	public static final String AUTO_DIALOG_MODE = "auto";

	public static final String EDIT_BAR_LAYOUT = "editbar";
	public static final String ROLLOVER_LAYOUT = "rollover";
	public static final String AUTO_LAYOUT = "auto";

	public String getTitle();

	public List<String> getActions();

	public String getDialogMode();

	public String getLayout();

	public String getPrimaryType();

	public Map<String, String> getListeners();

}
