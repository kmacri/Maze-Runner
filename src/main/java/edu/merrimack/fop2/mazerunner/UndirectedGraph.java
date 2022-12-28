package edu.merrimack.fop2.mazerunner;
//import edu.merrimack.fop2.adt.dictionary.LinkedDictionary;

/**
 * Implementation of an undirected graph as an adjacency matrix.
 */
public class UndirectedGraph<T extends Comparable<? super T>> implements GraphInterface<T> {

    private static final int MAX_VERTICES = 1000;
    
    private LinkedDictionary<T, Integer> vertices;
    private double adjacencyMatrix[][];
    private int edgeCount;

    /**
     * Construct a new empty digraph.
     */
    public UndirectedGraph() {
        vertices = new LinkedDictionary<>();
        adjacencyMatrix = new double[MAX_VERTICES][MAX_VERTICES];
        edgeCount = 0;
    }

    /**
     * Adds a vertex with label {@code label} to the graph.
     *
     * @param label a unique label for the vertex in the graph.
     * @return true if the vertex is added; otherwise, false.
     */
    @Override
    public boolean addVertex(T label) {
        if (!vertices.contains(label)) {
            return vertices.add(label, getNumberOfVertices()) == null;
        }
        return true;
    }
    

    /**
     * Adds an edge from vertex {@code begin} to vertex {@code end} with weight
     * {@code weight}.
     *
     * @param begin the label of the origin vertex.
     * @param end the label of the destination vertex.
     * @param weight the weight of the edge.
     * @return true if the edge is added; otherwise, false.
     */
    @Override
    public boolean addEdge(T begin, T end, double weight) {
        Integer beginV = vertices.getValue(begin);
        Integer endV = vertices.getValue(end);

        // If we can't find one of the end points, we can't have an edge.
        if (beginV == null || endV == null) {
            return false;
        }
        adjacencyMatrix[beginV][endV] = weight;
        adjacencyMatrix[endV][beginV] = weight;
        edgeCount++;
        return true;
    }

    /**
     * Adds an edge from vertex {@code begin} to vertex {@code end} with weight
     * {@code weight}.
     *
     * @param begin the label of the origin vertex.
     * @param end the label of the destination vertex.
     * @return true if the edge is added; otherwise, false.
     */
    @Override
    public boolean addEdge(T begin, T end) {
        return addEdge(begin, end, 1);
    }

    /**
     * Determines if there is an edge from {@code begin} to {@code end}.
     *
     * @return true if there is an edge from {@code begin} to {@code end};
     * otherwise, false.
     */
    @Override
    public boolean hasEdge(T begin, T end) {

        if (vertices.isEmpty()) {
            return false;
        }

        Integer beginV = vertices.getValue(begin);
        Integer endV = vertices.getValue(end);

        if (beginV == null || endV == null) {
            return false;
        }
        double weight = adjacencyMatrix[beginV][endV];
        if (weight <= 0) {
            return false;
        }
        return true;
    }

    /**
     * Determines if the graph is empty.
     *
     * @return true if the graph is empty; otherwise, false.
     */
    @Override
    public boolean isEmpty() {
        return vertices.isEmpty();
    }

    /**
     * Get the number of vertices in the graph.
     *
     * @return the number of vertices in the graph (greater than or equal to
     * zero).
     */
    @Override
    public int getNumberOfVertices() {
        return vertices.getSize();
    }

    /**
     * Get the number of edges in the graph.
     *
     * @return the number of edges in the graph (greater than or equal to zero).
     */
    @Override
    public int getNumberOfEdges() {
        return edgeCount;
    }

    /**
     * Remove all vertices and edges from the graph.
     */
    @Override
    public void clear() {
        vertices.clear();
        edgeCount = 0;
        adjacencyMatrix = new double[MAX_VERTICES][MAX_VERTICES];
    }
}
