import java.util.ArrayList;

//This class represents a node from the tree we are going to made to find the solution

public class TreeNode {
	
	private Integer[][] board;            // ->the board
	private double heuristicValue;        // ->the value of the heuristic function.
	private int depth;                    // ->the depth of the node.
	private TreeNode parent;              // ->the parent node (NULL for the root).
	private String move;                  // ->a string to save the move, we will need this so we can print all the moves for the solution
	private long nodesChecked;			  // ->a number of the nodes that algorithm visit to find the solution.
	                                      //   that attribute takes a value if this object is a solution.
	private ArrayList<TreeNode> children; // ->the children of this node. Every child represent a move.
	                             		  //The exact number of moves is unknown so the algorithm use a list.
	
	
	//Constructor
	public TreeNode(Integer[][] board,TreeNode parent,String move,int depth){
		this.board=board;
		this.parent=parent;
		children= new ArrayList<TreeNode>();
		this.depth=depth;
		this.move=move;
		this.heuristicValue=0;
	}
	
	
	/*public int get3RowsSum(Integer[][] board){
		int sum1=1,sum2=1,sum3=1;
		for(int j=0; j<board[0].length; j++){
				sum1+=board[0][j];
		}
		for(int j=0; j<board[1].length; j++){
			sum2+=board[2][j];
		}
		for(int j=0; j<board[1].length; j++){
			sum3+=board[2][j];
		}
		
		return (int) (sum1*Math.pow(sum2,sum3));
	}*/
	public void setMove(String move){
		this.move=move;
	}
	
	public void setDepth(int depth){
		this.depth=depth;
	}
    
	//This method is called when we find the child of a node. The child will have the depth of parent plus one.
	public void addDepth(){
		this.depth++;
	}
	
	public void addChild(TreeNode child){
		children.add(child);
	}
	
	public void printData(){
		for(int i=0; i<board.length; i++){
			for(int j=0; j<board[0].length; j++){
				System.out.print(board[i][j]+" ");
			}
			System.out.println();
		}
	}
	public void printMove(){
		System.out.println(move);
	}
	
	public Integer[][] getBoard(){
		return board;
	}
	
	public void setChildren(ArrayList<TreeNode> children){
		this.children=children;
	}
	
	public ArrayList<TreeNode> getChildren(){
		return children;
	}
	
	public int getDepth(){
		return depth;
	}
	
	public TreeNode getParent(){
		return parent;
	}
	
	public String getMove(){
		return move;
	}
	
	public void setHeuristicValue(double heuristicValue){
		this.heuristicValue=heuristicValue;
	}
	
	public double getHeuristicValue(){
		return heuristicValue;
	}
	
	public void setNodesChecked(long nodesChecked){
		this.nodesChecked=nodesChecked;
	}
	
	public long getNodesChecked(){
		return nodesChecked;
	}
	
}
