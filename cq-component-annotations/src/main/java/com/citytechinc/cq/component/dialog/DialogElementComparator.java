package com.citytechinc.cq.component.dialog;

import java.util.Comparator;

import com.citytechinc.cq.component.xml.XmlElement;

public class DialogElementComparator implements Comparator<XmlElement> {

	public int compare(XmlElement xe1, XmlElement xe2) {
		DialogElement de1 = (DialogElement) xe1;
		DialogElement de2 = (DialogElement) xe2;
		return Double.compare(de1.getRanking(), de2.getRanking());
	}

}
