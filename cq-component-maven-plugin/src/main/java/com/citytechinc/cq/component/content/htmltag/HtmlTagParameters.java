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
package com.citytechinc.cq.component.content.htmltag;

import com.citytechinc.cq.component.xml.DefaultXmlElementParameters;

public class HtmlTagParameters extends DefaultXmlElementParameters {

	private static final String CONTENT_PRIMARY_TYPE = "nt:unstructured";

	private String tagName;
	private String cssClass;
	private String id;

	@Override
	public String getPrimaryType() {
		return CONTENT_PRIMARY_TYPE;
	}

	@Override
	public String getFieldName() {
		return "cq:htmlTag";
	}

	@Override
	public void setFieldName(String fieldName) {
		throw new UnsupportedOperationException("fieldName is Static for Html Tag");
	}

	public String getTagName() {
		return tagName;
	}

	public void setTagName(String tagName) {
		this.tagName = tagName;
	}

	public String getCssClass() {
		return cssClass;
	}

	public void setCssClass(String cssClass) {
		this.cssClass = cssClass;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}
}
