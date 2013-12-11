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
public class Line{
    private GameBean.GameState[] lineDatas;
    private int lineIndex;
    
    public Line(GameBean.GameState[] lineDatas, int lineIndex){
        this.lineDatas = lineDatas;
        this.lineIndex = lineIndex;
    }

    public GameBean.GameState[] getDatas() {
        return lineDatas;
    }

    public int getIndex() {
        return lineIndex;
    }
}
