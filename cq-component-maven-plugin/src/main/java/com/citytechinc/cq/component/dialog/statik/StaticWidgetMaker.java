package com.citytechinc.cq.component.dialog.statik;

import com.citytechinc.cq.component.annotations.widgets.Static;
import com.citytechinc.cq.component.dialog.DialogElement;
import com.citytechinc.cq.component.dialog.maker.AbstractWidgetMaker;
import com.citytechinc.cq.component.dialog.maker.WidgetMakerParameters;
import com.google.common.base.Strings;

public class StaticWidgetMaker extends AbstractWidgetMaker {
	public StaticWidgetMaker(final WidgetMakerParameters parameters) {
		super(parameters);
	}

	@Override
	public final DialogElement make() throws ClassNotFoundException {
		final Static staticAnnotation = getAnnotation(Static.class);
		final StaticWidgetParameters staticWidgetParameters = buildStaticWidgetParameters(staticAnnotation);
		return new StaticWidget(staticWidgetParameters);
	}

	private StaticWidgetParameters buildStaticWidgetParameters(final Static staticAnnotation) {
		final StaticWidgetParameters staticWidgetParameters = new StaticWidgetParameters();

		//Set custom widget parameters
		staticWidgetParameters.setBold(staticAnnotation.bold());
		staticWidgetParameters.setBottommargin(staticAnnotation.bottommargin());
		staticWidgetParameters.setHref(Strings.emptyToNull(staticAnnotation.href()));
		staticWidgetParameters.setHtml(Strings.emptyToNull(staticAnnotation.html()));
		staticWidgetParameters.setItalic(staticAnnotation.italic());
		staticWidgetParameters.setNoWrap(staticAnnotation.noWrap());
		staticWidgetParameters.setSmall(staticAnnotation.small());
		staticWidgetParameters.setTag(Strings.emptyToNull(staticAnnotation.tag()));
		staticWidgetParameters.setTarget(Strings.emptyToNull(staticAnnotation.target()));
		staticWidgetParameters.setText(Strings.emptyToNull(staticAnnotation.text()));
		staticWidgetParameters.setTopmargin(staticAnnotation.topmargin());

		//Set parameters from DialogField
		staticWidgetParameters.setFieldLabel(getFieldLabelForField());
		staticWidgetParameters.setFieldName(getFieldNameForField());
		staticWidgetParameters.setFieldDescription(getFieldDescriptionForField());
		staticWidgetParameters.setHideLabel(getHideLabelForField());
		staticWidgetParameters.setAdditionalProperties(getAdditionalPropertiesForField());
		staticWidgetParameters.setListeners(getListeners());

		return staticWidgetParameters;
	}
}
