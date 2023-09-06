// --== CS400 File Header Information ==--
// Name: Avery Crane
// Email: adcrane@wisc.edu
// Team: DS
// TA: Ilay Raz
// Lecturer: Florian
// Notes to Grader: N/A

/**
 * Instances of classes that implement this interface represent a single book
 * object that can be stored, sorted, and searched for based on these accessors
 * below.
 */
public interface IBook extends Comparable<IBook> {
	// constructor args (String title, int ISBN, String Author, String Genre)
	String getTitle(); // retrieve the title of this book object

	int getLength(); // retrieve the length of this book object

	String getAuthor();// retrieve the author of this book object

	String getGenre(); // retrieve the genre of this book object, either "fiction", "nonfiction",
						// "tech", "science", "philosophy"

	int compareToLength(IBook o); // returns 0 if they are equal, 1 if this is greater, -1 if other is
}