package de.hska.lat.robot.component.generic.collision.view;




import de.hska.lat.robot.component.ComponentChangeNotifier;
import de.hska.lat.robot.component.generic.collision.CollisionDetector;
import de.hska.lat.robot.component.view.ComponentView;
import de.hska.lat.robot.component.view.MissingComponentView;
import de.hska.lat.robot.component.view.SensorDataView;


public class CollisionDetectorDataView  extends SensorDataView<CollisionDetector> implements ComponentChangeNotifier
{

	
	protected static final int width = 160;
	protected static final int height = 50;
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 6397689382576347950L;

	
	
	
	
public CollisionDetectorDataView(CollisionDetector sensor)
{
	super(sensor);
	

	this.buildView();
}

@Override
protected int getViewWidth()
{
	return(CollisionDetectorDataView.width);
}

@Override
protected int getViewHeight()
{
	return(CollisionDetectorDataView.height);
}




	
public void buildView()
{
	super.buildView();
	//ValueView<?> view;
	
	
//	Insets insets = this.getBorder().getBorderInsets(this);
	
	
	
//	view= CollisionDetectorDataView.createView(this.component,true );
	//view.setLocation(10,insets.top+5);
	//this.add(view);

}
	
	



public static ComponentView createView(CollisionDetector sensor)
{

	if (sensor!=null)
	{
		return(new CollisionDetectorDataView(sensor));
	}
	else
	{
		return(new MissingComponentView(CollisionDetector.class.getName()));
	}
}



}
