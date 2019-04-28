import java.util.HashMap;

/**
* Enum to model the different kinds of task a robot may complete
*@Author: Dorian Barrier
*@Date: 4/26/19
**/
public enum BotTask{

	DISHES("do the dishes", 1000, 0), SWEEP("sweep the house", 300, 0), LAUNDRY("do the laundry", 3000, 0),
	RECYCLING("take out the recycling", 4000, 0), SANDWHICH("Make a sammich", 700, 0), LAWN("mow the lawn", 20000, 0), 
	LEAVES("rake the leaves", 18000, 0), DOGBATH("give the dog a bath", 14500, 0), COOKIES("bake some cookies", 8000, 0), 
	CARWASH("wash the car", 20000, 0),HOPSCOTCH("play a round of hopscotch", 15000, 1), FIVEK("run a 5k", 25000, 2),
	FLIGHT("take a test flight around the world", 30000, 3), BEARCRAWLS("practice bear crawls", 1200, 4), WEBSWING("go web swinging", 7000, 8),
	STROLL("take a st(roll) down the street", 16000, 5);
	
	/**HashMap containing each task as well as a unique integer so they may be randomly assigned to a robot**/
	public static final HashMap<Integer, BotTask> botTaskMap = new HashMap<>(values().length, 1);
	
	/**Static block to initialize botTaskMap with each task**/
	static{
		int count = 0;
		for(BotTask x: values()){
			botTaskMap.put(count, x);
			count++;
		}
	}
	
	/**Description of the task**/
	private final String description;
	
	/**Time the task takes to complete**/
	private final int duration;
	
	/**Represents a number that only gives credit to Robot Types with the same task Type**/
	private final int taskType;
	
	/*
	* Constructor to assign the default values to each enum value
	**/
	private BotTask(String description, int duration, int taskType){
		this.description = description;
		this.duration = duration;
		this.taskType = taskType;
	}
	
	/**
	*@retutn description - The description of the Task
	**/
	public String getDescription(){
		return description;
	}
	
	/**
	*@return duration - The duration a task takes to complete
	**/
	public int getDuration(){
		return duration;
	}
	
	/**
	* @return taskType - Integer representing which types of robot will get credit when the task is completed
	**/
	public int getTaskType(){
		return taskType;
	}
}