package tk.ludva.restfulchecker;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
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
	private static final int maxSize = 100;
	
	private static final Logger log = Logger.getLogger(APIcheckerController.class.getName());
	
	@RequestMapping(value="/", method=RequestMethod.GET)
	public String showCheckAPI(ApiEntry remoteResource){
		return "checkapi";
	}
	
	@RequestMapping(value="/", method=RequestMethod.POST)
	public String doCheckAPI(ApiEntry apiEntry)
	{
		createTree(apiEntry);
		if (HttpValidator.responseOk(apiEntry))
		{
			validateTree(apiEntry);
			return JSPOkResponse;
		}
		else
		{
			return JSPBadResponse;
		}
	}

	private void validateTree(ApiEntry apiEntry) {
		RestValidator restValidator = new RestValidator(apiEntry.getResourceNodes());
		String validation;
		if (restValidator.validateApi()) validation = "<h3>Your API is not RESTful.</h3>";
		else validation = "<h3>Your API is not RESTful - see red text below to know why.</h3>";
		String message = validation + "the tree of API is: <ul>"+apiEntry.getResourceNodes().toString()+"</ul>";
		message = message.replace("<ul>[<li>", "<ul><li>");
		message = message.replace("<ul>null</ul>", "");
		message = message.replace("<ul>[]</ul>", "");
		message = message.replace("</li>, <li>", "</li><li>");
		message = message.replace("</li>]</ul>", "</li></ul>");
		message = message.replace("</li>]<li>", "</li><li>");
		apiEntry.setMessage(message);
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
					nextResource.setUrl(url);
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
/*
	@RequestMapping(value="/deprecated", method=RequestMethod.POST)
	public String doCheckAPIDeprecated(ApiEntry apiEntry){
		//TODO Check visitedUrls and toVisit for multithreading!!!
		visitedUrls = new HashMap<String, ResourceNode>();
		toVisit = new LinkedList<ResourceNode>();
		apiEntry.sendRequest();
		switch (apiEntry.getResponseCode()) {
		case 401: 
			apiEntry.setMessage("Authorization required, add appropriate headers (todo: print chalenge header)");
			apiEntry.setShowLevel(1);
			break;
		case 200:
			LinkExtrator links = new LinkExtrator();
			Set<String> urls = UrlWorker.getUrls(apiEntry.getUrl(), links.grabHTMLLinks(apiEntry.getResponseBody()));
			if (urls.size() > 0) {
				ResourceNode currentResourceNode = new ResourceNode(apiEntry);
				toVisit.add(currentResourceNode);
				doCrawle();
				apiEntry.setResourceNodes(currentResourceNode);
				RestValidator restValidator = new RestValidator(currentResourceNode);
				String validation;
				if (restValidator.validateApi()) validation = "<h3>Your API is not RESTful.</h3>";
				else validation = "<h3>Your API is not RESTful - see red text below to know why.</h3>";
				String message = validation + "the tree of API is: <ul>"+currentResourceNode.toString()+"</ul>";
				message = message.replace("<ul>[<li>", "<ul><li>");
				message = message.replace("<ul>null</ul>", "");
				message = message.replace("<ul>[]</ul>", "");
				message = message.replace("</li>, <li>", "</li><li>");
				message = message.replace("</li>]</ul>", "</li></ul>");
				message = message.replace("</li>]<li>", "</li><li>");
				apiEntry.setMessage(message);
			} else {
				apiEntry.setMessage("No descendats found");
			}
			if (apiEntry.getRequestHeaders() != null) apiEntry.setShowLevel(1);
		}
		
		return "checkapi";
	}
	
	private void doCrawle() {
		ResourceNode currentResourceNode = toVisit.poll();
		while (currentResourceNode != null) {
			getResources(currentResourceNode);
			currentResourceNode = toVisit.poll();
		}
	}
	
	private void getResources(ResourceNode currentResourceNode) {
		if (visitedUrls.containsKey(currentResourceNode.getCurrentResource().getUrl())) {
			return;
		}
		try {
			RemoteResource currentResourceOptions = (RemoteResource) currentResourceNode.getCurrentResource().clone();
			currentResourceOptions.setMethod("OPTIONS");
			currentResourceOptions.sendRequest();
			currentResourceNode.setCurrentResourceOptions(currentResourceOptions);
		} catch (CloneNotSupportedException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		
		LinkExtrator links = new LinkExtrator();
		if (currentResourceNode.getCurrentResource() == null) return;
		if (currentResourceNode.getCurrentResource().getResponseBody() == null) return;
		Set<String> urls = UrlWorker.getUrls(currentResourceNode.getCurrentResource().getUrl(), links.grabHTMLLinks(currentResourceNode.getCurrentResource().getResponseBody()));
		if (urls.size() > 0) {
			RemoteResource nextResource;
			int maxResourcesToLoad = 10;
			for (String url : urls) {
					try {
						nextResource = (RemoteResource) currentResourceNode.getCurrentResource().clone();
						nextResource.setUrl(url);
						if(--maxResourcesToLoad < 0) {
							ResourceNode nextResourceNode = new ResourceNode(nextResource);
							currentResourceNode.getDescendants().add(nextResourceNode);
						} else {
						nextResource.sendRequest();
						if (nextResource.getResponseBody() == null) {
							//Response without body - nonsense to parse it
							// TODO probably error in remote api - log it somehow
							ResourceNode nextResourceNode = new ResourceNode(nextResource);
							currentResourceNode.getDescendants().add(nextResourceNode);
							toVisit.add(nextResourceNode);
						} else {
							ResourceNode nextResourceNode = new ResourceNode(nextResource);
							currentResourceNode.getDescendants().add(nextResourceNode);
							toVisit.add(nextResourceNode);
						}
						}
					} catch (CloneNotSupportedException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
				}
			}
		}
		visitedUrls.put(currentResourceNode.getCurrentResource().getUrl(), currentResourceNode);
	}
	*/
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
