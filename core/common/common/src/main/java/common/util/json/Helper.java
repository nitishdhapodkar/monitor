package common.util.json;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.Collection;
import java.util.List;

import org.json.JSONObject;

public class Helper {

	public static boolean isEmpty(Collection collection) {
		if (collection != null && collection.size() > 0) {
			return false;
		} else {
			return true;
		}

	}

	public static boolean isBlankOrNull(String value) {
		if ((value == null) || (value.trim().length() == 0)) {
			return true;
		} else {
			return false;
		}
	}

	public static boolean jsonIsBlankOrNull(String json) {
		JSONObject jsonObject = new JSONObject(json);
		if ((jsonObject.length() == 0)) {
			return true;
		} else {
			return false;
		}
	}
	
	public static String listToString(List<String> list) {

		if (!Helper.isEmpty(list)) {
			return list.toString();
		} else {
			return null;
		}
	}

	public static String convertStreamToString(InputStream is) throws IOException {
    	String result = new BufferedReader(new InputStreamReader(is)).readLine();
    	return result;
    }


}
