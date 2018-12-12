package com.citytechinc.cq.component.annotations.editconfig;

import javassist.CtMember;

public class InPlaceEditorConfig {

    private Object inPlaceEditorAnnotation;

    private CtMember member;

    private Class<?> annotationClass;

    public InPlaceEditorConfig(Object inPlaceEditorAnnotation, CtMember member, Class<?> annotationClass) {
        this.inPlaceEditorAnnotation = inPlaceEditorAnnotation;
        this.member = member;
        this.annotationClass = annotationClass;
    }

    public Object getInPlaceEditorAnnotation() {
        return inPlaceEditorAnnotation;
    }

    public void setInPlaceEditorAnnotation(Object inPlaceEditorAnnotation) {
        this.inPlaceEditorAnnotation = inPlaceEditorAnnotation;
    }

    public CtMember getMember() {
        return member;
    }

    public void setMember(CtMember member) {
        this.member = member;
    }

    public Class<?> getAnnotationClass() {
        return annotationClass;
    }

    public void setAnnotationClass(Class<?> annotationClass) {
        this.annotationClass = annotationClass;
    }
}
