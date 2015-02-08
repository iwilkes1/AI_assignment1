package edu.jhu.cs.wilkes.ai_assignment1;

import java.util.ArrayList;

public class GridSearchPath {

	private static final int START_PATH_COST = 0;
	private ArrayList<Position> path;
	private int cost;
	
	/**
	 * base constructor for a grid search path.  starting path cost is 0.
	 */
	public GridSearchPath() {
		this.path = new ArrayList<Position>();
		this.cost = START_PATH_COST;
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
		this.addNode(toAdd);
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
	
}
