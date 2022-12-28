package edu.merrimack.fop2.mazerunner;
//import edu.merrimack.fop2.adt.dictionary.LinkedDictionary;

/**
 * Implementation of a directed graph as an adjacency list.
 */
public class DirectedGraph<T extends Comparable<? super T>> implements GraphInterface<T> {

    private LinkedDictionary<T, Vertex<T>> vertices;
    private int edgeCount;

    /**
     * Construct a new empty digraph.
     */
    public DirectedGraph() {
        vertices = new LinkedDictionary<>();
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
            return vertices.add(label, new Vertex<T>(label)) == null;
        }
        return true;
    }

    /**
     * Retrieve the Vertex for the given label
     * 
     * @param label - key to look up the Vertex in the graph
     * @return Vertex
     */    
    public Vertex<T> getVertex(T label) {
        return vertices.getValue(label);        
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
        Vertex<T> beginV = vertices.getValue(begin);
        Vertex<T> endV = vertices.getValue(end);

        // If we can't find one of the end points, we can't have an edge.
        if (beginV == null || endV == null) {
            return false;
        }

        // Connect the edge.
        if (beginV.connect(endV, weight)) {
            edgeCount++;
            return true;
        }

        return false;
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
        return addEdge(begin, end, 0);
    }

    /**
     * Determines if there is an edge from {@code begin} to {@code end}.
     *
     * @return true if there is an edge from {@code begin} to {@code end};
     * otherwise, false.
     */
    @Override
    public boolean hasEdge(T begin, T end) {
        Vertex<T> beginV;

        if (vertices.isEmpty()) {
            return false;
        }

        beginV = vertices.getValue(begin);

        if (beginV != null) {
            return beginV.hasEdge(end);
        }
        return false;
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
    
    
    public void breadthFirstSearch(VertexInterface<T> v) throws EmptyQueueException{
        LinkedQueue<VertexInterface <T>> q = new LinkedQueue();
        
        
        //once at end, want to insert at the front of the list starting from the end of the dict
        //walk backwards from the end to the start 
        //enque v
        
        //path method v should be a linkedQueue
            //LinkedQueue<T> bfsQueue = new LinkedQueue<>() instead v
        //
        q.enqueue(v);
        //want to add dictionary, key is neighbir, value current point 
        
        //mark as visited 
        v.isVisited();
        
        //while q is not empty 
        while(q.isEmpty() == false){
            //take the first item off the que
            VertexInterface<T> w = q.dequeue();
            
            //find everything thats adjacent to that vertex that hasnt been visited and enque those
            //get neighbors returns a ListInterface
            ListInterface<VertexInterface> neighbors = w.getNeighbors();
            //insert every neighbir of v into que
            for (int i = 0; i<neighbors.getLength(); i++){
                //enque each neighbor into queue
                VertexInterface<T> u = neighbors.getEntry(i);
                if (u.isVisited() == false){
                    //enqueue it
                    
                    u.setVisited();
                    
                    q.enqueue(u);
                    
                    
                }
            }
                
            //return to the beginning of the while loop and start again with the next entry dequeued???
        }
    }
    
    public LinkedList<String> getPath(T start, T end) throws EmptyQueueException{
        LinkedList path = new LinkedList();
        LinkedDictionary<T, T> newDict = new LinkedDictionary();
        LinkedQueue<T> q = new LinkedQueue();
        
 
        //return the vertex based on the start and end label??
        Vertex startVert = vertices.getValue(start);
        Vertex endVert = vertices.getValue(end);
        //reference start/end label
        
        q.enqueue(start);
        //want to add dictionary each time, key is neighbor, value current point 
        //key =label for u 
        //v is u predecessor caused u to be added
            //^this mean the vertex we got the neighbors of is v?
        
        //mark as visited 
        startVert.isVisited();
        
        //while q is not empty 
        while(q.isEmpty() == false){
            //take the first item off the queue
            //this should be the startVert first iteration? then the first neighbors dequeued next iteration 
            T label = q.dequeue();
            //get the dequeued labels value so that we can get its neighbors???
            Vertex v = vertices.getValue(label);
            
            //Get the neighbors and enqueue the neighbors
            ListInterface<VertexInterface> neighbors = v.getNeighbors();
            //insert every neighbir of v into que
            for (int i = 0; i<neighbors.getLength(); i++){
                //enque each neighbor into queue
                VertexInterface<T> u = neighbors.getEntry(i);
                if (u.isVisited() == false){
                    //set is as visited
                     u.setVisited();
                     
                    //enqueue
                    q.enqueue(u.getLabel());
                    newDict.add(u.getLabel(), label);
                    
                    if (label.equals(end)){
                        //key == label; add the label to the path
                        //value == pred used to get next key
                        T currentLabel = end;
                        //while currentLabel does not equal start (we are going backwards).
                        while(!newDict.getValue(currentLabel).equals(start)){
                            //insert current label into path
                            path.insert(0, currentLabel);
                            //get the predecessor which is the value of currentLabel
                            T predecessor = newDict.getValue(currentLabel);
                            //Update current label, use the value as the next key
                            currentLabel = predecessor;
                            //update the currentLabel(key) here with the value of the end 
                            
                            
                        }
                        break;
                        //or clear the queue 
                    }
                }
            }
        }
        //hard code a path if cant ge this working. 
        
        //always put in at 0, will keep the right path
        //eachn entry is a label in dict
        //only if you find the end 
        
        //getvalue, passing in the key, getting the value, using that value as the next 
        //loop until label .equals start
        //get value for label, set that label to be the new key
        
        //if end is found:
            //use as key 
            
            //***otherwise have code under line 226 here. 
            
            

        return path;

    }

    /**
     * Remove all vertices and edges from the graph.
     */
    @Override
    public void clear() {
        vertices.clear();
        edgeCount = 0;
    }
}
