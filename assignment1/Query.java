package assignment1;

public class Query {
	
	public String sRoad;
	public String gRoad;
	public int sPlot;
	public int gPlot;
	
	
	public Query(String startRoad,String endRoad,int startPlot,int goalPlot){
		
		this.sRoad = startRoad;
		this.gRoad = endRoad;
		this.sPlot = startPlot;
		this.gPlot = goalPlot;
		
	}

}
