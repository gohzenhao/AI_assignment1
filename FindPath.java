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
	    
	    startNode.costFromStart = 0;
	    openList.add(startNode);
		
	    while(!openList.isEmpty()){
	    	Node currentNode = (Node)openList.removeFirst();
	    	if(currentNode == goalNode){
	    		return constructPath(goalNode);
	    	}
	    	
	    	List neighbors = currentNode.getChildren();
	    	for(int i=0;i<neighbors.size();i++){
	    		Node neighborNode = (Node)neighbors.get(i);
		    	boolean isOpen = openList.contains(neighborNode);
		    	boolean isClosed = closedList.contains(neighborNode);

	    	}
	    }
		
		

		
	}

}
