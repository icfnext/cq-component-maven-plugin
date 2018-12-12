package com.citytechinc.cq.component.editconfig.maker;

import com.citytechinc.cq.component.editconfig.InPlaceEditorElement;
import com.citytechinc.cq.component.editconfig.InPlaceEditorParameters;
import com.citytechinc.cq.component.util.Constants;
import javassist.CtField;
import org.codehaus.plexus.util.StringUtils;

import java.lang.reflect.ParameterizedType;

public abstract class AbstractInPlaceEditorMaker<T extends InPlaceEditorParameters> implements InPlaceEditorMaker {

    protected final InPlaceEditorMakerParameters parameters;

    public AbstractInPlaceEditorMaker(InPlaceEditorMakerParameters parameters) {
        this.parameters = parameters;
    }

    @SuppressWarnings("unchecked")
    public final InPlaceEditorElement make() throws ClassNotFoundException, IllegalAccessException,
        InstantiationException {
        Class<?> clazz = getClass();
        while (clazz != null && !AbstractInPlaceEditorMaker.class.equals(clazz.getSuperclass())) {
            clazz = clazz.getSuperclass();
        }
        Class<T> parameterClass =
            (Class<T>) ((ParameterizedType) clazz.getGenericSuperclass()).getActualTypeArguments()[0];
        T parameters = parameterClass.newInstance();

        if (this.parameters.isSetActive()) {
            parameters.setActive(true);
            parameters.setFieldName("cq:inplaceEditing");
            parameters.setNameSpace(Constants.CQ_NS_URI);
            parameters.setPrimaryType("cq:InplaceEditingConfig");
        } else {
            parameters.setFieldName(getName());
            parameters.setPrimaryType("cq:ChildEditorConfig");
        }
        return make(parameters);
    }

    protected abstract InPlaceEditorElement make(T parameters) throws ClassNotFoundException, IllegalAccessException,
        InstantiationException;

    protected String getName() {
        if (isField()) {
            return parameters.getCtMember().getName();
        } else {
            String tempName = parameters.getCtMember().getName();
            if (tempName.startsWith("is")) {
                return StringUtils.uncapitalise(tempName.substring(2));
            } else if (tempName.startsWith("get")) {
                return StringUtils.uncapitalise(tempName.substring(3));
            } else {
                return StringUtils.uncapitalise(tempName);
            }
        }
    }

    /**
     * @return True if the Widget is represented by a field in the Component
     * class, false otherwise.
     */
    protected boolean isField() {
        return parameters.getCtMember() instanceof CtField;
    }
}
