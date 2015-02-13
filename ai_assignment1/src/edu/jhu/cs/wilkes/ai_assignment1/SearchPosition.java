package edu.jhu.cs.wilkes.ai_assignment1;

/**
 * Wrapper class for DFS to store positions instead of full paths. 
 * @author Ian Wilkes
 *
 */
public class SearchPosition {
	private MapPosition currentPosition;
	private SearchPosition previousPosition;
	
	public SearchPosition(MapPosition currentPosition, SearchPosition prevPosition) {
		this.currentPosition = currentPosition;
		this.previousPosition = prevPosition;
	}
	
	
	public MapPosition getCurrentPosition() {
		return this.currentPosition;
	}
	
	public SearchPosition getPreviousPosition() {
		return this.previousPosition;
	}
	
}
