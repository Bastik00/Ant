package de.hska.lat.robot.ui.analogueInstrument;

import java.awt.Color;
/**
 * 
 * @author dungeon keeper
 *
 * used by analog Instrument
 */
public class ScaleFragment {

	public final Color color;
	public final float size; 
	

public ScaleFragment(Color color, float size)
{
	this.color=color;
	this.size=size;
}
	

/**
 * get color of this scale fragment
 * @return
 */
public Color getColor()
{
	return(this.color);
}

/**
 * get size of this scale fragment
 * @return
 */

public float getSize()
{
	return(this.size);
}

	
}
