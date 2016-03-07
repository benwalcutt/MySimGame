import java.awt.Image;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

public class Commercial extends Building {
	
	Commercial(int newX, int newY) throws IOException {
		this.x = newX;
		this.y = newY;
		
		if (this.image == null) {
			this.image = ImageIO.read(new File("Images/Commercial.png"));
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