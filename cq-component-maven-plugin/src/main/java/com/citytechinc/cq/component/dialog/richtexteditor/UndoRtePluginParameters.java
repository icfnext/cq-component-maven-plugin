package com.citytechinc.cq.component.dialog.richtexteditor;

public class UndoRtePluginParameters extends RtePluginParameters {
	public static final String UNDO = "undo";
	private int maxUndoSteps;

	public int getMaxUndoSteps() {
		return maxUndoSteps;
	}

	public void setMaxUndoSteps(int maxUndoSteps) {
		this.maxUndoSteps = maxUndoSteps;
	}

	@Override
	public String getFieldName() {
		return UNDO;
	}

	@Override
	public void setFieldName(String fieldName) {
		throw new UnsupportedOperationException("Field Name is static for UndoRTEPlugin");
	}
}
