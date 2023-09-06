
// --== CS400 File Header Information ==--
// Name: Avery Crane
// Email: adcrane@wisc.edu
// Team: DS
// TA: Ilay Raz
// Lecturer: Florian
// Notes to Grader: N/A

/**
 * This class creates a book object using the IBook interface
 */
public class Book implements IBook {
	// variables
	private String title;
	private int length;
	private String author;
	private String genre;

	/**
	 * Constructor for a book, all books have a title, author, genre, and some have
	 * a length, so this is the constructor for when there is a length
	 */
	public Book(String iTitle, int iLength, String iAuthor, String iGenre) {
		this.title = iTitle;
		this.length = iLength;
		this.author = iAuthor;
		this.genre = iGenre;
	}

	/**
	 * Returns the title for the book
	 * 
	 * @returns String of the title
	 */
	@Override
	public String getTitle() {
		return title;
	}

	/**
	 * Returns the length of the book, length of 0 means no length
	 * 
	 * @returns int length of book
	 */
	@Override
	public int getLength() {
		return length;
	}

	/**
	 * Returns the author for the book
	 * 
	 * @returns String of the author
	 */
	@Override
	public String getAuthor() {
		return author;
	}

	/**
	 * Returns the genre for the book, either "fiction", "nonfiction", "tech",
	 * "science", "philosophy"
	 * 
	 * @returns String of the genre
	 */
	@Override
	public String getGenre() {
		return genre;
	}

	/**
	 * Compares two authors of books
	 * 
	 * @returns int of comparison
	 * @param IBook to be compared
	 */
	@Override
	public int compareTo(IBook o) {
		return this.author.compareTo(o.getAuthor());
	}

	/**
	 * Compares two lengths of books
	 * 
	 * @returns int of comparison, 0 is equal, 1 this is greater, -1 other is
	 *          greater
	 * @param IBook to be compared
	 */
	@Override
	public int compareToLength(IBook o) {
		if (this.length > o.getLength())
			return 1;
		else if (this.length < o.getLength())
			return -1;
		else
			return 0;
	}
}