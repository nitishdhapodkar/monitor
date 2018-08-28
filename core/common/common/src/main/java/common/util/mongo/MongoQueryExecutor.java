package common.util.mongo;

import java.util.List;

import org.bson.Document;

import common.util.json.Helper;
import common.util.json.JsonParser;


public class MongoQueryExecutor {

	public static String getDBObject(String databaseName, String collectionName, String jsonQuery, int limit, int skip) {

		List<String> list = MongoCore.getResultList(databaseName, collectionName, jsonQuery, limit, skip, null, null);
		return Helper.listToString(list);
	}

	public static String getDBObject(String databaseName, String collectionName, String jsonQuery) {

		List<String> list = MongoCore.getResultList(databaseName, collectionName, jsonQuery, null, null, null, null);
		return Helper.listToString(list);
	}

	public static String getDBObject(String databaseName, String collectionName, List<String> columns) {

		List<String> list = MongoCore.getResultList(databaseName, collectionName, null, null, null, columns, null);
		return Helper.listToString(list);
	}

	public static List<String> getDBList(String databaseName, String collectionName, String jsonQuery, int limit, int skip) {

		return  MongoCore.getResultList(databaseName, collectionName, jsonQuery, limit, skip, null, null);
	}

	public static List<String> getDBList(String databaseName, String collectionName, String jsonQuery) {

		return MongoCore.getResultList(databaseName, collectionName, jsonQuery, null, null, null, null);
	}

	public static List<String> getDBList(String databaseName, String collectionName, List<String> columns) {

		return MongoCore.getResultList(databaseName, collectionName, null, null, null, columns, null);
	}

	public static List<String> getDBList(String databaseName, String collectionName, List<String> columns, String jsonQuery) {

		return MongoCore.getResultList(databaseName, collectionName, jsonQuery, null, null, columns, null);
	}

	public static String getById(String databaseName, String collectionName, String id) {

		String result = null;
		String jsonQuery = MongoUtil.getIdQuery(id);
		List<String> listOfObjects = getDBList(databaseName, collectionName, jsonQuery);
		
		if (!listOfObjects.isEmpty()) {
			result = listOfObjects.get(0);
		}
		return result;
	}

	public static String getAggregateDBObject(String databaseName, String collectionName, String jsonQuery) {
		
		List<String> list = MongoCore.getAggregateObjects(databaseName, collectionName, jsonQuery, null, DatabaseOperations.AGGREGATE);
		return JsonParser.convertToJson(list);
	}

	public static List<String> getAggregateDBList(String databaseName, String collectionName, String jsonQuery) {

		return MongoCore.getAggregateObjects(databaseName, collectionName, jsonQuery, null, DatabaseOperations.AGGREGATE);
	}

	public static long getDBCount(String databaseName, String collectionName) {

		return MongoCore.getCount(databaseName, collectionName, null);
	}

	public static void insertIntoDB(String databaseName, String collectionName, String jsonObject) {

		MongoCore.doDMLOperations(databaseName, collectionName, jsonObject, null, false, DatabaseOperations.INSERT);
	}

	public static void removeFromDB(String databaseName, String collectionName, String jsonObject) {

		MongoCore.doDMLOperations(databaseName, collectionName, jsonObject, null, false, DatabaseOperations.REMOVE);
	}

	public static void removeById(String databaseName, String collectionName, String id) {
		String jsonObject = MongoUtil.getIdQuery(id);
		MongoCore.doDMLOperations(databaseName, collectionName, jsonObject, null, false, DatabaseOperations.REMOVE);
	}

	public static void removeWithFilter(String databaseName, String collectionName, String filter) {

		MongoCore.doDMLOperations(databaseName, collectionName, filter, null, true, DatabaseOperations.REMOVE);
	}

	public static void removeAll(String databaseName, String collectionName) {

		MongoCore.doDMLOperations(databaseName, collectionName, null, null, true, DatabaseOperations.REMOVE_ALL);
	}

	public static void updateOne(String databaseName, String collectionName, String filter, String update) {

		MongoCore.doDMLOperations(databaseName, collectionName, update, filter, false, DatabaseOperations.UPDATE);
	}

	public static void updateById(String databaseName, String collectionName, String id, String updateJson) {
		String jsonQuery = MongoUtil.getIdQuery(id);
		MongoCore.doDMLOperations(databaseName, collectionName, updateJson, jsonQuery, false, DatabaseOperations.UPDATE);
	}

	public static Document runStoredProcedure(String databaseName, String storedProcedure) {
		
		return MongoCore.runStoredProcedure(databaseName, storedProcedure); 
	}

	/**
	 * This method search text in specific columns and collection in DB. It sorts as
	 * well as is case insensitive. Please note sortType has be 1 or -1. 1
	 * represents sort by ascending order and -1 for descending order.
	 *
	 */
	public static String searchText(String databaseName, String collectionName, String columns, String text, String sortColumn, int sortType) {
		String query = MongoUtil.buildQuery(databaseName, collectionName, columns, text);
		String sort = null;
		if (!Helper.isBlankOrNull(sortColumn)) {
			sort = "{\"" + sortColumn + "\" : " + sortType + "}";
		}
		return getDBObjectWithSort(databaseName, collectionName, query, sort);
	}

	public static String getDBObjectWithSort(String databaseName, String collectionName, String jsonQuery, String sort) {

		List<String> list = MongoCore.getResultList(databaseName, collectionName, jsonQuery, null, null, null, sort);
		return Helper.listToString(list);
	}

	public static List<String> getDistinctValues(String databaseName, String collectionName, String columnName, String filter) {

		return MongoCore.getAggregateObjects(databaseName, collectionName, filter, columnName, DatabaseOperations.DISTINCT);
	}

	public static String getDBObject(String databaseName, String collectionName, List<String> columns, String jsonQuery, int limit, int skip) {

		List<String> list = MongoCore.getResultList(databaseName, collectionName, jsonQuery, limit, skip, columns, null);
		return Helper.listToString(list);
	}

	public static long getCountByQuery(String databaseName, String collectionName, String jQuery) {

		return MongoCore.getCount(databaseName, collectionName, jQuery);
	}

	public static void updateMany(String databaseName, String collectionName, String filter, String update) {

		MongoCore.doDMLOperations(databaseName, collectionName, update, filter, true, DatabaseOperations.UPDATE);

	}

	public static void update(String databaseName, String collectionName, String filter, String updateJson, boolean isMultiUpdate) {
		
		MongoCore.doDMLOperations(databaseName, collectionName, updateJson, filter, isMultiUpdate, DatabaseOperations.UPDATE);
	}

}
