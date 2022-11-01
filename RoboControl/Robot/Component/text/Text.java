package de.hska.lat.robot.component.text;


 
import de.hska.lat.robot.component.ComponentMetaData;
import de.hska.lat.robot.component.ComponentSettingsChangeNotifier;

import de.hska.lat.robot.component.RobotComponent;
import de.hska.lat.robot.component.template.protocol.Cmd_setTemplateSettings;
import de.hska.lat.robot.component.text.protocol.Cmd_getText;

public class Text extends RobotComponent<TemplateChangeNotifier,ComponentSettingsChangeNotifier, TextProtocol > 
{

	
	

	
// 2012.02.15	
public Text(ComponentMetaData metaData, TextProtocol protocol) 
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



public boolean remote_getText()
{
	if (this.componentProtocol==null)
		return(false);
	
	
	return(sendData(Cmd_getText.getCommand(this.componentProtocol.cmdGetText,this.localId)));
}



}
