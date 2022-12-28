package edu.merrimack.fop2.mazerunner;
//import edu.merrimack.fop2.adt.Node;
//import edu.merrimack.fop2.adt.list.ListInterface;
//import edu.merrimack.fop2.adt.list.LinkedList;

/**
 * A simple vertex.
 */
public class Vertex<T> implements VertexInterface<T> {

    private T label;
    private boolean visited;
    private Node<Edge<T>> edgeList;
    private int numEdges;

    /**
     * Creates a new vertex with label {@code label}.
     * 
     * @param label - an object that can be used ti uniquely identify this vertex
     */
    public Vertex(T label) {
        this.label = label;
        edgeList = null;
        visited = false;
        numEdges = 0;
    }

    /**
     * Gets the vertex's label.
     *
     * @return the label associated with the vertex.
     */
    @Override    
    public T getLabel() {
        return this.label;
    }

    /**
     * Connects this vertex to {@code end} with an edge.
     *
     * @param end the vertex to connect to.
     * @return true if the edge is added; otherwise, false.
     */
    @Override    
    public boolean connect(VertexInterface<T> end) {
        return connect(end, 0);
    }

    /**
     * Connects this vertex to {@code end} with an edge of weight
     * {@code weight}.
     *
     * @param end the vertex to connect to.
     * @param weight the weight of the edge.
     * @return true if the edge is added; otherwise, false.
     */
    @Override    
    public boolean connect(VertexInterface<T> end, double weight) {
        // Walk the edge chain checking to see if the vertex is
        // in the list. If it is, return false; otherwise, add a new edge
        // to the list.
        for (Node<Edge<T>> visitor = edgeList; visitor != null; visitor = visitor.getNext()) {
            if (visitor.getItem().getEndVertex().equals(end)) {
                return false;
            }
        }

        // No such edge was found, add it.
        Node<Edge<T>> newEdge = new Node<Edge<T>>(new Edge<>(end, weight), edgeList);
        edgeList = newEdge;

        // Increment the number of edges.
        numEdges++;

        return true;

    }

    /**
     * Determines if this vertex has at least one neighbor.
     *
     * @return true if the this vertex has a neighbor; otherwise, false.
     */
    @Override    
    public boolean hasNeighbor() {
        return edgeList != null;
    }

    /**
     * Determines if there is an edge from this vertex to {@code end}.
     *
     * @param endLabel the label of the vertex we may be connected to.
     */
    @Override    
    public boolean hasEdge(T endLabel) {
        for (Node<Edge<T>> walker = edgeList; walker != null; walker = walker.getNext()) {
            if (walker.getItem().getEndVertex().getLabel().equals(endLabel)) {
                return true;
            }
        }
        return false;
    }

    /**
     * Determine if two vertices are equal.
     *
     * @return true if the vertices are equal; otherwise, false.
     */
    @Override
    public boolean equals(Object obj) {
        if (obj != null && (getClass() == obj.getClass())) {
            @SuppressWarnings("unchecked")
            Vertex<T> other = (Vertex<T>) obj;
            return this.label.equals(other.getLabel());
        }
        return false;
    }

    /**
     * Marks the vertex as visited.
     */
    @Override    
    public void setVisited() {
        this.visited = true;
    }

    /**
     * Marks the vertex as unvisited.
     */
    @Override
    public void setUnvisited() {
        this.visited = false;
    }

    /**
     * Determines if the node has been visited.
     *
     * @return true if the node has been visited; otherwise, false.
     */
    @Override
    public boolean isVisited() {
        return this.visited;
    }

    
    /**
     * Gets a list of the neighbor vertices.
     *
     * @return a list representing the neighboring vertices.
     */  
    @Override    
    public ListInterface<VertexInterface> getNeighbors() {
        ListInterface<VertexInterface> neighbors = new LinkedList<>();
        for (Node<Edge<T>> visitor = edgeList; visitor != null; visitor = visitor.getNext()) {
           neighbors.insert(0, visitor.getItem().getEndVertex());
        }

        return neighbors;
    }     
}
