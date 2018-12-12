package com.citytechinc.cq.component.touchuidialog.widget.multifield;

import com.citytechinc.cq.component.annotations.DialogField;
import com.citytechinc.cq.component.annotations.IgnoreDialogField;
import com.citytechinc.cq.component.annotations.MultiFieldChildResource;
import com.citytechinc.cq.component.annotations.widgets.MultiField;
import com.citytechinc.cq.component.dialog.DialogFieldConfig;
import com.citytechinc.cq.component.dialog.exception.InvalidComponentClassException;
import com.citytechinc.cq.component.dialog.exception.InvalidComponentFieldException;
import com.citytechinc.cq.component.dialog.util.DialogUtil;
import com.citytechinc.cq.component.maven.util.ComponentMojoUtil;
import com.citytechinc.cq.component.touchuidialog.TouchUIDialogElement;
import com.citytechinc.cq.component.touchuidialog.TouchUIDialogElementComparator;
import com.citytechinc.cq.component.touchuidialog.TouchUIDialogType;
import com.citytechinc.cq.component.touchuidialog.container.Container;
import com.citytechinc.cq.component.touchuidialog.container.ContainerParameters;
import com.citytechinc.cq.component.touchuidialog.container.items.Items;
import com.citytechinc.cq.component.touchuidialog.container.items.ItemsParameters;
import com.citytechinc.cq.component.touchuidialog.exceptions.TouchUIDialogGenerationException;
import com.citytechinc.cq.component.touchuidialog.widget.factory.TouchUIWidgetFactory;
import com.citytechinc.cq.component.touchuidialog.widget.maker.AbstractTouchUIWidgetMaker;
import com.citytechinc.cq.component.touchuidialog.widget.maker.TouchUIWidgetMakerParameters;
import com.google.common.collect.ImmutableMap;
import javassist.CtMember;
import javassist.CtMethod;
import javassist.NotFoundException;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class MultiFieldWidgetMaker extends AbstractTouchUIWidgetMaker<MultiFieldWidgetParameters> {

    public MultiFieldWidgetMaker(TouchUIWidgetMakerParameters parameters) {
        super(parameters);
    }

    @Override
    public TouchUIDialogElement make(MultiFieldWidgetParameters widgetParameters) throws ClassNotFoundException,
            InvalidComponentFieldException, TouchUIDialogGenerationException {
        MultiField annotation = getAnnotation(MultiField.class);
        List<TouchUIDialogElement> containedElements = new ArrayList<TouchUIDialogElement>();

        if (isComposite(annotation)) {
            try {
                containedElements.add(generateFieldContainer(widgetParameters, this));
            } catch (NotFoundException e) {
                throw new TouchUIDialogGenerationException(
                        "Exception encountered while constructing contained elements for the Multifield "
                                + parameters.getDialogFieldConfig().getFieldName() + " of class "
                                + parameters.getContainingClass().getName(), e);
            }
            // For coral 3 composite multifield, the name parameter should be inside the field tag
            widgetParameters.setName("");
        } else {
            TouchUIDialogElement field = TouchUIWidgetFactory.make(parameters, MultiFieldWidget.RANKING);
            field.setFieldName("field");
            containedElements.add(field);
        }

        widgetParameters.setContainedElements(containedElements);
        widgetParameters.setComposite(isComposite(annotation));
        widgetParameters.setTouchUIDialogType(parameters.getTouchUIDialogType());

        if (TouchUIDialogType.CORAL3.isOfType(widgetParameters.getTouchUIDialogType())) {
            return new MultiFieldCoral3Widget(widgetParameters);
        }
        return new MultiFieldWidget(widgetParameters);
    }

    public TouchUIWidgetMakerParameters getParameters() {
        return parameters;
    }

    protected boolean isComposite(MultiField annotation) {
        if (annotation != null) {
            return annotation.composite();
        }
        return false;
    }

    private Container generateFieldContainer(MultiFieldWidgetParameters widgetParameters, MultiFieldWidgetMaker widgetMaker)
            throws NotFoundException, InvalidComponentFieldException, ClassNotFoundException, TouchUIDialogGenerationException {
        ContainerParameters containerParameters = new ContainerParameters();

        containerParameters.setFieldName("field");
        containerParameters.setTouchUIDialogType(TouchUIDialogType.CORAL3.getType());
        containerParameters.setContainedElements(generateItemsTag(widgetMaker));
        containerParameters.setAdditionalProperties(ImmutableMap.of("name", widgetParameters.getName()));

        return new Container(containerParameters);
    }

    private List<TouchUIDialogElement> generateItemsTag(MultiFieldWidgetMaker widgetMaker)
            throws NotFoundException, InvalidComponentFieldException, ClassNotFoundException, TouchUIDialogGenerationException {
        ItemsParameters itemsParameters = new ItemsParameters();

        itemsParameters.setFieldName("items");
        itemsParameters.setContainedElements(generateDialogElements(widgetMaker));
        List<TouchUIDialogElement> itemsElements = new ArrayList<TouchUIDialogElement>();
        itemsElements.add(new Items(itemsParameters));

        return itemsElements;
    }

    private List<TouchUIDialogElement> generateDialogElements(MultiFieldWidgetMaker widgetMaker)
            throws NotFoundException, InvalidComponentFieldException, ClassNotFoundException, TouchUIDialogGenerationException {
        List<TouchUIDialogElement> dialogElements = new ArrayList<TouchUIDialogElement>();

        buildDialogFields(dialogElements, widgetMaker);
        Collections.sort(dialogElements, new TouchUIDialogElementComparator());

        return dialogElements;
    }

    private static void buildDialogFields(List<TouchUIDialogElement> elements, MultiFieldWidgetMaker widgetMaker)
            throws NotFoundException, InvalidComponentFieldException, ClassNotFoundException,
            TouchUIDialogGenerationException {
        List<CtMember> fieldsAndMethods = new ArrayList<CtMember>();
        fieldsAndMethods.addAll(ComponentMojoUtil.collectFields(widgetMaker.getCtType()));
        fieldsAndMethods.addAll(ComponentMojoUtil.collectMethods(widgetMaker.getCtType()));

        for (CtMember member : fieldsAndMethods) {
            if (!member.hasAnnotation(IgnoreDialogField.class)) {
                DialogFieldConfig dialogFieldConfig = null;

                if (member instanceof CtMethod) {
                    try {
                        dialogFieldConfig = DialogUtil.getDialogFieldFromSuperClasses((CtMethod) member);
                    } catch (InvalidComponentClassException e) {
                        throw new InvalidComponentFieldException(e.getMessage(), e);
                    }
                } else if (member.hasAnnotation(DialogField.class)) {
                    dialogFieldConfig = new DialogFieldConfig((DialogField) member.getAnnotation(DialogField.class), member);
                }

                if (dialogFieldConfig != null && !dialogFieldConfig.isSuppressTouchUI()) {
                    TouchUIWidgetMakerParameters curFieldMember = generateWidgetMakerParameters(member, widgetMaker, dialogFieldConfig);

                    if (member.hasAnnotation(MultiFieldChildResource.class)) {
                        buildDialogFields(elements, new MultiFieldWidgetMaker(curFieldMember));
                    } else {
                        TouchUIDialogElement currentDialogElement = TouchUIWidgetFactory.make(curFieldMember, -1);

                        if (currentDialogElement != null) {
                            currentDialogElement.setRanking(dialogFieldConfig.getRanking());
                            elements.add(currentDialogElement);
                        }
                    }
                }
            }
        }
    }

    private static TouchUIWidgetMakerParameters generateWidgetMakerParameters(CtMember member, MultiFieldWidgetMaker widgetMaker, DialogFieldConfig dialogFieldConfig)
            throws ClassNotFoundException {
        TouchUIWidgetMakerParameters widgetMakerParameters = new TouchUIWidgetMakerParameters();
        Class<?> fieldClass = widgetMaker.getParameters().getClassLoader().loadClass(member.getDeclaringClass().getName());

        widgetMakerParameters.setDialogFieldConfig(dialogFieldConfig);
        widgetMakerParameters.setContainingClass(fieldClass);
        widgetMakerParameters.setClassLoader(widgetMaker.getParameters().getClassLoader());
        widgetMakerParameters.setClassPool(widgetMaker.getParameters().getClassPool());
        widgetMakerParameters.setWidgetRegistry(widgetMaker.getParameters().getWidgetRegistry());
        widgetMakerParameters.setUseDotSlashInName(widgetMaker.getParameters().isUseDotSlashInName());
        widgetMakerParameters.setTouchUIDialogType(widgetMaker.getParameters().getTouchUIDialogType());

        return widgetMakerParameters;
    }
}