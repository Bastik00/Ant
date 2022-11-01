package de.hska.lat.robot.display.generics.map2D.displayData;

import java.util.ArrayList;

/**
 * 
 * @author Oktavian Gniot
 * 
 *
 */

public class DisplayData {

	
	protected ArrayList<DisplayDataListener> listenerList = new ArrayList<DisplayDataListener>();
	
	private float displayXOffset;
	private float displayYOffset;
	

	private float xDimension;
	private float yDimension;
	private float gridSize;
	
	private float raster;
	
	private float scale=10;
	private float halfScale=scale /2 ;
	private boolean toGrid;


public DisplayData()
{
	this.setScale(10);
	this.toGrid=true;
}
	
public void setScale(float scale)
{
	
	this.gridSize=50;
	
	this.scale=scale;
	if (this.scale<10)
		this.scale=10;
	
	
	this.halfScale=scale /2 ;
	this.raster = this.scale / this.gridSize;
	
	this.offsetChanged();
	
}
	



public float toScale(float value)
{
	float scaledValue;

	
	if (value>0)
	{
		value += this.halfScale;
	}
		
	else
	{
		value -= this.halfScale;		
	}
	
	scaledValue = (((int)(value /scale)) * scale);  
	
	return(scaledValue); 
	
}


public void zoomOut()
{
	this.setScale(this.getScale()+10);
}


public void zoomOutMore()
{
	this.setScale(this.getScale()+100);
}

public void zoomIn()
{
	this.setScale(this.getScale()-10);
}

public void zoomInMore()
{
	this.setScale(this.getScale()-100);
}

/**
 * get scale of correspondent map display
 * @return scale in millimeters
 */
public float getScale()
{
	return(this.scale);
}


public float getGridSize()
{
	// TODO Auto-generated method stub
	return (this.gridSize);
}



public float getRasterSize()
{
	return(this.raster);
}

/**
 * set the on screen dimensions (x and y extend) of the corresponding map
 * @param xDimension
 * @param yDimension
 */

public void setDisplayDimension(float xDimension, float yDimension)
{
	this.xDimension=xDimension;
	this.yDimension=yDimension;
}


/**
 * set the x and y on screen offset of this map on screen
 * @param xOffset
 * @param yOffset
 */
public void setDisplayOffset(float xOffset,float yOffset)
{
	this.displayXOffset=xOffset;
	this.displayYOffset=yOffset;
	this.offsetChanged();
}

/**
 * get the on screen x offset of corresponding map
 * @return x offset
 */
public float getDisplayXOffset()
{
	return(this.displayXOffset);
}


/**
 * get the on screen y offset of corresponding map
 * @return x offset
 */
public float getDisplayYOffset()
{
	return(this.displayYOffset);
}

/**
 * move the offset of the corresponding map 
 * @param deltaX 
 */

public void moveDisplayXOffset(float deltaX)
{
	this.displayXOffset+=deltaX;
	this.offsetChanged();
}

public void moveDisplayYOffset(float deltaY)
{
	this.displayYOffset+=deltaY;	
	this.offsetChanged();
}

/**
 * move offset of the corresponding map   
 * @param deltaX 
 * @param deltaY
 */
public void moveDisplayOffset(int deltaX,int deltaY)
{
	this.displayXOffset+=deltaX;
	this.displayYOffset+=deltaY;	
	this.offsetChanged();
}


/**
 * translate the x coordinate from screen dimensions to map dimension 
 * @param x x coordinate on screen
 * @return  x coordinate on map
 */
public float translateXFormScreen(float x)
{
	float xT;
	
	xT= ((x-this.displayXOffset)- (this.xDimension / 2))*this.raster;

	return(xT);
}

/**
 * translate the y coordinate from screen dimensions to map dimension 
 * @param y y coordinate on screen
 * @return  Y coordinate on map 
 */
public float translateYFormScreen(float y)
{
	float yT;
	
	yT= -((y -this.displayYOffset) - (this.yDimension / 2))*this.raster;

	return(yT);	
}




/**
 * translate given length to screen Dimension 
 * @param length length to be converted
 * @return  length on screen
 */

public float toScreenLenght(float length)
{
	float newlength;
	

	newlength= (length/ this.raster) ;
	return(newlength);
}
/**
 * translate x coordinate from map dimension to screen Dimension 
 * @param x x coordinate on map
 * @return  x coordinate on screen
 */

public float translateXToScreen(float x)
{
	float xT;
	

	xT= (x/ this.raster)+ (this.xDimension / 2)+this.displayXOffset ;
	return(xT);
}

/**
 * translate y coordinate from map dimension to screen Dimension 
 * @param y y coordinate on map
 * @return  y coordinate on screen
 */

public float translateYToScreen(float y)
{
	float yT;
	

	yT= this.displayYOffset-(y/ this.raster)+ (this.yDimension / 2);
	
	return(yT);	
}


public void setGridAlignment(boolean alignment)
{
	this.toGrid=alignment;
}


public boolean toGrid()
{
	return (this.toGrid);
}




public void addListener(DisplayDataListener listener) 
{
	listenerList.add(listener);
	
}


public void removeListener(DisplayDataListener listener) 
{
	listenerList.remove(listener);
	
}



protected void offsetChanged()
{
	for (DisplayDataListener listener : listenerList  )
	{
		listener.displayDataChanged(this);
	}
}

public float getxDimension() {
	return xDimension;
}

public float getyDimension() {
	return yDimension;
}

}
