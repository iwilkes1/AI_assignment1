package edu.jhu.cs.wilkes.ai_assignment1;

import java.util.ArrayList;

public class GridSearchPath implements Comparable<GridSearchPath> {
	
	private Position goal;
	private static final int START_PATH_COST = 0;
	private ArrayList<Position> path;
	private int cost;
	
	/**
	 * base constructor for a grid search path.  starting path cost is 0.
	 * @param the goal for this search path.
	 */
	public GridSearchPath(Position goal) {
		this.path = new ArrayList<Position>();
		this.cost = START_PATH_COST;
		this.goal = goal;
		System.out.println("Base constructor pass");
	}
	
	/**
	 * Constructor to create a new path from an existing path and a new node.
	 * @param tail the old path used as the starting point.
	 * @param toAdd the new node to be added to the end of the path.
	 */
	public GridSearchPath(GridSearchPath tail, Position toAdd) {
		this.cost = START_PATH_COST;
		this.path = new ArrayList<Position>();
		for (Position p: tail.path) {
			this.addNode(p);
		}
		System.out.println("old nodes added");
		this.addNode(toAdd);
		System.out.println("new nodes");
	}
	
	/**
	 * Adds a node to an existing path at the end of the path and updates the path
	 * @param toAdd the new node to add. 
	 */
	private void addNode(Position toAdd) {
		this.path.add(toAdd);
		this.cost += toAdd.getCost();
	}
	
	/**
	 * returns the cost of the path
	 * @return the path cost.
	 */
	public int getCost() {
		return this.cost;
	}
	
	/**
	 * determines the A* cost of a path. 
	 * @param goal the node we are trying to reach.
	 * @return the predicted cost to reach that goal.
	 */
	private double getAStarCost() {
		return this.getCost() + heuristicCost();
	}
	
	/**
	 * heuristic cost based on the sum of manhattan and euclidian distance divided by two.
	 * @param goal the position of the goal
	 * @return the total heuristic value to get to that goal.
	 */
	private double heuristicCost() {
		Position currNode = this.getLastNode();
		double xDiff = goal.getX() - currNode.getX();
		double yDiff = goal.getY() - currNode.getY();
		return (Math.abs(xDiff) + Math.abs(yDiff) + 
				Math.sqrt(Math.pow(xDiff, 2) + Math.pow(yDiff, 2)))/2.0 ;
	}
	/**
	 * Returns the last element in the path.
	 * @return the last position visited on this path.
	 */
	public Position getLastNode(){
		return this.path.get(this.path.size() - 1);
		
	}
	
	/**
	 * Method to print the given path. Prints coordinate pairs, and then the cost of the path. 
	 */
	public void printPath() {
		for (Position p: this.path) {
			System.out.print("(" + p.getX() + "," + p.getY() + "), ");
		}
		System.out.println("Path cost: " + this.cost);
	}

	@Override
	public int compareTo(GridSearchPath other) {
		if (this.getAStarCost() > other.getAStarCost()) {
			return 1;
		} else if (this.getAStarCost() < other.getAStarCost()){
			return -1;
		} else {
			return 0;
		}
	}
	
	
	
}
