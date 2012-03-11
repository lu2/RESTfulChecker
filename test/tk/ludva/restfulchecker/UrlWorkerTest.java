package tk.ludva.restfulchecker;

import static org.junit.Assert.*;

import java.util.HashSet;
import java.util.Set;

import org.junit.Test;
import tk.ludva.restfulchecker.UrlWorker;

public class UrlWorkerTest {

	@Test
	public void testGetUrls() {
		Set<String> odkazy = new HashSet<String>();
		odkazy.add("/restful");
		odkazy.add("/restful/");
		odkazy.add("./restful");
		odkazy.add("/restful/");
		Set<String> constructedUrls = UrlWorker.getUrls("http://www.ludva.tk", odkazy);
	}

}
