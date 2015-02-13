package edu.jhu.cs.wilkes.ai_assignment1;

import java.util.HashSet;
import java.util.PriorityQueue;
import java.util.Set;
import java.util.Stack;
/**
 * A* search class used to perform searches using a priority queue.
 * Heuristic implemented on the Search positions, but here it is for half of the sum of the 
 * Euclidean distance and Manhattan distance.
 * @author Ian Wilkes
 *
 */
public class AStarSearch implements GridSearchAlgorithm {
	private PriorityQueue<SearchPosition> pathsToCheck;
	private Set<MapPosition> checkedNodes;
	private GridSearchMap gridMap;
	private int nodesExpanded;
	
	/** 
	 * General constructor for the search class
	 * @param map the map on which the search takes place.
	 */
	public AStarSearch(GridSearchMap map) {
		this.gridMap = map;
		this.nodesExpanded = 0;
		this.pathsToCheck = new PriorityQueue<SearchPosition>();
	}
	
	@Override
	public GridSearchPath gridSearch(MapPosition start, MapPosition goal) {
		GridSearchPath resultPath;
		Stack<SearchPosition> pathReverser;
		SearchPosition currPosition = new SearchPosition(start, null, this.gridMap.getHeuristicCost(start), 0);
		checkedNodes = new HashSet<MapPosition>();
		pathsToCheck.add(currPosition);
		
		
		while(!pathsToCheck.isEmpty()) {
		
			currPosition = pathsToCheck.poll();
			// Skips duplicate nodes. May be redundant
			if (checkedNodes.contains(currPosition.getCurrentPosition())) {
				continue;
			}
			checkedNodes.add(currPosition.getCurrentPosition());
			this.nodesExpanded++;
			// iterate back up to the start node, then add the nodes into the path to be returned.
			// essentailly reversing the order of nodes.
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
				// Adding new nodes. Nodes are only added if they have not been visited before.
				for (MapPosition p: this.gridMap.getAdjacentNodes(currPosition.getCurrentPosition())) {
					if (!checkedNodes.contains(p)) {
						pathsToCheck.add(new SearchPosition(p, currPosition, 
								this.gridMap.getHeuristicCost(p), currPosition.getPathCost()));
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
