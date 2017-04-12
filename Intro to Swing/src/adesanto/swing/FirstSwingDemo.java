package adesanto.swing;
import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;

public class FirstSwingDemo {

	public static final int SCREENWIDTH = 300;
	public static final int SCREENHEIGHT = 300;
	
	public static void main(String[] args){
		JFrame window = new JFrame ("COMP 240");
		window.setSize(SCREENWIDTH, SCREENHEIGHT);
		
		window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		//window.setLayout(new BorderLayout());
		
		window.setLayout(new GridLayout(2,3));
		
		
		JButton b2 = new JButton("b2");
		JButton button = new JButton("Click to End!");
		JButton b3 = new JButton("b3");
		JButton b4 = new JButton("b4");
		JButton b5 = new JButton("b5");
		
		button.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				System.exit(0);
			}
		});
		
		button.setSize(10,10);
		b2.setSize(20,20);
		
		window.add(button,BorderLayout.CENTER);
		window.add(b2,BorderLayout.EAST);
		window.add(b3, BorderLayout.NORTH);
		window.add(b4, BorderLayout.SOUTH);
		window.add(b5);
		window.setVisible(true);		
	}
}