package assignment1;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class Node{
	
	String junction;
	Node parent;
	double costFromStart;
	int plotNum;
	ArrayList<Edge> adjencies;
	double estimatedCostToGoal;
	
	public Node(String inJunction){
		
		this.junction = inJunction;
		this.parent = null;
		this.adjencies = new ArrayList<>();
		this.costFromStart = 0;
		this.estimatedCostToGoal =0;
		
	}
	

	public ArrayList<Edge> getAdjencies() {
		return adjencies;
	}


	public void setAdjencies(ArrayList<Edge> adjencies) {
		this.adjencies = adjencies;
	}


	public int getPlotNum(){
		return this.plotNum;
	}
	public void setPlotNum(int inNum){
		this.plotNum = inNum;
	}

	public Node getParent() {
		return parent;
	}


	public void setParent(Node parent) {
		this.parent = parent;
	}


	public String getJunction() {
		return junction;
	}


	public void setJunction(String junction) {
		this.junction = junction;
	}
	
	public void addChildren(Edge inEdge){
		this.adjencies.add(inEdge);
	}

	public double getCostFromStart() {
		return costFromStart;
	}

	public void setCostFromStart(double costFromStart) {
		this.costFromStart = costFromStart;
	}
	
	public Edge findEdge(Node inNode){
		
		for(int i=0;i<this.getAdjencies().size();i++){
			
			if(this.getAdjencies().get(i).target.equals(inNode)){
				return this.getAdjencies().get(i);
			}			
		}
		return null;
	}
	
	public void deleteEdge(){
		for(int i=0;i<this.adjencies.size();i++){
			if(this.adjencies.get(i).target.junction=="initial"){
				this.adjencies.remove(i);
			}
			if(this.adjencies.get(i).target.junction=="goal"){
				this.adjencies.remove(i);
			}
		}
	}
	
	public double getEstimatedCostToGoal(Node goal){
		
		return 0;
	}
	
	public double getTotalCost(){
		return (this.costFromStart+this.estimatedCostToGoal);
	}


	
	

	
//	protected List constructPath(Node inNode){
//		LinkedList path = new LinkedList();
//		while(inNode.parent!=null){
//			path.addFirst(inNode);
//			inNode = inNode.parent;
//		}
//		return path;
//	}
	
	


}
