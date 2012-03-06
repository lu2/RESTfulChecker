package tk.ludva.restfulchecker;

import java.util.Iterator;
import java.util.Set;
import java.util.logging.Logger;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class APIcheckerController {
	private static final Logger log = Logger.getLogger(APIcheckerController.class.getName());
	
	@RequestMapping(value="/", method=RequestMethod.GET)
	public String showCheckAPI(ApiEntry remoteResource){
		return "checkapi";
	}
	
	@RequestMapping(value="/", method=RequestMethod.POST)
	public String doCheckAPI(ApiEntry apiEntry){
		apiEntry.sendRequest();
		switch (apiEntry.getResponseCode()) {
		case 401: 
			apiEntry.setMessage("Authorization required, add appropriate headers (todo: print chalenge header)");
			apiEntry.setShowLevel(1);
			break;
		case 200:
			LinkExtrator links = new LinkExtrator();
			Set<String> urls = UrlWorker.getUrls(apiEntry.getUrl(), links.grabHTMLLinks(apiEntry.getResponseBody()));
			apiEntry.setMessage("leads to "+urls);
		}
		return "checkapi";
	}
	
	@RequestMapping(value="/client.html", method=RequestMethod.GET)
	public String showClient(RemoteResource remoteResource){
		return "client";
	}
	
	@RequestMapping(value="/client.html", method=RequestMethod.POST)
	public String doClient(RemoteResource remoteResource){
		remoteResource.sendRequest();
		return "client";
	}

}
