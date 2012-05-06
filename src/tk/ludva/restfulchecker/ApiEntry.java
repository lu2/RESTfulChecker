package tk.ludva.restfulchecker;

public class ApiEntry extends RemoteResource {
	private int showLevel = 0;
	private String message;
	private ResourceNode resourceNodes;
	private int maxSiblings;
	private String baseUrl;
	private int valid;
	private Questionnaires questionnaires;
	
	public ApiEntry() {
		super();
		setMethod("GET");
		maxSiblings = 10;
		valid = 0;
		questionnaires = new Questionnaires();
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

	public String getBaseUrl() {
		return baseUrl;
	}

	public void setBaseUrl(String baseUrl) {
		this.baseUrl = baseUrl;
	}

	public int getValid()
	{
		return valid;
	}

	public void setValid(int valid)
	{
		this.valid = valid;
	}

	public Questionnaires getQuestionnaires()
	{
		return questionnaires;
	}
	
}
