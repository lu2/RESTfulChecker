package tk.ludva.restfulchecker;

/**
 * Class for holding data about API entry point as well as for whole API.
 * 
 * @author Lu2
 * 
 */
public class ApiEntry extends RemoteResource
{
	/**
	 * Used for showing different elements in some JSPs
	 */
	private int showLevel = 0;
	
	/**
	 * Used to store messages between some JSPs and backend classes.
	 */
	private String message;
	
	/**
	 * Entrypoint's resource node.
	 */
	private ResourceNode resourceNodes;
	
	/**
	 * Limits max number of children for one ressource.
	 */
	private int maxSiblings;
	
	/**
	 * URL for limiting the scope of API.
	 */
	private String baseUrl;
	
	/**
	 * 
	 */
	private int valid;
	
	/**
	 * Questionnaire for this API.
	 */
	private Questionnaires questionnaires;

	public ApiEntry()
	{
		super();
		setMethod("GET");
		maxSiblings = 10;
		valid = 0;
		questionnaires = new Questionnaires();
	}

	/**
	 * Gets the value of showLevel.
	 * @return showLevel level number.
	 */
	public int getShowLevel()
	{
		return showLevel;
	}

	/**
	 * Sets the value of showLevel.
	 * @param showLevel desired showLevel value.
	 */
	public void setShowLevel(int showLevel)
	{
		this.showLevel = showLevel;
	}

	/**
	 * Gets message.
	 * @return message.
	 */
	public String getMessage()
	{
		return message;
	}

	/**
	 * Sets message.
	 * @param message desired message.
	 */
	public void setMessage(String message)
	{
		this.message = message;
	}

	/**
	 * Gets ResourceNode.
	 * @return ResourceNode.
	 */
	public ResourceNode getResourceNodes()
	{
		return resourceNodes;
	}

	/**
	 * Sets ResourceNode.
	 * @param resourceNodes desired ResourceNode.
	 */
	public void setResourceNodes(ResourceNode resourceNodes)
	{
		this.resourceNodes = resourceNodes;
	}

	/**
	 * Gets maximum number of children.
	 * @return maxSiblings.
	 */
	public int getMaxSiblings()
	{
		return maxSiblings;
	}

	/**
	 * Sets maximum number of children (minimum 0).
	 * @param maxSiblings desired maxSiblings.
	 */
	public void setMaxSiblings(int maxSiblings)
	{
		if (maxSiblings < 0)
			this.maxSiblings = 0;
		else
			this.maxSiblings = maxSiblings;
	}

	/**
	 * Gets baseUrl.
	 * @return baseUrl.
	 */
	public String getBaseUrl()
	{
		return baseUrl;
	}

	/**
	 * Sets baseUrl.
	 * @param baseUrl desired baseUrl.
	 */
	public void setBaseUrl(String baseUrl)
	{
		this.baseUrl = baseUrl;
	}

	/**
	 * Gets valid level.
	 * @return valid.
	 */
	public int getValid()
	{
		return valid;
	}

	/**
	 * Sets valid level.
	 * @param valid desired valid level.
	 */
	public void setValid(int valid)
	{
		this.valid = valid;
	}

	/**
	 * Gets questionnaire for this API.
	 * @return questionnaires.
	 */
	public Questionnaires getQuestionnaires()
	{
		return questionnaires;
	}

}
