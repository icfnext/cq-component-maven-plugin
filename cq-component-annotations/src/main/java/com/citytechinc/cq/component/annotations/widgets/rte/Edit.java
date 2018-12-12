package com.citytechinc.cq.component.annotations.widgets.rte;

/**
 * @see com.citytechinc.cq.component.annotations.widgets.RichTextEditor#edit()
 */
public @interface Edit {

	public boolean cut() default true;

	public boolean copy() default true;

	public boolean pasteDefault() default true;

	public boolean pastePlaintext() default true;

	public boolean pasteWordhtml() default true;

	/**
	 * @see <a href="https://docs.adobe.com/docs/en/aem/6-2/administer/operations/page-authoring/rich-text-editor.html#Default%20Paste%20Mode">Default Paste Mode</a>
	 *
	 * Currently only supported in Classic UI
	 *
	 * Possible values are 'wordhtml' (default), 'plaintext' or 'browser'
	 */
	public String defaultPasteMode() default "wordhtml";
}
