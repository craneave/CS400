// --== CS400 Project One File Header ==--
// Name: Avery Crane
// CSL Username: crane
// Email: adcrane@wisc.edu
// Lecture #:  004 @4:00pm
// Notes to Grader: N/A
/**
 * A bad placeholder Show class
 */
public class Show implements IShow {

	String title;
	int year;
	String provider;
	int rating;

	/**
	 * Constructor of show
	 * 
	 * @param String of title
	 * @param String of provider
	 * @param int    of year
	 * @param int    of rating
	 */
	public Show(String initTitle, int initYear, String initProvider, int initRating) {
		this.title = initTitle;
		this.year = initYear;
		this.provider = initProvider;
		this.rating = initRating;
	}

	/**
	 * DIDNT USE
	 */
	@Override
	public int compareTo(IShow o) {
		// didnt use
		return 0;
	}

	/**
	 * Returns title
	 * 
	 * @returns String title
	 */
	@Override
	public String getTitle() {
		// returns title
		return title;
	}

	/**
	 * Returns year
	 * 
	 * @returns int year
	 */
	@Override
	public int getYear() {
		// returns year
		return year;
	}

	/**
	 * Returns rating
	 * 
	 * @returns int rating
	 */
	@Override
	public int getRating() {
		// returns rating
		return rating;
	}

	/**
	 * Automatically return true, but usually checks if show has a provider
	 * 
	 * @return true if show has provider
	 */
	@Override
	public boolean isAvailableOn(String provider) {
		// for the sake of saving time, just set all providers to true
		return true;
	}
}
