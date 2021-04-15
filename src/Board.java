/*
 * Board.java
 * Summative Culminating Assignment
 * ICS3U1
 * Vanessa Mac
 * Jan 15 2020
 * The stonesGame package contains the files for the Stones Game Application.
 */


/**
 * The Board Class contains the background code for the Stones Application. The
 * methods in this class are instantiated in the Stones class as the game and
 * validate object.
 */

public class Board {

	private int[][] stonesBoard;
	private Player homeBin;

	/**
	 * constructor 
	 * pre: none 
	 * post: An empty board is created.
	 */
	public Board() {
		stonesBoard = new int[2][8];

		for (int row = 0; row <= 1; row++) {
			for (int col = 1; col < 7; col++) {
				stonesBoard[row][col] = 0;
			}
		}

		homeBin = new Player();
	}

	/**
	 * constructor 
	 * pre: none 
	 * post: stonesBoard array has been initialized with starting pit values and a Player object is created.
	 */
	public Board(int gameMode) {
		stonesBoard = new int[2][8];

		for (int row = 0; row <= 1; row++) {
			for (int col = 1; col < 7; col++) {
				stonesBoard[row][col] = gameMode;
			}
		}

		homeBin = new Player();
	}

	/**
	 * Moves the stones in the first row depending on chosen pit. 
	 * pre: the choice array must contain a valid pit and player must be equal to 1. 
	 * post: the stones selected by player 1 have been moved and added to home bin accordingly. An array with the stones' last position is returned.
	 */
	public int[] moveStonesPlayer1(int choice, int player) {
		int numStones;
		int lastPlayRow;
		int lastPlayCol;
		int lastStones;

		numStones = stonesBoard[0][choice];

		stonesBoard[0][choice] = 0;

		int position = choice - 1;
		int row = 0;

		for (int i = 0; i < numStones; i++) {

			if (position >= 1 && row == 0) {
				stonesBoard[row][position] += 1;
				position--;
			} else if (position == 0 && row == 0) {
				homeBin.addHomeBinPlayer1();
				row = 1;
				position = 1;
			} else if (position == 7 && row == 1) {
				row = 0;
				position = 6;
				i--;
			} else if (position >= 1 && row == 1) {
				stonesBoard[row][position]++;
				position++;
			}
		}

		if (row == 0 && position == 6) {
			stonesBoard[0][6] += 1;
		}

		if (row == 1 && position == 1) {
			row = 0;
			position = 0;
		} else if (position >= 0 && row == 0) {
			position++;
		} else if (position >= 0 && row == 1) {
			position--;
		}

		int[] returnArray = { (row), (position), 0 };

		lastPlayRow = (returnArray[0]);
		lastPlayCol = (returnArray[1]);

		if (stonesBoard[lastPlayRow][lastPlayCol] == 1 && lastPlayRow == 0 && stonesBoard[lastPlayRow + 1][lastPlayCol] != 0) {
			lastStones = stonesBoard[1][lastPlayCol];
			stonesBoard[1][lastPlayCol] = 0;
			homeBin.doRoundCheck(player, lastStones);
			returnArray[2] = 0;
		}  else {
			returnArray[2] = -1;
		}

		return (returnArray);
	}

	/**
	 * Moves the stones in the second row. 
	 * pre: the choice array must contain a valid pit and player must be equal to 2. 
	 * post: the stones selected by player 2 have been moved and added to home bin accordingly. An array with the stones' last position is returned.
	 */
	public int[] moveStonesPlayer2(int choice, int player) {
		int numStones;
		int lastPlayRow;
		int lastPlayCol;
		int lastStones;

		numStones = stonesBoard[1][choice];

		stonesBoard[1][choice] = 0;

		int row = 0;
		int position = 0;

		position = choice + 1;
		row = 1;

		for (int i = 0; i < numStones; i++) {

			if (position == 7 && row == 1) {
				homeBin.addHomeBinPlayer2();
				row = 0;
				position = 6;
			} else if (position >= 0 && row == 1) {
				stonesBoard[row][position] += 1;
				position++;
			} else if (position >= 1 && row == 0) {
				stonesBoard[row][position]++;
				position--;
			} else if (position == 0 && row == 0) {
				row = 1;
				position = 1;
				i--;
			}
		}

		if (row == 1 && position == 1) {
			stonesBoard[1][1] += 1;
		}

		if (row == 0 && position == 6) {
			row = 1;
			position = 7;
		} else if (position >= 1 && row == 0) {
			position++;
		} else if (position >= 1 && row == 1) {
			position--;
		}

		int[] returnArray = { (row), (position), 0};

		lastPlayRow = (returnArray[0]);
		lastPlayCol = (returnArray[1]);
		
		if (stonesBoard[lastPlayRow][lastPlayCol] == 1 && lastPlayRow == 1 && stonesBoard[lastPlayRow - 1][lastPlayCol] != 0) {
			lastStones = stonesBoard[0][lastPlayCol];
			stonesBoard[0][lastPlayCol] = 0;
			homeBin.doRoundCheck(player, lastStones);
			returnArray[2] = 0;
		} else {
			returnArray[2] = -1;
		}

		return (returnArray);
	}

	/**
	 * The number of stones in a pit are returned. 
	 * pre: r must be a valid row (0 or 1) and c must be a valid column number (0-7). 
	 * post: the number of stones in the accepted pit is returned.
	 */
	public int getNum(int r, int c) {

		int numStones;

		if (c >= 1 && c <= 6) {
			return stonesBoard[r][c];
		} else if (c == 0) {
			numStones = Integer.parseInt(homeBin.getPlayer1HomeBin());
			return numStones;
		} else if (c == 7) {
			numStones = Integer.parseInt(homeBin.getPlayer2HomeBin());
			return numStones;
		} else {
			return (-1);
		}
	}

	/**
	 * Determines if the game is over by checking for an empty row of pits. 
	 * pre: none 
	 * post: false is returned if the game is over and the remaining stones are added to the proper home bin. True is returned if the game continues.
	 */
	public boolean endGame() {

		int total;

		if (stonesBoard[0][1] == 0 && stonesBoard[0][2] == 0 && stonesBoard[0][3] == 0 && stonesBoard[0][4] == 0
				&& stonesBoard[0][5] == 0 && stonesBoard[0][6] == 0) {
			total = stonesBoard[1][1] + stonesBoard[1][2] + stonesBoard[1][3] + stonesBoard[1][4] + stonesBoard[1][5]
					+ stonesBoard[1][6];
			stonesBoard[1][1] = 0;
			stonesBoard[1][2] = 0;
			stonesBoard[1][3] = 0;
			stonesBoard[1][4] = 0;
			stonesBoard[1][5] = 0;
			stonesBoard[1][6] = 0;
			homeBin.endGame(total, 1);
			return (false);
		} else if (stonesBoard[1][1] == 0 && stonesBoard[1][2] == 0 && stonesBoard[1][3] == 0 && stonesBoard[1][4] == 0
				&& stonesBoard[1][5] == 0 && stonesBoard[1][6] == 0) {
			total = stonesBoard[0][1] + stonesBoard[0][2] + stonesBoard[0][3] + stonesBoard[0][4] + stonesBoard[0][5]
					+ stonesBoard[0][6];
			stonesBoard[0][1] = 0;
			stonesBoard[0][2] = 0;
			stonesBoard[0][3] = 0;
			stonesBoard[0][4] = 0;
			stonesBoard[0][5] = 0;
			stonesBoard[0][6] = 0;
			homeBin.endGame(total, 2);
			return (false);
		} else {
			return (true);
		}
	}

	/**
	 * Determines the results of the game by retrieving the home bin values from the Player class. 
	 * pre: all stones should be in the home bins (game has ended). 
	 * post: an integer value is returned to determine which player wins or if there is a draw.
	 */
	public int getResults() {
		int winner = homeBin.determineWinner();
		return (winner);
	}

	/**
	 * Determines if a player received a free turn. 
	 * pre: arr must contain one of 12 valid pit positions and player must be a 0 or 1. 
	 * post: true is returned if the last position is the player's home bin, false if otherwise.
	 */
	public boolean determineFreeTurn(int player, int[] arr) {

		if (arr[0] == 0 && arr[1] == 0 && player == 0) {
			return (true);
		} else if (arr[0] == 1 && arr[1] == 7 && player == 1) {
			return (true);
		} else {
			return (false);
		}

	}

	/**
	 * Determines if a valid number of starting stones is inputed by the user. 
	 * pre: none 
	 * post: true is returned if a valid value is entered, false if otherwise.
	 */
	public boolean validateGameMode(int a, int b) {
		if (a >= 2 && a <= 5 && b >= 1 && b <= 2) {
			return (true);
		} else {
			return (false);
		}
	}
}
