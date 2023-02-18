package growableArray;
import java.util.Scanner;
/*warning: doing ctrl + shift + f to reformat the code in eclipse will put the characters of
 * the summation image in the comment on one line.*/
public class CompareArrays extends ComplexityComparer {
	private Scanner keyboard = new Scanner(System.in);
	
	//the "total" parameter is the amount of elems that will be added to the array.
	
	//the formula for calculating the amount of copies in my growable array.
	@Override
	public int formula1(int total) {
		System.out.println("enter the size of the inner arrays.");
		int innerArrSize = Integer.parseInt(keyboard.nextLine());
		int n = total / innerArrSize;
		
		/*if the size goes evenly into the total then all the inner arrays are going to be filled.
		 * the formula would see that the array ran out if space and it would calculate
		 * that an inner array would be added and that copies would be made.
		 * decrementing 'n' removes the last set of copies that the summation calculated that
		 * would be done.
		 * it sets it so the array doesn't grow just because there is't any space left; it sets
		 * it to only grow once more space is needed.*/
		if(total % innerArrSize == 0) {
			n--;
		}
		
		/* the formula is the closed form of the following summation (the dashes and slashes
		 * form the image of a sigma):
		 * 
		 * 		   n
		 * 		-------
		 * 		\
		 * 		 \        i
		 * 		 /
		 *      /
		 *      -------
		 *       i = 0
		 *       
		 * eg: if the total amount of elements that the user is going to add to the array is 10,
		 * and the user chooses to have inner arrays of size 5, then 3 copies will be done.*/
		return (n * (n + 1)) / 2;
	}

	/* the formula for calculating the amount of copies in an ArrayList. (it isn't
	 * actually a formula, rather i do the calculation manually with a for loop.)*/
	@Override
	public int formula2(int total) {
		System.out.println("enter the initial size of the ArrayList.");
		int initialArrSize = Integer.parseInt(keyboard.nextLine());
		int copies = 0;
		int currArrSize = initialArrSize;
		for (int i = 1, a = initialArrSize; a < total; i++, a = (2 * a)) {
			copies += currArrSize;
			currArrSize = (int) Math.pow(2, i) * initialArrSize;
		}
		return copies;
	}
}