
// --== CS400 Project One File Header ==--
// Name: Avery Crane
// CSL Username: crane
// Email: adcrane@wisc.edu
// Lecture #:  004 @4:00pm
// Notes to Grader: N/A
import java.util.List;
import java.util.Scanner;

/**
 * The frontend class for this program
 */
public class ShowSearcherFrontend implements IShowSearcherFrontend {

// private variables 
	private IShowSearcherBackend searcher;
	private String keyWord;
	private int keyYear;
	private String keyCommand;
	private String keyFilter;
	private Scanner scanObj;

	/**
	 * Constructor for this class with no initial show name
	 * 
	 * @param IShowSearcherBackend object
	 */
	public ShowSearcherFrontend(IShowSearcherBackend initSearcher) {
		this.searcher = initSearcher;
		this.keyWord = null;
		this.keyYear = 0;
		this.keyCommand = null;
	}

	/**
	 * Constructor for this class with initial show name
	 * 
	 * @param IShowSearcherBackend object
	 * @param String               show name
	 */
	public ShowSearcherFrontend(String initShow, IShowSearcherBackend initSearcher) {
		this.searcher = initSearcher;
		this.keyWord = initShow;
		this.keyYear = 0;
		this.keyCommand = null;
	}

	/**
	 * While loop to run the commands for this program until user enters Q
	 * 
	 */
	@Override
	public void runCommandLoop() {
		// open scanner
		scanObj = new Scanner(System.in);
		// set choice to true
		Boolean userChoice = true;
		System.out.println("Welcome to the Show Searcher App!\n=================================");
		// display the command menu
		displayCommandMenu();
		// once the keyCommand is selected, decide which route to take it
		while (userChoice) {
			if (keyCommand.equals("t"))
				titleSearch();
			if (keyCommand.equals("y"))
				yearSearch();
			if (keyCommand.equals("f"))
				filter();
			if (keyCommand.equals("q"))
				// if q is selected then end the loop
				userChoice = false;
		}
		// print out a thank you
		System.out.println("Thank you for using the show searcher app!");
	}

	/**
	 * filter method which will filter the shows to be searched for
	 */
	public void filter() {
		// user choice to true
		Boolean userChoice = true;
		while (userChoice) {
			// display the menu of choices and use the filter checker helper to help decided
			// if a provider gets an x or not
			System.out.println("Providers of shows to be included: ");
			System.out.println("1.) " + filterChecker("N") + " [N]etflix");
			System.out.println("2.) " + filterChecker("H") + " [H]ulu");
			System.out.println("3.) " + filterChecker("D") + " [D]isney+");
			System.out.println("4.) " + filterChecker("P") + " [P]rime Video");
			System.out.println("5.) [Q]uit");
			System.out.print("Select a command: ");
			// take input and set the input to upper case
			this.keyFilter = scanObj.nextLine();
			this.keyFilter = keyFilter.toUpperCase();
			// then send it through the filter tester and if it returns false, ask for new
			// input and rerun this method
			if (!filterTester(keyFilter)) {
				System.out.print("Enter a valid provider: ");
				filter();
			}
			// if the keyFilter does not equal Quit then use our backend object to toggle
			// the provider that was selected
			if (!keyFilter.equals("Q"))
				searcher.toggleProviderFilter(keyFilter);
			else
				// otherwise end loop
				userChoice = false;
		}
		// after all of that redisplay command menu
		displayCommandMenu();
	}

	/**
	 * helper method for filter, helps check what providers are currently selected
	 * 
	 * @returns String to be used
	 */
	private String filterChecker(String provider) {
		// automatically empty
		String checker = "___";
		// using our searcher object, check if the provider is wanted, then put an x,
		// otherwise dont
		if (searcher.getProviderFilter(provider))
			checker = "_X_";
		return checker;
	}

	/**
	 * helper method for filter, helps check the input for filter
	 * 
	 * @returns true if the command works
	 */
	private boolean filterTester(String filter) {
		// if it is null return false
		if (keyFilter.equals(null))
			return false;
		// if it is anything other than the valid providers, return false
		if (keyFilter.equals("N") || keyFilter.equals("H") || keyFilter.equals("D") || keyFilter.equals("P")
				|| keyFilter.equals("Q"))
			return true;
		// placeholder
		return false;
	}

	/**
	 * Displays the command menu
	 * 
	 */
	@Override
	public void displayCommandMenu() {
		// display menu
		System.out.println("Command Menu:\n");
		System.out.println("1.) Search By [T]itle Word");
		System.out.println("2.) Search By [Y]ear First Produced");
		System.out.println("3.) [F]ilter By Streaming Provider");
		System.out.println("4.) [Q]uit");
		System.out.print("Choose a command from the menu above: ");
		// read the input
		this.keyCommand = scanObj.nextLine();
		// set to lowercase
		this.keyCommand = keyCommand.toLowerCase();
		// check if it is valid, if not then redisplay it
		if (!commandTester(keyCommand)) {
			System.out.print("Thats not a valid command, please try again: ");
			displayCommandMenu();
		}
	}

	/**
	 * Command Helper method to check if the input is valid
	 * 
	 * @returns true if command is valid
	 */
	private boolean commandTester(String command) {
		// if null return false
		if (command.equals(null))
			return false;
		// if anything other than the commands return false
		if (command.equals("t") || command.equals("y") || command.equals("f") || command.equals("q"))
			return true;
		return false;
	}

	/**
	 * Displays filtered shows by taking in an array of filtered shows
	 * 
	 * @param List<IShow> shows filtered
	 */
	@Override
	public void displayShows(List<IShow> shows) {
		// print out how many shows were found in total
		System.out.println("Found " + shows.size() + "/" + searcher.getNumberOfShows() + " matches!");
		// loop through the list and get the title, year, rating, and then use the
		// displaysShowsHelper method to check if the provider is valid
		for (int x = 0; x < shows.size(); x++) {
			System.out.println(x + 1 + ".) " + shows.get(x).getTitle());
			System.out.println("\t" + shows.get(x).getRating() + "/100 " + shows.get(x).getYear() + " on: "
					+ displayShowsHelper(shows, x));
		}
	}

	/**
	 * Displays show helper method that will decide if the show is available on
	 * whichever platform
	 * 
	 * @param List<IShow> shows filtered
	 * @param Int         index of the show in list
	 * @returns a String of providers
	 */
	private String displayShowsHelper(List<IShow> shows, int index) {
		String providers = "";
		// essentially goes through the list and adds whihc proivders it has to the
		// array
		if (shows.get(index).isAvailableOn("Hulu"))
			providers += "Hulu ";
		if (shows.get(index).isAvailableOn("Netflix"))
			providers += "Netflix ";
		if (shows.get(index).isAvailableOn("Disney+"))
			providers += "Disney+ ";
		if (shows.get(index).isAvailableOn("Prime Video"))
			providers += "Prime Video ";
		return providers;

	}

	/**
	 * Searches based off of the title
	 */
	@Override
	public void titleSearch() {
		// ask for title
		System.out.print("Enter the keyword for your search: ");
		// take in and set to lowercase
		this.keyWord = scanObj.nextLine();
		this.keyWord = keyWord.toLowerCase();
		// display the shows with the method by sending it a filtered list of shows with
		// said title
		displayShows(searcher.searchByTitleWord(keyWord));
		// redisplay menu
		displayCommandMenu();
	}

	/**
	 * Searches based off of the year
	 */
	@Override
	public void yearSearch() {
		// ask for year
		System.out.print("Enter the year for your search: ");
		// if it is not an integer year, keep asking
		while (!scanObj.hasNextInt()) {
			System.out.print("Thats not a valid year, please try again: ");
			scanObj.next();
		}
		// take input
		this.keyYear = scanObj.nextInt();
		// display the shows with filtered list of shows with year
		displayShows(searcher.searchByYear(keyYear));
		// redisplay the menu
		displayCommandMenu();
	}
}
