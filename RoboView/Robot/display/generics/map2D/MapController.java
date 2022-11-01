package de.hska.lat.robot.display.generics.map2D;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JSlider;

import javax.swing.border.LineBorder;
import javax.swing.border.TitledBorder;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

import de.hska.lat.robot.display.generics.map2D.displayData.DisplayData;

public class MapController extends JPanel implements ActionListener
{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private static final String CMD_MOVE_LEFT 	= "cmdMoveLeft";
	private static final String CMD_MOVE_RIGHT 	= "cmdMoveRight";
	private static final String CMD_MOVE_UP 	= "cmdMoveUp";
	private static final String CMD_MOVE_DOWN 	= "cmdMoveDown";
	private static final String CMD_TO_CENTER	= "cmdToCenter";
	
	protected DisplayData displayData;
	
	protected JSlider scale;
	protected JSlider stepSize;
	
	
public MapController(DisplayData displayData)
{
	
	this.displayData = displayData;
	
	this.setLayout(null);
	this.setBorder(new TitledBorder("map control"));
	
	this.setPreferredSize(new Dimension(220,200));
	
	this.buildControl();
	
}


protected void buildControl()
{
	JLabel tmpLabel;
	
	Font font = new Font("Dialog", Font.BOLD, 30);

	this.addButton("\u2190", "move left", CMD_MOVE_LEFT, font, 10, 60);
	this.addButton("\u2191", "move up", CMD_MOVE_UP, font, 50, 20);
	this.addButton("\u2192", "move right", CMD_MOVE_RIGHT, font, 90, 60);
	this.addButton("\u2193", "move down", CMD_MOVE_DOWN, font, 50, 100);

	this.addButton("\u2302", "to center", CMD_TO_CENTER, font, 50, 60);
	
	
	tmpLabel = new JLabel("grid");
	tmpLabel.setBounds(130,5,50,25);
	this.add(tmpLabel);
	
	this.stepSize= new JSlider(JSlider.VERTICAL, 1, 100, 10);
	this.stepSize.setBounds(130,30,40,130);
	this.stepSize.setMajorTickSpacing(10);
	this.stepSize.setPaintTicks(true);

	this.add(this.stepSize);

	
	tmpLabel = new JLabel("scale");
	tmpLabel.setBounds(170,5,50,25);
	this.add(tmpLabel);
	
	this.scale= new JSlider(JSlider.VERTICAL, 1, 100, 1);
	this.scale.setBounds(170,30,40,130);
	this.scale.setMajorTickSpacing(10);
	this.scale.setPaintTicks(true);
	this.scale.addChangeListener(new ChangeListener()
	{

		@Override
		public void stateChanged(ChangeEvent arg0)
		{
		
			;
			MapController.this.displayData.setScale(MapController.this.scale.getValue()*10);
		}
		
	}
	);
	this.add(this.scale);
	
	/*
	tmpButton = new JButton("X");
	tmpButton.setFont(myFont);
	tmpButton.setBorder(new LineBorder(Color.GRAY,2));
	tmpButton.setBounds(50, 60, 40, 40);
	tmpButton.setToolTipText("move up");
	this.add(tmpButton);
	
	*/
	
}

protected void addButton(String text, String toolTipsText,String actionCommand,Font font, int xPos,int yPos)
{
	JButton tmpButton;
	
	
	tmpButton = new JButton(text);
	tmpButton.setFont(font);
	tmpButton.setActionCommand(actionCommand);
	tmpButton.setContentAreaFilled( false );
	tmpButton.setBorderPainted( false );


	tmpButton.setBorder(new LineBorder(Color.GRAY,2));
	tmpButton.setBounds(xPos, yPos, 40, 40);
	tmpButton.setToolTipText(toolTipsText);
	tmpButton.addActionListener(this);
	this.add(tmpButton);
}



@Override
public void actionPerformed(ActionEvent actionEvent) 
{
	
	String command;
	int granularity;
	granularity = 10;
	
	command = actionEvent.getActionCommand();


	if (command.equals(CMD_MOVE_DOWN))
	{
		displayData.moveDisplayYOffset(granularity);
	}
	else if (command.equals(CMD_MOVE_UP))
	{
		displayData.moveDisplayYOffset(-granularity);
	}
	else if (command.equals(CMD_MOVE_LEFT))
	{
		displayData.moveDisplayXOffset(-granularity);
	}

	else if (command.equals(CMD_MOVE_RIGHT))
	{
		displayData.moveDisplayXOffset(+granularity);
	}
	else if (command.equals(CMD_TO_CENTER))
	{
		displayData.setDisplayOffset(0, 0);
	}

	
	
	
}

}
