package tk.ludva.restfulchecker;

import java.util.ArrayList;
import java.util.List;

public class RemoteResource {
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
	
}
