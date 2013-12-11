/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package net.kirauks.tictactoe;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;

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
        COMPUTER(GameState.O),
        NOBODY(GameState.NULL);
        
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
    
    public List<Line> getGridLines(){
        List<Line> lines = new ArrayList<>();
        int index = 0;
        for(GameState[] lineDatas : this.gameStatus){
            lines.add(new Line(lineDatas, index));
            index++;
        }
        return lines;
    }
    public List<Cell> getGridStatus(Line line){
        List<Cell> cells = new ArrayList<>();
        int index = 0;
        for(GameState state : line.getDatas()){
            cells.add(new Cell(state, line.getIndex(), index));
            index++;
        }
        return cells;
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
    
    public void playPlayerTurn(int line, int col){
        this.play(GamePlayer.USER, line, col);
    }
    
    public void playComputerTurn(){
        int line = this.getRandomLineIndexWithEmptyCell();
        int col = this.getRandomEmptyCell(line);
        this.play(GamePlayer.COMPUTER, line, col);
    }
    
    private void play(GamePlayer player, int line, int col){
        if(this.gameStatus[line][col] == GameState.NULL){
            this.gameStatus[line][col] = player.state;
        }
    }
    
    private GamePlayer getPlayer(GameState state){
        for(GamePlayer player : GamePlayer.values()){
            if(player.state.equals(state)){
                return player;
            }
        }
        return null;
    }
    public GamePlayer getWinner(){
        //Lines
        for(int line = 0; line < GRID_SIZE; line++){
            GameState lineState = this.gameStatus[line][0];
            boolean win = true;
            for(int col = 0; col < GRID_SIZE; col++){
                if(!this.gameStatus[line][col].equals(lineState)){
                    win = false;
                    break;
                }
            }
            if(win){
                return this.getPlayer(lineState);
            }
        }
        //Cols
        for(int col = 0; col < GRID_SIZE; col++){
            GameState colState = this.gameStatus[0][col];
            boolean win = true;
            for(int line = 0; line < GRID_SIZE; line++){
                if(!this.gameStatus[line][col].equals(colState)){
                    win = false;
                    break;
                }
            }
            if(win){
                return this.getPlayer(colState);
            }
        }
        //Cross
        GameState pCrossState = this.gameStatus[0][0];
        GameState nCrossState = this.gameStatus[0][GRID_SIZE - 1];
        boolean pWin = true;
        boolean nWin = true;
        for(int index = 0; index < GRID_SIZE; index++){
            if(!this.gameStatus[index][index].equals(pCrossState)){
                pWin = false;
            }
            if(!this.gameStatus[index][GRID_SIZE - 1 - index].equals(nCrossState)){
                nWin = false;
            }
        }
        if(pWin){
            return this.getPlayer(pCrossState);
        }
        else if(nWin){
            return this.getPlayer(nCrossState);
        }
        else{
            return GamePlayer.NOBODY;
        }
    }
    
    private static final Random rand = new Random();
    private int getRandomLineIndexWithEmptyCell(){
        List<Integer> indexes = new ArrayList();
        int index = 0;
        for(GameState[] line : this.gameStatus){
            boolean hasEmpty = false;
            for(GameState cell : line){
                if(cell == GameState.NULL){
                    hasEmpty = true;
                    break;
                }
            }
            if(hasEmpty){
                indexes.add(new Integer(index));
            }
            index++;
        }
        return indexes.get(rand.nextInt(indexes.size()));
    }
    private int getRandomEmptyCell(int line){
        List<Integer> indexes = new ArrayList();
        int index = 0;
        for(GameState cell : this.gameStatus[line]){
            if(cell == GameState.NULL){
                indexes.add(new Integer(index));
            }
            index++;
        }
        return indexes.get(rand.nextInt(indexes.size()));
    }
}
