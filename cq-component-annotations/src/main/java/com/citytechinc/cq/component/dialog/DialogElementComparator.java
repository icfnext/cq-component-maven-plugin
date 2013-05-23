package com.citytechinc.cq.component.dialog;

import java.util.Comparator;

public class DialogElementComparator implements Comparator<DialogElement> {

	public int compare(DialogElement de1, DialogElement de2) {
		return Double.compare(de1.getRanking(), de2.getRanking());
	}

}
