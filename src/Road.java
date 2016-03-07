import java.awt.Image;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

public class Road extends Building {
	
	Road(int newX, int newY) throws IOException {
		this.x = newX;
		this.y = newY;
		
		if (this.image == null) {
			this.image = ImageIO.read(new File("Images/Road.png"));
		}
	}
	
	@Override
	public Image getImage() {
		return image;
	}

	@Override
	public void update() {
		
	}

}