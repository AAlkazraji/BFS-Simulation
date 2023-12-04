/*
 * Ahmed Al-khazraji
 * c3277545@uon.edu.au
 * 2/10/2022
 * */

import java.util.ArrayList;
import java.util.Stack;
import java.util.concurrent.ThreadLocalRandom;

public class RandomWalkTechnique {
    private Stack<MazeCell> currStack, preOrderStack;
    private Maze maze;

    public RandomWalkTechnique(Maze maze) {
        this.maze = maze;
        currStack = new Stack<>();
        preOrderStack = new Stack<>();
        int randomRow = ThreadLocalRandom.current().nextInt(0, maze.getRows());
        int randomCol = ThreadLocalRandom.current().nextInt(0, maze.getCols());
        maze.getGrid()[randomRow][randomCol].setAsStart(true);
        generateMazeWithRWT(maze.getGrid()[randomRow][randomCol]);
    }

    private void generateMazeWithRWT(MazeCell currentCell) {
        if (!currentCell.isVisted()) {
            currStack.push(currentCell);
            currentCell.markVisted(true);
            preOrderStack.push(currentCell);
        }
        MazeCell next = getRandomNeighbour(currentCell);
        if (next != null) {
            removeWalls(currentCell, next);
            generateMazeWithRWT(next);
        } else if (!currStack.empty()) {
            next = currStack.pop();
            generateMazeWithRWT(next);
        } else {
            preOrderStack.peek().setAsFinish(true);
        }
    }

    // this method will go around the 4 neighbours and check if the visted, if not then will add them to the list
    private MazeCell getRandomNeighbour(MazeCell current) {
        ArrayList<MazeCell> neighbours = new ArrayList<>();
        MazeCell[][] mazeGrid = maze.getGrid();
        int neighboursColumn, neighbourRow;
        neighbourRow = current.getRow() - 1;
        neighboursColumn = current.getCol();
        if (!(neighbourRow < 0 || neighbourRow > maze.getRows() - 1 || neighboursColumn < 0 || neighboursColumn > maze.getCols() - 1)) {
            if (!mazeGrid[neighbourRow][neighboursColumn].isVisted()) {
                neighbours.add(mazeGrid[neighbourRow][neighboursColumn]);
            }
        }
        neighbourRow = current.getRow();
        neighboursColumn = current.getCol() + 1;
        if (!(neighbourRow < 0 || neighbourRow > maze.getRows() - 1 || neighboursColumn < 0 || neighboursColumn > maze.getCols() - 1)) {
            if (!mazeGrid[neighbourRow][neighboursColumn].isVisted()) {
                neighbours.add(mazeGrid[neighbourRow][neighboursColumn]);
            }
        }
        neighbourRow = current.getRow() + 1;
        neighboursColumn = current.getCol();
        if (!(neighbourRow < 0 || neighbourRow > maze.getRows() - 1 || neighboursColumn < 0 || neighboursColumn > maze.getCols() - 1)) {
            if (!mazeGrid[neighbourRow][neighboursColumn].isVisted()) {
                neighbours.add(mazeGrid[neighbourRow][neighboursColumn]);
            }
        }
        neighbourRow = current.getRow();
        neighboursColumn = current.getCol() - 1;
        if (!(neighbourRow < 0 || neighbourRow > maze.getRows() - 1 || neighboursColumn < 0 || neighboursColumn > maze.getCols() - 1)) {
            if (!mazeGrid[neighbourRow][neighboursColumn].isVisted()) {
                neighbours.add(mazeGrid[neighbourRow][neighboursColumn]);
            }
        }
        if (neighbours.size() > 0) {
            int randomIndex = ThreadLocalRandom.current().nextInt(0, neighbours.size());
            return neighbours.get(randomIndex);
        } else {
            return null;
        }
    }

    // Verify if the bottom wall of the current next is at the top or not.
    // Verify whether the following is to the right of the current.
    // verify whether the next is to the left of the current next's right wall before removing it.
    // verify if the bottom wall of the current must be removed to make room for the next.
    private void removeWalls(MazeCell current, MazeCell next) {
        if (next.getRow() == (current.getRow() - 1)) {
            if (next.getSurroundingWalls() == 0) {
                next.setWallsDir(2);
            } else if (next.getSurroundingWalls() == 1) {
                next.setWallsDir(3);
            }
        } else if (next.getCol() == (current.getCol() - 1)) {
            if (next.getSurroundingWalls() == 0) {
                next.setWallsDir(1);
            } else if (next.getSurroundingWalls() == 2) {
                next.setWallsDir(3);
            }
        } else if (next.getCol() == (current.getCol() + 1)) {
            if (current.getSurroundingWalls() == 0) {
                current.setWallsDir(1);
            } else if (current.getSurroundingWalls() == 2) {
                current.setWallsDir(3);
            }
        } else if (next.getRow() == (current.getRow() + 1)) {
            if (current.getSurroundingWalls() == 0) {
                current.setWallsDir(2);
            } else if (current.getSurroundingWalls() == 1) {
                current.setWallsDir(3);
            }
        }
    }
}
