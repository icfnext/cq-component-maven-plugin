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
package com.citytechinc.cq.component.annotations.widgets;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

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
import com.citytechinc.cq.component.annotations.widgets.rte.Undo;

/**
 * Represents a Widget of type CQ.form.RichText in Classic UI
 *
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
	public Edit[] edit() default {};

	/**
	 * FindReplace RTE Plugin configuration. For more information on configuring
	 * this plugin see <a href=
	 * "http://dev.day.com/docs/en/cq/current/widgets-api/index.html?class=CQ.form.rte.plugins.FindReplacePlugin"
	 * >the RTE configuration documentation</a>.
	 *
	 * @return FindReplace[]
	 */
	public FindReplace[] findreplace() default {};

	/**
	 * Format RTE Plugin configuration. For more information on configuring this
	 * plugin see <a href=
	 * "http://dev.day.com/docs/en/cq/current/widgets-api/index.html?class=CQ.form.rte.plugins.FormatPlugin"
	 * >the RTE configuration documentation</a>.
	 *
	 * @return Format[]
	 */
	public Format[] format() default {};

	/**
	 * Image RTE Plugin configuration. For more information on configuring this
	 * plugin see <a href=
	 * "http://dev.day.com/docs/en/cq/current/widgets-api/index.html?class=CQ.form.rte.plugins.ImagePlugin"
	 * >the RTE configuration documentation</a>.
	 *
	 * @return Image[]
	 */
	public Image[] image() default {};

	/**
	 * Keys RTE Plugin configuration. For more information on configuring this
	 * plugin see <a href=
	 * "http://dev.day.com/docs/en/cq/current/widgets-api/index.html?class=CQ.form.rte.plugins.KeyPlugin"
	 * >the RTE configuration documentation</a>.
	 *
	 * @return Keys[]
	 */
	public Keys[] keys() default {};

	/**
	 * Justify RTE Plugin configuration. For more information on configuring
	 * this plugin see <a href=
	 * "http://dev.day.com/docs/en/cq/current/widgets-api/index.html?class=CQ.form.rte.plugins.JustifyPlugin"
	 * >the RTE configuration documentation</a>.
	 *
	 * @return Justify[]
	 */
	public Justify[] justify() default {};

	/**
	 * Links RTE Plugin configuration. For more information on configuring this
	 * plugin see <a href=
	 * "http://dev.day.com/docs/en/cq/current/widgets-api/index.html?class=CQ.form.rte.plugins.LinkPlugin"
	 * >the RTE configuration documentation</a>.
	 *
	 * @return Links[]
	 */
	public Links[] links() default {};

	/**
	 * Lists RTE Plugin configuration. For more information on configuring this
	 * plugin see <a href=
	 * "http://dev.day.com/docs/en/cq/current/widgets-api/index.html?class=CQ.form.rte.plugins.ListPlugin"
	 * >the RTE configuration documentation</a>.
	 *
	 * @return Lists
	 */
	public Lists[] lists() default {};

	/**
	 * MiscTools RTE Plugin configuration. For more information on configuring
	 * this plugin see <a href=
	 * "http://dev.day.com/docs/en/cq/current/widgets-api/index.html?class=CQ.form.rte.plugins.MiscToolsPlugin"
	 * >the RTE configuration documentation</a>.
	 *
	 * @return MiscTools[]
	 */
	public MiscTools[] misctools() default {};

	/**
	 * ParaFormat RTE Plugin configuration. For more information on configuring
	 * this plugin see <a href=
	 * "http://dev.day.com/docs/en/cq/current/widgets-api/output/CQ.form.rte.plugins.ParagraphFormatPlugin.html"
	 * >the RTE configuration documentation</a>.
	 *
	 * @return ParaFormat[]
	 */
	public ParaFormat[] paraformat() default {};

	/**
	 * SpellCheck RTE Plugin configuration. For more information on configuring
	 * this plugin see <a href=
	 * "http://dev.day.com/docs/en/cq/current/widgets-api/output/CQ.form.rte.plugins.SpellCheckerPlugin.html"
	 * >the RTE configuration documentation</a>.
	 *
	 * @return SpellCheck[]
	 */
	public SpellCheck[] spellcheck() default {};

	/**
	 * Styles RTE Plugin configuration. For more information on configuring this
	 * plugin see <a href=
	 * "http://dev.day.com/docs/en/cq/current/widgets-api/output/CQ.form.rte.plugins.StylesPlugin.html"
	 * >the RTE configuration documentation</a>.
	 *
	 * @return Styles[]
	 */
	public Style[] styles() default {};

	/**
	 * SubSuperscript RTE Plugin configuration. For more information on
	 * configuring this plugin see <a href=
	 * "http://dev.day.com/docs/en/cq/current/widgets-api/output/CQ.form.rte.plugins.SubSuperScriptPlugin.html"
	 * >the RTE configuration documentation</a>.
	 *
	 * @return SubSuperscript[]
	 */
	public SubSuperscript[] subsuperscript() default {};

	/**
	 * Table RTE Plugin configuration. For more information on configuring this
	 * plugin see <a href=
	 * "http://dev.day.com/docs/en/cq/current/widgets-api/output/CQ.form.rte.plugins.TablePlugin.html"
	 * >the RTE configuration documentation</a>.
	 *
	 * @return Table[]
	 */
	public Table[] table() default {};

	/**
	 * Undo RTE Plugin configuration. For more information on configuring this
	 * plugin see <a href=
	 * "http://dev.day.com/docs/en/cq/current/widgets-api/output/CQ.form.rte.plugins.UndoRedoPlugin.html"
	 * >the RTE configuration documentation</a>.
	 *
	 * @return Undo[]
	 */
	public Undo[] undo() default {};

}
