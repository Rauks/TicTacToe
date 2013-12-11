/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package net.kirauks.tictactoe;

/**
 *
 * @author Karl
 */
public class Cell{
    private int cellLine;
    private int cellCol;
    private GameBean.GameState state;

    public Cell(GameBean.GameState state, int cellLine, int cellCol) {
        this.state= state;
        this.cellLine = cellLine;
        this.cellCol = cellCol;
    }

    public GameBean.GameState getState(){
        return this.state;
    }

    public int getLine() {
        return this.cellLine;
    }

    public int getCol() {
        return this.cellCol;
    }
}
