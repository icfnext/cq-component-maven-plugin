package com.citytechinc.cq.component.dialog.statik;

import com.citytechinc.cq.component.dialog.widget.WidgetParameters;
import com.citytechinc.cq.component.util.Constants;

public class StaticWidgetParameters extends WidgetParameters {
	private boolean bold;
	private boolean bottommargin;
	private String href;
	private String html;
	private boolean italic;
	private boolean noWrap;
	private boolean small;
	private String tag;
	private String target;
	private String text;
	private boolean topmargin;

	public final boolean isBold() {
		return bold;
	}

	public final void setBold(final boolean bold) {
		this.bold = bold;
	}

	public final boolean isBottommargin() {
		return bottommargin;
	}

	public final void setBottommargin(final boolean bottommargin) {
		this.bottommargin = bottommargin;
	}

	public final String getHref() {
		return href;
	}

	public final void setHref(final String href) {
		this.href = href;
	}

	public final String getHtml() {
		return html;
	}

	public final void setHtml(final String html) {
		this.html = html;
	}

	public final boolean isItalic() {
		return italic;
	}

	public final void setItalic(final boolean italic) {
		this.italic = italic;
	}

	public final boolean isNoWrap() {
		return noWrap;
	}

	public final void setNoWrap(final boolean noWrap) {
		this.noWrap = noWrap;
	}

	public final boolean isSmall() {
		return small;
	}

	public final void setSmall(final boolean small) {
		this.small = small;
	}

	public final String getTag() {
		return tag;
	}

	public final void setTag(final String tag) {
		this.tag = tag;
	}

	public final String getTarget() {
		return target;
	}

	public final void setTarget(final String target) {
		this.target = target;
	}

	public final String getText() {
		return text;
	}

	public final void setText(final String text) {
		this.text = text;
	}

	public final boolean isTopmargin() {
		return topmargin;
	}

	public final void setTopmargin(final boolean topmargin) {
		this.topmargin = topmargin;
	}

	@Override
	public final String getXtype() {
		return StaticWidget.XTYPE;
	}

	@Override
	public final void setXtype(final String xtype) {
		throw new UnsupportedOperationException("xtype is static for Static widget.");
	}

	@Override
	public final boolean isAllowBlank() {
		return true;
	}

	@Override
	public final void setAllowBlank(final boolean allowBlank) {
		throw new UnsupportedOperationException("allowBlank doesn't make sense for Static widget because"
			+ " it doesn't store data.");
	}

	@Override
	public final String getDefaultValue() {
		return "";
	}

	@Override
	public final void setDefaultValue(final String defaultValue) {
		throw new UnsupportedOperationException("defaultValue doesn't make sense for Static widget because it"
			+ " doesn't store data.");
	}

	@Override
	public final String getName() {
		return "";
	}

	@Override
	public final void setName(final String name) {
		throw new UnsupportedOperationException("name doesn't make sense for Static widget because it doesn't"
			+ " store data.");
	}

	@Override
	public final String getPrimaryType() {
		return Constants.CQ_WIDGET;
	}

	@Override
	public final void setPrimaryType(final String primaryType) {
		throw new UnsupportedOperationException("jcr:primaryType is static for Static widget.");
	}
}
