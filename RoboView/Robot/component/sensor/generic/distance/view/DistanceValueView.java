package de.hska.lat.robot.component.generic.distance.view;






import java.awt.Insets;

import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.SwingConstants;
import javax.swing.border.BevelBorder;
import javax.swing.border.SoftBevelBorder;


import de.hska.lat.robot.value.ComponentValue;
import de.hska.lat.robot.value.distance.DistanceValue;
import de.hska.lat.robot.value.view.ValueView;
import de.hska.lat.robot.value.view.MissingValueView;




public class DistanceValueView extends  ValueView<DistanceValue>
{



	/**
	 * 
	 */
	private static final long serialVersionUID = 7202219643461320451L;

	private JLabel distance;	

	
	DistanceValue value;
	
	protected static final int width = 90;
	protected static final int height = 30;
	
	ImageIcon icon; 
public DistanceValueView(DistanceValue value, boolean embedded) 
{
	super(value, embedded);
	
	value.addListener(this);
	
	this.showName = false;
	
	 icon = new ImageIcon("gfx\\light.png");
	
	buildView();
	
	
	
	

}

/*
 * ImageIcon icon = createImageIcon("gfx/light.png",
                                 "a pretty but meaningless splat");
label1 = new JLabel("Image and Text", icon, JLabel.CENTER);
...
label3 = new JLabel(icon);

 * (non-Javadoc)
 * @see de.hska.lat.robot.component.ComponentView#buildView()
 */
@Override
protected void buildView()
{

	super.buildView();

	Insets insets = this.getBorder().getBorderInsets(this);
	
	
/*	JLabel tmpLabel = new JLabel(icon);
	tmpLabel.setBounds(insets.left+5,insets.top+5,30,30);
	this.add(tmpLabel);
	*/
	
	this.distance = new JLabel("-");
	this.distance.setBounds(insets.left+5,insets.top+5,80,20);
	this.distance.setBorder(new SoftBevelBorder(BevelBorder.LOWERED));

	this.distance.setHorizontalAlignment(SwingConstants.CENTER);
	this.add(this.distance);

	
}

@Override
public void valueChanged(ComponentValue<?> componentValue)
{
	int distance;
	if (componentValue.isValid())
	{
		distance = (int) componentValue.getValue();
		this.distance.setText(distance+" mm");
	}
	else
	{
		this.distance.setText("-");
	}
	
}



public static ValueView<?> createView(DistanceValue value, boolean embedded)
{
	


	if (value!=null)
	{
		return( new DistanceValueView(value, embedded));
	}
	
	else
	{
		return(new MissingValueView(DistanceValue.class.getName(), embedded));
	}
	
}



@Override
protected int getViewWidth()
{
	return(DistanceValueView.width);
}

@Override
protected int getViewHeight()
{
	return(DistanceValueView.height);
}

protected void finalize()
{
	this.value.removeListener(this);
}








}
