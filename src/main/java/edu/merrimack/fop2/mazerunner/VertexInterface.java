package edu.merrimack.fop2.mazerunner;
//import edu.merrimack.fop2.adt.list.ListInterface;


/**
 * An interface that describes a graph vertex.
 */
public interface VertexInterface<T> {

    /**
     * Gets the vertex's label.
     *
     * @return the label associated with the vertex.
     */
    public T getLabel();

    /**
     * Connects this vertex to {@code end} with an edge.
     *
     * @param end the vertex to connect to.
     * @return true if the edge is added; otherwise, false.
     */
    public boolean connect(VertexInterface<T> end);

    /**
     * Connects this vertex to {@code end} with an edge of weight
     * {@code weight}.
     *
     * @param end the vertex to connect to.
     * @param weight the weight of the edge.
     * @return true if the edge is added; otherwise, false.
     */
    public boolean connect(VertexInterface<T> end, double weight);

    /**
     * Determines if this vertex has at least one neighbor.
     *
     * @return true if the this vertex has a neighbor; otherwise, false.
     */
    public boolean hasNeighbor();

    /**
     * Determines if there is an edge from this vertex to {@code end}.
     *
     * @param endL the label of the vertex we may be connected to.
     */
    public boolean hasEdge(T endL);

    /**
     * Marks the vertex as visited.
     */
    public void setVisited();

    /**
     * Marks the vertex as unvisited.
     */
    public void setUnvisited();

    /**
     * Determines if the node has been visited.
     *
     * @return true if the node has been visited; otherwise, false.
     */
    public boolean isVisited();

    /**
     * Gets a list of the neighbor vertices .
     *
     * @return a list representing the neighboring vertices.
     */
    public ListInterface<VertexInterface> getNeighbors();
}
