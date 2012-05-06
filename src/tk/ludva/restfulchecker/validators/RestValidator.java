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
		//Do not test this resource if it wasn't crawled.
		if (resourceNode.getCurrentResource().getResponseCode() == 0)
		{
			return;
		}
		validateCacheConstraint(resourceNode);
		validateUniformInterface(resourceNode);
		for (ResourceNode nextResourceNode : resourceNode.getDescendants()) {
			validateResource(nextResourceNode);
		}
	}
	
	private void  validateCacheConstraint(ResourceNode resourceNode) {
		for (Header header : resourceNode.getCurrentResource().getResponseHeaders()) {
			if (header.getHeaderKey() != null)
			if (header.getHeaderKey().equals("Cache-Control") 
					| header.getHeaderKey().equals("Expires")
					| header.getHeaderKey().equals("Last-Modified")) {
//				resourceNode.addNonViolationMessages("Cache constraint", "ok");
				return;
			}
		}
		isValid = false;
		resourceNode.addViolationMessage("Cache constraint violation", "No information about caching possibility has been found in headers (none of Cache-Control, Expires, Last-Modified header declared).");
	}
	
	private void validateUniformInterface(ResourceNode resourceNode) {
		validateOptions(resourceNode);
		validateHeaders(resourceNode);
//		if (isValid) resourceNode.addNonViolationMessages("Uniform Interface constraint", "ok");
	}

	private void validateHeaders(ResourceNode resourceNode) {
		switch (resourceNode.getCurrentResource().getResponseCode()) {
		case 204:
			if (resourceNode.getCurrentResource().getResponseBody() != null) {
				isValid = false;
				resourceNode.addViolationMessage("Uniform Interface constraint violation", "204 response must not include message-body");
			}
			break;
		case 205:
			//TODO handle properly
			if (resourceNode.getCurrentResource().getResponseBody() != null) {
				isValid = false;
				resourceNode.addViolationMessage("Uniform Interface constraint violation", "205 response must not include an entity");
			}
			break;
		case 206:
			//TODO handle properly
			
		case 304:
			//TODO handle properly
		
		case 401:
			//TODO handle properly
			
		case 405:
			//TODO handle properly
			
		case 416:
			//TODO handle properly
		}
	}

	/**
	 * @param resourceNode
	 */
	private void validateOptions(ResourceNode resourceNode) {
		if (resourceNode.getCurrentResourceOptions() != null) 
			for (Header header : resourceNode.getCurrentResourceOptions().getResponseHeaders()) {
				if (header.getHeaderKey() != null)
					if (header.getHeaderKey().equals("Allow")) {
						return;
					}
		}
		isValid = false;
		resourceNode.addNonViolationMessage("Uniform Interface constraint warning", "OPTIONS method doesn't giving acceptable verbs list.");
	}
}
