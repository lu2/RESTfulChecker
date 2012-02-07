package tk.ludva.restfulchecker;

import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class RemoteResource {
	private String url="http://";
	private String method;
	private Map<String, String> requestHeaders;
	private Map<String, String> responseHeaders;
	private String requestBody;
	private String responseBody;
	private String responseCode;
	private String responseMessage;

	public RemoteResource() {
		requestHeaders = new HashMap<String, String>();
		requestHeaders.put("hlavicka", "jeji hodnota");
		requestHeaders.put("hlavicka2", "jeji hodnota2");
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

	public Map<String, String> getRequestHeaders() {
		return requestHeaders;
	}

	public void setRequestHeaders(Map<String, String> requestHeaders) {
		this.requestHeaders = requestHeaders;
	}

	public Map<String, String> getResponseHeaders() {
		return responseHeaders;
	}

	public void setResponseHeaders(Map<String, String> responseHeaders) {
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

	public String getResponseCode() {
		return responseCode;
	}

	public void setResponseCode(String responseCode) {
		this.responseCode = responseCode;
	}

	public String getResponseMessage() {
		return responseMessage;
	}

	public void setResponseMessage(String responseMessage) {
		this.responseMessage = responseMessage;
	}	
	
	public Set<String> getRequestHeadersKeys() {
		return requestHeaders.keySet();
	}
	
	public void setRequestHeadersKeys(List<String> requestHeadersKeys) {
		requestHeaders = new HashMap<String, String>();
		for (Iterator<String> iterator = requestHeadersKeys.iterator(); iterator.hasNext();) {
			String string = (String) iterator.next();
			requestHeaders.put(string, null);
		}
	}
	
	public Collection<String> getRequestHeaderValues() {
		return requestHeaders.values();
	}
	
}
