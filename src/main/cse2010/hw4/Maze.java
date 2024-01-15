package cse2010.hw4;
/*
 * CSE2010 Homework #4: Maze.java
 *
 * Complete the code below.
 */

import java.util.Arrays;

public class Maze {
	private final int numRows; // number of rows
	private final int numCols; // number of columns

	private int[][] maze; // maze itself
	private boolean[][] visited; // true if cell was visited before

	private final Location entry; // Entry Location
	private final Location exit;  // Exit Location
	
	private final ArrayStack<Location> stack = new ArrayStack<>(100);

	/**
	 * Initialize this maze with a given maze and entry/exit locations.
	 * @param maze	2D array representing a maze
	 * @param entry	entry location
	 * @param exit	exit location
	 */
	public Maze(int[][] maze, Location entry, Location exit) {
		this.maze = maze;
		numRows = maze.length;
		numCols = maze[0].length;

		visited = new boolean[numRows][numCols];

		for (int i = 0; i < numRows; i++) {
			Arrays.fill(visited[i], false);
		}

		this.entry = entry;
		this.exit = exit;
	}

	// For testing purpose
	public void printMaze() {
		System.out.println("Maze[" + numRows + "][" + numCols + "]");
		System.out.println("Entry index = (" + entry.row + ", " + entry.col + ")");
		System.out.println("Exit index = (" + exit.row + ", " + exit.col + ")" + "\n");

		for (int i = 0; i < numRows; i++) {
			System.out.println(Arrays.toString(maze[i]));
		}
		System.out.println();
	}
	
	public boolean findPath() {
		return moveTo(entry.row, entry.col);
	}

	private boolean moveTo(int row, int col) {
		visited[row][col] = true;

		if (row == exit.row && col == exit.col) {
			stack.push(new Location(row, col));
			return true;
		} // exit

		if (col < numCols - 1 && maze[row][col + 1] == 0 && !visited[row][col + 1]) {
			if (moveTo(row, col + 1)) {
				stack.push(new Location(row, col));
				return true;
			}
		} // east

		if (col > 0 && maze[row][col - 1] == 0 && !visited[row][col - 1]) {
			if (moveTo(row, col - 1)) {
				stack.push(new Location(row, col));
				return true;
			}
		} // west

		if (row < numRows - 1 && maze[row + 1][col] == 0 && !visited[row + 1][col]) {
			if (moveTo(row + 1, col)) {
				stack.push(new Location(row, col));
				return true;
			}
		} // south

		if (row > 0 && maze[row - 1][col] == 0 && !visited[row - 1][col]) {
			if (moveTo(row - 1, col)) {
				stack.push(new Location(row, col));
				return true;
			}
		} // north

		return false;
	}

	private void reverseStack(ArrayStack<Location> stack) {
		if (stack.isEmpty()) {
			return;
		}

		Location temp = stack.pop();
		reverseStack(stack);
		insert(stack, temp);
	}

	private void insert(ArrayStack<Location> stack, Location location) {
		if (stack.isEmpty()) {
			stack.push(location);
			return;
		}

		Location temp = stack.pop();
		insert(stack, location);
		stack.push(temp);
	}
	
	public String showPath() {
		reverseStack(stack);
		StringBuilder builder = new StringBuilder();
		while (!stack.isEmpty()) {
			builder.append(stack.pop() + " <-- ");
		}
		builder.append("Start");
		return builder.toString();
	}
}