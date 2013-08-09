package com.citytechinc.cq.component.annotations.widgets.rte;

/**
 * @see com.citytechinc.cq.component.annotations.widgets.RichTextEditor#undo()
 */
public @interface Undo {

	public boolean undo() default true;

	public boolean redo() default true;

	public int maxUndoSteps() default 50;
}
