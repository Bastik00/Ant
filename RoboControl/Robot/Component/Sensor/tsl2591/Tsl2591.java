package de.hska.lat.robot.component.sensor.tsl2591;


 
import de.hska.lat.robot.component.ComponentMetaData;
import de.hska.lat.robot.component.ComponentProtocol;
import de.hska.lat.robot.component.ComponentSettingsChangeNotifier;
import de.hska.lat.robot.component.RobotComponent;
import de.hska.lat.robot.component.template.protocol.Cmd_setTemplateSettings;

public class Tsl2591 extends RobotComponent<Tsl2591ChangeNotifier,ComponentSettingsChangeNotifier, ComponentProtocol > 
{

	
	

	
// 2012.02.15	
public Tsl2591(ComponentMetaData metaData, ComponentProtocol protocol) 
{
	super(metaData, protocol);
}




public boolean remote_setSettings(int userDefined)
{
	if (this.componentProtocol==null)
		return(false);
	
	
	return(sendData(Cmd_setTemplateSettings.getCommand(this.componentProtocol.cmdSetSettingsId,this.localId,
			userDefined)));
}

}
