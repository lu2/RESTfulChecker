package tk.ludva.restfulchecker;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/*
 * This class is modification of http://www.mkyong.com/regular-expressions/how-to-extract-html-links-with-regular-expression/
 */
public class LinkExtrator {

	private static final String HTML_A_HREF_TAG_PATTERN = "\\s*(?i)href\\s*=\\s*(\"([^\"]*\")|'[^']*'|([^'\">\\s]+))";
	private static final String HTTP_PATTERN = "http://[[\\S]&&[^\"]&&[^\']&&[^<]&&[^>]]+";
	private static final String HTTPS_PATTERN = "https://[[\\S]&&[^\"]&&[^\']&&[^<]&&[^>]]+";

	private Pattern patternLink;
	private Pattern patternLinkHttp;
	private Pattern patternLinkHttps;
	private Matcher matcherLink;

	public LinkExtrator() {
		patternLink = Pattern.compile(HTML_A_HREF_TAG_PATTERN);
		patternLinkHttp = Pattern.compile(HTTP_PATTERN);
		patternLinkHttps = Pattern.compile(HTTPS_PATTERN);
	}

	public List<String> grabHTMLLinks(final String html) {
		matcherLink = patternLink.matcher(html);
		List<String> linksOut = new ArrayList<String>();
		Set<String> alreadyFoundedUrls = new HashSet<String>();
		while (matcherLink.find()) {
			String link = matcherLink.group(1); // link
			if (link != null)
			{
			if (link.startsWith("'") && link.endsWith("'"))
			{
				if (!alreadyFoundedUrls.contains(link.substring(1, link.length()-1)))
				{
					linksOut.add(link.substring(1, link.length()-1));
					alreadyFoundedUrls.add(link.substring(1, link.length()-1));
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
		while (matcherLink.find()) {
			String link = matcherLink.group(); // link
			if (!alreadyFoundedUrls.contains(link))
			{
				linksOut.add(link);
				alreadyFoundedUrls.add(link);
			}
		}
		
		matcherLink = patternLinkHttps.matcher(html);
		while (matcherLink.find()) {
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
