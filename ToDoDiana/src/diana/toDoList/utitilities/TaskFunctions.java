package diana.toDoList.utitilities;

import java.time.LocalDateTime;
import java.util.*;
import diana.toDoList.models.*;

public class TaskFunctions {

	private static ArrayList<Task> itemList = new ArrayList<Task>();
	private Task task;

	UI ui = new UI();

	// Add new items/tasks to the list
	public void add(){
		boolean loop = false;
		String answer;

		do{
			loop = false;
			boolean longLoop = true;
			Long taskDays =0L;
			String item = ExceptionHandling.nextLine("Add an item/task:");
			
			do{
				try{
					System.out.println("In how many days do you want to finish the task?");
					taskDays = ExceptionHandling.sc.nextLong();
					ExceptionHandling.sc.nextLine();
					longLoop = false;
				}catch (Exception e) {
					ExceptionHandling.sc.nextLine();
					System.err.println("You should choose a number!");
					longLoop = true;
				}
				
			}while(longLoop);
			
			LocalDateTime deadline= LocalDateTime.now().plusDays(taskDays);
			itemList.add(new Task(item, deadline));

			answer = ExceptionHandling.nextLine("Do you want to add more items? Y/N");

			boolean innerloop = false;
			do{
				if(!(answer.equalsIgnoreCase("y")^answer.equalsIgnoreCase("n"))){

					answer = ExceptionHandling.nextLine("Please asnswer by Y/N:");
					innerloop = true;
				}else
					innerloop = false;

			}while(innerloop);

			if(answer.equalsIgnoreCase("y"))
				loop = true;

		}while(loop);
		displayList();
	}

	public static void setItemList(ArrayList<Task> itemList) {
		TaskFunctions.itemList = itemList;
	}

	public ArrayList<Task> getItemList(){
		return new ArrayList<Task>(itemList);
	}

	// Show all items in the list with their status
	public void displayList(ArrayList<Task> ex) {
		UI.printItemList(ex);
	}
	public void displayList() {
		UI.printItemList(itemList);
	}

	// Search by itemName if an item exists in the list 
	public ArrayList<Task> searchByName(String name){
		ArrayList<Task> collectList1 = new ArrayList<Task>();

		for(Task task :itemList){
			if(task.getItem().equalsIgnoreCase(name)) {
				collectList1.add(task);
			}
		}
		return collectList1;
	}

	// Search by itemId if an item exists in the list 
	public Task searchById(int iD){

		for(Task task :itemList){
			if(task.getId() == iD)
				return task;
			else
				System.out.println("This item is not found!");
		}
		return null;
		//TODO
	}

	// User: Search by itemName if an item exists in the list 
	public void UserSearchByName(){
		Scanner s = new Scanner(System.in);
		ArrayList<Task> itemCollectList = new ArrayList<Task>();
		System.out.println("Which item are you looking for? enter the item's name:");
		String itemName = s.nextLine();

		for(Task task :itemList){
			if(task.getItem().equalsIgnoreCase(itemName)) {
				itemCollectList.add(task);
			}
		}
		displayList(itemCollectList);
	}

	// Remove an item by itemName
	public void removeByName() {

		displayList();

		String name = ExceptionHandling.nextLine("\nWhich item do you want to remove? (select by Name)");

		ArrayList<Task> t = searchByName(name);

		if(t.size()== 1){	
			String answer = ExceptionHandling.nextLine("Are you sure you want to remove "+ name + " item? Y/N");

			if(answer.equalsIgnoreCase("y")){

				System.out.println(name + " item is removed.");
				itemList.remove(t.get(0));
			}	
			else{
				System.out.println(name + " item is not removed.");
			}
		}
		else if (t.size()>1){
			System.out.println("There is more than one item by such name.\n");
			removeById();
		}
		else{
			System.out.println("No item exists in this list by such name");
		}
	}

	// Remove an item by itemId
	public void removeById() {

		displayList();

		int iD = ExceptionHandling.nextInt("\nWhich item do you want to remove? (select by Id)");

		Task task = searchById(iD);
		if(task== null)
			System.out.println("Not Found");

		else{	
			String answer = ExceptionHandling.nextLine("Are you sure you want to remove this item? Y/N");
			if(answer.equalsIgnoreCase("y")){
				itemList.remove(task);
				System.out.println("This item is removed.");
			}	
			else{
				System.out.println("This item is not removed.\n");
			}
			displayList();
		}
	}

	// Mark done items by itemName
	public void markByName() {

		displayList();

		String markName = ExceptionHandling.nextLine("\nWhich item do you want to mark? (specify by itemName)");

		ArrayList<Task> ttt = searchByName(markName);

		if(ttt.size()==1){
			ttt.get(0).setStatus(true);
			displayList();
		}else if (ttt.size()>1){
			System.out.println("There is more than one item in this list by such name!");
			markById();
		}
		else{
			System.out.println("There is no such item in the list.");
		}
	}

	// Mark done items by itemId
	public void markById() {

		displayList();
		int markId = ExceptionHandling.nextInt("\nWhich item do you want to mark? (specify by itemId)");

		Task task = searchById(markId);

		if(task!= null){
			task.setStatus(true);
		}else
			System.out.println("This item is not found");
		displayList();
	}

	// Unmark done items by itemId
	public void UnmarkById(){

		displayList();
		int unmark = ExceptionHandling.nextInt("\nWhich item do you want to unmark? (specify by itemId)");

		Task task = searchById(unmark);

		if(task!= null){
			task.setStatus(false);
		}else
			System.out.println("\nThis item is not found");
		displayList();
	}

	// Remove all done items
	public void clearDoneItems(){

		displayList();
		String answer = ExceptionHandling.nextLine("Do you want to clear all done tasks? Y/N");

		if(answer.equalsIgnoreCase("y")){
			Iterator<Task> it = itemList.iterator();
			while(it.hasNext()) {
				Task t = it.next();
				if(t.getStatus()) {
					it.remove();
				}
			}
			System.out.println("All done items are removed.\n");
			displayList();
		}

		else if (answer.equalsIgnoreCase("n")){
			System.out.println("No item is removed.");
			displayList();
		}
		else{
			System.out.println("Please write a valid answer (Y/N):");
			clearDoneItems();
		}
	}

	// Edit todo items properties
	public void editItems(){

		displayList();

		int editItem = ExceptionHandling.nextInt("Which item do you want to edit? (specify by itemId):");		
		task= searchById(editItem);

		if (task != null) {
			System.out.println("Your chosen task is:\n"+task);		
			switch(ExceptionHandling.nextInt(1, 3, "Which item properties do you want to edit?"
					+ "\n1. ItemTitle\n"
					+ "2. ItemDeadline\n"
					+ "3. ItemStatus\n")){
					case 1: 
						task.setItem(ExceptionHandling.nextLine("Enter the new name:"));
						break;
					case 2:		
						task.setDeadline(LocalDateTime.now().plusDays(ExceptionHandling.nextInt("Set the number of days:")));
						break;
					case 3: 	
						task.setStatus(!task.getStatus());
						break;
					default:
						ExceptionHandling.nextInt(1, 3, "Choose a number between 1 and 3:");
						break;
			}
			displayList();
		} else {
			System.out.println("This item is not found!");
		}
	}

}


