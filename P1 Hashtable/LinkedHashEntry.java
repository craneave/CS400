// --== CS400 Project One File Header ==--
// Name: Avery Crane
// CSL Username: crane
// Email: adcrane@wisc.edu
// Lecture #:  004 @4:00pm
// Notes to Grader: This is said helper class

/**
 * This class stores the linked pairs for the HashtableMapc class
 * 
 */
public class LinkedHashEntry<KeyType, ValueType> {
	// set KeyType and ValueType values along with a LinkedHashEntry next value
	private KeyType key;
	private ValueType value;
	private LinkedHashEntry next;

	/**
	 * Constructor for LinkedHashEntry with the key and values to be used
	 */
	LinkedHashEntry(KeyType key, ValueType value) {
		this.key = key;
		this.value = value;
		this.next = null;
	}

	/**
	 * Gets value of entry
	 * 
	 * @returns the value of the entry
	 */
	public ValueType getValue() {
		return value;
	}

	/**
	 * Sets value of entry
	 * 
	 */
	public void setValue(ValueType value) {
		this.value = (ValueType) value;
	}

	/**
	 * Gets key of entry
	 * 
	 * @returns the key of the entry
	 */
	public KeyType getKey() {
		return key;
	}

	/**
	 * Gets the next of entry
	 * 
	 */
	public LinkedHashEntry getNext() {
		return next;
	}

	/**
	 * Sets the next of entry
	 * 
	 */
	public void setNext(LinkedHashEntry next) {
		this.next = next;
	}

}