
// --== CS400 Project Three File Header ==--
// Name: Avery Crane
// CSL Username: crane
// Email: adcrane@wisc.edu
// Lecture #:  004 @4:00pm
// Notes to Grader: N/A
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThrows;

import java.util.NoSuchElementException;

import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

/**
 * Test the functionality of the AE code
 */
public class AlgorithmEngineerTests {

	private LengthGraph<String> graph;

	/**
	 * Instantiate graph from shortest path activity.
	 */
	@BeforeEach
	public void createGraph() {
		graph = new LengthGraph<>();
		// insert vertices A-F
		graph.insertVertex("A");
		graph.insertVertex("B");
		graph.insertVertex("C");
		graph.insertVertex("D");
		graph.insertVertex("E");
		graph.insertVertex("F");
		graph.insertVertex("G");
		// insert edges from Week 11. Shortest Path Activity
		graph.insertEdge("A", "B", 6);
		graph.insertEdge("A", "C", 2);
		graph.insertEdge("A", "D", 5);
		graph.insertEdge("B", "E", 1);
		graph.insertEdge("B", "C", 2);
		graph.insertEdge("C", "B", 3);
		graph.insertEdge("C", "F", 1);
		graph.insertEdge("D", "E", 3);
		graph.insertEdge("E", "A", 4);
		graph.insertEdge("F", "A", 1);
		graph.insertEdge("F", "D", 1);
		graph.insertEdge("G", "F", 9);
	}

	/**
	 * Checks shortest distance from B ---> F
	 */
	@Test
	public void testOne() {
		assertEquals(graph.dijkstrasShortestPath("B", "F").distance, 3);
	}

	/**
	 * Checks that a node that there is no distance throws exception
	 */
	@Test
	public void testTwo() {
		NoSuchElementException thrown = assertThrows(NoSuchElementException.class,
				() -> graph.dijkstrasShortestPath("A", "G"));
		assertTrue(thrown.getMessage().contains("No path was found with those start and end values"));
	}

	/**
	 * Checks that with null node throws exception
	 */
	@Test
	public void testThree() {
		NoSuchElementException thrown = assertThrows(NoSuchElementException.class,
				() -> graph.dijkstrasShortestPath("A", "R"));
		assertTrue(thrown.getMessage().contains("end value is not in the graph"));
	}

	/**
	 * Checks that the data sequence is correct
	 */
	@Test
	public void testFour() {
		assertTrue(graph.dijkstrasShortestPath("A", "E").dataSequence.toString().equals("[A, C, B, E]"));
	}

	/**
	 * Checks that the returnAll() method returns a list of sorted paths
	 */
	@Test
	public void testFive() {
		String s = "";
		graph.dijkstrasShortestPath("A", "E");
		for (int x = 0; x < graph.paths.size(); x++) {
			s = s + graph.paths.get(x).dataSequence;
		}
		assertTrue(s.equals("[A][A, C][A, C, F][A, C, F, D][A, C, B][A, C, B, E]"));
	}

	/**
	 * Checks the predecessor of E from path A to E
	 */
	@Test
	public void testSix() {
		assertTrue(graph.dijkstrasShortestPath("A", "E").dataSequence.get(2).equals("B"));
	}

	/**
	 * Checks the distance from A to E is 6
	 */
	@Test
	public void testSeven() {
		assertEquals(graph.dijkstrasShortestPath("A", "E").distance, 6);
	}
}
