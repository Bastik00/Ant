package de.hska.lat.robot.editor3d.palette;

import java.awt.Color;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JSlider;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

import de.hska.lat.color.HsvColor;
import de.hska.lat.color.RgbColor;
import de.hska.lat.math.Radiant;
import de.hska.lat.robot.morphology.appearance.Editor3D.entities.Figure;
import de.hska.lat.robot.morphology.appearance.Editor3D.event.FiguresEventHandler;

public class HSVChooser extends JPanel implements ChangeListener, ActionListener {
	/**
	 * 
	 */
	private static final long serialVersionUID = -8945199880240249978L;
	private JSlider hueSlider;
	private JSlider saturationSlider;
	private JSlider valueSlider;
	public FiguresEventHandler eventHandler;
	private JPanel colorPreview;
	private JButton setColorButton;

	@SuppressWarnings("static-access")
	public HSVChooser(FiguresEventHandler eventHandler) {
		setBorder(BorderFactory.createTitledBorder("Color"));
		this.eventHandler = eventHandler;
		GridBagLayout gridBagLayout = new GridBagLayout();
		setLayout(gridBagLayout);

		GridBagConstraints gridBagConstraints = new GridBagConstraints();
		gridBagConstraints.fill = gridBagConstraints.VERTICAL;
		gridBagConstraints.gridx = 0;
		gridBagConstraints.gridy = 0;
		gridBagConstraints.gridheight = 1;

		JLabel hueLabel = new JLabel("Hue");
		gridBagLayout.setConstraints(hueLabel, gridBagConstraints);
		add(hueLabel);
		gridBagConstraints.gridx = 1;
		gridBagConstraints.gridy = 0;
		gridBagConstraints.gridheight = 1;

		hueSlider = new JSlider(JSlider.HORIZONTAL, 0, 360, 180);
		hueSlider.setName("Hue");
		hueSlider.setMajorTickSpacing(50);
		hueSlider.setMinorTickSpacing(10);
		hueSlider.setPaintTicks(true);
		hueSlider.setPaintLabels(true);
		hueSlider.setValue(240);
		hueSlider.setToolTipText("0/360 = red, 120 = green, 240 = blue");
		gridBagLayout.setConstraints(hueSlider, gridBagConstraints);
		add(hueSlider);

		gridBagConstraints.gridx = 0;
		gridBagConstraints.gridy = 2;
		gridBagConstraints.gridheight = 1;

		JLabel saturationLabel = new JLabel("Saturation");
		gridBagLayout.setConstraints(saturationLabel, gridBagConstraints);
		add(saturationLabel);

		gridBagConstraints.gridx = 1;
		gridBagConstraints.gridy = 2;
		gridBagConstraints.gridheight = 1;

		saturationSlider = new JSlider(JSlider.HORIZONTAL, 0, 100, 50);
		saturationSlider.setMajorTickSpacing(50);
		saturationSlider.setMinorTickSpacing(10);
		saturationSlider.setPaintTicks(true);
		saturationSlider.setPaintLabels(true);
		saturationSlider.setValue(100);
		saturationSlider.setToolTipText("Saturation in percent");
		gridBagLayout.setConstraints(saturationSlider, gridBagConstraints);
		add(saturationSlider);

		gridBagConstraints.gridx = 0;
		gridBagConstraints.gridy = 3;
		gridBagConstraints.gridheight = 1;

		JLabel valueLabel = new JLabel("value");
		gridBagLayout.setConstraints(valueLabel, gridBagConstraints);
		add(valueLabel);

		gridBagConstraints.gridx = 1;
		gridBagConstraints.gridy = 3;
		gridBagConstraints.gridheight = 1;
		valueSlider = new JSlider(JSlider.HORIZONTAL, 0, 100, 50);
		valueSlider.setMajorTickSpacing(50);
		valueSlider.setMinorTickSpacing(10);
		valueSlider.setPaintTicks(true);
		valueSlider.setPaintLabels(true);
		valueSlider.setValue(100);
		valueSlider.setToolTipText("Enlightment in percent");
		gridBagLayout.setConstraints(valueSlider, gridBagConstraints);
		add(valueSlider);

		colorPreview = new JPanel();
		colorPreview.setSize(40, 40);
		RgbColor color = getColorAsRgb();
		colorPreview
				.setBackground(new Color(color.red, color.green, color.blue));
		add(colorPreview);

		hueSlider.addChangeListener(this);
		saturationSlider.addChangeListener(this);
		valueSlider.addChangeListener(this);

		setColorButton = new JButton();
		setColorButton.setText("Set");
		setColorButton.setName("set");
		setColorButton.addActionListener(this);
		add(setColorButton);
	}

	public int getHue() {
		return hueSlider.getValue();
	}


	public int getSaturation() {
		return saturationSlider.getValue();
	}

	public int getValue() {
		return valueSlider.getValue();
	}

	public RgbColor getColorAsRgb() {
		return new HsvColor(Radiant.convertDegreeToRadiant(this.hueSlider.getValue()),
				saturationSlider.getValue() / 100.0f,
				valueSlider.getValue() / 100.0f).getAsRgb();
	}

	public void setColor(RgbColor color) {
		HsvColor asHsv = color.getAsHsv();
		setHue((int) asHsv.h); 
		setValue((int) asHsv.v);
		setSaturation((int)asHsv.s);
	}

	public void setHue(int hue) {
		hueSlider.setValue(hue);
	}

	public void setValue(int value) {
		valueSlider.setValue(value);
	}

	public void setSaturation(int saturation) {
		saturationSlider.setValue(saturation);
	}

	@Override
	public void stateChanged(ChangeEvent e) {
		RgbColor color = getColorAsRgb();
		colorPreview
				.setBackground(new Color(color.red, color.green, color.blue));
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if(e.getSource() instanceof JButton){
			RgbColor color = getColorAsRgb();
			int selectedFigure = this.eventHandler.getSelectedFigure();
			if (selectedFigure > -1) {
				Figure figure = this.eventHandler.getFigures().getFigure(
						selectedFigure);
				figure.setColor(color.red, color.green, color.blue, 1.0f);
			}
			eventHandler.notifyUpdate(null);
		}
		
	}
}
