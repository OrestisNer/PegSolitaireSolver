//-------------------------------------------------------------
//
//This program solves Peg Solitaire puzzle with 2 algorithms
//- Depth first search
//- Best first search
//Puzzles are read from an input file, while solution is written
//to an output file.
//
//Author: Orestis R. Nerantzis, April 2018
//
//--------------------------------------------------------------


//A 5x5Peg Solitaire puzzle:
//
//	+---+---+---+---+---+
//   |0,0|0,1|0,2|0,3|0,4|
//	+---+---+---+---+---+
//   |1,0|1,1|1,2|1,3|1,4|
//	+---+---+---+---+---+
//   |2,0|2,1|2,2|2,3|2,4|
//	+---+---+---+---+---+
//   |3,0|3,1|3,2|3,3|3,4| 
//  +---+---+---+---+---+
//   |4,0|4,1|4,2|4,3|4,4|
//  +---+---+---+---+---+
//
// Two dimensional arrays are used to denote puzzles.
// The peg is denoted as 1, the blank is denoted as 2 and the cells out of the board denoted as 0
// Note the numbering of the cells, i.e. the upper left corner is the cell [0][0].
// We got a solution when only one peg is left on the board.
// The number of the moves to solve peg solitaire will be the amount of pegs minus one


// For example,6x5 puzzle is the following:
//	0 0 2 0 0
//  0 2 1 2 0
//  2 1 1 1 2 
//  0 2 1 2 0
//	0 0 1 0 0
//	0 0 2 0 0
// 

// And the solution :
//
//
//	0 0 1 0 0
//	0 2 2 2 0
//	2 2 2 2 2 
//	0 2 2 2 0
//	0 0 2 0 0
//	0 0 2 0 0
//
// Every node in the tree is a TreeNode object.
// All the methods to find the solution is in class SearchUtils.
// All the methods to read/write from/to file is in class FileUtils.

public class Main {	

	public static void main(String[] args) {	
		FileUtils.checkArguments(args);
		int method=FileUtils.getMethod(args[0]);
		Integer[][] board=FileUtils.getBoardFromFile(args[1]);
		FileUtils.checkBoard(board);
		
		long start= System.currentTimeMillis();
		
		int maxDepth=SearchUtils.getMaxDepth(board);
		TreeNode root= SearchUtils.initializeSearch(board);
		TreeNode solution=SearchUtils.search(root, maxDepth,method);
		
		long end= System.currentTimeMillis();
		long timeInMillis= end-start;
		double timeInSecs= timeInMillis/1000.0;
		
		
		if(solution!=null){
		   System.out.println("\n\nSolution Found.");
		   System.out.println("Time(millis): "+timeInMillis+"\nTime(secs): "+timeInSecs);
		   System.out.println("Nodes visited: "+solution.getNodesChecked());
		   FileUtils.writeSolutionToFile(args[2], solution);
	    }
		
	}
	
	
	
	
	
	
	
	
	
	

		

}
