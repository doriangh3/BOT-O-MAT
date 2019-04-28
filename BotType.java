/**
*Enum to model the different types of robots
*@author: Dorian Barrier
*@date: 4/26/19
**/
public enum BotType{

		UNIPEDAL("Unipedal",1), BIPEDAL("Bipedal",2), QUADRUPEDAL("Quadrupedal",4), ARACHNID("Arachnid",8), RADIAL("Radial",5), AERONAUTICAL("Aeronautical",3);
		
		/**Name of the robot type**/
		private String typeName;
		
		/**Represents a number that only gives credit for task that have the same taskType**/
		private int taskType;
	
		/**
		* Constructor to set default values for each robot type
		**/
		private BotType(String typeName, int taskType){
			this.typeName = typeName;
			this.taskType = taskType;
		}
		
		/**
		*@return typeName - Name of the robot type
		**/
		public String getTypeName(){
			return typeName;
		}
		
		/**
		*@return taskType - Integer representing the type of robots that will get credit for completing certain task
		**/
		public int getTaskType(){
			return taskType;
		}
	}