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
package com.citytechinc.cq.component.content.htmltag;

import java.util.Map;

import org.codehaus.plexus.util.StringUtils;

import com.citytechinc.cq.component.util.Constants;
import com.citytechinc.cq.component.xml.AbstractXmlElement;
import com.citytechinc.cq.component.xml.NameSpacedAttribute;
import com.google.common.collect.Maps;

public class HtmlTag extends AbstractXmlElement {

	private final NameSpacedAttribute<String> tagName;
	private final String cssClass;
	private final String id;

	public HtmlTag(HtmlTagParameters parameters) {

		super(parameters);

		this.tagName = new NameSpacedAttribute<String>(Constants.CQ_NS_URI, Constants.CQ_NS_PREFIX,
			parameters.getTagName());
		this.cssClass = parameters.getCssClass();
		this.id = parameters.getId();

	}

	public NameSpacedAttribute<String> getTagName() {
		return tagName;
	}

	/**
	 * Returns a map containing the single cssClass property keyed by the term
	 * 'class'. This is necessary as class is reserved and getClass has special
	 * meaning.
	 *
	 * @return A map of the string 'class' to the proposed cssClass if one is
	 *         provided or an empty map if not
	 */
	public Map<String, String> getCssClass() {

		Map<String, String> retMap = Maps.newHashMap();

		if (StringUtils.isNotBlank(cssClass)) {
			retMap.put("class", cssClass);
		}

		return retMap;

	}

	public String getId() {
		return id;
	}
}
