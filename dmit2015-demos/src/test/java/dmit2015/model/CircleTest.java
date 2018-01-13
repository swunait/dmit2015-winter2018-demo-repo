package dmit2015.model;

import static org.junit.Assert.*;

import org.junit.Test;

public class CircleTest {

	@Test
	public void testAll() {
		// Create a circle object with a radius of 5
		Circle circle1 = new Circle(5);
		// Verify the radius is 5
		assertEquals(5, circle1.getRadius(), 0);
		// Verify the area is 78.54
		assertEquals(78.54, circle1.getArea(), 0.001);
		// Verify the diameter is 10
		assertEquals(10, circle1.getDiameter(), 0);
		// Verify the circumference is 31.42
		assertEquals(31.42, circle1.getCircumference(), 0.005);
		
		
	}

}
