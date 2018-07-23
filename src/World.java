/*TODO
 * Write README2 text file
 * */

//File Name: World.java
//Developer: Kelvin Tagoe
//Purpose: Runs a GUI based World object simulation
//Inputs: None
//Outputs: None

import java.util.*;
import java.awt.*;
import javax.swing.*;
import javax.swing.border.Border;
import java.util.concurrent.TimeUnit;


public class World {
	
	
	/* FIELDS */
	private Item[][] ground;
	private JFrame frame;
	public final int X_LIMIT;
	public final int Y_LIMIT;
	private static World instance = null;
	
	
	/* CONSTRUCTOR */
	//Creator: Kelvin Tagoe
	//Purpose: Initializes a World object and a frame for the simulation
	//Parameters:
		//int: width
		//int: height
	//Returns: World Object
	//Side-effects: None
	private World(int x, int y) {
		this.X_LIMIT = x - 1;
		this.Y_LIMIT = y - 1;
		ground = new Item[y][x];
		this.frame = new JFrame("World Simulation");
		this.frame.setSize(550,550);
	}
	public static World getInstance() {
		if(instance == null) instance = buildWorld();
		return instance;
	}
	
	
	
	/**PRIVATE METHODS*/
	
	
	//Creator: Kelvin Tagoe
	//Purpose: Constructs a world of a particular size, then populates it with 5 immovable, 3 movable, and 2 autonomous objects.
	//Parameters: None
	//Returns: World
	//Side-effects: None
	private static World buildWorld() {
		int n = 11;
		World myWorld = new World(n, n);
		Item[] itemsArray = new Item[10];
		itemsArray[0] = new Immovable(1, 1, 'i', "Immovable1", n, n);
		itemsArray[1] = new Immovable(9, 1, 'i', "Immovable2", n, n);
		itemsArray[2] = new Immovable(1, 9, 'i', "Immovable3", n, n);
		itemsArray[3] = new Immovable(9, 9, 'i', "Immovable4", n, n);
		itemsArray[4] = new Immovable(5, 5, 'i', "Immovable5", n, n);
		itemsArray[5] = new Movable(5, 4, 'm', "Movable1", n, n);
		itemsArray[6] = new Movable(4, 6, 'm', "Movable2", n, n);
		itemsArray[7] = new Movable(6, 6, 'm', "Movable3", n, n);
		itemsArray[8] = new Autonomous(4, 5, 'a', "Autonomous1", n, n);
		itemsArray[9] = new Autonomous(6, 5, 'a', "Autonomous2", n, n);
		for (int i = 0; i < 10; i++) myWorld.add(itemsArray[i], itemsArray[i].getXCoordinate(), itemsArray[i].getYCoordinate());
		return myWorld;
	}
	
	
	
	/**OBJECT API*/
	
		
	/** Add method
	 * 
	 * @param 
	 * */
	//Creator: Kelvin Tagoe
	//Purpose: Used to populate the world by adding items to the array at cell (x,y). The cell needs to be available (empty) or the add fails.
	//Parameters:
		//Item: newItem
		//int: x coordinate
		//int: y coordinate
	//Returns: None
	//Side-effects: Adds an item object into the ground matrix in a World object 
	public void add(Item newItem, int x, int y) {
		if (ground[y][x] == null) ground[y][x] = newItem;
		else throw new ArrayStoreException("The selected cell is already filled");
	}
	
	
	
	//Creator: Kelvin Tagoe
	//Purpose: Displays the world on the screen. GUI grid displaying simple text tokens that represent the items in the world.
	//Parameters: None
	//Returns: None
	//Side-effects:	Dynamically updates the main frame 
	public void display() {
		String token;
		JLabel label;
		JPanel panel = new JPanel ( new GridLayout(Y_LIMIT+1, X_LIMIT+1) );
		JPanel[][] panelMatrix = new JPanel[this.Y_LIMIT+1][this.X_LIMIT+1];
		
		this.frame.getContentPane().setBackground( Color.black );
		this.frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.frame.setVisible(true);
		this.frame.add(panel);
		
		for (int i = 0; i <= this.Y_LIMIT ; i++) {
			for (int j = 0; j <= this.X_LIMIT ; j++) {
				panelMatrix[i][j] = new JPanel();
				panelMatrix[i][j].setBackground( Color.white );
				panelMatrix[i][j].setBorder( BorderFactory.createLineBorder(Color.black) );
				if (this.ground[i][j] != null) {
					token = Character.toString(this.ground[i][j].getToken());
					label = new JLabel( token , JLabel.CENTER);
					label.setSize(50,50);
					panelMatrix[i][j].add(label);
				}else {
					label = new JLabel(" ", JLabel.CENTER);
					label.setSize(50,50);
					panelMatrix[i][j].add(label);
				}
				panel.add(panelMatrix[i][j]);
			}
		}
	}
	
	
	
	//Creator: Kelvin Tagoe
	//Purpose: Iterates through the cells of the array changing the state of the world by updating the position of all the Autonomous and Movable objects. It does so once for each call to the method.
	//Parameters: None
	//Returns:	None
	//Side-effects:	The positions of autonomous and movable objects are updated 
	public void step() {
		Autonomous temp;
		boolean spaceFound = false;
		int oldX, oldY, newX, newY, deltaX, deltaY, spaceXCoordinate, spaceYCoordinate;
		//Sets all autonomous markers to false
		for (int i = 0; i <= this.Y_LIMIT ; i++) {
			for (int j = 0; j <= this.X_LIMIT ; j++) {
				if (this.ground[i][j] instanceof Autonomous) {
					temp = (Autonomous) this.ground[i][j];
					temp.setVisitedStatus(false);
				}
			}
		}
		//Step Process
		for (int i = 0; i <= this.Y_LIMIT ; i++) {
			for (int j = 0; j <= this.X_LIMIT ; j++) {
				if (this.ground[i][j] instanceof Autonomous) {
					temp = (Autonomous) this.ground[i][j];
					if (temp.getVisitedStatus()) break;
					oldX = temp.getXCoordinate();
					oldY = temp.getYCoordinate();
					temp.step();
					newX = temp.getXCoordinate();
					newY = temp.getYCoordinate();
					deltaX = newX - oldX;
					deltaY = newY - oldY;
					spaceXCoordinate = -1;	//Dummy variable
					spaceYCoordinate = -1;	//Dummy variable
					if (newX == oldX){//Autonomous moved up/down
						//Looks for a space
						for (int k = newY; k >= 0 && k <= this.Y_LIMIT && !spaceFound; k+=deltaY) {
							if (this.ground[k][newX] == null) {
								spaceFound = true;
								spaceXCoordinate = newX;
								spaceYCoordinate = k;
							}
						}
						if (spaceFound) {
							//Checks if there is an Immovable object within the range of the current autonomous object and the space found
							for (int k = oldY; k >= 0 && k <= this.Y_LIMIT && k != spaceYCoordinate && spaceFound; k+=deltaY) {
								if (this.ground[k][newX] instanceof Immovable) {
									this.ground[i][j].move(0 - deltaX, 0 - deltaY);
									spaceFound = false;
								}
							}
							//Shifts objects if there are no Immovable objects within the given range
							for (int k = spaceYCoordinate; spaceFound; k-=deltaY) {
								if (k == oldY) {
									//this.ground[k+deltaY][oldX] = this.ground[i][j];
									temp.setVisitedStatus(true);
									this.ground[i][j] = null;
									spaceFound = false;
								}else {
									this.ground[k][oldX] = this.ground[k-deltaY][oldX];
									this.ground[k][oldX].move(deltaX, deltaY);	
								}
							}
							spaceFound = false;
						}else this.ground[i][j].move(0 - deltaX, 0 - deltaY);
					}else if (newY == oldY) {//Autonomous moved left/right
						//Looks for a space
						for (int k = newX; k >= 0 && k <= this.X_LIMIT && !spaceFound; k+=deltaX) {
							if (this.ground[newY][k] == null) {
								spaceFound = true;
								spaceXCoordinate = k;
								spaceYCoordinate = newY;
							}
						}
						if (spaceFound) {
							//Checks if there is an Immovable object within the range of the current autonomous object and the space found
							//NOTE: IF spaceXCoordinate < oldX THE FIRST CONDITION BECOMES INVALID
							for (int k = oldX; k >= 0 && k <= this.X_LIMIT && k != spaceXCoordinate && spaceFound; k+=deltaX) {
								if (this.ground[newY][k] instanceof Immovable) {
									this.ground[i][j].move(0 - deltaX, 0 - deltaY);
									spaceFound = false;
								}
							}
							//Shifts objects if there are no Immovable objects within the given range
							for (int k = spaceXCoordinate; spaceFound; k-=deltaX) {
								if (k == oldX) {
									//this.ground[oldY][k+deltaX] = this.ground[oldY][k];
									temp.setVisitedStatus(true);
									this.ground[i][j] = null;
									spaceFound = false;
								}
								else {
									this.ground[oldY][k] = this.ground[oldY][k-deltaX];
									this.ground[oldY][k].move(deltaX, deltaY);	
								}
							}
							spaceFound = false;
						}else this.ground[i][j].move(0 - deltaX, 0 - deltaY);
					}
				}
			}
		}
		//Synchronize coordinates with location on the ground
		for (int i = 0; i <= this.Y_LIMIT ; i++) {
			for (int j = 0; j <= this.X_LIMIT ; j++) {
				if (this.ground[i][j] != null) {
					if ( this.ground[i][j].getYCoordinate() != i || this.ground[i][j].getXCoordinate() != j ) {
						deltaX = this.ground[i][j].getXCoordinate() - j;
						deltaY = this.ground[i][j].getYCoordinate() - i;
						this.ground.clone()[i][j].move(0 - deltaX, 0 - deltaY);
					}
				}
			}
		}
	}
	
	
	
	//Creator: Kelvin Tagoe
	//Purpose: Prints the ground in the form of a 2D matrix
	//Parameters: None
	//Returns: None
	//Side-effects: None 
	public void printMatrix(){
		int l1 = this.X_LIMIT+1;
	    int l2 = this.Y_LIMIT+1;
	    System.out.print("  ");
	    for (int i = 0; i < l2; i++){
	      System.out.print(" " + i);
	    }//End of Loop
	    System.out.print("\n");
	    for (int i = 0; i < l1; i++){
	      System.out.print(" " + i);
	      for (int j = 0; j < l2; j++) {
	        if (this.ground[i][j] == null) System.out.print(" -");
	        else System.out.print(" " + this.ground[i][j].getToken());
	      }//End of Inner Loop
	      System.out.print("\n");
	    }//End of Outter Loop
	    System.out.print("\n");
	  }//End of Method
	

}
