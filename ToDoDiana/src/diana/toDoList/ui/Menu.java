package diana.toDoList.ui;

import java.util.Scanner;
import diana.toDoList.utitilities.ExceptionHandling;
import diana.toDoList.utitilities.TaskFunctions;


public class Menu {

	public static boolean flag = true;
	static String name;
	static int id;
	static Scanner scanner = new Scanner (System.in);

	public static void menu(){

		TaskFunctions taskFunctions = new TaskFunctions();

		do{
			System.out.println(MsjDisp.string);
			int command = ExceptionHandling.nextInt(MsjDisp.START, MsjDisp.END,"\nChoose a command by a commandId:");

			switch(command){
			case 1:
				taskFunctions.add();
				break;
			case 2:
				taskFunctions.markByName();
				break;
			case 3:
				taskFunctions.markById();
				break;
			case 4:
				taskFunctions.UnmarkById();
				break;
			case 5:
				taskFunctions.removeByName();
				break;
			case 6:
				taskFunctions.removeById();
				break;
			case 7:
				taskFunctions.clearDoneItems();
				break;
			case 8:
				taskFunctions.UserSearchByName();
				break;
			case 9:
				taskFunctions.editItems();
				break;
			case 0: 
				flag = false;
				break;
			default:
				ExceptionHandling.nextInt(MsjDisp.START, MsjDisp.END, "Choose a command by commandId:\n");
				break;
			}
		}while(flag);
	}
}
