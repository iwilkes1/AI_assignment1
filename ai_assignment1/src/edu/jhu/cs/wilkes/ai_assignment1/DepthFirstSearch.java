package edu.jhu.cs.wilkes.ai_assignment1;

import java.util.HashSet;
import java.util.Set;
import java.util.Stack;

/**
 * DFS search algorithm.  Uses a set and minor look-ahead to prevent expanding nodes multiple times. 
 * @author Ian Wilkes
 *
 */
public class DepthFirstSearch implements GridSearchAlgorithm {

	private Stack<GridSearchPath> pathsToCheck;
	private Set<MapPosition> checkedNodes;
	private GridSearchMap gridMap;
	private int nodesExpanded;
	
	public DepthFirstSearch(GridSearchMap map) {
		this.gridMap = map;
		this.nodesExpanded = 0;
		this.pathsToCheck = new Stack<GridSearchPath>();
	}
	
	@Override
	public GridSearchPath gridSearch(MapPosition start, MapPosition goal) {
		GridSearchPath currPath = new GridSearchPath(new GridSearchPath(goal), start);
		checkedNodes = new HashSet<MapPosition>();
		pathsToCheck.push(currPath);
		
		while(!pathsToCheck.isEmpty()) {
			currPath = pathsToCheck.pop();
			if (checkedNodes.contains(currPath.getLastNode())) {
				continue;
			}
			checkedNodes.add(currPath.getLastNode());
			this.nodesExpanded++;
			if (currPath.getLastNode().isGoal()) {
				return currPath;
			} else {
				for (MapPosition p: this.gridMap.getAdjacentNodes(currPath.getLastNode())) {
					if (!checkedNodes.contains(p)){
						pathsToCheck.push(new GridSearchPath(currPath, p));
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
