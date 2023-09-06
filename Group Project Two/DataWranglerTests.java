// --== CS400 File Header Information ==--
// Name: Avery Crane
// Email: adcrane@wisc.edu
// Team: DS
// TA: Ilay Raz
// Lecturer: Florian
// Notes to Grader: N/A

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import java.io.FileNotFoundException;
import java.util.List;

import org.junit.jupiter.api.Test;

/**
 * This class tests the functionality of the DataWrangler code
 */
public class DataWranglerTests {

	/**
	 * First JUnit test, test that given a good file path, the xml file will be read
	 * through
	 */
	@SuppressWarnings({ "rawtypes", "unchecked" })
	@Test
	public void JUnitTestOne() {
		// test that the class will at least run with correct code and that the root
		// node is not null
		BookLoader test = new BookLoader();
		try {
			assertEquals(true,
					!test.loadBooks("C:/School/CS 400/Project2DataWrangler/books_new.xml").get(0).equals(null),
					"The root was null meaning the list was not returned right");
		} catch (FileNotFoundException e) {
			System.out.println("FAILED #1, FILENOTFOUND EXCEPTION THROWN");
		}
	}

	/**
	 * Second JUnit test, test that given a bad file path it will throw an exception
	 * through
	 */
	@SuppressWarnings({ "rawtypes", "unchecked" })
	@Test
	public void JUnitTestTwo() {
		BookLoader test = new BookLoader();

		assertThrows(FileNotFoundException.class, () -> {
			test.loadBooks("Bad/input/ok.xml");
		}, "Exception was not thrown when supposed to");
	}

	/**
	 * Third JUnit test, test given good input that you can call any given attribute
	 * of a book without any exceptions
	 */
	@SuppressWarnings({ "rawtypes", "unchecked" })
	@Test
	public void JUnitTestThree() {
		// test with good input and can call on something without error, and that that
		// something called is correct
		BookLoader test = new BookLoader();
		try {
			assertEquals("Goswami, Jaideva",
					test.loadBooks("C:/School/CS 400/Project2DataWrangler/books_new.xml").get(0).getAuthor(),
					"Could not call a certain book");
		} catch (Exception e) {
			System.out.println("FAILED #3, EXCEPTION");
		}
	}

	/**
	 * Fourth JUnit test, check if the book attributes are correct for any given
	 * book
	 */
	@SuppressWarnings({ "rawtypes", "unchecked" })
	@Test
	public void JUnitTestFour() {
		// test all of a books attributes
		BookLoader test = new BookLoader();
// author
		try {
			assertEquals("Foreman, John",
					test.loadBooks("C:/School/CS 400/Project2DataWrangler/books_new.xml").get(1).getAuthor(),
					"Author couldnt be found correctly");
		} catch (Exception e) {
			System.out.println("FAILED #4, EXCEPTION");
		}
		// genre
		try {
			assertEquals("tech",
					test.loadBooks("C:/School/CS 400/Project2DataWrangler/books_new.xml").get(1).getGenre(),
					"Genre couldnt be found correctly");
		} catch (Exception e) {
			System.out.println("FAILED #4, EXCEPTION");
		}
		// length
		try {
			assertEquals(235, test.loadBooks("C:/School/CS 400/Project2DataWrangler/books_new.xml").get(1).getLength(),
					"Length couldnt be found correctly");
		} catch (Exception e) {
			System.out.println("FAILED #4, EXCEPTION");
		}
		// title
		try {
			assertEquals("Data Smart",
					test.loadBooks("C:/School/CS 400/Project2DataWrangler/books_new.xml").get(1).getTitle(),
					"Title couldnt be found correctly");
		} catch (Exception e) {
			System.out.println("FAILED #4, EXCEPTION");
		}
	}

	/**
	 * Fifth JUnit test, test that the array has all the books from file
	 */
	@SuppressWarnings({ "rawtypes", "unchecked" })
	@Test
	public void JUnitTestFive() {
		// test with good input and can call on something without error, and that that
		// something called is correct
		BookLoader test = new BookLoader();
		try {
			assertEquals(211, test.loadBooks("C:/School/CS 400/Project2DataWrangler/books_new.xml").size(),
					"Array did not contain all books");
		} catch (Exception e) {
			System.out.println("FAILED #5, EXCEPTION");
		}
	}

	/**
	 * Sixth JUnit test, test the compare to methods
	 */
	@SuppressWarnings({ "rawtypes", "unchecked" })
	@Test
	public void JUnitTestSix() {
		// test both compare to methods
		Book test = new Book("OK", 30, "HEY", "YES");
		Book test2 = new Book("NO", 30, "BOB", "YES");
		assertEquals(0, test.compareToLength(test2));
		assertEquals(6, test.compareTo(test2));

	}

	/**
	 * First JUnit second round test, test that the xml file can be found in the
	 * work space, and that it is correctly saving all 211 books
	 */
	@SuppressWarnings({ "rawtypes", "unchecked" })
	@Test
	public void JUnitSecondRoundTestTwo() {
		// test with good input and can call on something without error, and that that
		// something called is correct
		BookLoader test = new BookLoader();
		try {
			assertEquals(211, test.loadBooks("C:/School/CS 400/Project2DataWrangler/books_new.xml").size(),
					"Array did not contain all books");
		} catch (Exception e) {
			System.out.println("FAILED #5, EXCEPTION");
		}
	}

	/**
	 * Second JUnit second round test, test that the backend can use the list and
	 * find the correct number of books in it
	 */
	@SuppressWarnings({ "rawtypes", "unchecked" })
	@Test
	public void JUnitSecondRoundTestOne() {
		// test with good input and can call on something without error, and that that
		// something called is correct
		BookLoader test = new BookLoader();
		try {
			List<IBook> books = test.loadBooks("books_new.xml");
			BookSearcherBackend searcher = new BookSearcherBackend();
			searcher.addListToTree(books);
			assertEquals(211, searcher.getNumOfBooks());
		} catch (FileNotFoundException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
	}

	/**
	 * First Partner JUnit Test, test that the root is always black and new nodes
	 * are red
	 */
	@SuppressWarnings({ "rawtypes", "unchecked" })
	@Test
	public static void JUnitPartenerTestOne() {
		// test that the root is always black and a newly added node is always red
		RedBlackTree test = new RedBlackTree();
		test.insert(45);
		test.insert(30);
		assertEquals(1, test.root.blackHeight, "Root should be black or 1");
		assertEquals(0, test.root.leftChild.blackHeight, "New node should be red or 0");
	}

	/**
	 * Second partener test, test with multiple inserts and this should require a
	 * rotation with the RBT when 40 is input when aunt is black
	 */
	@SuppressWarnings({ "rawtypes", "unchecked" })
	@Test
	public static void JUnitPartenerTestTwo() {
		// test that the to level order is equal
		RedBlackTree test = new RedBlackTree();
		test.insert(45);
		test.insert(30);
		test.insert(40);
		assertEquals("[ 40, 30, 45 ]", test.toLevelOrderString(), "Level order is not correct");
		// now test the colors
		assertEquals(1, test.root.blackHeight, "Root should be black");
		assertEquals(0, test.root.leftChild.blackHeight, "End nodes should be red");
		assertEquals(0, test.root.rightChild.blackHeight, "End nodes should be red");
	}

}