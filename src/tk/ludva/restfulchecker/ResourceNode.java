package tk.ludva.restfulchecker;

import java.util.ArrayList;
import java.util.List;

public class ResourceNode {
	private RemoteResource currentResource;
	private List<ResourceNode> descendants;
	
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

	public List<ResourceNode> getDescendants() {
		if (descendants == null) descendants = new ArrayList<ResourceNode>();
		return descendants;
	}

	public void setDescendants(List<ResourceNode> descendants) {
		this.descendants = descendants;
	}
	
	public String toStringResponse() {
		StringBuilder htmlOutput = new StringBuilder();
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
		return htmlOutput.toString();
	}
	
	public String toStringDetail() {
		return "<a href=\"#\" onclick=\"toggleVisibility(document.getElementById(\'"+currentResource.getUrl()+"\')); return false\" >#</a>";
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
	
	
	
	
	
}
