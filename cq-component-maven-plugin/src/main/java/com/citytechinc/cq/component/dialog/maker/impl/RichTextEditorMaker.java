package com.citytechinc.cq.component.dialog.maker.impl;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

import org.codehaus.plexus.util.StringUtils;

import javassist.CannotCompileException;
import javassist.ClassPool;
import javassist.CtClass;
import javassist.CtField;
import javassist.NotFoundException;

import com.citytechinc.cq.component.annotations.DialogField;
import com.citytechinc.cq.component.annotations.widgets.RichTextEditor;
import com.citytechinc.cq.component.annotations.widgets.rte.Edit;
import com.citytechinc.cq.component.annotations.widgets.rte.FindReplace;
import com.citytechinc.cq.component.annotations.widgets.rte.Format;
import com.citytechinc.cq.component.annotations.widgets.rte.Justify;
import com.citytechinc.cq.component.annotations.widgets.rte.Keys;
import com.citytechinc.cq.component.annotations.widgets.rte.Links;
import com.citytechinc.cq.component.annotations.widgets.rte.Lists;
import com.citytechinc.cq.component.annotations.widgets.rte.MiscTools;
import com.citytechinc.cq.component.annotations.widgets.rte.ParaFormat;
import com.citytechinc.cq.component.annotations.widgets.rte.ParaFormatFormat;
import com.citytechinc.cq.component.annotations.widgets.rte.Style;
import com.citytechinc.cq.component.annotations.widgets.rte.Styles;
import com.citytechinc.cq.component.annotations.widgets.rte.SubSuperscript;
import com.citytechinc.cq.component.annotations.widgets.rte.Table;
import com.citytechinc.cq.component.annotations.widgets.rte.Undo;
import com.citytechinc.cq.component.dialog.DialogElement;
import com.citytechinc.cq.component.dialog.exception.InvalidComponentFieldException;
import com.citytechinc.cq.component.dialog.impl.KeysRtePlugin;
import com.citytechinc.cq.component.dialog.impl.RichTextEditorWidget;
import com.citytechinc.cq.component.dialog.impl.RteParaFormat;
import com.citytechinc.cq.component.dialog.impl.RtePlugin;
import com.citytechinc.cq.component.dialog.impl.RteStyle;
import com.citytechinc.cq.component.dialog.impl.UndoRtePlugin;
import com.citytechinc.cq.component.dialog.impl.WidgetCollection;
import com.citytechinc.cq.component.dialog.maker.AbstractWidgetMaker;
import com.citytechinc.cq.component.dialog.maker.WidgetMaker;
import com.citytechinc.cq.component.maven.util.WidgetConfigHolder;

/**
 * 
 * Based on http://dev.day.com/docs/en/cq/current/administering/
 * configuring_rich_text_editor.html
 * 
 * @author paulmichelotti
 * 
 */
public class RichTextEditorMaker extends AbstractWidgetMaker {

	@Override
	public DialogElement make(String xtype, Field widgetField, CtField ctWidgetField, Class<?> containingClass,
		CtClass ctContainingClass, Map<Class<?>, WidgetConfigHolder> xtypeMap,
		Map<String, WidgetMaker> xTypeToWidgetMakerMap, ClassLoader classLoader, ClassPool classPool,
		boolean useDotSlashInName) throws ClassNotFoundException, InvalidComponentFieldException,
		CannotCompileException, NotFoundException {

		DialogField dialogFieldAnnotation = (DialogField) ctWidgetField.getAnnotation(DialogField.class);
		RichTextEditor rteAnnotation = (RichTextEditor) ctWidgetField.getAnnotation(RichTextEditor.class);

		String name = getNameForField(dialogFieldAnnotation, widgetField, useDotSlashInName);
		String fieldName = getFieldNameForField(dialogFieldAnnotation, widgetField);
		String fieldLabel = getFieldLabelForField(dialogFieldAnnotation, widgetField);
		String fieldDescription = getFieldDescriptionForField(dialogFieldAnnotation);
		Boolean isRequired = getIsRequiredForField(dialogFieldAnnotation);
		Map<String, String> additionalProperties = getAdditionalPropertiesForField(dialogFieldAnnotation);
		String defaultValue = getDefaultValueForField(dialogFieldAnnotation);
		boolean hideLabel = dialogFieldAnnotation.hideLabel();

		final List<DialogElement> rtePlugins = buildRtePlugins(rteAnnotation);

		return new RichTextEditorWidget(fieldLabel, fieldDescription, !isRequired, hideLabel, defaultValue, name,
			fieldName, additionalProperties, rtePlugins);
	}

	private List<DialogElement> buildRtePlugins(RichTextEditor rteAnnotation) {
		final List<DialogElement> rtePlugins = new ArrayList<DialogElement>();

		RtePlugin editPlugin = buildEditPlugin(rteAnnotation);
		RtePlugin findReplacePlugin = buildFindReplacePlugin(rteAnnotation);
		RtePlugin formatPlugin = buildFormatPlugin(rteAnnotation);
		RtePlugin imagePlugin = buildImagePlugin(rteAnnotation);
		RtePlugin keysPlugin = buildKeysPlugin(rteAnnotation);
		RtePlugin justifyPlugin = buildJustifyPlugin(rteAnnotation);
		RtePlugin linksPlugin = buildLinksPlugin(rteAnnotation);
		RtePlugin listsPlugin = buildListsPlugin(rteAnnotation);
		RtePlugin miscToolsPlugin = buildMiscToolsPlugin(rteAnnotation);
		RtePlugin paraFormat = buildParaFormatPlugin(rteAnnotation);
		RtePlugin spellcheckPlugin = buildSpellcheckPlugin(rteAnnotation);
		RtePlugin stylesPlugin = buildStylesRtePlugin(rteAnnotation);
		RtePlugin subSuperscriptPlugin = buildSubSuperscriptPlugin(rteAnnotation);
		RtePlugin tablePlugin = buildTablePlugin(rteAnnotation);
		RtePlugin undoPlugin = buildUndoPlugin(rteAnnotation);

		if (editPlugin != null) {
			rtePlugins.add(editPlugin);
		}
		if (findReplacePlugin != null) {
			rtePlugins.add(findReplacePlugin);
		}
		if (formatPlugin != null) {
			rtePlugins.add(formatPlugin);
		}
		if (imagePlugin != null) {
			rtePlugins.add(imagePlugin);
		}
		if (keysPlugin != null) {
			rtePlugins.add(keysPlugin);
		}
		if (justifyPlugin != null) {
			rtePlugins.add(justifyPlugin);
		}
		if (linksPlugin != null) {
			rtePlugins.add(linksPlugin);
		}
		if (listsPlugin != null) {
			rtePlugins.add(listsPlugin);
		}
		if (miscToolsPlugin != null) {
			rtePlugins.add(miscToolsPlugin);
		}
		if (paraFormat != null) {
			rtePlugins.add(paraFormat);
		}
		if (spellcheckPlugin != null) {
			rtePlugins.add(spellcheckPlugin);
		}
		if (stylesPlugin != null) {
			rtePlugins.add(stylesPlugin);
		}
		if (subSuperscriptPlugin != null) {
			rtePlugins.add(subSuperscriptPlugin);
		}
		if (tablePlugin != null) {
			rtePlugins.add(tablePlugin);
		}
		if (undoPlugin != null) {
			rtePlugins.add(undoPlugin);
		}

		return rtePlugins;
	}

	private RtePlugin buildSubSuperscriptPlugin(RichTextEditor rteAnnotation) {

		if (rteAnnotation.subsuperscript().length > 0) {
			SubSuperscript subSuperscriptAnnotation = rteAnnotation.subsuperscript()[0];

			List<String> features = new ArrayList<String>();

			if (subSuperscriptAnnotation.subscript()) {
				features.add("subscript");
			}
			if (subSuperscriptAnnotation.superscript()) {
				features.add("superscript");
			}

			return new RtePlugin("subsuperscript", features);
		}

		return null;

	}

	private RtePlugin buildSpellcheckPlugin(RichTextEditor rteAnnotation) {

		if (rteAnnotation.spellcheck().length > 0) {
			List<String> features = new ArrayList<String>();

			features.add("checktext");

			return new RtePlugin("spellcheck", features);
		}

		return null;

	}

	private RtePlugin buildListsPlugin(RichTextEditor rteAnnotation) {

		if (rteAnnotation.lists().length > 0) {
			Lists listsAnnotation = rteAnnotation.lists()[0];
			List<String> features = new ArrayList<String>();

			if (listsAnnotation.ordered()) {
				features.add("ordered");
			}
			if (listsAnnotation.unordered()) {
				features.add("unordered");
			}
			if (listsAnnotation.indent()) {
				features.add("indent");
			}
			if (listsAnnotation.outdent()) {
				features.add("outdent");
			}

			return new RtePlugin("lists", features);
		}

		return null;

	}

	private RtePlugin buildJustifyPlugin(RichTextEditor rteAnnotation) {

		if (rteAnnotation.justify().length > 0) {
			Justify justifyAnnotation = rteAnnotation.justify()[0];
			List<String> features = new ArrayList<String>();

			if (justifyAnnotation.justifyleft()) {
				features.add("justifyleft");
			}

			if (justifyAnnotation.justifycenter()) {
				features.add("justifycenter");
			}

			if (justifyAnnotation.justifyright()) {
				features.add("justifyright");
			}

			return new RtePlugin("justify", features);
		}

		return null;
	}

	private RtePlugin buildImagePlugin(RichTextEditor rteAnnotation) {

		if (rteAnnotation.image().length > 0) {
			List<String> features = new ArrayList<String>();

			features.add("image");

			return new RtePlugin("image", features);
		}

		return null;

	}

	private RtePlugin buildFormatPlugin(RichTextEditor rteAnnotation) {

		if (rteAnnotation.format().length > 0) {
			Format formatAnnotation = rteAnnotation.format()[0];
			List<String> features = new ArrayList<String>();

			if (formatAnnotation.bold()) {
				features.add("bold");
			}

			if (formatAnnotation.italic()) {
				features.add("italic");
			}

			if (formatAnnotation.underline()) {
				features.add("underline");
			}

			return new RtePlugin("format", features);
		}

		return null;

	}

	private RtePlugin buildFindReplacePlugin(RichTextEditor rteAnnotation) {

		if (rteAnnotation.findreplace().length > 0) {
			FindReplace findReplaceAnnotation = rteAnnotation.findreplace()[0];
			List<String> features = new ArrayList<String>();

			if (findReplaceAnnotation.find()) {
				features.add("find");
			}
			if (findReplaceAnnotation.replace()) {
				features.add("replace");
			}

			return new RtePlugin("findreplace", features);
		}

		return null;
	}

	private RtePlugin buildEditPlugin(RichTextEditor rteAnnotation) {

		if (rteAnnotation.edit().length > 0) {
			Edit[] editAnnotations = rteAnnotation.edit();
			Edit editAnnotation = editAnnotations[0];

			List<String> editFeatures = new ArrayList<String>();

			if (editAnnotation.cut()) {
				editFeatures.add("cut");
			}
			if (editAnnotation.copy()) {
				editFeatures.add("copy");
			}
			if (editAnnotation.pasteDefault()) {
				editFeatures.add("paste-default");
			}
			if (editAnnotation.pastePlaintext()) {
				editFeatures.add("paste-plaintext");
			}
			if (editAnnotation.pasteWordhtml()) {
				editFeatures.add("paste-wordhtml");
			}

			return new RtePlugin("edit", editFeatures);
		}

		return null;

	}

	private RtePlugin buildUndoPlugin(RichTextEditor rteAnnotation) {

		if (rteAnnotation.undo().length > 0) {
			Undo undoAnnotation = rteAnnotation.undo()[0];
			List<String> features = new ArrayList<String>();

			int maxUndoSteps = undoAnnotation.maxUndoSteps();

			if (undoAnnotation.undo()) {
				features.add("undo");
			}

			if (undoAnnotation.redo()) {
				features.add("redo");
			}

			return new UndoRtePlugin(features, maxUndoSteps);
		}

		return null;

	}

	private RtePlugin buildStylesRtePlugin(RichTextEditor rteAnnotation) {

		if (rteAnnotation.styles().length > 0) {
			Styles stylesAnnotation = rteAnnotation.styles()[0];

			List<DialogElement> styleList = new ArrayList<DialogElement>();

			for (int i = 0; i < stylesAnnotation.styles().length; i++) {
				String styleFieldName = "style" + i;

				styleList.add(new RteStyle(styleFieldName, stylesAnnotation.styles()[i].cssName(), stylesAnnotation
					.styles()[i].text()));
			}

			return new RtePlugin("styles", "*", styleList);
		}

		return null;
	}

	private RtePlugin buildParaFormatPlugin(RichTextEditor rteAnnotation) {

		if (rteAnnotation.paraformat().length > 0) {
			ParaFormat paraFormatAnnotation = rteAnnotation.paraformat()[0];

			List<DialogElement> formatList = null;

			if (paraFormatAnnotation.formats().length > 0) {
				formatList = new ArrayList<DialogElement>();

				for (int i = 0; i < paraFormatAnnotation.formats().length; i++) {
					String formatFieldName = "format" + i;

					ParaFormatFormat curFormat = paraFormatAnnotation.formats()[i];

					formatList.add(new RteParaFormat(formatFieldName, curFormat.tag(), curFormat.description()));
				}
			}

			List<DialogElement> formats = Arrays.asList(new DialogElement[] { new WidgetCollection(formatList,
				"formats") });

			return new RtePlugin("paraformat", "*", formats);
		}

		return null;
	}

	private RtePlugin buildKeysPlugin(RichTextEditor rteAnnotation) {

		if (rteAnnotation.keys().length > 0) {
			Keys keysAnnotation = rteAnnotation.keys()[0];

			if (keysAnnotation.tabSize().equals("")) {
				return new KeysRtePlugin(null);
			}

			return new KeysRtePlugin(keysAnnotation.tabSize());
		}

		return null;
	}

	private RtePlugin buildTablePlugin(RichTextEditor rteAnnotation) {

		if (rteAnnotation.table().length > 0) {
			Table tableAnnotation = rteAnnotation.table()[0];
			List<String> features = new ArrayList<String>();

			if (tableAnnotation.table()) {
				features.add("table");
			}
			if (tableAnnotation.removetable()) {
				features.add("removetable");
			}
			if (tableAnnotation.insertrow()) {
				features.add("insertrow");
			}
			if (tableAnnotation.removerow()) {
				features.add("removerow");
			}
			if (tableAnnotation.insertcolumn()) {
				features.add("insertcolumn");
			}
			if (tableAnnotation.removecolumn()) {
				features.add("removecolumn");
			}
			if (tableAnnotation.cellprops()) {
				features.add("cellprops");
			}
			if (tableAnnotation.mergecells()) {
				features.add("mergecells");
			}
			if (tableAnnotation.splitcell()) {
				features.add("splitcell");
			}
			if (tableAnnotation.selectrow()) {
				features.add("selectrow");
			}
			if (tableAnnotation.selectcolumns()) {
				features.add("selectcolumns");
			}

			List<DialogElement> containedStyles = new ArrayList<DialogElement>();

			List<DialogElement> tableStyles = null;
			List<DialogElement> cellStyles = null;

			if (tableAnnotation.tableStyles().length > 0) {
				tableStyles = new ArrayList<DialogElement>();

				for (int i = 0; i < tableAnnotation.tableStyles().length; i++) {
					Style curTableStyle = tableAnnotation.tableStyles()[i];
					String curFieldName = "tablestyle" + i;

					tableStyles.add(new RteStyle(curFieldName, curTableStyle.cssName(), curTableStyle.text()));
				}

				containedStyles.add(new WidgetCollection(tableStyles, "tableStyles"));
			}

			if (tableAnnotation.cellStyles().length > 0) {
				cellStyles = new ArrayList<DialogElement>();

				for (int i = 0; i < tableAnnotation.cellStyles().length; i++) {
					Style curCellStyle = tableAnnotation.cellStyles()[i];
					String curFieldName = "cellstyle" + i;

					cellStyles.add(new RteStyle(curFieldName, curCellStyle.cssName(), curCellStyle.text()));
				}
				containedStyles.add(new WidgetCollection(cellStyles, "cellStyles"));
			}

			if (containedStyles.size() > 0) {
				return new RtePlugin("table", "[" + StringUtils.join(features.iterator(), ",") + "]", containedStyles);
			}

			return new RtePlugin("table", features);
		}

		return null;
	}

	private RtePlugin buildLinksPlugin(RichTextEditor rteAnnotation) {

		if (rteAnnotation.links().length > 0) {
			Links linksAnnotation = rteAnnotation.links()[0];
			List<String> features = new ArrayList<String>();

			if (linksAnnotation.modifylink()) {
				features.add("modifylink");
			}
			if (linksAnnotation.unlink()) {
				features.add("unlink");
			}
			if (linksAnnotation.anchor()) {
				features.add("anchor");
			}

			return new RtePlugin("links", features);
		}

		return null;
	}

	// TODO: Build out mechanism to add own special characters per
	// http://dev.day.com/docs/en/cq/current/administering/configuring_rich_text_editor.html#Special
	// Characters
	private RtePlugin buildMiscToolsPlugin(RichTextEditor rteAnnotation) {

		if (rteAnnotation.misctools().length > 0) {
			MiscTools miscToolsAnnotation = rteAnnotation.misctools()[0];
			List<String> features = new ArrayList<String>();

			if (miscToolsAnnotation.specialchars()) {
				features.add("specialchars");
			}
			if (miscToolsAnnotation.sourceedit()) {
				features.add("sourceedit");
			}

			return new RtePlugin("misctools", features);
		}

		return null;

	}
}
