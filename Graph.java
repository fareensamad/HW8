
/******************************************************************
 *
 *   NAME: Fareen Samad       SECION: COMP 272-001
 *
 *   Note, additional comments provided throughout this source code
 *   is for educational purposes
 *
 ********************************************************************/

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

/**
 * Graph traversal exercise
 *
 * The Graph class is a representing an oversimplified Directed Graph of
 * vertices
 * (nodes) and edges. The graph is stored in an adjacency list
 */

public class Graph {
  int numVertices; // vertices in graph
  LinkedList<Integer>[] adjListArr; // Adjacency list
  List<Integer> vertexValues; // vertex values

  // Constructor
  public Graph(int numV) {
    numVertices = numV;
    adjListArr = new LinkedList[numVertices];
    vertexValues = new ArrayList<>(numVertices);

    for (int i = 0; i < numVertices; i++) {
      adjListArr[i] = new LinkedList<>();
      vertexValues.add(0);
    }
  }

  /*
   * method setValue
   * 
   * Sets a vertex's (node's) value.
   */

  public void setValue(int vertexIndex, int value) {
    if (vertexIndex >= 0 && vertexIndex < numVertices) {
      vertexValues.set(vertexIndex, value);
    } else {
      throw new IllegalArgumentException(
          "Invalid vertex index: " + vertexIndex);
    }
  }

  public void addEdge(int src, int dest) {
    adjListArr[src].add(dest);
  }

  /*
   * method printGraph
   * 
   * Prints the graph as an adjacency matrix
   */

  public void printGraph() {
    System.out.println(
        "\nAdjacency Matrix Representation:\n");
    int[][] matrix = new int[numVertices][numVertices];

    for (int i = 0; i < numVertices; i++) {
      for (Integer dest : adjListArr[i]) {
        matrix[i][dest] = 1;
      }
    }

    System.out.print("  ");
    for (int i = 0; i < numVertices; i++) {
      System.out.print(i + " ");
    }
    System.out.println();

    for (int i = 0; i < numVertices; i++) {
      System.out.print(i + " ");
      for (int j = 0; j < numVertices; j++) {
        if (matrix[i][j] == 1) {
          System.out.print("| ");
        } else {
          System.out.print(". ");
        }
      }
      System.out.println();
    }
  }

  /**
   * method findRoot
   *
   * This method returns the value of the root vertex, where root is defined in
   * this case as a node that has no incoming edges. If no root vertex is found
   * and/or more than one root vertex, then return -1.
   * 
   */

  public int findRoot() {

    // Create an array to track the number of incoming edges for each vertex
    int[] rootVertex = new int[numVertices];

    // Loop through each vertex in the graph
    for (int i = 0; i < numVertices; i++) {
      // Loop through the adjacency list of the current vertex
      for (int j = 0; j < adjListArr[i].size(); j++) {
        // For each outgoing edge, increment the count of incoming edges for the
        // destination vertex
        int dest = adjListArr[i].get(j);
        rootVertex[dest]++;
      }
    }

    // Initialize a variable to track the root vertex
    int root = -1;

    // Loop through the rootVertex array to find the root vertex
    for (int i = 0; i < rootVertex.length; i++) {
      // If the current vertex has no incoming edges
      if (rootVertex[i] == 0) {
        // If we already found a root vertex, return -1 (more than one root)
        if (root != -1) {
          return -1;
        }
        // Set the current vertex as the root
        root = i;
      }
    }

    // If we found a root vertex, return its value
    if (root != -1) {
      return vertexValues.get(root);
    }

    // If no root vertex was found, return -1
    return -1;

  } // end of findRoot method
}
