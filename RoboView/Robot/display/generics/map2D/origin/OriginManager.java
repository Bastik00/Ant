package de.hska.lat.robot.display.generics.map2D.origin;



import java.awt.Color;



import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;


import javax.swing.JColorChooser;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JSpinner;
import javax.swing.SpinnerNumberModel;
import javax.swing.border.LineBorder;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;


import de.hska.lat.robot.displayFrame.DisplayFrame;
import de.hska.lat.robot.ui.settings.UiSettings;

public class OriginManager extends DisplayFrame implements MouseListener, ChangeListener
{

	/**
	 * 
	 */
	private static final long serialVersionUID = 8766114786158944041L;

	/**
	 * 
	 */
	

	
	protected static final String FRAME_NAME = "Origin Manager";
	
	protected final static String COLOR_TEXT = "color";
	protected final static String WIDTH_TEXT = "width";
	protected final static String SIZE_TEXT = "size";
	
	
	
	protected JPanel colorLabel;
	
	protected MapOriginDrawer originDrawer;	
	
	protected JSpinner thicknesSpinner;
	protected JSpinner sizeSpinner;
	
	protected static final String sizeKey = ".size";
	protected static final String widthKey = ".width";
	protected static final String colorKey = ".color";
	
	
public OriginManager(MapOriginDrawer originDrawer)
{
	super(OriginManager.FRAME_NAME, false,true, false , false );

	this.settingsKey=this.getClass().getName();
	
	this.originDrawer = originDrawer;

	
	this.buildPanel();
	this.setLayout(null);
	this.setSize(250,100);
	
	
	this.recoverSettings();
	
}



private void buildPanel()
{
	JLabel tmpLabel = new JLabel(OriginManager.COLOR_TEXT);
	tmpLabel.setBounds(5,10,50,40);
	this.add(tmpLabel);
	
	this.colorLabel = new JPanel();
	this.colorLabel.addMouseListener(this);
	this.colorLabel.setBorder(new LineBorder(Color.BLACK, 2));
	this.colorLabel.setBounds(60, 10, 40, 40);
	this.colorLabel.setBackground(Color.WHITE);
	this.add(this.colorLabel);

	
	
	
	tmpLabel = new JLabel(OriginManager.WIDTH_TEXT);
	tmpLabel.setBounds(130,5,50,25);
	this.add(tmpLabel);
	
	this.thicknesSpinner = new JSpinner(new SpinnerNumberModel(1,1,10,1));
	this.thicknesSpinner.setBounds(170, 5, 50, 25);
	this.thicknesSpinner.addChangeListener(this);
	this.add(this.thicknesSpinner);
 
	
	
	
	tmpLabel = new JLabel(OriginManager.SIZE_TEXT);
	tmpLabel.setBounds(130,40,50,25);
	this.add(tmpLabel);
	
	this.sizeSpinner = new JSpinner(new SpinnerNumberModel(10,5,40,1));
	this.sizeSpinner.setBounds(170, 40, 50, 25);
	this.sizeSpinner.addChangeListener(this);
	this.add(this.sizeSpinner);
	
}

protected  void recoverSettings()
{
	
	
	this.thicknesSpinner.setValue(UiSettings.recoverInt(this.settingsKey+OriginManager.widthKey,1));
	this.sizeSpinner.setValue(UiSettings.recoverInt(this.settingsKey+OriginManager.sizeKey,10));
	
	Color color = new Color(UiSettings.recoverInt(this.settingsKey+OriginManager.colorKey,0x00ffff));
	
	this.originDrawer.setDisplayColor(color);
	this.colorLabel.setBackground(color);
    
     
}





protected  void saveSettings()
{
	this.saveColor();
	this.saveThicknes();
	this.saveSize();
}


protected  void saveThicknes()
{
	UiSettings.saveInt(this.settingsKey+OriginManager.widthKey,(Integer)this.thicknesSpinner.getValue());

}


protected  void saveSize()
{
	UiSettings.saveInt(this.settingsKey+OriginManager.sizeKey, (Integer)this.sizeSpinner.getValue());
}


protected  void saveColor()
{
	UiSettings.saveInt(this.settingsKey+OriginManager.colorKey, this.originDrawer.getDisplayColor().getRGB());
}



@Override
public void mouseClicked(MouseEvent arg0) {}

@Override
public void mouseEntered(MouseEvent arg0) {}

@Override
public void mouseExited(MouseEvent arg0) {}




@Override
public void mousePressed(MouseEvent arg0) 
{
	Color newColor = JColorChooser.showDialog(
            this,
            "Choose Background Color",
            null);
	
	this.colorLabel.setBackground(newColor);
	
	
	this.originDrawer.setDisplayColor(newColor);

	this.saveColor();
	
	
}

@Override
public void mouseReleased(MouseEvent arg0) {}




@Override
public void stateChanged(ChangeEvent changeEvent)
{
	// TODO Auto-generated method stub
	if (changeEvent.getSource() == this.thicknesSpinner)
	{
		this.originDrawer.setThicknes((Integer) this.thicknesSpinner.getValue());
		this.saveThicknes();
	}
	
	else if (changeEvent.getSource() == this.sizeSpinner)
	{
		this.originDrawer.setSize((Integer)this.sizeSpinner.getValue());
		this.saveSize();
	}
	
	
}



}
