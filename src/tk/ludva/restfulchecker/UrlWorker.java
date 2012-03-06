package tk.ludva.restfulchecker;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.HashSet;
import java.util.Set;

public class UrlWorker {

	public static Set<String> getUrls(String currentUrlString, Set<String> odkazy) {
		URL currentUrl;
		Set<String> urls = new HashSet<String>();
		try {
			currentUrl = new URL(currentUrlString);
		} catch (MalformedURLException e) {
			e.printStackTrace();
			return urls;
		}
		for (String odkaz : odkazy) {
			odkaz = chop(odkaz);
			if (odkaz.startsWith("http")) {
				urls.add(odkaz);
				}
			else if (odkaz.startsWith("/")) {
				if (currentUrl.getHost().endsWith("/")) {
					odkaz = odkaz.substring(1);
				}
				urls.add(currentUrl.getProtocol()+"://"+currentUrl.getHost()+odkaz);
			}
			else if (odkaz.startsWith("./")) odkaz = odkaz.substring(2);
				String vrat = currentUrl.getProtocol()+"://"+currentUrl.getHost()+currentUrl.getPath();
				vrat = vrat.substring(0, vrat.lastIndexOf('/')+1);
				vrat = vrat+odkaz;
				urls.add(vrat);
			}
		return urls;
	}

	private static String chop(String odkaz) {
		if (odkaz.startsWith("\"") && odkaz.endsWith("\"")) {
			return odkaz.substring(1, odkaz.length()-1);
		}
		return odkaz;
	}
}
