package adesanto.swing;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;

public class PanelDemoImproved extends JFrame implements ActionListener {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	JPanel bluePanel, redPanel, whitePanel;
	boolean red, blue, white;

	public PanelDemoImproved() {
		super("COMP 240");
		setSize(800,600);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setLayout(new BorderLayout());
		
		JPanel biggerPanel = new JPanel();
		biggerPanel.setLayout(new GridLayout(1,3));
		add(biggerPanel, BorderLayout.CENTER);
		
		bluePanel = new JPanel();
		whitePanel = new JPanel();
		redPanel = new JPanel();
		
		bluePanel.setBackground(Color.GRAY);
		whitePanel.setBackground(Color.GRAY);
		redPanel.setBackground(Color.GRAY);

		biggerPanel.add(redPanel);
		biggerPanel.add(whitePanel);
		biggerPanel.add(bluePanel);

		JPanel buttonPanel = new JPanel();
		buttonPanel.setBackground(Color.GRAY);
		buttonPanel.setLayout(new FlowLayout());
		add(buttonPanel, BorderLayout.SOUTH);
		
		JButton red = new JButton("red");
		JButton white = new JButton("white");
		JButton blue = new JButton("blue");
		
		red.setBackground(Color.RED);
		white.setBackground(Color.WHITE);
		blue.setBackground(Color.BLUE);
		
		buttonPanel.add(red);
		buttonPanel.add(white);
		buttonPanel.add(blue);
		
		red.addActionListener(this);
		white.addActionListener(this);
		blue.addActionListener(this);
		
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		String buttonString = e.getActionCommand();
		
		if (buttonString.equals("red")){
			if (red)
				redPanel.setBackground(Color.GRAY);
			else 
				redPanel.setBackground(Color.RED);
			red = !red;
		} else if (buttonString.equals("white")){
			if (white)
				whitePanel.setBackground(Color.GRAY);
			else 
				whitePanel.setBackground(Color.WHITE);
			white = !white;
		} else if (buttonString.equals("blue")){
			if (blue)
				bluePanel.setBackground(Color.GRAY);
			else 
				bluePanel.setBackground(Color.BLUE);
			blue = !blue;
		}
		
		
	}

	public static void main(String[] args) {
		PanelDemoImproved gui = new PanelDemoImproved();
		gui.setVisible(true);
	}

}
