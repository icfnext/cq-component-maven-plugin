/**
 *    Copyright 2017 ICF Olson
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
 * @see com.citytechinc.cq.component.annotations.widgets.RichTextEditor#table()
 */
public @interface Table {

	public boolean table() default true;

	public boolean removetable() default true;

	public boolean insertrow() default true;

	public boolean removerow() default true;

	public boolean insertcolumn() default true;

	public boolean removecolumn() default true;

	public boolean cellprops() default true;

	public boolean mergecells() default true;

	public boolean splitcell() default true;

	public boolean selectrow() default true;

	public boolean selectcolumns() default true;

	public Style[] tableStyles() default {};

	public Style[] cellStyles() default {};

}
