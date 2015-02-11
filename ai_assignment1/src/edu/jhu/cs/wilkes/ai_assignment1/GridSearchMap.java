package edu.jhu.cs.wilkes.ai_assignment1;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.Scanner;

/**
 * 
 * @author Ian Wilkes
 * Class for reading a grid map file and handling checking of actions from a given location.
 */
public class GridSearchMap {
	private Position[][] map;
	private Position start;
	private Position goal;
	private int rows;
	private int cols;
/**
 * Constructor to build the useable map from the map file location provided.  
 * @param mapFile the path to the map file being used
 * @throws FileNotFoundException
 */
	public GridSearchMap(String mapFile) throws FileNotFoundException {
		Scanner readFile = new Scanner(new FileReader(mapFile));
		this.cols = readFile.nextInt();
		this.rows = readFile.nextInt();	
		int rowCounter = 0;
		String line = readFile.nextLine();
		Position currPosition;
		
		map = new Position[rows][cols];
		
		while (readFile.hasNextLine()) {
			line = readFile.nextLine();
			System.out.println(line);
			for (int j = 0; j < line.length(); j++) {
				currPosition = new Position(j, rowCounter, line.charAt(j));
				map[rowCounter][j] = currPosition;
				if (currPosition.isStart()) {
					start = map[rowCounter][j];
				} else if (currPosition.isGoal()) {
					goal = map[rowCounter][j];
				}
				
			}
			rowCounter++;
		}
		readFile.close();
	}	
	
	/**
	 * Produces the adjacent nodes to this current node in a list.  
	 * Only includes nodes which are traversable, and only in the four cardinal directions.
	 * @param toCheck the position about which traversable nodes are being added.
	 * @return the list containing the adjacent traversable nodes.
	 */
	public ArrayList<Position> getAdjacentNodes(Position toCheck) {
		ArrayList<Position> adjacentPositions = new ArrayList<Position>();
		int x = toCheck.getX();
		int y = toCheck.getY();
		if (x > 0 && map[y][x - 1].isTraversable()) {
			adjacentPositions.add(map[y][x - 1]);

		}
		System.out.println("right node:");
		map[y][x + 1].printPosition();
		if (x < cols - 1 && map[y][x + 1].isTraversable()) {
			adjacentPositions.add(map[y][x + 1]);

		}
		if (y > 0 && map[y - 1][x].isTraversable()) {
			adjacentPositions.add(map[y - 1][x]);

		}
		if (y < rows - 1 && map[y + 1][x].isTraversable()) {
			adjacentPositions.add(map[y + 1][x]);
		}
		return adjacentPositions;
	}
	
	/**
	 * getter for the start position of the map
	 * @return the start position
	 */
	public Position getStartPosition() {
		return this.start;
	}
	
	/**
	 * getter for the goal position of the map
	 * @return the goal position.
	 */
	public Position getGoalPosition() {
		return this.goal;
	}
	
	
}
