package de.hska.lat.robot.dataPacketLogger.viewer;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;


import javax.swing.JButton;

import javax.swing.ButtonGroup;
import javax.swing.JCheckBox;
import javax.swing.JFileChooser;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JSpinner;
import javax.swing.JToolBar;
import javax.swing.SpinnerNumberModel;

import javax.swing.border.LineBorder;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

import de.hska.lat.comm.packetLogger.DataPacketLoggerControl;
import de.hska.lat.comm.packetLogger.LoggedDataPacket.DisplayDataWidth_e;
import de.hska.lat.comm.packetLogger.LoggedDataPacket.DisplayFormat_e;


public class PacketLoggerToolBar extends JToolBar implements ActionListener, ChangeListener{


	


	/**
	 * 
	 */
	private static final long serialVersionUID = -8962606857272754408L;
	

	protected static final String RECORD_TEXT = "record";
	
	protected static final String STOP_TEXT = "stop";
	protected static final String CLEAR_TEXT = "clear";
	protected static final String EDIT_TEXT = "edit";
	
	protected static final String RAW_TEXT = "Raw : ";
	protected static final String BIT8_TEXT = "8  ";
	protected static final String BIT16_TEXT = "16  ";
	protected static final String BIT24_TEXT = "24  ";
	protected static final String BIT32_TEXT = "32  ";
	
	
	protected static final String NATIVE_TEXT = "Native  ";
	protected static final String DESCRIPTION_TEXT = "Description  ";
	
	protected static final String HEX_TEXT = "hex";
	

	
	
	
	private JButton recordButton;
	private JButton stopButton;
	
	protected JButton fileButton;
	
	protected JButton clearButton;
	
	protected SpinnerNumberModel logSize;
	protected JSpinner logSpinner;
	
	protected JCheckBox size8;
	protected JCheckBox size16;
	protected JCheckBox size24;
	protected JCheckBox size32;
	
	
	private JButton filterEdit;
	
	protected JCheckBox nativePure;
	protected JCheckBox nativeWithDescription;
	
	protected JCheckBox dataOutputHex;
	
	protected DataPacketLoggerControl listener;
	
public PacketLoggerToolBar()
{
	
	this.setName("Toolbar");

//	setLayout(new BorderLayout());
	this.setPreferredSize(new Dimension(400,40));
//	this.setBorder(new LineBorder(Color.black));
	buildToolBar();
}

	

private void buildToolBar()
{
	

	

	
	this.add(addControlPanel());
	this.add(addRawPanel());
//	this.addSeparator();
	this.add(this.addNativePanel());
	this.add(this.addFilePanel());
	this.add(this.addFilterPanel());
	
	this.logSize = new SpinnerNumberModel(10,0,1000,1 );
	this.logSpinner = new JSpinner(logSize);	
	this.logSpinner.addChangeListener(this);
	this.logSpinner.setPreferredSize(new Dimension(100,30));
	this.add(this.logSpinner);
	
	
	
	fileButton= new JButton("file");
	fileButton.addActionListener(this);
	this.add(fileButton);
	
	
	
}

protected JPanel addControlPanel()
{
	JPanel controlPanel;
	
	controlPanel = new JPanel();
	controlPanel.setBorder(new LineBorder(Color.DARK_GRAY,1));
	this.add(controlPanel);
	
	
	this.recordButton= new JButton(PacketLoggerToolBar.RECORD_TEXT);
	this.recordButton.addActionListener(this);
	controlPanel.add(this.recordButton);

	this.stopButton= new JButton(PacketLoggerToolBar.STOP_TEXT);
	this.stopButton.addActionListener(this);
	this.stopButton.setEnabled(false);
	controlPanel.add(this.stopButton);
	
	this.clearButton =new JButton(PacketLoggerToolBar.CLEAR_TEXT);
	this.clearButton.addActionListener(this);
	this.clearButton.setEnabled(true);
	controlPanel.add(this.clearButton);
	
	return(controlPanel);
}


protected JPanel addRawPanel()
{
	JPanel rawPanel;
	JLabel tmpLabel;
	
	rawPanel = new JPanel();
	rawPanel.setBorder(new LineBorder(Color.DARK_GRAY,1));
	this.add(rawPanel);
	
	tmpLabel = new JLabel(PacketLoggerToolBar.RAW_TEXT); 
	rawPanel.add(tmpLabel);
	
	this.size8 = new JCheckBox(PacketLoggerToolBar.BIT8_TEXT);
	this.size8.addActionListener(new ActionListener(){

		@Override
		public void actionPerformed(ActionEvent e)
		{
			PacketLoggerToolBar.this.setRawFormat(DisplayDataWidth_e.WIDTH_8);
		}
	
	});
	this.size8.setEnabled(true);
	rawPanel.add(this.size8);
	
	
	this.size16 = new JCheckBox(PacketLoggerToolBar.BIT16_TEXT);
	this.size16.addActionListener(new ActionListener(){

		@Override
		public void actionPerformed(ActionEvent e)
		{
			PacketLoggerToolBar.this.setRawFormat(DisplayDataWidth_e.WIDTH_16);
		}
	
	});
	this.size16.setEnabled(true);
	rawPanel.add(this.size16);
	
	this.size24 = new JCheckBox(PacketLoggerToolBar.BIT24_TEXT);
	this.size24.addActionListener(new ActionListener(){

		@Override
		public void actionPerformed(ActionEvent e)
		{
			PacketLoggerToolBar.this.setRawFormat(DisplayDataWidth_e.WIDTH_24);
		}
	
	});
	this.size24.setEnabled(true);
	rawPanel.add(this.size24);
	
	
	this.size32 = new JCheckBox(PacketLoggerToolBar.BIT32_TEXT);
	this.size32.addActionListener(new ActionListener(){

		@Override
		public void actionPerformed(ActionEvent e)
		{
			PacketLoggerToolBar.this.setRawFormat(DisplayDataWidth_e.WIDTH_32);
		}
	
	});
	this.size32.setEnabled(true);
	rawPanel.add(this.size32);
	
	
	ButtonGroup dataWidth = new ButtonGroup();
	dataWidth.add(this.size8);
	dataWidth.add(this.size16);
	dataWidth.add(this.size24);
	dataWidth.add(this.size32);
	
	
	this.dataOutputHex = new JCheckBox(PacketLoggerToolBar.HEX_TEXT);
	this.dataOutputHex.addActionListener(new ActionListener(){

		@Override
		public void actionPerformed(ActionEvent actionEvent)
		{
			if (PacketLoggerToolBar.this.listener!=null)
			{
				if (PacketLoggerToolBar.this.dataOutputHex.isSelected())
				{
					PacketLoggerToolBar.this.listener.setDisplayFormat(DisplayFormat_e.HEXADECIMAL);	
				}
				else
				{
					PacketLoggerToolBar.this.listener.setDisplayFormat(DisplayFormat_e.DECIMAL);
				}
			}
				
		}
		
	}
	);
	this.dataOutputHex.setEnabled(true);
	rawPanel.add(this.dataOutputHex);
	
	
	return(rawPanel);
}

protected JPanel addNativePanel()
{
	JPanel nativePanel;
	
	nativePanel = new JPanel();
	nativePanel.setBorder(new LineBorder(Color.DARK_GRAY,1));
	this.add(nativePanel);
	
//	tmpLabel = new JLabel(PacketLoggerToolBar.DESCRIPTION_TEXT); 
//	nativePanel.add(tmpLabel);
	
	this.nativePure = new JCheckBox(PacketLoggerToolBar.NATIVE_TEXT);
	this.nativePure.addActionListener(new ActionListener(){

		@Override
		public void actionPerformed(ActionEvent e)
		{
			if (PacketLoggerToolBar.this.nativePure.isSelected())
			{
				PacketLoggerToolBar.this.listener.setDisplayFormat(DisplayFormat_e.NATIVE);	
			}
			else
			{
				PacketLoggerToolBar.this.nativePure.setSelected(true);
				//PacketLoggerToolBar.this.listener.setDisplayFormat(DisplayFormat_e.NATIVE);	
			}
		}
		{
		
		}
	
	});
	this.nativePure.setEnabled(true);
	nativePanel.add(this.nativePure);
	
	
	this.nativeWithDescription = new JCheckBox(PacketLoggerToolBar.DESCRIPTION_TEXT);
	this.nativeWithDescription.addActionListener(new ActionListener()
	{
		@Override
		public void actionPerformed(ActionEvent e)
		{
			if (PacketLoggerToolBar.this.nativeWithDescription.isSelected())
			{
				PacketLoggerToolBar.this.nativePure.setSelected(true);
				PacketLoggerToolBar.this.listener.setDisplayFormat(DisplayFormat_e.NATIVE_WITH_DESCRIPTION);	
			}
			else
			{
				PacketLoggerToolBar.this.listener.setDisplayFormat(DisplayFormat_e.NATIVE);	
			}
		}
	});
	this.nativeWithDescription.setEnabled(true);
	nativePanel.add(this.nativeWithDescription);
	

	
	return(nativePanel);
}




protected JPanel addFilePanel()
{
	JPanel filePanel;
	
	filePanel = new JPanel();
	filePanel.setBorder(new LineBorder(Color.DARK_GRAY,1));
	this.add(filePanel);
	
	return(filePanel);
	
}

protected JPanel addFilterPanel()
{
	JPanel filterPanel;
	
	
	filterPanel = new JPanel();
	filterPanel = new JPanel();
	filterPanel.setBorder(new LineBorder(Color.DARK_GRAY,1));
	this.add(filterPanel);
	
	this.filterEdit= new JButton(PacketLoggerToolBar.EDIT_TEXT);
	this.filterEdit.addActionListener(new ActionListener(){

		@Override
		public void actionPerformed(ActionEvent arg0)
		{
			//PacketLoggerToolBar.this.getDesktopPane().add(new DataPacketFilterBlockConfigViewer());
		//	PacketLoggerToolBar.this.getParent().add(new DataPacketFilterBlockConfigViewer());
		}
		
	});
	//this.filterEdit.setEnabled(false);
	filterPanel.add(this.filterEdit);
	
	
	return(filterPanel);
}


public void setListener(DataPacketLoggerControl listener)
{
	this.listener = listener;
}




public void setRawFormat(DisplayDataWidth_e dataWidth)
{
	if (this.listener!=null)
	{
		if (this.dataOutputHex.isSelected())
		{
			this.listener.setDisplayFormat(DisplayFormat_e.HEXADECIMAL);
		}
		else
		{
			this.listener.setDisplayFormat(DisplayFormat_e.DECIMAL);
		}
		this.nativePure.setSelected(false);
		this.listener.setDataWidth(dataWidth);
	}
}

@Override
public void actionPerformed(ActionEvent actionEvent) 
{
	if (actionEvent.getSource()==recordButton)
	{
		this.recordButton.setEnabled(false);
		this.stopButton.setEnabled(true);
		this.listener.startRecording();
	}
	else if (actionEvent.getSource()==stopButton)
	{
		this.recordButton.setEnabled(true);
		this.stopButton.setEnabled(false);
		this.listener.stopRecording();
	}
	else if(actionEvent.getSource()==this.clearButton)
	{
		if (this.listener!=null)
			this.listener.clearList();
	}

	
	else if(actionEvent.getSource()==this.size8)
	{
		if (this.listener!=null)
		{
			this.listener.setDataWidth(DisplayDataWidth_e.WIDTH_8);
	//		this.listener.setDisplayFormat(format)
		}
		
		this.nativePure.setSelected(false);
	}
	else if(actionEvent.getSource()==this.size16)
	{
		if (this.listener!=null)
		{
		this.listener.setDataWidth(DisplayDataWidth_e.WIDTH_16);
		}
		this.nativePure.setSelected(false);
	}
	else if(actionEvent.getSource()==this.size24)
	{
		if (this.listener!=null)
		this.listener.setDataWidth(DisplayDataWidth_e.WIDTH_24);
	}
	else if(actionEvent.getSource()==this.size32)
	{
		if (this.listener!=null)
		this.listener.setDataWidth(DisplayDataWidth_e.WIDTH_32);
	}
	
	
		
	else if(actionEvent.getSource()==fileButton)
	{
	//	File projectPath;
		
		//projectPath= new File(projectData.getProjectPath());
		
		JFileChooser MyFileChoser=new JFileChooser();
		//MyFileChoser.setSelectedFile(projectPath);
		
		MyFileChoser.setFileSelectionMode(JFileChooser.FILES_ONLY);
		

		MyFileChoser.showDialog(this,"select");
	}
}



@Override
public void stateChanged(ChangeEvent event)
{

	
	Object source = event.getSource();
	
	if (this.logSpinner == source)
	{
		this.listener.setNewLogSize(((Integer)logSize.getValue()).intValue());
	}
	
}

}