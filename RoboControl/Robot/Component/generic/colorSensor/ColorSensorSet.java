package de.hska.lat.robot.component.generic.colorSensor;



import de.hska.lat.color.HsvColor;
import de.hska.lat.color.RgbColor;
import de.hska.lat.comm.remote.RemoteMessage;
import de.hska.lat.comm.remote.RemoteStream;
import de.hska.lat.robot.component.ComponentSet;

import de.hska.lat.robot.component.generic.colorSensor.protocol.Msg_hsvColor;
import de.hska.lat.robot.component.generic.colorSensor.protocol.Msg_rgbColor;
import de.hska.lat.robot.component.generic.colorSensor.protocol.Stream_hsvColors;
import de.hska.lat.robot.component.generic.colorSensor.protocol.Stream_rgbColors;




/**
 * Super class for Sharp Gp2 Sensor sets.  
 * 
 * @author Oktavian Gniot
 *
 */
public abstract class ColorSensorSet<T extends ColorSensor<?,?>,P extends ColorSensorProtocol>  extends ComponentSet<T,P>
{

	
/**
	 * 
	 */
	private static final long serialVersionUID = 1985714891299637009L;

	

public void processHsvColors(Stream_hsvColors remoteStream) 
{
	T sensor;
	int index;
	

	for (index=0;index<remoteStream.getParameterCount(); index++)
	{
		sensor=this.getComponentOnLocalId(index);


		
		if (sensor!=null)
		{
			HsvColor color;
			
			color = remoteStream.getColor(index);
			
			sensor.setColor(color.h,
					color.s,
					color.v
					);
		}

	}
}

public void processRgbColors(Stream_rgbColors remoteStream) 
{
	T sensor;
	int index;
	

	for (index=0;index<remoteStream.getParameterCount(); index++)
	{
		sensor=this.getComponentOnLocalId(index);

		if (sensor!=null)
		{
			RgbColor color = remoteStream.getColor(index);
			System.out.println("RGB colors");
			System.out.println("color :"+color.getAsHsv());
		}

	}
}


public void processHsvColor(Msg_hsvColor remoteData)
{
	T sensor;
	int index;
	
	index=remoteData.getIndex();
	sensor=this.getComponentOnLocalId(index);
	if (sensor!=null)
	{
		HsvColor color;
		
		color = remoteData.getColor();
		
		sensor.setColor(color.h,
				color.s,
				color.v
				);

		System.out.println("HSV color");
		//sensor.setPresure(sensorPresure.getBarometricPresure());	
	}
	
}

public void processRgbColor(Msg_rgbColor remoteData)
{
	T sensor;
	int index;
	
	index=remoteData.getIndex();
	sensor=this.getComponentOnLocalId(index);
	if (sensor!=null)
	{
		RgbColor color = remoteData.getColor();
		System.out.println("RGB color");
		System.out.println("color :"+color.getAsHsv());
		//sensor.setPresure(sensorPresure.getBarometricPresure());	
	}
	
}


@Override
public boolean decodeMessage(RemoteMessage remoteData)
{
	if (remoteData instanceof Msg_hsvColor)
	{
		this.processHsvColor((Msg_hsvColor)remoteData);
	}
	else if (remoteData instanceof Msg_hsvColor)
	{
		this.processRgbColor((Msg_rgbColor)remoteData);
	}
	else
	{
		super.decodeMessage(remoteData);
	}
	
	return false;
}





@Override
public boolean decodeStream(RemoteStream remoteData)
{
	if (remoteData instanceof Stream_hsvColors)
	{
		this.processHsvColors((Stream_hsvColors)remoteData);

	}
	else if (remoteData instanceof Stream_rgbColors)
	{
		this.processRgbColors((Stream_rgbColors)remoteData);

	}
	else 
	{
		super.decodeStream(remoteData);	
	}
	
	return(true);	
}

}
