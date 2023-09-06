
// --== CS400 Project Three File Header ==--
// Name: Avery Crane
// CSL Username: crane
// Email: adcrane@wisc.edu
// Lecture #:  004 @4:00pm
// Notes to Grader: N/A
import java.util.Hashtable;
import java.util.LinkedList;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.PriorityQueue;

public class LengthGraph<T> implements ILengthGraph<T> {

	/**
	 * Vertex objects group a data field with an adjacency list of weighted directed
	 * edges that lead away from them.
	 */
	protected class Vertex {
		public T data; // vertex label or application specific data
		public LinkedList<Edge> edgesLeaving;
		public boolean visited;

		public Vertex(T data) {
			this.visited = false;
			this.data = data;
			this.edgesLeaving = new LinkedList<>();
		}
	}

	/**
	 * Edge objects are stored within their source vertex, and group together their
	 * target destination vertex, along with an integer weight.
	 */
	protected class Edge {
		public Vertex target;
		public int weight;

		public Edge(Vertex target, int weight) {
			this.target = target;
			this.weight = weight;
		}
	}

	protected Hashtable<T, Vertex> vertices; // holds graph verticies, key=data

	public LengthGraph() {
		vertices = new Hashtable<>();
	}

	/**
	 * Insert a new vertex into the graph.
	 * 
	 * @param data the data item stored in the new vertex
	 * @return true if the data can be inserted as a new vertex, false if it is
	 *         already in the graph
	 * @throws NullPointerException if data is null
	 */
	@Override
	public boolean insertVertex(T data) {
		if (data == null)
			throw new NullPointerException("Cannot add null vertex");
		if (vertices.containsKey(data))
			return false; // duplicate values are not allowed
		vertices.put(data, new Vertex(data));
		return true;
	}

	/**
	 * Remove a vertex from the graph. Also removes all edges adjacent to the vertex
	 * from the graph (all edges that have the vertex as a source or a destination
	 * vertex).
	 * 
	 * @param data the data item stored in the vertex to remove
	 * @return true if a vertex with *data* has been removed, false if it was not in
	 *         the graph
	 * @throws NullPointerException if data is null
	 */
	@Override
	public boolean removeVertex(T data) {
		if (data == null)
			throw new NullPointerException("Cannot remove null vertex");
		Vertex removeVertex = vertices.get(data);
		if (removeVertex == null)
			return false; // vertex not found within graph
		// search all vertices for edges targeting removeVertex
		for (Vertex v : vertices.values()) {
			Edge removeEdge = null;
			for (Edge e : v.edgesLeaving)
				if (e.target == removeVertex)
					removeEdge = e;
			// and remove any such edges that are found
			if (removeEdge != null)
				v.edgesLeaving.remove(removeEdge);
		}
		// finally remove the vertex and all edges contained within it
		return vertices.remove(data) != null;
	}

	/**
	 * Insert a new directed edge with a positive edge weight into the graph.
	 * 
	 * @param source the data item contained in the source vertex for the edge
	 * @param target the data item contained in the target vertex for the edge
	 * @param weight the weight for the edge (has to be a positive integer)
	 * @return true if the edge could be inserted or its weight updated, false if
	 *         the edge with the same weight was already in the graph
	 * @throws IllegalArgumentException if either source or target or both are not
	 *                                  in the graph, or if its weight is < 0
	 * @throws NullPointerException     if either source or target or both are null
	 */
	@Override
	public boolean insertEdge(T source, T target, int weight) {
		if (source == null || target == null)
			throw new NullPointerException("Cannot add edge with null source or target");
		Vertex sourceVertex = this.vertices.get(source);
		Vertex targetVertex = this.vertices.get(target);
		if (sourceVertex == null || targetVertex == null)
			throw new IllegalArgumentException("Cannot add edge with vertices that do not exist");
		if (weight < 0)
			throw new IllegalArgumentException("Cannot add edge with negative weight");
		// handle cases where edge already exists between these verticies
		for (Edge e : sourceVertex.edgesLeaving)
			if (e.target == targetVertex) {
				if (e.weight == weight)
					return false; // edge already exists
				else
					e.weight = weight; // otherwise update weight of existing edge
				return true;
			}
		// otherwise add new edge to sourceVertex
		sourceVertex.edgesLeaving.add(new Edge(targetVertex, weight));
		return true;
	}

	/**
	 * Remove an edge from the graph.
	 * 
	 * @param source the data item contained in the source vertex for the edge
	 * @param target the data item contained in the target vertex for the edge
	 * @return true if the edge could be removed, false if it was not in the graph
	 * @throws IllegalArgumentException if either source or target or both are not
	 *                                  in the graph
	 * @throws NullPointerException     if either source or target or both are null
	 */
	@Override
	public boolean removeEdge(T source, T target) {
		if (source == null || target == null)
			throw new NullPointerException("Cannot remove edge with null source or target");
		Vertex sourceVertex = this.vertices.get(source);
		Vertex targetVertex = this.vertices.get(target);
		if (sourceVertex == null || targetVertex == null)
			throw new IllegalArgumentException("Cannot remove edge with vertices that do not exist");
		// find edge to remove
		Edge removeEdge = null;
		for (Edge e : sourceVertex.edgesLeaving)
			if (e.target == targetVertex)
				removeEdge = e;
		if (removeEdge != null) { // remove edge that is successfully found
			sourceVertex.edgesLeaving.remove(removeEdge);
			return true;
		}
		return false; // otherwise return false to indicate failure to find
	}

	/**
	 * Check if the graph contains a vertex with data item *data*.
	 * 
	 * @param data the data item to check for
	 * @return true if data item is stored in a vertex of the graph, false otherwise
	 * @throws NullPointerException if *data* is null
	 */
	@Override
	public boolean containsVertex(T data) {
		if (data == null)
			throw new NullPointerException("Cannot contain null data vertex");
		return vertices.containsKey(data);
	}

	/**
	 * Check if edge is in the graph.
	 * 
	 * @param source the data item contained in the source vertex for the edge
	 * @param target the data item contained in the target vertex for the edge
	 * @return true if the edge is in the graph, false if it is not in the graph
	 * @throws NullPointerException if either source or target or both are null
	 */
	@Override
	public boolean containsEdge(T source, T target) {
		if (source == null || target == null)
			throw new NullPointerException("Cannot contain edge adjacent to null data");
		Vertex sourceVertex = vertices.get(source);
		Vertex targetVertex = vertices.get(target);
		if (sourceVertex == null)
			return false;
		for (Edge e : sourceVertex.edgesLeaving)
			if (e.target == targetVertex)
				return true;
		return false;
	}

	/**
	 * Return the weight of an edge.
	 * 
	 * @param source the data item contained in the source vertex for the edge
	 * @param target the data item contained in the target vertex for the edge
	 * @return the weight of the edge (0 or positive integer)
	 * @throws IllegalArgumentException if either sourceVertex or targetVertex or
	 *                                  both are not in the graph
	 * @throws NullPointerException     if either sourceVertex or targetVertex or
	 *                                  both are null
	 * @throws NoSuchElementException   if edge is not in the graph
	 */
	@Override
	public int getWeight(T source, T target) {
		if (source == null || target == null)
			throw new NullPointerException("Cannot contain weighted edge adjacent to null data");
		Vertex sourceVertex = vertices.get(source);
		Vertex targetVertex = vertices.get(target);
		if (sourceVertex == null || targetVertex == null)
			throw new IllegalArgumentException("Cannot retrieve weight of edge between vertices that do not exist");
		for (Edge e : sourceVertex.edgesLeaving)
			if (e.target == targetVertex)
				return e.weight;
		throw new NoSuchElementException("No directed edge found between these vertices");
	}

	/**
	 * Return the number of edges in the graph.
	 * 
	 * @return the number of edges in the graph
	 */
	@Override
	public int getEdgeCount() {
		int edgeCount = 0;
		for (Vertex v : vertices.values())
			edgeCount += v.edgesLeaving.size();
		return edgeCount;
	}

	/**
	 * Return the number of vertices in the graph
	 * 
	 * @return the number of vertices in the graph
	 */
	@Override
	public int getVertexCount() {
		return vertices.size();
	}

	/**
	 * Check if the graph is empty (does not contain any vertices or edges).
	 * 
	 * @return true if the graph does not contain any vertices or edges, false
	 *         otherwise
	 */
	@Override
	public boolean isEmpty() {
		return vertices.size() == 0;
	}

	/**
	 * Path objects store a discovered path of vertices and the overal distance of
	 * cost of the weighted directed edges along this path. Path objects can be
	 * copied and extended to include new edges and verticies using the extend
	 * constructor. In comparison to a predecessor table which is sometimes used to
	 * implement Dijkstra's algorithm, this eliminates the need for tracing paths
	 * backwards from the destination vertex to the starting vertex at the end of
	 * the algorithm.
	 */
	protected class Path implements Comparable<Path> {
		public Vertex start; // first vertex within path
		public int distance; // sumed weight of all edges in path
		public List<T> dataSequence; // ordered sequence of data from vertices in path
		public Vertex end; // last vertex within path

		/**
		 * Creates a new path containing a single vertex. Since this vertex is both the
		 * start and end of the path, it's initial distance is zero.
		 * 
		 * @param start is the first vertex on this path
		 */
		public Path(Vertex start) {
			this.start = start;
			this.distance = 0;
			this.dataSequence = new LinkedList<>();
			this.dataSequence.add(start.data);
			this.end = start;
		}

		/**
		 * This extension constructor makes a copy of the path passed into it as an
		 * argument without affecting the original path object (copyPath). The path is
		 * then extended by the Edge object extendBy.
		 * 
		 * @param copyPath is the path that is being copied
		 * @param extendBy is the edge the copied path is extended by
		 */
		public Path(Path copyPath, Edge extendBy) {
			this.start = copyPath.start;
			this.distance = copyPath.distance + extendBy.weight;
			this.dataSequence = new LinkedList<>();
			for (int x = 0; x < copyPath.dataSequence.size(); x++) {
				this.dataSequence.add(copyPath.dataSequence.get(x));
			}
			if (!extendBy.target.data.equals(this.dataSequence.get(0)))
				this.dataSequence.add(extendBy.target.data);
			this.end = extendBy.target;
		}

		/**
		 * Allows the natural ordering of paths to be increasing with path distance.
		 * When path distance is equal, the string comparison of end vertex data is used
		 * to break ties.
		 * 
		 * @param other is the other path that is being compared to this one
		 * @return -1 when this path has a smaller distance than the other, +1 when this
		 *         path has a larger distance that the other, and the comparison of end
		 *         vertex data in string form when these distances are tied
		 */
		@Override
		public int compareTo(Path other) {
			int cmp = this.distance - other.distance;
			if (cmp != 0)
				return cmp; // use path distance as the natural ordering
			// when path distances are equal, break ties by comparing the string
			// representation of data in the end vertex of each path
			return this.end.data.toString().compareTo(other.end.data.toString());
		}
	}

	// list of sorted paths
	List<Path> paths = new LinkedList<>();

	/**
	 * Uses Dijkstra's shortest path algorithm to find and return the shortest path
	 * between two vertices in this graph: start and end. This path contains an
	 * ordered list of the data within each node on this path, and also the distance
	 * or cost of all edges that are a part of this path.
	 * 
	 * @param start data item within first node in path
	 * @param end   data item within last node in path
	 * @return the shortest path from start to end, as computed by Dijkstra's
	 *         algorithm
	 * @throws NoSuchElementException when no path from start to end can be found,
	 *                                including when no vertex containing start or
	 *                                end can be found
	 */
	protected Path dijkstrasShortestPath(T start, T end) throws NoSuchElementException {
		// make sure the start and end values are in the graph, if not then throw a
		// NoSuchElementException
		if (start == null)
			throw new NoSuchElementException("Null start");
		if (end == null)
			throw new NoSuchElementException("Null end");
		if (!vertices.containsKey(start))
			throw new NoSuchElementException("Your starting value is not in the graph");
		if (!vertices.containsKey(end))
			throw new NoSuchElementException("Your end value is not in the graph");
		// set up the variables, here I am using two priority queues, one that is the
		// actual priority queue the other is simply just to hold the newly removed path
		// so that it can be used when creating the new potential paths
		PriorityQueue<Path> pq = new PriorityQueue<Path>();
		PriorityQueue<Path> pqWithUsablePolls = new PriorityQueue<Path>();
		// here I set the inital path with the start value
		Path initialPath = new Path(vertices.get(start));
		// then I initialize the currentEndVertex that is being used to find new
		// pathways
		Vertex currentEndVertex = vertices.get(start);
		// adding the original path to the PQ
		pq.add(initialPath);
		// while the pq is empty, we will continue
		while (!pq.isEmpty()) {
			// if the PQ is empty, first thing we check is if the current elements end node
			// has been visited or not, and if it has been, we remove it from the PQ, as if
			// it has already been visited we already have the shortest path
			if (!pq.isEmpty()) {
				while (pq.element().end.visited) {
					pq.poll();
					// if we realize that all of nodes are visited, we can break the loop
					if (pq.size() == 0)
						break;
				}
			}
			// if the PQ is empty after that, then we can break the whole algorithm
			if (pq.isEmpty())
				break;
			// once we have a node that is not visited, we can set it to visited
			pq.element().end.visited = true;
			// add this path to our list of paths
			paths.add(pq.element());
			// set our currentEndVertex to the end vertex of the current path
			currentEndVertex = pq.element().end;
			// then we can remove that path from our real PQ and add it to our temp PQ so it
			// can be used in the for loop
			pqWithUsablePolls.add(pq.poll());
			// we loop through each possible edge from the currentEndVertex
			for (int x = 0; x < vertices.get(currentEndVertex.data).edgesLeaving.size(); x++) {
				// we make a new 'possible path' with our path from last iteration, and add the
				// possible edge paths
				Path possiblePaths = new Path(pqWithUsablePolls.element(),
						vertices.get(currentEndVertex.data).edgesLeaving.get(x));
				// add these paths to the PQ
				pq.add(possiblePaths);
			}
			// remove the old path from the temp PQ
			pqWithUsablePolls.poll();
		}
		// after the loop is complete we can look through our path list and for any path
		// that has a starting node equal to the given starting node, and an end node
		// equal to given end node, then we must know this is the path being searched
		// for so we can return it
		int index = -2;
		for (int x = 0; x < paths.size(); x++) {
			if (paths.get(x).end.data.equals(end) && paths.get(x).start.data.equals(start))
				index = x;
		}
		// if it is not found however, we throw an exception saying it was not found
		if (index > 0)
			return paths.get(index);
		if (index < 0) {
			throw new NoSuchElementException("No path was found with those start and end values");
		}
		return null;
	}

	/**
	 * Returns the shortest path between start and end. Uses Dijkstra's shortest
	 * path algorithm to find the shortest path.
	 * 
	 * @param start the data item in the starting vertex for the path
	 * @param end   the data item in the destination vertex for the path
	 * @return list of data item in vertices in order on the shortest path between
	 *         vertex with data item start and vertex with data item end, including
	 *         both start and end
	 * @throws NoSuchElementException when no path from start to end can be found
	 *                                including when no vertex containing start or
	 *                                end can be found
	 */
	@Override
	public List<T> shortestPath(T start, T end) {
		return dijkstrasShortestPath(start, end).dataSequence;
	}

	/**
	 * Returns the cost of the path (sum over edge weights) between start and end.
	 * Uses Dijkstra's shortest path algorithm to find the shortest path.
	 * 
	 * @param start the data item in the starting vertex for the path
	 * @param end   the data item in the end vertex for the path
	 * @return the cost of the shortest path between vertex with data item start and
	 *         vertex with data item end, including all edges between start and end
	 * @throws NoSuchElementException when no path from start to end can be found
	 *                                including when no vertex containing start or
	 *                                end can be found
	 */
	@Override
	public int getPathCost(T start, T end) {
		return dijkstrasShortestPath(start, end).distance;
	}

	/**
	 * Returns the list of paths sorted by shortest length
	 * 
	 * @return list of sorted paths
	 * @throws NoSuchElementException when no path from start to end can be found
	 */
	@Override
	public List<Path> returnAll() {
		return paths;
	}
}
