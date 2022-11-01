package de.hska.lat.robot.component.ledMatrix;

import de.hska.lat.robot.component.ledMatrix.LedMatrixChangeNotifier;
import de.hska.lat.robot.component.view.ComponentView;


public class DataView extends ComponentView implements LedMatrixChangeNotifier
{

/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	LedMatrix ledMatrx; 

public DataView(String name,LedMatrix ledMatrx)
{
	super(name, false);

	this.ledMatrx=ledMatrx;

	buildView();
	this.setSize(200, 100);
}



protected void buildView()
{
}

}
