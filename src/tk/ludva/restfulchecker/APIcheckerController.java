package tk.ludva.restfulchecker;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Iterator;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class APIcheckerController {
	
	@RequestMapping(value="/", method=RequestMethod.GET)
	public String showCheckAPI(RemoteResource remoteResource){
		return "checkapi";
	}
	
	@RequestMapping(value="/", method=RequestMethod.POST)
	public String doCheckAPI(RemoteResource remoteResource){
		HttpURLConnection conn = null;
		try {
			URL remoteUrl = new URL(remoteResource.getUrl());
			conn = (HttpURLConnection)remoteUrl.openConnection();
			conn.setRequestMethod(remoteResource.getMethod());
			
			for (Iterator<Header> iterator = remoteResource.getRequestHeaders().iterator(); iterator.hasNext();) {
				Header header = (Header) iterator.next();
				if (header.isInUse()) conn.setRequestProperty(header.getHeaderKey(), header.getHeaderValue());
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
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return "checkapi";
	}

}
