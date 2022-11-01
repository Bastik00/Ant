package de.hska.lat.robot.component.distanceSensor.gp2D;


 
import de.hska.lat.robot.component.ComponentSettingsChangeNotifier;
import de.hska.lat.robot.component.detector.DetectorMetaData;
import de.hska.lat.robot.component.distanceSensor.gp2D.protocol.Cmd_setGp2DSettings;
import de.hska.lat.robot.component.generic.distance.DistanceSensor;



public class Gp2D extends DistanceSensor<ComponentSettingsChangeNotifier,Gp2DProtocol> 
{

	

	public static final float GP2D_BEAM_WIDTH = 1.5f;
	
	public static final float GP2D_MAX_RANGE	= 1500;
	public static final float GP2D_MIN_RANGE	= 40;
	public static final float GP2D_MAX_GRANULARITY = 10;
	
	private int offset=0;
	private int gradient=0;
	

	
// 2012.02.15	
public Gp2D(DetectorMetaData metaData,int analogRange, Gp2DProtocol protocol) 
{
	super(metaData,GP2D_MIN_RANGE,GP2D_MAX_RANGE,GP2D_BEAM_WIDTH, protocol);
}




public void setSetup(int gradient, int offset, int distanceRange)
{
	
	this.gradient=gradient;
	this.offset = offset;
	this.distance.setDistanceRange(distanceRange);
	
	this.notifySetupChanged();
	
}

/**
 * gets the actual gradient value needed to calculate distance
 * @return  gradient value
 */
public int getGradient()
{
	return(this.gradient);
}


/**
 * gets the actual offset value needed to calculate distance
 * @return  offset value
 */
public int getOffset()
{
	return(this.offset);
}



/**
 * gets the actual maximal detectable distance
 * @return  maximal detectable distance
 */
public float getDistanceRange()
{
	return(this.distance.getDistanceRange());
}




public boolean remote_setSettings(int gradient, int offset,	int maxDistance)
{
	
	if (this.componentProtocol==null)
		return(false);
	

	return(sendData(Cmd_setGp2DSettings.getCommand(this.componentProtocol.cmdSetSettingsId, this.localId, gradient, offset, maxDistance)));
}



public boolean remote_getGp2DValue()
{
	// TODO Auto-generated method stub
	return false;
}








}
