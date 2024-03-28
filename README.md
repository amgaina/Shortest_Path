# Shortest_Path and Distance Matrix Algorithm

## Description of the program
A java program that will determine the shortest path to a requested set of edges. Program uses dynamic
programming approach defined in Floyd’s Algorithm to solve this problem. The program will be
given the number of vertices, an adjacency matrix, a number of requested paths and a sequence
of vertex pairs that define the required terminal vertices of a path. This input will be given in a
text file(sample.txt) chosen at runtime. The input file will be selected using command line arguments not
hard coded or read from user responses. The program must also determine if no path exists
between the given vertex pairs. Output will be to both to standard output and to a file named
“out.txt.” The output file will contain the distance and path matrix determined by your program
with a single blank line between them. Note, the value of “-1” will be used in the adjacency
matrix for edges that have no adjacent edge. All edges will contain positive whole number
weights.

## File Description
FloydDriver.java : Program that has main method in it
                  that creates an object of Shortest_Path which will determine the shortest
                  path to a requested set of edges
Shortest_Path.java : Program that helps to determine the shortest path to a requested set of edges in the program.
sample.txt: Input text file having the vertices and the distance between them. 
out.txt: Output for the shortest path between the two vertices.

## How to Run
1. First, clone the respository in your terminal in the local device.
2. Second, after cloning it compile the (.java) file.
3. Then, inside your terminal, type java FloydDriver sample.txt
4. Output will appear to the terminal and out.txt file will be created for based on different inputs.

## Thank you.

