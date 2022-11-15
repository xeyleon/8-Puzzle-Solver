package eight_puzzle;

import java.lang.Math;
import java.util.PriorityQueue;

/**
 * 8-Puzzle Solver
 * @author Roy Ceyleon
 * @version 2021-05-7
 */

public class Main {
	/* ----------------CONFIGURATION---------------- */
	
	static final int start_x = 1; //set to starting coordinate x 
	static final int start_y = 1; //set to starting coordinate y
	
	/*
	 * START STATE
	 * note: ensure state is N x N, and coordinates of 0 corresponds to start_x and start_y
	 */
	static final int[][] initial_state = {
					{ 7, 2, 4 },
			  		{ 5, 0, 6 },
			  		{ 8, 3, 1 }
			  	};
	
	/*
	 * GOAL STATE
	 * note: ensure state is N x N, and coordinates of 0 corresponds to start_x and start_y
	 */
	static final int[][] goal_state = {
			  		{ 0, 1, 2 },
			  		{ 3, 4, 5 },
			  		{ 6, 7, 8 }
				};
	/* -------------------------------------------- */

	/* --------------GLOBAL CONSTANTS-------------- */
	/* DO NOT CHANGE */
	static final int N = 3;	//puzzle dimensions, default: 3x3
	static final int[] row_moves = {1, 0, -1, 0};
	static final int[] col_moves = {0, -1, 0, 1};
	/* -------------------------------------------- */
	
	public static void main(final String[] args) {
		/**
		 * Primary function
		 */
		System.out.println("Solving...\n");
		
		PriorityQueue<Node> pQ = new PriorityQueue<Node>(100);
		Node root = new Node(start_x, start_y, 0, null, initial_state);
		root.setCost(evalCost(initial_state));
		pQ.add(root);
		
		while (!pQ.isEmpty()) {
			
			Node node = pQ.remove();
			
			if (node.getCost() == 0) {
				tracePath(node);
				System.out.println("Puzzle Solved.");
				return;
			}
			
			for (int i = 0; i < 4; i++) {
				if (isLegal(node.getX() + row_moves[i], node.getY() + col_moves[i])) {
					Node child = new Node(node.getX(), node.getY(), node.getLevel()+1, node, node.getMatrix());
					child.swapXY(node.getX() + row_moves[i], node.getY() + col_moves[i]);
					child.setCost(evalCost(child.getMatrix()));
					pQ.add(child);
				}
			}
		}
		
		System.out.println("Puzzle could not be solved.\n");
	}
	

	public static void tracePath(Node node) {
		/**
		 * Recursively traces the path from a given state back to the initial
		 * state and prints the sequence of states taken to reach the goal state.
		 * @param node Node to be traced.
		 */
		if (node.getLevel() > 0) {
			tracePath(node.getParent());
			printMatrix(node.getMatrix());
		}
		else 
			printMatrix(initial_state);
		System.out.println("");
	}
	
	public static void printMatrix(int[][] matrix) {
		/**
		 * Prints a given 2D array
		 * @param matrix Matrix to be printed.
		 */
	    for (int i = 0; i < N; i++) {
	        for (int j = 0; j < N; j++) {
	            System.out.printf("%4d", matrix[i][j]);
	        }
	        System.out.println();
	    }
	}
	
	public static boolean isLegal(int x, int y) {
		/**
		 * Check if a move is legal by checking whether coordinates
		 * are within the boundaries of the puzzle
		 * @param x x-coordinate of potential move.
		 * @param y y-coordinate of potential move.
		 */
		if ((x >= 0 && x < N) && (y >= 0 && y < N))
			return true;
		return false;
	}
	
	public static int getGoalDeviation(final int x, final int y, final int tile) {
		/**
		 * Helper function for evalCost
		 * @param x x-coordinate of tile.
		 * @param y y-coordinate of tile.
		 * @param tile Value at given coordinates.
		 */
		for(int i = 0; i < N; i++)
			for(int j = 0; j < N; j++)
				if (tile == goal_state[i][j])
					return Math.abs(i-x) + Math.abs(j-y);
		return Integer.MAX_VALUE;
	}
	
	public static int evalCost(final int[][] state) {
		/**
		 * Utilizes the Manhattan Distance algorithm to evaluate the cost of a given state,
		 * taking into consideration its deviation from the goal state.
		 * @param state State to be evaluated.
		 */
		int cost = 0;
		for (int i = 0; i < N; i++)
			for (int j = 0; j < N; j++)
				cost = (cost + getGoalDeviation(i, j, state[i][j]));
		return cost;
	}
}
