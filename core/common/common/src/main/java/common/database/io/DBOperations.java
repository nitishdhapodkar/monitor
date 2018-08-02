package common.database.io;

import java.io.File;
import java.util.HashMap;
import java.util.Map;

import common.exceptions.SqInmenoryDBException;

public class DBOperations {
	
	private Map<String, Map<String, Object>> database;
	
	private String dataDir;
	
	DBFileHandler dbFileHandler;

	public DBOperations( String dataDir) {
		this.database = new HashMap<>();
		this.dataDir = dataDir;
		dbFileHandler = new DBFileHandler();
	}
	
	public void createTable(String tableName) throws SqInmenoryDBException {
		
		this.database.put(tableName, new HashMap<>());
	}
	
	public void deleteTable(String tableName) throws SqInmenoryDBException {
		
		this.database.remove(tableName);
		dbFileHandler.deleteFile(getFileNameFromTableName(tableName));
			
	}
	
	public void writeInTable(String tableName, Object object) throws SqInmenoryDBException {
		
		this.database.get(tableName).put(DatabaseUtil.getNewId(), object);
		writeTableToFile(tableName);
	}
	
	public void writeInTable(String tableName, Object object, String id) throws SqInmenoryDBException {

		this.database.get(tableName).put(id, object);
		writeTableToFile(tableName);
	}
	
	public void deleteFromDatabase(String tableName, String id) throws SqInmenoryDBException {
		this.database.get(tableName).remove(id);
		writeTableToFile(tableName);
	}
	
	public Object readFromDatabase(String tableName, String id) {
		return this.database.get(tableName).get(id);
	}
	
	private String getFileNameFromTableName(String fileName) {
		return this.dataDir + File.separator + fileName;
	}
	
	private void writeTableToFile(String tableName)  throws SqInmenoryDBException {
		dbFileHandler.writeObject(this.database.get(tableName), getFileNameFromTableName(tableName));
	}

}
