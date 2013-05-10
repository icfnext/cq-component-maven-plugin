package com.citytechinc.cq.component.annotations.widgets;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import com.citytechinc.cq.component.annotations.widgets.rte.*;

@Retention(RetentionPolicy.CLASS)
@Target(ElementType.FIELD)
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
