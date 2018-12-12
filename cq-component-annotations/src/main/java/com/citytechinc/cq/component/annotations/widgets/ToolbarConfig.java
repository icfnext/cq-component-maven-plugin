package com.citytechinc.cq.component.annotations.widgets;

public @interface ToolbarConfig {

    String FORMAT_BOLD = "format#bold";

    String FORMAT_ITALIC = "format#italic";

    String FORMAT_UNDERLINE = "format#underline";

    String SUBSUPERSCRIPT_SUBSCRIPT = "subsuperscript#subscript";

    String SUBSUPERSCRIPT_SUPERSCRIPT = "subsuperscript#superscript";

    String SEPERATOR = "-";

    String EDIT_CUT = "edit#cut";

    String EDIT_COPY = "edit#copy";

    String EDIT_PASTE_DEFAULT = "edit#paste-default";

    String EDIT_PASTE_PLAINTEXT = "edit#paste-plaintext";

    String EDIT_PASTE_WORDHTML = "edit#paste-wordhtml";

    String LINKS_MODIFYLINK = "links#modifylink";

    String LINKS_UNLINK = "links#unlink";

    String LINKS_ANCHOR = "links#anchor";

    String FINDREPLACE_FIND = "findreplace#find";

    String FINDREPLACE_REPLACE = "findreplace#replace";

    String UNDO_UNDO = "undo#undo";

    String UNDO_REDO = "undo#redo";

    String JUSTIFY_JUSITFYLEFT = "justify#justifyleft";

    String JUSTIFY_JUSITFYCENTER = "justify#justifycenter";

    String JUSTIFY_JUSITFYRIGHT = "justify#justifyright";

    String LISTS_UNORDERED = "lists#unordered";

    String LISTS_ORDERED = "lists#ordered";

    String LISTS_OUTDENT = "lists#outdent";

    String LISTS_INDENT = "lists#indent";

    String TABLE_CREATEOREDIT = "table#createoredit";

    String IMAGE_IMAGEPROPS = "image#imageProps";

    String SPELLCHECK_CHECKTEXT = "spellcheck#checktext";

    String MISCTOOLS_SPECIALCHARS = "misctools#specialchars";

    String MISCTOOLS_SOURCEEDIT = "misctools#sourceedit";

    String STYLES = "#styles";

    String PARAFORMAT = "#paraformat";

    String FULLSCREEN_START = "fullscreen#start";

    String FULLSCREEN_FINISH = "fullscreen#finish";

    String[] toolbars() default {};

    Popover[] popovers() default {};
}
