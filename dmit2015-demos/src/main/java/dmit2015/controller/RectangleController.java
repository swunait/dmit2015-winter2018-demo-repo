package dmit2015.controller;

import javax.enterprise.context.RequestScoped;
import javax.faces.event.ActionEvent;
import javax.inject.Named;

import org.omnifaces.util.Messages;

import dmit2015.model.Rectangle;

@Named("currentRectangleController")
@RequestScoped
public class RectangleController {

	private Rectangle currentRectangle = new Rectangle();	// +getter +setter

	public Rectangle getCurrentRectangle() {
		return currentRectangle;
	}

	public void setCurrentRectangle(Rectangle currentRectangle) {
		this.currentRectangle = currentRectangle;
	}
	
	public void calculateArea(ActionEvent event) {
		Messages.addGlobalInfo("The area of the rectangle is {0}", currentRectangle.getArea());
	}
	
	public void calculatePerimeter(ActionEvent event) {
		Messages.addGlobalInfo("The perimeter of the rectangle is {0} ", currentRectangle.getPerimeter());

	}
	
	public void calculateDiagonal(ActionEvent event) {
		Messages.addGlobalInfo("The diagonal of the rectangle is {0} ", currentRectangle.getDiagonal());

	}
}
