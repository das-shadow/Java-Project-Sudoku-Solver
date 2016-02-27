import java.util.Scanner;

// Used for pass by reference mechanism
class WrapperInt{
	int value;
	WrapperInt(int value){
		this.value = value;
	}
}

public class Sudoku {

	static int inputGrid[][] = new int[][]
			{
		{0,0,0,0,0,0,0,0,0},
		{0,0,0,0,0,0,0,0,0},
		{0,0,0,0,0,0,0,0,0},
		{0,0,0,0,0,0,0,0,0},
		{0,0,0,0,0,0,0,0,0},
		{0,0,0,0,0,0,0,0,0},
		{0,0,0,0,0,0,0,0,0},
		{0,0,0,0,0,0,0,0,0},
		{0,0,0,0,0,0,0,0,0}
			};// [row][column]
	static int expGrid[][] = new int[9][9];
	
	public static void initializeSolving(){
		
		Scanner sc = new Scanner(System.in);
		for(int i=0;i<9;i++){
			for(int j=0;j<9;j++){
				inputGrid[i][j] = sc.nextInt();
				expGrid[i][j] = inputGrid[i][j];
			}
		}
//		System.out.println("\nInput Grid");
//		print_array(inputGrid);
		
		if(solveSudoku(inputGrid)){
			System.out.println("Solution: ");
			print_array(inputGrid);
		}
		else
			System.out.println("No Solution Exists!");
	}
	
	// ALGO:
	private static boolean solveSudoku(int array[][]){
		WrapperInt row = new WrapperInt(0);
		WrapperInt col = new WrapperInt(0);
		if(!findUnassignedLocation(array,row,col))
			return true;
		for(int num=1;num<=9;num++){
			if(isSafe(array,row.value,col.value,num)){
				array[row.value][col.value] = num;
				if(solveSudoku(array))
					return true;
				array[row.value][col.value] = 0; // Unassigned
			}
		}
		return false;
	}
	
	// Finds next unassigned unit (0 = unassigned)
	// retains the resultant row and column, hence use of wrapper class
	private static boolean findUnassignedLocation(int[][] array, WrapperInt row,WrapperInt col) {
		for(row.value=0;row.value<9;row.value++)
			for(col.value=0;col.value<9;col.value++)
				if(array[row.value][col.value]==0) // Unassigned
					return true;
		return false;
	}
	
	// Whether num is already present in corresponding row,col,small box
	private static boolean isSafe(int[][] array, int row,int col, int num) {
		return !usedInRow(array,row,num) &&
				!usedInCol(array,col,num) &&
				!usedInBox(array,row-row%3,col-col%3,num);
	}
	
	// Whether num is already present in corresponding row
	private static boolean usedInRow(int array[][],int row,int num){
		for(int col=0;col<9;col++)
			if(array[row][col] == num)
				return true;
		return false;
	}
	
	// Whether num is already present in the corresponding col
	private static boolean usedInCol(int array[][],int col,int num){
		for(int row=0;row<9;row++)
			if(array[row][col] == num)
				return true;
		return false;
	}
	
	// Whether num is already present in the corresponding small unit
	private static boolean usedInBox(int array[][],int row,int col,int num){
		for(int rowOffset=0;rowOffset<3;rowOffset++)
			for(int colOffset=0;colOffset<3;colOffset++)
				if(array[row+rowOffset][col+colOffset] == num)
					return true;
		return false;
	}
	

	private static void print_array(int array[][]){
		for(int i=0;i<9;i++){
			for(int j=0;j<9;j++)
				System.out.print(array[i][j]+" ");
			System.out.println();
		}
	}
	
	public static void main(String args[]){
		initializeSolving();
	}
}
