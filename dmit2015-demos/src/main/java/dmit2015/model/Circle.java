package dmit2015.model;

/**
 * The Circle class models a circle shape.
 * 
 * @author Sam Wu
 * @version 2018.01.15
 * 
 */
public class Circle {

	/** The radius of the circle */
	private double radius;

	/**
	 * Return the radius of the circle
	 * @return The value in the radius field
	 */
	public double getRadius() {
		return radius;
	}

	/**
	 * Set the radius
	 * @param radius The value to store in the radius field
	 */
	public void setRadius(double radius) {
		if (radius >= 0)
			this.radius = radius;
	}

	/**
	 * Construct a circle with a radius of 1
	 */
	public Circle() {
		radius = 1;
	}

	/** 
	 * Construct a circle with a specified radius 
	 * @param radius The new radius to set the radius field to
	 */
	public Circle(double radius) {
		this.radius = radius;
	}

	/**
	 * Returns the area of the circle using the formula:
	 * area = PI * radius * radius
	 * @return The calculated area of the circle
	 */
	public double getArea()
    {
        return Math.PI * Math.pow(radius, 2);
    }
	
	/**
	 * Returns the diameter of the circle using the formula:
	 * diameter = radius * 2
	 * @return The calculated diameter of the circle
	 */
    public double getDiameter()
    {
        return 2 * radius;
    }
    
    /**
     * Return the circumference of the circle using the formula:
     * circumference = 2 * PI * radius
     * @return The calculated circumference of the circle
     */
    public double getCircumference()
    {
        return 2 * Math.PI * radius;
    }
	
}
