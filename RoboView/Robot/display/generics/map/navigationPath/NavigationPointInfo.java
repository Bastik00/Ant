package de.hska.lat.robot.display.generics.map.navigationPath;



import de.hska.lat.navigation.path.NavigationPoint;

import de.hska.lat.robot.ui.dataInfo.DataInfoView;

public class NavigationPointInfo  extends  DataInfoView
{

	
	/**
	 * 
	 */
	private static final long serialVersionUID = 4734511028488364071L;
	protected static String noDataString = "no data";
	NavigationPoint navigationPoint;
	
	
public NavigationPointInfo()
{


	
	
	this.width=180;
	this.height=90;
	

}


public void setNavigationPoint(NavigationPoint navigationPoint)
{
	this.navigationPoint = navigationPoint;
	this.invalidate();
}


/*
@Override
protected void onDraw(Canvas canvas) 
{
	//	float heading;
		String valueString;
		
		super.onDraw(canvas);

		
		paint.setColor(0xff5090ff);
		
		if (this.navigationPoint==null)
		{
			canvas.drawText(noDataString , 10, 30, paint);
		}
		else
		{
		/*	heading = (float) (pose.getHeading() *180 / Math.PI);
			
			valueString=FloatValue.toFormatedFractionString(heading, 2);
			canvas.drawText("\u03B1 : "+valueString+"°" , 10, 30, paint);
*/
	/*		valueString=FloatValue.toFormatedFractionString(navigationPoint.getXPosition(), 0);
			canvas.drawText("x : "+valueString+" mm" , 10, 60, paint);
			
			valueString=FloatValue.toFormatedFractionString(navigationPoint.getZPosition(), 0);
			canvas.drawText("z : "+valueString+" mm" , 10, 90, paint);
			
		}
}
*/

}
