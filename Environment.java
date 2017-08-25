package assignment1;


import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.StringTokenizer;

public class Environment {

	String initialRoad;
	String goalRoad;
	Node startingNode=null;
	Node goalNode=null;
	public HashMap<String,Node> nodes = new HashMap<String,Node>();
	public HashMap<String,RoadEntry> roads = new HashMap<String,RoadEntry>();
	
	public void setInitial(String inRoad){
		this.initialRoad =inRoad;
	}
	
	public void setGoal(String inRoad){
		this.goalRoad = inRoad;
	}
	
	public HashMap<String,RoadEntry> getRoads() {
		return roads;
	}

	public void setRoads( HashMap<String,RoadEntry> roads) {
		this.roads = roads;
	}

	public HashMap<String,Node> getNodes() {
		return nodes;
	}

	public void setNodes(HashMap<String,Node> nodes) {
		this.nodes = nodes;
	}

	public Environment(){		
		this.roads= new HashMap<String,RoadEntry>();
		this.nodes = new HashMap<String,Node>();
		this.startingNode = null;
		this.goalNode = null;
	}
	
	public void addRoad(RoadEntry inRoad){
		this.roads.put(inRoad.getRoadName(),inRoad);
	}
	
	public Node addNode(Node inNode)
	{
		this.nodes.put(inNode.getJunction(),inNode);
		return inNode;
	}
	
	public int getSize(){
		return this.roads.size();
	}
	
	public RoadEntry findRoad(String streetName){
		return this.roads.get(streetName);
	}
	
	public Node findNode(String juncName){
		return this.nodes.get(juncName);
	}
	
	public void addJunctions(String roadName,String j1,String j2,int roadLength){
		Node startJunc,endJunc;
		Node checkSJ = findNode(j1);
		if(checkSJ==null){
			startJunc = this.addNode(new Node(j1));
			addNode(startJunc);
			System.out.println("adding : "+startJunc.junction);
		}
		else{
			startJunc = findNode(j1);
		}
		Node checkEJ = findNode(j2);
		if(checkEJ==null){
			endJunc = this.addNode(new Node(j2));
			addNode(endJunc);
			System.out.println("adding : "+endJunc.junction);
	}
		else{
			endJunc = findNode(j2);
		}
		Edge edge1 = new Edge(endJunc,roadLength,roadName);
		Edge edge2 = new Edge(startJunc,roadLength, roadName);
		startJunc.addChildren(edge1);
		endJunc.addChildren(edge2);	
		
	}
	
	public boolean addGoals(String startingRoad,String endingRoad,int start,int end){
		
		if(startingNode!=null && goalNode!=null){
			System.out.println("EXISTING GOAL FOUND :");
			startingNode=null;
			for(int i=0;i<2;i++)
			{
				ArrayList<Edge> adjencies=goalNode.getAdjencies().get(i).getTarget().getAdjencies();
				for(int j=0;j<adjencies.size();j++)
				{
					Edge edge = adjencies.get(j);
					if(edge.getTarget().getJunction().equals("goal"))
						adjencies.remove(j);
				}
			}
			goalNode=null;
		}

		Node startingNode = new Node("initial");
		this.startingNode = startingNode;
		Node goalNode = new Node("goal");
		this.goalNode = goalNode;
		RoadEntry startRoad = this.roads.get(startingRoad);
		RoadEntry endRoad = this.roads.get(endingRoad);
		if(startRoad!=null && endRoad!= null && start<=startRoad.nLots && end<=endRoad.nLots && start>0 && end>0)
		{
			if(startRoad.equals(endRoad))
			{
				double cost = startRoad.distanceBetweenTwoNodes(start, end);
				Edge edge = new Edge(goalNode,cost,startingRoad);
				startingNode.addChildren(edge);
			}
			else
			{
				String junc1 = startRoad.startJunc;
				String junc2 = startRoad.endJunc;
				String junc3 = endRoad.startJunc;
				String junc4 = endRoad.endJunc;
			
				Node node1 = this.nodes.get(junc1);
				Node node2 = this.nodes.get(junc2);
				Node node3 = this.nodes.get(junc3);
				Node node4 = this.nodes.get(junc4);
				
				double cost1 = startRoad.distanceToStartJunc(start);
				double cost2 = startRoad.distanceToEndJunc(start);
				double cost3 = endRoad.distanceToStartJunc(end);
				double cost4 = endRoad.distanceToEndJunc(end);
		
				
				Edge newEdge = new Edge(node1,cost1,startingRoad);
				Edge newEdge2 = new Edge(node2,cost2,startingRoad);
				Edge newEdge3 = new Edge(node3,cost3,startingRoad);
				Edge newEdge4 = new Edge(node4,cost4,startingRoad);
		
				
				startingNode.addChildren(newEdge);
				startingNode.addChildren(newEdge2);
				goalNode.addChildren(newEdge3);
				goalNode.addChildren(newEdge4);
		
				Edge edge3 = new Edge(goalNode,cost3,endingRoad);
				Edge edge4 = new Edge(goalNode,cost4,endingRoad);
		
				node3.addChildren(edge3);
				node4.addChildren(edge4);
			}
			return true;
		}
		return false; 
	}
//	
//	public void addChildren(Node inNode,String inJunc){
//		
//
//		for(int i=0;i<this.roads.size();i++){
//			if(this.roads.get(i).startJunc.equals(inJunc)){
//				Node adjency = this.findNode(this.roads.get(i).endJunc);
//				
//	
//				String roadName = this.getRoadName(inNode, adjency);
//				RoadEntry inRoad = this.findRoad(roadName);
//				double cost = inRoad.getRoadLength();
//				
//
//				Edge newEdge = new Edge(adjency,cost,roadName);
//				this.findNode(inJunc).addChildren(newEdge);
//				
//			}
//			else if(this.roads.get(i).endJunc.equals(inJunc)){
//				Node adjency = this.findNode(this.roads.get(i).startJunc);
////				adjency.setIsStart();
//				String roadName = this.getRoadName(inNode, adjency);
//				RoadEntry inRoad = this.findRoad(roadName);
//				double cost = inRoad.getRoadLength();
//				Edge newEdge = new Edge(adjency,cost,roadName);
//				this.findNode(inJunc).addChildren(newEdge);
//				
//			}
//		}
//		
//		
//	}
//	
//	public String getRoadName(Node node, Node node2){
//		
//		String junc1 = node.junction;
//		String junc2 = node2.junction;
//		
//		if(node.junction=="initial"){
//			return initialRoad;
//		}
//		if(node2.junction=="goal"){
//			return goalRoad;
//		}
//		for(int i=0;i<this.roads.size();i++){
//			if(this.roads.get(i).startJunc.equals(junc1) && this.roads.get(i).endJunc.equals(junc2)){
//				return this.roads.get(i).roadName;
//			}
//			if(this.roads.get(i).startJunc.equals(junc2) && this.roads.get(i).endJunc.equals(junc1)){
//				return this.roads.get(i).roadName;
//			}
//		}
//		return "";
//	}
//	
//	public void deleteNode(){
//		
//		for(int i=0;i<this.nodes.size();i++){
//			if(this.nodes.get(i).junction.equals("initial")){
//				this.nodes.remove(i);
//			}
//			if(this.nodes.get(i).junction.equals("goal")){
//				this.nodes.remove(i);
//			}
//		}
//		
//	}
	
	

	


	}


