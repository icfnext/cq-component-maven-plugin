package com.citytechinc.cq.component.dialog.impl;

import java.util.List;

public class UndoRtePlugin extends RtePlugin {

	public static final String UNDO = "undo";

	private final int maxUndoSteps;

	public UndoRtePlugin(List<String> features, int maxUndoSteps) {

		super(UNDO, features);

		this.maxUndoSteps = maxUndoSteps;

	}

	public int getMaxUndoSteps() {
		return maxUndoSteps;
	}
}
