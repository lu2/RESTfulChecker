package tk.ludva.restfulchecker.validators;

import tk.ludva.restfulchecker.ApiEntry;

public class HttpValidator 
{
	public static boolean responseOk(ApiEntry apiEntry) {
		if (apiEntry.getResponseCode() >= 200 && apiEntry.getResponseCode() <=300)
		{
			return true;
		}
		else
		{
			return false;
		}
	}
}
