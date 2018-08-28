package common.util.mongo;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.bson.Document;

import com.mongodb.MongoClient;
import com.mongodb.ServerAddress;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;

import common.constants.ErrorCode;

public class MongoDBexce {

	private static Map<String, MongoDatabase> databases = null;
	private static int port;
	private static String hostName;
	private static List<String> databasesNames;

	public static void init(int iport, String ihostName, List<String> idatabasesNames) throws SqDatabaseConnectionException {
		port = iport;
		hostName = ihostName;
		databasesNames = idatabasesNames;
		CreateConnections();
		
	}

	public static void CreateConnections() throws SqDatabaseConnectionException {

		try {
			databases = new HashMap<>();
			MongoClient client = null;
			client = new MongoClient(new ServerAddress(hostName, port));
			
			for(String databaseName : databasesNames) {
				databases.put(databaseName, client.getDatabase(databaseName));
			}
			
		} catch (Exception e) {
			
			throw new SqDatabaseConnectionException("Unable to make connection to database ", e, ErrorCode.DATABASE_CONNECTION);
		}

	}

	public static MongoCollection<Document> getConnection(String database, String collectionName) throws SqDatabaseConnectionException {

		MongoCollection<Document> collection = null;

		try {
			
			collection = databases.get(database).getCollection(collectionName);

		} catch (Exception e) {
			try {
				CreateConnections();
			} catch (Exception dbe) {
				throw new SqDatabaseConnectionException("Unable to make connection to database ", e, ErrorCode.DATABASE_CONNECTION);
			}
		}

		return collection;

	}

	public static MongoDatabase getDatabase(String databaseName) throws SqDatabaseConnectionException {
		return databases.get(databaseName);
	}
	
}
