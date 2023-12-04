/*
 * Ahmed Al-khazraji
 * c3277545@uon.edu.au
 * 2/10/2022
 * */

import java.io.PrintWriter;

public class MazeGenerator {
    public static void main(String[] args) {
        if (args.length < 3 || !args[2].contains(".")){
            System.out.println("Invalid inputs");
            return;
        }
        int n = Integer.parseInt(args[0]);
        int m = Integer.parseInt(args[1]);
        String outputFile = args[2];
        makeMaze(n, m, outputFile);
    }

    private static void printToFile(String outputFile, Maze maze) {
        String output = getOutput(maze);
        try {
            PrintWriter writer = new PrintWriter(outputFile, "UTF-8");
            writer.println(output);
            writer.close();
        } catch (Exception e) {
            System.out.println(e);
        }
    }

    // this method will create the maze by reaciving the size of the rows and colomns passed to it with file that has output data
    private static void makeMaze(int n, int m, String outputFile) {
        Maze maze = new Maze(n, m);
        MazeCell[][] grid = maze.getGrid();
        RandomWalkTechnique RandomWalkTechnique = new RandomWalkTechnique(maze);
        printToFile(outputFile, maze);
        if(m <= 10 && n <= 10){
            printMaze(maze, grid);
        }
    }


    private static String getOutput(Maze maze) {
        String output = "";
        output += maze.getRows() + "," + maze.getCols() + ":";
        output += maze.getStartCell() + ":" + maze.getEndCell() + ":";
        for (int i = 0; i < maze.getRows(); i++) {
            for (int j = 0; j < maze.getCols(); j++) {
                output += maze.getGrid()[i][j].getSurroundingWalls();
            }
        }
        return output;
    }

    // this methode will be called to print the maze if the row and columns size smaller than 10
    private static void printMaze(Maze maze, MazeCell[][] grid) {
        for (int i = 0; i < maze.getRows(); i++) {
            if (i == 0) {
                for (int j = 0; j < maze.getCols(); j++) {
                    System.out.print(" -- ");
                }
            }
            if(i == 0){
                System.out.println();
            }
            for (int j = 0; j < maze.getCols(); j++) {

                if (j == 0) {
                    System.out.print("|");
                } else {
                    System.out.print(" ");
                }
                if (grid[i][j].isStart()) {
                    System.out.print("S ");
                } else if (grid[i][j].isFinish()) {
                    System.out.print("F ");
                } else {
                    System.out.print("  ");
                }
                if (grid[i][j].getSurroundingWalls() == 0 || grid[i][j].getSurroundingWalls() == 2) {
                    System.out.print("|");
                } else {
                    System.out.print(" ");
                }
            }
            System.out.println("");
            for (int j = 0; j < maze.getCols(); j++) {
                if (grid[i][j].getSurroundingWalls() == 0 || grid[i][j].getSurroundingWalls() == 1) {
                    System.out.print(" -- ");
                } else if (i == maze.getRows() - 1) {
                    System.out.print(" -- ");
                } else {
                    System.out.print("    ");
                }
            }
            System.out.println("");
        }
    }
}
