package de.hska.lat.robot.component.template;


 
import de.hska.lat.robot.component.ComponentMetaData;
import de.hska.lat.robot.component.ComponentProtocol;
import de.hska.lat.robot.component.ComponentSettingsChangeNotifier;
import de.hska.lat.robot.component.RobotComponent;
import de.hska.lat.robot.component.template.protocol.Cmd_setTemplateSettings;

public class TemplateComponent extends RobotComponent<TemplateChangeNotifier,ComponentSettingsChangeNotifier, ComponentProtocol > 
{

	
	

	
// 2012.02.15	
public TemplateComponent(ComponentMetaData metaData, ComponentProtocol protocol) 
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
