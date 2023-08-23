// --== CS400 Project One File Header ==--
// Name: Avery Crane
// CSL Username: crane
// Email: adcrane@wisc.edu
// Lecture #:  004 @4:00pm
// Notes to Grader: This class uses a helper class LinkedHashEntry, which creates the key value pairs for the linked list

import java.util.NoSuchElementException;

/**
 * This class creates a Hashtable map using the methods implemented from MapADT
 * 
 */
public class HashtableMap<KeyType, ValueType> implements MapADT<KeyType, ValueType> {
// create the hashtable by making an array of linked lists with the help of helper class LinkedHashEntry
	@SuppressWarnings("rawtypes")
	LinkedHashEntry[] hashtable;
// protected int of the amount of elements stored in array
	private int currentSize;

	/**
	 * Constructor for HashtableMap with capacity input
	 */
	public HashtableMap(int capacity) {
		// set table size to capacity and size to 0
		this.hashtable = new LinkedHashEntry[capacity];
		this.currentSize = 0;
	}

	/**
	 * Default constructor
	 */
	public HashtableMap() {
		// set table size to 20 and size to 0
		this.hashtable = new LinkedHashEntry[20];
		this.currentSize = 0;
	}

	/**
	 * Put function which will add new key value pairs to the array at their
	 * specific hashcodes, if there are collisions, due to the nature of the linked
	 * lists, chaining will be used to link the pairs together, if the array becomes
	 * too full, based off of the load factor, then the array size will be doubled
	 * and all values with be rehashed
	 * 
	 * @return true if the pair was put into the array, false otherwise;
	 */
	@SuppressWarnings({ "unchecked", "rawtypes" })
	@Override
	public boolean put(KeyType key, ValueType value) {
		// if key is null or the key already exists in the array, then return false
		if (key == null || this.containsKey(key))
			return false;
		// if the array load factor is reached, then use the rehash helper method to fix
		if (((double) (currentSize + 1) / (double) hashtable.length) >= 0.75)
			rehash();
		// hash is set equal to the hashcode for the key
		int hash = (int) Math.abs(key.hashCode()) % hashtable.length;
		// if the index for this key is empty, then creat a new LinkedHashEntery with
		// those key value pairs
		if (hashtable[hash] == null)
			hashtable[hash] = new LinkedHashEntry(key, value);
		else {
			// if not, then set entry equal to that first entry in the position
			LinkedHashEntry entry = hashtable[hash];
			// this while loop then goes through the entries at this position until it
			// reaches the last one, which will then set the 'next' of that pair, to this
			// pair
			while (entry.getKey() != key && entry.getNext() != null)
				entry = entry.getNext();
			if (entry.getKey() == key)
				return false;
			else
				// setting the next to the current pair
				entry.setNext(new LinkedHashEntry(key, value));
		}
		// then if it is made this far than that means it was put in the array and size
		// can be increased
		currentSize++;
		return true;
	}

	/**
	 * Rehash helper function which will take the array, double the size, and rehash
	 * all of the pairs
	 */
	@SuppressWarnings({ "rawtypes", "unchecked" })
	private void rehash() {
		// initialize counters for later, and create a new array called copy
		int counter = 0;
		int counter2 = 0;
		LinkedHashEntry[] copy;
		// set copy to the same length as the original array + 1 for extra space
		copy = new LinkedHashEntry[currentSize + 1];
		// run through he original hashtable
		for (int x = 0; x <= size(); x++) {
			// if the position is not null
			if (hashtable[counter2] != null) {
				// then set curr to the entry at that position
				LinkedHashEntry curr = hashtable[counter2];
				// set copys new LinkedHashEntry at x to currs key and value data
				copy[x] = new LinkedHashEntry(curr.getKey(), curr.getValue());
				// then if their are more entries at this spot
				while (curr.getNext() != null) {
					// increase counter for every one their is
					counter++;
					// move down the chain
					curr = curr.getNext();
					// then set that pair to the next position from that original value x from the
					// loop + however many spots are to be added
					copy[x + counter] = new LinkedHashEntry(curr.getKey(), curr.getValue());
					// then set x = to counter so that when you place the next pair in copy, it can
					// go in the correct spot
					x = counter;
				}
			}
			// increase counter2 by one each time so that the original table is increasing
			// by one
			counter2++;
		}
		// once all of that is done then you can increase the OG array times 2, and set
		// size to 0
		int length = hashtable.length * 2;
		this.hashtable = new LinkedHashEntry[length];
		currentSize = 0;
		// now go through the place holder array and grab every index and rehash them
		// with put
		for (int x = 0; x < copy.length; x++) {
			if (copy[x] != null)
				put((KeyType) copy[x].getKey(), (ValueType) copy[x].getValue());
		}
	}

	/**
	 * Searches the table by a key and then returns the value for it
	 * 
	 * @Throws NoSuchElementException when key is null or cant be found
	 * @returns the value of the key searched
	 */
	@SuppressWarnings({ "unchecked", "rawtypes" })
	@Override
	public ValueType get(KeyType key) throws NoSuchElementException {
		// if key is null then throw the exception
		if (key == null)
			throw new NoSuchElementException();
		// hash to hash code of key
		int hash = (int) Math.abs(key.hashCode()) % hashtable.length;
		// if it is not found, throw exception
		if (hashtable[hash] == null)
			throw new NoSuchElementException();
		else {
			// set entry to that entry
			LinkedHashEntry entry = hashtable[hash];
			// go through all the entries at that location and then if the key of that entry
			// is equal the one being searched, then return that entries value
			while (entry.getKey() != key && entry.getNext() != null)
				entry = entry.getNext();
			if (entry.getKey() == key)
				return (ValueType) entry.getValue();
		}
		// if none of this, then throw an exception
		throw new NoSuchElementException();
	}

	/**
	 * Getter method for how many elements
	 * 
	 * @returns the number of elements
	 */
	@Override
	public int size() {
		return currentSize;
	}

	/**
	 * Searches the table by a key and then returns true or false if it is in the
	 * array
	 * 
	 * @returns true if key exists
	 */
	@SuppressWarnings("rawtypes")
	@Override
	public boolean containsKey(KeyType key) {
		// if key is null return false
		if (key == null)
			return false;
		// hash set to hashcode
		int hash = (int) Math.abs(key.hashCode()) % (hashtable.length);
		// if there is no values at index return false
		if (hashtable[hash] == null)
			return false;
		else {
			// go through all the entries at that location and then if the key of that entry
			// is equal the one being searched, then true
			LinkedHashEntry entry = hashtable[hash];
			while (entry.getKey() != key && entry.getNext() != null)
				entry = entry.getNext();
			if (entry.getKey() == key)
				return true;
		}
		// if none of that, false
		return false;
	}

	/**
	 * Searches the table by a key and then removes the pair with that key, and
	 * returns the value of it
	 * 
	 * @throws NullPointerException   if key is null
	 * @throws NoSuchElementException if key is not found
	 * @returns value of removed key
	 */
	@SuppressWarnings({ "unchecked", "rawtypes" })
	@Override
	public ValueType remove(KeyType key) throws NullPointerException, NoSuchElementException {
		// key is null throw exception
		if (key == null)
			throw new NullPointerException();
		int hash = (int) Math.abs(key.hashCode()) % (hashtable.length);
		// if nothing therer throw no element exception
		if (hashtable[hash] == null)
			throw new NoSuchElementException();
		else {
			// create a previous entry and current entry
			LinkedHashEntry previousEntry = null;
			LinkedHashEntry entry = hashtable[hash];
			// look for the entry with the key entered, setting previous entry to the
			// current, then setting current to the next
			while (entry.getKey() != key && entry.getNext() != null) {
				previousEntry = entry;
				entry = entry.getNext();
			}
			// then if the key searching to remove is found, then set a temp to the value of
			// to be removed key
			if (entry.getKey() == key) {
				ValueType temp = (ValueType) entry.getValue();
				// if there was nothing behind it, then set the current entry to the next of the
				// to be removed
				if (previousEntry == null)
					hashtable[hash] = entry.getNext();
				else
					// otherwise set the previous to the next of the current to be removed, then
					// decrease size, and return the value
					previousEntry.setNext(entry.getNext());
				currentSize--;
				return temp;
			}
		}
		// place holder
		return null;
	}

	/**
	 * Resets the table
	 * 
	 */
	@Override
	public void clear() {
		// set length to the length of table
		int length = hashtable.length;
		// create a new table with same length
		hashtable = new LinkedHashEntry[length];
		// set size to 0
		currentSize = 0;
	}
}
