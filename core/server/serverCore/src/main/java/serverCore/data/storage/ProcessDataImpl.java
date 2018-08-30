package serverCore.data.storage;

import common.jobs.CommandType;
import common.util.json.JsonParser;
import common.util.mongo.MongoQueryExecutor;
import serverCore.constants.GlobalConstants;

public class ProcessDataImpl implements IProcessData{

	@Override
	public void storeData(String data) {
		MongoQueryExecutor.insertIntoDB(GlobalConstants.dataStreamingDB, getDataType(data).toString(), data);
		
	}
	
	private CommandType getDataType(String data) {
		String commandType = JsonParser.getAttribute(data, "commandType");
		return CommandType.valueOf(commandType);
	}

}
