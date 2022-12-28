package edu.merrimack.fop2.mazerunner;
/**
 * Represents an edge in a graph.
 */
public class Edge<T> {

    private VertexInterface<T> vertex;
    private double weight;

    /**
     * Sets the edge between this vertex and 
     * {@code end} with weight {@code weight}.
     *
     * @param end the other end of the edge.
     * @param weight the weight of the edge.
     */
    public Edge(VertexInterface<T> end, double weight) {
        this.vertex = end;
        this.weight = weight;
    }

    /**
     * Sets the edge between this vertex and end.
     *
     * @param end the other end of the edge.
     */
    public Edge(VertexInterface<T> end) {
        this(end, 0);
    }

    /**
     * Get the vertex associated with this edge.
     *
     * @return a vertex associated with the edge.
     */
    public VertexInterface<T> getEndVertex() {
        return vertex;
    }

    /**
     * Get the weight associated with the edge.
     *
     * @return the weight associated with the edge.
     */
    public double getWeight() {
        return weight;
    }
}
