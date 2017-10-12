package diana.toDoList.models;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Task {
	private static int id = 0; 
	
	private int itemID;
	private String item;
	private LocalDateTime startDate;
	private LocalDateTime deadline;
	private boolean status;
	
	
	public Task (String item, LocalDateTime deadline){
		
		this.itemID = ++id;
		this.item = item;
		this.startDate = LocalDateTime.now();
		this.deadline = deadline;
		
	}
	
	public int getId() {
		return itemID;
	}


	public String getItem() {
		return item;
	}


	public void setItem(String item) {
		this.item = item;
	}


	public String getStartDateAsString() {
		DateTimeFormatter a = DateTimeFormatter.BASIC_ISO_DATE;
		return startDate.format(a);
	}
	
	public String getDeadlineAsString() {
		DateTimeFormatter b = DateTimeFormatter.BASIC_ISO_DATE;
		return deadline.format(b);
	}
	
	public LocalDateTime getStartDate() {
		return startDate;
	}
	
	public LocalDateTime getDeadline() {
		return deadline;
	}

	public void setDeadline(LocalDateTime deadline) {
		this.deadline = deadline;
	}

	public boolean getStatus() {
		return status;
	}

	public void setStatus(boolean status) {
		this.status = status;
	}
	@Override
	public String toString() {

		return this.itemID+"   "+this.item+"   "+((this.status)?"[√]":"[ ]");  
	}

}
