package edu.jhu.cs.wilkes.ai_assignment1;

import java.util.ArrayList;

/**
 * Class used to store all the nodes of the search path in one place.
 * Somewhat deprecated by storing a parent in the SearchPosition Class.
 * @author Ian Wilkes
 *
 */
public class GridSearchPath implements Comparable<GridSearchPath> {
	
	private MapPosition goal;
	private static final int START_PATH_COST = 0;
	private ArrayList<MapPosition> path;
	private int cost;
	
	/**
	 * base constructor for a grid search path.  starting path cost is 0.
	 * @param the goal for this search path.
	 */
	public GridSearchPath(MapPosition goal) {
		this.path = new ArrayList<MapPosition>();
		this.cost = START_PATH_COST;
		this.goal = goal;
	}
	
	/**
	 * Constructor to create a new path from an existing path and a new node.
	 * @param tail the old path used as the starting point.
	 * @param toAdd the new node to be added to the end of the path.
	 */
	public GridSearchPath(GridSearchPath tail, MapPosition toAdd) {
		this.goal = tail.goal;
		this.cost = START_PATH_COST;
		this.path = new ArrayList<MapPosition>();
		for (MapPosition p: tail.path) {
			this.addNode(p);
		}
		this.addNode(toAdd);
	}
	
	/**
	 * Adds a node to an existing path at the end of the path and updates the path.
	 * If the path being added to is empty, then the node added is the start node, so no cost is added.
	 * @param toAdd the new node to add. 
	 */
	public void addNode(MapPosition toAdd) {
		if (this.path.size() != 0) {
			this.cost += toAdd.getCost();
		}
		this.path.add(toAdd);
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
		MapPosition currNode = this.getLastNode();
		double xDiff = goal.getX() - currNode.getX();
		double yDiff = goal.getY() - currNode.getY();
		return (Math.abs(xDiff) + Math.abs(yDiff) + 
				Math.sqrt(Math.pow(xDiff, 2) + Math.pow(yDiff, 2)))/2.0 ;
	}
	/**
	 * Returns the last element in the path.
	 * @return the last position visited on this path.
	 */
	public MapPosition getLastNode(){
		return this.path.get(this.path.size() - 1);
		
	}
	
	/**
	 * Method to print the given path. Prints coordinate pairs, and then the cost of the path. 
	 */
	public void printPath() {
		for (MapPosition p: this.path) {
			System.out.print("(" + p.getX() + "," + p.getY() + "), ");
		}
		System.out.println("\nPath cost: " + this.cost);
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
