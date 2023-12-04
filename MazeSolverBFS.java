/*
 * Ahmed Al-khazraji
 * c3277545@uon.edu.au
 * 2/10/2022
 * */

import java.io.File;
import java.util.Scanner;
import java.util.Stack;

public class MazeSolverBFS {
    public static void main(String[] args) {
        if (args.length < 1 || !args[0].contains(".")){
            System.out.println("Invalid inputs");
            return;
        }
        File file = new File(args[0]);

        // reading from file and setting the data on form that specified in the assigment specs (n,m:start_node:finish_node:cell_openness_list)
        // ten calling makeMaze() and send the size of the maze
        try {
            Scanner inputFile = new Scanner(file);
            int n, m, startCell, endCell;
            String input = inputFile.next();
            String[] nums = input.split("[,:]");
            n = Integer.parseInt(nums[0]);
            m = Integer.parseInt(nums[1]);
            startCell = Integer.parseInt(nums[2]);
            endCell = Integer.parseInt(nums[3]);
            String openessNums = nums[4];
            int[] cellOpeness = new int[openessNums.length()];
            for (int i = 0; i < cellOpeness.length; i++) {
                cellOpeness[i] = Integer.parseInt(openessNums.substring(i, i + 1));
            }
            inputFile.close();
            makeMaze(n, m, startCell, endCell, cellOpeness);
        } catch (Exception e){
        }
    }

    private static void makeMaze(int n, int m, int startCell, int endCell, int[] cellOpenness) {
        Maze maze = new Maze(n, m);
        maze.setStartCell(startCell);
        maze.setEndCell(endCell);
        maze.updateOpeness(cellOpenness);
        BreadthFirstSearch bfs = new BreadthFirstSearch(maze);
        if(m <= 10 && n <= 10){
            printMaze(maze, maze.getGrid());
        }
        System.out.println("\nResults of solving the maze by using BFS:");
        System.out.println(getPathString(bfs));
        System.out.println(bfs.getStepsTaken());
        System.out.println(String.format("%.10f", bfs.getTimeTaken()) + " MS\n");
    }

    private static Stack<MazeCell> listOrder(Stack<MazeCell> orderStack) {
        Stack<MazeCell> cellsListOrder = new Stack<>();
        while (!orderStack.empty()) {
            cellsListOrder.add(orderStack.pop());
        }
        return cellsListOrder;
    }

    // this method will return a string of the steps take by BFS in the specified format of the assignment
    private static String getPathString(BreadthFirstSearch BreadthFirstSearch) {
        String stepsToSolve = "(";
        Stack<MazeCell> listOfStepsToSolve = listOrder(BreadthFirstSearch.getOrderStack());
        while (!listOfStepsToSolve.empty()) {
            stepsToSolve += listOfStepsToSolve.pop().getCellNumber();
            if (!listOfStepsToSolve.empty()) {
                stepsToSolve += ",";
            }
        }
        stepsToSolve += ")";
        return stepsToSolve;
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
                    System.out.print(grid[i][j].isVisted() ? " *" : "  ");
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