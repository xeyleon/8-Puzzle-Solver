package eight_puzzle;

import java.util.Arrays;

/**
 * 8-Puzzle Solver
 * @author Roy Ceyleon
 * @version 2021-05-7
 */

public final class Node  implements Comparable<Node>{
	/**
	 * Object utilized to store all relevant data of a state
	 */
	
    // Attributes
    private int x;
    private int y;
    private int cost;
    private int level;
    private Node parent;
    private int[][] matrix;
    
    //Constructor
    public Node(final int x, final int y, final int level, final Node parent, int[][] matrix) {
    	this.x = x;
    	this.y = y;
    	this.cost = Integer.MAX_VALUE;
    	this.level = level;
    	this.parent = parent;
    	this.matrix = Arrays.stream(matrix).map(int[]::clone).toArray(int[][]::new);
    }
    
    //Get Methods
    public Node getParent() {
    	return this.parent;
    }
    
    public int getCost() {
    	return this.cost;
    }
    
    public int getLevel() {
    	return this.level;
    }
    
    public int getX() {
    	return this.x;
    }
    
    public int getY() {
    	return this.y;
    }
    
    public int getTotalCost() {
    	return this.cost + this.level;
    }
    
    public int[][] getMatrix(){
    	return this.matrix;
    }
    
    //Set Method
    public void setCost(final int cost) {
    	this.cost = cost;
    }
    
	//Special Method
    public void swapXY(final int x, final int y) {
    	int temp = this.matrix[this.x][this.y];
    	this.matrix[this.x][this.y] = this.matrix[x][y];
    	this.matrix[x][y] = temp;
    	this.x = x;
    	this.y = y;
    }
    
    //For Node Comparison
    @Override
    public int compareTo(final Node that) {

    	if (this.getTotalCost() > that.getTotalCost())
    		return 1;
    	
    	if (this.getTotalCost() < that.getTotalCost())
    		return -1;   	
    	
    	return 0;
    }
    
}
