package de.hska.lat.robot.display.generics.map2D.destination;


import de.hska.lat.navigation.Destination;
import de.hska.lat.robot.display.generics.map2D.drawer.Map2DDrawer;
import de.hska.lat.robot.display.generics.map2D.metaData.MapDisplayMetaData;



public class DestinationDrawer extends Map2DDrawer
{

	protected Destination destination;
	 
	
public DestinationDrawer(MapDisplayMetaData mapDisplayMetaData,Destination destination)
{
	this.displayData=mapDisplayMetaData.getDisplayData();
	this.destination=destination;
//	paint.setMaskFilter(innerMaskFilter);
//	paint.setStrokeWidth(2);
//	paint.setColor(this.displayColor);
}
	


/*	
public void draw(Canvas canvas)
{
	
	float x1,x2;
	float y1,y2;
	
	paint.setColor(this.displayColor);
	
	x1=displayData.translateXToScreen(destination.getXOrigin());
	y1=displayData.translateYToScreen(destination.getYOrigin());
	
	x2=displayData.translateXToScreen(this.destination.getXDestination());
	y2=displayData.translateYToScreen(this.destination.getYDestination());
	
	
	canvas.drawLine(x1,y1,x2, y2, paint);
	
	canvas.drawCircle(x2, y2, 5, paint);

	canvas.drawCircle(x1, y1, 2, paint);
	
	canvas.drawLine(x2,y2,x2, y2-40, paint);
	canvas.drawLine(x2+30,y2-30,x2, y2-40, paint);
	canvas.drawLine(x2+30,y2-30,x2, y2-20, paint);

	
}	
*/
}
