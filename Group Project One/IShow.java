// --== CS400 Project One File Header ==--
// Name: Avery Crane
// CSL Username: crane
// Email: adcrane@wisc.edu
// Lecture #:  004 @4:00pm
// Notes to Grader: N/A
/**
 * Instances of classes that implement this interface represent a single
 * streaming show object that can be stored, sorted, and searched for based on
 * these accessors below.
 */
public interface IShow extends Comparable<IShow> {
	// constructor args (String title, int year, int rating, String providers)
	// where the providers string includes the names of every streaming source
	String getTitle(); // retrieve the title of this show object

	int getYear(); // retrieve the year that this show was first produced

	int getRating(); // retrieve the Rotten Tomatoes Rating (out of 100)

	boolean isAvailableOn(String provider); // checks show availability
	// compareTo() method supports sorting shows in descending order by rating
}