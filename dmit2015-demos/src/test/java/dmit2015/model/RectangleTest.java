package dmit2015.model;

import static org.junit.Assert.*;

import org.junit.Test;

public class RectangleTest {

	@Test
	public void testGetArea() {
		// Create a rectangle object
		Rectangle rectangle1 = new Rectangle();
		// Set the length to 10
		rectangle1.setLength(10);
		// Set the width to 5
		rectangle1.setWidth(5);
		// Verify area is 50
		assertEquals(50, rectangle1.getArea(), 0);
		// Verify perimeter is 30
		assertEquals(30, rectangle1.getPerimeter(), 0);
		// Verify diagonal is 11.18
		assertEquals(11.18, rectangle1.getDiagonal(), 0.001);
		
	}

}
