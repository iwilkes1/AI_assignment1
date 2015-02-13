package edu.jhu.cs.wilkes.ai_assignment1;

import java.io.FileNotFoundException;;

/**
 * main class for project 1 of artificial intelligence.  Performs various types of search.
 * @author Ian Wilkes
 *
 */
public class GridSearchMain {

	private static final int NUM_REQUIRED_ARGS = 2;
	private static GridSearchMap gridMap;
	/**
	 * Main method for a program to perform various types of searches.  
	 * @param command line arguments.  None needed.
	 * 
	 */
	public static void main(String[] args) {
		Position start;
		Position goal;
		GridSearchPath path;
		GridSearchAlgorithm searcher = setUpSearch(args);

		start = gridMap.getStartPosition();
		goal = gridMap.getGoalPosition();
		// runs the search
		path = searcher.gridSearch(start, goal);
		
		if (path == null) {
			System.out.println("Could not reach the goal.\nPath cost: infinte.");
		} else {
			path.printPath();
		}
		System.out.println("Number of nodes expanded: " + searcher.getNumNodesExpanded());
	}
	
	/**
	 *
	 * Handles user input to get path to map to be searched.  Also specifies search type.
	 * Initializes the map file to be used when searching, as well as the search type to be used. 
	 *  
	 * @param the command line arguments. first element should be the full path to the map file, and the second is the search type to be used, either bfs, dfs or a*.
	 * @return The search algorithm which will be used for this iteration.
	 */
	private static GridSearchAlgorithm setUpSearch(String[] args) {
		String mapFile;
		String searchType;
		GridSearchAlgorithm searcher = null;
		
		if (args.length != NUM_REQUIRED_ARGS) {
			System.out.println("Incorrect number of arguments entered. Exiting.");
			System.exit(1);
		}
		mapFile = args[0];
		searchType = args[1];
		// setting up the grid map to be used.
		try {
			gridMap = new GridSearchMap(mapFile);
		} catch (FileNotFoundException e) {
			System.out.println("Error map file could not be found. Exiting");
			System.exit(1);
		}	
		// setting up the search type.
		if (searchType.equalsIgnoreCase("bfs")) {
			searcher = new BreadthFirstSearch(gridMap);
		} else if (searchType.equalsIgnoreCase("dfs")) {
			// TODO check new DFS
			//searcher = new DepthFirstSearch(gridMap);
			searcher = new OtherDepthFirstSearch(gridMap);
		} else if (searchType.equalsIgnoreCase("astar")) {
			searcher = new AStarSearch(gridMap);
		} else {
			System.out.println("Invalid search type. Exiting");
			System.exit(1);
		}			
		return searcher;
	}
}
