package tk.ludva.restfulchecker;

import static org.junit.Assert.*;

import java.net.MalformedURLException;
import java.util.HashSet;
import java.util.Set;

import org.junit.Test;
import tk.ludva.restfulchecker.UrlWorker;

public class UrlWorkerTest {

	@Test
	public void testConstructUrl() {
		try {
			assertEquals("Result", "http://ludva.tk/index.html", UrlWorker.constructUrl("http://ludva.tk", "index.html"));
			assertEquals("Result", "http://ludva.tk/index.html", UrlWorker.constructUrl("http://ludva.tk/", "index.html"));
			assertEquals("Result", "http://ludva.tk/index.html", UrlWorker.constructUrl("http://ludva.tk", "/index.html"));
			assertEquals("Result", "http://ludva.tk/index.html", UrlWorker.constructUrl("http://ludva.tk/", "/index.html"));
			assertEquals("Result", "http://ludva.tk/index.html", UrlWorker.constructUrl("http://ludva.tk/", "./index.html"));
			assertEquals("Result", "http://ludva.tk/index/users", UrlWorker.constructUrl("http://ludva.tk/", "/index/users"));
			assertEquals("Result", "http://ludva.tk/index/users", UrlWorker.constructUrl("http://ludva.tk/", "index/users"));
			assertEquals("Result", "http://ludva.tk/index/users", UrlWorker.constructUrl("http://ludva.tk", "./index/users"));
			assertEquals("Result", "http://ludva.tk/index/users", UrlWorker.constructUrl("http://ludva.tk", "/index/users"));
			assertEquals("Result", "http://ludva.tk/index/users", UrlWorker.constructUrl("http://ludva.tk", "index/users"));
			assertEquals("Result", "http://ludva.tk/index/users", UrlWorker.constructUrl("http://ludva.tk", "./index/users"));
		} catch (MalformedURLException e) {
			fail("Unexpected MalformedURLException");
			e.printStackTrace();
		}
	}

}
