package edu.merrimack.fop2.mazerunner;
/**
 * The basic interface for a graph.
 */
public interface GraphInterface<T> {

    /**
     * Adds a vertex with label {@code label} to the graph.
     *
     * @param label a unique label for the vertex in the graph.
     * @return true if the vertex is added; otherwise, false.
     */
    public boolean addVertex(T label);
    
    /**
     * Returns the vertex with label {@code label} from the graph.
     *
     * @param label a unique label for the vertex in the graph.
     * @return true if the vertex is added; otherwise, false.
     */
    //public Vertex<T> getVertex(T label);    

    /**
     * Adds an edge from vertex {@code begin} to vertex {@code end} with weight
     * {@code weight}.
     *
     * @param begin the label of the origin vertex.
     * @param end the label of the destination vertex.
     * @param weight the weight of the edge.
     * @return true if the edge is added; otherwise, false.
     */
    public boolean addEdge(T begin, T end, double weight);

    /**
     * Adds an edge from vertex {@code begin} to vertex {@code end} with weight
     * {@code weight}.
     *
     * @param begin the label of the origin vertex.
     * @param end the label of the destination vertex.
     * @return true if the edge is added; otherwise, false.
     */
    public boolean addEdge(T begin, T end);

    /**
     * Determines if there is an edge from {@code begin} to {@code end}.
     *
     * @param begin - starting vertex of edge
     * @param end - ending vertex of edge
     * @return true if there is an edge from {@code begin} to {@code end};
     * otherwise, false.
     */
    public boolean hasEdge(T begin, T end);

    /**
     * Determines if the graph is empty.
     *
     * @return true if the graph is empty; otherwise, false.
     */
    public boolean isEmpty();

    /**
     * Get the number of vertices in the graph.
     *
     * @return the number of vertices in the graph (greater than or equal to
     * zero).
     */
    public int getNumberOfVertices();

    /**
     * Get the number of edges in the graph.
     *
     * @return the number of edges in the graph (greater than or equal to zero).
     */
    public int getNumberOfEdges();

    /**
     * Remove all vertices and edges from the graph.
     */
    public void clear();
}
