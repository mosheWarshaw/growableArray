package growableArray;
/*This is my own idea for a growable array. An ArrayList is growable by doubling the size of
 * the array and copying all the elems (short for elements) of the original array into the new one.
 * My array is growable by having the elems in inner arrays of a 2d array. the 2d array starts with
 * one inner array. The user chooses what size the inner arrays should be (note: the user chooses
 * what size all the inner arrays should be; the user isn't choosing how big each new inner array
 * should be. they're all the same.). Every time the user wants to add an elem it is put in the inner
 * array, and when the inner array becomes full then a new 2d array is created with the
 * capacity to hold one more inner array, and the full inner array, and a new, empty inner array
 * is put into the new 2d array.
 * In my growable array, elements aren't being copied like ArrayLists, rather just the references
 * to inner arrays.
 * Use MainForComparing to compare how many copies would be made using my growable array as opposed to
 * when using an ArrayList.*/
public class GrowableArray<T> {
	private T[][] outerArr;
	private int numOfArrs = 1;
	private int arrSizes;
	private int size = 0;
	private int arrIndex;
	private int elemIndex;
	//In these fields, "last" refers to the largest index the user has set an elem in.
	private int lastUserIndex = -1;
	private int lastArrIndex = -1;
	private int lastElemIndex = -1;

	public GrowableArray(int arrSizesPar) {
		outerArr = (T[][]) new Object[1][arrSizesPar];
		arrSizes = arrSizesPar;
	}
		
	public T get(int userIndex) {
		/*The user passes in the index of the element they want as if there's only one
		 * big array (and not a collection of inner arrays), and the method calculates
		 * which index of which inner array the element is actually in.*/
		convertIndex(userIndex);
		return outerArr[arrIndex][elemIndex];
	}	

	//The elem returned is used by the insert method when it calls this method.
	public T set(int userIndex, T newElem) {
		convertIndex(userIndex);
		growIfNecessary();
		T replacedElem = outerArr[arrIndex][elemIndex];
		outerArr[arrIndex][elemIndex] = newElem;
		size++;
		updateLastIndex(userIndex, arrIndex, elemIndex);
		return replacedElem;
	}
	
	public void add(T newElem) {
		set(lastUserIndex + 1, newElem);
	}
	
	public void insert(int userIndex, T newElem) {
		convertIndex(userIndex);
		T returnedElem = set(userIndex, newElem);
		/*If newElem was placed at an index where there was another elem then shift over
		 * the elem that was at that index and all the elems after it.*/
		if(returnedElem != null) {
			T prevBumpedElem;
			T bumpedElem = returnedElem;
			for(int a = arrIndex; a <= lastArrIndex; a++) {
				for(int b = elemIndex + 1; b < lastElemIndex; b++) {
					prevBumpedElem = bumpedElem;
					bumpedElem =  outerArr[arrIndex][elemIndex];
					outerArr[arrIndex][elemIndex] = prevBumpedElem;
				}
			}
		}
	}
	
	public void delete(int userIndex) {
		convertIndex(userIndex);
		
		/*Setting it to null is necessary even though the for loops i'm going to move over all the elems
		 * and its spot will be filled with the elem to its right. The reason is that if the elem being
		 * removed was the last one (as in lastElem) entered then the for loop won't run past it to shift
		 * an elem to replace it, because there isn't an elem to shift over into its place.*/
		outerArr[arrIndex][elemIndex] = null;
		
		//Shift to the left all the elems that were to the right of the elem.
		for(int a = arrIndex; a <= lastArrIndex; a++) {
			for(int b = elemIndex + 1; b < lastElemIndex; b++) {
				outerArr[arrIndex][elemIndex - 1] = outerArr[arrIndex][elemIndex];
			}
		}
		
		decrementLastIndex(userIndex);
	}
	
	public int size() {
		return size;
	}
	
	
	private void convertIndex(int userIndex) {
		arrIndex = userIndex / arrSizes;
		elemIndex = userIndex % arrSizes;
	}
	
	private void growIfNecessary() {
		boolean isOutOfBounds = arrIndex >= numOfArrs;
		if (isOutOfBounds) {
			numOfArrs = arrIndex + 1;
			T[][] tempOuterArr = (T[][]) new Object[numOfArrs][arrSizes];
			for (int i = 0; i < outerArr.length; i++) {
				tempOuterArr[i] = outerArr[i];
			}
			outerArr = tempOuterArr;
		}
	}
	
	private void updateLastIndex(int userIndex, int arrIndex, int elemIndex) {
		if(userIndex > lastUserIndex) {
			lastUserIndex = userIndex;
			lastArrIndex = arrIndex;
			lastElemIndex = elemIndex;
		}
	}
	private void decrementLastIndex(int userIndex) {
		if(userIndex == lastUserIndex) {
			lastUserIndex--;
		}
	}
}