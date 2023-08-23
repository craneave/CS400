// --== CS400 Project One File Header ==--
// Name: Avery Crane
// CSL Username: crane
// Email: adcrane@wisc.edu
// Lecture #:  004 @4:00pm
// Notes to Grader: N/A

import java.util.NoSuchElementException;

/**
 * Tester class for the Hashtablemap implementation of MapADT
 */
public class HashtableMapTests {
	/**
	 * Tests the functionality of put() and the rehash() for Hashtablemap
	 * 
	 * @return true if tests pass
	 */
	@SuppressWarnings({ "unchecked", "rawtypes" })
	public static boolean putTest() {
		// create new map
		HashtableMap test1 = new HashtableMap(10);
		try {
			// test with a null key
			if (test1.put(null, "test"))
				return false;
			// test with a valid entry
			if (!test1.put(1, "test"))
				return false;
			// make sure it is in the array
			if (test1.size() != 1)
				return false;
			// test the object in the array with the get method, also sort of testing get
			// method but will check again later
			if (!test1.get(1).equals("test"))
				return false;
			// test with an already used key
			if (test1.put(1, "test1"))
				return false;
			// make sure size is still one
			if (test1.size() != 1)
				return false;
			// add a few more pairs
			test1.put(2, "test2");
			test1.put(3, "test3");
			test1.put(4, "test4");
			test1.put(5, "test5");
			test1.put(6, "test6");
			test1.put(7, "test7");
			// make sure the size is updated
			if (test1.size() != 7)
				return false;
			// try to add when the load factor is reached
			test1.put(8, "test7");
			// check num elements
			if (test1.size() != 8)
				return false;
			// test the size of the array, make sure it was doubled
			if (test1.hashtable.length != 20)
				return false;
			// lastly test if the elements were placed back in somewhere by picking a random
			// key and seeing if get can return it
			if (!test1.get(4).equals("test4"))
				return false;
		} catch (Exception e) {
			// no exceptions should be caught
			return false;
		}
		return true;
	}

	/**
	 * Tests the functionality of get() for Hashtablemap
	 * 
	 * @return true if tests pass
	 */
	@SuppressWarnings({ "unchecked", "rawtypes" })
	public static boolean getTest() {
		try {
			// create new map
			HashtableMap test2 = new HashtableMap(10);
			// scenario one: valid input
			// add a few pairs
			test2.put(1, "test2");
			test2.put(2, "test2");
			test2.put(3, "test3");
			test2.put(4, "test4");
			test2.put(5, "test5");
			test2.put(6, "test6");
			test2.put(7, "test7");
			// test if get works on valid input
			if (!test2.get(4).equals("test4"))
				return false;
			// test again
			if (!test2.get(5).equals("test5"))
				return false;
		} catch (Exception e) {
			// no exceptions should be caught
			return false;
		}
		// scenario two: null input
		try {
			// create new map
			HashtableMap test2 = new HashtableMap(10);
			// scenario one: valid input
			// add a few pairs
			test2.put(1, "test2");
			test2.put(2, "test2");
			test2.put(3, "test3");
			test2.put(4, "test4");
			test2.put(5, "test5");
			test2.put(6, "test6");
			test2.put(7, "test7");
			// trying with a null should throw an exception
			test2.get(null);
			return false;
		} catch (NoSuchElementException e) {
			// do nothing
		} catch (Exception e) {
			// if any other exception is caught
			return false;
		}
		// scenario two: not a key input
		try {
			// create new map
			HashtableMap test2 = new HashtableMap(10);
			// scenario one: valid input
			// add a few pairs
			test2.put(1, "test2");
			test2.put(2, "test2");
			test2.put(3, "test3");
			test2.put(4, "test4");
			test2.put(5, "test5");
			test2.put(6, "test6");
			test2.put(7, "test7");
			// trying with a null should throw an exception
			test2.get(10);
			return false;
		} catch (NoSuchElementException e) {
			// do nothing
		} catch (Exception e) {
			// if any other exception is caught
			return false;
		}
		// if none, return true
		return true;
	}

	/**
	 * Tests the functionality of containsKey() for Hashtablemap
	 * 
	 * @return true if tests pass
	 */
	@SuppressWarnings({ "unchecked", "rawtypes" })
	public static boolean containskeyTest() {
		// create new map
		HashtableMap test3 = new HashtableMap(10);
		try {
			// add pairs
			test3.put(2, "test2");
			test3.put(3, "test3");
			test3.put(4, "test4");
			test3.put(5, "test5");
			test3.put(6, "test6");
			test3.put(7, "test7");
			// test with a null key
			if (test3.containsKey(null))
				return false;
			// test with key not in array
			if (test3.containsKey(10))
				return false;
			// test valid input
			if (!test3.containsKey(7))
				return false;
		} catch (Exception e) {
			// no exceptions should be caught
			return false;
		}
		return true;
	}

	/**
	 * Tests the functionality of remove() for Hashtablemap
	 * 
	 * @return true if tests pass
	 */
	@SuppressWarnings({ "unchecked", "rawtypes" })
	public static boolean removeTest() {
		try {
			// create new map
			HashtableMap test3 = new HashtableMap(10);
			// add pairs
			test3.put(2, "test2");
			test3.put(3, "test3");
			test3.put(4, "test4");
			test3.put(5, "test5");
			test3.put(6, "test6");
			test3.put(7, "test7");
			// test removing a key
			test3.remove(2);
			if (test3.containsKey(2))
				return false;
			// make sure size is updated
			if (test3.size() != 5)
				return false;
			// test with key not in array
			if (test3.containsKey(10))
				return false;
		} catch (Exception e) {
			// no exceptions should be caught
			return false;
		}
		// invalid input
		try {
			// create new map
			HashtableMap test3 = new HashtableMap(10);
			// add pairs
			test3.put(2, "test2");
			test3.put(3, "test3");
			test3.put(4, "test4");
			test3.put(5, "test5");
			test3.put(6, "test6");
			test3.put(7, "test7");
			// test removing a key
			test3.remove(null);
			return false;
		} catch (NullPointerException e) {
			// do nothing
		} catch (Exception e) {
			// no exceptions should be caught
			return false;
		}
		// key not found
		try {
			// create new map
			HashtableMap test3 = new HashtableMap(10);
			// add pairs
			test3.put(2, "test2");
			test3.put(3, "test3");
			test3.put(4, "test4");
			test3.put(5, "test5");
			test3.put(6, "test6");
			test3.put(7, "test7");
			// test removing a key
			test3.remove(10);
			return false;
		} catch (NoSuchElementException e) {
			// do nothing
		} catch (Exception e) {
			// no exceptions should be caught
			return false;
		}
		return true;
	}

	/**
	 * Tests the functionality of clear() for Hashtablemap
	 * 
	 * @return true if tests pass
	 */
	@SuppressWarnings({ "unchecked", "rawtypes" })
	public static boolean clearTest() {
		// create new map
		HashtableMap test3 = new HashtableMap(10);
		try {
			// add pairs
			test3.put(2, "test2");
			test3.put(3, "test3");
			test3.put(4, "test4");
			test3.put(5, "test5");
			test3.put(6, "test6");
			test3.put(7, "test7");
			// clear the hashtable now
			test3.clear();
			// make sure the size is adjusted
			if (test3.size() != 0)
				return false;
			// make sure none of the pairs are in the array
			if (test3.containsKey(3))
				return false;
			// try again
			if (test3.containsKey(2))
				return false;
			// make sure array is still the same size
			if (test3.hashtable.length != 10)
				return false;
		} catch (Exception e) {
			// no exceptions should be caught
			return false;
		}
		return true;
	}

	public static void main(String args[]) {
		System.out.println(putTest());
		System.out.println(getTest());
		System.out.println(containskeyTest());
		System.out.println(removeTest());
		System.out.println(clearTest());

	}
}
