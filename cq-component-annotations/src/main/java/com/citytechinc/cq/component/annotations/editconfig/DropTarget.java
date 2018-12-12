package com.citytechinc.cq.component.annotations.editconfig;

public @interface DropTarget {

    String nodeName();

    String[] accept();

    String[] groups();

    String propertyName();
}
