package assignment1;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class Environment {
	
	public ArrayList<RoadEntry> roads;
	public ArrayList<Node> nodes;
	
	public ArrayList<RoadEntry> getRoads() {
		return roads;
	}

	public void setRoads(ArrayList<RoadEntry> roads) {
		this.roads = roads;
	}

	public ArrayList<Node> getNodes() {
		return nodes;
	}

	public void setNodes(ArrayList<Node> nodes) {
		this.nodes = nodes;
	}

	public Environment(){
		
		this.roads= new ArrayList<>();
		this.nodes = new ArrayList<>();
		
		
	}
	
	public void addRoad(RoadEntry inRoad){
		String junction1 = inRoad.junction1;
		String junction2 = inRoad.junction2;
		this.roads.add(inRoad);
	}
	
	public int getSize(){
		return this.roads.size();
	}
	
	public ArrayList<Node> getNodeArray(){
		
		return this.nodes;
		
	}
	
	public ArrayList<RoadEntry> getRoadArray(){
		return this.roads;
	}

	
	public RoadEntry findRoad(String streetName){

		RoadEntry foundRoad = null;
		for(int i =0;i<this.roads.size();i++){
			
			if(this.roads.get(i).getRoadName().equals(streetName)){
				foundRoad = this.roads.get(i);
			}
		}
		return foundRoad;
	}
	
	public void registerChildren(RoadEntry inRoad){
		
	}
	
	public void generateNodes(){
		
		List<String> uniqueJunc = new ArrayList<>();
		Set<String> hs = new HashSet<>();
		
		for(int i=0;i<this.roads.size();i++){
			
			uniqueJunc.add(this.roads.get(i).junction1);
			uniqueJunc.add(this.roads.get(i).junction2);
			
		}
		hs.addAll(uniqueJunc);
		uniqueJunc.clear();
		uniqueJunc.addAll(hs);
		for(int i=0;i<uniqueJunc.size();i++){
			System.out.println(uniqueJunc.get(i));
		}
	}
	
	



}
