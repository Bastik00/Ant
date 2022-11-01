package de.hska.lat.robot.tools.sharpIr;

import java.awt.Color;

import javax.swing.JInternalFrame;
import javax.swing.JLabel;
import javax.swing.JSpinner;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.border.LineBorder;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

public class SharpIrCalculator extends JInternalFrame implements ChangeListener{
	

	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JSpinner distanceA;
	private JSpinner distanceB;
	
	private JSpinner valueA;
	private JSpinner valueB;
	
	private JTextField offset;
	private JTextField gradient;

	
public SharpIrCalculator()
{
	super("",false,true,false);
	buildWindow();
	this.setSize(200,200);
	this.show();
}
	

private void buildWindow()
{
	JLabel tmpLabel;
	
	this.setLayout(null);
	
	
	tmpLabel=new JLabel("distance a");
	tmpLabel.setBounds(10, 10, 80, 20);
	this.add(tmpLabel);
	
	distanceA= new JSpinner();
	distanceA.setBounds(100, 10, 80, 20);
	distanceA.addChangeListener(this);
	this.add(distanceA);

	tmpLabel=new JLabel("distance b");
	tmpLabel.setBounds(10, 30, 80, 20);
	this.add(tmpLabel);
	
	distanceB= new JSpinner();
	distanceB.setBounds(100, 30, 80, 20);
	distanceB.addChangeListener(this);
	this.add(distanceB);

	
	tmpLabel=new JLabel("value a");
	tmpLabel.setBounds(10, 50, 80, 20);
	this.add(tmpLabel);
	
	valueA= new JSpinner();
	valueA.setBounds(100, 50, 80, 20);
	valueA.addChangeListener(this);
	this.add(valueA);
	
	tmpLabel=new JLabel("value b");
	tmpLabel.setBounds(10, 70, 80, 20);
	this.add(tmpLabel);
	
	valueB= new JSpinner();
	valueB.setBounds(100, 70, 80, 20);
	valueB.addChangeListener(this);
	this.add(valueB);
	

	
	tmpLabel=new JLabel("offset");
	tmpLabel.setBounds(10, 90, 80, 20);
	this.add(tmpLabel);
	
	offset=new JTextField();
	offset.setBorder(new LineBorder(Color.BLACK));
	offset.setBounds(100, 90, 80, 20);
	offset.setHorizontalAlignment(SwingConstants.CENTER);
	this.add(offset);
		
	tmpLabel=new JLabel("gradient");
	tmpLabel.setBounds(10, 110, 80, 20);
	this.add(tmpLabel);
	
	gradient=new JTextField();
	gradient.setBorder(new LineBorder(Color.BLACK));
	gradient.setBounds(100, 110, 80, 20);
	gradient.setHorizontalAlignment(SwingConstants.CENTER);
	this.add(gradient);	
	
	

}


@Override
public void stateChanged(ChangeEvent arg0) 
{
	long distanceA;
	long distanceB;
	long valueA;
	long valueB;
	long tmpValue;
	
	distanceA=(Integer) this.distanceA.getValue();
	distanceB=(Integer) this.distanceB.getValue();
	
	valueA=(Integer) this.valueA.getValue();
	valueB=(Integer) this.valueB.getValue();
	try{
	tmpValue = (distanceB*distanceA);
	tmpValue*=(valueB-valueA);
	tmpValue<<=8;
	tmpValue/=(distanceA-distanceB);

	gradient.setText(String.valueOf(tmpValue));
	
	
	
	tmpValue = (distanceB*valueB)-(distanceA*valueA);
	tmpValue <<=8;
	tmpValue/=(distanceB-distanceA);
	
	offset.setText(String.valueOf(tmpValue));	
	}
	catch (Exception e)
	{
		gradient.setText("invalid");
		offset.setText("invalid");	
	}

}

/*
 * 	tmpValue*=(valueB-valueA);

	tmpValue<<=8;

	tmpValue/=(distanceA-distanceB);

	SharpSensorData[sensor].gradient= tmpValue;	

	//A = (Vb-Va) (Db Da)/(Da-Db)



	// B = (D'X' - DX)/(D' - D)
	tmpValue = (distanceB*valueB)-(distanceA*valueA);
	tmpValue <<=8;
	tmpValue/=(distanceB-distanceA);
 */


}
