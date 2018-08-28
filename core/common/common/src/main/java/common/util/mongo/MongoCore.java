package common.util.mongo;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.bson.Document;
import org.bson.conversions.Bson;
import org.json.JSONArray;
import org.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.google.gson.Gson;
import com.mongodb.BasicDBObject;
import com.mongodb.client.AggregateIterable;
import com.mongodb.client.FindIterable;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoCursor;
import com.mongodb.client.MongoDatabase;

public class MongoCore {
	
	final static Logger LOGGER = LoggerFactory.getLogger(MongoCore.class);
	
	public static List<String> getResultList(String databaseName, String collectionName, String jsonQuery, Integer limit, Integer skip, List<String> columns, String sort) {

		MongoCollection<Document> dbCollection = null;
		FindIterable<Document> findIterable = null;
		List<String> list = null;
		
		MongoQueryObject mongoQueryObject = new MongoQueryObject(databaseName, collectionName, jsonQuery, limit, skip, columns, sort, DatabaseOperations.GET);
		try {

			BasicDBObject query = null;
			if (jsonQuery != null) {
				Object o = com.mongodb.util.JSON.parse(jsonQuery);
				query = (BasicDBObject) o;
			}

			dbCollection = MongoDBexce.getConnection(databaseName, collectionName);

			Map<String, Object> projection = new HashMap<String, Object>();
			projection.put("_id", 0);

			if (columns != null) {
				for (String column : columns) {
					projection.put(column, 1);
				}
			}

			if (sort == null) {
				if (query == null) {
					if (limit != null && skip != null) {
						findIterable = dbCollection.find().limit(limit).skip(skip).projection(new Document(projection));
					} else {
						findIterable = dbCollection.find().projection(new Document(projection));
					}

				} else {
					if (limit != null && skip != null) {
						findIterable = dbCollection.find(query).limit(limit).skip(skip).projection(new Document(projection));
					} else {
						findIterable = dbCollection.find(query).projection(new Document(projection));
					}

				}
			} else {

				Object o = com.mongodb.util.JSON.parse(sort);
				Bson sortObj = (Bson) o;

				if (query == null) {
					if (limit != null && skip != null) {
						findIterable = dbCollection.find().sort(sortObj).limit(limit).skip(skip).projection(new Document(projection));
					} else {
						findIterable = dbCollection.find().sort(sortObj).projection(new Document(projection));
					}

				} else {
					if (limit != null && skip != null) {
						findIterable = dbCollection.find(query).sort(sortObj).limit(limit).skip(skip).projection(new Document(projection));
					} else {
						findIterable = dbCollection.find(query).sort(sortObj).projection(new Document(projection));
					}

				}
			}

			Iterator<Document> iterator = findIterable.iterator();

			if (iterator.hasNext()) {
				Gson gson = new Gson();
				list = new ArrayList<>();
				list.add(gson.toJson(iterator.next()));
				while (iterator.hasNext()) {
					list.add(gson.toJson(iterator.next()));
				}
			}

		} catch (Exception e) {
			LOGGER.error("MONGO ERROR " + mongoQueryObject.toJson(), e.getMessage());
		}

		return list;

	}
	
	public static List<String> getAggregateObjects(String databaseName, String collectionName, String jsonQuery, String columnName, DatabaseOperations operation) {
		
		MongoCollection<Document> dbCollection = null;
		List<? extends Bson> query = null;
		List<String> result = null;
		AggregateIterable<Document> aggregateIterable = null;
		List<String> columns = Arrays.asList(collectionName);
		MongoQueryObject mongoQueryObject = new MongoQueryObject(databaseName, collectionName, jsonQuery, null, null, columns, null, operation);
		
		JSONArray queryWithProjection = new JSONArray(jsonQuery);
		queryWithProjection.put(new JSONObject("{ \"$project\": { \"_id\": 0}}"));
		
		try {
			Object o = com.mongodb.util.JSON.parse(queryWithProjection.toString());
			query = (List<? extends Bson>) o;
			dbCollection = MongoDBexce.getConnection(databaseName, collectionName);
		} catch (Exception e) {
			LOGGER.error("MONGO ERROR " + mongoQueryObject.toJson(), e.getMessage());
		}
		
		if(operation == DatabaseOperations.AGGREGATE) {
			
			try {

				aggregateIterable = dbCollection.aggregate(query);
				Iterator<Document> iterator = aggregateIterable.iterator();

				if (iterator.hasNext()) {
					result = new ArrayList<>();
					result.add(iterator.next().toJson());
					while (iterator.hasNext()) {
						result.add(iterator.next().toJson());
					}
				}


			} catch (Exception e) {
				LOGGER.error("MONGO ERROR " + mongoQueryObject.toJson(), e.getMessage());
			}
			
		} else if (operation == DatabaseOperations.DISTINCT) {
			try {
				MongoCursor<String> iterator = null;

				if (jsonQuery != null) {
					Object o = null;
					o = com.mongodb.util.JSON.parse(jsonQuery);
					Bson filterCond = (Bson) o;
					iterator = dbCollection.distinct(columnName, filterCond, String.class).iterator();
				} else {
					iterator = dbCollection.distinct(columnName, String.class).iterator();
				}
				if (iterator.hasNext()) {
					result = new ArrayList<>();
					result.add(iterator.next());
					while (iterator.hasNext()) {
						result.add(iterator.next());
					}
				}
			} catch (Exception e) {
				LOGGER.error("MONGO ERROR " + mongoQueryObject.toJson(), e.getMessage());
			}
		}
		
		return result;
		
	}
	
	public static long getCount(String databaseName, String collectionName, String jQuery) {
		
		MongoCollection<Document> dbCollection = null;
		long count = 0;
		
		MongoQueryObject mongoQueryObject = new MongoQueryObject(databaseName, collectionName, jQuery, null, null, null, null, DatabaseOperations.COUNT);

		try {
			
			dbCollection = MongoDBexce.getConnection(databaseName, collectionName);
			if(jQuery != null) {
				
				Object o = com.mongodb.util.JSON.parse(jQuery);
				Bson b = (Bson) o;
				count = dbCollection.count(b);
			} else {
				count = dbCollection.count();
			}
			

		} catch (Exception e) {
			LOGGER.error("MONGO ERROR " + mongoQueryObject.toJson(), e.getMessage());

		} finally {
			if (dbCollection != null) {
				dbCollection = null;
			}
		}
		return count;
	}
	
	public static void doDMLOperations(String databaseName, String collectionName, String jsonObject, String filter, boolean isMultiple, DatabaseOperations operation) {
		
		MongoCollection<Document> dbCollection = null;
		Document document = Document.parse(jsonObject);
		
		MongoQueryObject mongoQueryObject = new MongoQueryObject(databaseName, collectionName, filter, null, null, null, null, operation);

		try {
			dbCollection = MongoDBexce.getConnection(databaseName, collectionName);
			if ( operation == DatabaseOperations.INSERT) {
				
				dbCollection.insertOne(document);
			} else if ( operation == DatabaseOperations.REMOVE ) {
				
				if( isMultiple ) {
					dbCollection.deleteMany(document);
				} else {
					dbCollection.deleteOne(document);
				}
			} else if ( operation == DatabaseOperations.REMOVE_ALL ) {
				dbCollection.deleteMany(new Document());
			} else if ( operation == DatabaseOperations.UPDATE ) {
				
				Bson updateOperationDocument = null;
				
				Object o1 = com.mongodb.util.JSON.parse(filter);
				BasicDBObject filterObj = (BasicDBObject) o1;
				Object o2 = com.mongodb.util.JSON.parse(jsonObject);
				BasicDBObject updateObj = (BasicDBObject) o2;
				updateOperationDocument = new Document("$set", updateObj);
				
				if( !isMultiple ) {
					
					dbCollection.updateOne(filterObj, updateOperationDocument);
				} else {
					dbCollection.updateMany(filterObj, updateOperationDocument);
				}
				
			}
			

		} catch (Exception e) {
			LOGGER.error("MONGO ERROR " + mongoQueryObject.toJson(), e.getMessage());
		}
	}

	public static Document runStoredProcedure(String databaseName, String storedProcedure) {

		Document doc = null;
		MongoQueryObject mongoQueryObject = new MongoQueryObject(databaseName, null, storedProcedure, null, null, null, null, DatabaseOperations.RUN_PROCEDURE);
		try {
			MongoDatabase getDb = MongoDBexce.getDatabase(databaseName);
			doc = getDb.runCommand(new Document("$eval", storedProcedure));

		} catch (Exception e) {
			LOGGER.error("MONGO ERROR " + mongoQueryObject.toJson(), e.getMessage());
		}
		return doc;
	}

public static Document runJavaScript(String databaseName, String javaScript) {
		
		Document doc = null;
		try {
			MongoDatabase getDb = MongoDBexce.getDatabase(databaseName);
			doc = getDb.runCommand(new Document("$eval", javaScript));

		} catch (Exception e) {
			e.printStackTrace();
		}
		return doc;
	}
}
