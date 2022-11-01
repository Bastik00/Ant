package de.hska.lat.robot.component.touchSensor;



import de.hska.lat.robot.component.ComponentProtocol;
import de.hska.lat.robot.component.ComponentSet;


import de.hska.lat.robot.component.touchSensor.protocol.Msg_touchSensorValue;
import de.hska.lat.robot.component.touchSensor.protocol.*;

import de.hska.lat.robot.component.detector.DetectorMetaData;





public class TouchSensorSet extends  ComponentSet<TouchSensor,ComponentProtocol>{

	
/**
	 * 
	 */
	private static final long serialVersionUID = -819859937416232966L;




public TouchSensorSet(DetectorMetaData[] touchSensors,int range,ComponentProtocol protocol)
{
	
	for (DetectorMetaData toutchSensor: touchSensors)
	{
		this.add(new TouchSensor(toutchSensor,range, protocol));
	}
	

}	




public void  processSensorsValues(Stream_touchSensorsValues sensorValues)
{
	int index;
	TouchSensor sensor;
	
	for (index=0;index<sensorValues.getParameterCount(); index++)
	{
		sensor=this.getComponentOnLocalId(index);
		if (sensor!=null)
		{
			sensor.setValue(sensorValues.getValue(index));
		}
	}

}

public void  processSensorValue(Msg_touchSensorValue msgValue)
{
	
	TouchSensor sensor;

	sensor=this.getComponentOnLocalId(msgValue.getIndex());
	if (sensor!=null)
	{
		sensor.setValue(msgValue.getValue());
	}

}




/*
public void  processSensorsMaxes(Msg_touchSensorsMaxes maxValues)
{
	int index;
	TouchSensor sensor;
	
	
	for (index=0;index<maxValues.getParameterCount(); index++)
	{
		sensor=this.getComponentOnLocalId(index);
		sensor.setStatisticalMax(maxValues.getMax(index));
	}	

}

public void  processSensorMax(Msg_touchSensorMax msgMax)
{
	
	TouchSensor sensor;

	sensor=this.getComponentOnLocalId(msgMax.getIndex());
	if (sensor!=null)
	{
		sensor.setStatisticalMax(msgMax.getMax());
	}

}


public void processSensorsMins(Msg_touchSensorsMins minValues)
{
	int index;
	TouchSensor sensor;

	
	for (index=0;index<minValues.getParameterCount(); index++)
	{
		sensor=this.getComponentOnLocalId(index);
		sensor.setStatisticalMin(minValues.getMin(index));
	}
	 
}



public void  processSensorMin(Msg_touchSensorMin msgMin)
{
	
	TouchSensor sensor;

	sensor=this.getComponentOnLocalId(msgMin.getIndex());
	if (sensor!=null)
	{
		sensor.setStatisticalMax(msgMin.getMin());
	}

}

*/

/*
public void processSensorsSettings(Msg_touchSensorSettings msgSettings)
{
	TouchSensor sensor;

	sensor=this.getComponentOnLocalId(msgSettings.getIndex());
	if (sensor!=null)
	{
	//	sensor.setSettings(msgSettings.geThreshold(), msgSettings.isInverse());
	}
	
}

*/


}