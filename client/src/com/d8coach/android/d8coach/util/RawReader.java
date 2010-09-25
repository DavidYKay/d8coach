package com.d8coach.android.d8coach.util;

import java.io.IOException;
import java.io.InputStream;

import android.content.res.Resources;

public class RawReader {

	public static String readString(Resources resourceManager, int resourceId) {
		//InputStream in = resourceManager.openRawResource(R.raw.story_template);
		InputStream in = resourceManager.openRawResource(resourceId);

		int length = 0;
		byte[] bytes = null;
		int bytesRead = 0;
		try {
			length = in.available();
			bytes = new byte[length];
			bytesRead = in.read(bytes);
			in.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 
		assert (bytesRead == length);
		return new String(bytes);
	}
}
