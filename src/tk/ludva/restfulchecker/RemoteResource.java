package tk.ludva.restfulchecker;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.logging.Logger;

/**
 * Class for holding information (HTTP request/response) about a resource.
 * @author Lu2
 *
 */
public class RemoteResource implements Cloneable
{
	/**
	 * Logger for this class.
	 */
	private static final Logger log = Logger.getLogger(RemoteResource.class.getName());
	
	/**
	 * URL.
	 */
	private String url = "http://";
	
	/**
	 * HTTP METHOD.
	 */
	private String method;
	
	/**
	 * List of HTTP Headers for request.
	 */
	private List<Header> requestHeaders = new ArrayList<Header>();
	
	/**
	 * List of HTTP Headers from response.
	 */
	private List<Header> responseHeaders = new ArrayList<Header>();
	
	/**
	 * HTTP body for request.
	 */
	private String requestBody;
	
	/**
	 * Enables/disables sending of HTTP body in request.
	 */
	private boolean useRequestBody;
	
	/**
	 * HTTP body from response.
	 */
	private String responseBody;
	
	/**
	 * HTTP response code from response.
	 */
	private int responseCode;
	
	/**
	 * HTTP response message from response.
	 */
	private String responseMessage;

	public RemoteResource()
	{
		// requestHeaders.add(new Header("Accept", "*/*", true));
	}

	/**
	 * Sends HTTP request defined in this class and stores it into it. 
	 * Deletes previous response held by this class (if any).
	 */
	public void sendRequest()
	{
		deletePreviousResponse();
		HttpURLConnection conn = null;
		try
		{
			URL remoteUrl = new URL(getUrl());
			conn = (HttpURLConnection) remoteUrl.openConnection();
			conn.setRequestMethod(getMethod());
			for (Iterator<Header> iterator = getRequestHeaders().iterator(); iterator.hasNext();)
			{
				Header header = (Header) iterator.next();
				if (header.isInUse())
					conn.setRequestProperty(header.getHeaderKey(), header.getHeaderValue());
			}
			if (isUseRequestBody())
			{
				conn.setDoOutput(true);
				DataOutputStream data = new DataOutputStream(conn.getOutputStream());
				data.write(getRequestBody().getBytes());
				data.flush();
				data.close();
			}
			setResponseCode(conn.getResponseCode());
			setResponseMessage(conn.getResponseMessage());
			for (int n = 0; n < conn.getHeaderFields().size(); n++)
			{
				getResponseHeaders().add(new Header(conn.getHeaderFieldKey(n), conn.getHeaderField(n), true));
			}
			StringBuilder responseBody = new StringBuilder();
			String radek;
			BufferedReader reader = new BufferedReader(new InputStreamReader(conn.getInputStream()));
			while ((radek = reader.readLine()) != null)
			{
				responseBody.append(radek);
				int size = responseBody.length();
				if (size > 256000)
				{
					// The resource is too big, cancel transmit and do not store.
					responseBody = new StringBuilder("Content too big, ommiting this resource.");
					break;
				}
			}
			setResponseBody(responseBody.toString());
		}
		catch (MalformedURLException e)
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
			log.severe(e.getMessage());
		}
		catch (IOException e)
		{
			System.out.println("url " + conn.getURL() + " doesn't exists!");
		}
		finally
		{
			if (conn != null)
				conn.disconnect();
		}
	}

	/**
	 * Deletes HTTP response's data from this class.
	 */
	public void deletePreviousResponse()
	{
		responseHeaders = new ArrayList<Header>();
		responseBody = null;
		responseCode = 0;
		responseMessage = null;
	}

	/**
	 * Gets URL of this resource.
	 * @return URL as String.
	 */
	public String getUrl()
	{
		return url;
	}

	/**
	 * Sets URL for this resource.
	 * @param url String representation of URL.
	 */
	public void setUrl(String url)
	{
		this.url = url;
	}

	/**
	 * Gets HTTP METHOD.
	 * @return method.
	 */
	public String getMethod()
	{
		return method;
	}

	/**
	 * Sets HTTP METHOD.
	 * @param method String representation of HTTP METHOD.
	 */
	public void setMethod(String method)
	{
		this.method = method;
	}

	/**
	 * Gets HTTP Headers for request.
	 * @return List of Headers.
	 */
	public List<Header> getRequestHeaders()
	{
		return requestHeaders;
	}

	/**
	 * Sets HTTP Headers for request.
	 * @param requestHeaders List of Headers.
	 */
	public void setRequestHeaders(List<Header> requestHeaders)
	{
		this.requestHeaders = requestHeaders;
	}

	/**
	 * Gets HTTP Headers from response.
	 * @return List of Headers.
	 */
	public List<Header> getResponseHeaders()
	{
		return responseHeaders;
	}

	/**
	 * Sets HTTP Headers for response.
	 * @param responseHeaders List of Headers.
	 */
	public void setResponseHeaders(List<Header> responseHeaders)
	{
		this.responseHeaders = responseHeaders;
	}

	/**
	 * Gets HTTP body for request.
	 * @return requestBody as String.
	 */
	public String getRequestBody()
	{
		return requestBody;
	}

	/**
	 * Sets HTTP body for request.
	 * @param requestBody String to be set.
	 */
	public void setRequestBody(String requestBody)
	{
		this.requestBody = requestBody;
	}

	/**
	 * Gets HTTP body for response.
	 * @return body as String.
	 */
	public String getResponseBody()
	{
		return responseBody;
	}

	/**
	 * Sets HTTP body for response.
	 * @param responseBody String to be set.
	 */
	public void setResponseBody(String responseBody)
	{
		this.responseBody = responseBody;
	}

	/**
	 * Gets HTTP response code from response.
	 * @return response code.
	 */
	public int getResponseCode()
	{
		return responseCode;
	}

	/**
	 * Sets HTTP response code for response.
	 * @param i code to be set.
	 */
	public void setResponseCode(int i)
	{
		this.responseCode = i;
	}

	/**
	 * Gets HTTP response message from response.
	 * @return Response as String.
	 */
	public String getResponseMessage()
	{
		return responseMessage;
	}

	/**
	 * Sets HTTP response message.
	 * @param responseMessage.
	 */
	public void setResponseMessage(String responseMessage)
	{
		this.responseMessage = responseMessage;
	}

	/**
	 * Gets information if body is used in Request.
	 * @return true if is used.
	 */
	public boolean isUseRequestBody()
	{
		return useRequestBody;
	}

	/**
	 * Sets information if body should be used in Request.
	 * @param useRequestBody true if should be used.
	 */
	public void setUseRequestBody(boolean useRequestBody)
	{
		this.useRequestBody = useRequestBody;
	}

	/**
	 * Clone this object.
	 */
	protected Object clone() throws CloneNotSupportedException
	{
		return super.clone();
	}

}
