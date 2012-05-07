package tk.ludva.restfulchecker;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Class used to find URLs in text.
 * @author Lu2
 *
 */
public class LinkExtrator
{

	/**
	 * RegEx pattern for href="..."
	 */
	private static final String HTML_A_HREF_TAG_PATTERN = "\\s*(?i)href\\s*=\\s*(\"([^\"]*\")|'[^']*'|([^'\">\\s]+))";
	
	/**
	 * RegEx pattern for HTTP://...
	 */
	private static final String HTTP_PATTERN = "http://[[\\S]&&[^\"]&&[^\']&&[^<]&&[^>]]+";
	
	/**
	 * RegEx pattern for HTTPS://
	 */
	private static final String HTTPS_PATTERN = "https://[[\\S]&&[^\"]&&[^\']&&[^<]&&[^>]]+";

	private Pattern patternLink;
	private Pattern patternLinkHttp;
	private Pattern patternLinkHttps;
	private Matcher matcherLink;

	public LinkExtrator()
	{
		patternLink = Pattern.compile(HTML_A_HREF_TAG_PATTERN);
		patternLinkHttp = Pattern.compile(HTTP_PATTERN);
		patternLinkHttps = Pattern.compile(HTTPS_PATTERN);
	}

	/**
	 * Finds urls in String input.
	 * @param html String in which urls will be searched.
	 * @return List of founded URLs
	 */
	public List<String> grabHTMLLinks(final String html)
	{
		matcherLink = patternLink.matcher(html);
		List<String> linksOut = new ArrayList<String>();
		Set<String> alreadyFoundedUrls = new HashSet<String>();
		while (matcherLink.find())
		{
			String link = matcherLink.group(1); // link
			if (link != null)
			{
				if (link.startsWith("'") && link.endsWith("'"))
				{
					if (!alreadyFoundedUrls.contains(link.substring(1, link.length() - 1)))
					{
						linksOut.add(link.substring(1, link.length() - 1));
						alreadyFoundedUrls.add(link.substring(1, link.length() - 1));
					}
				}
				else
				{
					if (!alreadyFoundedUrls.contains(link))
					{
						linksOut.add(link);
						alreadyFoundedUrls.add(link);
					}
				}
			}
		}

		matcherLink = patternLinkHttp.matcher(html);
		while (matcherLink.find())
		{
			String link = matcherLink.group(); // link
			if (!alreadyFoundedUrls.contains(link))
			{
				linksOut.add(link);
				alreadyFoundedUrls.add(link);
			}
		}

		matcherLink = patternLinkHttps.matcher(html);
		while (matcherLink.find())
		{
			String link = matcherLink.group(); // link
			if (!alreadyFoundedUrls.contains(link))
			{
				linksOut.add(link);
				alreadyFoundedUrls.add(link);
			}
		}
		return linksOut;
	}
}
