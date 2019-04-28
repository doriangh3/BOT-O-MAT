import java.util.Scanner;
import java.util.HashMap;

/**
* BotOMat drives the functionality needed to navigate the application's menus as well as create, view, and delete robots
* @Author: Dorian Barrier
* @Date: 4/26/19
**/
public class BotOMat{
	
	/**Boolean to exit the application when == true**/
	private static boolean exit = false;//static block?
	
	/**List containing the current robots**/
	public static HashMap<String, Robot> robotList = new HashMap<>();
	
	/**List containing the menu options**/
	private static HashMap<Integer, String> menuOptions = new HashMap<>();
	
	/**List containing the different types of robots**/
	private static HashMap<Integer, String> botTypes = new HashMap<>();
	
	/**Scanner to prompt input from the user**/
	private static Scanner input = new Scanner(System.in); 
	
	/**
	* Main method to initialize list as well as call the mainMenu
	**/
	public static void main(String[] args){
		//add botTypes to the list
		botTypes.put(1,"UNIPEDAL");
		botTypes.put(2,"BIPEDAL");
		botTypes.put(3,"QUADRUPEDAL");
		botTypes.put(4,"ARACHNID");
		botTypes.put(5,"RADIAL");
		botTypes.put(6,"AERONAUTICAL");//why no make a final string for the menu? 
		
		//Add menu options to list
		menuOptions.put(1,"Create a Robot");//if this option call method above
		menuOptions.put(2,"Display Robots");//if this option call method above
		menuOptions.put(3,"Display Leader boards");//just display who is leading in what...
		menuOptions.put(4,"Delete Robots"); //options to delete or change, name or type.
		menuOptions.put(5,"Exit Bot-O-Mat");//are you sure? doing so will delete all robots; y/n
		
		while(!exit){
			mainMenu();
		}		
	}
	
	/**
	* Contains the logic to create a robot and assign its task
	**/
	public static void createRobot(){
		//Prompt for robots name
		System.out.print("Please provide a name for your robot: ");
		String botName = input.next();
		while(robotList.containsKey(botName) || botName.equals("1") || botName.isEmpty()){
			System.out.println("Robot name is already taken. Please choose another!");
			botName = input.next();
		}
		
		//Prompt for robot type
		String typeOptions = "";
		for(int i = 1; i <= botTypes.size(); i++){
			typeOptions += i + ")" + botTypes.get(i) + "\n";
		}
		System.out.println("Please enter the [number] that corresponds with the bot type you would like to choose:\n" + typeOptions);
		int botNum = input.nextInt();
		while(botNum <= 0 || botNum > 7){
			System.out.println("Invalid input. Please enter a number between 1-6");
			botNum = input.nextInt();
		}
		
		//Create and add robot to the list of robots or return to the menu
		if(botNum != 7){
			Robot robot = new Robot(botName, BotType.valueOf(botTypes.get(botNum)));
			robotList.put(botName, robot);
			robot.assignBotTask();
			Leaderboard.checkUserTime(robot);
			Leaderboard.checkUserProductivity(robot);
		}
		else{
			mainMenu();
		}
	}
	
	/**
	* Implements the logic to navigate the main menu
	**/
	public static void mainMenu(){
		//Prompt user to select a menu option
		String menu = "";
		for(int i = 1; i <= menuOptions.size(); i++){
			menu += i + ")" + menuOptions.get(i) + "\n";
		}
		System.out.println("\nWelcome to Bot-O-Mat! \nPlease enter the [number] that corresponds with the option you would like to choose:\n" + menu);
		int option = input.nextInt();
		while(option <= 0 || option > menuOptions.size()){
			System.out.println("Invalid input. Please enter a number between 1-4: \n");
			option = input.nextInt();
		}
		
		//Trigger move to next screen
		switch(option){
		case 1: 
			createRobot();
			break;
		case 2: 
			displayRobots();
			break;
		case 3: 
			Leaderboard.displayLeaderboard();
			break;
		case 4: 
			deleteRobots();
			break;
		case 5:
			System.out.println("Thanks for using the Bot-O-Mat. Goodbye!");
			exit = true;
			break;
		}
	}
	
	/**
	* Implements the logic to select and display a particular Robot's stats
	**/
	public static void displayRobots(){
		//Prompt the user to select a robot to display or return to the main menu
		System.out.println("\nEnter the [name] of the robot whose stats you would like to view or enter [1] to return to the main menu");
		int count = 1;
		for(HashMap.Entry<String, Robot> entry: robotList.entrySet()){
			System.out.println(count + ")" + entry.getKey() + ", " + entry.getValue().getBotType().getTypeName());
			count++;
		}
		
		//Re-prompt user to view a robot's stats or return to the main menu if invalid input is given
		String option = input.next();
		while(!option.equals("1")){
			if(robotList.containsKey(option)){
				System.out.println(robotList.get(option).toString());
				System.out.println("Please enter another [name] to view or enter [1] to exit");
				option = input.next();
			}else{
				System.out.println("Robot " + option + " does not exist. \nPlease enter another [name] to delete or enter [1] to exit");
				option = input.next();
			}
		}
	}
	
	/**
	* Implements logic to select and delete a robot from the list
	**/
	public static void deleteRobots(){
		//Prompt user to select a robot to delete or return to the main menu
		System.out.println("\nEnter the [name] of the robot you would like to delete or enter [1] to return to the main menu");
		int count = 1;
		for(HashMap.Entry<String, Robot> entry: robotList.entrySet()){
			System.out.println(count + ")" + entry.getKey() + ", " + entry.getValue().getBotType().getTypeName());
			count++;
		}
		
		//Re-prompt user delete a robot or return to the main menu
		String option = input.next();
		while(!option.equals("1")){
			if(robotList.containsKey(option)){
				//if the user held any leaderboard stats delete them and find a new stat leader if applicable
				if(robotList.get(option).getBotName().equals(Leaderboard.getFastestRobot()) || 
					robotList.get(option).getBotName().equals(Leaderboard.getMostProductiveRobot())){
						robotList.remove(option);
						Leaderboard.recalcStats();
				}else{
					robotList.remove(option);
				}
					
				System.out.println("Robot " + option + " has been deleted! \nPlease enter another [name] to delete or enter [1] to exit");
				option = input.next();
			}else{
				System.out.println("Robot " + option + " does not exist. \nPlease enter another [name] to delete or enter [1] to exit");
				option = input.next();
			}
		}
	}
}