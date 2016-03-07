import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import javax.imageio.ImageIO;

public class Model {
	Building[][] buildings;
	List<Building> buildingList;
	Building temp;
	Building test;
	
	int day;
	int year = 1;
	int counter;
	
	int population = 0;
	int cJobs = 0;
	int iJobs = 0;
	int residentialDemand = 10;
	int residentialSupply = 0;
	int commercialDemand = 0;
	int commercialSupply = 0;
	int industrialDemand = 0;
	int industrialSupply = 0;
	
	int availableFunds = 50000;
	int income = 0;
	int payments = 0;
	
	double residentialTax = 0.05;
	double commercialTax = 0.05;
	double industrialTax = 0.05;
	
	final int RESIDENTIAL_COST = 5000;
	final int COMMERCIAL_COST = 5000;
	final int INDUSTRIAL_COST = 5000;
	final int ROAD_COST = 500;
	
	
	
	Model() {
		buildings = new Building[35][35];
		for (int i = 0; i < 35; i++) {
			for (int j = 0; j < 35; j++) {
				buildings[i][j] = new Tile(i, j);
			}
		}
		buildingList = new ArrayList<Building>();
	}
	
	public void update() {
		counter = (counter + 1) % 15;
		if (counter == 0) {
			day = (day + 1) % 360;
			if (day % 30 == 0) {
				updateFunds();
			}
			updateCounts();
			if (day == 0) {
				year = year + 1;
			}
		}
		
		
	}
	
	public void addResidential(int x, int y) {
		if (this.availableFunds < this.RESIDENTIAL_COST) return;
		try {
			test = this.buildings[x][y];
			if (test instanceof Tile) {
				temp = new Residential(x, y);
				copyTileInfo(temp, test);
				this.buildings[x][y] = temp;
				this.buildingList.add(temp);
				
				residentialSupply += 10;
				this.availableFunds -= this.RESIDENTIAL_COST;
			}
		} catch (IOException NoFile) {
			System.out.println("No file.");
		}
	}
	public void addCommercial(int x, int y) {
		if (this.availableFunds < this.COMMERCIAL_COST) return;
		try {
			test = this.buildings[x][y];
			if (test instanceof Tile) {
				temp = new Commercial(x, y);
				copyTileInfo(temp, test);
				this.buildings[x][y] = temp;
				this.buildingList.add(temp);
				
				commercialSupply += 5;
				this.availableFunds -= this.COMMERCIAL_COST;
			}
		} catch (IOException NoFile) {
			System.out.println("No file.");
		}
	}
	public void addIndustrial(int x, int y) {
		if (this.availableFunds < this.INDUSTRIAL_COST) return;
		try {
			test = this.buildings[x][y];
			if (test instanceof Tile) {
				temp = new Industrial(x, y);
				copyTileInfo(temp, test);
				this.buildings[x][y] = temp;
				this.buildingList.add(temp);
				
				industrialSupply += 5;
				this.availableFunds -= this.INDUSTRIAL_COST;
			}
		} catch (IOException NoFile) {
			System.out.println("No file.");
		}
	}
	public void addRoad(int x, int y) {
		if (this.availableFunds < this.ROAD_COST) return;
		try {
			test = this.buildings[x][y];
			if (test instanceof Tile) {
				temp = new Road(x, y);
				copyTileInfo(temp, test);
				this.buildings[x][y] = temp;
				this.buildingList.add(temp);
				updateRoad(x, y);
				this.availableFunds -= this.ROAD_COST;
			}
		} catch (IOException NoFile) {
			System.out.println("No file.");
		}
	}
	
	private void copyTileInfo(Building A, Building B) {
		A.road = B.road;
		A.power = B.power;
		A.water = B.water;
		A.police = B.police;
		A.fire = B.fire;
		A.school = B.school;
		A.hospital = B.hospital;
	}
	
	private void updateRoad(int x, int y) {
		for (int i = 1; i <= 3; i++) {
			if (!(x - i < 0)) {
				temp = buildings[x - i][y];
				temp.road += 1;
			}
			if (!(x + 1 > 700)) {
				temp = buildings[x + i][y];
				temp.road += 1;
			}
			if (!(y - i < 0)) {
				temp = buildings[x][y - i];
				temp.road += 1;
			}
			if (!(y + i > 700)) {
				temp = buildings[x][y + i];
				temp.road += 1;
			}
		}
	}
	
	private void updateCounts() {
		Iterator<Building> buildingIter = this.buildingList.iterator();
		while (buildingIter.hasNext()) {
			temp = buildingIter.next();
			if (temp instanceof Residential && temp.road > 0 && temp.residents == 0 && residentialDemand > 0) {
				temp.residents += 5;
				population += 5;
				residentialDemand -= 5;
				commercialDemand += 1;
				industrialDemand += 1;
				
				income += (10000 * this.residentialTax);
				try {
					temp.image = ImageIO.read(new File("Images/Residential_Built.png"));
				} catch (IOException NoFile) {
					// oh well
				}
			}
			
			if (temp instanceof Commercial && temp.road > 0 && temp.jobs == 0 && commercialDemand > 0) {
				temp.jobs += 2;
				cJobs += 2;
				commercialDemand -= 2;
				residentialDemand += 5;
				
				income += (7500 * this.commercialTax);
				try {
					temp.image = ImageIO.read(new File("Images/Commercial_Built.png"));
				} catch (IOException NoFile) {
					// oh well
				}
			}
			
			if (temp instanceof Industrial && temp.road > 0 && temp.jobs == 0 && industrialDemand > 0) {
				temp.jobs += 2;
				iJobs += 2;
				industrialDemand -= 2;
				residentialDemand += 5;
				
				income += (5000 * this.industrialTax);
				try {
					temp.image = ImageIO.read(new File("Images/Industrial_Built.png"));
				} catch (IOException NoFile) {
					// oh well
				}
			}
		}
	}
	
	private void updateFunds() {
		this.availableFunds += (this.income - this.payments);
	}
}
