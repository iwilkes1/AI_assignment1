package edu.jhu.cs.wilkes.ai_assignment1;

import java.util.ArrayList;
import java.util.Stack;

public class DepthFirstSearch implements GridSearchAlgorithm {

	private Stack<GridSearchPath> pathsToCheck;
	private GridSearchMap gridMap;
	private int nodesExpanded;
	
	public DepthFirstSearch(GridSearchMap map) {
		this.gridMap = map;
		this.nodesExpanded = 0;
		this.pathsToCheck = new Stack<GridSearchPath>();
	}
	
	@Override
	public GridSearchPath gridSearch(Position start, Position goal) {
		GridSearchPath currPath = new GridSearchPath(new GridSearchPath(), start);
		pathsToCheck.push(currPath);
		while(!pathsToCheck.isEmpty()) {
			this.nodesExpanded++;
			currPath = pathsToCheck.pop();
			if (currPath.getLastNode().isGoal()) {
				return currPath;
			} else {
				for (Position p: (ArrayList<Position>) this.gridMap.getAdjacentNodes(currPath.getLastNode())) {
					pathsToCheck.push(new GridSearchPath(currPath, p));
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
