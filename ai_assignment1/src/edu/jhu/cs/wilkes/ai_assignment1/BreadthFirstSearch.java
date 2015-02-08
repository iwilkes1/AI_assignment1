package edu.jhu.cs.wilkes.ai_assignment1;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;

public class BreadthFirstSearch implements GridSearchAlgorithm {
	
	private Queue<GridSearchPath> pathsToCheck;
	private GridSearchMap gridMap;
	private int nodesExpanded;
	/**
	 * Constructor for the breadth first search. 
	 * @param map the map of positions which the algorithm uses to find new nodes. 
	 */
	public BreadthFirstSearch(GridSearchMap map) {
		this.gridMap = map;
		this.nodesExpanded = 0;
		this.pathsToCheck = new LinkedList<GridSearchPath>();
	}
	
	@Override
	public GridSearchPath gridSearch(Position start, Position goal) {
		GridSearchPath currPath = new GridSearchPath(new GridSearchPath(), start);
		pathsToCheck.add(currPath);
		while(!pathsToCheck.isEmpty()) {
			this.nodesExpanded++;
			currPath = pathsToCheck.remove();
			if (currPath.getLastNode().isGoal()) {
				return currPath;
			} else {
				for (Position p: (ArrayList<Position>) this.gridMap.getAdjacentNodes(currPath.getLastNode())) {
					pathsToCheck.add(new GridSearchPath(currPath, p));
				}
			}
		}
		return null;
	}

	@Override
	public int getNumNodesExpanded() {
		return this.nodesExpanded;
	}

}
