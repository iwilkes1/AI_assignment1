package edu.jhu.cs.wilkes.ai_assignment1;

import java.util.HashSet;
import java.util.PriorityQueue;
import java.util.Set;

/**
 * A* search algorithm implemented using a set to prevent node reuse. 
 * @author Ian Wilkes
 *
 */
public class AStarSearch implements GridSearchAlgorithm {

	private PriorityQueue<GridSearchPath> pathsToCheck;
	private GridSearchMap gridMap;
	private Set<MapPosition> checkedNodes;
	private int nodesExpanded;
	
	public AStarSearch(GridSearchMap map) {
		this.gridMap = map;
		this.nodesExpanded = 0;
		this.pathsToCheck = new PriorityQueue<GridSearchPath>();
	}
	
	@Override
	public GridSearchPath gridSearch(MapPosition start, MapPosition goal) {
		GridSearchPath currPath = new GridSearchPath(new GridSearchPath(goal), start);
		checkedNodes = new HashSet<MapPosition>();
		pathsToCheck.add(currPath);
		while(!pathsToCheck.isEmpty()) {
			
			currPath = pathsToCheck.poll();
			if (checkedNodes.contains(currPath.getLastNode())) {
				continue;
			}
			checkedNodes.add(currPath.getLastNode());
			this.nodesExpanded++;
			if (currPath.getLastNode().isGoal()) {
				return currPath;
			} else {
				for (MapPosition p: this.gridMap.getAdjacentNodes(currPath.getLastNode())) {
					if (!checkedNodes.contains(p)) {		
						pathsToCheck.add(new GridSearchPath(currPath, p));
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
