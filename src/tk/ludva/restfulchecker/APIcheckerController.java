package tk.ludva.restfulchecker;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class APIcheckerController {
	
	@RequestMapping(value="/", method=RequestMethod.GET)
	public String showCheckAPI(RemoteResource remoteResource){
		return "checkapi";
	}
	
	@RequestMapping(value="/", method=RequestMethod.POST)
	public String doCheckAPI(RemoteResource remoteResource){
		return "checkapi";
	}

	@RequestMapping(value="old", method=RequestMethod.POST)
	@ResponseBody
	public String checkAPI(@RequestParam String apiEntryURL) throws APINotAccesibleException {
		StringBuilder response = new StringBuilder();
		response.append("<!DOCTYPE html PUBLIC \"-//W3C//DTD XHTML 1.0 Strict//EN\"\n     \"http://www.w3.org/TR/xhtml1/DTD/xhtml1-strict.dtd\">\n<html xmlns=\"http://www.w3.org/1999/xhtml\">\n  <head>\n    <meta http-equiv=\"content-type\" content=\"text/html; charset=utf-8\" />\n    <title>RESTfulChecker</title>\n  </head>\n  <body>");
		response.append("Vstupni bod API je: ").append(apiEntryURL).append("<br>");
		URL remoteUrl;
		try {
			remoteUrl = new URL(apiEntryURL);
		} catch (MalformedURLException e) {
			throw new APINotAccesibleException("MalformedURLException: "+apiEntryURL);
		}
		URLConnection remoteApiConnection;
		try {
			remoteApiConnection = remoteUrl.openConnection();
		} catch (IOException e) {
			throw new APINotAccesibleException("IOException: "+apiEntryURL);
		}
		Map<String, List<String>> remoteHeaderFields = remoteApiConnection.getHeaderFields();
		Set<String> remoteHeadersKeys = remoteHeaderFields.keySet();
		response.append("<table border=\"1\">");
		for (Iterator<String> remoteHeadersKeysIterator = remoteHeadersKeys.iterator(); 
				remoteHeadersKeysIterator.hasNext();) {
			response.append("<tr>");
			String headerKey = (String) remoteHeadersKeysIterator.next();
			response.append("<th>"+headerKey+" : </th>");
			List<String> remoteHeaderValues = remoteHeaderFields.get(headerKey);
			for (Iterator<String> remoteHeaderValuesIterator = remoteHeaderValues.iterator(); 
					remoteHeaderValuesIterator.hasNext();) {
				String remoteHeaderValue = (String) remoteHeaderValuesIterator.next();
				response.append("<td>"+remoteHeaderValue+"</td>");
			}
			response.append("</tr>\n");
		}
		response.append("</table>");
		response.append("\n");
		try {
			StringBuilder soubor = new StringBuilder();
			String radek;
			BufferedReader reader = new BufferedReader(new InputStreamReader(remoteUrl.openStream()));
			while ((radek = reader.readLine()) != null) {
				soubor.append(radek);
			}
			response.append("<xmp>"+soubor+"</xmp>");
		} catch (IOException e) {
			throw new APINotAccesibleException("IOException: "+apiEntryURL);
		}
		response.append("</body></html>");
		return response.toString();
	}
}
