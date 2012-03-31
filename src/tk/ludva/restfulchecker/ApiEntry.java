package tk.ludva.restfulchecker;

public class ApiEntry extends RemoteResource {
	private int showLevel = 0;
	private String message;
	private ResourceNode resourceNodes;
	
	public ApiEntry() {
		super();
		setMethod("GET");
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
	
}
