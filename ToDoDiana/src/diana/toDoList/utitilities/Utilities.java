package diana.toDoList.utitilities;

import java.util.InputMismatchException;
import java.util.Scanner;

public class Utilities {

	private static Scanner sc = new Scanner(System.in);

	public static int newNextInt(int first, int last, String s){

		int num=0;
		boolean loop= true;
		
		System.out.println(s);

		try {
			do{
				num  = sc.nextInt();
				sc.nextLine();
				if(num>=first && num<=last)
					loop = false;
				else
					System.out.println("The chosen number should be between "+first+" and "+last);
			}while(loop);

		} catch (InputMismatchException e) {
			sc.nextLine();
			return newNextInt(first,last,"You chose a wrong number!");
		}
		return num;
	}
}


