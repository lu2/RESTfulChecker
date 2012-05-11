package tk.ludva.restfulchecker;

import static org.junit.Assert.*;

import org.junit.Test;

public class HeaderTest
{

	@Test
	public void testHeaderStringStringBoolean()
	{
		Header h = new Header("key", "value", true);
		assertEquals("key", h.getHeaderKey());
		assertEquals("value", h.getHeaderValue());
		assertEquals(true, h.isInUse());
	}

}
