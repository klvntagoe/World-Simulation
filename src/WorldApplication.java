import java.util.*;
import java.util.concurrent.TimeUnit;

public class WorldApplication {
	
	//Creator: Kelvin Tagoe
	//Purpose: Builds a world and then runs a simulation for 100 iterations.
	//Parameters: None
	//Returns: None
	//Side-effects: None 
	public static void main(String[] args) {
		try {
			int n = 100;
			String choice;
			Scanner in = new Scanner(System.in);
			World myWorld = World.getInstance();
			World myWorld2 = World.getInstance();
			myWorld.display();
			for (int i = 2; i <= n; i++) {
				TimeUnit.MILLISECONDS.sleep(250);
				myWorld.step();
				myWorld.display();
				if ( i == n ) {
					System.out.println("Would you like to run the simulaton again?");
					choice = in.nextLine();
					if ( choice.equalsIgnoreCase("yes") ) n+=n;
				}
			}
			in.close();
		}catch(InterruptedException e) {}
		
	}
}
