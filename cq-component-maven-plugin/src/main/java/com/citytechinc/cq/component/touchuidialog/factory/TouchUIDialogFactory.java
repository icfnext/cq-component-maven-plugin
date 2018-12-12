package com.citytechinc.cq.component.touchuidialog.factory;

import com.citytechinc.cq.component.annotations.Component;
import com.citytechinc.cq.component.touchuidialog.TouchUIDialog;
import com.citytechinc.cq.component.touchuidialog.TouchUIDialogParameters;
import com.citytechinc.cq.component.touchuidialog.TouchUIDialogType;
import com.citytechinc.cq.component.touchuidialog.exceptions.TouchUIDialogGenerationException;
import com.citytechinc.cq.component.touchuidialog.layout.Layout;
import com.citytechinc.cq.component.touchuidialog.layout.columns.fixedcolumns.FixedColumnsLayoutCoral3Maker;
import com.citytechinc.cq.component.touchuidialog.layout.columns.fixedcolumns.FixedColumnsLayoutMaker;
import com.citytechinc.cq.component.touchuidialog.layout.maker.LayoutMaker;
import com.citytechinc.cq.component.touchuidialog.layout.maker.LayoutMakerParameters;
import com.citytechinc.cq.component.touchuidialog.layout.maker.exceptions.LayoutMakerException;
import com.citytechinc.cq.component.touchuidialog.layout.tabs.TabsLayoutCoral3Maker;
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
        TouchUIWidgetRegistry widgetRegistry, String touchUIDialogType) throws TouchUIDialogGenerationException {
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
            layoutMakerParameters.setTouchUIDialogType(touchUIDialogType);

            LayoutMaker layoutMaker;

            if (TouchUIDialogType.CORAL3.isOfType(touchUIDialogType)) {
                if (componentAnnotation.tabs().length > 0) {
                    layoutMaker = new TabsLayoutCoral3Maker(layoutMakerParameters);
                } else {
                    layoutMaker = new FixedColumnsLayoutCoral3Maker(layoutMakerParameters);
                }
            } else {
                if (componentAnnotation.tabs().length > 0) {
                    layoutMaker = new TabsLayoutMaker(layoutMakerParameters);
                } else {
                    layoutMaker = new FixedColumnsLayoutMaker(layoutMakerParameters);
                }
            }

            // Delegate the rest of the production to the LayoutMaker
            Layout layout = layoutMaker.make();

            // Add the generated Layout to the Dialog's contained elements
            List<XmlElement> containedElements = new ArrayList<XmlElement>();
            containedElements.add(layout);

            parameters.setContainedElements(containedElements);

            // Add the extraClientLibs parameter
            String[] extraClientlibs = componentAnnotation.extraClientlibs();
            if (extraClientlibs.length > 0) {
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
