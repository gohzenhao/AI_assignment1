package assignment1;


import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.StringTokenizer;

public class Environment {
	
	public ArrayList<RoadEntry> roads;
	public ArrayList<Node> nodes;
	String initialRoad;
	String goalRoad;
	public HashMap<String,Node> hm = new HashMap<String,Node>();
	
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
	
	public Node addNode(Node inNode)
	{
		this.nodes.add(inNode);
		return inNode;
	}
	
	public int getSize(){
		return this.roads.size();
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
	
	public Node findNode(String juncName){
		Node result = null;
		for(int i=0;i<this.nodes.size();i++){
			System.out.println("Finding...");
			if(this.nodes.get(i).junction.equals(juncName)){
				result = this.nodes.get(i);
				return result;
			}
		}
		return result;
	}
	
	public void addJunctions(String roadName,String j1,String j2,int roadLength){
		Node startJunc,endJunc;
		Node checkSJ = this.hm.get(j1);
		if(checkSJ==null){
			startJunc = this.addNode(new Node(j1));
			this.hm.put(j1, startJunc);
		}
		else{
			startJunc = this.hm.get(j1);
		}
		Node checkEJ = this.hm.get(j2);
		if(checkEJ==null){
			endJunc = this.addNode(new Node(j2));
			this.hm.put(j2,endJunc);
	}
		else{
			endJunc = this.hm.get(j2);
		}
		Edge edge1 = new Edge(endJunc,roadLength,roadName);
		Edge edge2 = new Edge(startJunc,roadLength, roadName);
		startJunc.addChildren(edge1);
		endJunc.addChildren(edge2);	
	}
	
	public boolean addGoals(String startingRoad,String endingRoad,int start,int end){
		
		if(this.findNode("initial")!=null && this.findNode("goal")!=null){
			
			this.deleteNode();
			for(int i=0;i<this.nodes.size();i++){
				this.nodes.get(i).deleteEdge();
			}
			
		}

		Node startingNode = new Node("initial");
		Node goalNode = new Node("goal");
		RoadEntry startRoad = this.findRoad(startingRoad);
		RoadEntry endRoad = this.findRoad(endingRoad);
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
				
				Node node1 = this.findNode(junc1);
				Node node2 = this.findNode(junc2);
				Node node3 = this.findNode(junc3);
				Node node4 = this.findNode(junc4);
				
				double cost1 = startRoad.distanceToStartJunc(start);
				double cost2 = startRoad.distanceToEndJunc(start);
				double cost3 = endRoad.distanceToStartJunc(end);
				double cost4 = endRoad.distanceToEndJunc(end);
		
				
				Edge newEdge = new Edge(node1,cost1,startingRoad);
				Edge newEdge2 = new Edge(node2,cost2,startingRoad);
		
				
				startingNode.addChildren(newEdge);
				startingNode.addChildren(newEdge2);
		
				Edge edge3 = new Edge(goalNode,cost3,endingRoad);
				Edge edge4 = new Edge(goalNode,cost4,endingRoad);
		
				node3.addChildren(edge3);
				node4.addChildren(edge4);
			}			
			this.nodes.add(startingNode);
			this.nodes.add(goalNode);
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
	public static void main(String[] args)
	{
		File environmentFile = new File("src/assignment1/no_repeats_1000000.txt");
		FileReader fr = null;
		BufferedReader br = null;
		
		String roadName,j1,j2;
		int roadLength,nLots;
		RoadEntry newRoad;
		Environment map = new Environment();
		FindPath findPath;
		
		String initialRoad ="";
		String goalRoad ="";
		int startPlot=0;
		int goalPlot=0;
		
		ArrayList<Query> queries = new ArrayList<>();
		
		long start = System.nanoTime();
		try {
			if(environmentFile.exists())
			{

				fr = new FileReader(environmentFile);
				br = new BufferedReader(fr);
				String line = br.readLine();
			
				try
				{
					while(line!=null)
					{
						StringTokenizer st = new StringTokenizer(line," ; ");
						roadName = st.nextToken();
						j1 = st.nextToken();
						j2 = st.nextToken();
						roadLength = Integer.parseInt(st.nextToken());
						nLots = Integer.parseInt(st.nextToken());
						newRoad = new RoadEntry(roadName,j1,j2,roadLength,nLots);
						map.addRoad(newRoad);
						map.addJunctions(roadName, j1, j2, roadLength);
						line = br.readLine();						
					}

				}
				catch (IOException e)
				{
					System.out.println(e.getMessage());
				}
			}
		}
		catch (IOException e)
		{
			System.out.println(e.getMessage());
		}
		finally
		{
			try// if the file was opened, close it
			{
				if (fr != null)
				fr.close();
			}
			catch (IOException ioe)
			{
				System.out.println(ioe.getMessage());
			}

		}
		long end = System.nanoTime();
		long result = (end-start)/1000000000;
		System.out.println(result+" seconds ");
//		for(RoadEntry r:map.getRoads())
//		{
//			System.out.println(r.getRoadName());
//		}
		
//		for(Node n:map.getNodes())
//		{
//			System.out.print(n.getJunction());
//			System.out.print("Children: ");
//			for(Edge e:n.getAdjencies())
//			{
//				System.out.print(e.getTarget().getJunction()+" , ");
//			}
//			System.out.println("");
//		}

	}

}
