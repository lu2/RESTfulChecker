package tk.ludva.restfulchecker;

public class Header {
	
	private String headerKey;
	private String headerValue;
	
	public Header() {
	}

	public Header(String headerKey, String headerValue) {
		super();
		this.headerKey = headerKey;
		this.headerValue = headerValue;
	}

	public String getHeaderKey() {
		return headerKey;
	}

	public void setHeaderKey(String headerKey) {
		this.headerKey = headerKey;
	}

	public String getHeaderValue() {
		return headerValue;
	}

	public void setHeaderValue(String headerValue) {
		this.headerValue = headerValue;
	}
	
	
}
