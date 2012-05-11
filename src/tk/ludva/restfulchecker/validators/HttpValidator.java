package tk.ludva.restfulchecker.validators;

import tk.ludva.restfulchecker.ApiEntry;

/**
 * Class for working with HTTP messages.
 * @author Lu2
 * 
 */
public class HttpValidator
{
	/**
	 * Determine if the HTTP response in apiEntry was successful.
	 * @param apiEntry with response.
	 * @return True if successful.
	 */
	public static boolean responseOk(ApiEntry apiEntry)
	{
		if (apiEntry.getResponseCode() >= 200 && apiEntry.getResponseCode() <= 300)
		{
			return true;
		}
		else
		{
			return false;
		}
	}
	
	public static String toSafeId(String input)
	{
		return input.replace('/', '_').replace('?', '_').replace('=', '_').replace('&', '_').replace(';', '_');
	}
}
