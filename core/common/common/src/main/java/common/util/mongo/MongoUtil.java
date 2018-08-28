package common.util.mongo;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.json.simple.JSONArray;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.google.gson.Gson;

import common.util.json.Helper;


public class MongoUtil {

	final static Logger LOGGER = LoggerFactory.getLogger(MongoUtil.class);

	public static String buildQuery(String databaseName, String collectionName, String columns, String text) {
		String queryText = "{$regex : \".*" + text + ".*\",$options: 'i'}";
		StringBuilder query = new StringBuilder("{$or:[");
		try {
			if (!Helper.isBlankOrNull(columns) && !Helper.isBlankOrNull(collectionName)
					&& !Helper.isBlankOrNull(text)) {
				JSONParser parser = new JSONParser();
				JSONArray array;

				array = (JSONArray) parser.parse(columns);

				for (Object column : array) {
					query.append("{\"" + column + "\" : " + queryText + "}");
					query.append(",");
				}
				query.replace(query.length() - 1, query.length(), "]}");
			}
		} catch (ParseException e) {
			LOGGER.error("Error in Building Query", e.getMessage());
		}
		return query.toString();
	}

	public static String getIdQuery(String id) {
		return "{\"id\" : \"" + id + "\"}";
	}

	public static String generateQuery(Map<String, String> searchElement, boolean isAnd) {

		List<Map<String, String>> searchAttributes = new ArrayList<Map<String, String>>();

		for (String key : searchElement.keySet()) {
			Map<String, String> element = new HashMap<String, String>();
			element.put(key, searchElement.get(key));
			searchAttributes.add(element);
		}

		String jsonQuery = "{";
		if (isAnd)
			jsonQuery += "\"$and\":";
		else
			jsonQuery += "\"$or\":";
		jsonQuery += new Gson().toJson(searchAttributes) + "}";
		System.out.println(jsonQuery);
		return jsonQuery;
	}
}
