package tk.ludva.restfulchecker;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class APIcheckerController {

	@RequestMapping(value="/checkapi/", method=RequestMethod.POST)
	@ResponseBody
	public String checkAPI(@RequestParam String apiEntryURL) {
		StringBuilder response = new StringBuilder();
		response.append("Vstupni bod API je: ").append(apiEntryURL).append('\n');
		
		
		return response.toString();
	}
}
