package tk.ludva.restfulchecker;

import static org.junit.Assert.*;

import org.junit.Test;

public class QuestionnairesTest
{

	@Test
	public void testEvaluate()
	{
		Questionnaires q = new Questionnaires();
		assertEquals("", q.evaluate());
		
		q.setQ1a(false);
		assertNotSame("", q.evaluate());
	}

}
