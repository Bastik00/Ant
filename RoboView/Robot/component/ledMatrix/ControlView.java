package de.hska.lat.robot.component.ledMatrix;

import de.hska.lat.robot.component.ledMatrix.LedMatrixChangeNotifier;
import de.hska.lat.robot.component.view.ComponentView;



public class ControlView extends ComponentView implements LedMatrixChangeNotifier
{

/**
	 * 
	 */
	private static final long serialVersionUID = 1L;


	LedMatrix ledMatrix; 
	
public ControlView(String name,LedMatrix ledMatrix)
{
	super(name, false);

	this.ledMatrix=ledMatrix;
	
	buildView();
	this.setSize(200, 100);
}



protected void buildView()
{
	
}

}
