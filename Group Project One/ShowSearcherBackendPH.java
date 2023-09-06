
// --== CS400 Project One File Header ==--
// Name: Avery Crane
// CSL Username: crane
// Email: adcrane@wisc.edu
// Lecture #:  004 @4:00pm
// Notes to Grader: N/A
import java.util.ArrayList;
import java.util.List;

/**
 * A bad placeholder ShowSearcherBackend class
 */
public class ShowSearcherBackendPH implements IShowSearcherBackend {
// Initialize provider bools
	public Boolean net = true;
	public Boolean hulu = true;
	public Boolean disney = true;
	public Boolean prime = true;

	/**
	 * I DIDNT USE THIS
	 */
	@Override
	public void addShow(IShow show) {
	}

	/**
	 * Returns number of shows
	 * 
	 * @returns int number of shows
	 */
	@Override
	public int getNumberOfShows() {
		// just return a random number doesnt matter
		return 200;
	}

	/**
	 * DIDNT USE
	 */
	@Override
	public void setProviderFilter(String provider, boolean filter) {
// not using this one
	}

	/**
	 * gets the filter of the provider
	 * 
	 * @returns true if provider is to be used
	 */
	@Override
	public boolean getProviderFilter(String provider) {
		// return the state of the bool based on proivder passed through
		if (provider.equals("N"))
			return net;
		if (provider.equals("H"))
			return hulu;
		if (provider.equals("D"))
			return disney;
		if (provider.equals("P"))
			return prime;
		return false;
	}

	/**
	 * Toggle the provider
	 * 
	 * @param String of provider
	 */
	@Override
	public void toggleProviderFilter(String provider) {
		// set the providers to the opposite as they were
		if (provider.equals("N"))
			net = !net;

		if (provider.equals("H"))
			hulu = !hulu;

		if (provider.equals("D"))
			disney = !disney;
		if (provider.equals("P"))
			prime = !prime;
	}

	/**
	 * Gets list filtered by title
	 * 
	 * @param String title
	 * @returns List<IShow> of shows with title
	 */
	@Override
	public List<IShow> searchByTitleWord(String word) {
		// make a list of shows with sponge as a similar word to simulate a filtered
		// list
		List<IShow> string = new ArrayList<IShow>();
		Show show = new Show("Spongebob", 2003, "Netflix", 78);
		Show show2 = new Show("Spongelife", 2010, "Hulu", 12);
		Show show3 = new Show("Spongedog", 2015, "Netflix, Hulu", 99);
		Show show4 = new Show("sponge", 2022, "Prime Video", 1);
		Show show5 = new Show("Spongeball", 2021, "Disney+", 56);
		string.add(show);
		string.add(show2);
		string.add(show3);
		string.add(show4);
		string.add(show5);
		return string;
	}

	/**
	 * Gets list filtered by year
	 * 
	 * @param int year
	 * @returns List<IShow> of shows with year
	 */
	@Override
	public List<IShow> searchByYear(int year) {
		// make a list of shows with 2003 as a similar year to simulate a filtered list
		List<IShow> string = new ArrayList<IShow>();
		Show show = new Show("Dog", 2003, "Netflix", 78);
		Show show2 = new Show("Cat", 2003, "Hulu", 12);
		Show show3 = new Show("Dad", 2003, "Netflix, Hulu", 99);
		Show show4 = new Show("sponge", 2003, "Prime Video", 1);
		Show show5 = new Show("Hello", 2003, "Disney+", 56);
		string.add(show);
		string.add(show2);
		string.add(show3);
		string.add(show4);
		string.add(show5);
		return string;
	}
}
