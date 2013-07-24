package com.citytechinc.cq.component.dialog;

/**
 * Used to indicate that a Dialog Element may be rendered either as a Tab or inside of a Tab
 */
public interface TabbableDialogElement {

    /**
     * Indicates whether an instance of the Tabbable element is to be rendered within a Tab or
     * as a tab.
     *
     * @return True if the Dialog Element should be rendered as a Tab, false if the Dialog Element
     *         should be rendered inside an existing Tab.
     */
    public Boolean isTab();

}
