package tk.ludva.restfulchecker;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/*
 * This class is modification of http://www.mkyong.com/regular-expressions/how-to-extract-html-links-with-regular-expression/
 */
public class LinkExtrator {

	private static final String HTML_A_HREF_TAG_PATTERN = "\\s*(?i)href\\s*=\\s*(\"([^\"]*\")|'[^']*'|([^'\">\\s]+))";

	private Pattern patternTag, patternLink;
	private Matcher matcherTag, matcherLink;

	public LinkExtrator() {
		patternLink = Pattern.compile(HTML_A_HREF_TAG_PATTERN);
	}

	/**
	 * Validate html with regular expression
	 * 
	 * @param html
	 *            html content for validation
	 * @return Vector links and link text
	 */
	public String grabHTMLLinks(final String html) {
		StringBuilder vysledneLinky = new StringBuilder();
		matcherLink = patternLink.matcher(html);

		while (matcherLink.find()) {
			String link = matcherLink.group(1); // link
			vysledneLinky.append(link);
			vysledneLinky.append(" ");
		}
		return vysledneLinky.toString();
	}
}
