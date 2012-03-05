package tk.ludva.restfulchecker;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Iterator;
import java.util.logging.Logger;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class APIcheckerController {
	private static final Logger log = Logger.getLogger(APIcheckerController.class.getName());
	
	@RequestMapping(value="/", method=RequestMethod.GET)
	public String showCheckAPI(ApiEntry remoteResource){
		return "checkapi";
	}
	
	@RequestMapping(value="/", method=RequestMethod.POST)
	public String doCheckAPI(ApiEntry apiEntry){
		HttpURLConnection conn = null;
		try {
			URL remoteUrl = new URL(apiEntry.getUrl());
			conn = (HttpURLConnection)remoteUrl.openConnection();
			conn.setRequestMethod(apiEntry.getMethod());
			for (Iterator<Header> iterator = apiEntry.getRequestHeaders().iterator(); iterator.hasNext();) {
				Header header = (Header) iterator.next();
				if (header.isInUse()) conn.setRequestProperty(header.getHeaderKey(), header.getHeaderValue());
			}
			if (apiEntry.isUseRequestBody()) {
				conn.setDoOutput(true);
				DataOutputStream data = new DataOutputStream(conn.getOutputStream());
				data.write(apiEntry.getRequestBody().getBytes());
				data.flush();
				data.close();
			}
			apiEntry.setResponseCode(conn.getResponseCode());
			apiEntry.setResponseMessage(conn.getResponseMessage());
			for (int n=0; n<conn.getHeaderFields().size(); n++) {
				apiEntry.getResponseHeaders().add(new Header(conn.getHeaderFieldKey(n), conn.getHeaderField(n), true));
			}
			StringBuilder responseBody = new StringBuilder();
			String radek;
			BufferedReader reader = new BufferedReader(new InputStreamReader(conn.getInputStream()));
			while ((radek = reader.readLine()) != null) {
				responseBody.append(radek);
			}
			apiEntry.setResponseBody(responseBody.toString());
		} catch (MalformedURLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			log.severe(e.getMessage());
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			log.severe(e.getMessage());
		}
		finally {
			if (conn != null) conn.disconnect();
		}
		return "checkapi";
	}
	
	@RequestMapping(value="/client.html", method=RequestMethod.GET)
	public String showClient(RemoteResource remoteResource){
		return "client";
	}
	
	@RequestMapping(value="/client.html", method=RequestMethod.POST)
	public String doClient(RemoteResource remoteResource){
		HttpURLConnection conn = null;
		try {
			URL remoteUrl = new URL(remoteResource.getUrl());
			conn = (HttpURLConnection)remoteUrl.openConnection();
			conn.setRequestMethod(remoteResource.getMethod());
			for (Iterator<Header> iterator = remoteResource.getRequestHeaders().iterator(); iterator.hasNext();) {
				Header header = (Header) iterator.next();
				if (header.isInUse()) conn.setRequestProperty(header.getHeaderKey(), header.getHeaderValue());
			}
			if (remoteResource.isUseRequestBody()) {
				conn.setDoOutput(true);
				DataOutputStream data = new DataOutputStream(conn.getOutputStream());
				data.write(remoteResource.getRequestBody().getBytes());
				data.flush();
				data.close();
			}
			remoteResource.setResponseCode(conn.getResponseCode());
			remoteResource.setResponseMessage(conn.getResponseMessage());
			for (int n=0; n<conn.getHeaderFields().size(); n++) {
				remoteResource.getResponseHeaders().add(new Header(conn.getHeaderFieldKey(n), conn.getHeaderField(n), true));
			}
			StringBuilder responseBody = new StringBuilder();
			String radek;
			BufferedReader reader = new BufferedReader(new InputStreamReader(conn.getInputStream()));
			while ((radek = reader.readLine()) != null) {
				responseBody.append(radek);
			}
			remoteResource.setResponseBody(responseBody.toString());
		} catch (MalformedURLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			log.severe(e.getMessage());
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			log.severe(e.getMessage());
		}
		finally {
			if (conn != null) conn.disconnect();
		}
		return "client";
	}

}
