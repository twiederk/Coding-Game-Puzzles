import java.math.BigInteger;
import java.util.*;

/**
 * Auto-generated code below aims at helping you parse
 * the standard input according to the problem statement.
 **/
class Player {

  public static void main(String args[]) {
    int depth = 24;

    int[] versuch1 = {060222161, 322444322, 20};
    int[] versuch2 = {506450064, 951223336, 20};
    int[] versuch3 = {555505055, 36379286, 1};
    int[] versuch4 = {616101616, 264239762, 1};
    int[] versuch5 = {606000615, 76092874,  8};
    int[] versuch6 = {300362102, 661168294, 24};


    Node[][] nodes = convertInputToArray("606000615");

    System.err.println("Start State: " + getGameState(nodes));
    System.err.println("Depth: " + depth);


    int finalSum = findStates(new Board(depth, nodes), 3);

    System.out.println(finalSum);
  }

  private static int findStates(Board board, int size) {

    int sum = 0;
    Queue<Board> queue = new LinkedList<>();
    queue.add(board);

    while (!queue.isEmpty()) {
      Board current = queue.poll();

      String gameState = current.getGameState();
      System.err.println("Zwischenstand: " + gameState);
      System.err.println("Versuche: " + current.turnsLeft);

      if (isBoardFull(current.boardState) || current.turnsLeft < 1) {
        System.err.println("Endstand: " + getGameState(current.boardState));
        System.err.println("Versuche: " + current.turnsLeft);

        BigInteger finalBoardValue = getBoardRepresentation(current.boardState);
        BigInteger numRepr = BigInteger.valueOf(sum).add(finalBoardValue);

        BigInteger pow = BigInteger.valueOf(1073741824);
        BigInteger result = numRepr.mod(pow);
        sum = result.intValue();

      } else  {
        List<Node> emptyNodes = current.findEmptyNodes();
        for (Node node : emptyNodes) {
          int currentTurnsLeft = current.turnsLeft;
          Node[][] currentBoardState = copyNodes(current.boardState);
          List<Node> neighbours = findNeighbours(currentBoardState, node.row, node.col, size);
          int neighboursPoints = getValueSum(neighbours);
          if (neighbours.size() > 1 && neighboursPoints > 1 && neighboursPoints <= 6) {
            List<List<Node>> combinations = createCombinations(neighbours);
            currentTurnsLeft--;
            for (List<Node> combination : combinations) {
              currentBoardState = copyNodes(current.boardState);
              int currentPoints = getValueSum(combination);
              Node newNode = new Node(node.row, node.col, currentPoints);
              currentBoardState[node.row][node.col] = newNode;
              for (Node neighbour : combination) {
                Node newNeighbour = new Node(neighbour.row, neighbour.col, 0);
                currentBoardState[neighbour.row][neighbour.col] = newNeighbour;
              }

              Board newBoard = new Board(currentTurnsLeft, currentBoardState);
              queue.add(newBoard);
            }
          } else {
            Node newNode = new Node(node.row, node.col, 1);
            currentBoardState[node.row][node.col] = newNode;
            currentTurnsLeft--;

            Board newBoard = new Board(currentTurnsLeft, currentBoardState);
            queue.add(newBoard);
          }

        }
      }
    }
    return sum;
  }

  private static List<List<Node>> createCombinations(List<Node> input) {
    List<List<Node>> combinations = new ArrayList<>();

    for (Node node : input) {
      List<List<Node>> interimList = new ArrayList<>();
      for (List<Node> combination : combinations) {
        List<Node> newCombination = new ArrayList<>(combination);
        if (!newCombination.contains(node)) {
          newCombination.add(node);
        }
        if(!combination.contains(interimList)) {
          interimList.add(newCombination);
        }

      }
      combinations.addAll(interimList);
      List<Node> combination = new ArrayList<>();
      Node newNode = new Node(node.row, node.col, node.value);
      combination.add(newNode);
      combinations.add(combination);
    }

    System.err.println("Combinations: " + combinations);
    List<List<Node>> finalCombinations = new ArrayList<>();
    for (List<Node> combination : combinations) {
      if (combination.size() > 1) {
        finalCombinations.add(combination);
      }
    }
    System.err.println("Final Combinations: " + finalCombinations);
    return finalCombinations;
  }


  private static int getValueSum(List<Node> neighbours) {
    int sum = 0;
    for(Node neighbour : neighbours) {
      sum += neighbour.value;
    }
    return sum;
  }

  private static List<Node> findNeighbours(Node[][] nodes, int row, int col, int size) {
    List<Node> neighbours = new ArrayList<>();

    int left = col - 1;
    int right = col + 1;
    int top = row - 1;
    int bottom = row + 1;

    if (top >= 0 && nodes[top][col].value != 0) {
      neighbours.add(new Node(top, col, nodes[top][col].value));
    }
    if (left >= 0 && nodes[row][left].value != 0) {
      neighbours.add(new Node(row, left, nodes[row][left].value));
    }
    if (right < size && nodes[row][right].value != 0) {
      neighbours.add(new Node(row, right, nodes[row][right].value));
    }
    if (bottom < size && nodes[bottom][col].value != 0) {
      neighbours.add(new Node(bottom, col, nodes[bottom][col].value));
    }

    return neighbours;
  }

  private static Node[][] convertInputToArray(String input) {
    char[] chars = input.toCharArray();
    Node[][] nodes = new Node[3][3];
    int index = 0;
    for(char ch: chars) {
      int value = ch - '0';
      int row = index / 3;
      int col = index % 3;
      nodes[row][col] = new Node(row, col, value);
      index++;
    }
    return nodes;
  }

  private static BigInteger getBoardRepresentation(Node[][] boardState) {
    String numString = getGameState(boardState);
    return new BigInteger(numString);
  }

  private static String getGameState(Node[][] nodes) {
    String numString = "";
    for (int i = 0; i < 3; i++) {
      for (int j = 0; j < 3; j++) {
        numString = numString.concat(String.valueOf(nodes[i][j].value));
      }
    }
    return numString;
  }



  private static Node[][] copyNodes(Node[][] initialNodes) {
    Node nodes[][] = new Node[3][3];
    for (int i = 0; i < 3; i++) {
      for (int j = 0; j < 3; j++) {
        Node node = initialNodes[i][j];
        nodes[i][j] = new Node(node.row, node.col, node.value);
      }
    }
    return nodes;
  }

  private static boolean isBoardFull(Node[][] nodes) {
    for (int i = 0; i < 3; i++) {
      for (int j = 0; j < 3; j++) {
        Node node = nodes[i][j];
        if(node.value == 0) {
          return false;
        }
      }
    }
    return true;
  }
}

class Board {
  int turnsLeft;
  Node[][] boardState;

  public Board(int turnsLeft, Node[][] boardState) {
    this.turnsLeft = turnsLeft;
    this.boardState = copyBoardState(boardState);
  }

  private Node[][] copyBoardState(Node[][] initialNodes) {
    Node nodes[][] = new Node[3][3];
    for (int i = 0; i < 3; i++) {
      for (int j = 0; j < 3; j++) {
        Node node = initialNodes[i][j];
        nodes[i][j] = new Node(node.row, node.col, node.value);
      }
    }
    return nodes;
  }

  public String getGameState() {
    String numString = "";
    for (int i = 0; i < 3; i++) {
      for (int j = 0; j < 3; j++) {
        numString = numString.concat(String.valueOf(boardState[i][j].value));
      }
    }
    return numString;
  }

  public List<Node> findEmptyNodes() {

    List<Node> empty = new ArrayList<>();
    for (int i = 0; i < 3; i++) {
      for (int j = 0; j < 3; j++) {
        Node node = boardState[i][j];
        if (node.value == 0) {
          empty.add(new Node(node.row, node.col, node.value));
        }
      }
    }
    return empty;
  }
}

class Node {

  int value;
  int row;
  int col;

  public Node(int row, int col, int value) {
    this.row = row;
    this.col = col;
    this.value = value;
  }

  @Override
  public boolean equals(Object obj) {
    if (obj instanceof Node) {
      Node node = (Node) obj;
      return row == node.row && col == node.col && value == node.value;
    }
    return false;
  }

  @Override
  public int hashCode() {
    return Objects.hash(value, row, col);
  }

  @Override
  public String toString() {
    return "Node{" + "value=" + value + ", row=" + row + ", col=" + col + '}';
  }
}
