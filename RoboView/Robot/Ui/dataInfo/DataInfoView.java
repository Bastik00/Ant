package de.hska.lat.robot.ui.dataInfo;

import javax.swing.JPanel;

//import de.hska.lat.settings.DisplaySettings;

public class DataInfoView extends JPanel
{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;


//	protected Paint paint = new Paint();
	

	protected int textColor;
	
	protected int width;
	protected int height;
	

	
public DataInfoView() 
{
	super();
	this.init();
}





public void init()
{

	
	//paint.setTextSize(25);
//	paint.setAntiAlias(true);

	//this.setTextColor(DisplaySettings.getTextColor());
}



public void setPosition(int x, int y)
{
	
	//this.layout(x, y, x+this.width, y+this.height);

}




public void setTextColor(int color)
{
	this.textColor=color;
//	paint.setColor(color);
}





}
