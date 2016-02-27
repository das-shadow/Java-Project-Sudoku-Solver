import javax.swing.JFrame;

// Creates the gui & Passes value to Sudoku
public class GUI extends JFrame{
	
	GUI(){
		initComponent();
	}
	
	private void initComponent(){
		setTitle("Sudoku Solver");
		setVisible(true);
	}
	
	public static void main(String args[]){
		GUI gui = new GUI();
	}
}
