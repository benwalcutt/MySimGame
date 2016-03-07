import java.awt.Image;

abstract public class Building {
	public int x;
	public int y;
	public Image image;

	public int road;
	public boolean power;
	public boolean water;
	
	public int police;
	public int fire;
	public int hospital;
	public int school;
	
	public int residents;
	public int jobs;
	
	public abstract Image getImage();
	public abstract void update();
}
