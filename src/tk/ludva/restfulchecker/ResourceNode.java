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

	@Override
	public String toString() {
		return "<li>" + currentResource.getUrl()
				+ "<ul>" + descendants + "</ul></li>";
	}
	
	
	
	
	
}
