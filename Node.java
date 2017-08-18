package assignment1;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class Node{
	
	String junction;
	Node parent;
	ArrayList<Node> children;
	double costFromStart;
	int plotNum;
	boolean isPlot;
	boolean isStart;
	
	public Node(String inJunction){
		
		this.junction = inJunction;
		this.parent = null;
		this.children = new ArrayList<>();
		this.isPlot = false;
		
	}
	
	public int getPlotNum(){
		return this.plotNum;
	}
	public void setPlotNum(int inNum){
		this.plotNum = inNum;
	}
	
	public void setIsPlot(){
		this.isPlot = !this.isPlot;
	}



	public ArrayList<Node> getChildren() {
		return children;
	}


	public void setChildren(ArrayList<Node> children) {
		this.children = children;
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
	
	public void addChildren(Node inNode){
		this.children.add(inNode);
	}

	public double getCostFromStart() {
		return costFromStart;
	}

	public void setCostFromStart(double costFromStart) {
		this.costFromStart = costFromStart;
	}

	public boolean isStart() {
		return isStart;
	}

	public void setStart(boolean isStart) {
		this.isStart = isStart;
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
