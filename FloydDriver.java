/**
 * Author: Abhishek Amgain
 * Filename: FloydDriver.java
 * Program Description: This is a FloydDriver program that has main method in it
 * that creates an object of Shortest_Path which will determine the shortest
 * path to a requested set of edges
 */

// Class named FloydDriver
public class FloydDriver {
    // Main Method
    public static void main(String[] args) {
        // Creating the object of the Shortest_Path
        Shortest_Path shortestPath = new Shortest_Path(args[0]);
        shortestPath.floyd();
    }
}
