package com.d8coach.android.d8coach.util;

import org.json.JSONArray;

public interface JSONResponseProcessor {
	public void processResults(JSONArray results);
    public int getLoadingTitle();
    public int getLoadingSubtitle();
}
