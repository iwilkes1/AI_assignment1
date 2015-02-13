package edu.jhu.cs.wilkes.ai_assignment1;

import java.util.HashSet;
import java.util.Set;
import java.util.Stack;

public class OtherDepthFirstSearch implements GridSearchAlgorithm {
	private Stack<DepthFirstSearchPosition> pathsToCheck;
	private Set<Position> checkedNodes;
	private GridSearchMap gridMap;
	private int nodesExpanded;
	
	public OtherDepthFirstSearch(GridSearchMap map) {
		this.gridMap = map;
		this.nodesExpanded = 0;
		this.pathsToCheck = new Stack<DepthFirstSearchPosition>();
	}
	
	@Override
	public GridSearchPath gridSearch(Position start, Position goal) {
		GridSearchPath resultPath;
		Stack<DepthFirstSearchPosition> pathReverser;
		DepthFirstSearchPosition currPosition = new DepthFirstSearchPosition(start, null);
		checkedNodes = new HashSet<Position>();
		pathsToCheck.push(currPosition);
		
		while(!pathsToCheck.isEmpty()) {
			currPosition = pathsToCheck.pop();
			if (checkedNodes.contains(currPosition.getCurrentPosition())) {
				continue;
			}
			checkedNodes.add(currPosition.getCurrentPosition());
			this.nodesExpanded++;
			// iterate back up to the start node, then add the nodes into the path to be returned. 
			if (currPosition.getCurrentPosition().isGoal()) {
				resultPath = new GridSearchPath(goal);
				pathReverser = new Stack<DepthFirstSearchPosition>();
				while (currPosition != null) {
					pathReverser.push(currPosition);
					currPosition = currPosition.getPreviousPosition();
				}
				while (!pathReverser.isEmpty()) {
					resultPath.addNode(pathReverser.pop().getCurrentPosition());
				}
				return resultPath;
			} else {
				for (Position p: this.gridMap.getAdjacentNodes(currPosition.getCurrentPosition())) {
					if (!checkedNodes.contains(p)){
						pathsToCheck.push(new DepthFirstSearchPosition(p, currPosition));
					}
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
