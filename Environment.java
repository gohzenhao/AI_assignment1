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
	

	
	public void generateNodes(){
		String start ="";
		String end ="";
		Set<String> uniqueJunc = new HashSet<String>();
		
		
		for(int i=0;i<this.roads.size();i++){
			start = this.roads.get(i).startJunc;
			end = this.roads.get(i).endJunc;
			uniqueJunc.add(start);
			uniqueJunc.add(end);
		}
		String[] junc = uniqueJunc.toArray(new String[0]);
		for(int i=0;i<junc.length;i++){
			Node newNode = new Node(junc[i]);
			this.nodes.add(newNode);
		}
	
		for(int i=0;i<this.nodes.size();i++){
			this.addChildren(this.nodes.get(i).junction);
		}

		
	}
	
	public void addGoals(String startingRoad,String endingRoad){
		Node startingNode = new Node("initial");
		Node goalNode = new Node("goal");
		RoadEntry startRoad = this.findRoad(startingRoad);
		RoadEntry endRoad = this.findRoad(endingRoad);
		
		String junc1 = startRoad.startJunc;
		String junc2 = startRoad.endJunc;
		String junc3 = endRoad.startJunc;
		String junc4 = endRoad.endJunc;
		
		Node node1 = this.findNode(junc1);
		Node node2 = this.findNode(junc2);
		Node node3 = this.findNode(junc3);
		Node node4 = this.findNode(junc4);
		
		startingNode.addChildren(node1);
		startingNode.addChildren(node2);
		goalNode.addChildren(node3);
		goalNode.addChildren(node4);
		startingNode.setIsPlot();
		goalNode.setIsPlot();
		startingNode.setPlotNum(2);
		goalNode.setPlotNum(3);
		startingNode.setStreet(startRoad.roadName);
		goalNode.setStreet(endRoad.roadName);
		
		this.nodes.add(startingNode);
		this.nodes.add(goalNode);
		
		
	}
	
	
	public Node findNode(String juncName){
		Node result = null;
		for(int i=0;i<this.nodes.size();i++){
			if(this.nodes.get(i).junction.equals(juncName)){
				result = this.nodes.get(i);
				return result;
			}
		}
		return result;
	}
	
	public void addChildren(String inJunc){
		
		
		for(int i=0;i<this.roads.size();i++){
			if(this.roads.get(i).startJunc.equals(inJunc)){
				this.findNode(inJunc).addChildren(this.findNode(this.roads.get(i).endJunc));
			}
			else if(this.roads.get(i).endJunc.equals(inJunc)){
				this.findNode(inJunc).addChildren(this.findNode(this.roads.get(i).startJunc));
			}
		}
		
		
	}
	
	public String getRoadName(Node node, Node node2){
		
		String junc1 = node.junction;
		String junc2 = node2.junction;
		
		if(node.junction=="initial"){
			return node.street;
		}
		if(node2.junction=="goal"){
			return node2.street;
		}
		for(int i=0;i<this.roads.size();i++){
			if(this.roads.get(i).startJunc.equals(junc1) && this.roads.get(i).endJunc.equals(junc2)){
				return this.roads.get(i).roadName;
			}
			if(this.roads.get(i).startJunc.equals(junc2) && this.roads.get(i).endJunc.equals(junc1)){
				return this.roads.get(i).roadName;
			}
		}
		return "";
	}
	
	public String getPlotStreet(Node node){
		return node.street;
	}
	
	
	
	public void getJuncPosition(String instreet,Node inNode){
		
		RoadEntry inRoad = this.findRoad(instreet);
		if(inRoad.startJunc==inNode.junction){
			inNode.setStart(true);
		}
		else{
			inNode.setStart(false);;
		}
	}
	

}
