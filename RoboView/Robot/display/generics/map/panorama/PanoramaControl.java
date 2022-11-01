package de.hska.lat.robot.display.generics.map.panorama;


import java.awt.Color;



import de.hska.lat.robot.display.generics.map2D.MapControlElement;
import de.hska.lat.robot.display.generics.map2D.MapEventListener;

import de.hska.lat.robot.ui.colorSelector.ColorSelectorListener;
;


public class PanoramaControl extends MapControlElement implements ColorSelectorListener // implements DroidSwitchListener, 
{

	
	
	

/**
	 * 
	 */
	private static final long serialVersionUID = -3538292309844069526L;


public PanoramaControl( MapEventListener listener)
{
	super( false,  MapControlMode_e.MODE_STANDARD,  listener);
	
	
	
}


/*
public void addViews(Context context, Panorama [] panoramas)
{
	int enumerator;
	int yPos;
	ColorSelector colorSelector;
	
	
	yPos=10;
	
	
	for (enumerator=0;enumerator<panoramas.length;enumerator++)
	{
		if (panoramas[enumerator]!=null)
		{
			addSwitch(context,panoramas[enumerator].getName(),5, yPos,this.getWidth()-70,50,this);
		
			colorSelector = new ColorSelector(context,ColorSelector.BASIC_COLOR_LIST);
			colorSelector.setBounds(this.getWidth()-60, yPos, 50, 50);
			colorSelector.setListener(this);
			this.addView(colorSelector);
		
			yPos+=50;
		}
	}
	
	
}
*/




@Override
public void colorChanged(Color color)
{
//	colorSelector.getSelectedColor();
	
}










}
