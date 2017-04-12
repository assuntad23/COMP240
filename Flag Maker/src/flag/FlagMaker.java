package flag;

import java.awt.Color;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

public class FlagMaker extends JFrame implements ActionListener {
	
	private JPanel panel1, panel2, panel3;
	
	public FlagMaker(){
		super("COMP240");
		setSize(800,600);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setLayout(new GridLayout(3,1));
		
		panel1 = new JPanel();
		panel1.setBackground(Color.GRAY);
		add(panel1);
		
		panel2 = new JPanel();
		panel2.setBackground(Color.GRAY);
		add(panel2);
		
		panel3 = new JPanel();
		panel3.setBackground(Color.GRAY);
		add(panel3);
		
		JMenu colorMenu = new JMenu("Nations");
		JMenuItem aChoice = new JMenuItem("Armenia");
		JMenuItem bChoice = new JMenuItem("Bolivia");
		JMenuItem eChoice = new JMenuItem("Estonia");
		JMenuItem gChoice = new JMenuItem("Germany");
		JMenuItem hChoice = new JMenuItem("Holland");
		JMenuItem aboutChoice = new JMenuItem("About");
		JMenuItem exitChoice = new JMenuItem("Exit");
		
		add(colorMenu);
		
		colorMenu.add(aChoice);
		colorMenu.add(bChoice);
		colorMenu.add(eChoice);
		colorMenu.add(gChoice);
		colorMenu.add(hChoice);
		
		colorMenu.addSeparator();
		colorMenu.add(aboutChoice);
		colorMenu.add(exitChoice);
		
		aChoice.addActionListener(this);
		bChoice.addActionListener(this);
		eChoice.addActionListener(this);
		gChoice.addActionListener(this);
		hChoice.addActionListener(this);
		aboutChoice.addActionListener(this);
		exitChoice.addActionListener(this);
		
		JMenuBar bar = new JMenuBar();
		bar.add(colorMenu);
		setJMenuBar(bar);
		
	}

	private static final long serialVersionUID = 1L;

	@Override
	public void actionPerformed(ActionEvent e) {
		String buttonString = e.getActionCommand();
		String message = "Pick your country; change your flag! - Assunta ";
		String title = "Assunta DeSanto's Flag Maker";
		switch (buttonString){
		case "Armenia":
			panel1.setBackground(Color.RED);
			panel2.setBackground(Color.BLUE);
			panel3.setBackground(new Color(0xE39309));
			break;
		case "Bolivia":
			panel1.setBackground(new Color(0xde0000));
			panel2.setBackground(Color.YELLOW);
			panel3.setBackground(new Color(0x148205));
			break;
		case "Estonia":
			panel1.setBackground(new Color(0x4E68DE));
			panel2.setBackground(Color.BLACK);
			panel3.setBackground(Color.WHITE);
			break;
		case "Germany":
			panel1.setBackground(Color.BLACK);
			panel2.setBackground(new Color(0xde0000));
			panel3.setBackground(new Color(0xF2D61D));
			break;
		case "Holland":
			panel1.setBackground(new Color(0xde0000));
			panel2.setBackground(Color.WHITE);
			panel3.setBackground(new Color(0x0234d9));
			break;
		case "About":
			JOptionPane.showMessageDialog(this, message, title, JOptionPane.INFORMATION_MESSAGE);
			break;
		case "Exit":
			if (JOptionPane.showConfirmDialog(this, "Are you sure about that?", "Exit Dialog", JOptionPane.OK_CANCEL_OPTION) 
					== JOptionPane.OK_OPTION){
				this.dispose();
				System.exit(0);
			}
			break;
		}
	}


	public static void main(String[] args) {
		FlagMaker gui = new FlagMaker();
		gui.setVisible(true);

	}

}

