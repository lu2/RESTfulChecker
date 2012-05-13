package tk.ludva.restfulchecker.validators;

import java.util.List;

import tk.ludva.restfulchecker.Header;
import tk.ludva.restfulchecker.ResourceNode;

/**
 * Class for automatic validation of resources against constraints of REST.
 * @author Lu2
 * 
 */
public class RestValidator
{

	/**
	 * Resource to be validated.
	 */
	private ResourceNode parrentResourceNode;
	
	/**
	 * Outcome of validation.
	 */
	private boolean isValid = true;

	/**
	 * Initializes this validator and sets resource into it.
	 * @param parrentResourceNode Resource to be validated.
	 */
	public RestValidator(ResourceNode parrentResourceNode)
	{
		this.parrentResourceNode = parrentResourceNode;
	}

	/**
	 * Validates resource.
	 * @return true if the resource is valid.
	 */
	public boolean validateApi()
	{
		validateResource(parrentResourceNode);
		return isValid;
	}

	/**
	 * Starts validation of specified resource.
	 * @param resourceNode resource to be validated.
	 */
	private void validateResource(ResourceNode resourceNode)
	{
		// Do not test this resource if it wasn't crawled.
		if (resourceNode.getCurrentResource().getResponseCode() == 0)
		{
			return;
		}
		validateCacheConstraint(resourceNode);
		validateUniformInterface(resourceNode);
		for (ResourceNode nextResourceNode : resourceNode.getDescendants())
		{
			validateResource(nextResourceNode);
		}
	}

	/**
	 * Validates resource against cache constraint.
	 * @param resourceNode resource to be validated.
	 */
	private void validateCacheConstraint(ResourceNode resourceNode)
	{
		for (Header header : resourceNode.getCurrentResource().getResponseHeaders())
		{
			if (header.getHeaderKey() != null)
				if (header.getHeaderKey().equals("Cache-Control") | header.getHeaderKey().equals("Expires")
						| header.getHeaderKey().equals("Last-Modified") | header.getHeaderKey().equals("ETag"))
				{
					// resourceNode.addNonViolationMessages("Cache constraint",
					// "ok");
					return;
				}
		}
		isValid = false;
		resourceNode
				.addViolationMessage(
						"Cache constraint violation",
						"No information about response's cacheability (none of Cache-Control, Expires, Last-Modified header declared).");
	}

	/**
	 * Validates specified resource against uniform interface constraint.
	 * @param resourceNode to be validated.
	 */
	private void validateUniformInterface(ResourceNode resourceNode)
	{
		validateOptions(resourceNode);
		validateHeaders(resourceNode);
		// if (isValid)
		// resourceNode.addNonViolationMessages("Uniform Interface constraint",
		// "ok");
	}

	/**
	 * Validates HTTP headers against HTTP RFC.
	 * @param resourceNode
	 */
	private void validateHeaders(ResourceNode resourceNode)
	{
		switch (resourceNode.getCurrentResource().getResponseCode())
		{
		case 204:
			if (resourceNode.getCurrentResource().getResponseBody() != null)
			{
				isValid = false;
				resourceNode.addViolationMessage("Layered System constraint violation",
						"204 response must not include message-body");
			}
			break;
		case 205:
			if (containsEntity(resourceNode))
			{
				isValid = false;
				resourceNode.addViolationMessage("Uniform Interface constraint violation",
						"205 response must not include an entity");
			}
			break;
		case 206:
			if (!containsHeader("Content-Range", resourceNode.getCurrentResource().getResponseHeaders()))
			{
				isValid = false;
				resourceNode.addViolationMessage("Uniform Interface constraint violation",
						"206 response must contain Content-Range header.");
			}
			if (containsHeader("Content-Length", resourceNode.getCurrentResource().getResponseHeaders()))
			{
				if (!resourceNode.getCurrentResource().getResponseHeaders().contains(
						new Header("Content-Length", "" + resourceNode.getCurrentResource().getResponseBody().length(), false)))
				{
					isValid = false;
					resourceNode.addViolationMessage("Uniform Interface constraint violation",
							"Content-Length header must match the actual number of OCTETs transmitted in the message-body.");
				}
			}
			if (!containsHeader("Date", resourceNode.getCurrentResource().getResponseHeaders()))
			{
				isValid = false;
				resourceNode.addViolationMessage("Layered System constraint violation",
						"206 response must contain Date header.");
			}
			
			
			break;

		case 304:
			if (resourceNode.getCurrentResource().getResponseBody() != null)
			{
				isValid = false;
				resourceNode.addViolationMessage("Layered System constraint violation",
						"304 response must not include message-body");
			}
			if (!containsHeader("Date", resourceNode.getCurrentResource().getResponseHeaders()))
			{
				isValid = false;
				resourceNode.addViolationMessage("Layered System constraint violation",
						"304 response must contain Date header.");
			}
			
			break;

		case 401:
			if (!containsHeader("WWW-Authenticate", resourceNode.getCurrentResource().getResponseHeaders()))
			{
				isValid = false;
				resourceNode.addViolationMessage("Uniform Interface constraint violation",
						"401 response must contain WWW-Authenticate header.");
			}
			
			break;

		case 405:
			if (!containsHeader("Allow", resourceNode.getCurrentResource().getResponseHeaders()))
			{
				isValid = false;
				resourceNode.addViolationMessage("Uniform Interface constraint violation",
						"405 response must contain Allow header.");
			}
			
			break;

		case 416:
			if (containsHeader("Content-Type", resourceNode.getCurrentResource().getResponseHeaders()))
			{
				if (!resourceNode.getCurrentResource().getResponseHeaders().contains(
						new Header("Content-Type", "multipart/byteranges", false)))
				{
					isValid = false;
					resourceNode.addViolationMessage("Uniform Interface constraint violation",
							"response MUST NOT use the multipart/byteranges content-type.");
				}
			}
			
			break;
		}
	}

	/**
	 * Checks if the list of headers contains specified header key.
	 * @param headerKey which will be looked for.
	 * @param headers list of headers.
	 * @return true if contains false otherwise.
	 */
	private boolean containsHeader(String headerKey, List<Header> headers)
	{
		for (Header h : headers)
		{
			if (h.getHeaderKey() != null)
			{
				if (h.getHeaderKey().equals(headerKey)) return true;
			}
		}
		return false;
	}

	/**
	 * Checks if response contains HTTP entity.
	 * @param resourceNode response to be checked.
	 * @return true if contains entity false otherwise.
	 */
	private boolean containsEntity(ResourceNode resourceNode)
	{
		if (resourceNode.getCurrentResource().getResponseBody() != null) return true;
		boolean headerViolation = false;
		for (Header h : resourceNode.getCurrentResource().getResponseHeaders())
		{
			if (h.getHeaderKey().equals("Allow"))
			{
				headerViolation = true;
				break;
			}
			if (h.getHeaderKey().equals("Content-Encoding"))
			{
				headerViolation = true;
				break;
			}
			if (h.getHeaderKey().equals("Content-Language"))
			{
				headerViolation = true;
				break;
			}
			if (h.getHeaderKey().equals("Content-Length"))
			{
				headerViolation = true;
				break;
			}
			if (h.getHeaderKey().equals("Content-Location"))
			{
				headerViolation = true;
				break;
			}
			if (h.getHeaderKey().equals("Content-MD5"))
			{
				headerViolation = true;
				break;
			}
			if (h.getHeaderKey().equals("Content-Range"))
			{
				headerViolation = true;
				break;
			}
			if (h.getHeaderKey().equals("Content-Type"))
			{
				headerViolation = true;
				break;
			}
			if (h.getHeaderKey().equals("Expires"))
			{
				headerViolation = true;
				break;
			}
			if (h.getHeaderKey().equals("Last-Modified"))
			{
				headerViolation = true;
				break;
			}
			
		}
		return headerViolation;
	}

	/**
	 * Validates OPTIONS for specified resource.
	 * @param resourceNode to be validated.
	 */
	private void validateOptions(ResourceNode resourceNode)
	{
		if (resourceNode.getCurrentResourceOptions() != null)
			for (Header header : resourceNode.getCurrentResourceOptions().getResponseHeaders())
			{
				if (header.getHeaderKey() != null)
					if (header.getHeaderKey().equals("Allow"))
					{
						return;
					}
			}
		isValid = false;
		resourceNode.addNonViolationMessage("Uniform Interface constraint warning",
				"no Allow header in HTTP OPTIONS response.");
	}
}
