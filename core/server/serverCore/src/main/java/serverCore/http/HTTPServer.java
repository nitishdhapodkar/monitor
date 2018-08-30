package serverCore.http;

import java.io.IOException;
import java.net.InetSocketAddress;

import com.sun.net.httpserver.HttpServer;

import serverCore.constants.GlobalConstants;

public class HTTPServer {
	
	public void initHTTPServer() throws IOException {
		
		HttpServer server = HttpServer.create(new InetSocketAddress(GlobalConstants.httpPortNo), 0);
		server.createContext("/data", new DataReader());
        server.setExecutor(null); 
        server.start();
	}

}
