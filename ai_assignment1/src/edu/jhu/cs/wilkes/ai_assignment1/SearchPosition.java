package edu.jhu.cs.wilkes.ai_assignment1;

/**
 * Wrapper class for DFS to store positions instead of full paths. 
 * @author Ian Wilkes
 *
 */
public class SearchPosition implements Comparable<SearchPosition>{
	private MapPosition currentPosition;
	private SearchPosition previousPosition;
	private double aStarCost;
	private int currPathCost;
	
	public SearchPosition(MapPosition currentPosition, SearchPosition prevPosition) {
		this.currentPosition = currentPosition;
		this.previousPosition = prevPosition;
		this.aStarCost = 0;
		this.currPathCost = 0;
	}
	
	public SearchPosition(MapPosition currentPosition, SearchPosition prevPosition, double d, int prevPathCost) {
		this.currentPosition = currentPosition;
		this.previousPosition = prevPosition;
		this.currPathCost = prevPathCost + this.currentPosition.getCost();
		this.aStarCost = d + this.currPathCost;
	}
	
	public MapPosition getCurrentPosition() {
		return this.currentPosition;
	}
	
	public SearchPosition getPreviousPosition() {
		return this.previousPosition;
	}
	
	public int getPathCost() {
		return this.currPathCost;
	}
	

	@Override
	public int compareTo(SearchPosition other) {
		if (this.aStarCost < other.aStarCost) {
			return -1;
		} else if (this.aStarCost > other.aStarCost) {
			return 1;
		} else {
			return 0;
		}
	}
}
