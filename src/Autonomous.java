//File Name: Item.java
//Developer: Kelvin Tagoe
//Purpose: class for autonomous objects, items that can populate and move within the World Object
//Inputs: None
//Outputs: None

import java.util.*;

public class Autonomous extends Item {

	/** FIELDS */
	private boolean visited;
	
	
	
	/** CONSTRUCTOR */
	//Creator: Kelvin Tagoe
		//Purpose: Constructor for all objects that inherit this class
		//Parameters:
			//int: x coordinate
			//int: y coordinate
			//char: token
			//String: name
			//int: x limit
			//int: y limit
		//Returns: None
		//Side-effects: None
	public Autonomous(int x, int y, char token, String name, int xLimit, int yLimit) {
		super(x, y, token, name, xLimit, yLimit);
		this.visited = false;
	}
	
	
	
	//Creator: Kelvin Tagoe
	//Purpose: Randomly picks a direction and updates the item to a new location by one cell
	//Parameters: None
	//Returns:	None
	//Side-effects:	The positions of autonomous are updated 
	public void step() {
		Random r = new Random();
		int numberOfTries = 1;
		while (numberOfTries > 0){
			int caseValue = r.nextInt(4);
			if (caseValue == 0) {	//UP
				if ( this.getYCoordinate() > 1 ) {
					this.move(0, -1);
					numberOfTries--;
				}
			}else if (caseValue == 1) {//RIGHT
				if ( this.getXCoordinate() < (this.getXLimit() - 1) ) {
					this.move(1, 0);
					numberOfTries--;
				}
			}else if (caseValue == 2) {//DOWN
				if ( this.getYCoordinate() < (this.getYLimit() - 1) ) {
					this.move(0, 1);
					numberOfTries--;
				}
			}else if (caseValue == 3) {//LEFT
				if ( this.getXCoordinate() > 1 ) {
					this.move(-1, 0);
					numberOfTries--;
				}
			}
		}
		
	}
	
	
	
	//Creator: Kelvin Tagoe
	//Purpose: Accessor method for the visited attribute
	//Parameters: None
	//Returns: an items visited status
	//Side-effects:	None
	public boolean getVisitedStatus() {	return this.visited;	}
	
	
	
	//Creator: Kelvin Tagoe
	//Purpose: Accessor method for the visited attribute
	//Parameters:	boolean: current visited status of an autonomous object
	//Returns: None
	//Side-effects: None
	public void setVisitedStatus(boolean v) {	this.visited = v;	}
	
}
