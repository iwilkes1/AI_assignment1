package edu.jhu.cs.wilkes.ai_assignment1;

import java.util.HashSet;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Set;
import java.util.Stack;

/**
 * Class to perform BFS. Uses repeated state detection to minimize number of nodes expanded.
 * Implemented with a linked list as the queue.
 * @author Ian Wilkes
 *
 */
public class BreadthFirstSearch implements GridSearchAlgorithm {

	private Queue<SearchPosition> pathsToCheck;
	private Set<MapPosition> checkedNodes;
	private GridSearchMap gridMap;
	private int nodesExpanded;
	
	/**
	 * Basic constructor for a BFS.  
	 * @param map defines the search grid.
	 */
	public BreadthFirstSearch(GridSearchMap map) {
		this.gridMap = map;
		this.nodesExpanded = 0;
		this.pathsToCheck = new LinkedList<SearchPosition>();
	}
	
	@Override
	public GridSearchPath gridSearch(MapPosition start, MapPosition goal) {
		GridSearchPath resultPath;
		Stack<SearchPosition> pathReverser;
		SearchPosition currPosition = new SearchPosition(start, null);
		checkedNodes = new HashSet<MapPosition>();
		pathsToCheck.add(currPosition);
		
		
		while(!pathsToCheck.isEmpty()) {
			currPosition = pathsToCheck.remove();
			if (checkedNodes.contains(currPosition.getCurrentPosition())) {
				continue;
			}
			checkedNodes.add(currPosition.getCurrentPosition());
			this.nodesExpanded++;
			
			// iterate back up to the start node, then add the nodes into the path to be returned. 
			// the final node is found, and then the path is constructed back from that.
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
				// only positions who haven't been seen will be added to the queue.
				for (MapPosition p: this.gridMap.getAdjacentNodes(currPosition.getCurrentPosition())) {
					if (!checkedNodes.contains(p)){
						pathsToCheck.add(new SearchPosition(p, currPosition));
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
