package com.citytechinc.cq.component.touchuidialog;

import java.util.Comparator;

import com.citytechinc.cq.component.xml.XmlElement;

public class TouchUIDialogElementComparator implements Comparator<XmlElement> {

	public int compare(XmlElement xe1, XmlElement xe2) {
		TouchUIDialogElement dialogElement1 = (TouchUIDialogElement) xe1;
		TouchUIDialogElement dialogElement2 = (TouchUIDialogElement) xe2;

		return Double.compare(dialogElement1.getRanking(), dialogElement2.getRanking());
	}

}
