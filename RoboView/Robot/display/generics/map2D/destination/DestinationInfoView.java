package de.hska.lat.robot.display.generics.map2D.destination;



import de.hska.lat.navigation.Destination;
import de.hska.lat.navigation.DestinationChangeListener;
import de.hska.lat.robot.ui.dataInfo.DataInfoView;

public class DestinationInfoView  extends DataInfoView implements DestinationChangeListener
{

	
	/**
	 * 
	 */
	private static final long serialVersionUID = -8197633116723552569L;
//	private static final String DESTINATION_TEXT = "destination";
//	private static final String UNKNOWN_TEXT = "unknown";
	
	
	private Destination destination;
	
public DestinationInfoView(Destination destination)
{

	// TODO Auto-generated constructor stub


	this.destination = destination;
	
	this.width = 280;
	this.height = 300;
	
	this.destination.addListener(this);
	
}






public void setDestinationData(Destination destination)
{
	this.destination =  destination;
}

/*
@Override
protected void onDraw(Canvas canvas) 
{
		super.onDraw(canvas);

		
		
		
		canvas.drawText(DESTINATION_TEXT, 10, 30, paint);
		if( destination!=null)
		{
			canvas.drawText("  x  : "+destination.getXDestination()+"mm", 10, 60, paint);
			canvas.drawText("  y  : "+destination.getYDestination()+"mm" , 10, 90, paint);
		}
		else
		{
			canvas.drawText("  x  : "+UNKNOWN_TEXT, 10, 60, paint);
			canvas.drawText("  y  : "+UNKNOWN_TEXT, 10, 90, paint);
			
		}


}


*/



@Override
public void destinationChanged(Destination destination)
{
	this.invalidate();
}

}