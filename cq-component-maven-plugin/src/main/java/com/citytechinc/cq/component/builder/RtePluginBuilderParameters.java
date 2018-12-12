package com.citytechinc.cq.component.builder;

import com.citytechinc.cq.component.annotations.widgets.RichTextEditor;
import com.citytechinc.cq.component.annotations.widgets.rte.Edit;
import com.citytechinc.cq.component.annotations.widgets.rte.FindReplace;
import com.citytechinc.cq.component.annotations.widgets.rte.Format;
import com.citytechinc.cq.component.annotations.widgets.rte.Image;
import com.citytechinc.cq.component.annotations.widgets.rte.Justify;
import com.citytechinc.cq.component.annotations.widgets.rte.Keys;
import com.citytechinc.cq.component.annotations.widgets.rte.Links;
import com.citytechinc.cq.component.annotations.widgets.rte.Lists;
import com.citytechinc.cq.component.annotations.widgets.rte.MiscTools;
import com.citytechinc.cq.component.annotations.widgets.rte.ParaFormat;
import com.citytechinc.cq.component.annotations.widgets.rte.SpellCheck;
import com.citytechinc.cq.component.annotations.widgets.rte.Style;
import com.citytechinc.cq.component.annotations.widgets.rte.SubSuperscript;
import com.citytechinc.cq.component.annotations.widgets.rte.Table;
import com.citytechinc.cq.component.annotations.widgets.rte.UISettings;
import com.citytechinc.cq.component.annotations.widgets.rte.Undo;
import com.citytechinc.cq.component.editconfig.annotations.inplaceeditors.TextEditor;

public class RtePluginBuilderParameters {
	private Edit[] edit;
	private FindReplace[] findreplace;
	private Format[] format;
	private Image[] image;
	private Keys[] keys;
	private Justify[] justify;
	private Links[] links;
	private Lists[] lists;
	private MiscTools[] misctools;
	private ParaFormat[] paraformat;
	private SpellCheck[] spellcheck;
	private Style[] styles;
	private SubSuperscript[] subsuperscript;
	private Table[] table;
	private Undo[] undo;
	private UISettings[] uiSettings;

	public RtePluginBuilderParameters() {
	}

	public RtePluginBuilderParameters(RichTextEditor rteTextEditor) {
		this.edit = rteTextEditor.edit();
		this.findreplace = rteTextEditor.findreplace();
		this.format = rteTextEditor.format();
		this.image = rteTextEditor.image();
		this.keys = rteTextEditor.keys();
		this.justify = rteTextEditor.justify();
		this.links = rteTextEditor.links();
		this.lists = rteTextEditor.lists();
		this.misctools = rteTextEditor.misctools();
		this.paraformat = rteTextEditor.paraformat();
		this.spellcheck = rteTextEditor.spellcheck();
		this.styles = rteTextEditor.styles();
		this.subsuperscript = rteTextEditor.subsuperscript();
		this.table = rteTextEditor.table();
		this.undo = rteTextEditor.undo();
		this.uiSettings = rteTextEditor.uiSettings();
	}

	public RtePluginBuilderParameters(TextEditor textEditor) {
		this.edit = textEditor.edit();
		this.findreplace = textEditor.findreplace();
		this.format = textEditor.format();
		this.image = textEditor.image();
		this.keys = textEditor.keys();
		this.justify = textEditor.justify();
		this.links = textEditor.links();
		this.lists = textEditor.lists();
		this.misctools = textEditor.misctools();
		this.paraformat = textEditor.paraformat();
		this.spellcheck = textEditor.spellcheck();
		this.styles = textEditor.styles();
		this.subsuperscript = textEditor.subsuperscript();
		this.undo = textEditor.undo();
		this.uiSettings = textEditor.uiSettings();
	}

	public Edit[] getEdit() {
		return edit;
	}

	public void setEdit(Edit[] edit) {
		this.edit = edit;
	}

	public FindReplace[] getFindreplace() {
		return findreplace;
	}

	public void setFindreplace(FindReplace[] findreplace) {
		this.findreplace = findreplace;
	}

	public Format[] getFormat() {
		return format;
	}

	public void setFormat(Format[] format) {
		this.format = format;
	}

	public Image[] getImage() {
		return image;
	}

	public void setImage(Image[] image) {
		this.image = image;
	}

	public Keys[] getKeys() {
		return keys;
	}

	public void setKeys(Keys[] keys) {
		this.keys = keys;
	}

	public Justify[] getJustify() {
		return justify;
	}

	public void setJustify(Justify[] justify) {
		this.justify = justify;
	}

	public Links[] getLinks() {
		return links;
	}

	public void setLinks(Links[] links) {
		this.links = links;
	}

	public Lists[] getLists() {
		return lists;
	}

	public void setLists(Lists[] lists) {
		this.lists = lists;
	}

	public MiscTools[] getMisctools() {
		return misctools;
	}

	public void setMisctools(MiscTools[] misctools) {
		this.misctools = misctools;
	}

	public ParaFormat[] getParaformat() {
		return paraformat;
	}

	public void setParaformat(ParaFormat[] paraformat) {
		this.paraformat = paraformat;
	}

	public SpellCheck[] getSpellcheck() {
		return spellcheck;
	}

	public void setSpellcheck(SpellCheck[] spellcheck) {
		this.spellcheck = spellcheck;
	}

	public Style[] getStyles() {
		return styles;
	}

	public void setStyles(Style[] styles) {
		this.styles = styles;
	}

	public SubSuperscript[] getSubsuperscript() {
		return subsuperscript;
	}

	public void setSubsuperscript(SubSuperscript[] subsuperscript) {
		this.subsuperscript = subsuperscript;
	}

	public Table[] getTable() {
		return table;
	}

	public void setTable(Table[] table) {
		this.table = table;
	}

	public Undo[] getUndo() {
		return undo;
	}

	public void setUndo(Undo[] undo) {
		this.undo = undo;
	}

	public UISettings[] getUiSettings() {
		return uiSettings;
	}

	public void setUiSettings(UISettings[] uiSettings) {
		this.uiSettings = uiSettings;
	}
}
