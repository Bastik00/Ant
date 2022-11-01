package de.hska.lat.robot.component.sensor.pixyCamera.view;

import java.awt.Color;
import java.util.ArrayList;

import de.hska.lat.robot.component.sensor.pixyCamera.PixyCamera;
import de.hska.lat.robot.component.sensor.pixyCamera.PixyCameraChangeNotifier;
import de.hska.lat.robot.component.view.ComponentView;

public class PixyCameraObjectScreenView extends ComponentView implements PixyCameraChangeNotifier {

	protected static final int width = 320;
	protected static final int height = 200;

	private static final long serialVersionUID = 6152155775319804201L;

	private DrawCameraObjectRectPanel panel;

	private boolean frameSync = false;

	public PixyCameraObjectScreenView(PixyCamera camera, boolean embedded) {
		super(camera.getComponentName(), embedded);

		camera.addSensorListener(this);

		createView();
	}

	protected void createView() {
		super.buildView();
/*
		Insets insets = this.getBorder().getBorderInsets(this);

		Object rowData[][] = { { "signature:", "-" }, { "x center:", "-" }, { "y center:", "-" }, { "width:", "-" },
				{ "height:", "-" }, };
		Object columnNames[] = { "Descr.", "Value" };
*/
		// objectBlockTable = new JTable(rowData, columnNames);
		// objectBlockTable.setBounds(insets.left + 5, insets.top + 5, 150, 80);
		// objectBlockTable.setAutoResizeMode(JTable.AUTO_RESIZE_SUBSEQUENT_COLUMNS);
		// objectBlockTable.setValueAt("init", 2, 1);
		// this.add(objectBlockTable);

		this.setBackground(Color.BLUE);

		// Graphics g = this.getGraphics();
		//
		// g.drawRect(20, 20, 15, 25);
		// g.setColor(Color.GREEN);

		panel = new DrawCameraObjectRectPanel();

		// panel.setBounds(insets.left + 5, insets.top + 5, 150, 80);
		panel.setBounds(0, 0, this.getWidth(), this.getHeight());
		panel.setBackground(Color.gray);

		this.add(panel);

	}

	@Override
	protected int getViewWidth() {
		return (PixyCameraObjectScreenView.width);
	}

	@Override
	protected int getViewHeight() {
		return (PixyCameraObjectScreenView.height);
	}

	@Override
	public void cameraObjectDetected(PixyCamera camera) {

		if (this.frameSync != camera.frameSync) {
			this.frameSync = !this.frameSync;
			panel.repaint();
			//Copy Array because without copy the objects would get deleted before they´re drawn
	        panel.detectedObjectsDrawCopy = (ArrayList<DetectedObject>) panel.detectedObjects.clone();
			panel.detectedObjects.clear();
		}
		
		panel.detectedObjects.add(new DetectedObject(camera.signature, camera.xCenter, camera.yCenter, camera.width, camera.height, camera.angle));

		//panel.repaint();
		
	}

}
