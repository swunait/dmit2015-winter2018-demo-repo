package dmit2015.model;

public class Circle {

	/** The radius of the circle */
	private double radius;

	public double getRadius() {
		return radius;
	}

	public void setRadius(double radius) {
		if (radius >= 0)
			this.radius = radius;
	}

	public Circle() {
	}

	public Circle(double radius) {
		this.radius = radius;
	}

	public double getArea()
    {
        return Math.PI * Math.pow(radius, 2);
    }
	
    public double getDiameter()
    {
        return 2 * radius;
    }
    
    public double getCircumference()
    {
        return 2 * Math.PI * radius;
    }
	
}
