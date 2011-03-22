package com.luugiathuy.games.reversi;


import java.util.ArrayList;
import java.util.PriorityQueue;

import com.luugiathuy.games.reversi.Agent.MoveCoord;
import com.luugiathuy.games.reversi.Agent.MoveScore;

public class Evaluation {
	
	private static final int sBOARD_SIZE = Reversi.sBOARD_SIZE;
	
	private static int[][] sBOARD_VALUE = {
		{100, -1, 5, 2, 2, 5, -1, 100},
		{-1, -10,1, 1, 1, 1,-10, -1},
		{5 , 1,  1, 1, 1, 1,  1,  5},
		{2 , 1,  1, 0, 0, 1,  1,  2},
		{2 , 1,  1, 0, 0, 1,  1,  2},
		{5 , 1,  1, 1, 1, 1,  1,  5},
		{-1,-10, 1, 1, 1, 1,-10, -1},
		{100, -1, 5, 2, 2, 5, -1, 100}};
	
	public static int evaluateBoard(char[][] board, char piece, char oppPiece) {
		int score = 0;
		for (int r = 0; r < sBOARD_SIZE; ++r) {
			for (int c = 0; c < sBOARD_SIZE; ++c) {
				if (board[r][c] == piece)
					score += sBOARD_VALUE[r][c];
				else if (board[r][c] == oppPiece)
					score -= sBOARD_VALUE[r][c];
			}
		}
		return score;
	}
	
	public static ArrayList<MoveCoord> genPriorityMoves(char[][] board, char piece) {
		ArrayList<MoveCoord> moveList = Reversi.findValidMove(board, piece, false);
		PriorityQueue<MoveScore> moveQueue = new PriorityQueue<MoveScore>();
		
		for (int i=0; i < moveList.size(); ++i) {
			MoveCoord move = moveList.get(i);
			MoveScore moveScore = new MoveScore(move, sBOARD_VALUE[move.getRow()][move.getCol()]);
			moveQueue.add(moveScore);
		}
		
		moveList = new ArrayList<MoveCoord>();
		while (!moveQueue.isEmpty()) {
			moveList.add(moveQueue.poll().getMove());
		}
		
		return moveList;
	}
	
	
}
