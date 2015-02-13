package edu.jhu.cs.wilkes.ai_assignment1;

/**
 * Interface for the search algorithms.
 * Planned implementations: A*, BFS, DFS.
 * @author Ian Wilkes
 *
 */
public interface GridSearchAlgorithm {
	
	/**
	 * Method to search a grid, starting from the start and ending at the goal.
	 * @param start the position to start the search
	 * @param goal the position to end the search
	 * @return If a path to the goal is found, returns that path, if none is found, returns null.
	 */
	public GridSearchPath gridSearch(MapPosition start, MapPosition goal);
	
	/**
	 * returns the number of nodes expanded during the search process.
	 * @return the number of nodes expanded.
	 */
	public int getNumNodesExpanded();
}
