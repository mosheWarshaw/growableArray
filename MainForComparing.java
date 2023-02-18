package growableArray;
/*if you have an estimate of the amount of elems you'll eventually have in your array
 * then you can use this class to calculate whether it would be more efficient to use an ArrayList
 * or my growable array, (part of that includes figuring out how big the inner array's size should be).*/
public class MainForComparing {
	public static void main(String[] args) {
		CompareArrays obj = new CompareArrays();
		obj.compareFormulas();
	}
}