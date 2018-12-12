package com.citytechinc.cq.component.dialog;

import com.citytechinc.cq.component.annotations.Listener;

import java.util.HashMap;
import java.util.Map;

public class Listeners extends AbstractDialogElement {

    private final Map<String, String> properties = new HashMap<String, String>();

    public Listeners(ListenersParameters parameters) {
        super(parameters);
        for (Listener listener : parameters.getListenerAnnotations()) {
            properties.put(listener.name(), listener.value());
        }
    }

    public Map<String, String> getProperties() {
        return properties;
    }
}
