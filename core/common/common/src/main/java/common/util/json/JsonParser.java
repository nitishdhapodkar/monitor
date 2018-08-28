package common.util.json;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import com.google.gson.Gson;

public class JsonParser {

	
	public static List<String> getValueList(String json, String attribute) {
		
		String stringValue = getAttribute(json, attribute);
		return new Gson().fromJson(stringValue, ArrayList.class);
		
	}
	
	public static Set<String> getValueSet(String json, String attribute) {
		
		String stringValue = getAttribute(json, attribute);
		return new Gson().fromJson(stringValue, HashSet.class);
		
	}

	public static String getAttribute(String json, String attribute) {

		String finalValue = null;
		try {

			JSONParser jsonParser = new JSONParser();
			JSONObject jsonObject = (JSONObject) jsonParser.parse(json);

			finalValue = getJsonValue(jsonObject, attribute);

		} catch (Exception e) {
//			LOGGER.error("error occurred", e);
//			messageBuilder.putMessage("ErrorMessage", e.getMessage());
//			Logger.addLog(LoggerLevel.ERROR, messageBuilder.getModuleName(), messageBuilder.createMessage());
		}

		return finalValue;
	}

	public static String insertAttributr(String json, String attribute, String value) {

		String updatedJson = "";
		try {
			JSONParser jsonParser = new JSONParser();
			JSONObject jsonObject = (JSONObject) jsonParser.parse(json);

			updateJsonAttribute(jsonObject, attribute, value);

			updatedJson = jsonObject.toJSONString();

		} catch (ParseException e) {
//			LOGGER.error("error occurred while inserting attribute into JSON", e);
		}

		return updatedJson;
	}

	/**
	 * This method will insert a new property to a JSON. This method has been tested
	 * to work with the all Primitives and the following: String, Integer, Double,
	 * ArrayList, HashMap. This method does not handle {@code Set} data type as
	 * value. If a Set is passed then the values of the array in the JSON for the
	 * set will not be enclosed in quotes.
	 * 
	 * @author
	 * @param jsonObject
	 *            - The JSON object to be updated.
	 * @param property
	 *            - The property to be added to the {@code JSONObject}
	 * @param propertyValue
	 *            - The property value to be added to the JSON property.
	 * @return void
	 */
	public static String insertAttribute(String json, String attribute, Object value) {
		String updatedJson = "";
		try {
			JSONParser jsonParser = new JSONParser();
			JSONObject jsonObject = (JSONObject) jsonParser.parse(json);
			updateJsonAttribute(jsonObject, attribute, value);
			updatedJson = jsonObject.toJSONString();
		} catch (ParseException e) {
//			LOGGER.error("error occurred while inserting attribute into JSON", e);
		}
		return updatedJson;
	}

	public static String updateAttributr(String json, String attribute, String value) {

		JSONObject jsonObject = null;
		try {
			JSONParser jsonParser = new JSONParser();
			jsonObject = (JSONObject) jsonParser.parse(json);
			
			if( value != null ) {
				updateJsonAttribute(jsonObject, attribute, value);
			}

		} catch (ParseException e) {
//			LOGGER.error("error occurred while updating attribute into JSON", e);
		}

		return jsonObject.toJSONString();
	}

	public static Boolean validateJson(String json, List<String> attributes) {

		Boolean isValidJson = true;
		try {

			for (String attribute : attributes) {
				String attributeValue = getAttribute(json, attribute);
				if (attributeValue.isEmpty() || attributeValue == null) {
					isValidJson = false;
					System.out.println("Key  not found : "+attribute);
					break;
				}
			}
		} catch (Exception e) {
//			LOGGER.error("error occurred while validating given json", e);

			isValidJson = false;
		}

		return isValidJson;
	}

	public static Boolean validateJsonWithErrorLog(String json, List<String> attributes) {

		Boolean isValidJson = true;
		try {

			for (String attribute : attributes) {
				String attributeValue = getAttribute(json, attribute);
				if (attributeValue.isEmpty() || attributeValue == null) {
					isValidJson = false;
					break;
				}
			}
		} catch (Exception e) {
//			LOGGER.error("error occurred while validating given json", e);

			isValidJson = false;
		}

		return isValidJson;
	}
	
	public static String createJson(String jsonTemplate, Map<String, String> attributes) {

		String json = jsonTemplate;
		Iterator entries = attributes.entrySet().iterator();

		try {
			while (entries.hasNext()) {

				Entry thisEntry = (Entry) entries.next();

				String propertyKey = thisEntry.getKey().toString();
				String propertyValue = thisEntry.getValue().toString();

				json = updateAttributr(json, propertyKey, propertyValue);

			}
		} catch (Exception e) {
//			LOGGER.error("error occurred while creating json", e);
		}
		return json;
	}

	public static String convertToJson(Object javaObject) {

		String json = new Gson().toJson(javaObject);
		if( javaObject != null ) {
			return json;
		} else {
			return null;
		}
	}

	private static String getJsonValue(JSONObject jsonObject, String property) {

		String propertyValue = null;
		String tempAttributes;
		String firstAttribute;
		String otherAttributes;
		boolean isArray = false;

		try {
			if (property.contains(".")) {
				tempAttributes = property.replace(".", "##");
				firstAttribute = tempAttributes.substring(0, tempAttributes.indexOf("##")).trim();
				otherAttributes = tempAttributes.substring(tempAttributes.indexOf("##") + 2).replace("##", ".").trim();
			} else {
				firstAttribute = property;
				otherAttributes = null;
				if (firstAttribute.trim().endsWith("]")) {

					int index = Integer.parseInt(
							firstAttribute.substring(firstAttribute.indexOf("[") + 1, firstAttribute.indexOf("]")));
					firstAttribute = firstAttribute.substring(0, firstAttribute.indexOf("["));
					JSONArray jsonArray = (JSONArray) jsonObject.get(firstAttribute);
					propertyValue = jsonArray.get(index).toString();
				} else {
					if (jsonObject.get(property) != null) {
						propertyValue = String.valueOf(jsonObject.get(property));
					} else {
						propertyValue = null;
					}
				}

				return propertyValue;
			}

			if (firstAttribute.trim().endsWith("]")) {

				int index = Integer.parseInt(
						firstAttribute.substring(firstAttribute.indexOf("[") + 1, firstAttribute.indexOf("]")));
				firstAttribute = firstAttribute.substring(0, firstAttribute.indexOf("["));
				JSONArray jsonArray = (JSONArray) jsonObject.get(firstAttribute);
				propertyValue = getJsonValue((JSONObject) jsonArray.get(index), otherAttributes);
			} else {
				propertyValue = getJsonValue((JSONObject) jsonObject.get(firstAttribute), otherAttributes);
			}
		} catch (Exception e) {
			// e.printStackTrace();
//			LOGGER.error("error occurred while fetching json value for property : "+property, e);
		}

		return propertyValue;

	}

	public static int getJsonAttributeArraySize(String json, String attribute) {

		String propertyJson = null;
		int arraySize = -1;
		JSONArray jsonArray = null;
		JSONParser jsonParser = null;
		try {
			propertyJson = getAttribute(json, attribute);
			jsonParser = new JSONParser();
			jsonArray = (JSONArray) jsonParser.parse(propertyJson);
			arraySize = jsonArray.size();
		} catch (Exception e) {
//			LOGGER.error("error occurred", e);
		} finally {
			propertyJson = null;
			jsonArray = null;
			jsonParser = null;
		}
		return arraySize;
	}

	private static void updateJsonAttribute(JSONObject jsonObject, String property, String propertyValue) {

		String tempAttributes;
		String firstAttribute;
		String otherAttributes;
		boolean isArray = false;
		
		try {
			jsonObject = getAttributeForInsert(jsonObject, property);
			if (property.contains(".")) {
				tempAttributes = property.replace(".", "##");
				firstAttribute = tempAttributes.substring(0, tempAttributes.indexOf("##")).trim();
				otherAttributes = tempAttributes.substring(tempAttributes.indexOf("##") + 2).replace("##", ".").trim();

			} else {
				firstAttribute = property;
				otherAttributes = null;
				if (!firstAttribute.trim().endsWith("]")) {
					jsonObject.put(property, propertyValue);
				} else {

				}
			}

			if (firstAttribute.trim().endsWith("]")) {

				int index = Integer.parseInt(
						firstAttribute.substring(firstAttribute.indexOf("[") + 1, firstAttribute.indexOf("]")));
				firstAttribute = firstAttribute.substring(0, firstAttribute.indexOf("["));
				JSONArray jsonArray = (JSONArray) jsonObject.get(firstAttribute);
				if (otherAttributes == null) {
					jsonArray.add(propertyValue);
					jsonObject.put(firstAttribute, jsonArray);
				} else {
					updateJsonAttribute((JSONObject) jsonArray.get(index), otherAttributes, propertyValue);
				}

			} else {
				if (otherAttributes != null) {
					updateJsonAttribute((JSONObject) jsonObject.get(firstAttribute), otherAttributes, propertyValue);
				}
			}

		} catch (Exception e) {
			// e.printStackTrace();
//			LOGGER.error("error occurred while updating json", e);
		}
	}

	/**
	 * This method will update a JSON to include the {@code property} parameter.
	 * This method has been tested to work with the all Primitives and the
	 * following: String, Integer, Double, ArrayList, HashMap. This method does not
	 * handle {@code Set} data type as value. If a Set is passed then the values of
	 * the array in the JSON for the set will not be enclosed in quotes.
	 * 
	 * @author
	 * @param jsonObject
	 *            - The JSON object to be updated.
	 * @param property
	 *            - The property to be added to the {@code JSONObject}
	 * @param propertyValue
	 *            - The property value to be added to the JSON property.
	 * @return void
	 */
	private static void updateJsonAttribute(JSONObject jsonObject, String property, Object propertyValue) {
		String tempAttributes;
		String firstAttribute;
		String otherAttributes;
		try {
			if (property.contains(".")) {
				tempAttributes = property.replace(".", "##");
				firstAttribute = tempAttributes.substring(0, tempAttributes.indexOf("##")).trim();
				otherAttributes = tempAttributes.substring(tempAttributes.indexOf("##") + 2).replace("##", ".").trim();
			} else {
				firstAttribute = property;
				otherAttributes = null;
				if (!firstAttribute.trim().endsWith("]")) {
					jsonObject.put(property, propertyValue);
				}
			}
			if (firstAttribute.trim().endsWith("]")) {

				int index = Integer.parseInt(
						firstAttribute.substring(firstAttribute.indexOf("[") + 1, firstAttribute.indexOf("]")));
				firstAttribute = firstAttribute.substring(0, firstAttribute.indexOf("["));
				JSONArray jsonArray = (JSONArray) jsonObject.get(firstAttribute);
				if (otherAttributes == null) {
					jsonArray.add(propertyValue);
					jsonObject.put(firstAttribute, jsonArray);
				} else {
					updateJsonAttribute((JSONObject) jsonArray.get(index), otherAttributes, propertyValue);
				}
			} else {
				if (otherAttributes != null) {
					updateJsonAttribute((JSONObject) jsonObject.get(firstAttribute), otherAttributes, propertyValue);
				}
			}
		} catch (Exception e) {
			// e.printStackTrace();
//			LOGGER.error("error occurred while updating json", e);
		}
	}
	
	private static JSONObject getAttributeForInsert(JSONObject json, String attributeName) {
		
		String[] attributeParents = attributeName.replace(".", "##") .split("##");
		String childAttribute = null;
		for(String attribute : attributeParents) {
			
			if(childAttribute == null) {
				childAttribute = attribute;
			} else {
				childAttribute += "." + attribute;
			}
			
			JSONObject o = new JSONObject();
			if(getJsonValue(json, childAttribute) == null) {
				JsonParser.updateJsonAttribute(json, childAttribute, o);
			}
		}
				
		return json;
	}
	
	public static void main(String[] args) {
		String array = "[\"a\",\"b\"]";
		List<String> list = new Gson().fromJson(array, ArrayList.class);
		System.out.println(list.toString());
	}

}
