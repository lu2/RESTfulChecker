package tk.ludva.restfulchecker.validators;

import static org.junit.Assert.*;

import org.junit.Test;

public class HttpValidatorTest
{

	@Test
	public void testToSafeId()
	{
		assertEquals("_____", HttpValidator.toSafeId("/?=&;"));
		assertEquals("b_b_b_b_b_b", HttpValidator.toSafeId("b/b?b=b&b;b"));
	}

}
