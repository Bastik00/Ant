package de.hska.lat.robot.morphology.appearance.Editor3D.xml;

import java.io.File;

import javax.swing.JFileChooser;
import javax.swing.JOptionPane;

public class SaveFileChooser extends JFileChooser {
	private static final long serialVersionUID = 8031645983570913406L;
	
	@Override
	public void approveSelection() {
		String figurePath = getSelectedFile().getAbsolutePath();
		if (!figurePath.endsWith(".xml")) {
			figurePath += ".xml";
		}
		File file = new File(figurePath);
		if (file.exists()) {
			if (JOptionPane.showConfirmDialog(this, file.getName() + " ist bereits vorhanden." +
					System.getProperty("line.separator") + "Möchten Sie sie ersetzen?", "Speichern bestätigen",
					JOptionPane.YES_NO_OPTION, JOptionPane.WARNING_MESSAGE) == JOptionPane.YES_OPTION
			) {
					super.approveSelection();
			}
		} 
		else {
			super.approveSelection();
		}
		
	}
}
