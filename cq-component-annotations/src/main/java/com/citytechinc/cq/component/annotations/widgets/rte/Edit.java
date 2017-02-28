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
