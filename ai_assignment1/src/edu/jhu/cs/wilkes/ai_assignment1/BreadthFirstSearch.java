package edu.jhu.cs.wilkes.ai_assignment1;

import java.util.HashSet;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Set;


/**
 * BFS search class.  Implements basic functionality to prevent repeat nodes.
 * @author Ian Wilkes
 *
 */
public class BreadthFirstSearch implements GridSearchAlgorithm {
	
	private Queue<GridSearchPath> pathsToCheck;
	private GridSearchMap gridMap;
	private Set<MapPosition> checkedNodes;
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
	public GridSearchPath gridSearch(MapPosition start, MapPosition goal) {
		GridSearchPath currPath = new GridSearchPath(new GridSearchPath(goal), start);
		pathsToCheck.add(currPath);
		checkedNodes = new HashSet<MapPosition>();
		
		while(!pathsToCheck.isEmpty()) {
			currPath = pathsToCheck.remove();
			if (checkedNodes.contains(currPath.getLastNode())) {
				continue;
				
			}
			checkedNodes.add(currPath.getLastNode());
			this.nodesExpanded++;
			
			if (currPath.getLastNode().isGoal()) {
				return currPath;
			} else {
				for (MapPosition p:  this.gridMap.getAdjacentNodes(currPath.getLastNode())) {

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
