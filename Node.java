package assignment1;

import java.util.ArrayList;

public class Node {
	
	String junction;
	Node parent;
	ArrayList<Node> children;
	
	public Node(String inJunction){
		
		this.junction = inJunction;
		this.parent = null;
		this.children = new ArrayList<>();
		
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



}
