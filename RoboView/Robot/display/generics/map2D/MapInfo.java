package de.hska.lat.robot.display.generics.map2D;



import java.awt.Dimension;

import javax.swing.JLabel;
import javax.swing.border.TitledBorder;

import de.hska.lat.robot.display.generics.map2D.displayData.DisplayData;
import de.hska.lat.robot.display.generics.map2D.displayData.DisplayDataListener;
import de.hska.lat.robot.ui.dataInfo.DataInfoView;



public class MapInfo extends  DataInfoView implements DisplayDataListener
{


	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	
	protected static String noDataString = "no data";
	
	
	protected JLabel scale;
	protected JLabel xOffset;
	protected JLabel yOffset;
	protected JLabel gridSize;

	
	
	
public MapInfo(DisplayData displayData)
{
	
	
	displayData.addListener(this);
	
	this.setLayout(null);
	this.setBorder(new TitledBorder("map info"));
	
	this.setPreferredSize(new Dimension(210,160));
	
	this.buildInfo();
	
	this.displayDatad(displayData);
	
}





public void buildInfo()
{
	
	this.scale = new JLabel();
	this.scale.setBounds(10,20,100,40);
	this.scale.setBorder(new TitledBorder("scale"));
	this.add(this.scale);
	
	
	this.xOffset = new JLabel();
	this.xOffset.setBounds(10,60,100,40);
	this.xOffset.setBorder(new TitledBorder("x offset"));
	this.add(this.xOffset);
	
	this.yOffset = new JLabel();
	this.yOffset.setBounds(10,100,100,40);
	this.yOffset.setBorder(new TitledBorder("y offset"));
	this.add(this.yOffset);
	

	this.gridSize = new JLabel();
	this.gridSize.setBounds(110,20,100,40);
	this.gridSize.setBorder(new TitledBorder("grid size"));
	this.add(this.gridSize);
	
	
}


protected void displayDatad(DisplayData displayData)
{
	
	if (displayData!=null)
	{
		this.scale.setText(""+displayData.getScale());
		this.xOffset.setText(""+displayData.getDisplayXOffset());
		this.yOffset.setText(""+displayData.getDisplayYOffset());
		this.gridSize.setText(displayData.getGridSize()+"px");
	}
	else
	{
		this.scale.setText(noDataString);
		this.xOffset.setText(noDataString);
		this.yOffset.setText(noDataString);	
		this.gridSize.setText(noDataString);	
	}
}


@Override
public void displayDataChanged(DisplayData displayData) 
{

	this.displayDatad(displayData);


	
}

/*
@Override
protected void onDraw(Canvas canvas) 
{
		super.onDraw(canvas);

		paint.setColor(0xff5090ff);
		
		if (displayData==null)
		{
			canvas.drawText(noDataString , 10, 30, paint);
		}
		else
		{
			canvas.drawText("S : "+(displayData.getScale())+" mm" , 10, 30, paint);
			canvas.drawText("x : "+displayData.getDisplayXOffset()+" cm" , 10, 60, paint);
			canvas.drawText("y : "+displayData.getDisplayYOffset()+" cm" , 10, 90, paint);
			
		}
}
*/



}

