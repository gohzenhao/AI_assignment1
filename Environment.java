package assignment1;


import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class Environment {
	
	public ArrayList<RoadEntry> roads;
	public ArrayList<Node> nodes;
	String initialRoad;
	String goalRoad;
	
	public void setInitial(String inRoad){
		this.initialRoad =inRoad;
	}
	
	public void setGoal(String inRoad){
		this.goalRoad = inRoad;
	}
	
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
			
			this.addChildren(this.nodes.get(i),junc[i]);
		}
	

		
	}
	
	public void addGoals(String startingRoad,String endingRoad,int start,int end){
		
		if(this.findNode("initial")!=null && this.findNode("goal")!=null){
			
			this.deleteNode();	
			
		}
		
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
		
		double cost = startRoad.distanceToStartJunc(start);
		double cost2 = startRoad.distanceToEndJunc(start);
		double cost3 = endRoad.distanceToStartJunc(end);
		double cost4 = endRoad.distanceToEndJunc(end);
		
		Edge newEdge = new Edge(node1,cost,startingRoad);
		Edge newEdge2 = new Edge(node2,cost2,startingRoad);
		Edge newEdge3 = new Edge(node3,cost3,endingRoad);
		Edge newEdge4 = new Edge(node4,cost4,endingRoad);
		
		startingNode.addChildren(newEdge);
		startingNode.addChildren(newEdge2);
		goalNode.addChildren(newEdge3);
		goalNode.addChildren(newEdge4);
		startingNode.setPlotNum(2);
		goalNode.setPlotNum(3);
		
//		Edge edge = new Edge(startingNode,cost,startingRoad);
//		Edge edge2 = new Edge(startingNode,cost2,startingRoad);
//		node1.addChildren(edge);
//		node2.addChildren(edge2);
		Edge edge3 = new Edge(goalNode,cost3,endingRoad);
		Edge edge4 = new Edge(goalNode,cost4,endingRoad);
		node3.addChildren(edge3);
		node4.addChildren(edge4);
		
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
	
	public void addChildren(Node inNode,String inJunc){
		

		for(int i=0;i<this.roads.size();i++){
			if(this.roads.get(i).startJunc.equals(inJunc)){
				Node adjency = this.findNode(this.roads.get(i).endJunc);
				
	
				String roadName = this.getRoadName(inNode, adjency);
				RoadEntry inRoad = this.findRoad(roadName);
				double cost = inRoad.getRoadLength();
				

				Edge newEdge = new Edge(adjency,cost,roadName);
				this.findNode(inJunc).addChildren(newEdge);
				
			}
			else if(this.roads.get(i).endJunc.equals(inJunc)){
				Node adjency = this.findNode(this.roads.get(i).startJunc);
//				adjency.setIsStart();
				String roadName = this.getRoadName(inNode, adjency);
				RoadEntry inRoad = this.findRoad(roadName);
				double cost = inRoad.getRoadLength();
				Edge newEdge = new Edge(adjency,cost,roadName);
				this.findNode(inJunc).addChildren(newEdge);
				
			}
		}
		
		
	}
	
	public String getRoadName(Node node, Node node2){
		
		String junc1 = node.junction;
		String junc2 = node2.junction;
		
		if(node.junction=="initial"){
			return initialRoad;
		}
		if(node2.junction=="goal"){
			return goalRoad;
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
	
	public void deleteNode(){
		
		for(int i=0;i<this.nodes.size();i++){
			if(this.nodes.get(i).junction.equals("initial")){
				this.nodes.remove(i);
			}
			if(this.nodes.get(i).junction.equals("goal")){
				this.nodes.remove(i);
			}
		}
		
	}
	

	
	
	
//	public void getJuncPosition(String instreet,Node inNode){
//		
//		RoadEntry inRoad = this.findRoad(instreet);
//		if(inRoad.startJunc==inNode.junction){
//			inNode.setStart(true);
//		}
//		else{
//			inNode.setStart(false);;
//		}
//	}
	

}
