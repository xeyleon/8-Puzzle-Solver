# 8-Puzzle-Solver
This program currently only supports the solutions for the 8-puzzle (3x3 tiles).

The program must be configured by entering the starting x, y coordinates (coordinates of the blank tile), along with the initial state and goal state as 2D lists representing the arrangement of the respective 3x3 tiles, found at the beginning of the Main.java file. 

The upper left-most tile has the coordinates (0,0), and the lower right-most tile has the coordinates (2,2).

An example of a configuration:
```java
	static final int start_x = 1;
	static final int start_y = 1;
	
	static final int[][] start_state = {
					{ 7, 2, 4 },
			  		{ 5, 0, 6 },
			  		{ 8, 3, 1 }
			  	};

	static final int[][] goal_state = {
			  		{ 0, 2, 4 },
			  		{ 7, 3, 6 },
			  		{ 5, 8, 1 }
				};
```

Uniform Cost Search is utilized, along with the Manhattan Distance algorithm for state-cost evaluation at each step, to determine the solution for any given set of initial state and goal state.
After running the program, the solution will be displayed as the sequence of actions to be executed from the initial state in order to arrive at the goal state.

Example program run with above configuration:
```bash
Solving...

7 2 4 
5 0 6 
8 3 1 

7 2 4 
5 3 6 
8 0 1 

7 2 4 
5 3 6 
0 8 1 

7 2 4 
0 3 6 
5 8 1 

0 2 4 
7 3 6 
5 8 1 

Puzzle solved.
```
