package me.dretax.ambientspout.resources;


import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.InetSocketAddress;
import java.util.concurrent.Executors;

import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;
import com.sun.net.httpserver.HttpServer;

public class WebServer {

private File resoruceDir;
private HttpServer server;

public WebServer(int port, File resourceDir) throws IOException {
	if(resourceDir == null)
		throw new NullPointerException();

	this.resoruceDir = resourceDir;
	InetSocketAddress addr = new InetSocketAddress(port);
	server = HttpServer.create(addr, 0);
	server.createContext("/", new RootHandler());
	server.setExecutor(Executors.newCachedThreadPool());
	server.start();
	System.out.println("[Resources] Webserver for AmbientSpout is listening on port "+port);
	}

private class RootHandler implements HttpHandler {
			
			
			public void handle(HttpExchange exchange) throws IOException {
				String path = exchange.getRequestURI().getPath();
				if(path == null) {
					exchange.sendResponseHeaders(404, 0);
					OutputStream responseBody = exchange.getResponseBody();
					responseBody.flush();
					responseBody.close();
					return;
				}

				File file = new File(resoruceDir.getAbsolutePath()+File.separator+path);
				if(file.exists()) {
					exchange.sendResponseHeaders(200, 0);
					OutputStream responseBody = exchange.getResponseBody();
					byte[] barray = new byte[1024];
					InputStream is = new FileInputStream(file);
					while (is.read(barray) != -1) {
						responseBody.write(barray);
					}
					responseBody.flush();
					responseBody.close();
				}
				else {
					exchange.sendResponseHeaders(404, 0);
					OutputStream responseBody = exchange.getResponseBody();
					responseBody.flush();
					responseBody.close();
				}
			}
	}

}

