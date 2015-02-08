package edu.jhu.cs.wilkes.ai_assignment1;

import java.io.FileNotFoundException;

public class GridSearchMain {

	private static String searchType;
	private static GridSearchMap gridMap;
	/**
	 * @param command line arguments.  First argument is the map to use, second is the search type.
	 * 
	 */
	public static void main(String[] args) {
		String mapFile;
		Position start;
		Position goal;
		GridSearchAlgorithm searcher;
		GridSearchPath path;
		
		try {
			mapFile = args[0];
			gridMap = new GridSearchMap(mapFile);

			searchType = args[1];

		} catch(IndexOutOfBoundsException e) {
			System.err.println("Error, too few arguments.  First argument should " +
					"be the map file to use and the second should be the type " +
					"of search to use. Valid search types are dfs, bfs and A*. Exiting.");
			System.exit(1);
		} catch (FileNotFoundException e) {
			System.err.println("Error map file could not be found. Exiting.");
			e.printStackTrace();
			System.exit(1);
		}
		/*
		 * defaults to a star if an invalid search type is entered.
		 */
		if (searchType.equalsIgnoreCase("bfs")) {
			searcher = new BreadthFirstSearch(gridMap);
		} else if (searchType.equalsIgnoreCase("dfs")) {
			searcher = new DepthFirstSearch();
		} else {
			searcher = new AStarSearch();
		} 
		start = gridMap.getStartPosition();
		goal = gridMap.getGoalPosition();
		// runs the search
		path = searcher.gridSearch(start, goal);
		if (path == null) {
			System.out.println("Could not reach the goal");
			System.out.println("Number of nodes expanded is infinite.");
		} else {
			path.printPath();
			System.out.println("Number of nodes expanded: " + searcher.getNumNodesExpanded());
		}
	}
}
