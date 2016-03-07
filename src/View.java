import java.awt.Graphics;
import java.awt.Image;
import java.io.File;
import java.io.IOException;
import java.util.Iterator;
import javax.imageio.ImageIO;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;

@SuppressWarnings("serial")
public class View extends JPanel {
	Model model;
	Controller controller;
	Image terrain;
	JLabel Date;
	JLabel Population;
	JLabel CJobs;
	JLabel IJobs;
	JLabel Funds;
	JLabel Income;
	JLabel Payments;
	JLabel NewFunds;
	
	int counter;
	
	View(Model m, Controller c) {
		this.model = m;
		this.controller = c;
		try {
			this.terrain = ImageIO.read(new File("terrain.png"));
		} catch (IOException NoFile) {
			System.out.println("No terrain file.");
		}
		this.setLayout(null);
		
		JLabel Zones = new JLabel();
		Zones.setText("Zones:");
		Zones.setBounds(710, 10, 125, 20);
		this.add(Zones);
		
		JButton Residential = new JButton();
		Residential.setBounds(710, 40, 125, 20);
		Residential.setText("Residential");
		Residential.addActionListener(controller);
		Residential.setToolTipText("Residential");
		this.add(Residential);	
		
		JButton Commercial = new JButton();
		Commercial.setBounds(710, 70, 125, 20);
		Commercial.setText("Commercial");
		Commercial.addActionListener(controller);
		Commercial.setToolTipText("Commercial");
		this.add(Commercial);
		
		JButton Industrial = new JButton();
		Industrial.setBounds(710, 100, 125, 20);
		Industrial.setText("Industrial");
		Industrial.addActionListener(controller);
		Industrial.setToolTipText("Industrial");
		this.add(Industrial);
		
		JLabel Infrastructure = new JLabel();
		Infrastructure.setBounds(710, 130, 125, 20);
		Infrastructure.setText("Infrastructure");
		this.add(Infrastructure);
		
		JButton Road = new JButton();
		Road.setBounds(710, 160, 75, 20);
		Road.setText("Road");
		Road.addActionListener(controller);
		Road.setToolTipText("Road");
		this.add(Road);
		
		JButton Powerline = new JButton();
		Powerline.setBounds(790, 160, 75, 20);
		Powerline.setText("Powerline");
		Powerline.addActionListener(controller);
		Powerline.setToolTipText("Powerline");
		this.add(Powerline);
		
		JButton Police = new JButton();
		Police.setBounds(710, 190, 75, 20);
		Police.setText("Police");
		Police.addActionListener(controller);
		Police.setToolTipText("Police");
		this.add(Police);
		
		JButton Fire = new JButton();
		Fire.setBounds(790, 190, 75, 20);
		Fire.setText("Fire");
		Fire.addActionListener(controller);
		Fire.setToolTipText("Fire");
		this.add(Fire);
		
		JButton Hospital = new JButton();
		Hospital.setBounds(710, 220, 75, 20);
		Hospital.setText("Hospital");
		Hospital.addActionListener(controller);
		Hospital.setToolTipText("Hospital");
		this.add(Hospital);
		
		JButton School = new JButton();
		School.setBounds(790, 220, 75, 20);
		School.setText("School");
		School.addActionListener(controller);
		School.setToolTipText("School");
		this.add(School);
		
		JLabel Admin = new JLabel();
		Admin.setBounds(710, 250, 125, 20);
		Admin.setText("Administration");
		this.add(Admin);
		
		Population = new JLabel();
		Population.setBounds(710, 280, 125, 20);
		Population.setText("Population: 0");
		this.add(Population);
		
		CJobs = new JLabel();
		CJobs.setBounds(710, 300, 125, 20);
		CJobs.setText("Commerce Jobs: 0");
		this.add(CJobs);
		
		IJobs = new JLabel();
		IJobs.setBounds(710, 320, 125, 20);
		IJobs.setText("Industrial Jobs: 0");
		this.add(IJobs);
		
		Funds = new JLabel();
		Funds.setBounds(710, 350, 125, 20);
		Funds.setText("Funds: 0");
		this.add(Funds);
		
		Income = new JLabel();
		Income.setBounds(710, 370, 125, 20);
		Income.setText("Income: 0");
		this.add(Income);
		
		Payments = new JLabel();
		Payments.setBounds(710, 390, 125, 20);
		Payments.setText("Payments: 0");
		this.add(Payments);
		
		NewFunds = new JLabel();
		NewFunds.setBounds(710, 410, 125, 20);
		NewFunds.setText("NewFunds: 0");
		this.add(NewFunds);
		
		
		
		
		
		Date = new JLabel();
		Date.setBounds(710, 675, 100, 25);
		Date.setText("Day 0 Year 1");
		this.add(Date);
		
	}
	
	public void paintComponent(Graphics g) {
		Iterator<Building> BL = model.buildingList.iterator();
		g.drawImage(this.terrain, 0, 0, null);
		
		while (BL.hasNext()) {
			Building current = BL.next();
			g.drawImage(current.getImage(), current.x * 20, current.y * 20, null);
		}
		
		counter = (counter + 1) % 15;
		if (counter == 0) {
			updateCounts();
		}
	}
	
	public void updateCounts() {
		Date.setText("Day " + Integer.toString(model.day) + " Year " + Integer.toString(model.year));
		Population.setText("Population: " + Integer.toString(model.population));
		CJobs.setText("Commerce Jobs: " + Integer.toString(model.cJobs));
		IJobs.setText("Industrial Jobs: " + Integer.toString(model.iJobs));
		Funds.setText("Funds: " + Integer.toString(model.availableFunds));
		Income.setText("Income: " + Integer.toString(model.income));
		Payments.setText("Payments: " + Integer.toString(0));
		NewFunds.setText("Next Month: " + Integer.toString(model.availableFunds + model.income + 0));
	}
}
