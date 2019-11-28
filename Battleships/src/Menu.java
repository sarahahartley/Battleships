import java.util.Scanner;

/**
 * Top level Coordinator class
 * 
 * @author Sarah Hartley
 *
 */
public class Menu {

	private Grid callGrid;
	private Ship callShip;
	
	/**
	 * Main method
	 * 
	 * @param args Normal main arguments
	 */
	public static void main(String[] args) {
	// TODO Auto-generated method stub
	Menu menuTest = new Menu();
		menuTest.processUserChoice();
	}
	
	
	/**
	 * Constructor
	 */
	public Menu() {
		callGrid = new Grid();
		callShip = new Ship();
	}
	
	
	/**
	 * Method to display the menu to the user
	 */
	private void displayMenu() {
		
		System.out.println("\nWelcome to Battleships!");
		System.out.println("1. New Game");
		System.out.println("2. Load Game");
		System.out.println("3. Exit");
	}
	
	
	/**
	 * Method to process the input from the user and call the appropriate method 
	 */
	private void processUserChoice() {
		
		int choice;
		String game = "running";
		boolean gameOver = false;
		do
		{	
		displayMenu();
		Scanner s1 = new Scanner(System.in);
		choice = s1.nextInt();
		if (choice == 1) //Play game
		{
			System.out.println("New game");
			callGrid.displayInitialGrid();
			String shot;
			int col = 0;
			int row = 0;
			boolean shotResult = false;
			do
			{
				System.out.println("Please enter the grid location to shoot (the grid starts at 0,0 in the top left corner and finishes 9,9 in the bottom right)   i.e. 0,3 \nTo save the game at any point type '3'");
				Scanner s2 = new Scanner(System.in);
				shot = s2.nextLine();
				if (!"3".equals(shot)) {
					try {
						String[] shotCoordinates = shot.split(",");
						row = Integer.parseInt(shotCoordinates[0]);
						col = Integer.parseInt(shotCoordinates[1]);
					}
					catch (NumberFormatException e) {
						System.out.println("Sorry, there was an error with your entry. Please make sure you are inputting the coordinates correctly.\n" + e + "\n");
						continue; //skips to end of do...while loop
					}
					shotResult = callGrid.checkShot(row, col);
				}
				else if ("3".equals(shot)) {
					System.out.println("Save and exit?\nType Yes or No.");
					Scanner s3 = new Scanner(System.in);
					String validateInput = s3.nextLine();
					if ("Yes".equals(validateInput) || "yes".equals(validateInput) || "y".equals(validateInput) || "YES".equals(validateInput)) { //checking if the user definitely wants to exit game
						callGrid.saveGame();
						System.out.println("Game saved. Goodbye");
						System.exit(0); //stops running game
					}
					else {
						System.out.println("Cancelled... returning to game");
					}
				}

				if (shotResult == false && !"3".equals(shot)) { //If the user missed and they didn't cancel exiting the game
					System.out.println("MISS!");
					callGrid.modifyGrid(shotResult, row, col);
				}
				else if (shotResult == true) { //user hits ship
					System.out.println("HIT!");
					callGrid.modifyGrid(shotResult, row, col);
				}
				else if (!"3".equals(shot)){ //user input is invalid
					System.out.println("Sorry, there was an error, please make sure that you are typing the coordinate correctly. 0,0 to 9,9");
				}
				
				gameOver = callGrid.gameState(); //check if all ships have been sunk
				
				if (gameOver == true){
					System.out.println("Congratulations! You have sunk all of the battleships");
				}
			}
			while (gameOver == false || !"3".equals(shot)); //repeat while game is not over
		}
		
		else if (choice == 2)
		{
			System.out.println("Load a previously saved game");
			callGrid.loadGame(); //loads game
			String shot;
			int col = 0;
			int row = 0;
			boolean shotResult = false;
			do
			{
				System.out.println("Please enter the grid location to shoot (the grid starts at 0,0 in the top left corner and finishes 9,9 in the bottom right)   i.e. 0,3 \nTo save the game at any point type '3'");
				Scanner s2 = new Scanner(System.in);
				shot = s2.nextLine();
				if (!"3".equals(shot)) {
					try {
						String[] shotCoordinates = shot.split(",");
						row = Integer.parseInt(shotCoordinates[0]);
						col = Integer.parseInt(shotCoordinates[1]);
					}
					catch (NumberFormatException e) {
						System.out.println("Sorry, there was an error with your entry. Please make sure you are inputting the coordinates correctly.\n" + e +"\n");
						continue; //skips to end of do...while loop
					}
					shotResult = callGrid.checkShot(row, col);
				}
				else if ("3".equals(shot)) {
					System.out.println("Save and exit?\nType Yes or No.");
					Scanner s3 = new Scanner(System.in);
					String validateInput = s3.nextLine();
					if ("Yes".equals(validateInput) || "yes".equals(validateInput) || "y".equals(validateInput)) {
						callGrid.saveGame();
						System.out.println("Game saved. Goodbye");
						System.exit(0); //stops running game
					}
					else {
						System.out.println("Cancelled... returning to game");
					}
				}

				if (shotResult == false && !"3".equals(shot)) { //If the user missed and they didn't cancel exiting the game
					System.out.println("MISS!");
					callGrid.modifyGrid(shotResult, row, col);
				}
				else if (shotResult == true) {
					System.out.println("HIT!");
					callGrid.modifyGrid(shotResult, row, col);
				}
				else if (!"3".equals(shot)){
					System.out.println("Sorry, there was an error, please make sure that you are typing the coordinate correctly. 0,0 to 9,9");
				}
				
				gameOver = callGrid.gameState(); //check if all ships have been sunk
				
				if (gameOver == true){
					System.out.println("Congratulations! You have sunk all of the battleships");
				}
			}
			while (gameOver == false || !"3".equals(shot));
		
		}
	 
		else if (choice == 3)
		{
			System.out.println("Goodbye");
			System.exit(0);
		}
		
		else 
		{
			System.out.println("Sorry, we don't recognise what you entered. Please select an option from the menu.");
		}
		
		}
		while (gameOver != true);
	}
}