/*
 * Ahmed Al-khazraji
 * c3277545@uon.edu.au
 * 2/10/2022
 * */

public class Maze {
    private MazeCell[][] grid;
    private int size, rows, cols;

    public Maze(int rows, int cols) {
        grid = new MazeCell[rows][cols];
        this.rows = rows;
        this.cols = cols;
        this.size = rows * cols;
        makeGrid();
    }

    // Setters
    public void setGrid(MazeCell[][] grid){this.grid = grid;}

    public void setSize(int size){this.size = size;}

    public void setRows(int rows){this.rows = rows;}

    public void setCols(int cols){this.cols = cols;}

    // Getters
    public MazeCell[][] getGrid(){return grid;}

    public int getSize(){return size;}

    public int getRows(){return rows;}

    public int getCols(){return cols;}

    // this function will run 2 seted loops to set the positions of the end cell
    public void setEndCell(int endCell) {
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                if (grid[i][j].getCellNumber() == endCell) {
                    grid[i][j].setAsFinish(true);
                    break;
                }
            }
        }
    }

    // this function will run 2 seted loops to set the positions of the start cell
    public void setStartCell(int startCell) {
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                if (grid[i][j].getCellNumber() == startCell) {
                    grid[i][j].setAsStart(true);
                    break;
                }
            }
        }
    }

    // this function will run 2 seted loops to return the postions of the start cell
    public int getStartCell() {
        int cellNum = 0;
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                if (grid[i][j].isStart()) {
                    cellNum = grid[i][j].getCellNumber();
                    break;
                }
            }
        }
        return cellNum;
    }

    // this function will run 2 seted loops to return the postions of the end cell
    public int getEndCell() {
        int cellNum = 0;
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                if (grid[i][j].isFinish()) {
                    cellNum = grid[i][j].getCellNumber();
                    break;
                }
            }
        }
        return cellNum;
    }

    // this function will run 2 seted loops to return the positions of the start cell to be used in solving algorithm
    public MazeCell getStartCellSolve() {
        MazeCell mazeCellNum = null;
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                if (grid[i][j].isStart()) {
                    mazeCellNum = grid[i][j];
                    break;
                }
            }
        }
        return mazeCellNum;
    }


    public void updateOpeness(int[] cellOpeness) {
        int counter = 0;
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                grid[i][j].setWallsDir(cellOpeness[counter]);
                counter++;
            }
        }
    }

    //this function will make the random maze by running 2 nested loops for making the
    // rows and the colmns
    private void makeGrid() {
        int cellNum = 1;
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                grid[i][j] = new MazeCell(cellNum, i, j);
                cellNum++;
            }
        }
    }
}