package com.citytechinc.cq.component.annotations.widgets.rte;

/**
 * @see com.citytechinc.cq.component.annotations.widgets.RichTextEditor#edit()
 */
public @interface Edit {

    boolean cut() default true;

    boolean copy() default true;

    boolean pasteDefault() default true;

    boolean pastePlaintext() default true;

    boolean pasteWordhtml() default true;

    /**
     * @see <a href="https://docs.adobe.com/docs/en/aem/6-2/administer/operations/page-authoring/rich-text-editor.html#Default%20Paste%20Mode">Default Paste Mode</a>
     * <p>
     * Currently only supported in Classic UI
     * <p>
     * Possible values are 'wordhtml' (default), 'plaintext' or 'browser'
     */
    String defaultPasteMode() default "wordhtml";
}
