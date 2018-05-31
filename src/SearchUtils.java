import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;

public class SearchUtils{
	
	//Inputs:
	// Integer[][] A: a board
	// Integer[][] B: a board
	//
	// Output:
	// true : if the board A is equal to B
	// false: if the board A is NOT equal to B
	public static boolean isEqual(Integer[][] A, Integer[][] B){
		for(int i=0; i<A.length; i++){
			for(int j=0; j<A[i].length; j++){
				if(A[i][j]!=B[i][j])
					return false;
			}
		}
		return true;
	}
	
	
	//This function checks if a cell of the board is gap
	private static boolean isGap(int i){
		if(i==2) return true;
		return false;
	}
	
	//This function checks if a cell of the board is peg
	private static boolean isPeg(int i){
		if(i==1) return true;
		return false;
	}
	
	
	//Inputs :
	// Integer[][] A: a board 
	// Integer[][] B: a board
	//This function copies the board B to board A.
	//We have to clone or copy the array to make the board of the child.
	private static void copyArray(Integer[][] A, Integer[][] B){
		for(int i = 0; i < B.length; i++){
			for(int j=0; j<B[i].length; j++){
				A[i][j]=B[i][j];
			}
		}
	}
	
	//Inputs:
	//  TreeNode treeNode : a node that will be checked if it is a solution
	//  int maxDepth      : the max depth of the tree
	//
    //Output:
	//  true: if the three node object contains a board which is solution
	//  false: if the three node object contains a board which is not solution
	//
	// It's a solution ,if the depth of the node is equal with the max depth.
	// We already know that all the solutions will be found at max depth of the tree 
	public static boolean isSolution(TreeNode treeNode,int maxDepth){
		int treeNodeDepth=treeNode.getDepth();
		if(treeNodeDepth==maxDepth) return true;
		return false;
	}
	
	
	//Inputs: 
    // Integer[][] board: a board.
	//
	//Output:
	// int pegs: the amount of pegs that contains a board.
	//This function counts all the pegs of the board. 
	//This function helps to find the max depth of the tree
	public static int countPegs(Integer[][] board){
		int pegs=0;;
		for(int i=0; i<board.length; i++){
			for(int j=0; j<board[i].length; j++){
				if(board[i][j]==1)
					pegs++;
			}
		}
		return pegs;
	}
	
	//Inputs :
	// Integer[][] board : the starting board that algorithm tries to find the solution
	//
	//Output:
	// int maxDepth : the amount of pegs minus 1. 
	//We calculate max depth only at the beginning of the program on root's board.
	public static int getMaxDepth(Integer[][] board){
		return countPegs(board)-1;
	}
	
	//Inputs:
	//  Integer[][] board: the starting board that algorithm tries to find the solution
	//
	//Output:
	//  TreeNode root: the root of the tree which contains the starting board.
	public static TreeNode initializeSearch(Integer[][] pegSolitaire){
		TreeNode root= new TreeNode(pegSolitaire,null,"",0);
		return root;
	}
	
	
	//This function finds the children of a TreeNode which means that finds all the available moves
	//that can be done on this board.
	//It finds the children is two different ways. 
	//The first way, it finds a gap and checks if a peg can move to this gap.
	//The second way , it find a peg and checks if it can make a move.
	//The logic is that in the beginning of the game the board got only 
	//a few gaps so we use the first way, so the algorithm does
	//few comparisons to find the children.
	//As the depth of the tree growing the gaps is increasing and the peg is decreasing.
	//When the depth of the tree is max depth divided by two we know that the gaps are more than pegs 
	//and the algorithm starting to use the second way to find the children.
	//Inputs:
	//  TreeNode parent : the TreeNode object that algorithm will find it's children 
	//  int maxDepth    : the max depth of the tree
	//  int method      : the search method ( 0=DFS , 1=BFS )
	public static void findChildren(TreeNode parent,int maxDepth,int method){
		int depth= parent.getDepth();
		if(depth < (maxDepth/2))
			findChildrenFromGap(parent,method);
		else
			findChildrenFromPeg(parent,method);
			
	}
	
	
	//Inputs:
	// TreeNode parent : the TreeNode object that algorithm will find it's children 
	// int maxDepth    : the max depth of the tree
	// int method      : the search method ( 0=DFS , 1=BFS )
	
	// This function finds the children of a TreeNode with the second way.
	// Second way: it find a peg and checks if it can make a move.
	// We don't know the exact number of the children. We store them in children
	// attribute of the parent which is an ArrayList<TreeNode>.
	// 
	
	public static void findChildrenFromPeg(TreeNode parent, int method){
		ArrayList<TreeNode> children= new ArrayList<TreeNode>();
		Integer[][] board= parent.getBoard();
		for(int i=0; i<board.length; i++){
			for(int j=0; j<board[i].length; j++){
				if(isPeg(board[i][j])){
					
					if(i>1){ 
						if(isPeg(board[i-1][j]) && isGap(board[i-2][j])){
							Integer[][] topChildBoard= new Integer[board.length][board[0].length];
							copyArray(topChildBoard,board);
							topChildBoard[i][j]=2;
							topChildBoard[i-1][j]=2;
							topChildBoard[i-2][j]=1;
							String move= (i+1)+","+(j+1)+"-"+(i-1)+","+(j+1);
							int depth=parent.getDepth()+1;
							TreeNode topChild=new TreeNode(topChildBoard,parent,move,depth);
							if(method==1)
								topChild.setHeuristicValue(heuristic(topChildBoard));
							children.add(topChild);
						}
					}
					
					if(j<board[0].length-2){
						if(isPeg(board[i][j+1]) && isGap(board[i][j+2])){
							Integer[][] rightChildBoard= new Integer[board.length][board[0].length];
							copyArray(rightChildBoard,board);
							rightChildBoard[i][j]=2;
							rightChildBoard[i][j+1]=2;
							rightChildBoard[i][j+2]=1;
							String move= (i+1)+","+(j+1)+"-"+(i+1)+","+(j+3);
							int depth=parent.getDepth()+1;
							TreeNode rightChild= new TreeNode(rightChildBoard,parent,move,depth);
							if(method==1)
								rightChild.setHeuristicValue(heuristic(rightChildBoard));
							children.add(rightChild);
						}
					}
					
					if(i<board.length-2){
						if(isPeg(board[i+1][j]) && isGap(board[i+2][j])){
							Integer[][] bottomChildBoard= new Integer[board.length][board[0].length];
							copyArray(bottomChildBoard,board);
							bottomChildBoard[i][j]=2;
							bottomChildBoard[i+1][j]=2;
							bottomChildBoard[i+2][j]=1;
							String move= (i+1)+","+(j+1)+"-"+(i+3)+","+(j+1);
							int depth=parent.getDepth()+1;
							TreeNode bottomChild= new TreeNode(bottomChildBoard,parent,move,depth);
							if(method==1)
								bottomChild.setHeuristicValue(heuristic(bottomChildBoard));
							children.add(bottomChild);
						}
					}
					
					if(j>1){
						if(isPeg(board[i][j-1]) && isGap(board[i][j-2])){
							Integer[][] leftChildBoard= new Integer[board.length][board[0].length];
							copyArray(leftChildBoard,board);
							leftChildBoard[i][j]=2;
							leftChildBoard[i][j-1]=2;
							leftChildBoard[i][j-2]=1;
							String move= (i+1)+","+(j+1)+"-"+(i+1)+","+(j-1);
							int depth=parent.getDepth()+1;
							TreeNode leftChild= new TreeNode(leftChildBoard,parent,move,depth);
							if(method==1)
								leftChild.setHeuristicValue(heuristic(leftChildBoard));
							children.add(leftChild);
						}
					}
				}
			}
		}
		parent.setChildren(children);
	}
	
	//Inputs:
		// TreeNode parent : the TreeNode object that algorithm will find it's children 
		// int maxDepth    : the max depth of the tree
		// int method      : the search method ( 0=DFS , 1=BFS )
		
		// This function finds the children of a TreeNode with the first way.
		// The first way: it finds a gap and checks if any peg can move to this gap.
		// We don't know the exact number of the children. We store them in children
		// attribute of the parent which is an ArrayList<TreeNode>.
		// 
	public static void findChildrenFromGap(TreeNode parent, int method){
		ArrayList<TreeNode> children= new ArrayList<TreeNode>();
		Integer[][] board= parent.getBoard();
		for(int i=0; i<board.length; i++){
			for(int j=0; j<board[i].length; j++){
				if(isGap(board[i][j])){
					
					if(i>1){
						if(isPeg(board[i-1][j]) && isPeg(board[i-2][j])){
							Integer[][] topChildBoard= new Integer[board.length][board[0].length];
							copyArray(topChildBoard,board);
							topChildBoard[i][j]=1;
							topChildBoard[i-1][j]=2;
							topChildBoard[i-2][j]=2;
							String move= (i-1)+","+(j+1)+"-"+(i+1)+","+(j+1);
							int depth=parent.getDepth()+1;
							TreeNode topChild=new TreeNode(topChildBoard,parent,move,depth);
							if(method==1)
								topChild.setHeuristicValue(heuristic(topChildBoard));
							children.add(topChild);
						}
					}
					
					if(j<board[0].length-2){
						if(isPeg(board[i][j+1]) && isPeg(board[i][j+2])){
							Integer[][] rightChildBoard= new Integer[board.length][board[0].length];
							copyArray(rightChildBoard,board);
							rightChildBoard[i][j]=1;
							rightChildBoard[i][j+1]=2;
							rightChildBoard[i][j+2]=2;
							String move= (i+1)+","+(j+3)+"-"+(i+1)+","+(j+1);
							int depth=parent.getDepth()+1;
							TreeNode rightChild= new TreeNode(rightChildBoard,parent,move,depth);
							if(method==1)
								rightChild.setHeuristicValue(heuristic(rightChildBoard));
							children.add(rightChild);
						}
					}
					
					if(i<board.length-2){
						if(isPeg(board[i+1][j]) && isPeg(board[i+2][j])){
							Integer[][] bottomChildBoard= new Integer[board.length][board[0].length];
							copyArray(bottomChildBoard,board);
							bottomChildBoard[i][j]=1;
							bottomChildBoard[i+1][j]=2;
							bottomChildBoard[i+2][j]=2;
							String move= (i+3)+","+(j+1)+"-"+(i+1)+","+(j+1);
							int depth=parent.getDepth()+1;
							TreeNode bottomChild= new TreeNode(bottomChildBoard,parent,move,depth);
							if(method==1)
								bottomChild.setHeuristicValue(heuristic(bottomChildBoard));
							children.add(bottomChild);
						}
					}
	    
					
		
					if(j>1){
						if(isPeg(board[i][j-1]) && isPeg(board[i][j-2])){
							Integer[][] leftChildBoard= new Integer[board.length][board[0].length];
							copyArray(leftChildBoard,board);
							leftChildBoard[i][j]=1;
							leftChildBoard[i][j-1]=2;
							leftChildBoard[i][j-2]=2;
							String move= (i+1)+","+(j-1)+"-"+(i+1)+","+(j+1);
							int depth=parent.getDepth()+1;
							TreeNode leftChild= new TreeNode(leftChildBoard,parent,move,depth);
							if(method==1)
								leftChild.setHeuristicValue(heuristic(leftChildBoard));
							children.add(leftChild);
						}
					}
				}
			}
		}
		parent.setChildren(children);
	}
	
	
	//Inputs :
	// TreeNode root : the root of the tree i.e the starting board
	// int maxDepth  : the max depth of the tree
	// int method    : the search method ( 0=DFS , 1=BFS )
	
    //Output:
	// TreeNode solution : the tree node which contains the solution
	//
	// This function implements at the highest level the search algorithms.
	// The various search algorithms differ only in the way the insert
	// new nodes into list, so most of the code is common for all algorithms.
	// The list boardTocheck contains all the TreeNode objects that will be checked if any of them is a solution.
	// The list boardsChecked contains all the TreeNode objects that have checked already. 
	public static TreeNode search(TreeNode root, int maxDepth, int method){
		ArrayList<TreeNode> boardsToCheck= new ArrayList<TreeNode>();
		HashMap<String,ArrayList<Integer[][]>> boardsChecked = new HashMap<String,ArrayList<Integer[][]>>();
		TreeNode solution=null;
		TreeNode currentNode;
		
		boardsToCheck.add(root);
		
		int nodesChecked=0;
		System.out.println("Searching...");
		
		while(boardsToCheck.size()!=0){
			
			currentNode=boardsToCheck.get(0);
			Integer[][] currBoard=currentNode.getBoard();
			
			String key= currentNode.getDepth()+""+SearchUtils.getCross(currBoard);
			ArrayList<Integer[][]> boards= boardsChecked.get(key);
			
			if(boards==null){
				boards=new ArrayList<Integer[][]>();
				boardsChecked.put(key, boards);
			}
			
			boolean exists=false;
			for(Integer[][] board: boards){
				if(SearchUtils.isEqual(board, currBoard)){
					boardsToCheck.remove(0);
					exists=true;
					break;
				}
			}
			
			if(exists) continue;	
			
			if(isSolution(currentNode,maxDepth)){
				solution=currentNode;
				solution.setNodesChecked(nodesChecked);
				break;
			}
					
				
			nodesChecked++;
			
			boardsToCheck.remove(0);
			findChildren(currentNode,maxDepth,method);
			
			boards.add(currBoard);
			
			for(TreeNode child: currentNode.getChildren()){
				if(boardsToCheck.size()==0)
					boardsToCheck.add(child);
				else{
					if(method==0) //DFS
						boardsToCheck.add(0,child);
					if(method==1){ //BFS
						addNodesInOrder(boardsToCheck,child);					
					}
				}
			}
		}
		
		return solution;
	}
	
	//Inputs: 
	// ArrayList<TreeNode> list : The list that contains all the TreeNode objects that will be checked if any of them is solution.
	// TreeNode treeNode        : the TreeNode that will be inserted into the list.
	//
	// This function adds a TreeNode in the list in order .This list contains all the TreeNode objects 
	// that will be checked if any of them is solution.
	// This list is always kept in increasing order. The comparator is the heuristic value of the TreeNode. 
	// This function is called when we use the Best First Search(BFS).
	
	private static void addNodesInOrder(ArrayList<TreeNode> list, TreeNode treeNode){
		for(int i=0; i<list.size(); i++){
			
			if(treeNode.getHeuristicValue()>list.get(i).getHeuristicValue())
				continue;
			else if(treeNode.getHeuristicValue()<list.get(i).getHeuristicValue()){
				list.add(i,treeNode);
				return;
			}else{
				if(treeNode.getDepth()<list.get(i).getDepth())
					list.add(i+1,treeNode);
				else
					list.add(i,treeNode);
				return;
			}
		}
		list.add(list.size(),treeNode);
	}
	
	
	
	//Inputs: 
	// Integer[][] board: a board
	//
	//Output:
	// double heuristicValue= the heuristic value of the board.
	//
	// Giving a  a board, this function calculates the length and the width
	// of the rectangle that contains all the pegs. Also, the amount of pegs and the amount of isolated pegs.
	// This function multiplies all of them and returns the result.
	//
	// We call a peg isolated when there are no pegs near it. 
	// To be exact, we check in every possible direction(up,down,left,right) if there are at least two empty blocks.
	// When the algorithm is searching the isolated pegs drive him away from solution.Therefore, it involves them
	// in the calculation of the heuristic value.
	public static double heuristic(Integer[][] board){		
		double width= getWidthPegArea(board);
		double length= getLengthPegArea(board);
		double isolatedPegs= SearchUtils.countIsolatedPegs(board);
	    double borderPegs= SearchUtils.countPegsOnBorders(board);
		double crossPegs = SearchUtils.getCross(board);
		
		length=Math.pow(length,2);
		width=Math.pow(width,2);
		isolatedPegs=1+(Math.pow(isolatedPegs,4));
		borderPegs= 1+Math.pow(borderPegs, 3);
		crossPegs= 1+Math.pow(crossPegs, 6);
	
		double heuristicValue= length*width*isolatedPegs*borderPegs*crossPegs;
		return heuristicValue;
	}
	
	// Inputs :
	// Integer[][] board: a board.
	// 
	// Output: 
	// int width: the width of the rectangle that contains all the pegs of a board.
	
	public static int getWidthPegArea(Integer[][] board){
		int min=-1,max=0;
		for(int j=0; j<board[0].length; j++){
			for(int i=0; i<board.length; i++){
				if(board[i][j]==1){
					if(min==-1)
						min=j;
					max=j;
					
				}
					
			}
		}
		return max-min+1;
	}
	
	// Inputs :
	// Integer[][] board: a board.
	// 
	// Output: 
	// int length: the length of the rectangle that contains all the pegs of a board.
	public static int getLengthPegArea(Integer[][] board){
		int min=-1,max=0;
		
		for(int i=0; i<board.length; i++){
			if(Arrays.asList(board[i]).contains(1)){
				if(min==-1)
					min=i;
				max=i;
			}
		}
		return max-min+1;
	}
	
	// Inputs :
	// Integer[][] board : a board
	//
	// This function prints a board.
	// 1=peg
	// 0=gap
	// " "= out of the board
	public static void printBoard(Integer[][] board){
		for(int i = 0; i < board.length;i++){
			for(int j = 0; j < board[i].length; j++){
				if(board[i][j] == 0)
					System.out.print("  ");
				else if(board[i][j] == 1)
					System.out.print("1 ");
				else if(board[i][j] == 2)
					System.out.print("0 ");
			}
			System.out.println();
		}
	}
	
	//Input :
	// Integer[][] board : a board
	//
	// Output:
	// int isolatedPegs : the amount of isolated pegs.
	//
	// We call a peg isolated when there are no pegs near it. 
	// To be exact, we check in every possible direction(up,down,left,right) if there are at least two empty blocks.
	// This function finds the isolated pegs. To find them splits the board to 9 areas.
	//	+---+---+---+---+---+---+---+						+---+---+---+---+---+---+---+	
	//  |0,0|0,1|0,2|0,3|0,4|0,5|0,6|						| 2 | 2 | 1 | 1 | 1 | 8 | 8 |
	//	+---+---+---+---+---+---+---+						+---+---+---+---+---+---+---+				
	//  |1,0|1,1|1,2|1,3|1,4|1,5|1,6|						| 2 | 2 | 1 | 1 | 1 | 8 | 8 |
	//	+---+---+---+---+---+---+---+						+---+---+---+---+---+---+---+
	//  |2,0|2,1|2,2|2,3|2,4|2,5|2,6|						| 3 | 3 | 9 | 9 | 9 | 7 | 7 |
	//	+---+---+---+---+---+---+---+  Cell's area			+---+---+---+---+---+---+---+
	//  |3,0|3,1|3,2|3,3|3,4|3,5|3,6| ------------->		| 3 | 3 | 9 | 9 | 9 | 7 | 7 |
	//  +---+---+---+---+---+---+---+						+---+---+---+---+---+---+---+
	//  |4,0|4,1|4,2|4,3|4,4|4,5|4,6|						| 3 | 3 | 9 | 9 | 9 | 7 | 7 |
	//  +---+---+---+---+---+---+---+						+---+---+---+---+---+---+---+
	//  |5,0|5,1|5,2|5,3|5,4|5,5|5,6|						| 4 | 4 | 5 | 5 | 5 | 6 | 6 |
	//  +---+---+---+---+---+---+---+						+---+---+---+---+---+---+---+
	//  |6,0|6,1|6,2|6,3|6,4|6,5|6,6|						| 4 | 4 | 5 | 5 | 5 | 6 | 6 |
	//  +---+---+---+---+---+---+---+						+---+---+---+---+---+---+---+
	//
	//  The function splits the board because of the possibility of an exception.
	//  For example, if a peg is at area 2 and function check if it is isolated 
	//  top then the program will crash from an out of bounds exception, etc.
	
	public static int countIsolatedPegs(Integer[][] board){
		int isolatedPegs=0;
		for(int i=0; i<board.length; i++){
			for(int j=0; j<board[i].length; j++){
				if(isPeg(board[i][j])){
					if(i<2 && j<2){ //Area 2
						if(isIsolatedRight(i,j,board) && isIsolatedBottom(i,j,board))
							isolatedPegs++;
					}else if(i<2 && j>board[i].length-3){//Area 8
						if(isIsolatedLeft(i,j,board) && isIsolatedBottom(i,j,board))
							isolatedPegs++;
					}else if(i<2){//Area 1
						if(isIsolatedRight(i,j,board) && isIsolatedLeft(i,j,board) && isIsolatedBottom(i,j,board))
							isolatedPegs++;
					}else if(j<2 && i>board.length-3){//Area 4
						if(isIsolatedTop(i,j,board) && isIsolatedRight(i,j,board))
							isolatedPegs++;
					}else if(j<2){//Area 3
						if(isIsolatedRight(i,j,board) && isIsolatedTop(i,j,board) && isIsolatedBottom(i,j,board))
							isolatedPegs++;
					}else if(i>board.length-3 && j>board[i].length-3){//Area 6
						if(isIsolatedTop(i,j,board) && isIsolatedLeft(i,j,board))
							isolatedPegs++;;
					}else if(i>board.length-3){//Area 5
						if(isIsolatedRight(i,j,board) && isIsolatedTop(i,j,board) && isIsolatedLeft(i,j,board))
							isolatedPegs++;
					}else if(j>board[i].length-3){//Area 7
						if(isIsolatedBottom(i,j,board) && isIsolatedTop(i,j,board) && isIsolatedLeft(i,j,board))
							isolatedPegs++;
					}else{//Area 9
						if(isIsolatedBottom(i,j,board) && isIsolatedTop(i,j,board) && isIsolatedLeft(i,j,board)
								&& isIsolatedRight(i,j,board))
							isolatedPegs++;
					}
				}
			}
		}
		
		return isolatedPegs;
	}
	//Inputs:
	// int i : the row of the cell
	// int j : the column of the cell
	// Integer[][] board : the board
	// 
	// Outputs:
	// true: is isolated
	// false: is not isolated
	//
	// This function checks if a peg is right isolated. 
	public static boolean isIsolatedRight(int i, int j, Integer[][] board){
		return !isPeg(board[i][j+1]) && !isPeg(board[i][j+2]);
	}
	
	// Inputs and outputs same with the above function(s).
	//
	// This function checks if a peg is bottom isolated. 
	public static boolean isIsolatedBottom(int i, int j , Integer[][] board){
		return !isPeg(board[i+1][j]) && !isPeg(board[i+2][j]);
	}
	
	// Inputs and outputs same with the above function(s).
	//
	// This function checks if a peg is left isolated. 
	public static boolean isIsolatedLeft(int i , int j , Integer[][] board){
		return !isPeg(board[i][j-1]) && !isPeg(board[i][j-2]);
	}
	
	// Inputs and outputs same with the above function(s).
	//
	// This function checks if a peg is top isolated. 
	public static boolean isIsolatedTop(int i, int j, Integer[][] board){
		return !isPeg(board[i-1][j]) && !isPeg(board[i-2][j]);
	}
	
	//Input:
	// Integer[][] board : a board
	// 
	// Outputs:
	// int countPegs: the amount of pegs that are placed in the borders of the board.
	//
	public static int countPegsOnBorders(Integer[][] board){
		
		int countPeg=0;
		for(int i=0; i<board.length; i++){
			if(isPeg(board[i][0]))
				countPeg++;
			if(isPeg(board[i][board[i].length-1]))
				countPeg++;
		}
		
		for(int j=0; j<board[0].length; j++){
			if(isPeg(board[0][j]))
				countPeg++;
			if(isPeg(board[board.length-1][j]))
				countPeg++;
		}
		return countPeg;
	}
	
	public static boolean isBorderPeg(int i,int j,Integer[][] board){
		if(board[i+1][j]==0 || board[i-1][j]==0 || board[i][j+1]==0 || board[i][j-1]==0)
			return true;
		return false;
	}
	
	//Input:
	// Integer[][] board : a board
	// 
	// Outputs:
	// int sum: the sum of the middle column plus the sum of the middle row
	//
	public static int getCross(Integer[][] board){
		int middleW= board[0].length/2;
		int middleL= board.length/2;
		int sum=0;
		for(int i=0; i<board.length; i++){
			sum+=board[i][middleW];
		}
		for(int j=0; j<board[0].length; j++){
			sum+=board[middleL][j];
		}
		
		return sum;
	}    
}
