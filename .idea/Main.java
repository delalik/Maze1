import java.io.File;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        int startX = -1, startY = -1;
        char[][] maze = null;


        try (Scanner scanner = new Scanner(new File("/Users/delalikumapley/IdeaProjects/MazeProject/src/maze.dat"))) {
            int row = 0;
            while (scanner.hasNext()) {
                if (maze == null) {
                    String[] dimensions = scanner.nextLine().split(" ");
                    maze = new char[Integer.parseInt(dimensions[0])][Integer.parseInt(dimensions[1])];
                } else {
                    maze[row] = scanner.nextLine().toCharArray();
                    if (startX == -1) {
                        for (int i = 0; i < maze[row].length; i++) {
                            if (maze[row][i] == '+') {
                                startX = row;
                                startY = i;
                            }
                        }
                    }
                    row++; //why does this work????
                }
            }
        } catch (java.io.IOException e) {
            e.printStackTrace();
        }

        while (maze[startX][startY] != '-') {
            maze = exploreNorth(maze, startX, startY);
            if (maze[startX][startY] != '-') break;
            maze = exploreSouth(maze, startX, startY);
            if (maze[startX][startY] != '-') break;
            maze = exploreEast(maze, startX, startY);
            if (maze[startX][startY] != '-') break;
            maze = exploreWest(maze, startX, startY);
        }

        System.out.println("Maze solved! Here's the traversed maze:");
        displayMaze(maze);
    }

    // Display the maze on the screen
    public static void displayMaze(char[][] maze) {
        for (char[] row : maze) {
            for (char cell : row) {
                System.out.print(cell);
            }
            System.out.println();
        }
    }
    public static char[][] exploreNorth(char[][] maze, int x, int y) {
        if (maze[x][y] == '-') return maze;
        maze[x][y] = '+';
        if (x > 0 && maze[x - 1][y] == ' ') maze = exploreNorth(maze, x - 1, y);
        else maze = exploreNext(maze, x, y);
        return maze;
    }

    public static char[][] exploreSouth(char[][] maze, int x, int y) {
        if (maze[x][y] == '-') return maze;
        maze[x][y] = '+';
        if (x < maze.length - 1 && maze[x + 1][y] == ' ') maze = exploreSouth(maze, x + 1, y);
        else maze = exploreNext(maze, x, y);
        return maze;
    }

    public static char[][] exploreEast(char[][] maze, int x, int y) {if (maze[x][y] == '-') return maze;
        maze[x][y] = '+';
        if (y < maze[0].length - 1 && maze[x][y + 1] == ' ') maze = exploreEast(maze, x, y + 1);
        else maze = exploreNext(maze, x, y);
        return maze;
    }

    public static char[][] exploreWest(char[][] maze, int x, int y) {
        if (maze[x][y] == '-') return maze;
        maze[x][y] = '+';
        if (y > 0 && maze[x][y - 1] == ' ') maze = exploreWest(maze, x, y - 1);
        else maze = exploreNext(maze, x, y);
        return maze;}

    // check back
    public static char[][] exploreNext(char[][] maze, int x, int y) {
        if (x > 0 && maze[x - 1][y] == '+') maze[x - 1][y] = '.';
        if (x < maze.length - 1 && maze[x + 1][y] == '+') maze[x + 1][y] = '.';
        if (y > 0 && maze[x][y - 1] == '+') maze[x][y - 1] = '.';
        if (y < maze[0].length - 1 && maze[x][y + 1] == '+') maze[x][y + 1] = '.';
        return maze;
    }
}
