/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package net.kirauks.tictactoe.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import net.kirauks.tictactoe.GameBean;
import net.kirauks.tictactoe.GameBean.GamePlayer;

/**
 *
 * @author Karl
 */
public class GameServlet extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
        GameBean game = (GameBean) request.getSession(true).getAttribute("gameBean");
        
        int line = Integer.parseInt(request.getParameter("Line"));
        int col = Integer.parseInt(request.getParameter("Col"));
        
        game.playPlayerTurn(line, col);
        
        GamePlayer winner = game.getWinner();
        switch(winner){
            case NOBODY:
                if(game.hasEmptyCell()){
                    game.playComputerTurn();
                    switch(game.getWinner()){
                        case NOBODY:
                            break;
                        case COMPUTER:
                            request.setAttribute("winner", "L'ordinateur");
                            break;
                        case USER:
                            request.setAttribute("winner", "Je");
                            break;
                    }
                }
                break;
            case COMPUTER:
                request.setAttribute("winner", "L'ordinateur");
                break;
            case USER:
                request.setAttribute("winner", "Je");
                break;
        }
        if(winner == GamePlayer.NOBODY && !game.hasEmptyCell()){
            request.setAttribute("winner", "Personne");
        }
        request.getRequestDispatcher("/game.jsp").forward(request, response);
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
