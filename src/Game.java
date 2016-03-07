import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JFrame;
import javax.swing.Timer;

@SuppressWarnings("serial")
public class Game extends JFrame implements ActionListener{
	Model model;
	Controller controller;
	View view;
	int day;
	int year;
	int counter;
	
	Game() {
		day = 0;
		year = 1;
		this.model = new Model();
		controller = new Controller(model);
		view = new View(model, controller);
		view.addMouseListener(controller);
		
		this.setTitle("My Sim City");
		this.setSize(900, 700);
		this.getContentPane().add(view);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setVisible(true);
		new Timer(50, this).start();
	}	

	@Override
	public void actionPerformed(ActionEvent arg0) {
		this.model.update();
		repaint();
	}
	
public static void main(String[] args) {
		new Game();
	}

}
