package assignment1;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.PriorityQueue;
import java.util.Set;


public class FindPath {
	
	Environment map;



	public FindPath(Environment inMap){
		
		this.map = inMap;

	
	}
	
	
	public Node getStart(){
		return this.map.startingNode;
	}
	
	public Node getEnd(){
		return this.map.goalNode;
	}
	
//	protected List constructPath(Node node) {
//	    LinkedList path = new LinkedList();
//	    while (node.parent != null) {
//	      path.addFirst(node);
//	      node = node.parent;
//	    }
//	    return path;
//	  }
	
//	Comparator<Node> comparator = new Comparator<Node>() {
//	    @Override
//	    public int compare(Node i, Node j){
//            if(i.costFromStart > j.costFromStart){
//                return 1;
//            }
//
//            else if (i.costFromStart < j.costFromStart){
//                return -1;
//            }
//
//            else{
//                return 0;
//            }
//        }
//	};
	
	Comparator<Node> comparator = new Comparator<Node>() {
	    @Override
	    public int compare(Node i, Node j){
            if(i.getTotalCost() > j.getTotalCost()){
                return 1;
            }

            else if (i.getTotalCost() < j.getTotalCost()){
                return -1;
            }

            else{
                return 0;
            }
        }
	};
	
	  protected ArrayList<Node> constructPath(Node node) {
		  
		 ArrayList<Node> path = new ArrayList<>();

		    while (node.getParent() != null) {
		    	
		 
		      path.add(node);
		     	node = node.getParent();


		    }
		    return path;

		  
		  }
	
	public ArrayList<Node> compute(){
		
		PriorityQueue<Node> openList = new PriorityQueue<Node>(map.getNodes().size(),comparator);
	    LinkedList<Node> explored = new LinkedList<>();

	    Node startNode = this.getStart();
	    Node goalNode = this.getEnd();
	    if(startNode!=null && goalNode!=null)
	    {
		    startNode.setCostFromStart(0);
		    
		    openList.add(startNode);
		    
		    boolean goalNodeFound = false;
		    while(!openList.isEmpty()){
		    	
		    	Node current = openList.poll();
		    	
	
		    	if(current.equals(goalNode)){
		    		goalNodeFound = true;
		    		return constructPath(goalNode);
		    	}
		    	
		    	for(int i=0;i<current.getAdjencies().size();i++)
		    	{
	
		    		Edge edge = current.getAdjencies().get(i);
		    		Node child = edge.getTarget();	
		    		
		    		boolean isOpen = openList.contains(child);
		    		boolean isClosed = explored.contains(child);
		    		double cost = edge.getCost();
		    		double costFromStart = current.getCostFromStart() + cost;
		    		
		    		
		    		if((!isOpen && !isClosed) || costFromStart < child.costFromStart){
		    			child.setParent(current);
	
	
		    			child.costFromStart = costFromStart;

		    			openList.add(child);
	
		    		}
		    	}
		    	explored.add(current);
		    }
		    if(openList.isEmpty() && !goalNodeFound)
		    	return null;
	    }
	    return null;
		
		

		
	}

}
