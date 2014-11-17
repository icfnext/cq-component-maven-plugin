/**
 *    Copyright 2013 CITYTECH, Inc.
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
package com.citytechinc.cq.component.touchuidialog.layout.maker;

import javassist.ClassPool;
import javassist.CtClass;

public class LayoutMakerParameters {

    private CtClass componentClass;
    private ClassLoader classLoader;
    private ClassPool classPool;

    public CtClass getComponentClass() {
        return componentClass;
    }

    public void setComponentClass(CtClass componentClass) {
        this.componentClass = componentClass;
    }

    public ClassLoader getClassLoader() {
        return classLoader;
    }

    public void setClassLoader(ClassLoader classLoader) {
        this.classLoader = classLoader;
    }

    public ClassPool getClassPool() {
        return classPool;
    }

    public void setClassPool(ClassPool classPool) {
        this.classPool = classPool;
    }

}
