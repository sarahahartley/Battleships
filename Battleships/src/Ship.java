
/**
 * Class containing the information about the different battleships
 * 
 * @author Sarah Hartley
 *
 */
public class Ship {
	//declaring variables
	int lengthOfShip;
	String type;
	int noOfShips;

	
	public Ship(){
		lengthOfShip = 0;
		type = null;
		noOfShips = 0;
	}
	
	//Getters and setters
	
	/**
	 * Method to set the details of a battleship
	 */
	public void setBattleshipDetails() {
		lengthOfShip = 4;
		type = "1";
		noOfShips = 1;
	}
	
	
	/**
	 * Method to set the details of a cruiser
	 */
	public void setCruiserDetails() {
		lengthOfShip = 3;
		type = "2";
		noOfShips = 2;
	}
	
	
	/**
	 * Method to set the details of a destroyer
	 */
	public void setDestroyerDetails() {
		lengthOfShip = 2;
		type = "3";
		noOfShips = 3;
	}
	
	
	/**
	 * Method to set the details of a submarine
	 */
	public void setSubmarineDetails() {
		lengthOfShip = 1;
		type = "4";
		noOfShips = 4;
	}
	
	
	/**
	 * Method to get the length of the ship
	 * 
	 * @return the lengthOfShip
	 */
	public int getLengthOfShip() {
		return lengthOfShip;
	}
	
	
	/**
	 * Method to get the type of ship
	 * 
	 * @return the type
	 */
	public String getType() {
		return type;
	}


	/**
	 * Method to get the number of ships to place
	 * 
	 * @return the noOfShips
	 */
	public int getNoOfShips() {
		return noOfShips;
	}
}