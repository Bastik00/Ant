package de.hska.lat.robot.value.view;

import de.hska.lat.robot.value.ComponentValue;
import de.hska.lat.robot.value.FloatValue;

/**
 * Class for displaying that a Value has could not found
 * @author Oktavian Gniot
 *
 */


public class MissingValueView  extends  ValueView <FloatValue>
{
	

/**
	 * 
	 */
	private static final long serialVersionUID = 1820267339241231444L;


	protected static final int width = 100;
	protected static final int height = 100;

	
public MissingValueView(String type)
{
		super(new FloatValue("missing :"+ type), false);
		this.buildView();
}



public MissingValueView(String type, boolean embedded)
{
		super(new FloatValue("missing :"+ type), embedded);
		this.buildView();
}


@Override
protected void buildView()
{
	
	super.buildView();

}	

@Override
protected int getViewWidth()
{
	return(MissingValueView.width);
}

@Override
protected int getViewHeight()
{
	return(MissingValueView.height);
}


/**
 * does nothing , has no values to display
 */
@Override
public void valueChanged(ComponentValue<?> componentValue)
{
	
}








}
