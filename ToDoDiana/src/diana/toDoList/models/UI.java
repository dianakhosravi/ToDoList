package diana.toDoList.models;

import java.util.ArrayList;

public class UI {
	
	private static final String pFHeader = "%-4s%-20s%-15s%-15s%-6s\n";
	private static final String pFBody = "%-4d%-20s%-15s%-15s%-6s\n";

	public static void printItemList(ArrayList<Task> ex){
		System.out.printf(pFHeader,"Id","Task","StartDate","Deadline","Status");
		for(Task i :ex){
			System.out.printf(pFBody,i.getId(),i.getItem(),i.getStartDateAsString(),
					i.getDeadlineAsString(),(i.getStatus())?" [√]":" [ ]");
		}
	}

}
