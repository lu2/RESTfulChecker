package tk.ludva.restfulchecker;

import static org.junit.Assert.*;

import org.junit.Test;

public class APIcheckerControllerTest
{

	@Test
	public void testShowCheckAPI()
	{
		APIcheckerController a = new APIcheckerController();
		assertEquals("checkapi", a.showCheckAPI(null));
	}

	@Test
	public void testShowCheckAPIRepeatedTesting()
	{
		APIcheckerController a = new APIcheckerController();
		assertEquals("checkapirepeatedtesting", a.showCheckAPIRepeatedTesting(null));
	}

}
