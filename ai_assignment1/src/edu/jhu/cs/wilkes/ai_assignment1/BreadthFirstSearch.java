package edu.jhu.cs.wilkes.ai_assignment1;

import java.util.HashSet;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Set;

public class BreadthFirstSearch implements GridSearchAlgorithm {
	
	private Queue<GridSearchPath> pathsToCheck;
	private GridSearchMap gridMap;
	private Set<Position> checkedNodes;
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
		GridSearchPath currPath = new GridSearchPath(new GridSearchPath(goal), start);
		pathsToCheck.add(currPath);
		checkedNodes = new HashSet<Position>();
		
		while(!pathsToCheck.isEmpty()) {
			currPath = pathsToCheck.remove();
			if (checkedNodes.contains(currPath.getLastNode())) {
				// TODO remove debugging output
				System.out.print("node already visited   ");
				currPath.getLastNode().printPosition();
				continue;
				
			}
			checkedNodes.add(currPath.getLastNode());
			this.nodesExpanded++;
			
			if (currPath.getLastNode().isGoal()) {
				return currPath;
			} else {
				for (Position p:  this.gridMap.getAdjacentNodes(currPath.getLastNode())) {
					if (!checkedNodes.contains(p)) {
						System.out.print("adjacent node added at   ");
						p.printPosition();
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
