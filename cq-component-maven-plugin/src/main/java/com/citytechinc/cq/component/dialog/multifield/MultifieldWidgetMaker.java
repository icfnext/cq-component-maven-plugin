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
