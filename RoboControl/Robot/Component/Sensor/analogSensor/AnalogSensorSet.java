package de.hska.lat.robot.component.analogSensor;



import de.hska.lat.comm.remote.RemoteStream;
import de.hska.lat.robot.component.ComponentSet;
import de.hska.lat.robot.component.analogSensor.protocol.*;
import de.hska.lat.robot.component.detector.DetectorMetaData;




public class AnalogSensorSet extends  ComponentSet<AnalogSensor,AnalogSensorProtocol>  
{

	
/**
	 * 
	 */
	private static final long serialVersionUID = -4345841987121027713L;




public AnalogSensorSet(DetectorMetaData[] analogSensors,int range, AnalogSensorProtocol protocol)
{

	for (DetectorMetaData detector: analogSensors)
	{
		this.add(new AnalogSensor(detector, range, protocol));
	}
	
	
}	




public void  processSensorsValues(Stream_analogSensorsValues sensorValues)
{
	int index;
	AnalogSensor sensor;
	
	for (index=0;index<sensorValues.getValuesCount(); index++)
	{
		sensor=this.getComponentOnLocalId(index);
		if (sensor!=null)
		{
			sensor.setValue(sensorValues.getValue(index));
		}
	}

}



public void  processSensorValue(Msg_analogSensorValue msgValue)
{
	
	AnalogSensor sensor;

	sensor=this.getComponentOnLocalId(msgValue.getIndex());
	if (sensor!=null)
	{
		sensor.setValue(msgValue.getValue());
	}

}






public void  processSensorStatistics(Msg_analogSensorStatistics message)
{
	
	AnalogSensor sensor;

	sensor=this.getComponentOnLocalId(message.getIndex());
	if (sensor!=null)
	{
		sensor.setStatisticalMin(message.getMinValue());
		sensor.setStatisticalMax(message.getMaxValue());
	}

}




public void processSensorsSettings(Msg_analogSensorSettings message)
{
	AnalogSensor sensor;

	sensor=this.getComponentOnLocalId(message.getIndex());
	if (sensor!=null)
	{
		sensor.setSettings(message.getThreshold(), message.isInverse());
	}
	
}


void processStream(RemoteStream streamData)
{
System.out.println("mist");
	
}

void processStream(Stream_analogSensorsValues sensorValues)
{
	int index;
	AnalogSensor sensor;

	System.out.println("data");
	
	for (index=0;index<sensorValues.getValuesCount(); index++)
	{
		sensor=this.getComponentOnLocalId(index);
		if (sensor!=null)
		{
			sensor.setValue(sensorValues.getValue(index));
		}
	}

}


@Override
public boolean decodeStream(RemoteStream streamData)
{
	
if (streamData instanceof Stream_analogSensorsValues)
	{
		this.processSensorsValues((Stream_analogSensorsValues) streamData);
	}

	return false;
}



}
