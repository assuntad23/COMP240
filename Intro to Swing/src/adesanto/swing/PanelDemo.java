package adesanto.swing;

import java.awt.Color;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JButton;

public class PanelDemo {
	public static void main(String[] args) {
				
		JFrame window = new JFrame("Panel Demo");
		window.setSize(300, 300);
		window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		window.setLayout(new GridLayout(1,3));
		
		JPanel bluePanel = new JPanel();
		JPanel redPanel = new JPanel();
		JPanel whitePanel = new JPanel();
		JButton out = new JButton("button");
		
		out.setBackground(Color.GREEN);
		out.setForeground(Color.WHITE);
		
		out.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				System.exit(0);
			}
		});
		
		bluePanel.setBackground(Color.BLUE);
		redPanel.setBackground(Color.RED);
		whitePanel.setBackground(Color.WHITE);
		
		whitePanel.add(out);
		
		window.add(bluePanel);
		window.add(whitePanel);
		window.add(redPanel);
		
		
		
		window.setVisible(true);
	}

	
	
}
