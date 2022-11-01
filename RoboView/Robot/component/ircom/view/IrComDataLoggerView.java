package de.hska.lat.robot.component.ircom.view;

import java.awt.BorderLayout;
import java.awt.Color;


import javax.swing.BorderFactory;
import javax.swing.JScrollPane;
import javax.swing.JTable;

import de.hska.lat.comm.packetLogger.DataPacketLogerEvent;
import de.hska.lat.comm.packetLogger.DataPacketLogger;
import de.hska.lat.robot.component.ComponentChangeNotifier;
import de.hska.lat.robot.component.actor.generic.rc5Transciver.Rc5Transciver;
import de.hska.lat.robot.component.view.ComponentView;

public class IrComDataLoggerView extends ComponentView implements DataPacketLogerEvent, ComponentChangeNotifier{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1950922079664996051L;
	private Rc5Transciver transceiver;
	private JTable table;
	IrComPackageLogger dataLogger;
	
	public IrComDataLoggerView(Rc5Transciver transciver, boolean embedded, DataPacketLogger systemDataLogger) {
		super(transciver.getComponentName(), embedded);
		this.transceiver = transciver;
		//systemDataLogger.addListener(this);
		
		// start listening to the IrComPackageLogger
		dataLogger = new IrComPackageLogger(systemDataLogger);
		dataLogger.addListener(this);
		
		this.transceiver.addSensorListener(this);
		creatView(systemDataLogger);
		
	}
	
	private void creatView(DataPacketLogger systemDataLogger) {
		super.buildView();
		this.setLayout(new BorderLayout());
		this.setBounds(10, 120, 800, 300);
		
		IrComTableModel tableModel = new IrComTableModel();
		//tableModel.setPackageLogger(systemDataLogger);
		tableModel.setIrComPackageLogger(dataLogger);
		
		table = new JTable(tableModel);	

		table.setAutoResizeMode(JTable.AUTO_RESIZE_SUBSEQUENT_COLUMNS);
		table.getColumnModel().getColumn(0).setPreferredWidth(10);
		table.getColumnModel().getColumn(1).setPreferredWidth(200);
		table.getColumnModel().getColumn(2).setPreferredWidth(10);
		table.getColumnModel().getColumn(3).setPreferredWidth(10);
		table.getColumnModel().getColumn(3).setPreferredWidth(10);
		table.getColumnModel().getColumn(5).setPreferredWidth(200);
		
		JScrollPane scrollPane = new JScrollPane(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED, 
				JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
		
		scrollPane.setViewportBorder(BorderFactory.createLineBorder(Color.black));
		
		scrollPane.setViewportView(table); // makes the table scrollable
		
		this.add(scrollPane);
	
	}
	
	public void loggerChange() {
		table.revalidate();
		table.repaint();
	}

}
