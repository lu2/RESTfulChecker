package tk.ludva.restfulchecker;

import java.util.HashSet;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/*
 * This class is modification of http://www.mkyong.com/regular-expressions/how-to-extract-html-links-with-regular-expression/
 */
public class LinkExtrator {

	private static final String HTML_A_HREF_TAG_PATTERN = "\\s*(?i)href\\s*=\\s*(\"([^\"]*\")|'[^']*'|([^'\">\\s]+))";

	private Pattern patternLink;
	private Matcher matcherLink;

	public LinkExtrator() {
		patternLink = Pattern.compile(HTML_A_HREF_TAG_PATTERN);
	}

	public Set<String> grabHTMLLinks(final String html) {
		matcherLink = patternLink.matcher(html);
		Set<String> linksOut = new HashSet<String>();
		while (matcherLink.find()) {
			String link = matcherLink.group(1); // link
			linksOut.add(link);
		}
		return linksOut;
	}
}
