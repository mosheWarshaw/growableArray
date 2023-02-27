package growableArray;
import java.util.Scanner;
public abstract class ComplexityComparer {
	public void compareFormulas(){
		Scanner keyboard = new Scanner(System.in);
		boolean userWantsToContinue;
		int n;
		int formual1Result;
		int formual2Result;
		do{
			System.out.println("enter the input size for the formulas.");
			n = Integer.parseInt(keyboard.nextLine());
			formual1Result =  formula1(n);
			formual2Result = formula2(n);
			System.out.print("formula1: " + formual1Result + "\t");
			System.out.println("formula2: " + formual2Result);
			
			System.out.println("type \"y\" to compare the formulas again, else type anything to exit.");
			userWantsToContinue = keyboard.nextLine().equals("y") ? true : false;
		}while(userWantsToContinue);
	}
	public abstract int formula1(int n);
	public abstract int formula2(int n);
}