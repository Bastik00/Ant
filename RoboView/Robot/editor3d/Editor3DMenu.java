package de.hska.lat.robot.editor3d;

import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.beans.PropertyChangeListener;
import java.util.List;

import javax.swing.Action;
import javax.swing.JCheckBoxMenuItem;
import javax.swing.JComboBox;
import javax.swing.JFileChooser;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JSeparator;
import javax.swing.SwingConstants;
import javax.swing.filechooser.FileNameExtensionFilter;

import de.hska.lat.robot.editor3d.editor.Editor;
import de.hska.lat.robot.morphology.appearance.Editor3D.entities.Figures;
import de.hska.lat.robot.morphology.appearance.Editor3D.event.FiguresEvent;
import de.hska.lat.robot.morphology.appearance.Editor3D.event.FiguresEventHandler;
import de.hska.lat.robot.morphology.appearance.Editor3D.event.FiguresListener;
import de.hska.lat.robot.morphology.appearance.Editor3D.xml.Export;
import de.hska.lat.robot.morphology.appearance.Editor3D.xml.SaveFileChooser;

public class Editor3DMenu extends JMenuBar implements Action, ActionListener,
		FiguresListener {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JMenu model;
	private JMenu figure;

	private JMenuItem loadItem;
	private JMenuItem saveItem;
	private JMenuItem saveAsItem;
	private FiguresEventHandler eventHandler;
	private final int NORMALZOOM_INDEX = 1;
	private JMenuItem zoomItem;
	private boolean init;
	private List<Editor> editors;

	public Editor3DMenu(FiguresEventHandler eventHandler,
			Container contentPane, List<Editor> editors) {
		this.eventHandler = eventHandler;
		init = true;
		eventHandler.addFiguresListener(this);
		model = new JMenu("Model");
		figure = new JMenu("Figure");
		this.editors = editors;
		loadItem = new JMenuItem("Load organ");
		loadItem.setName("load");
		saveItem = new JMenuItem("Save");
		saveItem.setName("save");
		saveItem.addActionListener(this);
		loadItem.addActionListener(this);
		model.add((Action) this);

		saveAsItem = new JMenuItem("Save as");
		saveAsItem.setName("saveAs");
		saveAsItem.addActionListener(this);

		model.add(loadItem);
		model.add(saveItem);
		model.add(saveAsItem);

		model.addActionListener(this);
		JComboBox<String> zoomBox = new JComboBox<String>(
				"75%,100%,125%,175%,200%,250%,300%".split(","));
		zoomItem = new JMenuItem();
		zoomBox.setSelectedIndex(NORMALZOOM_INDEX);
		zoomBox.setPreferredSize(new Dimension(50, 30));
		zoomItem.add(zoomBox);
		zoomItem.setPreferredSize(new Dimension(50, 20));
		// zoomItem.addActionListener(this);
		JLabel label = new JLabel(" Editor Zoom: ");
		label.add(zoomItem);
		zoomBox.addActionListener(new comboboxListener());

		add(model, BorderLayout.WEST);
		add(figure, BorderLayout.WEST);
		add(new JSeparator(SwingConstants.VERTICAL));
		add(label, BorderLayout.WEST);
		add(zoomItem, BorderLayout.WEST);

	}

	@Override
	public void actionPerformed(ActionEvent event) {
		JMenuItem source = null;
		JCheckBoxMenuItem sourceBox = null;

		if (event.getSource() instanceof JCheckBoxMenuItem) {
			sourceBox = (JCheckBoxMenuItem) event.getSource();
		} else if (event.getSource() instanceof JMenuItem) {
			source = (JMenuItem) event.getSource();
		}

		if (sourceBox != null) {

			int figureID = Integer.valueOf(sourceBox.getName());
			eventHandler.getFigures().getFigure(figureID)
					.setVisibile(sourceBox.isSelected());
		} else if (source != null && source.equals(loadItem)) {
			LoadOrganWizard wizard = new LoadOrganWizard(eventHandler);
			this.getTopLevelAncestor().add(wizard);
			wizard.toFront();
		} else if (source != null && source.equals(saveItem)) {
			save();
		} else if (source != null && source.equals(saveAsItem)) {

			JFileChooser chooser = new SaveFileChooser();

			// Filter nur für XML
			chooser.removeChoosableFileFilter(chooser.getFileFilter());
			FileNameExtensionFilter filter = new FileNameExtensionFilter(
					"XML figure files", "xml");
			chooser.setFileFilter(filter);

			if (chooser.showSaveDialog(null) == JFileChooser.APPROVE_OPTION) {

				String figurePath = chooser.getSelectedFile().getPath();

				if (!figurePath.endsWith(".xml")) {
					figurePath += ".xml";
				}
				resetEditors();
				new Export(eventHandler, figurePath);
			}

		}
		eventHandler.notifyUpdate(null);
	}

	@Override
	public void addPropertyChangeListener(PropertyChangeListener arg0) {
	}

	@Override
	public Object getValue(String arg0) {
		return null;
	}

	@Override
	public boolean isEnabled() {
		return false;
	}

	@Override
	public void putValue(String arg0, Object arg1) {
	}

	@Override
	public void removePropertyChangeListener(PropertyChangeListener arg0) {

	}

	@Override
	public void setEnabled(boolean arg0) {
	}

	private class comboboxListener implements ActionListener {

		@SuppressWarnings("unchecked")
		@Override
		public void actionPerformed(ActionEvent event) {
			if (event.getSource() instanceof JComboBox) {
				JComboBox<String> sourceBox = (JComboBox<String>) event
						.getSource();
				try {
					Integer.parseInt(((String) sourceBox.getSelectedItem())
							.replace("%", ""));
				} catch (NumberFormatException e) {
					sourceBox.setSelectedIndex(NORMALZOOM_INDEX);
				}

				float value = (float) Integer.parseInt(((String) sourceBox
						.getSelectedItem()).replace("%", ""));
				eventHandler.setZoomfactor(value / 100);
				eventHandler.notifyUpdate(null);
			}
		}
	}

	@Override
	public void wasUpdated(FiguresEvent e) {
		Figures figures = eventHandler.getFigures();
		figure.removeAll();
		for (int i = 0; i < figures.getSize(); i++) {
			JCheckBoxMenuItem figureBox = new JCheckBoxMenuItem("Figur: " + i
					+ " ", figures.getFigure(i).isVisibile());
			figureBox.setName("" + i);
			if (init) {
				// figureBox.setSelected(true);
			}
			figureBox.addActionListener(this);
			figure.add(figureBox);
		}
		init = false;
	}

	private void save() {
		System.out.println("Speichere Modell...");
		resetEditors();
		new Export(eventHandler, eventHandler.getFigurePath());
	}

	private void resetEditors() {
		for (Editor editor : editors) {
			editor.getPaintArea().getEditorEventHandler().reset();
		}
	}
}
