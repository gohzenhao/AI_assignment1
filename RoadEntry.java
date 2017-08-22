package assignment1;

import java.util.ArrayList;

public class RoadEntry {
	
	public String roadName;
	public String startJunc;
	public String endJunc;
	public int roadLength;
	public int nLots;

	
	public RoadEntry(String roadName,String junction1,String junction2,int roadLength, int nLots){
		this.roadName = roadName;
		this.startJunc = junction1;
		this.endJunc = junction2;
		this.roadLength = roadLength;
		this.nLots = nLots;

	}

	public String getRoadName() {
		return roadName;
	}


	public String getStartJunc() {
		return startJunc;
	}

	public void setStartJunc(String startJunc) {
		this.startJunc = startJunc;
	}

	public String getEndJunc() {
		return endJunc;
	}

	public void setEndJunc(String endJunc) {
		this.endJunc = endJunc;
	}

	public int getRoadLength() {
		return roadLength;
	}


	public int getnLots() {
		return nLots;
	}
	
	public double getCostBetweenEachLot(){
		return (double) (roadLength/(nLots/2.0));
	}
	
	public double distanceToStartJunc(double lotNum){
		if((lotNum%2)==0){
			lotNum = lotNum-1;
			return (double) (lotNum/2)*getCostBetweenEachLot();
			
			
		}
		else{
			return (double) ((lotNum/2)*getCostBetweenEachLot());
		}
	}
	
	public double distanceToEndJunc(double lotNum){
		
		return (roadLength-(distanceToStartJunc(lotNum)));
		
	}
	
	
	
}
	
	
	

	

	
	
	
	


