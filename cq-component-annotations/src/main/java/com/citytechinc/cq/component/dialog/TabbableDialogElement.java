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
package com.citytechinc.cq.component.dialog;

/**
 * Used to indicate that a Dialog Element may be rendered either as a Tab or
 * inside of a Tab
 */
public interface TabbableDialogElement {

	/**
	 * Indicates whether an instance of the Tabbable element is to be rendered
	 * within a Tab or as a tab.
	 *
	 * @return True if the Dialog Element should be rendered as a Tab, false if
	 *         the Dialog Element should be rendered inside an existing Tab.
	 */
	public Boolean isTab();

	/**
	 *
	 * @return The title of the tab to be rendered to an author dialog
	 */
	public String getTitle();

	/**
	 *
	 * @param title The title of the tab to be rendered to an author dialog
	 */
	public void setTitle(String title);

}
