
/**
 * Author: Abhishek Amgain
 * Filename: SoccerStatus.java
 * Program Description: This is a class named Shortest_Path that helps to determine the shortest path to a requested set of edges in the program.
 */

// File Import
import java.io.*;
import java.util.*;

//class named Shortest_Path
public class Shortest_Path {
    /** Instance variable named num_vertices that stores the number of vertices */
    private int num_vertices;
    /**
     * Instance variable named distance_matrix that stores the distance between
     * different vertex
     */
    private int[][] distance_matrix;
    /**
     * Instance variable named num_requests that stores the number of request for
     * the shortest distance between two given vertices
     */
    private int num_requests;
    /**
     * Instance variable named num_vertices that stores two vertices which is the
     * path request to go from one vertex to another
     */
    private int[][] path_requests;

    /**
     * Constructor named Shortest_Path that initializes the instance variables.
     * 
     * @param filename the name of the input file
     */
    public Shortest_Path(String filename) {
        // Taking file input
        File inputFile = new File(filename);
        try {
            // Creating the scanner class
            Scanner scanner = new Scanner(inputFile);

            // Reading the input and storing in the instance variable.
            String line1 = scanner.nextLine();
            num_vertices = Integer.parseInt(line1);
            distance_matrix = new int[num_vertices][num_vertices];

            // Using the for loop to read each line for the distance matrix
            for (int i = 0; i < num_vertices; i++) {
                String line = scanner.nextLine();
                String[] values = line.split(" ");
                // Using the for loop to read each value in each line for the distance matrix
                for (int j = 0; j < num_vertices; j++) {
                    distance_matrix[i][j] = Integer.parseInt(values[j]);
                }
            }

            // Reading the input and storing in the instance variable
            String line2 = scanner.nextLine();
            num_requests = Integer.parseInt(line2);
            path_requests = new int[num_requests][2];

            // Using the for loop to read each line for the path request matrix
            for (int k = 0; k < num_requests; k++) {
                String line4 = scanner.nextLine();
                String[] path_Vertices = line4.split(" ");
                // Using the for loop to read each value in each line for the path request
                // matrix
                path_requests[k][0] = Integer.parseInt(path_Vertices[0]);
                path_requests[k][1] = Integer.parseInt(path_Vertices[1]);
            }

            // Scanner close
            scanner.close();
        }
        // Handling Exception
        catch (FileNotFoundException e) {
            System.out.println(e.getMessage());
        }
    }

    /**
     * Method named floyd that calculates the shortest distance between any two
     * vertices
     * and writes the shortest matrix and the direction matrix to obtain the
     * shortest path in the file named "out.txt".
     * Then, it prints the shortest path between requested path vertices and its
     * direction in the console.
     *
     */
    public void floyd() {
        // Creating the direction_matrix that stores the direction for the shortest path
        // between any two vertices.
        int[][] direction_matrix = new int[num_vertices][num_vertices];
        // Creating the short_dis_matrix that stores the the shortest path between any
        // two vertices.
        int[][] short_dis_matrix = new int[num_vertices][num_vertices];

        // Using for nested for loop to initialize the short_dis_matrix with the value
        // from the distance matrix and direction_matrix to be zero.
        for (int i = 0; i < num_vertices; i++) {
            for (int j = 0; j < num_vertices; j++) {
                short_dis_matrix[i][j] = distance_matrix[i][j];
                direction_matrix[i][j] = 0;
            }
        }

        // Floyd's algorithm to calculate the shortest distance between any two vertices
        for (int k = 0; k < num_vertices; k++) {
            for (int i = 0; i < num_vertices; i++) {
                for (int j = 0; j < num_vertices; j++) {
                    // Only enters inside the statement if there is some finite path between vertex
                    // i to k and k to j.

                    if (short_dis_matrix[i][k] != -1 && short_dis_matrix[k][j] != -1) {
                        // Creating variable named val to store the value of the shortest distance
                        // between i and j till this time

                        int val = short_dis_matrix[i][j];
                        // If no path exist from vertex i to j
                        if (short_dis_matrix[i][j] == -1) {
                            short_dis_matrix[i][j] = short_dis_matrix[i][k] + short_dis_matrix[k][j];
                        } else {
                            short_dis_matrix[i][j] = minimum(short_dis_matrix[i][j],
                                    short_dis_matrix[i][k] + short_dis_matrix[k][j]);
                        }
                        // If value is changed from the previous loop then direction_matrix is now
                        // updated as it has new shortest distance
                        if (short_dis_matrix[i][j] != val) {
                            direction_matrix[i][j] = k + 1;
                        }

                    }
                }
            }
        }

        // Calling method that writes the short_dis_matrix and direction_matrix by
        // creating a new file
        writeInFile(short_dis_matrix, direction_matrix);
        // Calling method that prints the required information in the proper format
        printToConsole(short_dis_matrix, direction_matrix);
    }

    /**
     * Method named minimum that returns the minimum value between two numbers. It
     * returns num1 if they are equal.
     * 
     * @param num1 first number
     * @param num2 second number
     * @return the minimum value between num1 and num2
     */
    public int minimum(int num1, int num2) {
        // Returns the num1 if they are equal. Just to make this function more efficient
        if (num1 == num2) {
            return num1;
        } else {
            return Math.min(num1, num2);
        }
    }

    /**
     * Method named writeInFile that writes the shortest path matrix and the path
     * matrix in a new file in a desired format.
     * 
     * @param shortest_distance_matrix the shortest_distance matrix
     * @param direction_matrix         the direction matrix for the shortest path
     */
    public void writeInFile(int[][] shortest_distance_matrix, int[][] direction_matrix) {
        String filePath = "./out.txt";
        try {
            // FileWriter for creating the file
            FileWriter writeFile = new FileWriter(filePath);

            // Using the nested for loop to write the shortest_distance_matrix in the
            // created file
            for (int i = 0; i < num_vertices; i++) {
                for (int j = 0; j < num_vertices; j++) {
                    // Writing the content
                    writeFile.write(shortest_distance_matrix[i][j] + " ");
                }
                // New line after each line
                writeFile.write("\n");
            }
            writeFile.write("\n");

            // Using the nested for loop to write the direction_matrix in the created file
            for (int i = 0; i < num_vertices; i++) {
                for (int j = 0; j < num_vertices; j++) {
                    // Writing the content
                    writeFile.write(direction_matrix[i][j] + " ");
                }
                if (i != (num_vertices - 1)) {
                    // New line after each line
                    writeFile.write("\n");
                }
            }
            // Closing the FileWriter
            writeFile.close();
        }
        // Exception Handling
        catch (IOException e) {
            System.out.println("An error occurred while writing the file.");
        }
    }

    /**
     * Method named printToConsole that prints the direction for the shortest route
     * from one vertex to another in the console
     * 
     * @param short_dis_matrix the shortest distance matrix
     * @param direction_matrix the direction matrix for the shortest path
     */
    public void printToConsole(int[][] short_dis_matrix, int[][] direction_matrix) {
        // Create a variable i and initialize with the value of 1.
        int i = 1;
        // Using while loop till num_requests number of times
        while (i <= num_requests) {

            // Storing the start and end vertex
            int start = path_requests[i - 1][0];
            int dest = path_requests[i - 1][1];

            // Creating the list to store the list of the path
            List<Integer> list = new ArrayList<>();

            // Using first as a sentinel just to store whether it is the first loop or not
            // for the upcoming loop
            boolean first = true;

            while (start != 0) {
                // Taking the direction from the direction_matrix
                int direction = direction_matrix[start - 1][dest - 1];

                // If the direction is not zero
                if (direction != 0) {
                    // Add the start (initial direction) in the list
                    list.add(start);
                    // Updates the start to be the direction of this loop
                    start = direction;

                } else {
                    // If the shortest path between the start and destination is infinite and the
                    // loop is running for the first time.
                    if (short_dis_matrix[start - 1][dest - 1] == -1 && first) {
                        // No path
                        System.out.print("NO PATH EXISTS between " + start + " and " + dest);
                    } else if (first) {
                        // It means the direct path
                        System.out.print(start + ", " + dest);
                    } else {
                        // Adding the intermediate and the last vertex as this condition will run only
                        // if we reach the destination after number of intermediate vertices.
                        list.add(start);
                        list.add(dest);
                    }
                    break;
                }
                // First loop ends then, first = false
                first = false;
            }

            // If we have intermediate vertices
            if (list.size() > 2) {
                // Using the for loop to print the output in the desired format.
                for (int k = 0; k < list.size(); k++) {
                    if (k == list.size() - 1) {
                        System.out.print(list.get(k));
                    } else {
                        System.out.print(list.get(k) + ", ");
                    }
                }
            }
            System.out.println();
            // Increment i
            i++;
        }
    }

}
