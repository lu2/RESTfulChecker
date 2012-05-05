package tk.ludva.restfulchecker;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import tk.ludva.restfulchecker.model.ViolationMessagesHolder;

public class ResourceNode {
	private RemoteResource currentResource;
	private RemoteResource currentResourceOptions;
	private List<ResourceNode> descendants;
	private Map<String, ViolationMessagesHolder> violationMessages;
	private Map<String, ViolationMessagesHolder> nonViolationMessages;
	
	public ResourceNode() {
	}
	
	public ResourceNode(RemoteResource currentResource) {
		this.currentResource = currentResource;
	}

	public RemoteResource getCurrentResource() {
		return currentResource;
	}

	public void setCurrentResource(RemoteResource currentResource) {
		this.currentResource = currentResource;
	}

	public RemoteResource getCurrentResourceOptions() {
		return currentResourceOptions;
	}

	public void setCurrentResourceOptions(RemoteResource currentResourceOptions) {
		this.currentResourceOptions = currentResourceOptions;
	}

	public List<ResourceNode> getDescendants() {
		if (descendants == null) descendants = new ArrayList<ResourceNode>();
		return descendants;
	}

	public void setDescendants(List<ResourceNode> descendants) {
		this.descendants = descendants;
	}
	
	public Map<String, ViolationMessagesHolder> getViolationMessages() {
		if (violationMessages == null) violationMessages = new HashMap<String, ViolationMessagesHolder>();
		return violationMessages;
	}

	public void setViolationMessages(Map<String, ViolationMessagesHolder> violationMessages) {
		this.violationMessages = violationMessages;
	}
	
	public void addViolationMessage(String key, String message) {
		if (getViolationMessages().containsKey(key)) {
			getViolationMessages().get(key).addMessage(message);
		} else {
			getViolationMessages().put(key, new ViolationMessagesHolder(key, message));
		}
	}
	
	public Map<String, ViolationMessagesHolder> getNonViolationMessages() {
		if (nonViolationMessages == null) nonViolationMessages = new HashMap<String, ViolationMessagesHolder>();
		return nonViolationMessages;
	}

	public void setNonViolationMessages(Map<String, ViolationMessagesHolder> nonViolationMessages) {
		this.nonViolationMessages = nonViolationMessages;
	}
	
	public void addNonViolationMessages(String key, String message) {
		if (getNonViolationMessages().containsKey(key)) {
			getNonViolationMessages().get(key).addMessage(message);
		} else {
			getNonViolationMessages().put(key, new ViolationMessagesHolder(key, message));
		}
	}

	public String toStringResponse() {
		StringBuilder htmlOutput = new StringBuilder();
//		htmlOutput.append("<div>");
//		htmlOutput.append("<span class=\"violationMessage\">"+violationMessages.values().toString()+"</span>");
//		htmlOutput.append("<span class=\"nonViolationMessage\">"+nonViolationMessages.values().toString()+"</span>");
//		htmlOutput.append("</div>");
		htmlOutput.append("<div class=\"requestResponse\" id=\""+currentResource.getUrl()+"\">");
		htmlOutput.append("<p>"+currentResource.getResponseCode()+" "+currentResource.getResponseMessage()+"</p>");
		htmlOutput.append("<table class=\"responseHeaders\">");
		for (Header responseHeader : currentResource.getResponseHeaders()) {
			htmlOutput.append("<tr>");
			htmlOutput.append("<td>"+responseHeader.getHeaderKey()+"</td>");
			htmlOutput.append("<td>"+responseHeader.getHeaderValue()+"</td>");
			htmlOutput.append("</tr>");
		}
		htmlOutput.append("</table>");
		htmlOutput.append("<p><textarea>"+currentResource.getResponseBody()+"</textarea></p>");
		htmlOutput.append("</div>");
		if (currentResourceOptions == null) {
			return htmlOutput.toString();
		}
		htmlOutput.append("<div class=\"requestResponse\" id=\"o"+currentResourceOptions.getUrl()+"\">");
		htmlOutput.append("<p>"+currentResourceOptions.getResponseCode()+" "+currentResourceOptions.getResponseMessage()+"</p>");
		htmlOutput.append("<table class=\"responseHeaders\">");
		for (Header responseHeader : currentResourceOptions.getResponseHeaders()) {
			htmlOutput.append("<tr>");
			htmlOutput.append("<td>"+responseHeader.getHeaderKey()+"</td>");
			htmlOutput.append("<td>"+responseHeader.getHeaderValue()+"</td>");
			htmlOutput.append("</tr>");
		}
		htmlOutput.append("</table>");
		htmlOutput.append("<p><textarea>"+currentResourceOptions.getResponseBody()+"</textarea></p>");
		htmlOutput.append("</div>\n");
		return htmlOutput.toString();
	}
	
	public String toStringDetail() {
		return "<a href=\"#\" onclick=\"toggleVisibility(document.getElementById(\'"+currentResource.getUrl()+"\')); return false\" >#</a>" 
	+ "<a href=\"#\" onclick=\"toggleVisibility(document.getElementById(\'o"+currentResource.getUrl()+"\')); return false\" >o</a>";
	}

	@Override
	public String toString() {
		if (descendants == null) return "<li>" + toStringDetail() + " "  + currentResource.getUrl() + " " + toStringResponse() + " "
				+ "<ul>" + descendants + "</ul></li>";
		if (descendants.size() >= 10) {
			return "<li>" + toStringDetail() + " "  + currentResource.getUrl() + " " + toStringResponse() + " "
					+ "<ul>" + descendants.subList(0, 10) + "<li>and "+(descendants.size()-10)+" more, TODO dead ones.</li></ul></li>";
		}
		return "<li>" + toStringDetail() + " "  + currentResource.getUrl() + " " + toStringResponse() + " "
				+ "<ul>" + descendants + "</ul></li>";
	}

	public void sendOptions() {
		try {
			currentResourceOptions = (RemoteResource) currentResource.clone();
		} catch (CloneNotSupportedException e) {
			currentResourceOptions = null;
		}
		currentResourceOptions.setMethod("OPTIONS");
		currentResourceOptions.sendRequest();
	}

	public void sendRequest() {
		currentResource.sendRequest();
	}
	
	
	
	
	
}
