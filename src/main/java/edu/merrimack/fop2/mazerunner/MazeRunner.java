package edu.merrimack.fop2.mazerunner;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

/**
 *
 * @author Ed
 */
public class MazeRunner {
    
    public static void main(String args[]) {       
        Scanner scanner = new Scanner(System.in);
        int choice = 0;
        Maze newMaze = new Maze(10);
        do {
            System.out.println("What do you want to do?");
            System.out.println("1. Load a new maze");
            System.out.println("2. Solve the maze");
            System.out.println("3. End Program");
            choice = scanner.nextInt();
            
            //new maze obj
            
            
            switch (choice) {
                

                case 1:
                    // your code here
                    //load a new maze 
                    
                    //prompt user for file name 
                    Scanner fileScanner = new Scanner(System.in);
                    System.out.println("Enter a file name: ");
                    String currentFileName = fileScanner.nextLine();
                    
                    
                    
                    try{
                        //returns a linked list called lines, each node is a String(line) from the file 
                        ListInterface<String> file = readLinesFromFile(currentFileName);
                        
                        //create a maze obj
                        
                        
                        //for each line
                        //for each char
                        for (int i = 0; i<file.getLength(); i++){
                            for (int j = 0; j <file.getLength(); j++){
                                String line = file.getEntry(i);
                                char c = line.charAt(j);
                                
                                //set room of newMaze
                                newMaze.setMazeRoom(j, i, c);
                                
                            }
                        
                        }
                        //print newMaze
                        System.out.println(newMaze.toString());
                        /**try{
                            newMaze.findPath();
                        }catch (EmptyQueueException eq){
                            System.out.println("oops" + eq.getMessage());
                        }**/
                        
                        
                        //create graph
                        newMaze.createGraph();

                    }catch (FileNotFoundException fn){
                        System.out.println("oops" + fn.getMessage());
                    }
                    
                    
                    
                    break;
                case 2:
                    // your code here
                    //Solve the maze 
                    
                    System.out.println(newMaze.toString());
                    //Check that the maze is not empty
                    if (newMaze.isEmpty()){
                        System.out.println("Maze is empty");
                        break;
                    }
                    else{
                        try{
                            //key is null
                            newMaze.findPath();
                        }catch (EmptyQueueException eq){
                            System.out.println("oops" + eq.getMessage());
                        }
                        
                        
                        
                    }

                    break;
                case 3:
                    break;             
                default:
                    System.out.println("Invalid choice");
            }

        } while (choice != 3);

    }    
    
    public static ListInterface<String> readLinesFromFile(String fileName) throws FileNotFoundException {
        ListInterface<String> lines = new LinkedList<>();
        // create a File object based on the fileName provided
        File file = new File(fileName);

        Scanner scanner = new Scanner(file); // use the file instead of the keyboard for input
        // go through each line of the file
        int i = 0;
        while (scanner.hasNextLine()) {
            String line = scanner.nextLine();
            lines.insert(i++, line);
        }

        return lines;
    }    
    
}
