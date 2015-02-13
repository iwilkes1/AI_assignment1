package edu.jhu.cs.wilkes.ai_assignment1;

/**
 * Wrapper class for MapPositions to store both the current position
 * and the previous location.  Can also store the A* heuristic costs as
 * well as the path cost up to this node. Used as the general node in all
 * search trees. 
 * @author Ian Wilkes
 *
 */
public class SearchPosition implements Comparable<SearchPosition>{
	private MapPosition currentPosition;
	private SearchPosition previousPosition;
	private double aStarCost;
	private int currPathCost;
	
	/**
	 * Generic constructor for BFS and DFS when path cost is not required.
	 * @param currentPosition the data corresponding to this state.
	 * @param prevPosition the search node from which this node was expanded.
	 */
	public SearchPosition(MapPosition currentPosition, SearchPosition prevPosition) {
		this.currentPosition = currentPosition;
		this.previousPosition = prevPosition;
		this.aStarCost = 0;
		this.currPathCost = 0;
	}
	
	/**
	 * Constructor for A* style searches where current 
	 * path cost, as well as heuristic costs are needed.
	 * @param currentPosition the map node containing data about this location.
	 * @param prevPosition The parent node of this node.
	 * @param heuristicCost the predicted distance to the goal from this location.
	 * @param prevPathCost the cost of all nodes before this one in the path.
	 */
	public SearchPosition(MapPosition currentPosition, SearchPosition prevPosition, double heuristicCost, int prevPathCost) {
		this.currentPosition = currentPosition;
		this.previousPosition = prevPosition;
		this.currPathCost = prevPathCost + this.currentPosition.getCost();
		this.aStarCost = heuristicCost + this.currPathCost;
	}
	
	/**
	 * getter for the map file position and corresponding data.
	 * @return the map position corresponding to this SearchPosition.
	 */
	public MapPosition getCurrentPosition() {
		return this.currentPosition;
	}
	
	/**
	 * getter for the parent node.
	 * @return The previous SearchPosition.
	 */
	public SearchPosition getPreviousPosition() {
		return this.previousPosition;
	}
	
	/**
	 * getter for the current path cost including this node.
	 * @return the current path cost.
	 */
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
