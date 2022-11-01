package de.hska.lat.robot.dataViewer.recorder;




import java.util.ArrayList;

import de.hska.lat.robot.abstractRobot.AbstractRobot;
import de.hska.lat.robot.dataGraph.DataGraph;
import de.hska.lat.robot.value.ComponentValue;



public class ValueRecorderView extends DataGraph
{




/**
	 * 
	 */
	private static final long serialVersionUID = 5386555094579874801L;

	
//	protected ArrayList<ComponentValue<?,?>> valueList = new ArrayList<ComponentValue<?,?>>();
	
/*	protected ArrayList<DataGeneratorDestination> destinations = new ArrayList<DataGeneratorDestination>();

	
	
	
	
	
	protected DataGeneratorToolbar toolBar;

	protected ValuePlayList playlist = new ValuePlayList();

	*/
public ValueRecorderView() 
{
	super("Value recorder");
	
/*	
	this.setLayout(null);

	this.scalePanel = new ScalePanel(this.screenData, this);
	this.scalePanel.setBounds(150,0,600,30);
	this.add(this.scalePanel);
	this.addComponentListener(this);

/*	
	this.toolBar = new DataGeneratorToolbar(this);
	this.toolBar.setBounds(0, 0,this.getWidth(),25);
	this.add(this.toolBar);
	
	*/
//	this.rearange();
//	this.show();
	

}

@Override
protected ArrayList<ComponentValue<?>> getValues(AbstractRobot<?,?,?> robot)
{
	ArrayList<ComponentValue<?>> valueList = super.getValues(robot);
	
	valueList.addAll(robot.getInputValues());
	
	
	return(valueList);
}

/*

protected void addDestinationView(ComponentValue<?,?> value)
{
	DataGeneratorDestination destination = new DataGeneratorDestination(value, screenData, this);

	destinations.add(destination);
	this.add(destination);
}


*/
/*
protected void rearange()
{
	int yPosition;
	
	this.toolBar.setSize(this.getWidth(),25);
	this.scalePanel.setLocation(150,this.toolBar.getHeight());
	this.scalePanel.setSize(this.getWidth()-100,30);
	
	yPosition=scalePanel.getY()+scalePanel.getHeight();

	for (DataGeneratorDestination destination : destinations)
	{
		destination.setBounds(0, yPosition,this.getWidth(),destination.getHeight());
		yPosition+=destination.getHeight();
	}

}


*/


/*

@Override
public void setRobot(AbstractRobot<?,?,?> robot)
{
	System.out.println("setEmulator(RobotEmulator emulator)");
	
	

	this.valueList.addAll(robot.getValues());
	
	for (ComponentValue<?,?> value : valueList)
	{
	//	addDestinationView(value);
		System.out.println(" name : "+value.getName()+"       type "+value.getTypeName());
	}

	this.rearange();
}

/*

@Override
public void moved(int delta)
{
	this.screenData.moveOffset(delta);
	
	
	this.scalePanel.update();
	
	for (DataGeneratorDestination destination : destinations)
	{
		
		destination.update();
	}
}



@Override
public void timeScaleChanged(int delta)
{
	this.screenData.zoomTimescale(delta);
	this.scalePanel.update();
	
	
	for (DataGeneratorDestination destination : destinations)
	{
		
		destination.update();
	}
	
}







public boolean isOpen(ComponentValue<?,?> value)
{
	for(DataGeneratorDestination destination : this.destinations)
	{
		if (destination.getValue()==value)
			return(true);
	}
	return(false);
}


@Override
public void addValue()
{

	//TODO filter out visible value
	
	ArrayList<ComponentValue<?,?>> values = new ArrayList<ComponentValue<?,?>>();

	for ( ComponentValue<?,?> value  : this.valueList)
	{
		if (this.isOpen(value)==false)
			values.add(value);
			
	}
	
	
	ComponentValue<?,?> value = (ComponentValue<?,?>)JOptionPane.showInputDialog(
	                    this,
	                    "chose value",
	                    "Customized Dialog",
	                    JOptionPane.PLAIN_MESSAGE,
	                    null, // icon
	                    values.toArray(),
	                    "");

	
	if (value!=null)
	{
		this.addDestinationView(value);
	}
	this.rearange();
}





@Override
public void play()
{
	
	this.playlist.clear();
	
	for (DataGeneratorDestination destination : destinations)
	{
		this.playlist.add(destination.getValueFlow());
	}
	this.playlist.play(0);
	
}



@Override
public void stop()
{

	this.playlist.stop();
}



@Override
public void save()
{
	
	//Create a file chooser
	final JFileChooser fc = new JFileChooser();

	//In response to a button click:
	int returnVal = fc.showSaveDialog(this);

	fc.getSelectedFile();
	
	
	// TODO Auto-generated method stub
	
}



@Override
public void load()
{
	final JFileChooser fc = new JFileChooser();

	//In response to a button click:
	int returnVal = fc.showOpenDialog(this);

	
	//http://blog.mynotiz.de/programmieren/java-sax-parser-tutorial-773/
	
	/*
	CSVParser csvParser = new CSVParser( new FileInputStream("datei.csv") );
	for ( String t; (t = csvParser.nextValue()) != null; )  
		System.out.println( csvParser.lastLineNumber() + " " + t );

	*/
	// TODO Auto-generated method stub
// http://openbook.galileodesign.de/javainsel8/javainsel_14_013.htm#mjfa6196ba026bf39d84e605c1ca45f33c
	/*
	URL url = new URL( "http://www.tutego.com/index.html" ); 
	Reader reader = new InputStreamReader( url.openStream() );
	StreamTokenizer st = new StreamTokenizer( reader );  
	// 	st.slashSlashComments( true ); */ 
	/*st.slashStarComments( true );
	st.ordinaryChar( '/' ); 
	st.parseNumbers(); 
	st.eolIsSignificant( true ); 
	for ( int tval; (tval = st.nextToken())!= StreamTokenizer.TT_EOF; )
	{   if ( tval == StreamTokenizer.TT_NUMBER )   
		System.out.println( "Nummer: " + st.nval );  
	else if ( tval == StreamTokenizer.TT_WORD )   
		System.out.println( "Wort: " + st.sval );  
	else if ( tval == StreamTokenizer.TT_EOL )    
		System.out.println( "Ende der Zeile" ); 
	else  
		System.out.println( "Zeichen: " + (char) st.ttype );   } }

}


@Override
public void closeValue(ComponentValue<?, ?> value)
{
	// TODO Auto-generated method stub
	for (DataGeneratorDestination destination: this.destinations)
	{
		if (destination.getValue()==value)
		{
			this.remove(destination);
			this.destinations.remove(destination);
			this.invalidate();
			this.repaint();
			return;
		}
	}
}



@Override
protected void onClosing()
{
	this.playlist.clear();
	this.playlist.stop();
	
}

*/



}
