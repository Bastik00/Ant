package de.hska.lat.robot.editor3d;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFileChooser;
import javax.swing.JInternalFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.filechooser.FileNameExtensionFilter;

import de.hska.lat.robot.morphology.appearance.AppearanceModel;
import de.hska.lat.robot.morphology.appearance.Mesh3D;
import de.hska.lat.robot.morphology.appearance.OrganAppearance;
import de.hska.lat.robot.morphology.appearance.Editor3D.entities.Figure;
import de.hska.lat.robot.morphology.appearance.Editor3D.entities.Figures;
import de.hska.lat.robot.morphology.appearance.Editor3D.event.FiguresEvent;
import de.hska.lat.robot.morphology.appearance.Editor3D.event.FiguresEventHandler;
import de.hska.lat.robot.morphology.appearance.Editor3D.event.FiguresListener;
import de.hska.lat.robot.morphology.appearance.Editor3D.xml.Import;
/**
 * the new variant of the Main Menu from the elder editor
 * @author moritz
 *
 */
public class LoadOrganWizard extends JInternalFrame implements ActionListener,
		FiguresListener {

	private static final long serialVersionUID = -4642997659423042978L;
	private FiguresEventHandler eventHandler;
	private String figureName;
	private String figurePath;

	private JComboBox<String> combobox;
	private JButton buttonOk;
	private JButton buttonLoad;
	private JButton buttonNew;

	private AppearanceModel appearanceModel;

	int elementId = 0;

	public LoadOrganWizard(FiguresEventHandler eventHandler) {
		super("New / Load Organ");
		setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		setClosable(true);
		JPanel content = new JPanel();
		this.eventHandler = eventHandler;
		this.appearanceModel = eventHandler.getAppearanceModel();
		combobox = new JComboBox<String>();
		combobox.setBounds(70, 40, 120, 30);

		combobox.addItem("");
		for (OrganAppearance organ : appearanceModel.getOrganList()) {
			for (Mesh3D mesh : organ.getMeshList()) {
				combobox.addItem(mesh.getBone().getName());
			}
		}

		combobox.addActionListener(this);
		add(content);
		buttonOk = new JButton("Ok");
		buttonOk.setEnabled(false);
		buttonOk.addActionListener(this);
		content.add(combobox);
		content.add(buttonOk);
		eventHandler.addFiguresListener(this);
		setSize(getPreferredSize().width, 250);

		createUserInterface();
	}

	private void createUserInterface() {
		this.setLayout(null);
		this.setSize(230, 330);
		this.setLocation(550, 0);

		JLabel labelLoad = new JLabel();
		labelLoad.setBounds(10, 10, 150, 30);
		labelLoad.setText("Load existing organ");
		this.add(labelLoad);

		JLabel labelOrgan = new JLabel();
		labelOrgan.setBounds(10, 40, 60, 30);
		labelOrgan.setText("Organ");
		this.add(labelOrgan);

		combobox.addActionListener(this);

		this.add(combobox);

		buttonLoad = new JButton("Load XML");

		buttonLoad.setBounds(70, 80, 120, 40);
		buttonLoad.addActionListener(this);
		this.add(buttonLoad);

		buttonOk = new JButton("Ok");

		buttonOk.setBounds(70, 130, 120, 40);
		buttonOk.addActionListener(this);
		buttonOk.setEnabled(false);
		this.add(buttonOk);

		JLabel labelNew = new JLabel();
		labelNew.setBounds(10, 200, 150, 30);
		labelNew.setText("Create new");
		this.add(labelNew);

		buttonNew = new JButton("New");

		buttonNew.setBounds(70, 230, 120, 40);
		buttonNew.addActionListener(this);
		this.add(buttonNew);

		this.show();
	}

	@Override
	public void actionPerformed(ActionEvent e) {

		if (e.getSource().equals(combobox)) {
			String selectedItem = (String) combobox.getSelectedItem();
			if (selectedItem.equals("")) {
				buttonOk.setEnabled(false);
				buttonLoad.setEnabled(true);
				Figures figures = eventHandler.getFigures();
				figures.clearFigures();
				eventHandler.notifyUpdate(new FiguresEvent(this));
			} else {
				importSelectedFigure(selectedItem);
				buttonOk.setEnabled(true);
			}
		} else if (e.getSource().equals(buttonLoad)) {
			JFileChooser chooser = new JFileChooser();

			// Filter nur f�r XML
			chooser.removeChoosableFileFilter(chooser.getFileFilter());

			FileNameExtensionFilter filter = new FileNameExtensionFilter(
					"XML figure files", "xml");
			chooser.setFileFilter(filter);

			if (chooser.showOpenDialog(null) == JFileChooser.APPROVE_OPTION) {
				figurePath = chooser.getSelectedFile().getPath();
				new Import(eventHandler, figurePath);
			}

			buttonOk.setEnabled(true);
		}

		else if (e.getSource().equals(buttonNew)) {
			eventHandler.getFigures().clearFigures();
			eventHandler.notifyUpdate(new FiguresEvent(this));
			this.dispose();
		}

		else if (e.getSource().equals(buttonOk)) {
			this.dispose();
		}
	}

	private void importSelectedFigure(String selectedValue) {

		Figures figures = eventHandler.getFigures();
		figures.clearFigures();
		eventHandler.setOrganLoaded(true);
		for (OrganAppearance organ : appearanceModel.getOrganList()) {
			for (Mesh3D mesh : organ.getMeshList()) {
				if (mesh.getBone().getName().equals(selectedValue)) {
					figureName = mesh.getClass().getSimpleName() + "_Bone_"
							+ mesh.getBone().getId();
					figurePath = "config/" + figureName + ".xml";

					if (new File(figurePath).isFile()) {
						new Import(eventHandler, figurePath);
						eventHandler.setFigurePath(figurePath);
					} else {

						// H�ndischer Import, falls Daten �ber Vertics gepflegt
						// werden
						Figure figure = new Figure();
						figures.addFigure(figure);
						float[] vertices = mesh.getVertexes();

						for (int i = 0; i < (vertices.length / 10); i++) {
							figure.addCoordinate(i, vertices[i * 10],
									vertices[(i * 10) + 1],
									vertices[(i * 10) + 2]);
						}

						figure.addElements(mesh.getElements());
						figure.setName("Figure");
						figure.setStatus("done");
						figure.setType("Figure");
						figure.setVisibile(true);
						
						eventHandler.notifyUpdate(new FiguresEvent(this));
					}
				}
			}
		}
	}

	@Override
	public void wasUpdated(FiguresEvent e) {
	}
}
