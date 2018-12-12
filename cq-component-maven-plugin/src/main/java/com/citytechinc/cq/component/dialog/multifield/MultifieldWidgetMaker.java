package com.citytechinc.cq.component.dialog.multifield;

import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.List;

import javassist.CannotCompileException;
import javassist.NotFoundException;

import com.citytechinc.cq.component.annotations.widgets.MultiField;
import com.citytechinc.cq.component.dialog.DialogElement;
import com.citytechinc.cq.component.dialog.exception.InvalidComponentFieldException;
import com.citytechinc.cq.component.dialog.factory.WidgetFactory;
import com.citytechinc.cq.component.dialog.maker.AbstractWidgetMaker;
import com.citytechinc.cq.component.dialog.maker.WidgetMakerParameters;

public class MultifieldWidgetMaker extends AbstractWidgetMaker<MultiFieldWidgetParameters> {

	public MultifieldWidgetMaker(WidgetMakerParameters parameters) {
		super(parameters);
	}

	@Override
	public DialogElement make(MultiFieldWidgetParameters widgetParameters) throws ClassNotFoundException,
		SecurityException, InvalidComponentFieldException, CannotCompileException, NotFoundException,
		NoSuchFieldException, InstantiationException, IllegalAccessException, IllegalArgumentException,
		InvocationTargetException, NoSuchMethodException {

		MultiField multiFieldAnnotation = getAnnotation(MultiField.class);

		widgetParameters.setOrderable(getOrderableForField(multiFieldAnnotation));
		widgetParameters.setAddItemLabel(getAddItemLabelForField(multiFieldAnnotation));
		parameters.setUseDotSlashInName(false);
		DialogElement element = WidgetFactory.make(parameters, MultiFieldWidget.RANKING);

		element.setFieldName("fieldConfig");

		List<DialogElement> elements = new ArrayList<DialogElement>();
		elements.add(element);

		widgetParameters.setContainedElements(elements);

		return new MultiFieldWidget(widgetParameters);

	}

	protected boolean getOrderableForField(MultiField annotation) {
		if (annotation != null) {
			return annotation.orderable();
		}

		return MultiField.ORDERABLE_DEFAULT;
	}

	protected String getAddItemLabelForField(MultiField annotation) {
		if (annotation != null) {
			return annotation.addItemLabel();
		}

		return MultiField.ADD_ITEM_LABEL_DEFAULT;
	}

}
