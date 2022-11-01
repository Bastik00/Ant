package de.hska.lat.behavior.arbiter.view;

import java.awt.Color;
import java.awt.Graphics;

import javax.swing.JLabel;
import javax.swing.border.LineBorder;
import javax.swing.border.TitledBorder;

import de.hska.lat.behavior.arbiter.Arbiter;
import de.hska.lat.behavior.arbiter.SuppressorNode;
import de.hska.lat.behavior.arbiter.action.ArbiterAction;
import de.hska.lat.behavior.behavior.RobotBehavior;
import de.hska.lat.robot.RobotSettings;

public class SuppressorNodeViewer extends ArbiterViewer<SuppressorNode>
{

	/**
	 * 
	 */
	private static final long serialVersionUID = -3473626373855044307L;

	
	JLabel action;
	JLabel suppressionAction;
	JLabel outputAction;
	
public SuppressorNodeViewer(RobotBehavior<?> behavior, SuppressorNode arbiter, RobotSettings settings)
{
	super(behavior, arbiter, settings);


	this.setBorder(new TitledBorder("S: "+arbiter.getName()));
	this.setLayout(null);
	this.setSize(260, 100);
	this.update((SuppressorNode) arbiter);
}



protected void build()
{

	
	
	this.suppressionAction = new JLabel();
	
	this.suppressionAction.setBounds(10,30,100,25);
	this.suppressionAction.setBorder(new LineBorder(Color.black,1));
	this.add(this.suppressionAction);
	

	

	this.action = new JLabel();
	
	this.action.setBounds(10,60,100,25);
	this.action.setBorder(new LineBorder(Color.black,1));
	this.add(this.action);
	

	
	this.outputAction = new JLabel();
	
	this.outputAction.setBounds(150,60,100,25);
	this.outputAction.setBorder(new LineBorder(Color.black,1));
	this.add(this.outputAction);
	

	

}


@Override 
public void paint(Graphics g)
{
	super.paint(g);
	Color color;
	
	color = g.getColor();
	
	
	
	if (((SuppressorNode)arbiter).getSuppressorAction()!=null)
	{
		g.setColor(Color.RED);
	}
	else
	{
		g.setColor(Color.BLACK);
	}
	
	
	g.drawLine(110, 40, 200, 40);
	g.drawLine(200, 40, 200, 60);
	
	g.setColor(Color.BLACK);
	
	g.drawLine(110, 70, 150, 70);
	
	g.setColor(color);
}






public void update(SuppressorNode arbiter)
{
	ArbiterAction action;
	ArbiterAction supressionAction;
	
	action = arbiter.getAction();
	
	if (action!=null)
	{
		this.action.setText(action.getName());
	}
	else
	{
		this.action.setText("-");
		
	}
	
	supressionAction = arbiter.getSuppressorAction();
	
	if (supressionAction!=null)
	{
		this.suppressionAction.setText(supressionAction.getName());
		this.suppressionAction.setForeground(Color.red);
		this.action.setForeground(Color.DARK_GRAY);
		
		this.outputAction.setForeground(Color.red);
		this.outputAction.setText(supressionAction.getName());
	}
	else
	{
		this.suppressionAction.setText("-");
		this.suppressionAction.setForeground(Color.gray);
		this.action.setForeground(Color.BLACK);

		this.outputAction.setForeground(Color.DARK_GRAY);

		if (action!=null)
		{
			this.outputAction.setText(action.getName());
		}
		else
		{
			this.outputAction.setText("-");
			
		}
	}
	
	this.invalidate();
	this.repaint();
}


@Override
public void arbiterChanged(Arbiter arbiter)
{
	 update((SuppressorNode) arbiter);
}


}
