/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package net.kirauks.tictactoe;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 *
 * @author Karl
 */
public class GameBean {
    private static final int GRID_SIZE = 3;
    
    public enum GameState{
        NULL, O, X;
    }
    public enum GamePlayer{
        USER(GameState.X),
        COMPUTER(GameState.O);
        
        private GameState state;
        private GamePlayer(GameState state){
            this.state = state;
        }
    }
    
    private boolean userFirst = true;
    private GameState[][] gameStatus;
    
    public GameBean(){
        this.gameStatus = new GameState[GRID_SIZE][GRID_SIZE];
    }
    
    public class Line{
        private GameState[] lineDatas;
        private Line(GameState[] lineDatas){
            this.lineDatas = lineDatas;
        }
    }
    public List<Line> getGridLines(){
        List<Line> lines = new ArrayList<>();
        for(GameState[] lineDatas : this.gameStatus){
            lines.add(new Line(lineDatas));
        }
        return lines;
    }
    public List<GameState> getGridStatus(Line line){
        return Arrays.asList(line.lineDatas);
    }
    
    public void setStartByUser(boolean userFirst){
        this.userFirst = userFirst;
    }

    public void startGame(){
        for (int line = 0; line < GRID_SIZE; line++){
            for (int col = 0; col < GRID_SIZE; col++){
                this.gameStatus[line][col] = GameState.NULL;
            }
        }
        if(!this.userFirst){
            this.play(GamePlayer.COMPUTER, 1, 1);
        }
    }
    
    private void playPlayerTurn(int line, int col){
        this.play(GamePlayer.USER, line, col);
        this.play(GamePlayer.USER, 0, 0);
    }
    
    private void play(GamePlayer player, int line, int col){
        if(this.gameStatus[line][col] != GameState.NULL){
            this.gameStatus[line][col] = player.state;
        }
    }
    
}