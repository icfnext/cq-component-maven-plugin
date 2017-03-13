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
package com.citytechinc.cq.component.touchuidialog.factory;

import com.citytechinc.cq.component.annotations.Component;
import com.citytechinc.cq.component.touchuidialog.TouchUIDialog;
import com.citytechinc.cq.component.touchuidialog.TouchUIDialogParameters;
import com.citytechinc.cq.component.touchuidialog.exceptions.TouchUIDialogGenerationException;
import com.citytechinc.cq.component.touchuidialog.layout.Layout;
import com.citytechinc.cq.component.touchuidialog.layout.maker.LayoutMaker;
import com.citytechinc.cq.component.touchuidialog.layout.maker.LayoutMakerParameters;
import com.citytechinc.cq.component.touchuidialog.layout.maker.exceptions.LayoutMakerException;
import com.citytechinc.cq.component.touchuidialog.layout.tabs.TabsLayoutMaker;
import com.citytechinc.cq.component.touchuidialog.widget.registry.TouchUIWidgetRegistry;
import com.citytechinc.cq.component.xml.XmlElement;
import javassist.ClassPool;
import javassist.CtClass;
import org.codehaus.plexus.util.StringUtils;

import javax.annotation.Nullable;
import java.util.ArrayList;
import java.util.List;

public class TouchUIDialogFactory {

	private TouchUIDialogFactory() {
	}

	@Nullable
	public static TouchUIDialog make(CtClass componentClass, ClassLoader classLoader, ClassPool classPool,
		TouchUIWidgetRegistry widgetRegistry) throws TouchUIDialogGenerationException {
		try {

			Component componentAnnotation = (Component) componentClass.getAnnotation(Component.class);

			// If output of the Touch UI dialog is disabled, return null
			if (componentAnnotation.suppressTouchUIDialog()) {
				return null;
			}

			TouchUIDialogParameters parameters = new TouchUIDialogParameters();

			parameters.setTitle(componentAnnotation.value());
			parameters.setFileName(componentAnnotation.touchFileName());

			if (StringUtils.isNotBlank(componentAnnotation.helpPath())) {
				parameters.setHelpPath(componentAnnotation.helpPath());
			}

			// Determine the LayoutMaker to use
			// TODO: Make dynamic - currently we always use the tabs layout
			// maker
			LayoutMakerParameters layoutMakerParameters = new LayoutMakerParameters();

			layoutMakerParameters.setComponentClass(componentClass);
			layoutMakerParameters.setClassLoader(classLoader);
			layoutMakerParameters.setClassPool(classPool);
			layoutMakerParameters.setWidgetRegistry(widgetRegistry);
			LayoutMaker layoutMaker = new TabsLayoutMaker(layoutMakerParameters);

			// Delegate the rest of the production to the LayoutMaker
			Layout layout = layoutMaker.make();

			// Add the generated Layout to the Dialog's contained elements
			List<XmlElement> containedElements = new ArrayList<XmlElement>();
			containedElements.add(layout);

			parameters.setContainedElements(containedElements);

			// Add the extraClientLibs parameter
			String[] extraClientlibs = componentAnnotation.extraClientlibs();
			if(extraClientlibs.length > 0){
				parameters.setExtraClientlibs(componentAnnotation.extraClientlibs());
			}

			return new TouchUIDialog(parameters);

		} catch (ClassNotFoundException e) {
			throw new TouchUIDialogGenerationException(
				"ClassNotFound exception encountered generating Touch UI Dialog", e);
		} catch (LayoutMakerException e) {
			throw new TouchUIDialogGenerationException("Layout Maker Exception encountered producing Dialog Layout", e);
		}
	}

}
