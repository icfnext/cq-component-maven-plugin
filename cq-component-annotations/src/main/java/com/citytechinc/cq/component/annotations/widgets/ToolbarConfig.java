package com.citytechinc.cq.component.annotations.widgets;

public @interface ToolbarConfig {
	public static final String FORMAT_BOLD = "format#bold";
	public static final String FORMAT_ITALIC = "format#italic";
	public static final String FORMAT_UNDERLINE = "format#underline";
	public static final String SUBSUPERSCRIPT_SUBSCRIPT = "subsuperscript#subscript";
	public static final String SUBSUPERSCRIPT_SUPERSCRIPT = "subsuperscript#superscript";
	public static final String SEPERATOR = "-";
	public static final String EDIT_CUT = "edit#cut";
	public static final String EDIT_COPY = "edit#copy";
	public static final String EDIT_PASTE_DEFAULT = "edit#paste-default";
	public static final String EDIT_PASTE_PLAINTEXT = "edit#paste-plaintext";
	public static final String EDIT_PASTE_WORDHTML = "edit#paste-wordhtml";
	public static final String LINKS_MODIFYLINK = "links#modifylink";
	public static final String LINKS_UNLINK = "links#unlink";
	public static final String LINKS_ANCHOR = "links#anchor";
	public static final String FINDREPLACE_FIND = "findreplace#find";
	public static final String FINDREPLACE_REPLACE = "findreplace#replace";
	public static final String UNDO_UNDO = "undo#undo";
	public static final String UNDO_REDO = "undo#redo";
	public static final String JUSTIFY_JUSITFYLEFT = "justify#justifyleft";
	public static final String JUSTIFY_JUSITFYCENTER = "justify#justifycenter";
	public static final String JUSTIFY_JUSITFYRIGHT = "justify#justifyright";
	public static final String LISTS_UNORDERED = "lists#unordered";
	public static final String LISTS_ORDERED = "lists#ordered";
	public static final String LISTS_OUTDENT = "lists#outdent";
	public static final String LISTS_INDENT = "lists#indent";
	public static final String TABLE_CREATEOREDIT = "table#createoredit";
	public static final String IMAGE_IMAGEPROPS = "image#imageProps";
	public static final String SPELLCHECK_CHECKTEXT = "spellcheck#checktext";
	public static final String MISCTOOLS_SPECIALCHARS = "misctools#specialchars";
	public static final String MISCTOOLS_SOURCEEDIT = "misctools#sourceedit";
	public static final String STYLES = "#styles";
	public static final String PARAFORMAT = "#paraformat";
	public static final String FULLSCREEN_START = "fullscreen#start";
	public static final String FULLSCREEN_FINISH = "fullscreen#finish";

	public String[] toolbars() default {};

	public Popover[] popovers() default {};
}
