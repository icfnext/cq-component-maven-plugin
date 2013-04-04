package com.citytechinc.cq.component.annotations.widgets;

import com.citytechinc.cq.component.annotations.widgets.rte.*;

public @interface RichTextEditor {

	public Edit[] edit() default {};

	public FindReplace[] findreplace() default {};

	public Format[] format() default {};

	public Image[] image() default {};

	public Keys[] keys() default {};

	public Justify[] justify() default {};

	public Links[] links() default {};

	public Lists[] lists() default {};

	public MiscTools[] misctools() default {};

	public ParaFormat[] paraformat() default {};

	public SpellCheck[] spellcheck() default {};

	public Styles[] styles() default {};

	public SubSuperscript[] subsuperscript() default {};

	public Table[] table() default {};

	public Undo[] undo() default {};

}
