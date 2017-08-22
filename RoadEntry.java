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
		return roadLength/(nLots/2.0);
	}
	
	public double distanceBetweenTwoNodes(int lotNum1, int lotNum2)
	{
		if(lotNum1%2==0)
			lotNum1=lotNum1-1;
		if(lotNum2%2==0)
			lotNum2=lotNum2-1;
		if(lotNum1==lotNum2)
			return 0;
		else
			return (Math.abs(lotNum1-lotNum2)/2*getCostBetweenEachLot());
	}
	
	public double distanceToStartJunc(double lotNum){
		if(lotNum<=nLots)
		{
			if((lotNum%2)==0)
				lotNum = lotNum-1;
			return (lotNum/2)*getCostBetweenEachLot();
		}
		else
			return -1;
	}
	
	public double distanceToEndJunc(double lotNum){
		if(lotNum<=nLots)
			return (roadLength-(distanceToStartJunc(lotNum)));
		return -1;
	}
	
}
	
	
	

	

	
	
	
	


