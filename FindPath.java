package assignment1;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.PriorityQueue;

public class FindPath {
	
	Environment map;
	String initial;
	String goal;
	RoadEntry initialRoad;
	RoadEntry goalRoad;
	int cost;
	int costToNode;
	int totalCost;
	
	
	
	public FindPath(Environment inMap,String inInitial,String inGoal){
		
		this.map = inMap;
		this.initial = "2Warren";
		this.goal = "3Durham";
		compute();
		System.out.println(cost);
		
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
	
	protected List constructPath(Node node) {
	    LinkedList path = new LinkedList();
	    while (node.parent != null) {
	      path.addFirst(node);
	      node = node.parent;
	    }
	    return path;
	  }
	
	public void compute(){
		
		PriorityList openList = new PriorityList();
	    LinkedList closedList = new LinkedList();
	    ArrayList returnNodes = new ArrayList();
		
		initialRoad = map.findRoad("Warren");
		goalRoad = map.findRoad("Durham");
		if(initialRoad!=null){

		}
		
		
		
		
		
		
		
	}

}
