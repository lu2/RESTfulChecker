package tk.ludva.restfulchecker;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.logging.Logger;

public class RemoteResource implements Cloneable {
	private static final Logger log = Logger.getLogger(RemoteResource.class.getName());
	private String url="http://";
	private String method;
	private List<Header> requestHeaders = new ArrayList<Header>();
	private List<Header> responseHeaders = new ArrayList<Header>();
	private String requestBody;
	private boolean useRequestBody;
	private String responseBody;
	private int responseCode;
	private String responseMessage;

	public RemoteResource() {
	}
	
	public void sendRequest() {
		deletePreviousResponse();
		HttpURLConnection conn = null;
		try {
			URL remoteUrl = new URL(getUrl());
			conn = (HttpURLConnection)remoteUrl.openConnection();
			conn.setRequestMethod(getMethod());
			for (Iterator<Header> iterator = getRequestHeaders().iterator(); iterator.hasNext();) {
				Header header = (Header) iterator.next();
				if (header.isInUse()) conn.setRequestProperty(header.getHeaderKey(), header.getHeaderValue());
			}
			if (isUseRequestBody()) {
				conn.setDoOutput(true);
				DataOutputStream data = new DataOutputStream(conn.getOutputStream());
				data.write(getRequestBody().getBytes());
				data.flush();
				data.close();
			}
			setResponseCode(conn.getResponseCode());
			setResponseMessage(conn.getResponseMessage());
			for (int n=0; n<conn.getHeaderFields().size(); n++) {
				getResponseHeaders().add(new Header(conn.getHeaderFieldKey(n), conn.getHeaderField(n), true));
			}
			StringBuilder responseBody = new StringBuilder();
			String radek;
			BufferedReader reader = new BufferedReader(new InputStreamReader(conn.getInputStream()));
			while ((radek = reader.readLine()) != null) {
				responseBody.append(radek);
			}
			setResponseBody(responseBody.toString());
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
	}

	private void deletePreviousResponse() {
		responseHeaders = new ArrayList<Header>();
		responseBody = null;
		responseCode = 0;
		responseMessage = null;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public String getMethod() {
		return method;
	}

	public void setMethod(String method) {
		this.method = method;
	}

	public List<Header> getRequestHeaders() {
		return requestHeaders;
	}

	public void setRequestHeaders(List<Header> requestHeaders) {
		this.requestHeaders = requestHeaders;
	}

	public List<Header> getResponseHeaders() {
		return responseHeaders;
	}

	public void setResponseHeaders(List<Header> responseHeaders) {
		this.responseHeaders = responseHeaders;
	}

	public String getRequestBody() {
		return requestBody;
	}

	public void setRequestBody(String requestBody) {
		this.requestBody = requestBody;
	}

	public String getResponseBody() {
		return responseBody;
	}

	public void setResponseBody(String responseBody) {
		this.responseBody = responseBody;
	}

	public int getResponseCode() {
		return responseCode;
	}

	public void setResponseCode(int i) {
		this.responseCode = i;
	}

	public String getResponseMessage() {
		return responseMessage;
	}

	public void setResponseMessage(String responseMessage) {
		this.responseMessage = responseMessage;
	}

	public boolean isUseRequestBody() {
		return useRequestBody;
	}

	public void setUseRequestBody(boolean useRequestBody) {
		this.useRequestBody = useRequestBody;
	}	
	
	protected Object clone() throws CloneNotSupportedException {
		return super.clone();
	}
	
}
