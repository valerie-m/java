/*
 * Player.java
 * Summative Culminating Assignment
 * ICS3U1
 * Vanessa Mac
 * Jan 15 2020
 * The stonesGame package contains the files for the Stones Game Application.
 */


/**
 * The Player Class contains the background code for the Stones Application.
 * The methods in this class are instantiated in the Board class as the homeBin object.
 * The Player class handles methods that modify or access the home bins.
 */

public class Player {

	private int[] homeBinBoard;

	/**
	 * constructor 
	 * pre: none 
	 * post: Home bins are initialized with a value of 0.
	 */
	
	public Player() {

		homeBinBoard = new int[2]; 

		homeBinBoard[0] = 0; // Player 1 home bin

		homeBinBoard[1] = 0; // Player 2 home bin
	}

	
	/**
	 * Returns the value of player 1's home bin. 
	 * pre: none 
	 * post: the number of stones in player 1's home bin is returned.
	 */
	public String getPlayer1HomeBin() {

		String numStones = String.valueOf(homeBinBoard[0]);

		return (numStones);
	}

	/**
	 * Returns the value of player 2's home bin. 
	 * pre: none 
	 * post: the number of stones in player 2's home bin is returned.
	 */
	public String getPlayer2HomeBin() {

		String numStones = String.valueOf(homeBinBoard[1]);

		return (numStones);
	}

	/**
	 * Adds a stone to player 1's home bin.
	 * pre: the stone being added as been moved by player 1.  
	 * post: a stone is added to player 1's home bin.
	 */
	public void addHomeBinPlayer1() {
		homeBinBoard[0]++;
	}

	/**
	 * Adds a stone to player 2's home bin.
	 * pre: the stone being added as been moved by player 2.  
	 * post: a stone is added to player 2's home bin.
	 */
	public void addHomeBinPlayer2() {
		homeBinBoard[1]++;
	}

	/**
	 * Checks if a player receives stones from the opposite/opponent's pits.
	 * pre: the last stone must land in an empty pit on the player's side. 
	 * post: the stones from the opposite/opponent's pit are removed and added to the proper home bin.
	 */
	public void doRoundCheck(int a, int b) {

		if (a == 1) {
			homeBinBoard[0] += b;
		} else if (a == 2) {
			homeBinBoard[1] += b;
		}
	}
	
	/**
	 * Adds remaining stones to the home bin of the player whose pits are empty first.
	 * pre: all pits, excluding home bin must be empty.
	 * post: all remaining stones are removed from all pits and added to the player home bin who had zero stones first.
	 */
	public void endGame(int finalTotal, int player) {
		if (player == 1) {
			homeBinBoard[0] += finalTotal;
		} else if (player == 2) {
			homeBinBoard[1] += finalTotal;
		}
	}

	/**
	 * A winner is determined.
	 * pre: all of the player's pits must have a value of 0 and all stones used during the game should be in the home bins.
	 * post: 0 is returned if draw, 1 is returned if player 1 wins, 2 is returned if player 2 wins
	 */
	public int determineWinner() {
		if (homeBinBoard[0] == homeBinBoard[1]) {
			return 0;
		} else if (homeBinBoard[0] > homeBinBoard[1]) {
			return 1;
		} else if (homeBinBoard[0] < homeBinBoard[1]) {
			return 2;
		}

		return 0;
	}

}
