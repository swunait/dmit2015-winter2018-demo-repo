package dmit2015.model;

public class Rectangle {

	private double length;
	private double width;
	
	public double getLength() {
		return length;
	}
	public void setLength(double length) {
		this.length = length;
	}
	public double getWidth() {
		return width;
	}
	public void setWidth(double width) {
		this.width = width;
	}
	
	public Rectangle() {
	}
	public Rectangle(double length, double width) {
		this.length = length;
		this.width = width;
	}

	public double getArea() {
		return length * width;
	}
	
	public double getPerimeter() {
		return 2 * (length + width);
	}
	
	public double getDiagonal() {
		return Math.sqrt( Math.pow(width, 2) + Math.pow(length, 2));
	}
	
}
