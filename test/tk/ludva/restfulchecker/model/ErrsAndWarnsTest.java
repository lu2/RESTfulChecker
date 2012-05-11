package tk.ludva.restfulchecker.model;

import static org.junit.Assert.*;

import org.junit.Test;

public class ErrsAndWarnsTest
{

	@Test
	public void testEaw()
	{
		ErrsAndWarns e = new ErrsAndWarns();
		assertEquals(0, e.getErrorsCount());
		assertEquals(0, e.getWarnsCount());
	}
	
	@Test
	public void testErrpp()
	{
		ErrsAndWarns e = new ErrsAndWarns();
		e.errpp();
		assertEquals(1, e.getErrorsCount());
		e.errpp();
		assertEquals(2, e.getErrorsCount());
		assertEquals(0, e.getWarnsCount());
	}

	@Test
	public void testWarnpp()
	{
		ErrsAndWarns e = new ErrsAndWarns();
		e.warnpp();
		assertEquals(1, e.getWarnsCount());
		e.warnpp();
		assertEquals(2, e.getWarnsCount());
		assertEquals(0, e.getErrorsCount());
	}

	@Test
	public void testAddErr()
	{
		ErrsAndWarns e = new ErrsAndWarns();
		e.addErr(5);
		assertEquals(5, e.getErrorsCount());
		assertEquals(0, e.getWarnsCount());
	}

	@Test
	public void testAddWarn()
	{
		ErrsAndWarns e = new ErrsAndWarns();
		e.addWarn(5);
		assertEquals(5, e.getWarnsCount());
		assertEquals(0, e.getErrorsCount());
	}

}
