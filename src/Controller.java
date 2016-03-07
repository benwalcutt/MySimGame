import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;


public class Controller implements MouseListener, ActionListener {
	Model model;
	BuildMode mode;
	
	Controller(Model m) {
		this.model = m;
		this.mode = BuildMode.NONE;
	}
	
	Controller() {
		this.mode = BuildMode.NONE;
	}

	@Override
	public void mouseClicked(MouseEvent arg0) {
		if (arg0.getX() < 700) {
			switch (this.mode) {
			case NONE: {
				break;
			}
			case RESIDENTIAL: {
				int possX = arg0.getX() / 20;
				int possY = arg0.getY() / 20;
				model.addResidential(possX, possY);
				break;
			}
			case COMMERCIAL: {
				int possX = arg0.getX() / 20;
				int possY = arg0.getY() / 20;
				model.addCommercial(possX, possY);
				break;
			}
			case INDUSTRIAL: {
				int possX = arg0.getX() / 20;
				int possY = arg0.getY() / 20;
				model.addIndustrial(possX, possY);
				break;
			}
			case ROAD: {
				int possX = arg0.getX() / 20;
				int possY = arg0.getY() / 20;
				model.addRoad(possX, possY);
				break;
			}
			default:
				System.out.println("Default");
				break;
			}
		}
	}

	@Override
	public void mouseEntered(MouseEvent arg0) {
		
	}

	@Override
	public void mouseExited(MouseEvent arg0) {

	}

	@Override
	public void mousePressed(MouseEvent arg0) {

	}

	@Override
	public void mouseReleased(MouseEvent arg0) {

	}

	@Override
	public void actionPerformed(ActionEvent arg0) {
		this.mode = BuildMode.NONE;
		
		if (arg0.getActionCommand().equals("Residential")) {
			this.mode = BuildMode.RESIDENTIAL;
		}
		
		if (arg0.getActionCommand().equals("Commercial")) {
			this.mode = BuildMode.COMMERCIAL;
		}
		
		if (arg0.getActionCommand().equals("Industrial")) {
			this.mode = BuildMode.INDUSTRIAL;
		}
		
		if (arg0.getActionCommand().equals("Road")) {
			this.mode = BuildMode.ROAD;
		}
	}
	
}
