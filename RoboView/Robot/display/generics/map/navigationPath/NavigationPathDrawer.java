package de.hska.lat.robot.display.generics.map.navigationPath;


import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;

import de.hska.lat.math.vector.FloatVector3D;
import de.hska.lat.navigation.path.NavigationPath;
import de.hska.lat.navigation.path.NavigationPoint;
import de.hska.lat.robot.display.generics.map2D.drawer.Map2DDrawer;
import de.hska.lat.robot.display.generics.map2D.metaData.MapDisplayMetaData;

public class NavigationPathDrawer  extends Map2DDrawer
{
	
	protected NavigationPath navigationPath;
	protected float[] lines = new float [24] ;
	
	protected int reachedColor;
	
public NavigationPathDrawer(MapDisplayMetaData mapDisplayMetaData)
{
	this.navigationPath = mapDisplayMetaData.getNavigationPath();
	this.displayData = mapDisplayMetaData.getDisplayData();
	this.calculateReachedColor();
	
	
	navigationPath.addNavPoint(new FloatVector3D(100, 0,100));
	
}
	
	


/**
* set display color of this drawer	
* @param color new color for drawing
*/
@Override
public void setDisplayColor(Color color)
{
	super.setDisplayColor(color);
	this.calculateReachedColor();
}



protected void calculateReachedColor()
{
	
	/*
	int alfa;
		
	alfa=this.displayColor &0xff000000;
	alfa>>>=2;
	alfa &=0xff000000;
	
	this.reachedColor = (this.displayColor & 0xffffff) | alfa;
	*/ 
}


public void drawNavpoint(Graphics graphics, NavigationPoint navigationPoint,int index)
{
	
	Graphics2D graphics2D = (Graphics2D)graphics;

	
	if (navigationPoint.isSelected())
	{
		graphics2D.setStroke(new BasicStroke(3));
//		this.paint.setStrokeWidth(4);
	}
	else
	{
		graphics2D.setStroke(new BasicStroke(1));
//		this.paint.setStrokeWidth(1);
	}
		
	
	if (navigationPoint.wasReached())
	{
	//	this.paint.setColor(this.reachedColor);
		this.drawReachedNavpoint(graphics, navigationPoint, index);
	}
	else
	{
	//	this.paint.setColor(this.displayColor);
		this.drawUnReachedNavpoint(graphics, navigationPoint, index);
	
	}
}
	
public void drawReachedNavpoint(Graphics graphics, NavigationPoint navigationPoint,int index)
{
	
	int xPos;
	int yPos;	
	
	xPos= (int) displayData.translateXToScreen(navigationPoint.getXPosition());
	yPos=(int) displayData.translateYToScreen(navigationPoint.getZPosition());
	
	//graphics.drawLine(xPos-7, yPos-7 , xPos-7 , yPos+7);
	graphics.drawLine(xPos-7, yPos-7 , xPos-7 , yPos+7);
	
	lines[0] = xPos-7 ;
	lines[1] = yPos-7 ;
	lines[2] = xPos-7 ;
	lines[3] = yPos+7 ;

	
	
	lines[4] = xPos-7 ;
	lines[5] = yPos+7 ;
	lines[6] = xPos+7 ;
	lines[7] = yPos+7 ;
	
	lines[8] = xPos+7 ;
	lines[9] = yPos+7 ;
	lines[10] = xPos+7 ;
	lines[11] = yPos-7 ;
	
	lines[12] = xPos+7 ;
	lines[13] = yPos-7 ;
	lines[14] = xPos-7 ;
	lines[15] = yPos-7 ;
	

	lines[16] = xPos-7 ;
	lines[17] = yPos-7 ;
	lines[18] = xPos+7 ;
	lines[19] = yPos+7 ;
	
	lines[20] = xPos-7 ;
	lines[21] = yPos+7 ;
	lines[22] = xPos+7 ;
	lines[23] = yPos-7 ;
	
	
	//canvas.drawLines(lines, this.paint); 
	((Graphics2D)graphics).drawString(String.valueOf(index), xPos+10, yPos);

}




public void drawUnReachedNavpoint(Graphics graphics, NavigationPoint navigationPoint,int index)
{

	int xPos;
	int yPos;	
	
	xPos= (int) displayData.translateXToScreen(navigationPoint.getXPosition());
	yPos=(int) displayData.translateYToScreen(navigationPoint.getZPosition());
	
	
	graphics.drawLine(xPos-7, yPos-7 , xPos-7 , yPos+7);
	graphics.drawLine(xPos+7, yPos-7 , xPos+7 , yPos+7);
	graphics.drawLine(xPos-7, yPos-7 , xPos-2 , yPos-7);
	graphics.drawLine(xPos+7, yPos-7 , xPos+2 , yPos-7);
	graphics.drawLine(xPos-7, yPos+7 , xPos-2 , yPos+7);
	graphics.drawLine(xPos+7, yPos+7 , xPos+2 , yPos+7);


	graphics.drawString(String.valueOf(index), xPos+10,  yPos);
	graphics.drawString(navigationPoint.getName(), xPos+10,  yPos+10);
}

@Override
public void draw(Graphics2D graphics)
{
	int enumerator;
	NavigationPoint navigationPoint;
	
	graphics.setColor(Color.BLUE);
	
	for (enumerator = 0; enumerator< this.navigationPath.size()  ; enumerator++)
	{
	
		navigationPoint = this.navigationPath.getNavpoint(enumerator);
		
		
		if (navigationPoint!=null)
			this.drawNavpoint(graphics, navigationPoint,enumerator);
		
	}
	

}




public void setPath(NavigationPath path)
{
	this.navigationPath = path;
}	

}
