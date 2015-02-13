package edu.jhu.cs.wilkes.ai_assignment1;

/**
 * Wrapper class for DFS to store positions instead of full paths. 
 * @author Ian Wilkes
 *
 */
public class DepthFirstSearchPosition {
	private Position currentPosition;
	private DepthFirstSearchPosition previousPosition;
	
	public DepthFirstSearchPosition(Position currentPosition, DepthFirstSearchPosition prevPosition) {
		this.currentPosition = currentPosition;
		this.previousPosition = prevPosition;
	}
	
	
	public Position getCurrentPosition() {
		return this.currentPosition;
	}
	
	public DepthFirstSearchPosition getPreviousPosition() {
		return this.previousPosition;
	}
	
}
