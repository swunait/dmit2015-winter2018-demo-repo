package dmit2015.controller;

import javax.enterprise.context.RequestScoped;
import javax.faces.event.ActionEvent;
import javax.inject.Named;

import org.omnifaces.util.Messages;

import dmit2015.model.Circle;

@Named("currentCircleController")
@RequestScoped
public class CircleController {
	
	private Circle currentCircle = new Circle();	// +getter +setter

	public Circle getCurrentCircle() {
		return currentCircle;
	}

	public void setCurrentCircle(Circle currentCircle) {
		this.currentCircle = currentCircle;
	}
	
	public void calculateArea(ActionEvent event) {
		Messages.addGlobalInfo("The area of the circle is {0}", currentCircle.getArea() );
	}
	
	public void calculateCircumference(ActionEvent event) {
		Messages.addGlobalInfo("The circumference of the circle is {0}", currentCircle.getCircumference() );		
	}
	
	public void calculateDiameter(ActionEvent event) {
		Messages.addGlobalInfo("The diameter of the circle is {0}", currentCircle.getDiameter() );		
	}

}
