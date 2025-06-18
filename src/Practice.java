import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class Practice {

  /**
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
  public static int oddVertices(Vertex<Integer> starting) {
    Set<Vertex<Integer>> myVisited = new HashSet<>();
    return oddVerticesHelper(starting, myVisited);
  }

  public static int oddVerticesHelper(Vertex<Integer> vertex, Set<Vertex<Integer>> visited)
  {
    if (vertex == null || visited.contains(vertex)) return 0;

    int oddCount = 0;
    visited.add(vertex);

    if (vertex.data % 2 != 0) 
    {
      oddCount++;
    }

    for (Vertex<Integer> neighbor : vertex.neighbors)
    {
      oddCount += oddVerticesHelper(neighbor, visited);
    }

    return oddCount;
  }

  public static int sumEvenVertices(Vertex<Integer> starting)
  {
    Set<Vertex<Integer>> myVisited = new HashSet<>();
    return sumEvenVertices(starting, myVisited);
  }

  public static int sumEvenVertices(Vertex<Integer> vertex, Set<Vertex<Integer>> visited)
  {
    if (vertex == null || visited.contains(vertex)) return 0;

    int sum = 0;
    visited.add(vertex);

    if (vertex.data % 2 == 0)
    {
      sum += vertex.data;
    }
    
    for (Vertex<Integer> neighbor : vertex.neighbors)
    {
      sum += sumEvenVertices(neighbor, visited);
    }

    return sum;
  }

  public static int countAbove(Vertex<Integer> starting, int threshold)
  {
    Set<Vertex<Integer>> myVisited = new HashSet<>();
    return countAbove(starting, threshold, myVisited);
  }

  public static int countAbove(Vertex<Integer> vertex, int threshold, Set<Vertex<Integer>> visited)
  {
    if (vertex == null || visited.contains(vertex)) return 0;

    visited.add(vertex);
    int count = 0;

    if (vertex.data > threshold)
    {
      count++;
    }

    for (Vertex<Integer> neighbor : vertex.neighbors)
    {
      count += countAbove(neighbor, threshold, visited);
    }

    return count;
  }

  public static boolean containsValue(Vertex<Integer> starting, int target)
  {
    Set<Vertex<Integer>> myVisited = new HashSet<>();
    return containsValue(starting, target, myVisited);
  }

  public static boolean containsValue(Vertex<Integer> vertex, int target, Set<Vertex<Integer>> visited)
  {
    if (vertex == null || visited.contains(vertex)) return false;

    visited.add(vertex);

    if (vertex.data == target) return true;

    for (Vertex<Integer> neighbor : vertex.neighbors)
    {
      return containsValue(neighbor, target, visited);
    }

    return false;
  }

  public static List<Integer> collectValues(Vertex<Integer> starting) {
    Set<Vertex<Integer>> myVisited = new HashSet<>();
    return collectValues(starting, myVisited);
  }

  public static List<Integer> collectValues(Vertex<Integer> vertex, Set<Vertex<Integer>> visited)
  {
    ArrayList<Integer> list = new ArrayList<>();

    if (vertex == null || visited.contains(vertex)) return list; // could also say return new ArrayList<>();

    visited.add(vertex);

    list.add(vertex.data);

    for (Vertex<Integer> neighbor : vertex.neighbors)
    {
      list.addAll(collectValues(neighbor, visited));
    }

    return list;
  }


  /**
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
  public static List<Integer> sortedReachable(Vertex<Integer> starting) {
    Set<Vertex<Integer>> myVisited = new HashSet<>();
    return sortedReachable(starting, myVisited);
  }

  public static List<Integer> sortedReachable(Vertex<Integer> vertex, Set<Vertex<Integer>> visited)
  {
    ArrayList<Integer> list = new ArrayList<>();

    if (vertex == null || visited.contains(vertex)) return list;

    visited.add(vertex);

    list.add(vertex.data);

    for (Vertex<Integer> neighbor : vertex.neighbors)
    {
      list.addAll(sortedReachable(neighbor, visited));
    }

    Collections.sort(list);

    return list;
  }

  /**
   * Returns a sorted list of all values reachable from the given starting vertex in the provided graph.
   * The graph is represented as a map where each key is a vertex and its corresponding value is a set of neighbors.
   * It is assumed that there are no duplicate vertices.
   * If the starting vertex is not present as a key in the map, returns an empty list.
   *
   * @param graph a map representing the graph
   * @param starting the starting vertex value
   * @return a sorted list of all reachable vertex values
   */
  public static List<Integer> sortedReachable(Map<Integer, Set<Integer>> graph, int starting) {
    Set<Integer> myVisited = new HashSet<>();
    return sortedReachable(graph, starting, myVisited);
  }

  public static List<Integer> sortedReachable(Map<Integer, Set<Integer>> graph, int vertex, Set<Integer> visited) {
    ArrayList<Integer> list = new ArrayList<>();

    if (!graph.containsKey(vertex) || visited.contains(vertex)) return list;

    visited.add(vertex);
    list.add(vertex);

    for (int neighbor : graph.get(vertex))
    {
      list.addAll(sortedReachable(graph, neighbor, visited));
    }

    Collections.sort(list);

    return list;
  }

  /**
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
  public static <T> boolean twoWay(Vertex<T> v1, Vertex<T> v2) {
    Set<Vertex<T>> myVisited_v1 = new HashSet<>();
    Set<Vertex<T>> myVisited_v2 = new HashSet<>();
    return twoWay(v1, myVisited_v1, v2) && twoWay(v2, myVisited_v2, v1);
  }

  public static <T> boolean twoWay(Vertex<T> vertex, Set<Vertex<T>> visited, Vertex<T> target)
  {
    if (vertex == null || visited.contains(vertex)) return false;
    if (vertex == target) return true;

    visited.add(vertex);

    for (Vertex<T> neighbor : vertex.neighbors)
    {
      if (twoWay(neighbor, visited, target))
      {
        return true;
      }
    }

    return false;
  }

  /**
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
  public static boolean positivePathExists(Map<Integer, Set<Integer>> graph, int starting, int ending) {
    Set<Integer> myVisited = new HashSet<>();
    return positivePathExists(graph, starting, ending, myVisited);
  }

  public static boolean positivePathExists(Map<Integer, Set<Integer>> graph, int vertex, int ending, Set<Integer> visited) {
    if (vertex < 0 || ending < 0 || !graph.containsKey(vertex) || !graph.containsKey(ending) || visited.contains(vertex)) return false;

    visited.add(vertex);

    if (vertex == ending) return true;

    for (int neighbor : graph.get(vertex))
    {
      if (positivePathExists(graph, neighbor, ending, visited))
      {
        return true;
      }
    }

    return false; // if no valid path exists
  }

  /**
   * Returns true if a professional has anyone in their extended network (reachable through any number of links)
   * that works for the given company. The search includes the professional themself.
   * If the professional is null, returns false.
   *
   * @param person the professional to start the search from (may be null)
   * @param companyName the name of the company to check for employment
   * @return true if a person in the extended network works at the specified company, false otherwise
   */
  public static boolean hasExtendedConnectionAtCompany(Professional person, String companyName) {
    Set<Professional> myVisited = new HashSet<>();
    return hasExtendedConnectionAtCompany(person, companyName, myVisited);
  }

  public static boolean hasExtendedConnectionAtCompany(Professional currentPerson, String companyName, Set<Professional> visited)
  {
    if (currentPerson == null || visited.contains(currentPerson)) return false;

    visited.add(currentPerson);

    if (currentPerson.getCompany() == companyName) return true;

    for (Professional neighbor : currentPerson.getConnections())
    {
      if (hasExtendedConnectionAtCompany(neighbor, companyName, visited))
      {
        return true;
      }
    }
    return false;
  }

  /**
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
  public static List<int[]> nextMoves(char[][] board, int[] current, int[][] directions) {
    List<int[]> moves = new ArrayList<>();
    
    int curR = current[0];
    int curC = current[1];

    for (int[] dir : directions) {
      int newR = curR + dir[0];
      int newC = curC + dir[1];

      if (newR >= 0 && newR < board.length && newC >= 0 && newC < board[0].length && board[newR][newC] != 'X')
      {
        moves.add(new int[]{newR, newC});
      }
    }

    return moves;
  }
}
