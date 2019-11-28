import java.io.BufferedReader;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Random;

/**
 * Class to display the grid
 * 
 * @author Sarah Hartley
 *
 */
public class Grid {
	
	//declaring variables
	Ship battleship;
	Ship cruiser;
	Ship destroyer;
	Ship submarine;
	String[][] grid = new String[10][10];
	String[][] shipGrid = new String[10][10];
	int randomRow, randomColumn;
	Random getRandomLocation = new Random();
	int row, col;
	int noOfShots;
	
	
	/**
	 * Constructor
	 */
	public Grid() {
		battleship = new Ship();
		cruiser = new Ship();
		destroyer = new Ship();
		submarine = new Ship();
		noOfShots = 0; //counter for how many shots the user takes
	}
		
	
	/**
	 * Method to display the initial grid to the user
	 */
	public void displayInitialGrid() {
		int row, col;
	
		for (row=0; row<grid.length; row++) {
			for (col=0; col<grid.length; col++) {
				grid[row][col] = "-"; //setting the default display of grid to "-"
			}
		}
		System.out.print(" ");
		for (int i=0; i<grid.length; i++) {
			System.out.print(" " + i); //displaying numbers along the top of the grid to aid the user when selecting coordinates
		}
		System.out.println();
		for (row=0; row<grid.length; row++) {
			System.out.print(row + " "); //displaying numbers down the side of the grid to aid the user when selecting coordinates
			for (col=0; col<grid.length; col++) {
				System.out.print(grid[row][col] + " ");
			}
			System.out.print("\n");
		}
		
		randomlyPlaceShips(); //randomly places ships on the grid
	}
	
	
	/**
	 * Method to modify the state of the grid after a user has taken a shot
	 * 
	 * @param shotResult if the shot was a hit or miss
	 * @param row the row
	 * @param col the column
	 */
	public void modifyGrid(boolean shotResult, int row, int col) {
		
		if (shotResult == true) //changing the values held in the array if the ship has been hit
		{
			grid[col][row] = "!";
			
			if (shipGrid[col][row] == "1") {
				shipGrid[col][row] = "B"; 
				System.out.println("You've hit a battleship!");
			}
			else if (shipGrid[col][row] == "2") {
				shipGrid[col][row] = "C";
				System.out.println("You've hit a cruiser!");
			}
			else if (shipGrid[col][row] == "3") {
				shipGrid[col][row] = "D";
				System.out.println("You've hit a destroyer!");
			}
			else if (shipGrid[col][row] == "4") {
				shipGrid[col][row] = "S";
				System.out.println("You've hit a submarine!");
			}
		}
		else if (shotResult == false)
		{
			grid[col][row] = "X";
		}
		
		noOfShots++; //counter for how many shots the user has fired
		System.out.println("You have taken " + noOfShots + " shots!");
		
		System.out.print(" ");
		for (int i=0; i<grid.length; i++) { 
			System.out.print(" " + i); //displaying numbers along the top of the grid to aid the user when selecting coordinates
		}
		System.out.println();
		for (row=0; row<grid.length; row++) {
			System.out.print(row + " "); //displaying numbers down the side of the grid to aid the user when selecting coordinates
			for (col=0; col<grid.length; col++) {
				System.out.print(grid[row][col] + " "); // printing modified grid
			}
			System.out.print("\n");
		}	
	}
	
	
	/**
	 * Method to randomly place the ships on the ship grid 
	 */
	public void randomlyPlaceShips() { 
		int i, j, k;
		int counter = 0;
		int noOfShips = 0;
		int shipLength = 0;
		String shipType = null;
		
		for (row=0; row<shipGrid.length; row++) {
			for (col=0; col<shipGrid.length; col++) {
				shipGrid[row][col] = "-"; //setting the default display of grid to "-"
			}
		}
		
		//setting the values of shipLength, shipType and noOfShips
		for (counter = 0; counter<4; counter++) {
			if (counter == 0){
				battleship.setBattleshipDetails();
				shipLength = battleship.getLengthOfShip();
				shipType = battleship.getType();
				noOfShips = battleship.getNoOfShips();
			}
			else if (counter == 1) {
				cruiser.setCruiserDetails();
				shipLength = cruiser.getLengthOfShip();
				shipType = cruiser.getType();
				noOfShips = cruiser.getNoOfShips();
			}
			else if (counter == 2) {
				destroyer.setDestroyerDetails();
				shipLength = destroyer.getLengthOfShip();
				shipType = destroyer.getType();
				noOfShips = destroyer.getNoOfShips();
			}
			else if (counter == 3) {
				submarine.setSubmarineDetails();
				shipLength = submarine.getLengthOfShip();
				shipType = submarine.getType();
				noOfShips = submarine.getNoOfShips();
			}
		
			
			for (k=0; k<noOfShips; k++) {
				boolean shipPlaced;
				
				do
				{	
				shipPlaced = true;
				generateRandomCoordinate(); //generates a random coordinate
				
				if (Math.random()<0.5) { // this is to randomly generate if its printing horizontally or vertically
					//horizontal
					if (randomColumn<6) {
							for (j=0; j<shipLength; j++) {
								if (!"-".equals(shipGrid[randomRow][randomColumn+j])) { //checking if desired coordinates are all blank
									shipPlaced=false;
									break; //breaks to the end of the do while loop (line 232)
								}
							}
							if (shipPlaced ==true) {
								for (i=0; i<shipLength; i++) { //placing ship
								shipGrid[randomRow][randomColumn + i] = shipType;
								}
							}
						}
					else {
							for (j=0; j<shipLength; j++) { //checking if desired coordinates are all blank
								if (!"-".equals(shipGrid[randomRow][randomColumn-j])) {
									shipPlaced=false;
									break;
								}
							}
							if (shipPlaced ==true) {
								for (i=0; i<shipLength; i++) { //placing ship
								shipGrid[randomRow][randomColumn - i] = shipType;
								}
							}	
						}
					}	
				else { 
					//vertical
					if(randomRow<6){
							for (j=0; j<shipLength; j++) { //checking if desired coordinates are all blank
								if (!"-".equals(shipGrid[randomRow +j][randomColumn])) {
									shipPlaced=false;
									break;
								}
							}
							if (shipPlaced ==true) {
								for (i=0; i<shipLength; i++) {//placing ship
								shipGrid[randomRow +i][randomColumn] = shipType;
							}
						}
					}
					else {
							for (i=0; i<shipLength; i++) {
								for (j=0; j<shipLength; j++) { //checking if desired coordinates are all blank
									if (!"-".equals(shipGrid[randomRow -j][randomColumn])) {
										shipPlaced=false;
										break;
									}
								}
								if (shipPlaced ==true) { 
									for (i=0; i<shipLength; i++) { //placing ship
									shipGrid[randomRow -i][randomColumn] = shipType;
									}
								}
							}
						}	
					}
				}
			while (shipPlaced == false); //goes back to line 168 if shipPlaced is false
			}
		}
	}

	
	/**
	 * Method to randomly generate a coordinate from the array shipGrid		
	 */
	public void generateRandomCoordinate() {
		do {
			randomRow = getRandomLocation.nextInt(grid.length); //random y coordinate
			randomColumn = getRandomLocation.nextInt(grid.length); //random x coordinate
			}
		while (!"-".equals(shipGrid[randomRow][randomColumn])); //making sure its a blank coordinate before returning value
	}
	
	
	/**
	 * Method to check of the users shot was a hit or a miss
	 * 
	 * @param row The y coordinate to check
	 * @param col The x coordinate to check
	 * @return result A boolean containing whether the shot was a hit or a miss
	 */
	public boolean checkShot(int row, int col) 
	{
		boolean result = false;
		if (!"-".equals(shipGrid[col][row])) { //Checking if the user has hit a ship
			result = true;
		}
		if ("X".equals(grid[col][row]) || "!".equals(grid[row][col])) { //Checking if the user has already fired at the coordinate
			System.out.println("You've already fired at (" + row + "," + col + "). Try a new coordinate");
		}
		return result;
	}
	
	
	/** 
	 * Method to save the current game to two text files
	 */
	public void saveGame() {
		FileOutputStream outputStream, outputStream2 = null;
		PrintWriter printWriter = null;
		PrintWriter printWriter2 = null;
		int row, col;
		try 
		{
			outputStream = new FileOutputStream("SavedUserGrid.txt");
			outputStream2 = new FileOutputStream("SavedShipGrid.txt");
			printWriter = new PrintWriter(outputStream);
			printWriter2 = new PrintWriter(outputStream2);
		
			for (row=0; row<grid.length; row++) { //saving the user grid in a text file
				for (col=0; col<grid.length; col++) {
					printWriter.print(grid[row][col] + " ");
				}
				printWriter.println();
			}
			
			for (row=0; row<shipGrid.length; row++) { //saving the ship grid in a text file
				for (col=0; col<shipGrid.length; col++) {
					printWriter2.print(shipGrid[row][col] + " ");
				}
				printWriter2.println();
			}
		}
		catch (IOException e)
		{
			System.out.println("Error reading or writing to file: " + e);
		}
			
		finally {
			printWriter.close();
			printWriter2.close();
		}
	}
	
	/**
	 * Method to read in two 2-D arrays from separate text files and load a previously saved game
	 */
	public void loadGame() {
		FileReader fileReader, fileReader2 = null;
		BufferedReader bufferedReader = null;
		BufferedReader bufferedReader2 = null;
		try 
		{
			fileReader = new FileReader("SavedUserGrid.txt");
			fileReader2 = new FileReader("SavedShipGrid.txt");
			bufferedReader = new BufferedReader(fileReader);
			bufferedReader2 = new BufferedReader(fileReader2);
			
			String nextLine = bufferedReader.readLine();
			
			while (nextLine != null) { //reading in the user grid
				for(int row = 0; row < grid.length; row++) {
					String[] parts = nextLine.split(" ");
				    	for(int col = 0; col < grid.length; col++) {
				    		grid[row][col] = (parts[col]);
				        }
				    nextLine = bufferedReader.readLine();
				}
			}
				System.out.print(" ");
				for (int i=0; i<grid.length; i++) {
					System.out.print(" " + i);
				}
				System.out.println();
				for (int row=0; row<grid.length; row++) {
					System.out.print(row + " ");
					for (int col=0; col<grid.length; col++) {
						System.out.print(grid[row][col] + " ");
					}
						System.out.print("\n");
				}
			
			String nextLine2 = bufferedReader2.readLine();
			
			while (nextLine2 != null) {
				for(int row = 0; row < shipGrid.length; row++) { //reading in the ship grid
					String[] parts = nextLine2.split(" ");
				    	for(int col = 0; col < shipGrid.length; col++) {
				    		shipGrid[row][col] = (parts[col]);
				        }
				    nextLine2 = bufferedReader2.readLine();
				}
			}
		}
		catch (IOException e)
		{
			System.out.println("Error reading from file: " + e);
		}
	}
	
	/**
	 * Method to check the current state of the board
	 * 
	 * @return gameOver true or false
	 */
	public boolean gameState() {
		boolean gameOver = true;
		for(int row = 0; row < 10; row++) { //seeing if the game is over by checking the array shipGrid for remaining ships
		    for(int col = 0; col < 10; col++) {
		    	if ("1".equals(shipGrid[row][col]) || "2".equals(shipGrid[row][col]) || "3".equals(shipGrid[row][col]) || "4".equals(shipGrid[row][col])) {
		    		gameOver = false;
		    	}
		    }
		}
		
		return gameOver;
	}
	

}