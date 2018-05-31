import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Scanner;

import com.sun.javafx.scene.control.skin.Utils;

//This class is responsible to read/write to/from file. 
//Also got some functions that check if the program is called appropriate.

public class FileUtils {

	//Input:
	//String fileName: the name of the file that algorithm will read the board
	//
	//Output:
	// Integer[][] board= the starting board that algorithm will try to find the solution.
	static Integer[][] getBoardFromFile(String fileName){
		Scanner inputStream=openInputFile(fileName);
		String line;
		Integer[][] board =null;
		int M=-1,N=-1;
		boolean readingDimensions=true;
		int currentRow=0,currentColumn=0;
		
		while(inputStream.hasNextLine()){ 
			line=(inputStream.nextLine());
			if(line.isEmpty()) continue;
			
			line=line.replaceAll("\\s","").trim();
			
			if(readingDimensions){
				if(line.length()!=2){
					printSyntaxMessage();
					System.exit(0);
				}
			
				M=line.charAt(0)-48;
				N=line.charAt(1)-48;
				
				System.out.println( N+","+M);
				board= new Integer[M][N];
				readingDimensions=false;
				continue;
			}
			
			
		    for(int i=0; i<N; i++){
		    	char ch = line.charAt(i);
		    	Integer number=getNumberFromChar(ch);
		    	board[currentRow][currentColumn]=number;
		    	currentColumn++;
		    	
		    	if(i==N-1){
		    		currentColumn=0;
		    		break;
		    	}
		    }
		    currentRow++;
		}
		inputStream.close();
		return board;
	}
	
	//Input:
	// String method: the search method
	//
	//Outputs:
	// 0: the search method is DFS(Depth First Search)
	// 1: the search method is BFS(Best Fist Method)
	// -1: invalid method name
	public static int getMethod(String method){
		if(method.equalsIgnoreCase("depth"))
			return 0;
		else if(method.equalsIgnoreCase("best"))
			return 1;
		else {
			printSyntaxMessage();
			System.exit(0);
			return -1;
		}
	}
	
	//This function prints a message that guides the user how to run the program
	//It is called when user tries to start program in an inappropriate way.
	public static void printSyntaxMessage(){
		System.out.println("####SYNTAX PROBLEM####\n");
		System.out.println("The correct way to run the program is:");
		System.out.println("puzzle <method> <input-file> <output-file>\n");
		System.out.println("<method> = depth|best");
		System.out.println("<input-file>= is a .txt file containing a peg solitaire puzzle."
				+ " \nInput file should be in an appropriate form. Check Documentation.");
		System.out.println("<output-file>= is the .txt file where the solution will be written");
	}
	
	//Input:
	// char ch: a character
	//
	//Output:
	// 0 = if character is 0
	// 1 = if character is 1
	// 2 = if character is 2
	// -1= if character is something else
	public static Integer getNumberFromChar(char ch) {
	    if(ch=='0') return 0;
	    else if(ch=='1') return 1;
	    else if(ch=='2') return 2;
	    else{
	    	printSyntaxMessage();
	    	System.exit(0);
	    	return -1;
	    }
	}
	
	//Inputs:
	// String[] args: the array with arguments
	//
	// This function checks the amount of arguments. If it is not the appropriate number 
	// prints a syntax message.
	static void checkArguments(String[] args){
		if(args.length!=3){
			printSyntaxMessage();
			System.exit(0);
		}
	}
	
	//Inputs:
	// String inputFile: the name of the input file
	//
	//Output: 
	// Scanner inputStream: the stream that is connected with the input file.
	private static Scanner openInputFile(String inputFile){
		Scanner inputStream=null;
		try{
	    	 inputStream = new Scanner(new File(inputFile));
	     }
	     catch(FileNotFoundException e){
	    	 System.out.println("Error opening the file");
	    	 System.exit(0);
	     }
		return inputStream;
	}
	
	
	//Inputs:
	// String solutionFileName: the name of the file that algorithm will write the solution
	// TreeNode solution: the object that contains the solution
	
	// This function writes in .txt file the number of the moves and all the moves that needed
	// to solve the puzzle.
	public static void writeSolutionToFile(String solutionFileName,TreeNode solution){
		PrintWriter outputStream=null;
		
		try{
	    	outputStream=new PrintWriter(solutionFileName);
	    }catch(FileNotFoundException e){
	    	System.out.println("Error opening the file "+solutionFileName);
	    	System.exit(0);
	    }
		
		ArrayList<String> moves= new ArrayList<String>();
		TreeNode currentNode=solution;
		    
		while(currentNode.getParent()!=null){
			moves.add(0, currentNode.getMove());
			currentNode= currentNode.getParent();
		}
		outputStream.println(solution.getDepth());
		for(String move : moves){
			outputStream.println(move);
		}
		outputStream.close();
	}
	
	
	//Inputs:
	// Integer[][] board: a board
	// 
	//This function check if the board is a rectangle.
	public static void checkBoard(Integer[][] board){
	    int m=board[0].length;
		for(int i=1; i<board.length; i++){
		    if(board[i].length!=m)
		    	printSyntaxMessage();
				System.exit(0);
		}
	}
}
