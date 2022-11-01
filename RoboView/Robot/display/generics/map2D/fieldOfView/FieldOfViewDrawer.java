package de.hska.lat.robot.display.generics.map2D.fieldOfView;


import de.hska.lat.perception.fieldOfView.FieldOfView;
import de.hska.lat.robot.display.generics.map2D.drawer.Map2DDrawer;


public class FieldOfViewDrawer extends Map2DDrawer 
{
	boolean active;
	
	
	protected FieldOfView<?> fieldOfView;

	
public FieldOfViewDrawer(FieldOfView<?> fieldOfView)
{
	this.fieldOfView = fieldOfView;
	this.active= true;

}


public boolean isOn() 
{
	return(this.active);
}


public void on()
{
	this.active=true;
}

public void off()
{
	this.active=false;
}


public String getName() 
{
	if (this.fieldOfView!=null)
		return (this.fieldOfView.getName());
	
	return("");
}





	



}
