/**
 *    Copyright 2013 CITYTECH, Inc.
 *
 *    Licensed under the Apache License, Version 2.0 (the "License");
 *    you may not use this file except in compliance with the License.
 *    You may obtain a copy of the License at
 *
 *        http://www.apache.org/licenses/LICENSE-2.0
 *
 *    Unless required by applicable law or agreed to in writing, software
 *    distributed under the License is distributed on an "AS IS" BASIS,
 *    WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *    See the License for the specific language governing permissions and
 *    limitations under the License.
 */
package com.citytechinc.cq.component.dialog.richtexteditor;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.codehaus.plexus.util.StringUtils;

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
import com.citytechinc.cq.component.dialog.maker.AbstractWidgetMaker;
import com.citytechinc.cq.component.dialog.maker.WidgetMakerParameters;
import com.citytechinc.cq.component.dialog.widgetcollection.WidgetCollection;
import com.citytechinc.cq.component.dialog.widgetcollection.WidgetCollectionParameters;

/**
 *
 * Based on http://dev.day.com/docs/en/cq/current/administering/
 * configuring_rich_text_editor.html
 *
 */
public class RichTextEditorMaker extends AbstractWidgetMaker<RichTextEditorWidgetParameters> {
	private static final String ALL_FEATURES = "*";

	public RichTextEditorMaker(WidgetMakerParameters parameters) {
		super(parameters);
	}

	@Override
	public DialogElement make(RichTextEditorWidgetParameters widgetParameters) throws ClassNotFoundException {

		RichTextEditor rteAnnotation = getAnnotation(RichTextEditor.class);

		widgetParameters.setContainedElements(Arrays.asList(new DialogElement[] { buildRtePlugins(rteAnnotation) }));

		return new RichTextEditorWidget(widgetParameters);

	}

	private RtePlugins buildRtePlugins(RichTextEditor rteAnnotation) {
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

		RtePluginsParameters rtePluginsParameters = new RtePluginsParameters();

		rtePluginsParameters.setContainedElements(rtePlugins);

		return new RtePlugins(rtePluginsParameters);
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
			RtePluginParameters widgetParameters = new RtePluginParameters();
			widgetParameters.setFieldName("subsuperscript");
			widgetParameters.setFeatures(convertFeatures(features));
			return new RtePlugin(widgetParameters);
		}

		return null;

	}

	private RtePlugin buildSpellcheckPlugin(RichTextEditor rteAnnotation) {

		if (rteAnnotation.spellcheck().length > 0) {
			List<String> features = new ArrayList<String>();

			features.add("checktext");
			RtePluginParameters widgetParameters = new RtePluginParameters();
			widgetParameters.setFieldName("spellcheck");
			widgetParameters.setFeatures(convertFeatures(features));
			return new RtePlugin(widgetParameters);
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
			RtePluginParameters widgetParameters = new RtePluginParameters();
			widgetParameters.setFieldName("lists");
			widgetParameters.setFeatures(convertFeatures(features));
			return new RtePlugin(widgetParameters);
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

			RtePluginParameters widgetParameters = new RtePluginParameters();
			widgetParameters.setFieldName("justify");
			widgetParameters.setFeatures(convertFeatures(features));
			return new RtePlugin(widgetParameters);
		}

		return null;
	}

	private RtePlugin buildImagePlugin(RichTextEditor rteAnnotation) {

		if (rteAnnotation.image().length > 0) {
			List<String> features = new ArrayList<String>();

			features.add("image");
			RtePluginParameters widgetParameters = new RtePluginParameters();
			widgetParameters.setFieldName("image");
			widgetParameters.setFeatures(convertFeatures(features));
			return new RtePlugin(widgetParameters);
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
			RtePluginParameters widgetParameters = new RtePluginParameters();
			widgetParameters.setFieldName("format");
			widgetParameters.setFeatures(convertFeatures(features));
			return new RtePlugin(widgetParameters);
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
			RtePluginParameters widgetParameters = new RtePluginParameters();
			widgetParameters.setFieldName("findreplace");
			widgetParameters.setFeatures(convertFeatures(features));
			return new RtePlugin(widgetParameters);
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
			RtePluginParameters widgetParameters = new RtePluginParameters();
			widgetParameters.setFieldName("edit");
			widgetParameters.setFeatures(convertFeatures(editFeatures));
			return new RtePlugin(widgetParameters);
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

			UndoRtePluginParameters undoParameters = new UndoRtePluginParameters();
			undoParameters.setFeatures(convertFeatures(features));
			undoParameters.setMaxUndoSteps(maxUndoSteps);

			return new UndoRtePlugin(undoParameters);
		}

		return null;

	}

	private RtePlugin buildStylesRtePlugin(RichTextEditor rteAnnotation) {

		if (rteAnnotation.styles().length > 0) {
			Styles stylesAnnotation = rteAnnotation.styles()[0];

			List<DialogElement> styleList = new ArrayList<DialogElement>();

			for (int i = 0; i < stylesAnnotation.styles().length; i++) {
				String styleFieldName = "style" + i;

				RteStyleParameters styleParameters = new RteStyleParameters();
				styleParameters.setFieldName(styleFieldName);
				styleParameters.setCssName(stylesAnnotation.styles()[i].cssName());
				styleParameters.setText(stylesAnnotation.styles()[i].text());
				styleList.add(new RteStyle(styleParameters));
			}
			WidgetCollectionParameters wcp = new WidgetCollectionParameters();
			wcp.setContainedElements(styleList);
			wcp.setFieldName("styles");

			List<DialogElement> stylesDialogElements = Arrays.asList(new DialogElement[] { new WidgetCollection(wcp) });
			RtePluginParameters widgetParameters = new RtePluginParameters();
			widgetParameters.setFieldName("styles");
			widgetParameters.setFeatures(ALL_FEATURES);
			widgetParameters.setContainedElements(stylesDialogElements);
			return new RtePlugin(widgetParameters);
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
					RteParaFormatParameters paraParameters = new RteParaFormatParameters();
					paraParameters.setFieldName(formatFieldName);
					paraParameters.setTag(curFormat.tag());
					paraParameters.setDescription(curFormat.description());
					formatList.add(new RteParaFormat(paraParameters));
				}
			}

			WidgetCollectionParameters wcp = new WidgetCollectionParameters();
			wcp.setContainedElements(formatList);
			wcp.setFieldName("formats");

			List<DialogElement> formats = Arrays.asList(new DialogElement[] { new WidgetCollection(wcp) });
			RtePluginParameters widgetParameters = new RtePluginParameters();
			widgetParameters.setFieldName("paraformat");
			widgetParameters.setFeatures(ALL_FEATURES);
			widgetParameters.setContainedElements(formats);
			return new RtePlugin(widgetParameters);
		}

		return null;
	}

	private RtePlugin buildKeysPlugin(RichTextEditor rteAnnotation) {

		if (rteAnnotation.keys().length > 0) {
			Keys keysAnnotation = rteAnnotation.keys()[0];

			KeysRtePluginParameters keysParameters = new KeysRtePluginParameters();
			if (!keysAnnotation.tabSize().equals("")) {
				keysParameters.setTabSize(keysAnnotation.tabSize());
			}
			return new KeysRtePlugin(keysParameters);
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

					RteStyleParameters styleParameters = new RteStyleParameters();
					styleParameters.setFieldName(curFieldName);
					styleParameters.setCssName(curTableStyle.cssName());
					styleParameters.setText(curTableStyle.text());

					tableStyles.add(new RteStyle(styleParameters));
				}
				WidgetCollectionParameters wcp = new WidgetCollectionParameters();
				wcp.setContainedElements(tableStyles);
				wcp.setFieldName("tableStyles");
				containedStyles.add(new WidgetCollection(wcp));
			}

			if (tableAnnotation.cellStyles().length > 0) {
				cellStyles = new ArrayList<DialogElement>();

				for (int i = 0; i < tableAnnotation.cellStyles().length; i++) {
					Style curCellStyle = tableAnnotation.cellStyles()[i];
					String curFieldName = "cellstyle" + i;

					RteStyleParameters styleParameters = new RteStyleParameters();
					styleParameters.setFieldName(curFieldName);
					styleParameters.setCssName(curCellStyle.cssName());
					styleParameters.setText(curCellStyle.text());

					cellStyles.add(new RteStyle(styleParameters));
				}
				WidgetCollectionParameters wcp = new WidgetCollectionParameters();
				wcp.setContainedElements(cellStyles);
				wcp.setFieldName("cellStyles");
				containedStyles.add(new WidgetCollection(wcp));
			}
			RtePluginParameters widgetParameters = new RtePluginParameters();
			widgetParameters.setFieldName("table");
			widgetParameters.setFeatures(convertFeatures(features));
			if (containedStyles.size() > 0) {
				widgetParameters.setContainedElements(containedStyles);
			}

			return new RtePlugin(widgetParameters);
		}

		return null;
	}

	private String convertFeatures(List<String> features) {
		if (features.size() > 0) {
			return "[" + StringUtils.join(features.iterator(), ",") + "]";
		} else {
			return "-";
		}
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
			RtePluginParameters widgetParameters = new RtePluginParameters();
			widgetParameters.setFieldName("links");
			widgetParameters.setFeatures(convertFeatures(features));
			return new RtePlugin(widgetParameters);
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
			RtePluginParameters widgetParameters = new RtePluginParameters();
			widgetParameters.setFieldName("misctools");
			widgetParameters.setFeatures(convertFeatures(features));
			return new RtePlugin(widgetParameters);
		}

		return null;

	}
}
