package tk.ludva.restfulchecker.validators;

import tk.ludva.restfulchecker.Header;
import tk.ludva.restfulchecker.ResourceNode;

public class RestValidator {
	
	private ResourceNode parrentResourceNode;
	private boolean isValid = true;
	
	public RestValidator(ResourceNode parrentResourceNode) {
		this.parrentResourceNode = parrentResourceNode;
	}
	
	public boolean validateApi() {
		validateResource(parrentResourceNode);
		return isValid;
	}
	
	private void validateResource(ResourceNode resourceNode) {
		validateCacheConstraint(resourceNode);
		validateUniformInterface(resourceNode);
		for (ResourceNode nextResourceNode : resourceNode.getDescendants()) {
			validateResource(nextResourceNode);
		}
	}
	
	private void  validateCacheConstraint(ResourceNode resourceNode) {
		//TODO proper cache headers checkinkg
		for (Header header : resourceNode.getCurrentResource().getResponseHeaders()) {
			if (header.getHeaderKey() != null)
			if (header.getHeaderKey().equals("Cache-Control") 
					| header.getHeaderKey().equals("Expires")
					| header.getHeaderKey().equals("Last-Modified")) {
				resourceNode.addNonViolationMessages("Cache constraint", "ok");
				return;
			}
		}
		isValid = false;
		resourceNode.addViolationMessage("Cache constraint violation", "No information about caching possibility has been found in headers (none of Cache-Control, Expires, Last-Modified header declared).");
	}
	
	private void validateUniformInterface(ResourceNode resourceNode) {
		if (resourceNode.getCurrentResourceOptions() != null) 
			for (Header header : resourceNode.getCurrentResourceOptions().getResponseHeaders()) {
				if (header.getHeaderKey() != null)
					if (header.getHeaderKey().equals("Allow")) return;
		}
		isValid = false;
		resourceNode.addViolationMessage("Uniform Interface constraint violation", "OPTIONS method doesn't giving acceptable verbs list.");
	}
}
