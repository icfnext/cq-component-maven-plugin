package com.citytechinc.cq.component.annotations.widgets.rte;

//TODO: Finish
public @interface Undo {

	public boolean undo() default true;

	public boolean redo() default true;

	public int maxUndoSteps() default 50;
}
