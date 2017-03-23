/**
 * Copyright 2017 ICF Olson
 * <p>
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * <p>
 * http://www.apache.org/licenses/LICENSE-2.0
 * <p>
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.citytechinc.cq.component.builder;

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
import com.citytechinc.cq.component.annotations.widgets.rte.Style;
import com.citytechinc.cq.component.annotations.widgets.rte.SubSuperscript;
import com.citytechinc.cq.component.annotations.widgets.rte.Table;
import com.citytechinc.cq.component.annotations.widgets.rte.Undo;
import com.citytechinc.cq.component.dialog.DialogElement;
import com.citytechinc.cq.component.dialog.richtexteditor.EditRtePlugin;
import com.citytechinc.cq.component.dialog.richtexteditor.EditRtePluginParameters;
import com.citytechinc.cq.component.dialog.richtexteditor.KeysRtePlugin;
import com.citytechinc.cq.component.dialog.richtexteditor.KeysRtePluginParameters;
import com.citytechinc.cq.component.dialog.richtexteditor.RteParaFormat;
import com.citytechinc.cq.component.dialog.richtexteditor.RteParaFormatParameters;
import com.citytechinc.cq.component.dialog.richtexteditor.RtePlugin;
import com.citytechinc.cq.component.dialog.richtexteditor.RtePluginParameters;
import com.citytechinc.cq.component.dialog.richtexteditor.RtePlugins;
import com.citytechinc.cq.component.dialog.richtexteditor.RtePluginsParameters;
import com.citytechinc.cq.component.dialog.richtexteditor.RteStyle;
import com.citytechinc.cq.component.dialog.richtexteditor.RteStyleParameters;
import com.citytechinc.cq.component.dialog.richtexteditor.UndoRtePlugin;
import com.citytechinc.cq.component.dialog.richtexteditor.UndoRtePluginParameters;
import com.citytechinc.cq.component.dialog.widgetcollection.WidgetCollection;
import com.citytechinc.cq.component.dialog.widgetcollection.WidgetCollectionParameters;
import org.codehaus.plexus.util.StringUtils;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public final class RtePluginBuilder {

    private static final String ALL_FEATURES = "*";

    private final RichTextEditor rteAnnotation;

    public RtePluginBuilder(RichTextEditor rteAnnotation) {
        this.rteAnnotation = rteAnnotation;
    }

    public RtePlugins build() {
        final List<RtePlugin> plugins = new ArrayList<RtePlugin>();

        plugins.add(buildEditPlugin());
        plugins.add(buildFindReplacePlugin());
        plugins.add(buildFormatPlugin());
        plugins.add(buildFullScreenPlugin());
        plugins.add(buildImagePlugin());
        plugins.add(buildKeysPlugin());
        plugins.add(buildJustifyPlugin());
        plugins.add(buildLinksPlugin());
        plugins.add(buildListsPlugin());
        plugins.add(buildMiscToolsPlugin());
        plugins.add(buildParaFormatPlugin());
        plugins.add(buildSpellcheckPlugin());
        plugins.add(buildStylesRtePlugin());
        plugins.add(buildSubSuperscriptPlugin());
        plugins.add(buildTablePlugin());
        plugins.add(buildUndoPlugin());

        final List<DialogElement> elements = new ArrayList<DialogElement>();

        for (final RtePlugin plugin : plugins) {
            if (plugin != null) {
                elements.add(plugin);
            }
        }

        final RtePluginsParameters rtePluginsParameters = new RtePluginsParameters();

        rtePluginsParameters.setContainedElements(elements);

        return new RtePlugins(rtePluginsParameters);
    }

    private RtePlugin buildSubSuperscriptPlugin() {
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

    private RtePlugin buildSpellcheckPlugin() {
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

    private RtePlugin buildListsPlugin() {
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

    private RtePlugin buildJustifyPlugin() {
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

    private RtePlugin buildImagePlugin() {
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

    private RtePlugin buildFormatPlugin() {
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

    private RtePlugin buildFindReplacePlugin() {
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

    private RtePlugin buildEditPlugin() {
        if (rteAnnotation.edit().length > 0) {
            Edit[] editAnnotations = rteAnnotation.edit();
            Edit editAnnotation = editAnnotations[0];
            String defaultPasteMode = editAnnotation.defaultPasteMode();

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
            EditRtePluginParameters widgetParameters = new EditRtePluginParameters();
            widgetParameters.setDefaultPasteMode(defaultPasteMode);
            widgetParameters.setFeatures(convertFeatures(editFeatures));
            return new EditRtePlugin(widgetParameters);
        }

        return null;

    }

    private RtePlugin buildUndoPlugin() {
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

    private RtePlugin buildStylesRtePlugin() {
        if (rteAnnotation.styles().length > 0) {
            List<DialogElement> styleList = new ArrayList<DialogElement>();

            for (int i = 0; i < rteAnnotation.styles().length; i++) {
                String styleFieldName = "style" + i;

                RteStyleParameters styleParameters = new RteStyleParameters();
                styleParameters.setFieldName(styleFieldName);
                styleParameters.setCssName(rteAnnotation.styles()[i].cssName());
                styleParameters.setText(rteAnnotation.styles()[i].text());
                styleList.add(new RteStyle(styleParameters));
            }

            WidgetCollectionParameters wcp = new WidgetCollectionParameters();
            wcp.setContainedElements(styleList);
            wcp.setFieldName("styles");

            List<DialogElement> stylesDialogElements = Arrays.asList(new DialogElement[]{ new WidgetCollection(wcp) });
            RtePluginParameters widgetParameters = new RtePluginParameters();
            widgetParameters.setFieldName("styles");
            widgetParameters.setFeatures(ALL_FEATURES);
            widgetParameters.setContainedElements(stylesDialogElements);
            return new RtePlugin(widgetParameters);
        }

        return null;
    }

    private RtePlugin buildParaFormatPlugin() {
        if (rteAnnotation.paraformat().length > 0) {
            List<DialogElement> formatList = new ArrayList<DialogElement>();

            for (int i = 0; i < rteAnnotation.paraformat().length; i++) {
                String formatFieldName = "format" + i;

                ParaFormat curFormat = rteAnnotation.paraformat()[i];
                RteParaFormatParameters paraParameters = new RteParaFormatParameters();
                paraParameters.setFieldName(formatFieldName);
                paraParameters.setTag(curFormat.tag());
                paraParameters.setDescription(curFormat.description());
                formatList.add(new RteParaFormat(paraParameters));
            }

            WidgetCollectionParameters wcp = new WidgetCollectionParameters();
            wcp.setContainedElements(formatList);
            wcp.setFieldName("formats");

            List<DialogElement> formats = Arrays.asList(new DialogElement[]{ new WidgetCollection(wcp) });
            RtePluginParameters widgetParameters = new RtePluginParameters();
            widgetParameters.setFieldName("paraformat");
            widgetParameters.setFeatures(ALL_FEATURES);
            widgetParameters.setContainedElements(formats);
            return new RtePlugin(widgetParameters);
        }

        return null;
    }

    private RtePlugin buildKeysPlugin() {
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

    private RtePlugin buildTablePlugin() {
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

            if (tableAnnotation.tableStyles().length > 0) {
                List<DialogElement> tableStyles = new ArrayList<DialogElement>();

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
                List<DialogElement> cellStyles = new ArrayList<DialogElement>();

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

    private RtePlugin buildLinksPlugin() {
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
    private RtePlugin buildMiscToolsPlugin() {
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

    private RtePlugin buildFullScreenPlugin() {
        if (rteAnnotation.fullScreen().length > 0) {
            RtePluginParameters widgetParameters = new RtePluginParameters();

            widgetParameters.setFieldName("fullscreen");
            widgetParameters.setFeatures(convertFeatures(Collections.singletonList(ALL_FEATURES)));

            return new RtePlugin(widgetParameters);
        }

        return null;
    }
}
