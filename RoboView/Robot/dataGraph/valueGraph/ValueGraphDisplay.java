package de.hska.lat.robot.dataGraph.valueGraph;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ComponentEvent;
import java.awt.event.ComponentListener;
import java.io.File;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import javax.swing.border.Border;

import de.hska.lat.robot.dataGraph.DataGraphControl;
import de.hska.lat.robot.dataGraph.DataGraphScreenControl;
import de.hska.lat.robot.dataGraph.DataGraphTimeScaleSettings;
import de.hska.lat.robot.value.flow.ValueFlow;
import de.hska.lat.robot.value.valueViewer.ValueGraphNotifier;
import de.hska.lat.robot.value.ComponentValue;

public class ValueGraphDisplay extends JPanel implements ComponentListener,
		ValueGraphResizeListener
{

	/**
	 * 
	 */
	private static final long serialVersionUID = 3449627351856264467L;

	protected static final int MARGIN = 2;
	protected static final int MIN_GRAPH_HEIGHT = 20;

	protected ValueGraphControl valueGraphControl; // control element on left
													// side
	protected ValueGraphGrid valueGraph; // grid with values
	protected ValueGraphScale valueScale; // scale for grid
	protected ValueGraphSettings settings;
	
	protected JButton btnClose;

	
	
	protected ValueGraphSeparator separator;

	protected int cursor;
	protected DataGraphScreenControl screenControl;
	protected DataGraphControl control;

	public ValueGraphDisplay(final ComponentValue<?> value,
			DataGraphTimeScaleSettings timeScaleSettings,
			DataGraphScreenControl screenControl, DataGraphControl control)
	{

		this.screenControl = screenControl;
		this.control = control;
		
		this.setLayout(null);

		this.separator = new ValueGraphSeparator(SwingConstants.HORIZONTAL);
		Border raisedbevel = BorderFactory.createRaisedBevelBorder();
		Border loweredbevel = BorderFactory.createLoweredBevelBorder();
		this.separator.setBorder(BorderFactory.createCompoundBorder(
				raisedbevel, loweredbevel));
		this.separator.setBounds(-5, 130, 600, 4);
		this.add(separator);
		this.separator.addResizeListener(this);

		this.btnClose = new JButton("\u2715");
		this.btnClose.setFont(new Font("Dialog", Font.PLAIN, 10));
		this.btnClose.setMargin(new java.awt.Insets(0, 0, 0, 0));
		this.btnClose.setBounds(this.getWidth() - this.btnClose.getPreferredSize().width, 0, this.btnClose.getPreferredSize().width, this.btnClose.getPreferredSize().height);
		this.btnClose.setToolTipText("Close Graph '" + value.getName() + "'");
		this.btnClose.addActionListener(new ActionListener()
		{
			
			@Override
			public void actionPerformed(ActionEvent e)
			{
				ValueGraphDisplay.this.control.removeValue(value);
			}
		});
		
		this.add(btnClose);
		
		this.settings = new ValueGraphSettings();
		
		this.valueGraphControl = new ValueGraphControl(value, this.settings);
		this.valueGraphControl.setLocation(0, 0);
		this.add(this.valueGraphControl);

		

		this.valueScale = new ValueGraphScale(this.settings);
		this.valueScale.setBounds(180, 0, 50, 130);
		this.add(this.valueScale);

		this.valueGraph = new ValueGraphGrid(timeScaleSettings, value, this.settings);

		this.valueGraph.setBounds(230, 0, 600, 130);
		this.valueGraph.addValueListener(this.valueGraphControl);
		this.add(this.valueGraph);


		
		this.setSize(100, 130);
		this.addComponentListener(this);

	}

	public void saveCsvData(File fileToSave) {
		valueGraph.saveCsvData(fileToSave);
	}
	
	public void loadCsvData(File fileToLoad) {
		valueGraph.loadCsvData(fileToLoad);
	}
	
	public void update()
	{
		this.valueGraph.update();
	}

	@Override
	public void componentHidden(ComponentEvent arg0)
	{
		// TODO Auto-generated method stub

	}

	@Override
	public void componentMoved(ComponentEvent arg0)
	{
		// TODO Auto-generated method stub

	}

	@Override
	public void componentResized(ComponentEvent arg0)
	{
		this.btnClose.setLocation((int) (this.getWidth() - this.btnClose.getSize().getWidth()), 0);
		this.valueScale.setBounds(180, MARGIN, 50, this.getHeight() - (MARGIN * 2) - this.separator.getHeight());
		this.valueGraph.setBounds(230, 2, this.getWidth()
				- this.valueGraphControl.getWidth() - 20, this.getHeight() - (MARGIN * 2) - this.separator.getHeight());

		
		
		this.separator.setBounds(-5, this.getHeight() - 4, this.getWidth() + 10,
				this.separator.getHeight());
	}

	@Override
	public void componentShown(ComponentEvent arg0)
	{
		// TODO Auto-generated method stub

	}

	public ValueFlow getValueFlow()
	{
		return (this.valueGraph.getValueFlow());
	}

	public ComponentValue<?> getValue()
	{
		return (this.valueGraph.getValue());
	}
	
	
	public void removeValueGraphNotifier(ValueGraphNotifier notifier) {
		valueGraph.removeValueListener(notifier);
	}
	
	public void addValueGraphNotifier(ValueGraphNotifier notifier) {
		valueGraph.addValueListener(notifier);
	}

	@Override
	public void heightChanged(int deltaY)
	{
			int height;

			height = this.getHeight();

			height += deltaY;

			if (height < MIN_GRAPH_HEIGHT)
			{
				height = MIN_GRAPH_HEIGHT;
			}

			this.setSize(this.getWidth(), height);
			this.screenControl.sizeChanged(this);		
	}

	@Override
	public void widthChanged(int deltaX)
	{
		// TODO Auto-generated method stub
		
	}

}
