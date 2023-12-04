/*
 * Ahmed Al-khazraji
 * c3277545@uon.edu.au
 * 2/10/2022
 * */

import java.util.ArrayList;
import java.util.Stack;

public class BreadthFirstSearch {
    private Maze maze;
    private double timeTaken;
    private Stack<MazeCell> currQueue, orderQueue;
    private int stepsTaken;
    private long startTime, finishTime;

    public BreadthFirstSearch(Maze maze) {
        this.maze = maze;
        timeTaken = 0;
        stepsTaken = 0;
        startTime = System.nanoTime();
        currQueue = new Stack<>();
        orderQueue = new Stack<>();
        solveWithBFS(maze.getStartCellSolve());
    }

    // getters
    public int getStepsTaken(){return stepsTaken;}

    public Stack<MazeCell> getOrderStack(){return orderQueue;}

    public double getTimeTaken(){return timeTaken;}

    // the mechanisim of this function is going to the right cell check if there isnt any walls then check if they visted, the oder as follows
    // go to the top neighbour then check if there no wall to the top then check if not visited
    // go to the right neighbour then check if there no walls to the right then check if not visited
    // go to the bottom neighbour then check if there no walls to the bottom then check if not visited
    // go to the left neighbour then check if there no walls to the left then check if not visited
    // If nearby cells are there then choose the appropriate cell to process.
    private void getNeighbour(MazeCell current) {
        ArrayList<MazeCell> currentNeighbours = new ArrayList<>();
        MazeCell[][] mazeGrid = maze.getGrid();
        int neighbourColumn, neighbourRow;
        neighbourRow = current.getRow() - 1;
        neighbourColumn = current.getCol();
        if (!(neighbourRow < 0 || neighbourRow > maze.getRows() - 1 || neighbourColumn < 0 || neighbourColumn > maze.getCols() - 1)) {
            if (!(mazeGrid[neighbourRow][neighbourColumn].getSurroundingWalls() == 0
                    || mazeGrid[neighbourRow][neighbourColumn].getSurroundingWalls() == 1)) {
                if (!mazeGrid[neighbourRow][neighbourColumn].isVisted()) {
                    currentNeighbours.add(mazeGrid[neighbourRow][neighbourColumn]);
                }
            }
        }
        neighbourRow = current.getRow();
        neighbourColumn = current.getCol() + 1;
        if (!(neighbourRow < 0 || neighbourRow > maze.getRows() - 1 || neighbourColumn < 0 || neighbourColumn > maze.getCols() - 1)) {
            if (!(current.getSurroundingWalls() == 0 || current.getSurroundingWalls() == 2)) {
                if (!mazeGrid[neighbourRow][neighbourColumn].isVisted()) {
                    currentNeighbours.add(mazeGrid[neighbourRow][neighbourColumn]);
                }
            }
        }
        neighbourRow = current.getRow();
        neighbourColumn = current.getCol() - 1;
        if (!(neighbourRow < 0 || neighbourRow > maze.getRows() - 1 || neighbourColumn < 0 || neighbourColumn > maze.getCols() - 1)) {
            if (!(mazeGrid[neighbourRow][neighbourColumn].getSurroundingWalls() == 0
                    || mazeGrid[neighbourRow][neighbourColumn].getSurroundingWalls() == 2)) {
                if (!mazeGrid[neighbourRow][neighbourColumn].isVisted()) {
                    currentNeighbours.add(mazeGrid[neighbourRow][neighbourColumn]);
                }
            }
        }
        neighbourRow = current.getRow() + 1;
        neighbourColumn = current.getCol();
        if (!(neighbourRow < 0 || neighbourRow > maze.getRows() - 1 || neighbourColumn < 0 || neighbourColumn > maze.getCols() - 1)) {
            if (!(current.getSurroundingWalls() == 0 || current.getSurroundingWalls() == 1)) {
                if (!mazeGrid[neighbourRow][neighbourColumn].isVisted()) {
                    currentNeighbours.add(mazeGrid[neighbourRow][neighbourColumn]);
                }
            }
        }
        if (currentNeighbours.size() > 0) {
            for (MazeCell temp : currentNeighbours) {
                currQueue.add(temp);
            };
        }
    }

    // the mechanism of this methode will recive a cell then check if finished, if finished cell found then marks as done otherwise will call getNeighbour() and send through the cell to get
    // unvisted cell that nearby this cell
    private void solveWithBFS(MazeCell currentCell) {
        orderQueue.push(currentCell);
        if (currentCell.isFinish()) {
            markAsDone();
            return;
        }
        if (!currentCell.isVisted()) {
            currentCell.markVisted(true);
        }
        getNeighbour(currentCell);
        MazeCell next = currQueue.pop();
        if (next != null) {
            solveWithBFS(next);
        }
    }

    // mark as done method will calculate the time take by the algorithem to solve the maze by substracting the start time of the finshed time
    // then divide it by the 100000 to convert from nanoseconds to milliseconds, as well as returning the steps taken to finish solving the maze
    private void markAsDone() {
        stepsTaken = orderQueue.size() - 1;
        finishTime = System.nanoTime();
        timeTaken = finishTime - startTime;
        timeTaken = timeTaken / 1000000;
    }
}
