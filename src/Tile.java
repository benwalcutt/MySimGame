import java.awt.Image;

public class Tile extends Building {
	
	Tile(int newX, int newY) {
		this.x = newX;
		this.y = newY;

		this.road = 0;
		this.power = false;
		this.water = false;
		
		this.police = 0;
		this.fire = 0;
		this.hospital = 0;
		this.school = 0;
		
		this.residents = 0;
		this.jobs = 0;		
	}
	
	@Override
	public Image getImage() {
		return null;
	}

	@Override
	public void update() {
		
	}

}