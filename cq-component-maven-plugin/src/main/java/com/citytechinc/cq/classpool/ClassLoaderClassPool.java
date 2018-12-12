package com.citytechinc.cq.classpool;

import javassist.ClassPool;
import javassist.LoaderClassPath;

public class ClassLoaderClassPool extends ClassPool {

    private ClassLoader classLoader;

    public ClassLoaderClassPool(ClassLoader classLoader) {
        super();
        this.classLoader = classLoader;
        this.appendClassPath(new LoaderClassPath(classLoader));
    }

    @Override
    public ClassLoader getClassLoader() {
        return classLoader;
    }
}
