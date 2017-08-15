package assignment1;

public class RoadEntry {
	
	public String roadName;
	public String junction1;
	public String junction2;
	public int roadLength;
	public int nLots;

	
	public RoadEntry(String roadName,String junction1,String junction2,int roadLength, int nLots){
		this.roadName = roadName;
		this.junction1 = junction1;
		this.junction2 = junction2;
		this.roadLength = roadLength;
		this.nLots = nLots;

	}

	public String getRoadName() {
		return roadName;
	}

	public String getJunction1() {
		return junction1;
	}


	public String getJunction2() {
		return junction2;
	}


	public int getRoadLength() {
		return roadLength;
	}


	public int getnLots() {
		return nLots;
	}
	
	
	

	

	
	
	
	

}
