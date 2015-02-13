package edu.jhu.cs.wilkes.ai_assignment1;

/**
 * MapPosition class which will be used as the nodes in the GridMap.
 * Stores location information about the specific positions, as well as
 * their characters, and can tell if they are goals or not. 
 * @author Ian Wilkes
 *
 */
public class MapPosition {
	private int x;
	private int y;
	private int cost;
	private char positionChar;
	private static final int LOW_TRAVERSAL_COST = 1;
	private static final int HIGH_TRAVERSAL_COST = 2;
	
	/**
	 * constructor for the position class
	 * @param x the x location of this position
	 * @param y the y location of this position
	 * @param positionChar the charachter at this position.
	 */
	public MapPosition(int x, int y, char positionChar) {
		this.x = x;
		this.y = y;
		this.positionChar = positionChar;
		if (positionChar == '.' || positionChar == 's' || positionChar == 'g') {
			this.cost = LOW_TRAVERSAL_COST;	
		} else {
			this.cost = HIGH_TRAVERSAL_COST;
		}
	}
	
	/**
	 * getter for the x location
	 * @return the x location of this position
	 */
	public int getX() {
		return this.x;
	}
	
	/**
	 * getter for the y location
	 * @return the y location of this position
	 */
	public int getY() {
		return this.y;
	}
	
	/**
	 * Method to get the cost to move into this node
	 * @return the cost required to move to this position.
	 */
	public int getCost(){
		return this.cost;
	}
	
	/** 
	 * method to get the character of this position
	 * @return the character associated with this position.
	 */
	public char getChar() {
		return this.positionChar;
	}
	
	/**
	 * checks whether this node is the goal
	 * @return true if it is the goal, false otherwise
	 */
	public boolean isGoal() {
		return this.positionChar == 'g';
	}
	
	/**
	 * checks whether this node is the start
	 * @return true if it is the start, false otherwise
	 */
	public boolean isStart() {
		return this.positionChar == 's';
	}
	
	/**
	 * Checks whether this node is traversable.  all types except for # are traversable
	 * @return true if the character of this node is not #
	 */
	public boolean isTraversable() {
		return this.positionChar != '#';
	}

	/**
	 * equals method for comparing positions.
	 * @param the other position that this one is being compared to.
	 */
	public boolean equals(MapPosition o) {
		return this.getX() == o.getX() && this.getY() == o.getY();
	}

	/**
	 * debugging method to print info about a position. 
	 */
	public void printPosition() {
		System.out.println("position: (" + this.getX() + "," + this.getY() + ") : " + this.positionChar);
	}
	
}
