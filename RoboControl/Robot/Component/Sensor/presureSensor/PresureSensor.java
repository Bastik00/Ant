package de.hska.lat.robot.component.presureSensor;


import de.hska.lat.robot.component.ComponentMetaData;
import de.hska.lat.robot.component.ComponentProtocol;
import de.hska.lat.robot.component.ComponentSettingsChangeNotifier;
import de.hska.lat.robot.component.RobotComponent;








public abstract class PresureSensor<T  extends PresureChangeNotifier,S extends ComponentSettingsChangeNotifier, P extends ComponentProtocol> 
extends  RobotComponent <T,S,P> 

{

	
	protected PresureValue presure;
	
public PresureSensor(ComponentMetaData metaData, int rawRange, P protocol)
{
	super(metaData, protocol);
	// TODO Auto-generated constructor stub
}

}
