package com.citytechinc.cq.component.annotations.widgets;

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

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * Represents a Widget of type CQ.form.RichText in Classic UI
 * <p>
 * This widget is not supported in Touch UI and will render as a textarea
 */
@Retention(RetentionPolicy.CLASS)
@Target({ ElementType.FIELD, ElementType.METHOD })
public @interface RichTextEditor {

    /**
     * Edit RTE Plugin configuration. For more information on configuring this
     * plugin see <a href=
     * "http://dev.day.com/docs/en/cq/current/widgets-api/output/CQ.form.rte.plugins.EditToolsPlugin.html"
     * >the RTE configuration documentation</a>.
     *
     * @return Edit[]
     */
    Edit[] edit() default {};

    /**
     * FindReplace RTE Plugin configuration. For more information on configuring
     * this plugin see <a href=
     * "http://dev.day.com/docs/en/cq/current/widgets-api/index.html?class=CQ.form.rte.plugins.FindReplacePlugin"
     * >the RTE configuration documentation</a>.
     *
     * @return FindReplace[]
     */
    FindReplace[] findreplace() default {};

    /**
     * Format RTE Plugin configuration. For more information on configuring this
     * plugin see <a href=
     * "http://dev.day.com/docs/en/cq/current/widgets-api/index.html?class=CQ.form.rte.plugins.FormatPlugin"
     * >the RTE configuration documentation</a>.
     *
     * @return Format[]
     */
    Format[] format() default {};

    /**
     * Image RTE Plugin configuration. For more information on configuring this
     * plugin see <a href=
     * "http://dev.day.com/docs/en/cq/current/widgets-api/index.html?class=CQ.form.rte.plugins.ImagePlugin"
     * >the RTE configuration documentation</a>.
     *
     * @return Image[]
     */
    Image[] image() default {};

    /**
     * Keys RTE Plugin configuration. For more information on configuring this
     * plugin see <a href=
     * "http://dev.day.com/docs/en/cq/current/widgets-api/index.html?class=CQ.form.rte.plugins.KeyPlugin"
     * >the RTE configuration documentation</a>.
     *
     * @return Keys[]
     */
    Keys[] keys() default {};

    /**
     * Justify RTE Plugin configuration. For more information on configuring
     * this plugin see <a href=
     * "http://dev.day.com/docs/en/cq/current/widgets-api/index.html?class=CQ.form.rte.plugins.JustifyPlugin"
     * >the RTE configuration documentation</a>.
     *
     * @return Justify[]
     */
    Justify[] justify() default {};

    /**
     * Links RTE Plugin configuration. For more information on configuring this
     * plugin see <a href=
     * "http://dev.day.com/docs/en/cq/current/widgets-api/index.html?class=CQ.form.rte.plugins.LinkPlugin"
     * >the RTE configuration documentation</a>.
     *
     * @return Links[]
     */
    Links[] links() default {};

    /**
     * Lists RTE Plugin configuration. For more information on configuring this
     * plugin see <a href=
     * "http://dev.day.com/docs/en/cq/current/widgets-api/index.html?class=CQ.form.rte.plugins.ListPlugin"
     * >the RTE configuration documentation</a>.
     *
     * @return Lists
     */
    Lists[] lists() default {};

    /**
     * MiscTools RTE Plugin configuration. For more information on configuring
     * this plugin see <a href=
     * "http://dev.day.com/docs/en/cq/current/widgets-api/index.html?class=CQ.form.rte.plugins.MiscToolsPlugin"
     * >the RTE configuration documentation</a>.
     *
     * @return MiscTools[]
     */
    MiscTools[] misctools() default {};

    /**
     * ParaFormat RTE Plugin configuration. For more information on configuring
     * this plugin see <a href=
     * "http://dev.day.com/docs/en/cq/current/widgets-api/output/CQ.form.rte.plugins.ParagraphFormatPlugin.html"
     * >the RTE configuration documentation</a>.
     *
     * @return ParaFormat[]
     */
    ParaFormat[] paraformat() default {};

    /**
     * SpellCheck RTE Plugin configuration. For more information on configuring
     * this plugin see <a href=
     * "http://dev.day.com/docs/en/cq/current/widgets-api/output/CQ.form.rte.plugins.SpellCheckerPlugin.html"
     * >the RTE configuration documentation</a>.
     *
     * @return SpellCheck[]
     */
    SpellCheck[] spellcheck() default {};

    /**
     * Styles RTE Plugin configuration. For more information on configuring this
     * plugin see <a href=
     * "http://dev.day.com/docs/en/cq/current/widgets-api/output/CQ.form.rte.plugins.StylesPlugin.html"
     * >the RTE configuration documentation</a>.
     *
     * @return Styles[]
     */
    Style[] styles() default {};

    /**
     * SubSuperscript RTE Plugin configuration. For more information on
     * configuring this plugin see <a href=
     * "http://dev.day.com/docs/en/cq/current/widgets-api/output/CQ.form.rte.plugins.SubSuperScriptPlugin.html"
     * >the RTE configuration documentation</a>.
     *
     * @return SubSuperscript[]
     */
    SubSuperscript[] subsuperscript() default {};

    /**
     * Table RTE Plugin configuration. For more information on configuring this
     * plugin see <a href=
     * "http://dev.day.com/docs/en/cq/current/widgets-api/output/CQ.form.rte.plugins.TablePlugin.html"
     * >the RTE configuration documentation</a>.
     *
     * @return Table[]
     */
    Table[] table() default {};

    /**
     * Undo RTE Plugin configuration. For more information on configuring this
     * plugin see <a href=
     * "http://dev.day.com/docs/en/cq/current/widgets-api/output/CQ.form.rte.plugins.UndoRedoPlugin.html"
     * >the RTE configuration documentation</a>.
     *
     * @return Undo[]
     */
    Undo[] undo() default {};

    /**
     * For Touch-UI Only
     * <p>
     * UI Settings Node configuration plugin see <a href=
     * "https://docs.adobe.com/docs/en/aem/6-2/administer/operations/page-authoring/rich-text-editor.html?#Configuri
     * n g RichText Editor dialog settings for toolbar (Touch UI)>Configuring
     * RichText Editor dialog settings for toolbar (Touch UI)</a>.
     *
     * @return Undo[]
     */
    UISettings[] uiSettings() default {};

}
