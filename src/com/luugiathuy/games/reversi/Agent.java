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

/**
 * This is an interface for ReversiAgents to work with the GUI interface.
 */
public interface Agent {

	/**
	 * Method to make a move. game state is stored in the class variables
	 *
	 * Method for you to implement. You want to make your modifications here.
	 *
	 * @return Returns a move.
	 */
	MoveCoord findMove(char[][] board, char piece);

	/**
	 * class that implements a pair of integer coordinates
	 */
	public class MoveCoord {
		private int row;

		private int col;

		/**
		 * constructor for a Pair of coordinates
		 */
		public MoveCoord(int row, int col) {
			this.row = row;
			this.col = col;
		}

		/** accessor methods */
		public int getRow() {
			return this.row;
		}

		public int getCol() {
			return this.col;
		}

		/** mutation methods */
		public void setRow(int row) {
			this.row = row;
		}

		public void setCol(int col) {
			this.col = col;
		}


		/** takes a pair of x,y coordinates, converts to standard board notation */
		public static String encode(int row, int col) {
			return ("" + new Character((char) ('A' + col)) + (row + 1));
		}
	}
	
	/**
	* Class for presenting the move and the score together
	*/
	public class MoveScore implements Comparable<MoveScore>{
		private MoveCoord move ;
	    private int score ;
	    
	    public MoveScore(MoveCoord move, int score){
	        this.move = move;
	        this.score = score;
	    }
	    
	    public int getScore(){ 
	    	return score ;
	    }
	    
	    public MoveCoord getMove(){ 
		  	return move ;
	   }

		@Override
		public int compareTo(MoveScore o) {
			if(o.score > this.score)
				return 1;
			else if (o.score < this.score)
				return -1;
			else
				return 0;
		}
	}
}