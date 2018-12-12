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

		this.tagName =
			new NameSpacedAttribute<String>(Constants.CQ_NS_URI, Constants.CQ_NS_PREFIX, parameters.getTagName());
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
