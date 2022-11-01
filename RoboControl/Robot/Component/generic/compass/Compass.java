package de.hska.lat.robot.component.generic.compass;


import de.hska.lat.math.vector.FloatVector3D;
import de.hska.lat.robot.component.ComponentMetaData;
import de.hska.lat.robot.component.ComponentSettingsChangeNotifier;
import de.hska.lat.robot.component.sensor.Sensor;
import de.hska.lat.robot.value.magneticField.MagneticFieldValue;

public class Compass  <S extends ComponentSettingsChangeNotifier,  P extends CompassProtocol> 
				extends  Sensor <CompassChangeNotifier,S,P>   


{
	public static final String COMPONENT_NAME = "Cmp";
	
	
	public static final int X_AXIS =0 ;
	public static final int Y_AXIS =1 ;
	public static final int Z_AXIS =2 ;
	
	protected MagneticFieldValue[] magnetFieldRates = new MagneticFieldValue[3]; 
		
	
	public Compass(ComponentMetaData metaData, P protocol)
	{
		super(metaData, protocol);
		this.magnetFieldRates[X_AXIS] = new MagneticFieldValue("x axis");
		this.magnetFieldRates[Y_AXIS] = new MagneticFieldValue("y axis");
		this.magnetFieldRates[Z_AXIS] = new MagneticFieldValue("z axis");
	}
	
	public void setGeomagneticField(FloatVector3D values){
				
		this.magnetFieldRates[X_AXIS].setValue(values.x);
		this.magnetFieldRates[Y_AXIS].setValue(values.y);
		this.magnetFieldRates[Z_AXIS].setValue(values.z);
		
		for (CompassChangeNotifier listener:   sensorListener)
		{
			listener.rateChanged(this);	
		}
		
	}
	
	public FloatVector3D getGeomagneticField()
	{
		FloatVector3D  geomagneticField; 
		
		geomagneticField = new FloatVector3D(
				this.magnetFieldRates[X_AXIS].getValue(),
				this.magnetFieldRates[Y_AXIS].getValue(),
				this.magnetFieldRates[Z_AXIS].getValue()
				);
		


		return(geomagneticField);
	}

	
}
