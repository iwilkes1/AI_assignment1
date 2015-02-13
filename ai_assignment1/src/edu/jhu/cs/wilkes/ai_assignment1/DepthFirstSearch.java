package edu.jhu.cs.wilkes.ai_assignment1;

import java.util.HashSet;
import java.util.Set;
import java.util.Stack;

/**
 * Standard DFS implementation with a stack.
 * Uses repeated state detection to prevent infinite loops.
 * @author Ian Wilkes
 *
 */
public class DepthFirstSearch implements GridSearchAlgorithm {
	private Stack<SearchPosition> pathsToCheck;
	private Set<MapPosition> checkedNodes;
	private GridSearchMap gridMap;
	private int nodesExpanded;
	
	/**
	 * DFS constructor. Sets up the algorithm.
	 * @param map which defines the search space.
	 */
	public DepthFirstSearch(GridSearchMap map) {
		this.gridMap = map;
		this.nodesExpanded = 0;
		this.pathsToCheck = new Stack<SearchPosition>();
	}
	
	@Override
	public GridSearchPath gridSearch(MapPosition start, MapPosition goal) {
		GridSearchPath resultPath;
		Stack<SearchPosition> pathReverser;
		SearchPosition currPosition = new SearchPosition(start, null);
		checkedNodes = new HashSet<MapPosition>();
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
				pathReverser = new Stack<SearchPosition>();
				while (currPosition != null) {
					pathReverser.push(currPosition);
					currPosition = currPosition.getPreviousPosition();
				}
				while (!pathReverser.isEmpty()) {
					resultPath.addNode(pathReverser.pop().getCurrentPosition());
				}
				return resultPath;
			} else {
				for (MapPosition p: this.gridMap.getAdjacentNodes(currPosition.getCurrentPosition())) {
					if (!checkedNodes.contains(p)){
						pathsToCheck.push(new SearchPosition(p, currPosition));
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
