package tk.ludva.restfulchecker;

public class ApiEntry extends RemoteResource {
	private int showLevel = 0;
	private String message;
	private ResourceNode resourceNodes;
	private int maxSiblings;
	private int maxDescendats;
	private String baseUrl;
	private int maxSize;
	
	public ApiEntry() {
		super();
		setMethod("GET");
		maxSiblings = 10;
		maxDescendats = 3;
		maxSize = 128;
	}

	public int getShowLevel() {
		return showLevel;
	}

	public void setShowLevel(int showLevel) {
		this.showLevel = showLevel;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}
	
	public ResourceNode getResourceNodes() {
		return resourceNodes;
	}

	public void setResourceNodes(ResourceNode resourceNodes) {
		this.resourceNodes = resourceNodes;
	}

	public int getMaxSiblings() {
		return maxSiblings;
	}

	public void setMaxSiblings(int maxSiblings) {
		this.maxSiblings = maxSiblings;
	}

	public int getMaxDescendats() {
		return maxDescendats;
	}

	public void setMaxDescendats(int maxDescendats) {
		this.maxDescendats = maxDescendats;
	}

	public String getBaseUrl() {
		return baseUrl;
	}

	public void setBaseUrl(String baseUrl) {
		this.baseUrl = baseUrl;
	}

	public int getMaxSize() {
		return maxSize;
	}

	public void setMaxSize(int maxSize) {
		this.maxSize = maxSize;
	}

	
}
