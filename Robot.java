import java.util.Random;
import java.util.HashMap;
import java.util.HashSet;

/**
* Class to model an individual robot
*@Author: Dorian Barrier
*@Date: 4/26/19
**/
public class Robot{

	/**Name of the robot**/
	private String botName;
	
	/**Type of robot**/
	private BotType botType;
	
	/**Time it took to complete all valid task**/
	private int timeElapsed;
	
	/**Task completed by the robot**/
	private HashMap<Integer, BotTask> completeBotTask;

	public Robot(String botName, BotType botType){
		this.botName = botName;
		this.botType = botType;
		timeElapsed = 0;
	}
	
	/**
	* Implements logic to determine which task a robot will perform
	**/
	public void assignBotTask(){
		completeBotTask = new HashMap<>();
		HashSet<Integer> usedTask = new HashSet<>();
		Random rand = new Random();
		//find 5 unique random numbers then assign the corresponding task to the robot
		for(int i = 0; i < 5; i++){
			int taskNum = rand.nextInt(16);
			while(usedTask.contains(taskNum)){
				taskNum = rand.nextInt(16);
			}
			usedTask.add(taskNum);
			completeTask(i, taskNum, BotTask.botTaskMap.get(taskNum));
		}
	}
	
	/**
	* Contains the logic to complete the task assigned to the robot. 
	* Once each task is assigned, the allotted time given to that task will pass before another task is assigned.
	* Adds task to the robot's list of completed task. 
	**/
	private void completeTask(int idx, int taskNum, BotTask botTask){
		try{
			Thread.sleep(botTask.getDuration());
			//only credit time if the task type is specific to the robot type or a general task type
			if((BotTask.botTaskMap.get(taskNum).getTaskType() == botType.getTaskType()) 
				|| BotTask.botTaskMap.get(taskNum).getTaskType() == 0){
				timeElapsed += botTask.getDuration();
			}
			System.out.println("Task " + botTask.getDescription() + " completed! \nTotal time elapsed: " + timeElapsed);
			completeBotTask.put(idx, BotTask.botTaskMap.get(taskNum));
		}catch(InterruptedException e){
			System.out.println(e);
		}
	}
	
	/**
	*@return botName - The name of the robot
	**/
	public String getBotName(){
		return botName;
	}
	
	/**
	*@return botType - The type of robot
	**/
	public BotType getBotType(){
		return botType;
	}
	
	/**
	*@return displayTask - A String representing the task completed
	**/
	public String getCompleteTask(){
		String displayTask = "";
		for(int i = 0; i < 5; i++){
			displayTask += i + 1 + ")" + completeBotTask.get(i).getDescription() + "\n";
		}
		return displayTask;
	}
	
	/**
	*@return timeElapsed - The total time it took to complete the task
	**/
	public int getTimeElapsed(){
		return timeElapsed;
	}
	
	/**
	*@String - returns a String representation of the robot object
	**/
	public String toString(){
		return "Robot Name: " + botName + "\nRobot Type: " + botType + "\nTotal Time: " + timeElapsed 
			+ "\nTask Completed: \n" + getCompleteTask();
	}
}