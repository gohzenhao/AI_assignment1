package assignment1;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.PriorityQueue;

public class FindPath {
	
	Environment map;
	String initialRoad;
	String goalRoad;


	public FindPath(Environment inMap,String inRoad, String inRoad2){
		
		this.map = inMap;
		this.initialRoad = inRoad;
		this.goalRoad = inRoad2;
		
	}
	
	public static class PriorityList extends LinkedList {

	    public void add(Comparable object) {
	      for (int i=0; i<size(); i++) {
	        if (object.compareTo(get(i)) <= 0) {
	          add(i, object);
	          return;
	        }
	      }
	      addLast(object);
	    }
	  }
	
	public Node getStart(){
		return this.map.findNode("initial");
	}
	
	public Node getEnd(){
		return this.map.findNode("goal");
	}
	
	protected List constructPath(Node node) {
	    LinkedList path = new LinkedList();
	    while (node.parent != null) {
	      path.addFirst(node);
	      node = node.parent;
	    }
	    return path;
	  }
	
	public List compute(){
		
		PriorityList openList = new PriorityList();
	    LinkedList closedList = new LinkedList();
	    double totalCost = 0;
	    Node startNode = this.getStart();
	    Node goalNode = this.getEnd();
	    String streetName;
	    
	    startNode.costFromStart = 0;
	    openList.add(startNode);
	    System.out.println("Added :"+startNode.junction);
		
	    while(!openList.isEmpty()){
	    	Node currentNode = (Node)openList.removeFirst();
	    	System.out.println("Current Node: "+currentNode.junction);
	    	if(currentNode == goalNode){
	    		System.out.println("goalFound");
	    		return constructPath(goalNode);
	    		
	    	}
	    	
	    	ArrayList<Node> neighbors = currentNode.getChildren();
	    	System.out.println(neighbors);
	    	for(int i=0;i<neighbors.size();i++){
	    		Node neighborNode = neighbors.get(i);
	    		System.out.println(neighborNode.street);
		    	boolean isOpen = openList.contains(neighborNode);
		    	boolean isClosed = closedList.contains(neighborNode);
		    	double costFromStart;
		    	double costBetweenNode;
		    	if(currentNode.isPlot){
		    		System.out.println("1");
		    		streetName = currentNode.street;
		    		RoadEntry inRoad = map.findRoad(streetName);
		    		map.getJuncPosition(streetName, neighborNode);
		    		System.out.println("Move to :" + neighborNode.street);
		    		if(neighborNode.isStart){
		    			costBetweenNode= inRoad.distanceToStartJunc(2);
		    			
		    		}
		    		else{
		    			costBetweenNode = inRoad.distanceToEndJunc(2);
		    		}
		    	}
		    	else{
		    		streetName = map.getRoadName(currentNode, neighborNode);
		    		RoadEntry inRoad = map.findRoad(streetName);
		    		costBetweenNode = inRoad.roadLength;
		    		System.out.println("4");
		    	}
		    	
		    	costFromStart = currentNode.costFromStart + costBetweenNode;
		    	if((!isOpen && !isClosed) || costFromStart < neighborNode.costFromStart){
		    		neighborNode.parent = currentNode;
		    		neighborNode.costFromStart = costFromStart;
		    		if(isClosed){
		    			closedList.remove(neighborNode);
		    		}
		    		if(!isOpen){
		    			openList.add(neighborNode);
		    		}
		    	}
	    	}
	    	closedList.add(currentNode);
	    }
	    return null;
		
		

		
	}

}
