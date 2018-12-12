package com.citytechinc.cq.component.annotations.widgets.rte;

/**
 * @see com.citytechinc.cq.component.annotations.widgets.RichTextEditor#undo()
 */
public @interface Undo {

    boolean undo() default true;

    boolean redo() default true;

    int maxUndoSteps() default 50;
}
