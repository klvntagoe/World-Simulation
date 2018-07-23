//File Name: Item.java
//Developer: Kelvin Tagoe
//Purpose: class for immovable objects, items that can populate the World Object
//Inputs: None
//Outputs: None

public class Immovable extends Item {
	
	/** CONSTRUCTOR */
	//Creator: Kelvin Tagoe
	//Purpose: Constructor for instantiating immovable objects
	//Parameters:
		//int: x coordinate
		//int: y coordinate
		//char: token
		//String: name
		//int: x limit
		//int: y limit
	//Returns: None
	//Side-effects: None
	public Immovable(int x, int y, char token, String name, int xLimit, int yLimit) {
		super(x, y, token, name, xLimit, yLimit);
	}
	
	
	
	//Creator: Kelvin Tagoe
	//Purpose: Overloads the inherited method so that immovable objects cannot have their x and y coordinates changed after being initialized 
	//Parameters:
		//int: x coordinate
		//int: y coordinate
		//Both dummy variables
	//Returns: None
	//Side-effects: None
	public void move(int xDirection, int yDirection) {}
	
}
