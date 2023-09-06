
// --== CS400 File Header Information ==--
// Name: Avery Crane
// Email: adcrane@wisc.edu
// Team: DS
// TA: Ilay Raz
// Lecturer: Florian
// Notes to Grader: N/A
import java.util.List;
import java.io.FileNotFoundException;

/**
 * Instances of classes that implement this interface can be used to load a list
 * of books from a specified xml source file.
 */
public interface IBookLoader {
	/**
	 * This method loads the list of books described within a xml file.
	 * 
	 * @param filepath is relative to executable's working directory
	 * @return a list of book objects that were read from specified file
	 */
	List<IBook> loadBooks(String filepath) throws FileNotFoundException;
}