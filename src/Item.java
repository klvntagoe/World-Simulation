//File Name: Item.java
//Developer: Kelvin Tagoe
//Purpose: Abstract class for items that can populate the World object. Parent class for movable, immovable, and autonomous objects.
//Inputs: None
//Outputs: None

public abstract class Item {
	
	
	/** FIELDS
	 * x: item's x coordinate
	 * y: item's y coordinate
	 * token: symbol that represents the item when printed to the screen
	 * name: item's name
	 * */
	private int x, y;
	private char token;
	private String name;
	public final int XLIMIT;
	public final int YLIMIT;
	
	
	//Creator: Kelvin Tagoe
	//Purpose: Constructor for instantiating autonomous objects
	//Parameters:
		//int: x coordinate
		//int: y coordinate
		//char: token
		//String: name
		//int: x limit
		//int: y limit
	//Returns: None
	//Side-effects: None
	public Item( int x, int y, char token, String name, int xLimit, int yLimit) {
		this.x = x;
		this.y = y;
		this.token = token;
		this.name = name;
		this.XLIMIT = xLimit - 1;
		this.YLIMIT = yLimit - 1;
	}
	
	
	
	/**OBJECT API*/
	
	
	//Creator: Kelvin Tagoe
	//Purpose: Accessor method for the x coordinate attribute
	//Parameters: None
	//Returns: an item object's x coordinate
	//Side-effects: None 
	public int getXCoordinate() {
		return this.x;
	}
	
	
	
	//Creator: Kelvin Tagoe
	//Purpose: Accessor method for the y coordinate attribute
	//Parameters: None
	//Returns: an item object's y coordinate
	//Side-effects: None 
	public int getYCoordinate() {
		return this.y;
	}
	
	
	
	//Creator: Kelvin Tagoe
	//Purpose: Accessor method for the token attribute
	//Parameters: None
	//Returns: an item object's token
	//Side-effects: None 
	public char getToken() {
		return this.token;
	}
	
	
	
	//Creator: Kelvin Tagoe
	//Purpose: Accessor method for the name attribute
	//Parameters: None
	//Returns: an item object's name
	//Side-effects: None 
	public String getName() {
		return this.name;
	}
	
	
	
	//Creator: Kelvin Tagoe
	//Purpose: Accessor method for the x limit attribute
	//Parameters: None
	//Returns: an item object's x limit
	//Side-effects: None 
	public int getXLimit() {
		return this.XLIMIT;
	}
	
	
	
	//Creator: Kelvin Tagoe
	//Purpose: Accessor method for the y limit attribute
	//Parameters: None
	//Returns: an item object's y limit
	//Side-effects: None 
	public int getYLimit() {
		return this.YLIMIT;
	}
	
	
	
	//Creator: Kelvin Tagoe
	//Purpose: Shifts an item object's coordinate values in the direction specified
	//Parameters:
		//int: x direction
		//int: y direction
	//Returns: None
	//Side-effects: None 
	public void move(int xDirection, int yDirection) {
		this.x += xDirection;
		this.y += yDirection;
	}
	
}
