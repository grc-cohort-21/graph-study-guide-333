import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class Practice 
{

  /**
   * #1
   * Returns the count of vertices with odd values that can be reached from the given starting vertex.
   * The starting vertex is included in the count if its value is odd.
   * If the starting vertex is null, returns 0.
   *
   * Example:
   * Consider a graph where:
   *   5 --> 4
   *   |     |
   *   v     v
   *   8 --> 7 < -- 1
   *   |
   *   v
   *   9
   * 
   * Starting from 5, the odd nodes that can be reached are 5, 7, and 9.
   * Thus, given 5, the number of reachable odd nodes is 3.
   * @param starting the starting vertex (may be null)
   * @return the number of vertices with odd values reachable from the starting vertex
   */
  
   //count vertices odd
public static int oddVertices(Vertex<Integer> starting) 
{
    //keeps track of vertices visited
    Set<Vertex<Integer>> visited = new HashSet<>();
    //call helper method with stater and visited
    return oddVertices(starting, visited);
  }

  //herlper method, recursive method 
  public static int oddVertices(Vertex<Integer> starting, Set<Vertex<Integer>> visited) 
  {
    //base case no starting point or already visited then return 0
    if (starting == null || visited.contains(starting)) 
    {
      return 0;
    }
    //visited marker
    visited.add(starting);
    //tracker at 0 pr 1 with mod operator
    int count = 0;
    if (starting.data % 2 == 1) 
    {
      count = 1;
    }
    //neighbor set, gos through them and count odd
    for (Vertex<Integer> neighbor : starting.neighbors) 
    {
      count += oddVertices(neighbor, visited);
    }
    //return odd total
    return count;
  }


    




  /**
   * 
   * #2
   * 
   * Returns a *sorted* list of all values reachable from the starting vertex (including the starting vertex itself).
   * If duplicate vertex data exists, duplicates should appear in the output.
   * If the starting vertex is null, returns an empty list.
   * They should be sorted in ascending numerical order.
   *
   * Example:
   * Consider a graph where:
   *   5 --> 8
   *   |     |
   *   v     v
   *   8 --> 2 <-- 4
   * When starting from the vertex with value 5, the output should be:
   *   [2, 5, 8, 8]
   *
   * @param starting the starting vertex (may be null)
   * @return a sorted list of all reachable vertex values by 
   */
  //method calls outside
  public static List<Integer> sortedReachable(Vertex<Integer> starting) 
  {
    //set keeps track of already visited
    Set<Vertex<Integer>> visited = new HashSet<>();
    //get the list of reachable values
    List<Integer> list = sortReachable(starting, visited);
    //sort the list 
    Collections.sort(list);
    //return sorted list
    return list;
  }

  //helper method recursive, to collect reachable values
  public static List<Integer> sortReachable(Vertex<Integer> starting, Set<Vertex<Integer>> visited) 
  {
    //create a new list to store results
    List<Integer> list = new ArrayList<>();
    //starting vertex is null or contains start then return empty list
    if (starting == null || visited.contains(starting)) 
    {
      return list;
    }
    //dont go there again if already visited
    visited.add(starting);
    //add to the list
    list.add(starting.data);
    //visit the neighbors and add the values
    for (Vertex<Integer> neighbor : starting.neighbors) 
    {
      list.addAll(sortReachable(neighbor, visited));
    }
    //reaturn reachable values
    return list;
  }



  /**
   * #3
   * 
   * Returns a sorted list of all values reachable from the given starting vertex in the provided graph.
   * The graph is represented as a map where each key is a vertex and its corresponding value is a set of neighbors.
   * It is assumed that there are no duplicate vertices.
   * If the starting vertex is not present as a key in the map, returns an empty list.
   *
   * @param graph a map representing the graph
   * @param starting the starting vertex value
   * @return a sorted list of all reachable vertex values
   */
  public static List<Integer> sortedReachable(Map<Integer, Set<Integer>> graph, int starting) 
  {
    //visited set named visted
    Set<Integer> visited = new HashSet<>();
    //list of reachable numbers, recursion 
    List<Integer> list = sortedReachable(graph, starting, visited);
    //sort the list
    Collections.sort(list);
    return list;
  }
  //recurcsive method named sorted reachable 
  public static List<Integer> sortedReachable(Map<Integer, Set<Integer>> graph, int starting, Set<Integer> visited) 
  {
    //tracker list of ones reachable
    List<Integer> list = new ArrayList<>();
    //do not carry on if visisted or not a key
    if (visited.contains(starting) || !graph.containsKey(starting)) 
    {
      return list;
    }
    //track visited marking it
    visited.add(starting);
    //add it the list of visited
    list.add(starting);
    //visited nighbords and add their reachable values
    for (int neighbor : graph.get(starting)) 
    {
      list.addAll(sortedReachable(graph, neighbor, visited));
    }
    return list;
  }
  



  /**
   * 
   * #4
   * 
   * Returns true if and only if it is possible both to reach v2 from v1 and to reach v1 from v2.
   * A vertex is always considered reachable from itself.
   * If either v1 or v2 is null or if one cannot reach the other, returns false.
   *
   * Example:
   * If v1 and v2 are connected in a cycle, the method should return true.
   * If v1 equals v2, the method should also return true.
   *
   * @param <T> the type of data stored in the vertex
   * @param v1 the starting vertex
   * @param v2 the target vertex
   * @return true if there is a two-way connection between v1 and v2, false otherwise
   */
 public static <T> boolean twoWay(Vertex<T> v1, Vertex<T> v2) 
 {
  //vertex is null or not connect return false
    if (v1 == null || v2 == null) 
    {
      return false;
    }
    //same vertex, reachable then
    if (v1 == v2) 
    {
      return true;
    }
    //a set to keep track of visited vertices
    Set<Vertex<T>> visited = new HashSet<>();
    //check from v1 and so on with helper method
    return twoWay(v1, v2, v1, visited);
  }

  //helper recursive method
  public static <T> boolean twoWay(Vertex<T> v1, Vertex<T> v2, Vertex<T> current, Set<Vertex<T>> visited) 
  {
    //track the visited and return back to start
    if (visited.contains(current) && v1 == current) 
    {
      return true;
    }
    //null or contains visited then stop
    if (current == null || visited.contains(current)) 
    {
      return false;
    }

    //mark as visited
    visited.add(current);
    //visit neighbors
    for (Vertex<T> neighbor : current.neighbors) 
    {
      //recurse
      if (twoWay(v1, v2, neighbor, visited) && visited.contains(v2)) 
      {
        return true;
      }
    }
    return false;
  }




  /**
   * 
   * #5
   * 
   * Returns whether there exists a path from the starting to ending vertex that includes only positive values.
   * 
   * The graph is represented as a map where each key is a vertex and each value is a set of directly reachable neighbor vertices. A vertex is always considered reachable from itself.
   * If the starting or ending vertex is not positive or is not present in the keys of the map, or if no valid path exists,
   * returns false.
   *
   * @param graph a map representing the graph
   * @param starting the starting vertex value
   * @param ending the ending vertex value
   * @return whether there exists a valid positive path from starting to ending
   */
  public static boolean positivePathExists(Map<Integer, Set<Integer>> graph, int starting, int ending) 
  {
    //track nodes that have been visited
    Set<Integer> visited = new HashSet<>();
    //start search from starting value
    return positivePathExists(graph, ending, starting, visited);
  }

  //helper recursive method 
  public static boolean positivePathExists(Map<Integer, Set<Integer>> graph, int ending, int current,Set<Integer> visited) 
  {
    //if negaive or already visited then flase, stop
    if (current < 0 || visited.contains(current)) 
    {
      return false;
    }
    //if target reached then return true
    if (current == ending) 
    {
      return true;
    }
    //mark it as visited
    visited.add(current);
    //vivist neighbors
    for (int neighbor : graph.get(current)) 
    {
      if (positivePathExists(graph, ending, neighbor, visited)) 
      {
        return true;
      }
    }
    return false;
  }



  /**
   * #6
   * 
   * Returns true if a professional has anyone in their extended network (reachable through any number of links)
   * that works for the given company. The search includes the professional themself.
   * If the professional is null, returns false.
   *
   * @param person the professional to start the search from (may be null)
   * @param companyName the name of the company to check for employment
   * @return true if a person in the extended network works at the specified company, false otherwise
   */
 public static boolean hasExtendedConnectionAtCompany(Professional person, String companyName) 
 {
    Set<Professional> visited = new HashSet<>();
    return hasExtendedConnectionAtCompany(person, companyName, visited);
  }

  //helper method with  a visited set 
  //prevent infinite loop
  public static boolean hasExtendedConnectionAtCompany(Professional person, String companyName, Set<Professional> visited) 
  {
    // If the person is null or we've already checked them, stop
    if (person == null || visited.contains(person)) 
    {
      return false;
    }
    //string comparision
    if (person.getCompany() == companyName) 
    {
      return true;
    }

    //mark it person as visited
    visited.add(person);

    //check the connection
    for (Professional conntection : person.getConnections()) 
    {
      if (hasExtendedConnectionAtCompany(conntection, companyName, visited)) 
      {
        return true;
      }
    }
    return false;
  }



  /**
   * #7
   * 
   * Returns a list of possible next moves starting from a given position.
   * 
   * Starting from current, which is a [row, column] location, a player can move 
   * by one according to the directions provided.
   * 
   * The directions are given as a 2D array, where each inner array is a [row, column]
   * pair that describes a move. For example, if given the below array it would be possible
   * to move to the right, up, down, or down/left diagonally.
   * {
   *  {0, 1},  // Right
   *  {-1, 0}, // Up
   *  {1, 0},  // Down
   *  {1, -1}  // Down/left diagonal
   * }
   * 
   * However, the player can not move off the edge of the board, or onto any 
   * location that has an 'X' (capital X).
   * 
   * The possible moves are returned as a List of [row, column] pairs. The List
   * can be in any order.
   * 
   * Example:
   * 
   * board: 
   * {
   *  {' ', ' ', 'X'},
   *  {'X', ' ', ' '},
   *  {' ', ' ', ' '}
   * }
   * 
   * current:
   * {1, 2}
   * 
   * directions:
   * {
   *  {0, 1},  // Right
   *  {-1, 0}, // Up
   *  {1, 0},  // Down
   *  {1, -1}  // Down/left diagonal
   * }
   * 
   * expected output (order of list is unimportant):
   * [{2, 2}, {2, 1}]
   * 
   * Explanation:
   * The player starts at {1, 2}.
   * The four directions the player might have to go are right, up, down, and down/left (based on the directions array).
   * They cannot go right because that would go off the edge of the board.
   * They cannot go up because there is an X.
   * They can go down.
   * They can go down/left.
   * The resultant list has the two possible positions.
   * 
   * 
   * You can assume the board is rectangular.
   * You can assume valid input (no nulls, properly sized arrays, current is in-bounds,
   * directions only move 1 square, any row/column pairs are arrays of length 2,
   * directions are unique).
   * 
   * If there are no possible moves, the method returns an empty list.
   * 
   * @param board a rectangular array where 'X' represent an impassible location
   * @param current the [row, column] starting position of the player
   * @param directions an array of [row, column] possible directions
   * @return an unsorted list of next moves
   */
  public static List<int[]> nextMoves(char[][] board, int[] current, int[][] directions) 
  {
    //row
    int r = current[0];
    //column
    int c = current[1];

    //list named next storing possible moves
    List<int[]> next = new ArrayList<>();

    for (int[] move : directions) 
    {
      //new row after the move
      int newR = r + move[0];
      //new column after the move
      int newC = c + move[1];

      //if not an x and exists then its a legit move
      if (newR >= 0 && newR < board.length &&
          newC >= 0 && newC < board[newR].length &&
          board[newR][newC] == ' ') 
        {
          //add legit move to the list 
        next.add(new int[] { newR, newC });
      }
    }
    return next;
  }
}