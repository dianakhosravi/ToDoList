package diana.toDoList.utitilities;

import java.util.*;

public class ExceptionHandling {

	public static Scanner sc = new Scanner(System.in);

	public static String nextLine(String s){
		System.out.println(s); 
		return sc.nextLine();
	}
	public static int nextInt(int first, int last, String s){
		int num = 0;
		boolean loop = true;
		try {
			do{
				System.out.println(s);
				num = sc.nextInt();
				sc.nextLine();
				if (num >= first && num <= last) {
					loop = false;
				}
				else
					System.out.println("The Id should be between " + first + " and " + last + "\n");
			}while(loop);

		} catch (InputMismatchException e) {
			System.err.println("You did not choose a number!");
		}
		return num;
	}
	
	public static int nextInt(String s){
		int num = 0;
		System.out.println(s);		
		try {
			num = sc.nextInt();
			sc.nextLine();
		} catch (InputMismatchException e) {
			sc.nextLine();
			System.out.println("Wrong input! Try again ...");
			nextInt(s);
		}
		return num;
	}
	
	public static Long nextLong(String s) {
		Long days = 0L;
		System.out.println(s);	
		try {
			days = sc.nextLong();
			sc.nextLine();
		} catch (InputMismatchException e) {
			System.err.println("Wrong input! Try again ...");
			nextLong(s);
		}
		return days;
	}
}
