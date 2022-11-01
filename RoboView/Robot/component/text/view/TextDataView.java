package de.hska.lat.robot.component.text.view;


import java.awt.Insets;

import javax.swing.JLabel;


import de.hska.lat.robot.component.text.Text;
import de.hska.lat.robot.component.view.ComponentView;
import de.hska.lat.robot.component.view.MissingComponentView;

public class TextDataView extends ComponentView
{

	
/**
	 * 
	 */
	private static final long serialVersionUID = 7943290152855004084L;

	protected static final int width = 250;
	protected static final int height = 90;
	
	
public TextDataView(Text text)
{
	super(text.getComponentName(), false);
	buildView();
}


@Override
protected void buildView()
{
super.buildView();

	
	// extended Mode

	
	
	
	JLabel description;
	
	
	Insets insets = this.getBorder().getBorderInsets(this);
	
	description = new JLabel();
	description.setBounds(insets.left+5, insets.top+5,100,20);
	
	
}
	


@Override
protected int getViewWidth()
{
	return(TextDataView.width);
}

@Override
protected int getViewHeight()
{
	return(TextDataView.height);
}



/**
 * create an new data view for a text component 
 * @param sensor
 * @return
 */

public static ComponentView createView(Text text)
{
	 
	if (text!=null)
	{
		return(new TextDataView(text));
	}
	else
	{
		return(new MissingComponentView(Text.class.getName()));
	}
}


}
