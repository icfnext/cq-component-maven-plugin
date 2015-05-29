package com.citytechinc.cq.component.dialog.statik;

import com.citytechinc.cq.component.annotations.config.Widget;
import com.citytechinc.cq.component.annotations.widgets.Static;
import com.citytechinc.cq.component.dialog.AbstractWidget;

@Widget(annotationClass = Static.class, makerClass = StaticWidgetMaker.class, xtype = StaticWidget.XTYPE)
public class StaticWidget extends AbstractWidget {
	public static final String XTYPE = "static";

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

	public StaticWidget(final StaticWidgetParameters parameters) {
		super(parameters);
		bold = parameters.isBold();
		bottommargin = parameters.isBottommargin();
		href = parameters.getHref();
		html = parameters.getHtml();
		italic = parameters.isItalic();
		noWrap = parameters.isNoWrap();
		small = parameters.isSmall();
		tag = parameters.getTag();
		target = parameters.getTarget();
		text = parameters.getText();
		topmargin = parameters.isTopmargin();
	}

	public final boolean isBold() {
		return bold;
	}

	public final boolean isBottommargin() {
		return bottommargin;
	}

	public final String getHref() {
		return href;
	}

	public final String getHtml() {
		return html;
	}

	public final boolean isItalic() {
		return italic;
	}

	public final boolean isNoWrap() {
		return noWrap;
	}

	public final boolean isSmall() {
		return small;
	}

	public final String getTag() {
		return tag;
	}

	public final String getTarget() {
		return target;
	}

	public final String getText() {
		return text;
	}

	public final boolean isTopmargin() {
		return topmargin;
	}
}
