package de.hska.lat.robot.component.sensor.mlx90620;

public class Mlx9020IrFrame
{

	protected float [] [] irData = new float [16] [4];

	
	
	
	
	public void setPixel(int row, int column, float value)
	{
		this.irData[column] [row] = value;
	}
	
	
	
	
	public Mlx9020IrFrame getCopy()
	{
		int column;
		int row;
		
		Mlx9020IrFrame copy;
		copy = new Mlx9020IrFrame();
		
		
		for (column =0;column<this.irData.length ; column++)
		{
			for (row=0;row< this.irData[0].length; row ++)
				copy.irData[column] [row] = this.irData[column] [row];
		}
		return (copy);
	}  	
	
	
	
	
	
	
}
