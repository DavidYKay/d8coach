package com.d8coach.android.d8coach.model;

import java.util.ArrayList;

public enum SortOrder {
    LATEST  (0),
    CLOSEST (1),
    EVENTS  (2),
    TEMP_ID (3);

    private final int index;

    SortOrder(int index) {
        this.index = index;
    }

    int getIndex() {
        return index;
    }
    
    private static ArrayList<SortOrder> array = new ArrayList<SortOrder>();
    static {
    	//int i = 0;
    	for (SortOrder order : SortOrder.values()) {
    		//array.set(i, order);
    		//i++;
    		array.add(order);
    	}
    }

    public static SortOrder valueOf(Integer index) {
        return array.get(index);
        //return SortOrder.values.get(index);
    }
}
