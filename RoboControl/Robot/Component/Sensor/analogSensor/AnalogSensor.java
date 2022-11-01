package de.hska.lat.robot.component.analogSensor;
 



import de.hska.lat.robot.component.ComponentSettingsChangeNotifier;
import de.hska.lat.robot.component.analogDetector.AnalogDetector;
import de.hska.lat.robot.component.analogSensor.protocol.Cmd_getAnalogSensorValue;
import de.hska.lat.robot.component.analogSensor.protocol.Cmd_setAnalogSensorSettings;
import de.hska.lat.robot.component.detector.DetectorMetaData;



public class AnalogSensor extends  AnalogDetector<AnalogSensorChangeNotifier,ComponentSettingsChangeNotifier, AnalogSensorProtocol > 
{


	

	
public AnalogSensor(DetectorMetaData metaData,int rawRange, AnalogSensorProtocol protocol) 
{
		super(metaData, rawRange, protocol);
}





public boolean remote_getValue()
{
	if (this.componentProtocol==null)
		return(false);
	
	
	return(sendData(Cmd_getAnalogSensorValue.getCommand(this.componentProtocol.getAnalogSensorValueCommandId,this.localId)));
}


public boolean remote_getStatistics()
{
	if (this.componentProtocol==null)
		return(false);
	
	
	return(sendData(Cmd_getAnalogSensorValue.getCommand(this.componentProtocol.getAnalogSensorValueCommandId,this.localId)));
}



public boolean remote_setStettings(int threshold,boolean inverse)
{
	if (this.componentProtocol==null)
		return(false);
	
	
	return(sendData(Cmd_setAnalogSensorSettings.getCommand(this.componentProtocol.cmdSetSettingsId,this.localId, threshold, inverse)));
}


public void setInverse(boolean inverse)
{
	this.inverse=inverse;
	
}













}


