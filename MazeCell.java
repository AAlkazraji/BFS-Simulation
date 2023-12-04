/*
* Ahmed Al-khazraji
* c3277545@uon.edu.au
* 2/10/2022
* this opject to hold data to have right opject orinted selutions
* */


public class MazeCell {
    private Boolean visted, isStart, isFinish;
    private int cellNumber, wallsDir, row, col;

    public MazeCell(int cellNumber, int row, int col) {
        this.cellNumber = cellNumber;
        this.isStart = false;
        this.isFinish = false;
        this.row = row;
        this.col = col;
        this.visted = false;
        this.wallsDir = 0;

    }

    //Setters && Getters
    public Boolean isVisted() {
        return visted;
    }

    public int getRow() {
        return row;
    }

    public int getCol() {
        return col;
    }

    public void setAsFinish(Boolean finish) {
        this.isFinish = finish;
    }

    public void setWallsDir(int wallsDirections) {
        this.wallsDir = wallsDirections;
    }

    public void setAsStart(Boolean start) {
        this.isStart = start;
    }

    public void markVisted(Boolean visted) {
        this.visted = visted;
    }

    public Boolean isStart() {return isStart;}

    public int getCellNumber() {
        return cellNumber;
    }

    public int getSurroundingWalls() {
        return wallsDir;
    }

    public Boolean isFinish() {
        return isFinish;
    }

}
