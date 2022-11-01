package de.hska.lat.robot.component.generic.gyroscope;


import java.util.ArrayList;

import de.hska.lat.math.vector.AngularVector3D;
import de.hska.lat.robot.component.ComponentSettingsChangeNotifier;
import de.hska.lat.robot.component.ComponentMetaData;
import de.hska.lat.robot.component.sensor.Sensor;
import de.hska.lat.robot.value.ComponentValue;
import de.hska.lat.robot.value.angular.AngularValue;




public class Gyroscope <S extends ComponentSettingsChangeNotifier,  P extends GyroscopeProtocol> 
					extends  Sensor <GyroscopeChangeNotifier,S,P>  
 
		
{

	

	
	
	public static final int X_AXIS =0 ;
	public static final int Y_AXIS =1 ;
	public static final int Z_AXIS =2 ;
	
	protected AngularValue[] angularRates = new AngularValue[3]; 
	

	
public Gyroscope(ComponentMetaData metaData,P protocol) 
{
		super(metaData, protocol);
		this.angularRates[X_AXIS] = new AngularValue("x axis");
		this.angularRates[Y_AXIS] = new AngularValue("y axis");
		this.angularRates[Z_AXIS] = new AngularValue("z axis");
}



protected void setAngularRates(AngularVector3D angularRates)
{
	
	this.angularRates[Gyroscope.X_AXIS].setValue(angularRates.x);
	this.angularRates[Gyroscope.Y_AXIS].setValue(angularRates.y);
	this.angularRates[Gyroscope.Z_AXIS].setValue(angularRates.z);
	

	for (GyroscopeChangeNotifier listener:   sensorListener)
	{
		listener.rateChanged(this);	
	}	
}




public AngularVector3D getAngularRates()
{
	AngularVector3D angularRates;
	
	angularRates = new AngularVector3D(this.angularRates[Gyroscope.X_AXIS].getValue(),
				this.angularRates[Gyroscope.Y_AXIS].getValue(),
				this.angularRates[Gyroscope.Z_AXIS].getValue());
	


	return(angularRates);
}


@Override
public ArrayList<ComponentValue<?>> getDataValues()
{
	
	ArrayList<ComponentValue<?>> values = new ArrayList<ComponentValue<?>>();
	values.add(this.angularRates[X_AXIS]);
	values.add(this.angularRates[Y_AXIS]);
	values.add(this.angularRates[Z_AXIS]);
			
	return (values);
}







}
