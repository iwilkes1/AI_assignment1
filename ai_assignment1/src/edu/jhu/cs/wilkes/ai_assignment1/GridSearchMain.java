package edu.jhu.cs.wilkes.ai_assignment1;

import java.io.FileNotFoundException;
import java.util.Scanner;

/**
 * main class for project 1 of artificial intelligence.  Performs various types of search.
 * @author Ian Wilkes
 *
 */
public class GridSearchMain {

	private static String searchType;
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
		
		GridSearchAlgorithm searcher = setUpSearch();

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
	
	/**
	 * Handles user input to get path to map to be searched.  Also specifies search type.
	 * @return The search algorithm which will be used for this iteration. 
	 */
	private static GridSearchAlgorithm setUpSearch() {
		String mapFile;
		GridSearchAlgorithm searcher = null;
		boolean notReadyToSearch = true;
		Scanner kb = new Scanner(System.in);
		// user prompt for path to map file.
		while (notReadyToSearch) {
			System.out.println("Enter full path to data files.");
			mapFile = kb.next(); 
			try {
				gridMap = new GridSearchMap(mapFile);
				notReadyToSearch = false;
			} catch (FileNotFoundException e) {
				System.out.println("Error map file could not be found.");
			}
		}
		// prompting user for search type.
		while (searcher == null) {
			System.out.println("Enter a search type.  Valid types are bfs, dfs, or a*");
			searchType = kb.next();
			if (searchType.equalsIgnoreCase("bfs")) {
				searcher = new BreadthFirstSearch(gridMap);
			} else if (searchType.equalsIgnoreCase("dfs")) {
				searcher = new DepthFirstSearch(gridMap);
			} else if (searchType.equalsIgnoreCase("a*")) {
				searcher = new AStarSearch(gridMap);
			} else {
				System.out.println("Invalid search type.");
			}
		}		
		kb.close();
		return searcher;
	}
}
