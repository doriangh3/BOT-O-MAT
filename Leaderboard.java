import java.util.HashMap;
import java.util.Scanner;

/**
* Class to model the leaderboard which displays fastest and most productive robot
*@Author: Dorian Barrier
*@Date: 4/26/19
**/
public final class Leaderboard{

	/**Fastest time to complete all of their task**/
	private static int fastestTime;
	
	/**Name of the fastest robot**/
	private static String fastestRobot;
	
	/**Represents largest amount of time/work completed by a robot**/
	private static int mostProductive;
	
	/**name of the most productive robot**/
	private static String mostProductiveRobot;
	
	/**
	*Constructor to assign default values to the leaderboard
	**/
	private Leaderboard(){
		fastestTime = 0;
		mostProductive = 0;
		fastestRobot = "";
		mostProductiveRobot = "";
	}
	
	/**
	* Contains logic to display the leaderboard stats to the user
	**/
	public static void displayLeaderboard(){
		//Prompt user to return to the main menu
		Scanner input = new Scanner(System.in); 
		System.out.print("Leaderboard Stats. Enter [1] to return to the main menu \nFastest time: " + fastestRobot + " - "+fastestTime
			+"\nMost Productive: " + mostProductiveRobot + " - " + mostProductive + "\n");
		
		//Re-Prompt user if the enter invalid input
		int back = input.nextInt();
		while(back != 1){
			System.out.println("Invalid input. Please enter [1] to return to the main menu");
			back = input.nextInt();
		}
		BotOMat.mainMenu();
	}
	
	/**
	* Implements logic to determine if a new robot has the fastest time
	**/
	public static void checkUserTime(Robot robot){
		if((fastestTime == 0) || (robot.getTimeElapsed() < fastestTime)){
			fastestTime = robot.getTimeElapsed();
			fastestRobot = robot.getBotName();
			System.out.println("Congratulations " + fastestRobot + "! You completed your task in the fastest time.");
		}
	}
	
	/**
	* Implements logic to determine if a robot has been the most productive
	**/
	public static void checkUserProductivity(Robot robot){
		//if mostProductive = 0 set that robot else check stats for greater
		if(robot.getTimeElapsed() > mostProductive){
			mostProductive = robot.getTimeElapsed();
			mostProductiveRobot = robot.getBotName();
			System.out.println("Congratulations " + mostProductiveRobot + "! You are now the most productive robot");
		}
	}
	
	/**
	* Contains the logic to recalculate the leaderboard stats when a robot who held a record is deleted
	**/
	public static void recalcStats(){
		resetLeaderboard();
		for(HashMap.Entry<String, Robot> entry: BotOMat.robotList.entrySet()){
			checkUserTime(entry.getValue());
			checkUserProductivity(entry.getValue());
		}
	}
	
	/**
	* Contains the logic to reset the leaderboard stats
	**/
	public static void resetLeaderboard(){
		fastestTime = 0;
		mostProductive = 0;
		fastestRobot = "";
		mostProductiveRobot = "";
	}
	
	/**
	*@return fastestRobot - Name of the robot with the fastest time
	**/
	public static String getFastestRobot(){
		return fastestRobot;
	}
	
	/**
	*@return mostProductiveRobot - Name of the robot who has been the most productive
	**/
	public static String getMostProductiveRobot(){
		return mostProductiveRobot;
	}
}