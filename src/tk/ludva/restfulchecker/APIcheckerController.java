package tk.ludva.restfulchecker;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class APIcheckerController {
	
	@RequestMapping(value="/", method=RequestMethod.GET)
	public String showCheckAPI(RemoteResource remoteResource){
		return "checkapi";
	}
	
	@RequestMapping(value="/", method=RequestMethod.POST)
	public String doCheckAPI(RemoteResource remoteResource){
		HttpURLConnection conn = null;
		try {
			URL remoteUrl = new URL(remoteResource.getUrl());
			conn = (HttpURLConnection)remoteUrl.openConnection();
			conn.setRequestMethod(remoteResource.getMethod());
			
			System.out.println(conn.getResponseCode()+" "+conn.getResponseMessage());
			for (int n=0; n<conn.getHeaderFields().size(); n++) {
				System.out.println(conn.getHeaderFieldKey(n)+": "+conn.getHeaderField(n));
			}
			String radek;
			BufferedReader reader = new BufferedReader(new InputStreamReader(conn.getInputStream()));
			while ((radek = reader.readLine()) != null) {
				System.out.println(radek);
			}
		} catch (MalformedURLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return "checkapi";
	}

}
