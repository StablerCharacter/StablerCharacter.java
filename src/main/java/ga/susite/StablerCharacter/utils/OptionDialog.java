/**
 * 
 */
package ga.susite.StablerCharacter.utils;

import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;

import javax.swing.BoxLayout;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;

import ga.susite.StablerCharacter.GameManager;
import tech.fastj.engine.FastJEngine;
import tech.fastj.graphics.display.RenderSettings;
import tech.fastj.math.Point;

class BeautifulPoint extends Point {
	public BeautifulPoint(int x, int y) {
		super(x, y);
	}
	
	public String toString() {
		return Integer.toString(x) + "x" + Integer.toString(y);
	}
}

/**
 * @author lines-of-codes
 * The OptionDialog class used in Options dialog.
 */
@SuppressWarnings("serial")
public class OptionDialog extends JPanel {
	public OptionDialog() {
		Font segoe = new Font("Segoe UI", Font.PLAIN, 15); // Create a new "Segoe UI" font object.
		
		this.setSize(new Dimension(480, 480)); // Set the size of the window
		BoxLayout layout = new BoxLayout(this, BoxLayout.Y_AXIS);
		this.setLayout(layout); // Set the layout of the Panel to SpringLayout
		
		JPanel resolutionOption = new JPanel();
		resolutionOption.setLayout(new FlowLayout());
		// Label for the Window Resolution dropdown
		JLabel resDropdownLabel = new JLabel("Resolution");
		resDropdownLabel.setFont(segoe);
		resolutionOption.add(resDropdownLabel);
		
		Point[] resolutions = {
			new BeautifulPoint(640, 360),
			new BeautifulPoint(800, 600),
			new BeautifulPoint(1280, 720),
			new BeautifulPoint(1920, 1080)
		};
		JComboBox<Point> resDropdown = new JComboBox<Point>(resolutions);
		resDropdown.addActionListener(e -> {
			GameManager.gameWindowResolution = (Point)resDropdown.getSelectedItem();
		});
		resDropdown.setFont(segoe);
		resolutionOption.add(resDropdown);
		this.add(resolutionOption);
		
		JPanel renderingQuality = new JPanel();
		renderingQuality.setLayout(new FlowLayout());
		// Label for the Quality dropdown
		JLabel qualityDropdownLabel = new JLabel("Rendering Quality");
		qualityDropdownLabel.setFont(segoe);
		renderingQuality.add(qualityDropdownLabel);
		
		String[] qualityLabelList = {"Low", "High"};
		JComboBox<String> qualityDropdown = new JComboBox<String>(qualityLabelList);
		qualityDropdown.setSelectedIndex(1);
		qualityDropdown.setAlignmentX(Component.CENTER_ALIGNMENT);
		qualityDropdown.setFont(segoe);
		qualityDropdown.addActionListener(e -> {
			String selected = (String) qualityDropdown.getSelectedItem();
			GameManager gm = GameManager.get();
			if(selected == "Low") gm.renderingQuality = RenderSettings.GeneralRenderingQuality.Low;
			else gm.renderingQuality = RenderSettings.GeneralRenderingQuality.High;
		});
		renderingQuality.add(qualityDropdown);
		this.add(renderingQuality);
		
		// Anti-aliasing Option
		JCheckBox aaCheckbox = new JCheckBox("Anti-aliasing");
		aaCheckbox.setFocusable(false);
		aaCheckbox.setFont(segoe);
		aaCheckbox.setSelected(true);
		aaCheckbox.setAlignmentX(Component.CENTER_ALIGNMENT);
		aaCheckbox.addActionListener(e -> {
			GameManager.get().antiAliasing = aaCheckbox.isSelected();
		});
		this.add(aaCheckbox);
	}
}
