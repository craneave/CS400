
// --== CS400 File Header Information ==--
// Name: Avery Crane
// Email: adcrane@wisc.edu
// Team: DS
// TA: Ilay Raz
// Lecturer: Florian
// Notes to Grader: N/A
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;
import javax.xml.XMLConstants;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

/**
 * This class takes in an XML file and extracts all of the contents of each book
 * and creates new Books of each
 */
public class BookLoader implements IBookLoader {
	/**
	 * Returns a list of books from the xml file
	 * 
	 * @param String of the file path
	 * @returns List<IBook> of books from file
	 * @throws FileNotFoundException when the file is not found
	 */
	@Override
	public List<IBook> loadBooks(String filepath) throws FileNotFoundException {
		// Instantiate the Factory
		DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
		// create the list
		List<IBook> bookList = new ArrayList<IBook>();
		// a try for when running the xml parsing code
		try {

			// optional, but recommended
			// process XML securely, avoid attacks like XML External Entities (XXE)
			dbf.setFeature(XMLConstants.FEATURE_SECURE_PROCESSING, true);

			// parse XML file
			
			DocumentBuilder db = dbf.newDocumentBuilder();

			
			Document doc = db.parse(new File(filepath));

			// optional, but recommended
			// http://stackoverflow.com/questions/13786607/normalization-in-dom-parsing-with-java-how-does-it-work
			doc.getDocumentElement().normalize();

			// get <row>
			NodeList list = doc.getElementsByTagName("row");

			// loop through the file
			for (int temp = 0; temp < list.getLength(); temp++) {
				// new node at spot in file
				Node node = list.item(temp);

				// if the node tupe is equal to the element node
				if (node.getNodeType() == Node.ELEMENT_NODE) {

					// then set element to current node
					Element element = (Element) node;

					// set each attribute of the rows node to the correct variables
					String title = element.getElementsByTagName("Title").item(0).getTextContent();
					String author = element.getElementsByTagName("Author").item(0).getTextContent();
					String genre = element.getElementsByTagName("Genre").item(0).getTextContent();
					String lengthS = element.getElementsByTagName("Height").item(0).getTextContent();

					// parse the length to an int
					int length = Integer.parseInt(lengthS);

					// create a new book with new info
					Book book = new Book(title, length, author, genre);

					// add it to the list
					bookList.add(book);
				}
			}
		} catch (ParserConfigurationException | SAXException | IOException e) {
			// if any of these exceptions are thrown it is because the file was not found,
			// so throw it
			throw new FileNotFoundException("The file was not found");
		}
		// return the list
		return bookList;
	}

	public static void main(String args[]) {

		IBookLoader book = new BookLoader();
		try {
			System.out.println(book.loadBooks("books_new.xml").get(20).getAuthor());
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	

	}
}
