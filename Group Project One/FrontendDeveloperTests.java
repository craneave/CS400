import java.awt.List;

// --== CS400 Project One File Header ==--
// Name: Avery Crane
// CSL Username: crane
// Email: adcrane@wisc.edu
// Lecture #:  004 @4:00pm
// Notes to Grader: N/A
/**
 * Tester class for the frontend development of this project
 */
public class FrontendDeveloperTests {

	/**
	 * Tests the functionality of commandLoop() NOTE: this is NOT meant to test the
	 * Functionality of the methods WITHIN the loop, but rather to only test that if
	 * a certain command is pressed, then the appropriate outcome occurs, and to
	 * also check that the loop will run infinitely until the user uses the quit
	 * command
	 * 
	 * @return true if tests pass
	 */
	public static boolean commandLoopTester() {
		// Test Command loop and with valid commands will make the next UI
		// appear
		// create our tester objects
		IShowSearcherBackend backTest = new ShowSearcherBackendPH();
		ShowSearcherFrontend test = new ShowSearcherFrontend(backTest);

		// Create a new TextUITester object with input T and sponge
		TextUITester tester = new TextUITester("T\nsponge\nq");

		// run command loop
		test.runCommandLoop();

		// we check if it starts with the show searcher app welcome for starting the
		// command loop, then we check if it contains the string which shows the command
		// menu, then with input t, the testerSearch() strings should show up asking us
		// for our keyword, then we check if the shows actually came up by checking for
		// sponge, then lastly we check the the command loop ran again by seeing if the
		// menu came up again, along with the end prompt for quitting
		String output = tester.checkOutput();
		if (output.startsWith("Welcome to the Show Searcher App!") && output.contains("Command Menu:\n")
				&& output.contains("Enter the keyword for your search: ") && output.contains("sponge")
				&& output.contains("Choose a command from the menu above: ")
				&& output.contains("Thank you for using the show searcher app!"))
			System.out.println("Test passed.");
		else {
			// if not then failed
			System.out.println("Test FAILED.");
			return false;
		}
		return true;
	}

	/**
	 * Tests the titleSearch() method, this will test if the outputs are appropriate
	 * to what is entered
	 * 
	 * @return true if tests pass
	 */
	public static boolean titleSearchTester() {
		// test the titelSearch method start with creating the objects
		IShowSearcherBackend backTest = new ShowSearcherBackendPH();
		ShowSearcherFrontend test = new ShowSearcherFrontend(backTest);

		// Create a new TextUITester object with input T and sponge
		TextUITester tester = new TextUITester("t\nsponge\nq\nq");
		// run the method
		test.runCommandLoop();
		// test with regular input to make sure the shows show up with the right names
		String output = tester.checkOutput();
		if (output.contains("Enter the keyword for your search: ") && output.contains("sponge"))
			System.out.println("Test passed.");
		else {
			// if not then failed
			System.out.println("Test FAILED.");
			return false;
		}
		return true;
	}

	/**
	 * Tests the yearSearch() method, this will test if the outputs are appropriate
	 * to what is entered
	 * 
	 * @return true if tests pass
	 */
	public static boolean yearSearchTester() {
		IShowSearcherBackend backTest = new ShowSearcherBackendPH();
		ShowSearcherFrontend test = new ShowSearcherFrontend(backTest);

		// Create a new TextUITester object with input y and year
		TextUITester tester = new TextUITester("y\n2003\nq\nq");
		// run the method
		test.runCommandLoop();
		// test with regular input to make sure the shows show up with the right year
		String output = tester.checkOutput();
		if (output.contains("Enter the year for your search: ") && output.contains("2003"))
			System.out.println("Test passed.");
		else {
			// if not then failed
			System.out.println("Test FAILED.");
			return false;
		}

		// try again but put something other than a integer

		// Create a new TextUITester object with y, then a bad input
		TextUITester tester2 = new TextUITester("y\nhello\n2003\nq");
		// run the method
		test.runCommandLoop();
		String output2 = tester2.checkOutput();
		// should say bad input then should re-ask, then reenter valid argument
		if (output2.contains("Thats not a valid year, please try again: ") && output2.contains("2003"))
			System.out.println("Test passed.");
		else {
			// if not then failed
			System.out.println("Test FAILED.");
			return false;
		}
		return true;
	}

	/**
	 * Tests the filterTester() method, this will test if the outputs are
	 * appropriate to what is entered
	 * 
	 * @return true if tests pass
	 */
	public static boolean filterTester() {
		IShowSearcherBackend backTest = new ShowSearcherBackendPH();
		ShowSearcherFrontend test = new ShowSearcherFrontend(backTest);

		// Create a new TextUITester object with input f then unfilter netflix, then
		// unfilter hulu, and refilter, then quit
		TextUITester tester = new TextUITester("f\nn\nh\nh\nq\nq");
		// run the method
		test.runCommandLoop();
		// test with regular input to make sure the right texts show up
		String output = tester.checkOutput();
		// make sure correct text appears, and then also check that the netflix mark is
		// now blank, which tests out format and our checking format
		if (output.contains("Providers of shows to be included:") && output.contains("[N]etflix")
				&& output.contains("___") && output.contains("_X_"))
			System.out.println("Test passed.");
		else {
			// if not then failed
			System.out.println("Test FAILED.");
			return false;
		}

		// try a random letter, should reshow filter options and then quit
		TextUITester tester2 = new TextUITester("f\nK\nq\nq\nq");
		// run the method
		test.runCommandLoop();
		String output2 = tester2.checkOutput();
		// should show that it was invalid input and then reprint the list
		if (output2.contains("Providers of shows to be included:") && output2.contains("Enter a valid provider: ")
				&& output2.contains("_X_"))
			System.out.println("Test passed.");
		else {
			// if not then failed
			System.out.println("Test FAILED.");
			return false;
		}
		return true;
	}

	/**
	 * Tests the displayCommandMenu() method, this will test if the outputs are
	 * appropriate to what is entered
	 * 
	 * @return true if tests pass
	 */
	public static boolean displayCommandMenuTester() {
		IShowSearcherBackend backTest = new ShowSearcherBackendPH();
		ShowSearcherFrontend test = new ShowSearcherFrontend(backTest);

		// Create a new TextUITester object with input
		TextUITester tester = new TextUITester("\nq");
		// run the method
		test.runCommandLoop();
		// test with regular input to make sure the menu shows
		String output = tester.checkOutput();
		// make sure the title menu appears, dont have to check all lines because if one
		// appears it can be assumed all do
		if (output.contains("Command Menu:\n") && output.contains("1.) Search By [T]itle Word"))
			System.out.println("Test passed.");
		else {
			// if not then failed
			System.out.println("Test FAILED.");
			return false;
		}

		// try again but put something thats not a command

		// Create a new TextUITester object with y, then a bad input
		TextUITester tester2 = new TextUITester("R\nq\nq");
		// run the method
		test.runCommandLoop();
		String output2 = tester2.checkOutput();
		// should say bad input then should re-ask
		if (output2.contains("Thats not a valid command, please try again: ") && output2.contains("Command Menu:\n"))
			System.out.println("Test passed.");
		else {
			// if not then failed
			System.out.println("Test FAILED.");
			return false;
		}
		return true;
	}

	/**
	 * Tests the conjunction of frontenddeveloper code and backenddeveloper code
	 * 
	 * @return true if tests pass
	 */
	public static boolean backendConjunctionTest() {
		IShowSearcherBackend backTest = new ShowSearcherBackend();
		ShowSearcherFrontend test = new ShowSearcherFrontend(backTest);

		// Create a new TextUITester object with input f then unfilter netflix
		TextUITester tester = new TextUITester("f\nn\nq\nq");
		// run the method
		test.runCommandLoop();
		String output = tester.checkOutput();
		// if ShowSearcherBackend is working properly with this class, then netflix
		// should be unchecked
		if (output.contains("Providers of shows to be included:") && output.contains("1.) ___ [N]etflix"))
			System.out.println("Test passed.");
		else {
			// if not then failed
			System.out.println("Test FAILED.");
			return false;
		}
		return true;
	}

	/**
	 * Tests the conjunction of frontenddeveloper code and show.java code
	 * 
	 * @return true if tests pass
	 */
	public static boolean showConjuctionTest() {
		// this is going test if the show functions work within this code by testing the
		// search by title function because it uses show.java methods
		IShowSearcherBackend backTest = new ShowSearcherBackendPH();
		ShowSearcherFrontend test = new ShowSearcherFrontend(backTest);

		
		// Create a new TextUITester object with input T and 'Black Mirror' for title
		TextUITester tester = new TextUITester("t\nBlack Mirror\nq\nq");
		// run the method
		test.runCommandLoop();
		// by searching for a specific title, we can see if Show.java gives us the
		// correct rating, year, title, and providers
		String output = tester.checkOutput();
		System.out.println(output);
		if (output.contains("Enter the keyword for your search: ") && output.contains("BlackMirror")
				&& output.contains("2011") && output.contains("92") && output.contains("Netflix"))
			System.out.println("Test passed.");
		else {
			// if not then failed
			System.out.println("Test FAILED.");
			return false;
		}
		return true;
	}

	/**
	 * Main method which prints out all of the results
	 * 
	 */
	public static void main(String[] args) {
		System.out.println(showConjuctionTest());
	}
}
