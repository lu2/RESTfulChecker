package tk.ludva.restfulchecker;

import static org.junit.Assert.*;

import org.junit.Test;

public class ResourceNodeTest {
	
	ResourceNode resourceNode;

	@Test
	public void testGetViolationMessages() {
		resourceNode = new ResourceNode();
		assertNotNull(resourceNode.getViolationMessages());
	}

	@Test
	public void testAddViolationMessage() {
		resourceNode = new ResourceNode();
		assertFalse(resourceNode.getViolationMessages().containsKey("1"));
		resourceNode.addViolationMessage("1", "one");
		assertTrue(resourceNode.getViolationMessages().containsKey("1"));
		resourceNode.addViolationMessage("1", "two");
		assertTrue(resourceNode.getViolationMessages().get("1").getMessages().size() == 2);
		resourceNode.addViolationMessage("2", "two");
		assertTrue(resourceNode.getViolationMessages().size() == 2);
		assertEquals("one", resourceNode.getViolationMessages().get("1").getMessages().get(0));
		assertEquals("two", resourceNode.getViolationMessages().get("1").getMessages().get(1));
	}
	
	@Test
	public void testGetDescendats() {
		resourceNode = new ResourceNode();
		assertNotNull(resourceNode.getDescendants());
	}

}
