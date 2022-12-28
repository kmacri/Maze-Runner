package edu.merrimack.fop2.mazerunner;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

/**
 *
 * @author Ed
 */
public class DirectedGraphTest {
    
    public DirectedGraphTest() {
    }

    @Test
    public void testGetPathNullStartGraph() throws EmptyQueueException  {
        String start = null;
        String end = "00";
        DirectedGraph graph = new DirectedGraph();
        // start is null so must return empty path
        LinkedList emptyPath = graph.getPath(start, end);
        assertTrue(emptyPath.isEmpty());
    }
    
    @Test
    public void testGetPathNullEndGraph() throws EmptyQueueException {
        String start = "00";
        String end = null;
        DirectedGraph graph = new DirectedGraph();
        // end is null so must return empty path
        LinkedList emptyPath = graph.getPath(start, end);
        assertTrue(emptyPath.isEmpty());
    }    
    
    @Test
    public void testGetPathEmptyGraph() throws EmptyQueueException {
        String start = "00";
        String end = "01";
        DirectedGraph graph = new DirectedGraph(); 
        // graph does not have any vertices so must be empty
        LinkedList emptyPath = graph.getPath(start, end);
        assertTrue(emptyPath.isEmpty());
    }     
    
    @Test
    public void testStartAndEndSame() throws EmptyQueueException  {
        String start = "00";
        String end = "00";
        DirectedGraph graph = new DirectedGraph(); 
        graph.addVertex("00");
        // start and end are same (this isn't technically possible with a real map
        // but you should handle this case
        LinkedList<String> path = graph.getPath(start, end);
        assertEquals("00", path.getEntry(0));
    }   
    
    @Test
    public void testSimplePathLeftToRight()throws EmptyQueueException  {
        String start = "00";
        String end = "30";
        DirectedGraph graph = new DirectedGraph(); 
        graph.addVertex("00");
        graph.addVertex("10");
        graph.addVertex("20");
        graph.addVertex("30");
        
        // directed graph so create edges in both directions
        graph.addEdge("00", "10");
        graph.addEdge("10", "00");
        
        // directed graph so create edges in both directions
        graph.addEdge("10", "20");
        graph.addEdge("20", "10");        
        
        // directed graph so create edges in both directions
        graph.addEdge("20", "30");
        graph.addEdge("30", "20");                

        LinkedList<String> path = graph.getPath(start, end);
        assertEquals("00", path.getEntry(0));
        assertEquals("10", path.getEntry(1));
        assertEquals("20", path.getEntry(2));
        assertEquals("30", path.getEntry(3));
    }    
    
    
    @Test
    public void testSimplePathRightToLeft() throws EmptyQueueException  {
        String start = "30";
        String end = "00";
        DirectedGraph graph = new DirectedGraph(); 
        graph.addVertex("00");
        graph.addVertex("10");
        graph.addVertex("20");
        graph.addVertex("30");
        
        // directed graph so create edges in both directions
        graph.addEdge("00", "10");
        graph.addEdge("10", "00");
        
        // directed graph so create edges in both directions
        graph.addEdge("10", "20");
        graph.addEdge("20", "10");        
        
        // directed graph so create edges in both directions
        graph.addEdge("20", "30");
        graph.addEdge("30", "20");                

        LinkedList<String> path = graph.getPath(start, end);
        assertEquals("30", path.getEntry(0));
        assertEquals("20", path.getEntry(1));
        assertEquals("10", path.getEntry(2));
        assertEquals("00", path.getEntry(3));
    }   

    @Test
    public void testSimplePathTopToBottom() throws EmptyQueueException  {
        String start = "00";
        String end = "03";
        DirectedGraph graph = new DirectedGraph(); 
        graph.addVertex("00");
        graph.addVertex("01");
        graph.addVertex("02");
        graph.addVertex("03");
        
        // directed graph so create edges in both directions
        graph.addEdge("00", "01");
        graph.addEdge("01", "00");
        
        // directed graph so create edges in both directions
        graph.addEdge("01", "02");
        graph.addEdge("02", "01");        
        
        // directed graph so create edges in both directions
        graph.addEdge("02", "03");
        graph.addEdge("03", "02");                

        LinkedList<String> path = graph.getPath(start, end);
        assertEquals("00", path.getEntry(0));
        assertEquals("01", path.getEntry(1));
        assertEquals("02", path.getEntry(2));
        assertEquals("03", path.getEntry(3));
    }  
    
    @Test
    public void testSimplePathBottomToTop() throws EmptyQueueException  {
        String start = "03";
        String end = "00";
        DirectedGraph graph = new DirectedGraph(); 
        graph.addVertex("00");
        graph.addVertex("01");
        graph.addVertex("02");
        graph.addVertex("03");
        
        // directed graph so create edges in both directions
        graph.addEdge("00", "01");
        graph.addEdge("01", "00");
        
        // directed graph so create edges in both directions
        graph.addEdge("01", "02");
        graph.addEdge("02", "01");        
        
        // directed graph so create edges in both directions
        graph.addEdge("02", "03");
        graph.addEdge("03", "02");                

        LinkedList<String> path = graph.getPath(start, end);
        assertEquals("03", path.getEntry(0));
        assertEquals("02", path.getEntry(1));
        assertEquals("01", path.getEntry(2));
        assertEquals("00", path.getEntry(3));
    }      
    
    @Test
    //  Tests a path that looks like:
    //
    //  S----
    //  -XXX-
    //  -XXXG
    //  -XXX-
    //  -----
    public void testTwoPossiblePaths() throws EmptyQueueException  {
        String start = "00";
        String end = "42";
        DirectedGraph graph = new DirectedGraph(); 
        graph.addVertex("00");
        graph.addVertex("10");
        graph.addVertex("20");
        graph.addVertex("30");
        graph.addVertex("40");
        graph.addVertex("41");
        graph.addVertex("42");
        
        graph.addVertex("01");
        graph.addVertex("02");
        graph.addVertex("03");
        graph.addVertex("04");
        graph.addVertex("14");
        graph.addVertex("24");
        graph.addVertex("34");
        graph.addVertex("44");
        graph.addVertex("43");
        
        // directed graph so create edges in both directions
        graph.addEdge("00", "01");
        graph.addEdge("01", "00");
        
        // directed graph so create edges in both directions
        graph.addEdge("01", "02");
        graph.addEdge("02", "01");        
        
        // directed graph so create edges in both directions
        graph.addEdge("02", "03");
        graph.addEdge("03", "02");                
        
        graph.addEdge("03", "04");
        graph.addEdge("04", "03");   
        
        graph.addEdge("00", "10");
        graph.addEdge("10", "00");
        
        graph.addEdge("10", "20");
        graph.addEdge("20", "10");        
        
        graph.addEdge("20", "30");
        graph.addEdge("30", "20");  

        graph.addEdge("30", "40");
        graph.addEdge("40", "30");  
        
        graph.addEdge("40", "41");
        graph.addEdge("41", "40");          
        
        graph.addEdge("41", "42");
        graph.addEdge("42", "41");          
        
        graph.addEdge("42", "43");
        graph.addEdge("43", "42");          

        graph.addEdge("43", "44");
        graph.addEdge("44", "43");
        
        graph.addEdge("34", "44");
        graph.addEdge("44", "34");        
        
        graph.addEdge("24", "34");
        graph.addEdge("34", "24");                
        
        graph.addEdge("14", "24");
        graph.addEdge("24", "14");                        
        
        graph.addEdge("04", "14");
        graph.addEdge("14", "04");                                

        LinkedList<String> path = graph.getPath(start, end);
        assertEquals("00", path.getEntry(0));
        assertEquals("10", path.getEntry(1));
        assertEquals("20", path.getEntry(2));
        assertEquals("30", path.getEntry(3));
        assertEquals("40", path.getEntry(4));
        assertEquals("41", path.getEntry(5));
        assertEquals("42", path.getEntry(6));
    }     
    
    
    @Test
    //  Tests a path that looks like:
    //
    //  S----
    //  -XXXX
    //  -XXXG
    //  -XXX-
    //  ----X
    public void testNoPaths() throws EmptyQueueException   {
        String start = "00";
        String end = "42";
        DirectedGraph graph = new DirectedGraph(); 
        graph.addVertex("00");
        graph.addVertex("10");
        graph.addVertex("20");
        graph.addVertex("30");
        graph.addVertex("40");
        graph.addVertex("42");
        
        graph.addVertex("01");
        graph.addVertex("02");
        graph.addVertex("03");
        graph.addVertex("04");
        graph.addVertex("14");
        graph.addVertex("24");
        graph.addVertex("34");
        graph.addVertex("43");
        
        // directed graph so create edges in both directions
        graph.addEdge("00", "01");
        graph.addEdge("01", "00");
        
        // directed graph so create edges in both directions
        graph.addEdge("01", "02");
        graph.addEdge("02", "01");        
        
        // directed graph so create edges in both directions
        graph.addEdge("02", "03");
        graph.addEdge("03", "02");                
        
        graph.addEdge("03", "04");
        graph.addEdge("04", "03");   
        
        graph.addEdge("00", "10");
        graph.addEdge("10", "00");
        
        graph.addEdge("10", "20");
        graph.addEdge("20", "10");        
        
        graph.addEdge("20", "30");
        graph.addEdge("30", "20");  

        graph.addEdge("30", "40");
        graph.addEdge("40", "30");                 
              
        graph.addEdge("42", "43");
        graph.addEdge("43", "42");                       
        
        graph.addEdge("24", "34");
        graph.addEdge("34", "24");                
        
        graph.addEdge("14", "24");
        graph.addEdge("24", "14");                        
        
        graph.addEdge("04", "14");
        graph.addEdge("14", "04");                                

        LinkedList<String> path = graph.getPath(start, end);
        assertTrue(path.isEmpty());
    }    
    
}
