package tk.ludva.restfulchecker;

import static org.junit.Assert.*;

import java.util.List;

import org.junit.Test;

public class LinkExtratorTest
{

	@Test
	public void testGrabHTMLLinks()
	{
		LinkExtrator l = new LinkExtrator();
		List<String> g = l.grabHTMLLinks("http://www.moje.cz");
		assertTrue(g.size()==1);
		assertEquals("http://www.moje.cz", g.get(0));
		
		g = l.grabHTMLLinks("'http://www.moje.cz'");
		assertEquals("http://www.moje.cz", g.get(0));
		
		g = l.grabHTMLLinks("\"http://www.moje.cz\"");
		assertEquals("http://www.moje.cz", g.get(0));
		
		g = l.grabHTMLLinks("'http://www.moje.cz', http://www.moje2.cz     ");
		assertEquals("http://www.moje.cz", g.get(0));
		assertEquals("http://www.moje2.cz", g.get(1));
		
		g = l.grabHTMLLinks("https://www.moje.cz");
		assertTrue(g.size()==1);
		assertEquals("https://www.moje.cz", g.get(0));
		
		g = l.grabHTMLLinks("'https://www.moje.cz'");
		assertEquals("https://www.moje.cz", g.get(0));
		
		g = l.grabHTMLLinks("\"https://www.moje.cz\"");
		assertEquals("https://www.moje.cz", g.get(0));
		
		g = l.grabHTMLLinks("'https://www.moje.cz', https://www.moje2.cz     ");
		assertEquals("https://www.moje.cz", g.get(0));
		assertEquals("https://www.moje2.cz", g.get(1));
		
		g = l.grabHTMLLinks("href=\"mine\"    href='mine'     ");
		assertEquals("\"mine\"", g.get(0));
		assertEquals("mine", g.get(1));
	}

}
