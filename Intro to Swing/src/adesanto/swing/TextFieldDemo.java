package adesanto.swing;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextArea;

public class TextFieldDemo extends JFrame implements ActionListener {

	private static final long serialVersionUID = 1L;
	
	private static final int NUMBER_OF_CHAR = 30;
	private JTextArea name;
	
	public TextFieldDemo(){
		super("COMP240");
		setSize(800,600);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		setLayout(new GridLayout(2,1));
		
		JPanel namePanel = new JPanel();
		namePanel.setLayout(new BorderLayout());
		namePanel.setBackground(Color.WHITE);
		
		name = new JTextArea(5, NUMBER_OF_CHAR);
		namePanel.add(name, BorderLayout.SOUTH);
		
		JLabel nameLabel = new JLabel("Enter your name: ");
		namePanel.add(nameLabel, BorderLayout.CENTER);
		
		add(namePanel);
		
		JPanel buttonPanel = new JPanel();
		buttonPanel.setLayout(new FlowLayout());
		buttonPanel.setBackground(Color.PINK);
		
		JButton actionButton = new JButton("Click me");
		actionButton.addActionListener(this);
		buttonPanel.add(actionButton);

		JButton clearButton = new JButton("Clear");
		clearButton.addActionListener(this);
		buttonPanel.add(clearButton);
		
		add(buttonPanel);
	}
	
	
	@Override
	public void actionPerformed(ActionEvent e) {
		String buttonString = e.getActionCommand();
		if (buttonString.equals("Click me")){
			name.setText("Hello " + name.getText());
		} else if (buttonString.equals("Clear")){
			name.setText("");
		} else {
			name.setText("Unexpected Error!");
		}
	}

	public static void main(String[] args) {
		TextFieldDemo gui = new TextFieldDemo();
		gui.setVisible(true);

	}

}
