package common.util.mongo;

import java.util.List;

import common.util.json.JsonParser;

public class MongoQueryObject {
	
	private String databaseName;
	private String collectionName;
	private String jsonQuery;
	private Integer limit;
	private Integer skip;
	private List<String> columns;
	private String sort;
	private DatabaseOperations method;
	
	public MongoQueryObject(String databaseName, String collectionName, String jsonQuery, Integer limit, Integer skip, List<String> columns, String sort, DatabaseOperations method) {
		
		this.databaseName = databaseName;
		this.collectionName = collectionName;
		this.jsonQuery = jsonQuery;
		this.limit = limit;
		this.skip = skip;
		this.columns = columns;
		this.sort = sort;
		this.method = method;
		
	}
	
	public String toJson() {
		return JsonParser.convertToJson(this);
	}
	
	public String getDatabaseName() {
		return databaseName;
	}

	public void setDatabaseName(String databaseName) {
		this.databaseName = databaseName;
	}

	public String getCollectionName() {
		return collectionName;
	}

	public void setCollectionName(String collectionName) {
		this.collectionName = collectionName;
	}

	public String getJsonQuery() {
		return jsonQuery;
	}

	public void setJsonQuery(String jsonQuery) {
		this.jsonQuery = jsonQuery;
	}

	public Integer getLimit() {
		return limit;
	}

	public void setLimit(Integer limit) {
		this.limit = limit;
	}

	public Integer getSkip() {
		return skip;
	}

	public void setSkip(Integer skip) {
		this.skip = skip;
	}

	public List<String> getColumns() {
		return columns;
	}

	public void setColumns(List<String> columns) {
		this.columns = columns;
	}

	public String getSort() {
		return sort;
	}

	public void setSort(String sort) {
		this.sort = sort;
	}

	public DatabaseOperations getMethod() {
		return method;
	}

	public void setMethod(DatabaseOperations method) {
		this.method = method;
	}
	
}
