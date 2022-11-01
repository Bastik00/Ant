package de.hska.lat.robot.component.sensor.bmp085;



import de.hska.lat.robot.component.ComponentSettingsChangeNotifier;
import de.hska.lat.robot.component.detector.DetectorMetaData;
import de.hska.lat.robot.component.generic.barometric.BarometricSensor;
import de.hska.lat.robot.component.generic.barometric.BarometricSensorProtocol;
import de.hska.lat.robot.value.barometric.BarometricValue;




public class Bmp085BarometricSensor extends BarometricSensor<ComponentSettingsChangeNotifier,BarometricSensorProtocol>  
{

		
	
public Bmp085BarometricSensor(DetectorMetaData metaData, BarometricSensorProtocol protocol)
{
	super( metaData, protocol);
	this.name = this.name+" barometer";
	this.presure = new BarometricValue(this.getComponentName(),300,1100); //,203.15f, 653.15f);
	
}

public BarometricValue getBarometricValue()
{
	return (this.presure);
}


}
