//File Name: Item.java
//Developer: Kelvin Tagoe
//Purpose: class for movable objects, items that can populate the World Object
//Inputs: None
//Outputs: None

public class Movable extends Item {
	
	/** CONSTRUCTOR */
	//Creator: Kelvin Tagoe
	//Purpose: Constructor for instantiating movable objects
	//Parameters:
		//int: x coordinate
		//int: y coordinate
		//char: token
		//String: name
		//int: x limit
		//int: y limit
	//Returns: None
	//Side-effects: None
	public Movable(int x, int y, char token, String name, int xLimit, int yLimit) {
		super(x, y, token, name, xLimit, yLimit);
	}
	
}
