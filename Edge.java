package assignment1;

public class Edge {
	
	 	public double cost;
	    public Node target;
	    public String streetName;

	    public Edge(Node targetNode, double costVal,String streetName){
	    	this.cost = costVal;
	        this.target = targetNode;
	        this.streetName = streetName;

	    }

		public double getCost() {
			return cost;
		}

		public Node getTarget() {
			return target;
		}

		public void setCost(double cost) {
			this.cost = cost;
		}

		public void setTarget(Node target) {
			this.target = target;
		}

}
