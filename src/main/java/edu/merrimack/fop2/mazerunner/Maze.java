/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package edu.merrimack.fop2.mazerunner;

/**
 *
 * @author kmacr
 */
public class Maze {

    //Attributes
    private char mazeRooms[][];
    private final DirectedGraph<String> mazeGraph = new DirectedGraph<>();
    private int size;
    private String startLabel;
    private String endLabel;
    public static final int D_SIZE = 10;

    //Constructor 
    public Maze(int size) {
        this.size = size;
        //create 2d char array with size parameter
        mazeRooms = new char[size][size];
    }
    
    public Maze(){
        mazeRooms = new char[D_SIZE][D_SIZE];
    }

    public void setStartLabel(String startLabel) {
        this.startLabel = startLabel;
    }

    public void setEndLabel(String endLabel) {
        this.endLabel = endLabel;
    }

    public String getStartLabel() {
        return startLabel;
    }

    public String getEndLabel() {
        return endLabel;
    }

    public DirectedGraph<String> getMazeGraph() {
        return mazeGraph;
    }

    public void setMazeRoom(int column, int row, char c) {
        //mazeRooms 2d char array
        //read the file, set each room using setMazeRoom 
        //set the room (col/row) to the given char
        mazeRooms[column][row] = c;

        //The value of the String for start or end should be the column and row concatenated
        if (c == 'S') {
            //startLabel = Integer.toString(column) + Integer.toString(row);
            startLabel = "" + column + "" + row;
        }
        if (c == 'G') {
            //endLabel = Integer.toString(column) + Integer.toString(row);
            endLabel = "" + column + "" + row;
            }
        

    }

    public char getMazeRoom(int column, int row) {
        //return char in maze room based on input parameters col/row
        return mazeRooms[column][row];
    }

    public boolean isEmpty() {
        return mazeGraph.isEmpty();
    }

    public void createGraph() {
        //i=col
        //j=row
        for (int i = 0; i < mazeRooms.length; i++) {
            for (int j = 0; j < mazeRooms.length - 1; j++) {

                //if two adjacent squares are not walls
                //vert
                if (mazeRooms[i][j] != 'X' && mazeRooms[i][j + 1] != 'X') {
                    //create two verticies and two edges, label is the row/col as string
                    //column moves one, row stays same 
                    String a = String.valueOf(i) + String.valueOf(j);
                    String b = String.valueOf(i) + String.valueOf(j + 1);
                    mazeGraph.addVertex(a);
                    mazeGraph.addVertex(b);
                    //mazeGraph.addVertex(String.valueOf(i)+String.valueOf(j));
                    //mazeGraph.addVertex(String.valueOf(i+1)+String.valueOf(j+1));
                    mazeGraph.addEdge(a, b);
                    mazeGraph.addEdge(b, a);

                }

                //want secodn set of for loops with i going to leg -1
                //reverse i and j logit 
                //top
                //check index out of bounds??
                /**
                 * else if (mazeRooms[i][j] != 'X' && mazeRooms[i-1][j] != 'X'){
                 * String a = String.valueOf(i)+String.valueOf(j); String b =
                 * String.valueOf(i-1)+String.valueOf(j);
                 * mazeGraph.addVertex(a); mazeGraph.addVertex(b);
                 * //mazeGraph.addVertex(String.valueOf(i)+String.valueOf(j));
                 * //mazeGraph.addVertex(String.valueOf(i+1)+String.valueOf(j+1));
                 * mazeGraph.addEdge(a, b); mazeGraph.addEdge(b, a);
                 *
                 * }
                 * //bottom else if (mazeRooms[i][j] != 'X' && mazeRooms[i+1][j]
                 * != 'X'){ String a = String.valueOf(i)+String.valueOf(j);
                 * String b = String.valueOf(i+1)+String.valueOf(j);
                 * mazeGraph.addVertex(a); mazeGraph.addVertex(b);
                 * //mazeGraph.addVertex(String.valueOf(i)+String.valueOf(j));
                 * //mazeGraph.addVertex(String.valueOf(i+1)+String.valueOf(j+1));
                 * mazeGraph.addEdge(a, b); mazeGraph.addEdge(b, a);
                 *
                 * }*
                 */
            }

        }
        for (int i = 0; i < mazeRooms.length - 1; i++) {
            for (int j = 0; j < mazeRooms.length; j++) {

                //if two adjacent squares are not walls
                //horizontal
                if (mazeRooms[i][j] != 'X' && mazeRooms[i + 1][j] != 'X') {
                    //create two verticies and two edges, label is the row/col as string
                    //column moves one, row stays same 
                    String a = String.valueOf(i) + String.valueOf(j);
                    String b = String.valueOf(i + 1) + String.valueOf(j);
                    mazeGraph.addVertex(a);
                    mazeGraph.addVertex(b);
                    //mazeGraph.addVertex(String.valueOf(i)+String.valueOf(j));
                    //mazeGraph.addVertex(String.valueOf(i+1)+String.valueOf(j+1));
                    mazeGraph.addEdge(a, b);
                    mazeGraph.addEdge(b, a);

                }

            }
        }
    }

    public void findPath() throws EmptyQueueException {
        //NEED TO FINISH GET PATH
        //LinkedList created from getPath finding path from start to end label 
        
        System.out.println("StartLabel" + " " + startLabel);
        System.out.println("EndLabel" + " " + endLabel);
        
        LinkedList<String> thisPath = mazeGraph.getPath(startLabel, endLabel);
        
        //see whats going wrong
        //for (int x = 0; x < thisPath.getLength(); x++)
        
        
        if (thisPath.getLength() == 0) {
            System.out.println("No path");
        }
        for (int i = 0; i < thisPath.getLength(); i++) {
            //returns the row/col as a string
            //series of row/col as string
            String entryAsString = thisPath.getEntry(i);  //going to reutnr a string form of row/col "12"
            //Need col as int
            int col = Integer.parseInt(entryAsString.substring(0, 1));
            //Need row as int
            int row = Integer.parseInt(entryAsString.substring(1));

            //change - to 0 to indicate that square is part of the path 
            if (mazeRooms[col][row] != 'S' && mazeRooms[col][row] != 'G') {
                //change from - to O
                mazeRooms[col][row] = 'O';

            }
            //Display updated maze with solution
            //this = instance of the class
            
        }
        System.out.println(this);

            //Display list of nodes from start to finish under the graph   
            //Display thisPath linkedList. 
            //go through path 1 at time, print out, format with arrows. 
            for (int j = 0; j < thisPath.getLength(); j ++){
                System.out.print(thisPath.getEntry(j) + "-->" );
                
            }
            System.out.println("");

    }

    @Override
    public String toString() {

        String mazeString = "+---+---+---+---+---+---+---+---+---+---+---+\n" + "|   ";

        //columns
        for (int j = 0; j < 10; j++) {
            mazeString += "| " + j + " ";
        }

        //rows
        for (int i = 0; i < 10; i++) {
            mazeString += "\n| " + i + " |";
            for (int k = 0; k < 10; k++) {
                mazeString += " " + mazeRooms[k][i] + " |";

            }
        }

        return mazeString;

    }

}
