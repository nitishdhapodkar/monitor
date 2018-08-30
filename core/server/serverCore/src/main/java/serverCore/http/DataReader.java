package serverCore.http;

import java.io.IOException;
import java.io.InputStream;

import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;

import common.util.json.Helper;
import serverCore.data.storage.IProcessData;
import serverCore.data.storage.ProcessDataImpl;

public class DataReader implements HttpHandler {

	@Override
	public void handle(HttpExchange httpExchange) throws IOException {
		
		IProcessData processData = null;
		InputStream is = null;
		String dataLine = null;
		
		try {
			
			is = httpExchange.getRequestBody();
        	dataLine = Helper.convertStreamToString(is);
        	processData = new ProcessDataImpl();
        	processData.storeData(dataLine);
			
		} catch (Exception e) {
			
			e.printStackTrace();
		} finally {
			processData = null;
			is = null;
			dataLine = null;
		}
	}
}
