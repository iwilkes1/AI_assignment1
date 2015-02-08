package edu.jhu.cs.wilkes.ai_assignment1;

import java.util.ArrayList;
import java.util.PriorityQueue;

public class AStarSearch implements GridSearchAlgorithm {

	private PriorityQueue<GridSearchPath> pathsToCheck;
	private GridSearchMap gridMap;
	private int nodesExpanded;
	
	public AStarSearch(GridSearchMap map) {
		this.gridMap = map;
		this.nodesExpanded = 0;
		this.pathsToCheck = new PriorityQueue<GridSearchPath>();
	}
	
	@Override
	public GridSearchPath gridSearch(Position start, Position goal) {
		GridSearchPath currPath = new GridSearchPath(new GridSearchPath(goal), start);
		pathsToCheck.add(currPath);
		while(!pathsToCheck.isEmpty()) {
			this.nodesExpanded++;
			currPath = pathsToCheck.poll();
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
