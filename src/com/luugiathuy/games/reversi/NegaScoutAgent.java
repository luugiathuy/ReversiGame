/**
Copyright (c) 2011-present - Luu Gia Thuy

Permission is hereby granted, free of charge, to any person
obtaining a copy of this software and associated documentation
files (the "Software"), to deal in the Software without
restriction, including without limitation the rights to use,
copy, modify, merge, publish, distribute, sublicense, and/or sell
copies of the Software, and to permit persons to whom the
Software is furnished to do so, subject to the following
conditions:

The above copyright notice and this permission notice shall be
included in all copies or substantial portions of the Software.

THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND,
EXPRESS OR IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES
OF MERCHANTABILITY, FITNESS FOR A PARTICULAR PURPOSE AND
NONINFRINGEMENT. IN NO EVENT SHALL THE AUTHORS OR COPYRIGHT
HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER LIABILITY,
WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING
FROM, OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR
OTHER DEALINGS IN THE SOFTWARE.
*/

package com.luugiathuy.games.reversi;

import java.util.ArrayList;

public class NegaScoutAgent implements Agent{
        
    static final int INFINITY = 1000000;
    
    private int mMaxPly = 5;
	
	@Override
	public MoveCoord findMove(char[][] board, char piece) {
		return abNegascoutDecision(board, piece);
	}
	
	public MoveCoord abNegascoutDecision(char[][] board, char piece){
    	MoveScore moveScore = abNegascout(board,0,-INFINITY,INFINITY,piece);
    	return moveScore.getMove();
    }
    
    /**
     * Searching the move using NegaScout
     * @param board the state of game
     * @param ply the depth of searching
     * @param alpha the boundary
     * @param beta the boundary
     * @param player the player will find the move
     * @return the pair of move and score
     */
    public MoveScore abNegascout(char[][] board, int ply, int alpha, int beta, char piece){
    	char oppPiece = (piece == Reversi.sBLACK_PIECE) ? Reversi.sWHITE_PIECE : Reversi.sBLACK_PIECE;
    	
    	// Check if we have done recursing
    	if (ply==mMaxPly){
            return new MoveScore(null, Evaluation.evaluateBoard(board, piece, oppPiece));
        }
    		
    	int currentScore;
    	int bestScore = -INFINITY;
    	MoveCoord bestMove = null;
    	int adaptiveBeta = beta; 	// Keep track the test window value
    	
    	// Generates all possible moves
    	ArrayList<MoveCoord> moveList = Evaluation.genPriorityMoves(board, piece);
    	if (moveList.isEmpty())
    		return new MoveScore(null, bestScore);
    	bestMove = moveList.get(0);
    	
    	// Go through each move
    	for(int i=0;i<moveList.size();i++){
    		MoveCoord move = moveList.get(i);
    		char[][] newBoard = new char[8][8];
    		for (int r = 0; r < 8; ++r)
    			for (int c=0; c < 8; ++c)
    				newBoard[r][c] = board[r][c];
    		Reversi.effectMove(newBoard, piece, move.getRow(), move.getCol());
    		
    		// Recurse
    		MoveScore current = abNegascout(newBoard, ply+1, -adaptiveBeta, - Math.max(alpha,bestScore), oppPiece);
    		
    		currentScore = - current.getScore();
    		
    		// Update bestScore
    		if (currentScore>bestScore){
    			// if in 'narrow-mode' then widen and do a regular AB negamax search
    			if (adaptiveBeta == beta || ply>=(mMaxPly-2)){
    				bestScore = currentScore;
					bestMove = move;
    			}else{ // otherwise, we can do a Test
    				current = abNegascout(newBoard, ply+1, -beta, -currentScore, oppPiece);
    				bestScore = - current.getScore();
    				bestMove = move;
    			}
    			
    			// If we are outside the bounds, the prune: exit immediately
        		if(bestScore>=beta){
        			return new MoveScore(bestMove,bestScore);
        		}
        		
        		// Otherwise, update the window location
        		adaptiveBeta = Math.max(alpha, bestScore) + 1;
    		}
    	}
    	return new MoveScore(bestMove,bestScore);
    }
}
