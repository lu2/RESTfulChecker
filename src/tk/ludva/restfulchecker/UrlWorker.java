package tk.ludva.restfulchecker;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * Class responsible for creating full URLs from base URLs + paths.
 * @author Lu2
 *
 */
public class UrlWorker
{

	/**
	 * Creates full URL from base URL + path.
	 * @param currentUrlString base URL as String.
	 * @param link path as String.
	 * @return Full URL as String.
	 * @throws MalformedURLException if base URL is not valid URL.
	 */
	public static String constructUrl(String currentUrlString, String link) throws MalformedURLException
	{
		URL currentUrl = new URL(currentUrlString);
		return constructUrl(currentUrl, link);
	}

	/**
	 * Creates full URL from base URL + path.
	 * @param currentUrl base URL as URL
	 * @param link path as String.
	 * @return Full URL as String.
	 */
	public static String constructUrl(URL currentUrl, String link)
	{
		link = chop(link);
		if (link.startsWith("http"))
		{
			return link;
		}
		else if (link.startsWith("/"))
		{
			if (currentUrl.getHost().endsWith("/"))
			{
				link = link.substring(1);
			}
			return (currentUrl.getProtocol() + "://" + currentUrl.getHost() + link);
		}
		else if (link.startsWith("./"))
		{
			link = link.substring(2);
		}
		String buildedUrl = currentUrl.getProtocol() + "://" + currentUrl.getHost() + currentUrl.getPath();
		// buildedUrl = buildedUrl.substring(0, buildedUrl.lastIndexOf('/') + 1);
		if (buildedUrl.endsWith("/"))
		{

		}
		else
		{
			buildedUrl = buildedUrl + "/";
		}
		buildedUrl = buildedUrl + link;
		return buildedUrl;
	}

	/**
	 * Creates list of full URLs from base URL + list of paths.
	 * @param currentUrlString base URL as String.
	 * @param odkazy List of paths in String.
	 * @return List of full URLs.
	 */
	public static List<String> getUrls(String currentUrlString, List<String> odkazy)
	{
		URL currentUrl;
		List<String> urls = new ArrayList<String>();
		try
		{
			currentUrl = new URL(currentUrlString);
		}
		catch (MalformedURLException e)
		{
			e.printStackTrace();
			return urls;
		}
		for (String link : odkazy)
		{
			urls.add(constructUrl(currentUrl, link));
		}
		return urls;
	}

	/**
	 * Cut starting and trailing \ (if any) from link.
	 * @param link in String.
	 * @return chopped link in String.
	 */
	private static String chop(String link)
	{
		if (link.startsWith("\"") && link.endsWith("\""))
		{
			return link.substring(1, link.length() - 1);
		}
		return link;
	}
}
