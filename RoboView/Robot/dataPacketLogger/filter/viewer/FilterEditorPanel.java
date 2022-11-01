package de.hska.lat.robot.dataPacketLogger.filter.viewer;

import java.awt.Color;
import java.awt.Dimension;

import javax.swing.DefaultCellEditor;
import javax.swing.JComboBox;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.border.LineBorder;
import javax.swing.table.TableColumn;

public class FilterEditorPanel extends JScrollPane
{

	/**
	 * 
	 */
	private static final long serialVersionUID = -5927367005437592504L;

	
	
public FilterEditorPanel()	
{
	
	this.setBorder(new LineBorder(Color.BLACK));
	this.buildPanel();
}
	


protected void buildPanel()
{
	this.setPreferredSize(new Dimension(900,100));
	this.setSize(new Dimension(900,100));
	
	JTable rulesTable;
	rulesTable = new JTable(new FilterRuleTableModel());

	this.setViewportView(rulesTable);	
	
	
	TableColumn sportColumn = rulesTable.getColumnModel().getColumn(1);

	JComboBox<String> comboBox = new JComboBox<String>();
	comboBox.addItem("Snowboarding");
	comboBox.addItem("Rowing");
	comboBox.addItem("Chasing toddlers");
	comboBox.addItem("Speed reading");
	comboBox.addItem("Teaching high school");
	comboBox.addItem("None");
	sportColumn.setCellEditor(new DefaultCellEditor(comboBox));
	
	//FilterRuleTablethis.add(new )
}
	
}
