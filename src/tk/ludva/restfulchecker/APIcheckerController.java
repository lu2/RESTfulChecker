package tk.ludva.restfulchecker;

import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.Map;
import java.util.Queue;
import java.util.Set;
import java.util.logging.Logger;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import tk.ludva.restfulchecker.validators.HttpValidator;
import tk.ludva.restfulchecker.validators.RestValidator;

@Controller
public class APIcheckerController {
	
	private static final String JSPBadResponse = "checkapi_bad";
	private static final String JSPOkResponse = "checkapi_ok";
	
	private static final Logger log = Logger.getLogger(APIcheckerController.class.getName());
	
	@RequestMapping(value="/", method=RequestMethod.GET)
	public String showCheckAPI(ApiEntry remoteResource){
		return "checkapi";
	}
	
	@RequestMapping(value="/", method=RequestMethod.POST)
	public String doCheckAPI(ApiEntry apiEntry)
	{
		createTree(apiEntry);
		if (HttpValidator.responseOk(apiEntry) && apiEntry.getResourceNodes().getDescendants().size() > 0)
		{
			validateTree(apiEntry);
			generateViewOfTree(apiEntry);
			return JSPOkResponse;
		}
		else
		{
			return JSPBadResponse;
		}
	}

	private void generateViewOfTree(ApiEntry apiEntry)
	{
		StringBuilder sb = new StringBuilder(apiEntry.getMessage());
		sb.append("This API structure was found: ");
		sb.append("<ul>\n");
		int index = 0;
		writeResourceNodeView(apiEntry.getResourceNodes(), apiEntry.getBaseUrl(), apiEntry.getMaxSiblings(), index++, sb);
		
		sb.append("</ul>\n");
		
		apiEntry.setMessage(sb.toString());
	}

	private void writeResourceNodeView(ResourceNode resourceNode, String baseUrl, int maxSiblings, int index, StringBuilder sb)
	{
		String temp;
		sb.append("<li>");
		temp = resourceNode.getCurrentResource().getUrl().replaceFirst(baseUrl, "");
		if (temp.length() == 0)
		{
			temp = "/";
		}
		sb.append("<a href=\"#\" onclick=\"toggleVisibility(document.getElementById(\'http"+index+"\')); return false\" >");
		sb.append(temp);
		sb.append("</a>");

		if (resourceNode.getDescendants().size() > 0)
		{
			sb.append("<ul>\n");
			try 
			{
			for (ResourceNode rs : resourceNode.getDescendants().subList(0, maxSiblings))
			{
				writeResourceNodeView(rs, baseUrl, maxSiblings, index++, sb);
			}
			int overSize = resourceNode.getDescendants().size()-maxSiblings;
			if (overSize > 0)
			{
				sb.append("<li>...and "+overSize+" more.</li>");
			}
			}
			catch(java.lang.IndexOutOfBoundsException e)
			{
				for (ResourceNode rs : resourceNode.getDescendants())
				{
					writeResourceNodeView(rs, baseUrl, maxSiblings, index++, sb);
				}
			}
			sb.append("</ul>\n");
		}
		
		sb.append("</li>");
		sb.append("\n");
	}
	
	private void generateViewOfTreeDeprecated(ApiEntry apiEntry)
	{
		String validation = apiEntry.getMessage();
		String message = validation + "the tree of API is: <ul>"+apiEntry.getResourceNodes().toString()+"</ul>";
		message = message.replace("<ul>[<li>", "<ul><li>");
		message = message.replace("<ul>null</ul>", "");
		message = message.replace("<ul>[]</ul>", "");
		message = message.replace("</li>, <li>", "</li><li>");
		message = message.replace("</li>]</ul>", "</li></ul>");
		message = message.replace("</li>]<li>", "</li><li>");
		apiEntry.setMessage(message);
	}

	private void validateTree(ApiEntry apiEntry) {
		RestValidator restValidator = new RestValidator(apiEntry.getResourceNodes());
		String validation;
		if (restValidator.validateApi()) validation = "<h3>Your API is not RESTful.</h3>";
		else validation = "<h3>Your API is not RESTful - see red marks below to know why.</h3>";
		apiEntry.setMessage(validation);
	}

	private void createTree(ApiEntry apiEntry) 
	{
		Map<String, ResourceNode> discoveredResources = new HashMap<String, ResourceNode>();
		Queue<ResourceNode> toVisit = new LinkedList<ResourceNode>();
		ResourceNode currentResourceNode = new ResourceNode((RemoteResource)apiEntry);
		apiEntry.setResourceNodes(currentResourceNode);
		while (currentResourceNode != null) 
		{
			doCrawle(currentResourceNode, apiEntry, discoveredResources, toVisit);
			currentResourceNode = toVisit.poll();
		}
	}

	private void doCrawle(ResourceNode currentResourceNode, ApiEntry apiEntry,
			Map<String, ResourceNode> discoveredResources,
			Queue<ResourceNode> toVisit)
	{
		if (discoveredResources.containsKey(currentResourceNode.getCurrentResource().getUrl()))
		{
			return;
		}
		currentResourceNode.sendOptions();
		currentResourceNode.sendRequest();
		LinkExtrator links = new LinkExtrator();
		if (currentResourceNode.getCurrentResource().getResponseBody() == null)
		{
			return;
		}
		Set<String> urls = UrlWorker.getUrls(currentResourceNode.getCurrentResource().getUrl(), 
				links.grabHTMLLinks(currentResourceNode.getCurrentResource().getResponseBody()));
		Set<String> validUrls = new HashSet<String>();
		for (String url : urls)
		{
			if (url.startsWith(apiEntry.getBaseUrl()))
			{
				validUrls.add(url);
			}
		}
		if (validUrls.size() > 0)
		{
			RemoteResource nextResource;
			int maxResourcesToLoad = apiEntry.getMaxSiblings();
			for (String url : validUrls)
			{
				try
				{
					nextResource = (RemoteResource) currentResourceNode.getCurrentResource().clone();
					if (currentResourceNode.getCurrentResource().getUrl().contains("?") && !url.contains("?"))
					{
						String append = currentResourceNode.getCurrentResource().getUrl();
						append = currentResourceNode.getCurrentResource().getUrl().substring(
								currentResourceNode.getCurrentResource().getUrl().lastIndexOf('?'));
						nextResource.setUrl(url+append);
					}
					else
					{
						nextResource.setUrl(url);
					}
					ResourceNode nextResourceNode = new ResourceNode(nextResource);
					currentResourceNode.getDescendants().add(nextResourceNode);
					if (--maxResourcesToLoad < 0)
					{
						//No more crawling for this resource's childs.
					} 
					else
					{
						toVisit.add(nextResourceNode);
					}
				} catch (CloneNotSupportedException e)
				{
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}
		discoveredResources.put(currentResourceNode.getCurrentResource()
				.getUrl(), currentResourceNode);
	}

	@RequestMapping(value="/client.html", method=RequestMethod.GET)
	public String showClient(RemoteResource remoteResource){
		return "client";
	}
	
	@RequestMapping(value="/client.html", method=RequestMethod.POST)
	public String doClient(RemoteResource remoteResource){
		remoteResource.sendRequest();
		return "client";
	}

}
